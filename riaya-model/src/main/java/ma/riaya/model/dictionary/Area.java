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
@TableGenerator(name = "AreaKeyGen", table = "DIC_SEQUENCE", pkColumnName = "COLUMN_NAME",
				pkColumnValue = "AREA_ID", valueColumnName = "SEQ_VAL", initialValue = 0,
				allocationSize = 1)
public class Area extends BaseObject {

	private static final long serialVersionUID = 6737795587016913584L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "AreaKeyGen")
	@Column(name = "AREA_ID", nullable = false, insertable = true, updatable = true)
	private Long id;

	@Column(unique = true)
	private String areaName;

	private String address;

	/**
	 * Get the areaName for this object
	 *
	 * @author ouledmoussa
	 * @return the areaName
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * Set the areaName for this object
	 *
	 * @author ouledmoussa
	 * @param areaName
	 *            the areaName to set
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * Get the address for this object
	 *
	 * @author ouledmoussa
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set the address for this object
	 *
	 * @author ouledmoussa
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	
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
	public void setId(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Area [id=" + id + ", areaName=" + areaName + ", address="
				+ address + "]";
	}

}
