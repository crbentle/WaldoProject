package com.waldo.recruiting.data;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

/**
 * A helper class to extract exif data from a photo.
 * Uses third-party jar 
 *  <a href="https://drewnoakes.com/code/exif/">metadata-extractor-2.10.1.jar</a>
 * 
 * @author Chris Bentley
 */
public class MetaDataExtractor {

	/**
	 * Opens a connection to the photo url and reads the meta data
	 * 
	 * @param photoURL The url of the photo to read
	 * @return A Map containing exif data key/value pairs
	 * @throws Any exceptions that occur during data extraction
	 */
	public static Map<String, String> extractMetaData( String photoURL ) throws Exception {
		Map<String, String> metaData = new HashMap<String, String>();

		if ( photoURL != null && photoURL.length() > 0 ) {
			try {
				URL url = new URL( photoURL );
				Metadata metadata = ImageMetadataReader.readMetadata( url.openStream() );

				String tagName;
				String tagDescription;
				for ( Directory directory : metadata.getDirectories() ) {
					for ( Tag tag : directory.getTags() ) {
						tagName = tag.getTagName();
						tagDescription = tag.getDescription();
						
						// Do not include empty or unknown attributes
						if ( tagName != null && tagName.trim().length() > 0
								&& tagDescription != null && tagDescription.trim().length() > 0
								&& !tagName.contains( "Unknown" ) ) {
							metaData.put( tagName.trim(), tagDescription.trim() );
						}
					}
					
					if ( directory.hasErrors() ) {
						String error = "ERROR: ";
						for ( String err : directory.getErrors() ) {
							error += err;
						}
						throw new Exception( error );
					}
				}

			} catch ( ImageProcessingException e ) {
				// Throw the exception to be handled by the caller
				throw e;
			} catch ( IOException e ) {
				// Throw the exception to be handled by the caller
				throw e;
			}
		}

		return metaData;
	}
}
