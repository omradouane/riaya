/**
 * 
 */
package ma.riaya.integration.repos;

import java.util.List;
import java.util.Optional;

import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.model.dictionary.Area;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class AreaRepository extends RepositoryImpl<Area> implements IAreaRepository {


	public AreaRepository(final Class<Area> domainClass) {
		super(domainClass);
	}
	
	@Override
	public Optional<Area> getByAreaName(String areaName) throws IntegrationException {
		/*final List<Area> list = findBy("areaName", areaName);
		if (list.isEmpty()) {
			return Optional.empty();
		}
		if (list.size() != 1) {
			throw new IntegrationException("Only one area must exist with name " + areaName);
		}
		return Optional.of(list.get(0));*/
		return getBy("areaName", areaName);
	}

}
