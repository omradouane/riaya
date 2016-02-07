/**
 * 
 */
package ma.riaya.integration.repos;

import static org.junit.Assert.assertNotNull;
import ma.riaya.integration.AbstractTest;
import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.model.dictionary.BaseObject;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class RepositoryTest extends AbstractTest {

	private static  IRepository<BaseObject> repos;

	@BeforeClass
	public static void setUp() {
		repos = new RepositoryImpl<BaseObject>(BaseObject.class);
	}
	
	@Test
	public void validateRepos() throws IntegrationException {
		assertNotNull(repos);
		assertNotNull(repos.getEm());
		assertNotNull(repos.getDomainClass());
	}
}
