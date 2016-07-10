/**
 * 
 */
package ma.riaya.integration.repos.impl;

import ma.riaya.integration.repos.IAddressRepository;
import ma.riaya.model.dictionary.Address;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class AddressRepository extends RepositoryImpl<Address> implements
		IAddressRepository {

	public AddressRepository(Class<Address> domainClass) {
		super(domainClass);
	}

}
