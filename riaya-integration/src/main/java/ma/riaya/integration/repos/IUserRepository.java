/**
 * 
 */
package ma.riaya.integration.repos;

import java.util.Optional;

import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.model.dictionary.User;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public interface IUserRepository extends IRepository<User> {

	/**
	 * Get a unique user by its login.
	 * 
	 * @param login
	 * @return
	 * @throws IntegrationException
	 */
	Optional<User> getByLogin(final String login)
			throws IntegrationException;
}
