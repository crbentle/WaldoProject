package com.waldo.recruiting.beans.amazons3;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * An Object with attributes matching an Amazon S3 ListBucket Content element
 * 
 * @author Chris Bentley
 */
@XmlRootElement(name="Contents")
@XmlAccessorType(XmlAccessType.FIELD)
public class Contents {
	@XmlElement
	private String Key;
	@XmlElement
	private Date LastModified;
	@XmlElement
	private String ETag;
	@XmlElement
	private int Size;
	@XmlElement
	private String StorageClass;
	
	/**
	 * Gets the Key
	 * @return the Key
	 */
	public String getKey() {
		return Key;
	}
	/**
	 * Sets the Key
	 * @param Key the Key to set
	 */
	public void setKey( String Key ) {
		this.Key = Key;
	}
	/**
	 * Gets the LastModified
	 * @return the LastModified
	 */
	public Date getLastModified() {
		return LastModified;
	}
	/**
	 * Sets the LastModified
	 * @param LastModified the LastModified to set
	 */
	public void setLastModified( Date LastModified ) {
		this.LastModified = LastModified;
	}
	/**
	 * Gets the ETag
	 * @return the ETag
	 */
	public String getETag() {
		return ETag;
	}
	/**
	 * Sets the ETag
	 * @param ETag the ETag to set
	 */
	public void setETag( String ETag ) {
		this.ETag = ETag;
	}
	/**
	 * Gets the Size
	 * @return the Size
	 */
	public int getSize() {
		return Size;
	}
	/**
	 * Sets the Size
	 * @param Size the Size to set
	 */
	public void setSize( int Size ) {
		this.Size = Size;
	}
	/**
	 * Gets the StorageClass
	 * @return the StorageClass
	 */
	public String getStorageClass() {
		return StorageClass;
	}
	/**
	 * Sets the StorageClass
	 * @param StorageClass the StorageClass to set
	 */
	public void setStorageClass( String StorageClass ) {
		this.StorageClass = StorageClass;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\n\t\tContents [\n\t\t\tKey=" + Key + "\n\t\t\tLastModified=" + LastModified
				+ "\n\t\t\tETag=" + ETag + "\n\t\t\tSize=" + Size + "\n\t\t\tStorageClass="
				+ StorageClass + "]";
	}

}
