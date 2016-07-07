/**
 * 
 */
package ma.riaya.integration.dictionary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import javax.persistence.RollbackException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import ma.riaya.integration.AbstractTest;
import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.integration.repos.AreaRepository;
import ma.riaya.integration.repos.IAreaRepository;
import ma.riaya.model.dictionary.Area;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class AreaTest extends AbstractTest {

	private static final Logger log = Logger.getLogger(AreaTest.class);

	private IAreaRepository repos;

	@Before
	public void setUp() {
		repos = new AreaRepository(Area.class);
	}
	
	@Test
	public void testArea() throws IntegrationException {
		log.debug("test repos");
		assertNotNull(repos);
		
		log.debug("test findByAreaName => empty");
		Optional<Area> op = repos.getByAreaName("titi");
		assertFalse(op.isPresent());
		
		log.debug("test save");
		final Area area = new Area();
		area.setAreaName("titi");
		area.setAddress("Adresse de titi");
		final Area savedArea = repos.save(area);
		assertNotNull(savedArea.getId());
		
		log.debug("test findByAreaName => titi");
		op = repos.getByAreaName("titi");
		assertTrue(op.isPresent());
		
	}
	
	@Test(expected = RollbackException.class)
	public void testDuplicatedArea() throws IntegrationException {
		log.debug("test repos");
		assertNotNull(repos);
		log.debug("test save");
		final Area area = new Area();
		area.setAreaName("toto");
		area.setAddress("Adresse 1");
		final Area savedArea = repos.save(area);
		assertNotNull(savedArea.getId());
		
		log.debug("test save2");
		final Area area2 = new Area();
		area2.setAreaName("toto");
		area2.setAddress("Adresse 2");
		final Area savedArea2 = repos.save(area2);
		assertNotNull(savedArea2.getId());
		
	}

}
