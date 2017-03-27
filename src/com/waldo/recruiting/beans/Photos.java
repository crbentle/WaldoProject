package com.waldo.recruiting.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Chris Bentley
 */
@XmlRootElement(name = "photos")
@XmlAccessorType(XmlAccessType.FIELD)
public class Photos {

	@XmlElement(name = "photo")
	private List<Photo> photos = null;

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos( List<Photo> photos ) {
		this.photos = photos;
	}
}
