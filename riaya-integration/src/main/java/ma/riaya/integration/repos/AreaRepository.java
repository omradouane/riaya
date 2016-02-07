/**
 * 
 */
package ma.riaya.integration.repos;

import java.util.List;
import java.util.Optional;

import ma.riaya.integration.util.AssertTool;
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
	public Optional<Area> findByAreaName(String areaName) {
		final List<Area> list = findBy("areaName", areaName);
		if (list.isEmpty()) {
			return Optional.empty();
		}
		AssertTool.assertEquals(1, list.size(), "Only one area must exist with name " + areaName);
		return Optional.of(list.get(0));
	}

}
