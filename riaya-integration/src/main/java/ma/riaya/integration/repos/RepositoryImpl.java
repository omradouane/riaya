/**
 * 
 */
package ma.riaya.integration.repos;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.integration.repos.query.QueryTool;
import ma.riaya.integration.util.AssertTool;
import ma.riaya.integration.util.ReflectionTool;
import ma.riaya.model.dictionary.BaseObject;

import org.apache.log4j.Logger;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class RepositoryImpl<T extends BaseObject> implements IRepository<T> {

	private static final Logger log = Logger.getLogger(RepositoryImpl.class);

	private static final String PERSISTENCE_UNIT_NAME = "riaya";

	private Class<T> domainClass;

	private EntityManager em;

	private RepositoryImpl() {
		if (em == null) {
			final EntityManagerFactory emfactory = Persistence
					.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = emfactory.createEntityManager();
		}
	}

	public RepositoryImpl(final Class<T> domainClass) {
		this();
		this.domainClass = domainClass;
	}

	/**
	 * Get the em
	 *
	 * @author ouledmoussa
	 * @return the em
	 */
	@Override
	public EntityManager getEm() {
		return this.em;
	}

	/**
	 * Get the domainClass
	 *
	 * @author ouledmoussa
	 * @return the domainClass
	 */
	@Override
	public Class<T> getDomainClass() {
		return domainClass;
	}

	@Override
	public List<T> findAll() throws IntegrationException {
		log.info("findAll start");
		final TypedQuery<T> allQuery = getAllQuery(null);
		final List<T> result = allQuery.getResultList();
		log.info("findAll end " + result.size());
		return result;
	}

	@Override
	public List<T> findAll(final Order order) throws IntegrationException {
		throw new IntegrationException("Not yet implemented !!");
	}

	@Override
	public T save(final T entity) throws IntegrationException {
		log.info("save start " + entity);
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		log.info("save end " + entity);
		return entity;
	}

	@Override
	public Optional<T> findOne(final Long id) throws IntegrationException {
		log.info("findOne start " + id);
		final T t = em.find(domainClass, id);
		log.info("findOne end " + t);
		return Optional.of(t);
	}

	@Override
	public boolean exists(final Long id) throws IntegrationException {
		log.info("exists start " + id);
		if (id == null) {
			throw new IllegalArgumentException(
					"The given id must not be null !");
		}
		final String existsQuery = QueryTool
				.getExistsQueryString(this.domainClass);
		final TypedQuery<Long> query = em.createQuery(existsQuery, Long.class);
		query.setParameter("id", id);
		final boolean result = query.getSingleResult() == 1L;
		log.info("exists end " + result);
		return result;
	}

	@Override
	public long count() throws IntegrationException {
		log.info("count start ");
		final String qlString = String.format(QueryTool.COUNT_QUERY_STRING,
				this.domainClass.getSimpleName(), "%s");
		final TypedQuery<Long> tq = em.createQuery(qlString, Long.class);
		final Long c = tq.getSingleResult();
		log.info("count end " + c);
		return c;
	}

	@Override
	public void delete(final Long id) throws IntegrationException {
		log.info("delete start " + id);
		final Optional<T> op = findOne(id);
		final T t = op.orElseThrow(() -> new IntegrationException(String
				.format("No entity with id %s exists", id)));
		delete(t);
		log.info("delete end " + id);
	}

	@Override
	public void delete(final T entity) throws IntegrationException {
		log.info("delete start " + entity);
		em.remove(em.contains(entity) ? entity : em.merge(entity));
		log.info("delete end " + entity);
	}

	@Override
	public void deleteAll() throws IntegrationException {
		log.info("deleteAll start ");
		final Stream<T> all = findAll().stream();
		all.parallel().forEach(t -> {
			try {
				delete(t);
			} catch (final Exception e) {
				log.error("Can't delete " + t, e);
			}
		});
		log.info("deleteAll end ");
	}

	@Override
	public void deleteAllFast() throws IntegrationException {
		log.info("deleteAllFast start ");
		final String delQuery = String.format(
				QueryTool.DELETE_ALL_QUERY_STRING,
				this.domainClass.getSimpleName(), "%s");
		final int nbrOfDeleted = em.createQuery(delQuery).executeUpdate();
		log.info("deleteAllFast end " + nbrOfDeleted);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<T> findBy(final String attributeName, final Object value) {
		log.info("findBy start " + attributeName + "=" + value);
		final Field field = ReflectionTool.findField(this.domainClass,
				attributeName);

		AssertTool.notNull(field, String.format(
				"The attribute %s isn't part of %s", attributeName,
				this.domainClass));
		final Class<?> clazz = field.getType();

		AssertTool
				.isTrue(value.getClass().isAssignableFrom(clazz), String
						.format("The object %s must be of type %s", value,
								clazz));

		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<T> cq = builder.createQuery(this.domainClass);
		final Root<T> rootEntry = cq.from(this.domainClass);

		final ParameterExpression p = builder.parameter(clazz);
		final CriteriaQuery<T> all = cq.select(rootEntry).where(
				builder.equal(rootEntry.get(attributeName), p));
		final TypedQuery<T> allQuery = em.createQuery(all);
		allQuery.setParameter(p, value);

		final List<T> result = allQuery.getResultList();
		log.info("findBy end " + result.size());
		return result;
	}
}
