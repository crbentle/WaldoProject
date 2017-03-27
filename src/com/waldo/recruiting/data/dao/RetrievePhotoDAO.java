package com.waldo.recruiting.data.dao;

import java.util.List;

import com.waldo.recruiting.beans.Photo;

/**
 * An interface outlining the methods needed to retrieve photo data
 * 
 * @author Chris Bentley
 */
public interface RetrievePhotoDAO {
	
	/**
	 * Retrieve a list of Photo objects containing exif data
	 * @return The list of Photo objects
	 */
	public List<Photo> retrievePhotoList() throws Exception;

}
