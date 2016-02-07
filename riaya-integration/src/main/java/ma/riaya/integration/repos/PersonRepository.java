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
public class PersonRepository extends RepositoryImpl<Person> implements IPersonRepository {

	/**
	 * @param domainClass
	 */
	public PersonRepository(final Class<Person> domainClass) {
		super(domainClass);
	}

	@Override
	public List<Person> findByFirstName(final String firstName) {
		return findBy("firstName", firstName);
	}


	@Override
	public List<Person> findByLastName(final String lastName) {
		return findBy("lastName", lastName);
	}
}
