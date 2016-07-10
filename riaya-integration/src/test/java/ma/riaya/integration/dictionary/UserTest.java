/**
 * 
 */
package ma.riaya.integration.dictionary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import ma.riaya.integration.AbstractTest;
import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.integration.repos.IUserRepository;
import ma.riaya.integration.repos.impl.UserRepository;
import ma.riaya.model.dictionary.User;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class UserTest extends AbstractTest {

	private static final Logger log = Logger.getLogger(UserTest.class);

	private IUserRepository repos;

	@Before
	public void setUp() {
		repos = new UserRepository(User.class);
	}

	@Test
	public void testUser() throws IntegrationException {
		log.debug("test repos");
		assertNotNull(repos);

		log.debug("test findByLogin => empty");
		final Optional<User> op = repos.getByLogin("mido");
		assertFalse(op.isPresent());

		log.debug("test save");
		final User u = new User();
		u.setLogin("mido");
		u.setPassword("0123456789");
		final User savedUser = repos.save(u);
		assertNotNull(savedUser.getId());

		log.debug("check password encoding");
		assertEquals("84D89877F0D4041EFB6BF91A16F0248F2FD573E6AF05C19F96BEDB9F882F7882", savedUser.getPassword());

	}

}
