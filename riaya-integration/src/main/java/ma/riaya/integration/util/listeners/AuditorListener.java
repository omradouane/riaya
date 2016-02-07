/**
 * 
 */
package ma.riaya.integration.util.listeners;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import ma.riaya.model.dictionary.BaseObject;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class AuditorListener {

	@PrePersist
	public void onPrePersist(final BaseObject bo) {
		bo.setCreated(LocalDateTime.now());
		bo.setCreatedBy("createdBy");
	}

	@PreUpdate
	public void onPreUpdate(final BaseObject bo) {
		bo.setUpdated(LocalDateTime.now());
		bo.setUpdatedBy("updatedBy");
	}
}
