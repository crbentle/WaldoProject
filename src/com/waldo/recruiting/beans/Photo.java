package com.waldo.recruiting.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author Chris Bentley
 */
@XmlRootElement(name = "photo")
@XmlAccessorType (XmlAccessType.FIELD)
public class Photo {
	
	public static final List<String> VALID_IMG_EXT = new ArrayList<String>( Arrays.asList( "JPEG", "JPG",
			"BMP", "PNG" ) );
	
	private String key;
	private int size;
	private Date lastModified;
	private String error;
	private Map<String,String> exifData = new HashMap<String,String>();
	
	/**
	 * Gets the key
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * Sets the key
	 * @param key the key to set
	 */
	public void setKey( String key ) {
		this.key = key;
	}
	/**
	 * Gets the size
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Sets the size
	 * @param size the size to set
	 */
	public void setSize( int size ) {
		this.size = size;
	}
	/**
	 * Gets the lastModified
	 * @return the lastModified
	 */
	public Date getLastModified() {
		return lastModified;
	}
	/**
	 * Sets the lastModified
	 * @param lastModified the lastModified to set
	 */
	public void setLastModified( Date lastModified ) {
		this.lastModified = lastModified;
	}
	/**
	 * Gets the error
	 * @return the error
	 */
	public String getError() {
		return error;
	}
	/**
	 * Sets the error
	 * @param error the error to set
	 */
	public void setError( String error ) {
		this.error = error;
	}
	/**
	 * Gets the exifData
	 * @return the exifData
	 */
	public Map<String, String> getExifData() {
		return exifData;
	}
	/**
	 * Sets the exifData
	 * @param exifData the exifData to set
	 */
	public void setExifData( Map<String, String> exifData ) {
		this.exifData = exifData;
	}
	public void addExifData( String key, String value ) {
		exifData.put( key, value );
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nPhoto [\n\tkey=" + key + "\n\tsize=" + size + "\n\tlastModified=" + lastModified
				 + "\n\terror=" + error+ "\n\texifData=" + exifData + "]";
	}

}
