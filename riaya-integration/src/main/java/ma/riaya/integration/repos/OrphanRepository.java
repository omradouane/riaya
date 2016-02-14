/**
 * 
 */
package ma.riaya.integration.repos;

import ma.riaya.model.folder.social.Orphan;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class OrphanRepository extends RepositoryImpl<Orphan> implements
		IOrphanRepository {

	/**
	 * @param domainClass
	 */
	public OrphanRepository(Class<Orphan> domainClass) {
		super(domainClass);
	}

	

}
