/**
 * 
 */
package ma.riaya.integration.repos.impl;

import ma.riaya.integration.repos.IPictureRepository;
import ma.riaya.model.dictionary.Picture;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class PictureRepository extends RepositoryImpl<Picture> implements
		IPictureRepository {

	/**
	 * @param domainClass
	 */
	public PictureRepository(final Class<Picture> domainClass) {
		super(domainClass);
	}

}
