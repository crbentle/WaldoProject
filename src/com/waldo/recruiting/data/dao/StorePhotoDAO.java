package com.waldo.recruiting.data.dao;

import java.util.List;

import com.waldo.recruiting.beans.Photo;

/**
 * An interface outlining the methods needed to store photo data
 * 
 * @author Chris Bentley
 */
public interface StorePhotoDAO {
	
	/**
	 * Store a list of Photo objects containing exif data
	 * 
	 * @param photoList The list of photos to store
	 * @throws Exception Any exceptions that occur while storing the data
	 */
	public void storePhotoList( List<Photo> photoList ) throws Exception;

}
