/**
 * 
 */
package ma.riaya.integration.dictionary;

import static org.junit.Assert.*;

import java.util.Optional;

import ma.riaya.integration.AbstractTest;
import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.integration.repos.AddressRepository;
import ma.riaya.integration.repos.IAddressRepository;
import ma.riaya.model.dictionary.Address;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class AddressTest extends AbstractTest {

	private static final Logger log = Logger.getLogger(AddressTest.class);

	private IAddressRepository repos;

	@Before
	public void setUp() {
		repos = new AddressRepository(Address.class);
	}
	
	@Test
	public void testAddress() throws IntegrationException {
		log.debug("test repos");
		assertNotNull(repos);
		
		log.debug("test save");
		final String desc = "No 5 Rue X Quarier Y";
		final Address address = new Address();
		address.setDescription(desc);
		final Address savedAddress = repos.save(address);
		assertNotNull(savedAddress.getId());
		
		log.debug("test findAll");
		Optional<Address> op = repos.findOne(savedAddress.getId());
		assertTrue(op.isPresent());
		assertEquals(desc, op.get().getDescription());
	}
}