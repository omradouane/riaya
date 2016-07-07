/**
 * 
 */
package ma.riaya.integration.folder.social;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.Month;

import ma.riaya.integration.AbstractTest;
import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.integration.repos.IOrphanRepository;
import ma.riaya.integration.repos.IPersonRepository;
import ma.riaya.integration.repos.OrphanRepository;
import ma.riaya.integration.repos.PersonRepository;
import ma.riaya.model.dictionary.Person;
import ma.riaya.model.folder.social.Orphan;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

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
		final String personFirstName = "Radouane";
		final String personLastName = "OULEDMOUSSA";
		
		log.debug("test save");
		final Orphan o = new Orphan();
		
		final Person person = new Person();
		person.setFirstName(personFirstName);
		person.setCinNumber("AAAA");
		person.setLastName(personLastName);
		person.setDateOfBirth(LocalDate.of(1960, Month.APRIL, 15));
		o.setPerson(person);;
	}
}
