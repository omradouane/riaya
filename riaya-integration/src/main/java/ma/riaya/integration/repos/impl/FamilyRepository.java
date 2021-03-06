/**
 * 
 */
package ma.riaya.integration.repos.impl;

import java.util.List;

import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.integration.repos.IFamilyRepository;
import ma.riaya.model.folder.social.Family;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class FamilyRepository extends RepositoryImpl<Family> implements
		IFamilyRepository {

	/**
	 * @param domainClass
	 */
	public FamilyRepository(final Class<Family> domainClass) {
		super(domainClass);
	}

	
	@Override
	public List<Family> findByFamilyName(final String familyName) throws IntegrationException {
		return findByUsingLike("familyName", familyName);
	}
}
