/**
 * 
 */
package ma.riaya.integration.folder.social;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import ma.riaya.integration.AbstractTest;
import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.integration.repos.IPersonRepository;
import ma.riaya.integration.repos.ISocialWorkerRepository;
import ma.riaya.integration.repos.PersonRepository;
import ma.riaya.integration.repos.SocialWorkerRepository;
import ma.riaya.model.dictionary.Person;
import ma.riaya.model.dictionary.Picture;
import ma.riaya.model.folder.social.SocialWorker;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class SocialWorkerTest extends AbstractTest {

	private static final Logger log = Logger.getLogger(SocialWorkerTest.class);

	private ISocialWorkerRepository repos;
	private IPersonRepository reposPer;

	@Before
	public void setUp() {
		repos = new SocialWorkerRepository(SocialWorker.class);
		reposPer = new PersonRepository(Person.class);
	}

	@Test
	public void testSocialWorker() throws IntegrationException {
		log.debug("test repos");
		assertNotNull(repos);
		final String personFirstName = "Radouane";
		final String personLastName = "OULEDMOUSSA";
		
		log.debug("test save");
		final SocialWorker sw = new SocialWorker();
		final Person person = new Person();
		person.setFirstName(personFirstName);
		person.setCinNumber("AAAA");
		person.setLastName(personLastName);
		person.setDateOfBirth(LocalDate.of(1960, Month.APRIL, 15));
		sw.setPerson(person);
		final SocialWorker savedSw = repos.save(sw);
		assertNotNull(savedSw.getId());
		
		List<Person> list = reposPer.findByFirstName(personFirstName);
		assertFalse(list.isEmpty());
		
		final Optional<SocialWorker> op = repos.getOne(1L);
		assertTrue(op.isPresent());
		
		final String fileName = "riaya.png";
		Picture p = new Picture();
		p.setFileName(fileName);
		byte[] bytes = null;
		try {
			bytes = Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(fileName).toURI()));
		} catch (IOException | URISyntaxException e1) {
			e1.printStackTrace();
		}
		p.setFileSize(bytes.length);
		p.setContent(bytes);
		op.get().getPerson().setPicture(p);
		final SocialWorker s = repos.save(op.get());
		assertNotNull(s.getPerson().getPicture().getId());

		List<SocialWorker> l = repos.findByFirstName(personFirstName);
		assertNotNull(l);
		assertFalse(l.isEmpty());
		assertEquals(1, l.size());
		assertEquals(sw, l.get(0));
		assertEquals(person, l.get(0).getPerson());

		l = repos.findByLastName(personLastName);
		assertNotNull(l);
		assertFalse(l.isEmpty());
		assertEquals(1, l.size());
		assertEquals(sw, l.get(0));
		assertEquals(person, l.get(0).getPerson());

		l = repos.findByLastName("TOTO");
		assertNotNull(l);
		assertTrue(l.isEmpty());

	}
	
}
