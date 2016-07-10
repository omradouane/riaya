/**
 * 
 */
package ma.riaya.integration.repos.impl;

import java.util.Optional;

import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.integration.repos.IUserRepository;
import ma.riaya.integration.util.PasswordEncoder;
import ma.riaya.model.dictionary.User;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class UserRepository extends RepositoryImpl<User> implements IUserRepository {

	private final PasswordEncoder pwdEncoder;

	public UserRepository(final Class<User> domainClass) {
		super(domainClass);
		pwdEncoder = new PasswordEncoder();
	}

	/**
	 * Save the user after encoding his password.
	 * 
	 * @see PasswordEncoder#encode(String)
	 * @see ma.riaya.integration.repos.impl.RepositoryImpl#save(ma.riaya.model.dictionary.BaseObject)
	 */
	@Override
	public User save(final User user) throws IntegrationException {
		user.setPassword(pwdEncoder.encode(user.getPassword()));
		return super.save(user);
	}

	@Override
	public Optional<User> getByLogin(final String login) throws IntegrationException {
		return getBy("login", login);
	}

}
