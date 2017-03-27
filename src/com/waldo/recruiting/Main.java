package com.waldo.recruiting;

import java.util.List;

import com.waldo.recruiting.beans.Photo;
import com.waldo.recruiting.data.dao.PhotoDAOFactory;
import com.waldo.recruiting.data.dao.PhotoDAOFactory.RETRIEVE_DAO;
import com.waldo.recruiting.data.dao.PhotoDAOFactory.STORE_DAO;
import com.waldo.recruiting.data.dao.RetrievePhotoDAO;
import com.waldo.recruiting.data.dao.StorePhotoDAO;

/**
 * Extract exif data from a list of photos and store the data in a persistent store
 * 
 * @author Chris Bentley
 */
public class Main {
	
	public static void main(String[] args){
		RetrievePhotoDAO retrieveDAO = PhotoDAOFactory.getRetrievePhotoDAO( RETRIEVE_DAO.AmazonA3 );
//		StorePhotoDAO storeDAO = PhotoDAOFactory.getStorePhotoDAO( STORE_DAO.JSON );
		
		// A DAO to store the results in an XML file
		 StorePhotoDAO storeDAO = PhotoDAOFactory.getStorePhotoDAO( STORE_DAO.XML );

		if ( retrieveDAO == null ) {
			// Log message and end program
			System.out.println("RetrievePhotoDAO could not be created for " + RETRIEVE_DAO.AmazonA3 );
			System.exit( -1 );
		}
		
		if ( storeDAO == null ) {
			// Log message and end program
			System.out.println("StorePhotoDAO could not be created for " + STORE_DAO.JSON );
			System.exit( -1 );
		}
		
		try {
			List<Photo> photoList = retrieveDAO.retrievePhotoList();
			storeDAO.storePhotoList( photoList );
		} catch ( Exception e ) {
			// Log message and end program
			e.printStackTrace();
			System.exit( -1 );
		}
	}
}
