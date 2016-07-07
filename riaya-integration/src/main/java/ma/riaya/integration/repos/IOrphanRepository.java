/**
 * 
 */
package ma.riaya.integration.repos;

import java.util.List;

import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.model.folder.social.Orphan;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public interface IOrphanRepository extends IRepository<Orphan> {

	/**
	 * @param firstName
	 * @return
	 * @throws IntegrationException
	 */
	List<Orphan> findByFirstName(String firstName)
			throws IntegrationException;

	/**
	 * @param lastName
	 * @return
	 * @throws IntegrationException
	 */
	List<Orphan> findByLastName(String lastName)
			throws IntegrationException;
	
}
