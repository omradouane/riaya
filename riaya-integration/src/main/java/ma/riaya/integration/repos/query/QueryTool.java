/**
 * 
 */
package ma.riaya.integration.repos.query;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public abstract class QueryTool {

	public static final String COUNT_QUERY_STRING = "select count(x) from %s x";
	public static final String DELETE_ALL_QUERY_STRING = "delete from %s x";
	private static final String EQUALS_CONDITION_STRING = "%s.%s = :%s";

	public static <T> String getExistsQueryString(final Class<T> domainClass) {
		final String idAttribute = "id";
		final String entityName = domainClass.getSimpleName();
		final StringBuilder sb = new StringBuilder(String.format(
				COUNT_QUERY_STRING, entityName, "%s"));
		sb.append(" WHERE ");

		sb.append(String.format(EQUALS_CONDITION_STRING, "x", idAttribute,
				idAttribute));
		return sb.toString();
	}
}
