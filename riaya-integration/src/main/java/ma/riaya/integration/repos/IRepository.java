/**
 * 
 */
package ma.riaya.integration.repos;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.integration.util.Constants;
import ma.riaya.model.dictionary.BaseObject;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public interface IRepository<T extends BaseObject> {
	
	final String PERSISTENCE_UNIT_NAME = System.getProperty(Constants.PU_KEY) == null ? Constants.PROD_PU
			: System.getProperty(Constants.PU_KEY);
	
	public default TypedQuery<T> getAllQuery(final Order order) {
		final CriteriaQuery<T> all = getAllCriteriaQuery();
		if (order != null) {
			all.orderBy(order);
		}
		final TypedQuery<T> allQuery = getEm().createQuery(all);
		return allQuery;
	}

	public default CriteriaQuery<T> getAllCriteriaQuery() {
		final CriteriaBuilder builder = getEm().getCriteriaBuilder();
		final CriteriaQuery<T> cq = builder.createQuery(getDomainClass());
		final Root<T> rootEntry = cq.from(getDomainClass());
		final CriteriaQuery<T> all = cq.select(rootEntry);
		return all;
	}

	EntityManager getEm();

	Class<T> getDomainClass();

	List<T> findAll() throws IntegrationException;

	List<T> findAll(Order order) throws IntegrationException;

	/**
	 * Saves a given entity. Use the returned instance for further operations as
	 * the save operation might have changed the entity instance completely.
	 * 
	 * @param entity
	 * @return the saved entity
	 */
	T save(T entity) throws IntegrationException;

	/**
	 * Retrieves an entity by its id.
	 * 
	 * @param id
	 *            must not be {@literal null}.
	 * @return the entity with the given id or {@literal null} if none found
	 * @throws IllegalArgumentException
	 *             if {@code id} is {@literal null}
	 */
	Optional<T> findOne(Long id) throws IntegrationException;

	/**
	 * Returns whether an entity with the given id exists.
	 * 
	 * @param id
	 *            must not be {@literal null}.
	 * @return true if an entity with the given id exists, {@literal false}
	 *         otherwise
	 * @throws IllegalArgumentException
	 *             if {@code id} is {@literal null}
	 */
	boolean exists(Long id) throws IntegrationException;

	/**
	 * Returns the number of entities available.
	 * 
	 * @return the number of entities
	 */
	long count() throws IntegrationException;

	/**
	 * Deletes the entity with the given id.
	 * 
	 * @param id
	 *            must not be {@literal null}.
	 * @throws IllegalArgumentException
	 *             in case the given {@code id} is {@literal null}
	 */
	void delete(Long id) throws IntegrationException;

	/**
	 * Deletes a given entity.
	 * 
	 * @param entity
	 * @throws IllegalArgumentException
	 *             in case the given entity is (@literal null}.
	 */
	void delete(T entity) throws IntegrationException;

	/**
	 * Deletes all entities managed by the repository.
	 */
	void deleteAll() throws IntegrationException;

	/**
	 * Deletes all entities managed by the repository in a batch call.
	 */
	void deleteAllFast() throws IntegrationException;

	public List<T> findBy(final String attributeName, final Object value);
}
