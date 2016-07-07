/**
 * 
 */
package ma.riaya.model.folder.social;

/**
 * @author ouledmoussa
 *
 */
public enum CareType {

	PERMANENT("Permanente"), SAISONAL("Saisonnière");

	private String value;

	/**
	 * @param value
	 */
	private CareType(final String value) {
		this.value = value;
	}

	/**
	 * Get the value for this object
	 *
	 * @author ouledmoussa
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
}
