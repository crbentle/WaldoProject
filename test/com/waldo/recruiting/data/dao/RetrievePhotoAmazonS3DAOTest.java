package com.waldo.recruiting.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.waldo.recruiting.beans.Photo;
import com.waldo.recruiting.beans.amazons3.Contents;
import com.waldo.recruiting.beans.amazons3.ListBucketResult;

/**
 * @author Chris Bentley
 *
 */
public class RetrievePhotoAmazonS3DAOTest {

	@Test
	public void testParseXML() {
		RetrievePhotoAmazonS3DAO dao = new RetrievePhotoAmazonS3DAO();
		try {
			ListBucketResult result = dao.parseXML();
			assertNotNull( result );
			assertTrue( "Contents was empty", result.getContents() != null && result.getContents().length > 0 );
			Contents contents = result.getContents()[0];
			assertTrue( "Contents[0].key was empty.", contents.getKey() != null && contents.getKey().trim().length() > 0 );
			assertTrue( "Contents[0].lastModified was empty.", contents.getLastModified() != null );
			assertTrue( "Contents[0].size was empty.", contents.getSize() > 0 );
			
		} catch ( Exception e ) {
			fail();
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreatePhotoList() {
		ListBucketResult result = new ListBucketResult();
		result.setMarker( "result marker" );
		result.setMaxKeys( 3 );
		result.setName( "List Bucket Test" );
		result.setPrefix( "pre_" );
		result.setTruncated( false );
		
		Contents[] contents = new Contents[3];
		Contents content = new Contents();
		content.setKey( "content1.jpg" );
		content.setLastModified( new Date() );
		content.setSize( 1234567 );
		contents[0] = content;

		content = new Contents();
		content.setKey( "content2.png" );
		content.setLastModified( new Date() );
		content.setSize( 7654321 );
		contents[1] = content;

		content = new Contents();
		content.setKey( "bad-extension.exe" );
		content.setLastModified( new Date() );
		content.setSize( 111 );
		contents[2] = content;
		
		result.setContents( contents );
		
		RetrievePhotoAmazonS3DAO dao = new RetrievePhotoAmazonS3DAO();
		List<Photo> photoList = dao.createPhotoList( result );
		
		assertNotNull( "photoList is null", photoList );
		assertEquals( "photoList is not the expected size.", 2, photoList.size() );
		
		Photo photo = photoList.get( 0 );
		assertEquals( "Incorrect key.", "content1.jpg", photo.getKey() );
		assertNotNull( "Not lastModified date.", photo.getLastModified() );
		assertEquals( "Incorrect size.", 1234567, photo.getSize() );
		
		photo = photoList.get( 1 );
		assertEquals( "Incorrect key.", "content2.png", photo.getKey() );
		assertNotNull( "Not lastModified date.", photo.getLastModified() );
		assertEquals( "Incorrect size.", 7654321, photo.getSize() );
	}
	
	@Test
	public void testExtractMetaData() {
		List<Photo> photoList = new ArrayList<Photo>();
		
		Photo photo = new Photo();
		photo.setKey( "0003b8d6-d2d8-4436-a398-eab8d696f0f9.68cccdd4-e431-457d-8812-99ab561bf867.jpg" );
		photoList.add( photo );

		photo = new Photo();
		photo.setKey( "0015A5C3-D186-471J-A032-9E952CFF3CC6.8fedf4a8-8695-4d6d-ad1e-b686daa713a2.jpg" );
		photoList.add( photo );
//        <error>java.io.IOException: Server returned HTTP response code: 403 for URL: http://s3.amazonaws.com/waldo-recruiting/0015A5C3-D186-471J-A032-9E952CFF3CC6.8fedf4a8-8695-4d6d-ad1e-b686daa713a2.jpg</error>
//        <exifData/>

		photo = new Photo();
		photo.setKey( "002a6821-1059-4f22-8203-4504284cb973.ccdeffef-59d2-4a16-813c-35fefa546e93.jpg" );
		photoList.add( photo );
//        <error>java.lang.Exception: ERROR: Illegal TIFF tag pointer offset</error>
//        <exifData/>
		
		RetrievePhotoAmazonS3DAO dao = new RetrievePhotoAmazonS3DAO();
		dao.extractMetaData( photoList );
		
		assertEquals( "Unexpected photoList size.", 3, photoList.size() );
		
		photo = photoList.get( 0 );
		assertTrue( "Exif data should not be empty.", photo.getExifData() != null && photo.getExifData().size() > 0 );
		assertNull( "Error should be empty.", photo.getError() );
		for( String exifKey : photo.getExifData().keySet() ) {
			assertTrue("exifKey is empty.", exifKey.trim().length() > 0 );
			String value = photo.getExifData().get( exifKey );
			assertTrue( "exif value is empty.", value != null && value.trim().length() > 0  );
			assertTrue( "exif key should not contain unknown values: " + exifKey, exifKey.toLowerCase().indexOf( "unknown" ) < 0 );
		}
		
		photo = photoList.get( 1 );
		assertTrue( "Exif data should be empty.", photo.getExifData().size() == 0 );
		assertTrue( "Error should not be empty.", photo.getError() != null && photo.getError().trim().length() > 0 );
		
		photo = photoList.get( 2 );
		assertTrue( "Exif data should be empty.", photo.getExifData().size() == 0 );
		assertTrue( "Error should not be empty.", photo.getError() != null && photo.getError().trim().length() > 0 );
	}
}
