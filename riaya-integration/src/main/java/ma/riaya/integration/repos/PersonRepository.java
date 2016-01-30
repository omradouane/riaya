/**
 * 
 */
package ma.riaya.integration.repos;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import ma.riaya.integration.util.AssertTool;
import ma.riaya.integration.util.ReflectionTool;
import ma.riaya.model.dictionary.Person;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class PersonRepository extends RepositoryImpl<Person> implements IPersonRepository {

	/**
	 * @param domainClass
	 */
	public PersonRepository(final Class<Person> domainClass) {
		super(domainClass);
	}

	@Override
	public Stream<Person> findByFirstName(final String firstName) {
		final CriteriaBuilder builder = getEm().getCriteriaBuilder();
		final CriteriaQuery<Person> cq = builder.createQuery(Person.class);
		final Root<Person> rootEntry = cq.from(Person.class);
		
		final ParameterExpression<String> p = builder.parameter(String.class);
		final CriteriaQuery<Person> all = cq.select(rootEntry)
											.where(builder.equal(rootEntry.get("firstName"), p));
		final TypedQuery<Person> allQuery = getEm().createQuery(all);
		allQuery.setParameter(p, firstName);
		final List<Person> result = allQuery.getResultList();
		return result.stream();
	}

	public List<Person> findByyyyy(final String... attrs) {
		final long size = attrs.length;
		final long count = Stream.of(attrs).distinct().count();
		AssertTool.assertEquals(size, count, "Please avoid duplicate entries");
		
	final Optional<String> op = Stream.of(attrs)
	        .filter(s -> ReflectionTool.findField(Person.class, s) == null).findFirst();

		op.ifPresent(s -> AssertTool.isTrue(false, String.format("The attribute %s isn't part of %s" , s, Person.class)));
		
		final List<Person> result = Collections.emptyList();
		
		final CriteriaBuilder builder = getEm().getCriteriaBuilder();
		final CriteriaQuery<Person> cq = builder.createQuery(Person.class);
		final Root<Person> rootEntry = cq.from(Person.class);
		Stream.of(attrs).forEach( s -> {
			final Field field = ReflectionTool.findField(Person.class, s);
			final Class<?> clazz = field.getType();
			final ParameterExpression<?> p = builder.parameter(clazz);
			final CriteriaQuery<Person> all = cq.select(rootEntry)
					.where(builder.equal(rootEntry.get(s), p));
		});
		final ParameterExpression<String> p = builder.parameter(String.class);

		return result;
	}
	
	public List<Person> findBy(final Map<String, Object> map) {
		validateMap(map);
		
		final CriteriaBuilder builder = getEm().getCriteriaBuilder();
		final CriteriaQuery<Person> cq = builder.createQuery(Person.class);
		final Root<Person> rootEntry = cq.from(Person.class);
		
		map.forEach( (s, o) -> {
			
		});
		return null;
	}

	/**
	 * @param map
	 */
	private void validateMap(final Map<String, Object> map) {
		map.forEach( (s,o) -> {
			final Field field = ReflectionTool.findField(Person.class, s);
			AssertTool.notNull(field, String.format("The attribute %s isn't part of %s", s, Person.class));
			final Class<?> clazz = field.getType();
			AssertTool.isTrue(o.getClass().isAssignableFrom(clazz),
					String.format("The object value %s for the attribute %s must be of type %s", o, s, clazz));
		});
	}
	
	public static void main(final String[] args) {
		final PersonRepository r = new PersonRepository(Person.class);
		final List<Person> result = r.findBy("dateOfBirth", LocalDate.of(1960, Month.APRIL, 15));
		result.forEach(System.out::println);
	}

}
