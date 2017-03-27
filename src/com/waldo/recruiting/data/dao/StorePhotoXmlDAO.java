package com.waldo.recruiting.data.dao;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.waldo.recruiting.beans.Photo;
import com.waldo.recruiting.beans.Photos;

/**
 * Stores photo data in an XML file
 * 
 * @author Chris Bentley
 */
public class StorePhotoXmlDAO implements StorePhotoDAO {

	/* (non-Javadoc)
	 * @see com.waldo.recruiting.data.dao.StorePhotoDAO#storePhotoList(java.util.List)
	 */
	@Override
	public void storePhotoList( List<Photo> photoList ) throws Exception {

		if ( photoList == null || photoList.size() == 0 ) {
			throw new Exception( "Cannot store an empty photo list." );
		}

		// Create a wrapper object for the photo list
		Photos photos = new Photos();
		photos.setPhotos( photoList );

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance( Photos.class );
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );

			// Marshal the employees list in file
			jaxbMarshaller.marshal( photos, new File( "photos.xml" ) );
		} catch ( JAXBException e ) {
			e.printStackTrace();
		}
	}

}
