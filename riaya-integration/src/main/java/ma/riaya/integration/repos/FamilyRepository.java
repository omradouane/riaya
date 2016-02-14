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

	@SuppressWarnings("unchecked")
	public List<Family> findByFamilyName(final String familyName) throws IntegrationException {
		List<Family> result = new ArrayList<>();
		try {
			final Query q = getEm().createQuery(
					String.format(QueryTool.SELECT_ALL_QUERY,
							Family.class.getSimpleName()).concat(
							"where upper(x.familyName) like upper('%:familyName%')"));
			q.setParameter("familyName", familyName);
			result = q.getResultList();
		} catch (Exception e) {
			throw new IntegrationException(e);
		}
		return result;
	}
}
