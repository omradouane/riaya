/**
 * 
 */
package ma.riaya.integration.repos;

import java.util.List;

import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.model.dictionary.Person;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public interface IPersonRepository extends IRepository<Person> {
	
	List<Person> findByFirstName(final String firstName) throws IntegrationException ;
	
	List<Person> findByLastName(final String lastName) throws IntegrationException ;

}
