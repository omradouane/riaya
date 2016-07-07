/**
 * 
 */
package ma.riaya.model.folder.social;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;

import ma.riaya.model.dictionary.BaseObject;
import ma.riaya.model.dictionary.Person;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
@Entity
@TableGenerator(name = "SocialWorkerKeyGen", table = "DIC_SEQUENCE", pkColumnName = "COLUMN_NAME", 
				pkColumnValue = "SOCIAL_WORKER_ID", valueColumnName = "SEQ_VAL", initialValue = 0,
				allocationSize = 1)
@NamedQueries({
		@NamedQuery(name = "socialWorker.findByFirstName", 
					query = "select s from SocialWorker s join s.person p where p.firstName = :firstName"),
		@NamedQuery(name = "socialWorker.findByLastName", 
					query = "select s from SocialWorker s join s.person p where p.lastName = :lastName")
})
public class SocialWorker extends BaseObject {

	private static final long serialVersionUID = -3629402996391867834L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SocialWorkerKeyGen")
	@Column(name = "SOCIAL_WORKER_ID", nullable = false, insertable = true, updatable = true)
	private Long id;

	// TODO oneToOne
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PERSON_ID")
	private Person person;

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
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SocialWorker [id=" + id + ", person=" + person + "]";
	}
	
}
