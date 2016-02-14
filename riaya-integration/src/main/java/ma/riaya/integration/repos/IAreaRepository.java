/**
 * 
 */
package ma.riaya.integration.repos;

import java.util.Optional;

import ma.riaya.integration.exception.IntegrationException;
import ma.riaya.model.dictionary.Area;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public interface IAreaRepository extends IRepository<Area> {

	Optional<Area> findByAreaName(final String areaName) throws IntegrationException;
}
