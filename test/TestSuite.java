import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.waldo.recruiting.data.dao.PhotoDAOFactoryTest;
import com.waldo.recruiting.data.dao.RetrievePhotoAmazonS3DAOTest;
import com.waldo.recruiting.data.dao.StorePhotoJsonDAOTest;
import com.waldo.recruiting.data.dao.StorePhotoXmlDAOTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	PhotoDAOFactoryTest.class,
	RetrievePhotoAmazonS3DAOTest.class,
	StorePhotoJsonDAOTest.class,
	StorePhotoXmlDAOTest.class
})

/**
 * @author Chris Bentley
 *
 */
public class TestSuite {
  // the class remains empty,
  // used only as a holder for the above annotations
}

