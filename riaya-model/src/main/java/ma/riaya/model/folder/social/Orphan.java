/**
 * 
 */
package ma.riaya.model.folder.social;

import java.time.LocalDate;
import java.time.Period;

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
import javax.persistence.Transient;

import ma.riaya.model.dictionary.Address;
import ma.riaya.model.dictionary.BaseObject;
import ma.riaya.model.dictionary.Person;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
@Entity
@TableGenerator(name = "OrphanKeyGen", table = "DIC_SEQUENCE", pkColumnName = "COLUMN_NAME",
				pkColumnValue = "ORPHAN_ID", valueColumnName = "SEQ_VAL", initialValue = 0,
				allocationSize = 1)
public class Orphan extends BaseObject {

	private static final long serialVersionUID = -6811293418550313199L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "OrphanKeyGen")
	@Column(name = "ORPHAN_ID", nullable = false, insertable = true, updatable = true)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PERSON_ID")
	private Person person;
	
	private Integer number;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCOLAR_LEVEL_ID")
	private ScolarLevel scolarLevel;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FAMILY_ID")
	private Family family;
	
	@Transient
	private Integer age;
	

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

	/**
	 * Get the person
	 *
	 * @author ouledmoussa
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * Set the person
	 *
	 * @param person
	 *            the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
		if (person != null && person.getDateOfBirth() != null) {
			final LocalDate toDay = LocalDate.now();
			final Period p = Period.between(person.getDateOfBirth(), toDay);
			this.age = Integer.valueOf(p.getYears());
		}
	}

	/**
	 * Get the age
	 *
	 * @author ouledmoussa
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * Get the number
	 *
	 * @author ouledmoussa
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * Set the number
	 *
	 * @param number
	 *            the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	
	/**
	 * Get the scolarLevel
	 *
	 * @author ouledmoussa
	 * @return the scolarLevel
	 */
	public ScolarLevel getScolarLevel() {
		return scolarLevel;
	}

	/**
	 * Set the scolarLevel
	 *
	 * @param scolarLevel
	 *            the scolarLevel to set
	 */
	public void setScolarLevel(ScolarLevel scolarLevel) {
		this.scolarLevel = scolarLevel;
	}

	/**
	 * Get the address
	 *
	 * @author ouledmoussa
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Set the address
	 *
	 * @param address
	 *            the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * Get the family
	 *
	 * @author ouledmoussa
	 * @return the family
	 */
	public Family getFamily() {
		return family;
	}

	/**
	 * Set the family
	 *
	 * @param family
	 *            the family to set
	 */
	public void setFamily(Family family) {
		this.family = family;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Orphan [id=" + id + ", person=" + person + ", number=" + number
				+ ", age=" + age + "]";
	}
	
}
