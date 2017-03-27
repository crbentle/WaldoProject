package com.waldo.recruiting.data.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.waldo.recruiting.beans.Photo;

/**
 * @author Chris Bentley
 *
 */
public class StorePhotoXmlDAOTest {
	
	@Test
	public void testStorePhotoList() {
		List<Photo> photoList = new ArrayList<Photo>();
		
		Photo photo = new Photo();
		photo.setKey( "0003b8d6-d2d8-4436-a398-eab8d696f0f9.68cccdd4-e431-457d-8812-99ab561bf867.jpg" );
		Map<String, String> exifData = new HashMap<String, String>();
		exifData.put( "key1", "value1" );
		exifData.put( "key2", "value2" );
		photo.setExifData( exifData );
		photoList.add( photo );

		photo = new Photo();
		photo.setKey( "0015A5C3-D186-471J-A032-9E952CFF3CC6.8fedf4a8-8695-4d6d-ad1e-b686daa713a2.jpg" );
		photo.setError( "java.io.IOException: Server returned HTTP response code: 403 for URL: http://s3.amazonaws.com/waldo-recruiting/0015A5C3-D186-471J-A032-9E952CFF3CC6.8fedf4a8-8695-4d6d-ad1e-b686daa713a2.jpg" );
		photoList.add( photo );

		photo = new Photo();
		photo.setKey( "002a6821-1059-4f22-8203-4504284cb973.ccdeffef-59d2-4a16-813c-35fefa546e93.jpg" );
		photo.setError( "java.lang.Exception: ERROR: Illegal TIFF tag pointer offset" );
		photoList.add( photo );
		
		StorePhotoXmlDAO dao = new StorePhotoXmlDAO( "test.xml" );
		try {
			dao.storePhotoList( photoList );
			File file = new File("test.xml");
			assertTrue( "test.json was not created.", file.exists() );
		} catch ( Exception e ) {
			fail();
			e.printStackTrace();
		}
		
	}

}
