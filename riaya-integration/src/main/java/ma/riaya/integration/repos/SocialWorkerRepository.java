/**
 * 
 */
package ma.riaya.integration.repos;

import ma.riaya.model.folder.social.SocialWorker;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class SocialWorkerRepository extends RepositoryImpl<SocialWorker> implements ISocialWorkerRepository {

	public SocialWorkerRepository(Class<SocialWorker> domainClass) {
		super(domainClass);
	}

}
