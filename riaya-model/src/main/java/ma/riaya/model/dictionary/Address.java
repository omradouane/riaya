/**
 * 
 */
package ma.riaya.model.dictionary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
@Entity
@TableGenerator(name = "AddressKeyGen", table = "DIC_SEQUENCE", pkColumnName = "COLUMN_NAME",
				pkColumnValue = "ADDRESS_ID", valueColumnName = "SEQ_VAL", initialValue = 0,
				allocationSize = 1)
public class Address extends BaseObject {

	private static final long serialVersionUID = 33021173340694837L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "AddressKeyGen")
	@Column(name = "ADDRESS_ID", nullable = false, insertable = true, updatable = true)
	private Long id;
	
	private String description;

	/**
	 * Get the id
	 *
	 * @author ouledmoussa
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set the id
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Get the description
	 *
	 * @author ouledmoussa
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description
	 *
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Address [id=" + id + ", description=" + description + "]";
	}
	
}
