/**
 * 
 */
package ma.riaya.integration.repos;

import java.util.List;

import javax.persistence.Query;

<<<<<<< HEAD
import org.apache.log4j.Logger;

=======
>>>>>>> refs/remotes/origin/master
import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.model.folder.social.SocialWorker;

import org.apache.log4j.Logger;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class SocialWorkerRepository extends RepositoryImpl<SocialWorker> implements ISocialWorkerRepository {

	private static final Logger log = Logger.getLogger(SocialWorkerRepository.class);
	
<<<<<<< HEAD
	public SocialWorkerRepository(final Class<SocialWorker> domainClass) {
=======
	public SocialWorkerRepository(Class<SocialWorker> domainClass) {
>>>>>>> refs/remotes/origin/master
		super(domainClass);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SocialWorker> findByFirstName(final String firstName) throws IntegrationException {
		log.info("findByFirstName start " + firstName);
		List<SocialWorker> result;
		try {
			final Query q = getEm().createNamedQuery("socialWorker.findByFirstName")
									.setParameter("firstName", firstName);
			result = q.getResultList();
<<<<<<< HEAD
		} catch (final Exception e) {
			e.printStackTrace();
			throw new IntegrationException(e);
		}
		log.info("findByFirstName end " + result.size());
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SocialWorker> findByLastName(final String lastName) throws IntegrationException {
		log.info("findByLastName start " + lastName);
		List<SocialWorker> result;
		try {
			final Query q = getEm().createNamedQuery("socialWorker.findByLastName")
									.setParameter("lastName", lastName);
			result = q.getResultList();
		} catch (final Exception e) {
=======
		} catch (Exception e) {
			e.printStackTrace();
			throw new IntegrationException(e);
		}
		log.info("findByFirstName end " + result.size());
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SocialWorker> findByLastName(final String lastName) throws IntegrationException {
		log.info("findByLastName start " + lastName);
		List<SocialWorker> result;
		try {
			final Query q = getEm().createNamedQuery("socialWorker.findByLastName")
									.setParameter("lastName", lastName);
			result = q.getResultList();
		} catch (Exception e) {
>>>>>>> refs/remotes/origin/master
			e.printStackTrace();
			throw new IntegrationException(e);
		}
		log.info("findByLastName end " + result.size());
		return result;
	}
}
