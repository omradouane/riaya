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
@TableGenerator(name = "UserKeyGen", table = "DIC_SEQUENCE", pkColumnName = "COLUMN_NAME",
				pkColumnValue = "USER_ID", valueColumnName = "SEQ_VAL", initialValue = 0,
				allocationSize = 1)
public class User extends BaseObject {

	private static final long serialVersionUID = 2522195664374512079L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "UserKeyGen")
	@Column(name = "USER_ID", nullable = false, insertable = true, updatable = true)
	private Long id;

	@Column(unique = true)
	private String login;

	private String password;

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
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(final String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=******]";
	}
}
