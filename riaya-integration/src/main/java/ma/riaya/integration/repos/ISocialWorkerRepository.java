/**
 * 
 */
package ma.riaya.integration.repos;

import java.util.List;

import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.model.folder.social.SocialWorker;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public interface ISocialWorkerRepository extends IRepository<SocialWorker> {

	/**
	 * @param firstName
	 * @return
	 * @throws IntegrationException
	 */
	List<SocialWorker> findByFirstName(String firstName)
			throws IntegrationException;

	/**
	 * @param lastName
	 * @return
	 * @throws IntegrationException
	 */
	List<SocialWorker> findByLastName(String lastName)
			throws IntegrationException;

	
}
