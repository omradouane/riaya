/**
 * 
 */
package ma.riaya.integration.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class PasswordEncoder {
	
	private static final Logger log = Logger.getLogger(PasswordEncoder.class);

	/**
	 * Encodes a password using SHA-256 Algorithm.
	 * 
	 * @param rawPass
	 *            The password to be encoded.
	 */
	public String encode(final String rawPass) {
		log.trace("encode password");
		try {
			return byteArrayToHexString(hashPwd(rawPass));
		} catch (final NoSuchAlgorithmException e) {
			e.printStackTrace();
			return rawPass;
		}
	}

	private byte[] hashPwd(final String pwd) throws NoSuchAlgorithmException {
		final MessageDigest d = MessageDigest.getInstance("SHA-256");
		d.reset();
		d.update(pwd.getBytes());
		return d.digest();
	}

	private String byteArrayToHexString(final byte[] b) {
		final StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(String.format("%02x", b[i]));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * Checks a password's validity.
	 * 
	 * @param encPass
	 *            The encrypted password (digest) against which to check.
	 * @param rawPass
	 *            The password to be checked.
	 */
	public boolean isPasswordValid(final String encPass, final String rawPass) {
		log.debug("isPasswordValid " + rawPass);
		try {
			final String pwd = byteArrayToHexString(hashPwd(rawPass));
			return encPass.equals(pwd);
		} catch (final NoSuchAlgorithmException e) {
			e.printStackTrace();
			return false;
		}
	}

}
