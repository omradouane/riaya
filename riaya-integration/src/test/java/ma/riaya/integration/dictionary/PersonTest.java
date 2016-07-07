/**
 * 
 */
package ma.riaya.integration.dictionary;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import ma.riaya.integration.AbstractTest;
import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.integration.repos.IPersonRepository;
import ma.riaya.integration.repos.PersonRepository;
import ma.riaya.model.dictionary.Person;
import ma.riaya.model.dictionary.Picture;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class PersonTest extends AbstractTest {

	private static final Logger log = Logger.getLogger(PersonTest.class);

	private IPersonRepository repos;

	@Before
	public void setUp() {
		repos = new PersonRepository(Person.class);
	}

	@Test
	public void testPerson() throws IntegrationException {
		log.debug("test repos");
		assertNotNull(repos);
		
		log.debug("test save");
		final Person person = new Person();
		person.setFirstName("Radouane");
		person.setCinNumber("AAAA");
		person.setLastName("OULEDMOUSSA");
		person.setDateOfBirth(LocalDate.of(1960, Month.APRIL, 15));
		final Person p = repos.save(person);
		assertNotNull(p.getId());
		
		final Person person2 = new Person();
		person2.setFirstName("toto");
		person2.setCinNumber("ZZZZ");
		person2.setLastName("titi");
		person2.setDateOfBirth(LocalDate.of(1990, Month.DECEMBER, 10));
		final Person p2 = repos.save(person2);
		assertNotNull(p2.getId());
		
		log.debug("test findAll");
		final Stream<Person> s = repos.findAll().stream();
		assertEquals(2L, s.count());
		
		log.debug("test findOne");
		Optional<Person> op = repos.getOne(1L);
		assertTrue(op.isPresent());
		assertEquals("Radouane", op.get().getFirstName());
		
		log.debug("test update");
		op.get().setPhoneNumber("0989870987");
		try {
			repos.save(op.get());
		} catch (final Exception e) {
		}
		
		op = repos.getOne(1L);
		assertEquals("0989870987", op.get().getPhoneNumber());
		
		log.debug("test count");
		final long count = repos.count();
		assertEquals(2L, count);
		
		log.debug("test exist");
		final boolean exist = repos.exists(2L);
		assertTrue(exist);
		
		log.debug("test findByFirstName");
		final List<Person> l2 = repos.findByFirstName("toto");
		assertEquals(1, l2.size());
		
		log.debug("test findByLastName");
		final List<Person> l3 = repos.findByLastName("titi");
		assertEquals(1, l3.size());
		
		log.debug("test set picture");
		final Optional<Person> oPers = repos.getOne(1L);
		final Picture pic = new Picture();
		pic.setFileName("file.jpg");
		pic.setFileSize(2345);
		pic.setContent(new byte[0]);
		oPers.get().setPicture(pic);
		final Person pers = repos.save(oPers.get());
		assertNotNull(pers.getPicture());
		assertNotNull(pers.getPicture().getId());
		
		log.debug("test deleteAll");
		repos.deleteAll();
		final List<Person> all = repos.findAll();
		assertEquals(0, all.size());
		
		
	}
}
