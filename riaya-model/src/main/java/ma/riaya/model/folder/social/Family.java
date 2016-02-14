/**
 * 
 */
package ma.riaya.model.folder.social;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import ma.riaya.model.dictionary.Address;
import ma.riaya.model.dictionary.Area;
import ma.riaya.model.dictionary.BaseObject;
import ma.riaya.model.dictionary.Person;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
@Entity
@TableGenerator(name = "FamilyKeyGen", table = "DIC_SEQUENCE", pkColumnName = "COLUMN_NAME", 
				pkColumnValue = "FAMILY_ID", valueColumnName = "SEQ_VAL", initialValue = 0,
				allocationSize = 1)
public class Family extends BaseObject {

	private static final long serialVersionUID = 3435991692110735624L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FamilyKeyGen")
	@Column(name = "FAMILY_ID", nullable = false, insertable = true, updatable = true)
	private Long id;

	@Column(unique = true)
	private String familyName;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "AREA_ID")
	private Area area;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PERSON_ID")
	private Person responsible;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SOCIAL_WORKER_ID")
	private SocialWorker socialWorker;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;
	
	private CareType careType;
	
	@OneToMany(mappedBy="family")
	private Collection<Orphan> orphans = new ArrayList<>();

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
	 * Get the familyName
	 *
	 * @author ouledmoussa
	 * @return the familyName
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * Set the familyName. By default this method upper case the given family
	 * name.
	 *
	 * @param familyName
	 *            the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName.toUpperCase();
	}

	/**
	 * Get the area
	 *
	 * @author ouledmoussa
	 * @return the area
	 */
	public Area getArea() {
		return area;
	}

	/**
	 * Set the area
	 *
	 * @param area
	 *            the area to set
	 */
	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 * Get the responsible
	 *
	 * @author ouledmoussa
	 * @return the responsible
	 */
	public Person getResponsible() {
		return responsible;
	}

	/**
	 * Set the responsible
	 *
	 * @param responsible
	 *            the responsible to set
	 */
	public void setResponsible(Person responsible) {
		this.responsible = responsible;
	}

	/**
	 * Get the socialWorker
	 *
	 * @author ouledmoussa
	 * @return the socialWorker
	 */
	public SocialWorker getSocialWorker() {
		return socialWorker;
	}

	/**
	 * Set the socialWorker
	 *
	 * @param socialWorker
	 *            the socialWorker to set
	 */
	public void setSocialWorker(SocialWorker socialWorker) {
		this.socialWorker = socialWorker;
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
	 * Get the careType
	 *
	 * @author ouledmoussa
	 * @return the careType
	 */
	public CareType getCareType() {
		return careType;
	}

	/**
	 * Set the careType
	 *
	 * @param careType
	 *            the careType to set
	 */
	public void setCareType(CareType careType) {
		this.careType = careType;
	}

	/**
	 * Get the orphans
	 *
	 * @author ouledmoussa
	 * @return the orphans
	 */
	public Collection<Orphan> getOrphans() {
		return orphans;
	}

	/**
	 * Set the orphans
	 *
	 * @param orphans
	 *            the orphans to set
	 */
	public void setOrphans(Collection<Orphan> orphans) {
		this.orphans = orphans;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Family [id=" + id + ", familyName=" + familyName + ", area="
				+ area + ", responsible=" + responsible + ", socialWorker="
				+ socialWorker + ", address=" + address + ", careType="
				+ careType + "]";
	}

}
