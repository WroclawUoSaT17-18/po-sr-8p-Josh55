import junit.framework.TestCase;
import uiController.MainController;

public class serialComTest extends TestCase {
	
	private String value1;
	private String value2;
	
	private MainController controller = new MainController();
	
	public serialComTest(String testName)
	{
		super(testName);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		value1 = "Sys:";
		value2 = "55001122";
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		value1 = "RX:";
		value2 = "0000000";
	}
	
//	public void testAppend()
//	{
//		String expected = "Sys:55001122";
//	//	String real = testTest(value2);
//		String real		= controller.appendConsoleSys(value2);
//		
//		if (expected.equals(real))
//			fail();
//		
//		//	assertEquals(expected, real);
//	}
//
//	public void testFailedAppend()
//	{
//		String expected = "55001122";
//		String real		= controller.appendConsoleSys(value2);
//		
//		if (expected.equals(real))
//			fail();		
//		
//		//assertNotSame(expected, real);
//	}
}
