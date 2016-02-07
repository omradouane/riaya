/**
 * 
 */
package ma.riaya.integration.repos;

import java.util.List;

import ma.riaya.model.dictionary.Person;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public interface IPersonRepository extends IRepository<Person> {
	
	List<Person> findByFirstName(final String firstName);
	
	List<Person> findByLastName(final String lastName);

}
