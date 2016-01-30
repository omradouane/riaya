/**
 * 
 */
package ma.riaya.integration.exception;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class IntegrationException extends Exception {

	private static final long serialVersionUID = -7739555494734555170L;

	/**
	 * 
	 */
	public IntegrationException(final String reason) {
		super(reason);
	}

	/**
	 * 
	 */
	public IntegrationException(final Throwable cause) {
		super(cause);
	}

}
