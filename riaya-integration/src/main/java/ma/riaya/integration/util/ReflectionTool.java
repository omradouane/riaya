/**
 * 
 */
package ma.riaya.integration.util;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public abstract class ReflectionTool {

	public static Field findField(final Class<?> clazz, final String name) {
		AssertTool.notNull(clazz, "Class must not be null");
		AssertTool.notNull(name, "Field name must not be null");
		Class<?> searchType = clazz;
		while (!Object.class.equals(searchType) && searchType != null) {
			final Field[] fields = searchType.getDeclaredFields();
			final Optional<Field> field = Stream.of(fields).filter(f -> name.equals(f.getName())).findFirst();
			if (field.isPresent()) {
				return field.get();
			}
			searchType = searchType.getSuperclass();
		}
		return null;
	}
}
