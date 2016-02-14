/**
 * 
 */
package ma.riaya.integration.repos;

import java.util.List;
import java.util.Optional;

import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.model.folder.social.Family;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public interface IFamilyRepository extends IRepository<Family> {

	/**
	 * Get the family by family name. Only one entry should exist
	 * @param familyName
	 * @return
	 * @throws IntegrationException 
	 */
	default Optional<Family> getFamilyByFamilyName(final String familyName) throws IntegrationException {
		final List<Family> l = findBy("familyName", familyName);
		if (l.isEmpty()) {
			return Optional.empty();
		}
		if (l.size() != 1) {
			throw new IntegrationException("Only one family must exist with name " + familyName);
		}
		return Optional.of(l.get(0));
	}

	List<Family> findByFamilyName(final String familyName) throws IntegrationException;
	
}
