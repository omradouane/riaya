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
@TableGenerator(name = "RoleKeyGen", table = "DIC_SEQUENCE", pkColumnName = "COLUMN_NAME",
				pkColumnValue = "ROLE_ID", valueColumnName = "SEQ_VAL", initialValue = 0,
				allocationSize = 1)
public class Role extends BaseObject {

	private static final long serialVersionUID = -8424013686322615076L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RoleKeyGen")
	@Column(name = "ROLE_ID", nullable = false, insertable = true, updatable = true)
	private Long id;

	@Column(unique = true)
	private RoleEnum roleName;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @return the roleName
	 */
	public RoleEnum getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(final RoleEnum roleName) {
		this.roleName = roleName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + "]";
	}
	
}
