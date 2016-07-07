/**
 * 
 */
package ma.riaya.integration.repos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.integration.repos.query.QueryTool;
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
	public FamilyRepository(Class<Family> domainClass) {
		super(domainClass);
	}

	
	public List<Family> findByFamilyName(final String familyName) throws IntegrationException {
		return findByUsingLike("familyName", familyName);
	}
}
