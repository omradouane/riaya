/**
 * 
 */
package ma.riaya.integration.repos.impl;

import java.util.Optional;

import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.integration.repos.IRoleRepository;
import ma.riaya.model.dictionary.Role;
import ma.riaya.model.dictionary.RoleEnum;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class RoleRepository extends RepositoryImpl<Role> implements IRoleRepository {

	

	public RoleRepository(final Class<Role> domainClass) {
		super(domainClass);
	}

	/* (non-Javadoc)
	 * @see ma.riaya.integration.repos.IRoleRepository#getByRoleName(ma.riaya.model.dictionary.RoleEnum)
	 */
	@Override
	public Optional<Role> getByRoleName(final RoleEnum roleName) throws IntegrationException {
		return getBy("roleName", roleName);
	}

}
