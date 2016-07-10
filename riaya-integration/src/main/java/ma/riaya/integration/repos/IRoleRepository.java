/**
 * 
 */
package ma.riaya.integration.repos;

import java.util.Optional;

import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.model.dictionary.Role;
import ma.riaya.model.dictionary.RoleEnum;


/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public interface IRoleRepository extends IRepository<Role> {

	/**
	 * Get a unique Role by its name.
	 * 
	 * @param roleName
	 * @return
	 * @throws IntegrationException
	 */
	Optional<Role> getByRoleName(final RoleEnum roleName)
			throws IntegrationException;
	
}
