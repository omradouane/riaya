/**
 * 
 */
package ma.riaya.integration.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class PasswordEncoderTest {

	private PasswordEncoder pwdEncoder;
	private final String rawPwd = "0123456789";
	private final String encPwd = "84D89877F0D4041EFB6BF91A16F0248F2FD573E6AF05C19F96BEDB9F882F7882";

	@Before
	public void setUp() {
		pwdEncoder = new PasswordEncoder();
	}

	@Test
	public void checkEncodingAndValidity() {
		final String enc = pwdEncoder.encode(rawPwd);
		assertEquals(encPwd, enc);
		final boolean isPasswordValid = pwdEncoder.isPasswordValid(encPwd, rawPwd);
		assertTrue(isPasswordValid);
	}

}
