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
@TableGenerator(name = "PictureKeyGen", table = "DIC_SEQUENCE", pkColumnName = "COLUMN_NAME", pkColumnValue = "PICTURE_ID", valueColumnName = "SEQ_VAL", initialValue = 0, allocationSize = 1)
public class Picture extends BaseObject {

	private static final long serialVersionUID = 5471398731687149008L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PictureKeyGen")
	@Column(name = "PICTURE_ID", nullable = false, insertable = true, updatable = true)
	private Long id;

	private String fileName;

	private Integer fileSize;

	private byte[] content;

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
	 * Get the fileName
	 *
	 * @author ouledmoussa
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Set the fileName
	 *
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Get the fileSize
	 *
	 * @author ouledmoussa
	 * @return the fileSize
	 */
	public Integer getFileSize() {
		return fileSize;
	}

	/**
	 * Set the fileSize
	 *
	 * @param fileSize
	 *            the fileSize to set
	 */
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * Get the content
	 *
	 * @author ouledmoussa
	 * @return the content
	 */
	public byte[] getContent() {
		return content;
	}

	/**
	 * Set the content
	 *
	 * @param content
	 *            the content to set
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Picture [id=" + id + ", fileName=" + fileName + ", fileSize="
				+ fileSize + "]";
	}

}
