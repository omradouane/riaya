/**
 * 
 */
package ma.riaya.integration.dictionary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import ma.riaya.integration.AbstractTest;
import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.integration.repos.IPictureRepository;
import ma.riaya.integration.repos.PictureRepository;
import ma.riaya.model.dictionary.Picture;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class PictureTest extends AbstractTest {

	private static final Logger log = Logger.getLogger(PictureTest.class);

	private IPictureRepository repos;

	@Before
	public void setUp() {
		repos = new PictureRepository(Picture.class);
	}

	@Test
	public void testPicture() throws IntegrationException {
		log.debug("test save");
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
		
		p = repos.save(p);
		assertNotNull(p.getId());
		assertEquals(bytes.length, p.getContent().length);
		
		Picture p2 = new Picture();
		p2.setFileName("toto.gif");
		p2.setFileSize(1212);
		p2.setContent(new byte[10]);
		p2 = repos.save(p2);
		assertNotNull(p2.getId());
		
		log.debug("test findAll");
		final Stream<Picture> s = repos.findAll().stream();
		assertEquals(2L, s.count());
		
		log.debug("test findOne");
		Optional<Picture> op = repos.findOne(1L);
		assertTrue(op.isPresent());
		assertEquals(fileName, op.get().getFileName());
		
		log.debug("test update");
		op.get().setFileName("riaya.gif");
		try {
			repos.save(op.get());
		} catch (final Exception e) {
		}
		
		op = repos.findOne(1L);
		assertEquals("riaya.gif", op.get().getFileName());
		
		log.debug("test count");
		final long count = repos.count();
		assertEquals(2L, count);
		
		log.debug("test exist");
		final boolean exist = repos.exists(2L);
		assertTrue(exist);
		
		log.debug("test deleteAll");
		repos.deleteAllFast();
		final List<Picture> all = repos.findAll();
		assertEquals(0, all.size());
		
		
	}
}
