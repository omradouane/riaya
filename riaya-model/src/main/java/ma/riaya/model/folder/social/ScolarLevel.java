/**
 * 
 */
package ma.riaya.model.folder.social;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

import ma.riaya.model.dictionary.BaseObject;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
@Entity
@TableGenerator(name = "ScolarLevelKeyGen", table = "DIC_SEQUENCE", pkColumnName = "COLUMN_NAME", 
				pkColumnValue = "SCOLAR_LEVEL_ID", valueColumnName = "SEQ_VAL", initialValue = 0,
				allocationSize = 1)
public class ScolarLevel extends BaseObject {

	private static final long serialVersionUID = 7205651713391702756L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ScolarLevelKeyGen")
	@Column(name = "SCOLAR_LEVEL_ID", nullable = false, insertable = true, updatable = true)
	private Long id;
	
	@Column(unique = true)
	private String name;

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
	 * Get the name
	 *
	 * @author ouledmoussa
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name
	 *
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ScolarLevel [id=" + id + ", name=" + name + "]";
	}
	
}
