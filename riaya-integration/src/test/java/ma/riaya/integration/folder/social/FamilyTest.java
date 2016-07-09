/**
 * 
 */
package ma.riaya.integration.folder.social;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import ma.riaya.integration.AbstractTest;
import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.integration.repos.FamilyRepository;
import ma.riaya.integration.repos.IFamilyRepository;
import ma.riaya.integration.repos.IOrphanRepository;
import ma.riaya.integration.repos.ISocialWorkerRepository;
import ma.riaya.integration.repos.OrphanRepository;
import ma.riaya.integration.repos.SocialWorkerRepository;
import ma.riaya.model.dictionary.Address;
import ma.riaya.model.dictionary.Area;
import ma.riaya.model.dictionary.Person;
import ma.riaya.model.dictionary.Picture;
import ma.riaya.model.dictionary.Sex;
import ma.riaya.model.folder.social.CareType;
import ma.riaya.model.folder.social.Family;
import ma.riaya.model.folder.social.Orphan;
import ma.riaya.model.folder.social.SocialWorker;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class FamilyTest extends AbstractTest {

	private static final Logger log = Logger.getLogger(FamilyTest.class);

	private IFamilyRepository repos;
	private ISocialWorkerRepository swRepos;
	private IOrphanRepository oRepos;

	@Before
	public void setUp() {
		repos = new FamilyRepository(Family.class);
		swRepos = new SocialWorkerRepository(SocialWorker.class);
		oRepos = new OrphanRepository(Orphan.class);
	}
	
	@Test
	public void testFamily() throws IntegrationException {
		log.debug("test repos");
		assertNotNull(repos);
		assertNotNull(swRepos);
		
		log.debug("test save");
		final Family f = new Family();
		f.setFamilyName("Aqqad");
		// family responsible
		final Person responsible = new Person();
		responsible.setFirstName("fff");
		responsible.setCinNumber("AAAA");
		responsible.setLastName("LLLL");
		responsible.setDateOfBirth(LocalDate.of(1960, Month.APRIL, 15));
		responsible.setSex(Sex.F);
		// picture
		final String fileName = "riaya.png";
		final Picture pic = new Picture();
		pic.setFileName(fileName);
		byte[] bytes = null;
		try {
			bytes = Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(fileName).toURI()));
		} catch (IOException | URISyntaxException e1) {
			e1.printStackTrace();
		}
		pic.setFileSize(bytes.length);
		pic.setContent(bytes);
		responsible.setPicture(pic);
		f.setResponsible(responsible);
		// Area
		final Area area = new Area();
		area.setAreaName("titi");
		area.setAddress("Adresse de titi");
		f.setArea(area);
		// social worker
		final SocialWorker sw = new SocialWorker();
		final Person person = new Person();
		person.setFirstName("Amine");
		person.setCinNumber("AAAA");
		person.setLastName("B. K.");
		person.setDateOfBirth(LocalDate.of(1960, Month.APRIL, 15));
		person.setSex(Sex.M);
		sw.setPerson(person);
		f.setSocialWorker(swRepos.save(sw));
		// address
		final String desc = "No 5 Rue X Quarier Y";
		final Address address = new Address();
		address.setDescription(desc);
		f.setAddress(address);
		// care type
		f.setCareType(CareType.PERMANENT);
		
		final Family savedFamily = repos.save(f);
		assertNotNull(savedFamily);
		assertNotNull(savedFamily.getResponsible().getId());
		assertNotNull(savedFamily.getSocialWorker().getId());
		assertEquals(CareType.PERMANENT, savedFamily.getCareType());
		
		log.debug("test update");
		savedFamily.setCareType(CareType.SAISONAL);
		assertEquals(CareType.SAISONAL, repos.save(savedFamily).getCareType());
		
		final List<Family> l = repos.findByFamilyName("AQQAD");
		assertFalse(l.isEmpty());
		
		final Optional<Family> op = repos.getFamilyByFamilyName("AQQAD");
		assertTrue(op.isPresent());

		Orphan o = new Orphan();
		final Person orphanPerson = new Person();
		orphanPerson.setFirstName("Said");
		orphanPerson.setCinNumber("AAAA0090");
		orphanPerson.setLastName("Kawtari");
		orphanPerson.setDateOfBirth(LocalDate.of(1960, Month.APRIL, 15));
		o.setPerson(orphanPerson);
		o.setFamily(f);
		o = oRepos.save(o);
		
		final Optional<Family> fam = repos.getOne(1L);
		assertTrue(fam.isPresent());
		final Collection<Orphan> orphans = fam.get().getOrphans();
		assertTrue(!orphans.isEmpty());
		
	}
}
