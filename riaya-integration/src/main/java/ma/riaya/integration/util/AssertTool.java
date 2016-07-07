/**
 * 
 */
package ma.riaya.integration.util;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public abstract class AssertTool {

	/**
	 * Assert that an object is not {@code null} .
	 * 
	 * <pre class="code">
	 * AssertTool.notNull(object, &quot;The object must not be null&quot;);
	 * </pre>
	 * 
	 * @param object
	 *            the object to check
	 * @param message
	 *            the exception message to use if the assertion fails
	 * @throws IllegalArgumentException
	 *             if the object is {@code null}
	 */
	public static void notNull(final Object object, final String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that an object is not {@code null} .
	 * 
	 * <pre class="code">
	 * AssertTool.notNull(obj);
	 * </pre>
	 * 
	 * @param object
	 *            the object to check
	 * @throws IllegalArgumentException
	 *             if the object is {@code null}
	 */
	public static void notNull(final Object object) {
		final String message = "Object must not be null";
		notNull(object, message);
	}

	/**
	 * Assert that the given string object is not {@code null} .
	 * 
	 * <pre class="code">
	 * AssertTool.notNull(str, &quot;The class must not be null&quot;);
	 * </pre>
	 * 
	 * @param str
	 *            the string to check
	 * @param message
	 *            the exception message to use if the assertion fails
	 * @throws IllegalArgumentException
	 *             if the string is {@code null}
	 */
	public static void notNull(final String str, final String message) {
		if (str == null || "".equals(str.trim())) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert a boolean expression, throwing {@code IllegalArgumentException} if
	 * the test result is {@code false}.
	 * 
	 * <pre class="code">
	 * AssertTool.isTrue(i &gt; 0, &quot;The value must be greater than zero&quot;);
	 * </pre>
	 * 
	 * @param expression
	 *            a boolean expression
	 * @param message
	 *            the exception message to use if the assertion fails
	 * @throws IllegalArgumentException
	 *             if expression is {@code false}
	 */
	public static void isTrue(final boolean expression, final String message) {
		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Asserts that two longs are equal. If they are not, an
	 * {@link IllegalArgumentException} is thrown.
	 *
	 * @param expected
	 *            expected long value.
	 * @param actual
	 *            actual long value
	 */
	static public void assertEquals(final long expected, final long actual, final String message) {
		if (expected != actual) {
			throw new IllegalArgumentException(message != null ? message : "The given values are not equal");
		}
	}
}
