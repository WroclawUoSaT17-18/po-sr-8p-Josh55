package main;

import java.util.LinkedList;

import main.CommunicationManager.ConnectResult;
import main.CommunicationManager.DisconnectResult;


public class kernel {
	
	
	private CommunicationManager communicationManager = null;
	
	public kernel()
	{
		communicationManager = new CommunicationManager();
	}

	//////////////////////////////////////////////////////////////////
	/**
	 * Communication methods section
	 */
	public void requestCommandTransmission(String command)
	{
		communicationManager.sendString(command + "\r\n");
	}
	
	public LinkedList<String> requestAvailableCOMs()
	{
		return communicationManager.getComList();
	}
	
	public ConnectResult requestConnect(String COM)
	{		
		CommunicationManager.ConnectResult result = communicationManager.connectToCOM(COM);
		
		return result;
	}
	
	public DisconnectResult requestDisconnect() 
	{
		CommunicationManager.DisconnectResult result = communicationManager.disconnectFromCOM();
		
		return result;
	}
	/////////////////////////////////////////////////////////////////////

}
