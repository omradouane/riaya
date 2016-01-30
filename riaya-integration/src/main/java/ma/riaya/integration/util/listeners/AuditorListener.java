/**
 * 
 */
package ma.riaya.integration.util.listeners;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import ma.riaya.model.dictionary.BaseObject;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public class AuditorListener {

	@PrePersist
	public void onPrePersist(final BaseObject baseObject) {
		baseObject.setCreatedBy("createdBy");
	}

	@PreUpdate
	public void onPreUpdate(final BaseObject baseObject) {
		baseObject.setUpdatedBy("updatedBy");
	}
}
