/**
 * 
 */
package ma.riaya.integration.repos;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.model.folder.social.Orphan;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class OrphanRepository extends RepositoryImpl<Orphan> implements
		IOrphanRepository {

	private static final Logger log = Logger.getLogger(OrphanRepository.class);
	
	/**
	 * @param domainClass
	 */
	public OrphanRepository(final Class<Orphan> domainClass) {
		super(domainClass);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orphan> findByFirstName(final String firstName) throws IntegrationException {
		log.info("findByFirstName start " + firstName);
		List<Orphan> result;
		try {
			final Query q = getEm().createNamedQuery("orphan.findByFirstName")
									.setParameter("firstName", firstName);
			result = q.getResultList();
		} catch (final Exception e) {
			e.printStackTrace();
			throw new IntegrationException(e);
		}
		log.info("findByFirstName end " + result.size());
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Orphan> findByLastName(final String lastName) throws IntegrationException {
		log.info("findByLastName start " + lastName);
		List<Orphan> result;
		try {
			final Query q = getEm().createNamedQuery("orphan.findByLastName")
									.setParameter("lastName", lastName);
			result = q.getResultList();
		} catch (final Exception e) {
			e.printStackTrace();
			throw new IntegrationException(e);
		}
		log.info("findByLastName end " + result.size());
		return result;
	}

}
