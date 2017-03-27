package com.waldo.recruiting.data.dao;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import com.waldo.recruiting.beans.Photo;
import com.waldo.recruiting.beans.amazons3.Contents;
import com.waldo.recruiting.beans.amazons3.ListBucketResult;
import com.waldo.recruiting.data.MetaDataExtractor;

/**
 * The DAO to retrieve photo data from an Amazon S3 store.
 * The list of photos will be in an XML document.
 * 
 * @author Chris Bentley
 */
public class RetrievePhotoAmazonS3DAO implements RetrievePhotoDAO {
	
	private static final String S3_URL = "http://s3.amazonaws.com/waldo-recruiting";

	/* (non-Javadoc)
	 * @see com.waldo.recruiting.data.dao.RetrievePhotoDAO#retrievePhotoList()
	 */
	@Override
	public List<Photo> retrievePhotoList() throws Exception {
		ListBucketResult xmlResult = parseXML();
		List<Photo> photoList = createPhotoList( xmlResult );
		extractMetaData( photoList );
		return photoList;
	}
	
	/**
	 * Unmarshal the photo index XML
	 * 
	 * @return A Java object containing the photo index details
	 */
	protected ListBucketResult parseXML() throws Exception {
		ListBucketResult result = null;
		try {
			URL url = new URL( S3_URL );
			JAXBContext jc = JAXBContext.newInstance( ListBucketResult.class );
			Unmarshaller u = jc.createUnmarshaller();
			u.setEventHandler( new ValidationEventHandler() {
				public boolean handleEvent( ValidationEvent event ) {
					throw new RuntimeException( event.getMessage(), event.getLinkedException() );
				}
			} );

			result = (ListBucketResult) u.unmarshal( url.openStream() );
		} catch ( JAXBException e ) {
			// Throw the exception to be handled by the Main class
			throw e;
		} catch ( MalformedURLException e ) {
			// Throw the exception to be handled by the Main class
			throw e;
		} catch ( IOException e ) {
			// Throw the exception to be handled by the Main class
			throw e;
		}

		return result;
	}
	
	/**
	 * Create a list of Photo objects from the ListBucketResult ListBucketResult
	 *  is an Amazon S3 specific file type that we need to convert into a generic
	 *  object
	 * 
	 * @param xmlResult The index of photos from the Amazon S3 store
	 * @return The list of generic Photo objects
	 */
	protected List<Photo> createPhotoList( ListBucketResult xmlResult ) {
		List<Photo> photoList = new ArrayList<Photo>();
		if ( xmlResult != null && xmlResult.getContents() != null
				&& xmlResult.getContents().length > 0 ) {
			Photo photo;
			for ( Contents content : xmlResult.getContents() ) {
				if ( content != null ) {
					String key = content.getKey();
					String extension = null;
					int extIndex = key.lastIndexOf( "." );
					if ( extIndex > -1 ) {
						extension = key.substring( extIndex + 1 ).toUpperCase();
					}

					// Ignore any files that do not have a valid photo extension
					if ( Photo.VALID_IMG_EXT.contains( extension ) ) {
						photo = new Photo();
						photo.setKey( key );
						photo.setLastModified( content.getLastModified() );
						photo.setSize( content.getSize() );
						photoList.add( photo );
					} else {
						// Log unknown file type
					}
				}
			}
		}

		return photoList;
	}
	
	/**
	 * Download the photo, extract its exif data, and update the Photo with the data
	 * 
	 * @param photoList The list of Photos for which to extract data
	 */
	protected void extractMetaData( List<Photo> photoList ) {

		for ( Photo photo : photoList ) {
			String photoUrl = S3_URL + "/" + photo.getKey();
			// Log "Processing " + photoUrl
			System.out.println( "Processing " + photoUrl );
			try {
				photo.setExifData( MetaDataExtractor.extractMetaData( photoUrl ) );
			} catch ( Exception e ) {
				photo.setError( e.toString() );
			}
		}
	}

}
