package com.waldo.recruiting.data.dao;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.waldo.recruiting.beans.Photo;
import com.waldo.recruiting.beans.Photos;

/**
 * Stores photo data in a JSON file
 * 
 * @author Chris Bentley
 */
public class StorePhotoJsonDAO implements StorePhotoDAO {

	/* (non-Javadoc)
	 * @see com.waldo.recruiting.data.dao.StorePhotoDAO#storePhotoList(java.util.List)
	 */
	@Override
	public void storePhotoList( List<Photo> photoList ) throws Exception {
		
		if( photoList == null || photoList.size() == 0 ){
			throw new Exception( "Cannot store an empty photo list." );
		}
		
		// Create a wrapper object for the photo list
		Photos photos = new Photos();
		photos.setPhotos( photoList );

		ObjectMapper mapper = new ObjectMapper();

		try {
			mapper.writeValue(new File("photos.json"), photos);
		} catch ( JsonGenerationException e ) {
			// Throw the exception to be handled by the Main class
			throw e;
		} catch ( JsonMappingException e ) {
			// Throw the exception to be handled by the Main class
			throw e;
		} catch ( IOException e ) {
			// Throw the exception to be handled by the Main class
			throw e;
		}

	}

}
