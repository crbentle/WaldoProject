package com.waldo.recruiting.data.dao;

/**
 * Determine which RetrievePhotoDAO to use
 * 
 * @author Chris Bentley
 */
public class PhotoDAOFactory {

	public static enum RETRIEVE_DAO {
		AmazonA3
	};
	
	public static enum STORE_DAO {
		JSON,
		XML
	};

	/**
	 * Returns the RetrievePhotoDAO to use for the given DAO type
	 * 
	 * @param dao The DAO type to be retrieved
	 * @return The matching RetrievePhotoDAO
	 */
	public static RetrievePhotoDAO getRetrievePhotoDAO( RETRIEVE_DAO dao ) {
		RetrievePhotoDAO photoDAO = null;
		switch ( dao ) {
			case AmazonA3:
				photoDAO = new RetrievePhotoAmazonS3DAO();
				break;
			default:
				// Do nothing. Will return a null DAO
		}

		return photoDAO;
	}
	
	/**
	 * Returns the StorePhotoDAO to use for the given DAO type
	 * 
	 * @param dao The DAO type to be retrieved
	 * @return The matching StorePhotoDAO
	 */
	public static StorePhotoDAO getStorePhotoDAO( STORE_DAO dao ) {
		StorePhotoDAO storeDAO = null;
		switch ( dao ) {
			case JSON:
				storeDAO = new StorePhotoJsonDAO();
				break;
			case XML:
				storeDAO = new StorePhotoXmlDAO();
				break;
			default:
				// Do nothing. Will return a null DAO

		}
		
		return storeDAO;
	}
}
