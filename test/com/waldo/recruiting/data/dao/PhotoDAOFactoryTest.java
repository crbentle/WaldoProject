package com.waldo.recruiting.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.waldo.recruiting.data.dao.PhotoDAOFactory.RETRIEVE_DAO;
import com.waldo.recruiting.data.dao.PhotoDAOFactory.STORE_DAO;

/**
 * @author Chris Bentley
 *
 */
public class PhotoDAOFactoryTest {
	
	@Test
	public void testGetRetrievePhotoDAO() {
		RetrievePhotoDAO dao = PhotoDAOFactory.getRetrievePhotoDAO( RETRIEVE_DAO.AmazonA3 );
		assertNotNull( "dao shold not be null.", dao );
		assertEquals( "dao was not the expected class.", RetrievePhotoAmazonS3DAO.class, dao.getClass() );
		
		dao = PhotoDAOFactory.getRetrievePhotoDAO( null );
		assertNull( "dao shold be null.", dao );
	}
	
	@Test
	public void testGetStorePhotoDAO() {
		StorePhotoDAO dao = PhotoDAOFactory.getStorePhotoDAO( STORE_DAO.JSON );
		assertNotNull( "dao shold not be null.", dao );
		assertEquals( "dao was not the expected class.", StorePhotoJsonDAO.class, dao.getClass() );
		
		dao = PhotoDAOFactory.getStorePhotoDAO( STORE_DAO.XML );
		assertNotNull( "dao shold not be null.", dao );
		assertEquals( "dao was not the expected class.", StorePhotoXmlDAO.class, dao.getClass() );
		
		dao = PhotoDAOFactory.getStorePhotoDAO( null );
		assertNull( "dao shold be null.", dao );
	}

}
