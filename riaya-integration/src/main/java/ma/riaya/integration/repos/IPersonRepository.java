/**
 * 
 */
package ma.riaya.integration.repos;

import java.util.stream.Stream;

import ma.riaya.model.dictionary.Person;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public interface IPersonRepository extends IRepository<Person> {
	
	Stream<Person> findByFirstName(final String firstName);

}
