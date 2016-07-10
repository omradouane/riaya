/**
 * 
 */
package ma.riaya.integration.dictionary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import ma.riaya.integration.AbstractTest;
import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.integration.repos.IRoleRepository;
import ma.riaya.integration.repos.impl.RoleRepository;
import ma.riaya.model.dictionary.Role;
import ma.riaya.model.dictionary.RoleEnum;

/**
 * @author ouledmoussa
 *
 */
public class RoleTest extends AbstractTest {

	private static final Logger log = Logger.getLogger(RoleTest.class);

	private IRoleRepository repos;

	@Before
	public void setUp() {
		repos = new RoleRepository(Role.class);
	}

	@Test
	public void testRole() throws IntegrationException {
		log.debug("test repos");
		assertNotNull(repos);

		log.debug("test getByRoleName => empty");
		final Optional<Role> op = repos.getByRoleName(RoleEnum.ADMIISTRATOR);
		assertFalse(op.isPresent());

		log.debug("test save");
		final Role r = new Role();
		r.setRoleName(RoleEnum.ADMIISTRATOR);
		final Role savedRole = repos.save(r);
		assertNotNull(savedRole.getId());
		
		log.debug("test getByRoleName");
		final Optional<Role> opAdm = repos.getByRoleName(RoleEnum.ADMIISTRATOR);
		assertTrue(opAdm.isPresent());
	}
	
}
