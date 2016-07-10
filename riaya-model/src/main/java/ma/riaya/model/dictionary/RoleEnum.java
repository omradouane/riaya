/**
 * 
 */
package ma.riaya.model.dictionary;

/**
 * @author <a href="mailto:om.radouane@gmail.com">Radouane OULEDMOUSSA</a>
 *
 */
public enum RoleEnum {

	ADMIISTRATOR("Administrateur"),
	SOCIAL_WORKER("Assistant social");
	
	private String value;

	RoleEnum(final String name) {
		this.value = name;
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
	
}
