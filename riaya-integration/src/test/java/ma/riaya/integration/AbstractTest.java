/**
 * 
 */
package ma.riaya.integration;

import ma.riaya.integration.util.Constants;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public abstract class AbstractTest {

	static {
		System.setProperty(Constants.PU_KEY, Constants.TEST_PU);
	}
}
