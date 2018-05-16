package main;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;
import uiController.MainController;

public class CommunicationManager implements SerialPortEventListener{
	
	// ENUMS
	public enum ConnectResult {
		CONNECTED, CONNECT_ERROR
	}
		
	public enum DisconnectResult {
		DISCONNECTED, DISCONNECT_ERROR
	}
	
	private SerialPort sp = null;
	
	private ExecutorService executorService = null;
	private LinkedBlockingQueue<String> buffRX = null;
	
	// CONSTRUCTOR
	public CommunicationManager()
	{
		executorService = Executors.newSingleThreadExecutor();
		buffRX = new LinkedBlockingQueue<String>();
		
		executorService.execute(new Runnable()
		{
			
			public void run()
			{
				String buf = "";
				
				while(true)
				{
					try {
						String data = buffRX.poll(10, TimeUnit.MILLISECONDS);
						
						if(data != null) 
						{
							buf += data;
							
							if(buf.indexOf("\r\n") > 0) 
							{
								String command = buf.substring(0, buf.indexOf("\r\n") + 1);
								command = command.replaceAll("\r", "");
								command = command.replaceAll("\n", "");
								buf = buf.substring(buf.indexOf("\r\n") + 1);
								//TODO bug fix
								AppComponents.getMainController().appendConsoleRx(command);
								//TODO  notify list view
							}
						}
					} catch (InterruptedException e)
					{
						System.out.println("RX error");
					}
				}
			}
		});
	}
	
	// SERIAL PORT EVENT HANDLER
	public void serialEvent(SerialPortEvent serialPortEvent) 
	{
			// TODO Auto-generated method stub
			if(serialPortEvent.isRXCHAR() && serialPortEvent.getEventValue() > 0)
			{
				try {
					buffRX.add(sp.readString(serialPortEvent.getEventValue()));
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Critical failure RX");
				}
			}			
	}
	
	/**
	 * Lists available COM ports
	 * @return LinkedList of COM port names
	 */
	public LinkedList<String> getComList() 
	{
		String[] ports = SerialPortList.getPortNames();
		
		LinkedList<String> coms = new LinkedList<String>();
		
		for(String s : ports) {
			coms.add(s);
		}		
	
		return coms;
	}
	
	/**
	 * Plugs into provided COM port
	 * @param com COM port name
	 * @return operation result
	 */
	public ConnectResult connectToCOM(String com) 
	{
		try {
			// check if the SerialPort object 
			if(sp != null) {
				if(sp.isOpened())
					return ConnectResult.CONNECT_ERROR;
			}
				
			sp = new SerialPort(com);
			sp.openPort();
			sp.setParams(SerialPort.BAUDRATE_115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			
			// make sure the connection is OK
			if(sp.isOpened()) {
				// add event listener do the serial port
				sp.addEventListener(this, SerialPort.MASK_RXCHAR);
				return ConnectResult.CONNECTED;
			}
			else {
				return ConnectResult.CONNECT_ERROR;
				
			}			
			
		} catch(Exception e) {
			return ConnectResult.CONNECT_ERROR;
		}
	}
	
	/**
	 * Disconnects from the current COM port
	 * @return operation result
	 */
	public DisconnectResult disconnectFromCOM() 
	{
		try {
			sp.closePort();
			return DisconnectResult.DISCONNECTED;
		} catch(Exception e) {
			return DisconnectResult.DISCONNECT_ERROR;
		}
	}
	
	/**
	 * Attempts to transmit a String through connected COM port.
	 * @param aData					string for transmission
	 */
	public void sendString(String aData) 
	{
		try {
			sp.writeString(aData);
		} catch (SerialPortException e) {
			
			System.out.println("Fatal Error sendString");
		}
	}
	
	/**
	 * Attempts to transmit a Integer array through connected COM port.
	 * @param aData		Integer array to transmission
	 */
	public void sendByteArray(byte[] aData)
	{
		try {
			sp.writeBytes(aData);
			
		} catch (SerialPortException e)
		{
			System.out.println("Fatal Error sendByte");
		}
	}
	
}
