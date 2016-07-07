/**
 * 
 */
package ma.riaya.integration.folder.social;

import static ma.riaya.integration.util.AssertTool.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.Month;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import ma.riaya.integration.AbstractTest;
import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.integration.repos.IOrphanRepository;
import ma.riaya.integration.repos.IPersonRepository;
import ma.riaya.integration.repos.OrphanRepository;
import ma.riaya.integration.repos.PersonRepository;
import ma.riaya.model.dictionary.Person;
import ma.riaya.model.folder.social.Orphan;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class OrphanTest extends AbstractTest {

	private static final Logger log = Logger.getLogger(OrphanTest.class);

	private IOrphanRepository repos;
	private IPersonRepository reposPer;

	@Before
	public void setUp() {
		repos = new OrphanRepository(Orphan.class);
		reposPer = new PersonRepository(Person.class);
	}

	@Test
	public void testOrphan() throws IntegrationException {
		log.debug("test repos");
		assertNotNull(repos);
		repos.deleteAll();
		final String personFirstName = "Radouane";
		final String personLastName = "OULEDMOUSSA";
		
		log.debug("test save");
		Orphan o = new Orphan();
		
		final Person person = new Person();
		person.setFirstName(personFirstName);
		person.setCinNumber("AAAA");
		person.setLastName(personLastName);
		person.setDateOfBirth(LocalDate.of(1960, Month.APRIL, 15));
		o.setPerson(person);
		o = repos.save(o);
		assertNotNull(o.getId());
		
		Orphan o2 = new Orphan();
		final Person person2 = new Person();
		person2.setFirstName("dddd");
		person2.setCinNumber("AAAA");
		person2.setLastName("dddd");
		person2.setDateOfBirth(LocalDate.of(1960, Month.APRIL, 15));
		
		o2.setPerson(person2);
		o2 = repos.save(o2);
		assertNotNull(o2.getId());
		assertEquals(person2.getId(), o2.getPerson().getId(), "Person has changed");
		
	}
}
