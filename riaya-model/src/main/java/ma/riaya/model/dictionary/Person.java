/**
 * 
 */
package ma.riaya.model.dictionary;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */

@Entity
@TableGenerator(name = "PersonKeyGen", table = "DIC_SEQUENCE", pkColumnName = "COLUMN_NAME", 
				pkColumnValue = "PERSON_ID", valueColumnName = "SEQ_VAL", initialValue = 0,
				allocationSize = 1)
public class Person extends BaseObject {

	private static final long serialVersionUID = 7081577100219936062L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PersonKeyGen")
	@Column(name = "PERSON_ID", nullable = false, insertable = true, updatable = true)
	private Long id;

	private String firstName;

	private String lastName;

	private LocalDate dateOfBirth;

	private String cinNumber;

	private String phoneNumber;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PICTURE_ID")
	private Picture picture;

	/**
	 * Get the id for this object
	 *
	 * @author ouledmoussa
	 * @return the Id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set the id for this object
	 *
	 * @author ouledmoussa
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get the firstName for this object
	 *
	 * @author ouledmoussa
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set the firstName for this object
	 *
	 * @author ouledmoussa
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get the lastName for this object
	 *
	 * @author ouledmoussa
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set the lastName for this object
	 *
	 * @author ouledmoussa
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get the dateOfBirth for this object
	 *
	 * @author ouledmoussa
	 * @return the dateOfBirth
	 */
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Set the dateOfBirth for this object
	 *
	 * @author ouledmoussa
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Get the cinNumber for this object
	 *
	 * @author ouledmoussa
	 * @return the cinNumber
	 */
	public String getCinNumber() {
		return cinNumber;
	}

	/**
	 * Set the cinNumber for this object
	 *
	 * @author ouledmoussa
	 * @param cinNumber
	 *            the cinNumber to set
	 */
	public void setCinNumber(String cinNumber) {
		this.cinNumber = cinNumber;
	}

	/**
	 * Get the phoneNumber for this object
	 *
	 * @author ouledmoussa
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Set the phoneNumber for this object
	 *
	 * @author ouledmoussa
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	/**
	 * Get the picture
	 *
	 * @author ouledmoussa
	 * @return the picture
	 */
	public Picture getPicture() {
		return picture;
	}

	/**
	 * Set the picture
	 *
	 * @param picture
	 *            the picture to set
	 */
	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", dateOfBirth=" + dateOfBirth + ", cinNumber="
				+ cinNumber + ", phoneNumber=" + phoneNumber + ", picture="
				+ picture + "]";
	}

}
