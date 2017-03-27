package com.waldo.recruiting.beans.amazons3;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * An Object with attributes matching an Amazon S3 ListBucketResult element
 * 
 * @author Chris Bentley
 */
@XmlRootElement(name="ListBucketResult", namespace="http://s3.amazonaws.com/doc/2006-03-01/")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListBucketResult {

	@XmlElement
	private String Name;
	@XmlElement
	private String Prefix;
	@XmlElement
	private String Marker;
	@XmlElement
	private int MaxKeys = -1;
	@XmlElement
	private boolean IsTruncated = false;
	@XmlElement
    private Contents[] Contents = new Contents[1];
	
	/**
	 * Gets the name
	 * @return the name
	 */
	public String getName() {
		return Name;
	}
	/**
	 * Sets the name
	 * @param name the name to set
	 */
	public void setName( String Name ) {
		this.Name = Name;
	}
	/**
	 * Gets the prefix
	 * @return the prefix
	 */
	public String getPrefix() {
		return Prefix;
	}
	/**
	 * Sets the prefix
	 * @param prefix the prefix to set
	 */
	public void setPrefix( String Prefix ) {
		this.Prefix = Prefix;
	}
	/**
	 * Gets the Marker
	 * @return the Marker
	 */
	public String getMarker() {
		return Marker;
	}
	/**
	 * Sets the Marker
	 * @param Marker the Marker to set
	 */
	public void setMarker( String Marker ) {
		this.Marker = Marker;
	}
	/**
	 * Gets the MaxKeys
	 * @return the MaxKeys
	 */
	public int getMaxKeys() {
		return MaxKeys;
	}
	/**
	 * Sets the MaxKeys
	 * @param MaxKeys the MaxKeys to set
	 */
	public void setMaxKeys( int MaxKeys ) {
		this.MaxKeys = MaxKeys;
	}
	/**
	 * Gets the IsTruncated
	 * @return the IsTruncated
	 */
	public boolean IsTruncated() {
		return IsTruncated;
	}
	/**
	 * Sets the IsTruncated
	 * @param IsTruncated the IsTruncated to set
	 */
	public void setTruncated( boolean IsTruncated ) {
		this.IsTruncated = IsTruncated;
	}
	/**
	 * Gets the contents
	 * @return the contents
	 */
	public Contents[] getContents() {
		return Contents;
	}
	/**
	 * Sets the contents
	 * @param contents the contents to set
	 */
	@XmlElement
	public void setContents( Contents[] Contents ) {
		this.Contents = Contents;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ListBucketResult [\n\tName=" + Name + "\n\tPrefix=" + Prefix
				+ "\n\tMarker=" + Marker + "\n\tMaxKeys=" + MaxKeys + "\n\tIsTruncated=" + IsTruncated
				+ "\n\tContents=" + Arrays.toString( Contents ) + "]";
	}

}
