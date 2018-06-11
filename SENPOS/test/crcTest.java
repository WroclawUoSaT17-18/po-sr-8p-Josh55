import java.util.Random;

import junit.framework.TestCase;
import main.Crc16;

public class crcTest extends TestCase {
	
	private byte[] value1 = {1,1,0,3,0,1,0,2};
	
	Random random = new Random();
	
	public crcTest(String testName)
	{
		super(testName);
	}
	
	public void testCrcGeneratorPass() throws Exception
	{
		int expected = 0x372C;
		int real = Crc16.crc16(value1);
		System.out.println(real);
		
		if(expected != real)
			fail();
	}
	
	public void testCrcGeneratorFail() throws Exception
	{
		int expected = 0x3455;
		int real = Crc16.crc16(value1);
		System.out.println(real);
		
		if(expected != real)
			fail();
	}
	
	int pass = 0;
	int fail = 0;
	
	public void testCrcGeneratorCyclic() throws Exception
	{
		int expected = 0x372C;
		int real;
		
		for (long i = 0; i < 1000000; i++)
		{
			for (int j = 0; j < 8 ; j++)
			{	
				value1[j] = (byte) random.nextInt(0xF);
			}
			
			real = Crc16.crc16(value1);
			//System.out.println(real);
			
			if(expected != real)
				fail++;
			else
				pass++;
		}
		
		System.out.println("Pass = " + pass + "\nfail = " + fail);
		
		if (fail != 0)
			fail();
	}

	
//	protected void setUp() throws Exception {
//		super.setUp();
//		
//		for (int i =0; i < 20 ; i++)
//			value1[i] = 0;
//	}
//
//	protected void tearDown() throws Exception {
//		super.tearDown();
//		value1[0] = 0;
//		value1[1] = 0;
//		value1[2] = 0;
//		value1[3] = 0;
//	}
	
//	public void testAppend()
//	{
//		int expected = 0xffff;
//		int real = Crc16.crc16(value1);
//		System.out.println(real);
//		
//		if(expected != real)
//			fail();
//	}
//	
//	public void testFailedAppend()
//	{
//		int expected = 0xffff;
//		int real = Crc16.crc16(value1);
//		System.out.println(real);
//		
//		if(expected != real)
//			fail();
//	}

}
