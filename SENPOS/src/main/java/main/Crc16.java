package main;

public class Crc16 {
		
	public static int crc16(byte[] buffer) 
	{
	    int crc = 0xffff;

	    for (int i = 0; i < buffer.length ; i++) {
	        crc = ((crc  >>> 8) | (crc  << 8) )& 0xffff;
	        crc ^= (buffer[i] & 0xff);
	        crc ^= ((crc & 0xff) >> 4);
	        crc ^= (crc << 12) & 0xffff;
	        crc ^= ((crc & 0xFF) << 5) & 0xffff;
	    }
	    crc &= 0xffff;
	    
	    return crc;
	}
	
	public static int crcComparator(String buffer)
	{
		byte[] rawBuf = buffer.getBytes();
		int newCrc = crc16(rawBuf);
		
		int rawCrc = Integer.parseInt(buffer);
		
		if (rawCrc == newCrc)
			return 1;
		else
			return 0;
	}
}
