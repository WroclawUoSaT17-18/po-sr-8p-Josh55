package main;

public class CommandSetter 
{
	private String sName = "user1";
	private String sAddress = "00";
	private String sFunction = "03";
	private String sData = "0000";
	private String iID = "1";
	
	private String sCommand;
	private String sCRC = "00";
	
	public String saveCommandName() 
	{
		return sName;
	}
	
	public String saveCommandID()
	{
		return iID;
	}
	
	public String saveCommand()
	{
		sCommand = sAddress + sFunction + sData + sCRC;
		return sCommand;
	}
	
	public void getCommand(String sAddress, String sFunction, String sData, String sName, String iID) 
	{
		this.sName = sName;
		this.sAddress = sAddress;
		this.sFunction = sFunction;
		this.sData = sData;
		this.iID = iID;
	}

	public String getName()
	{
		return sName;
	}
	
	public void setName(String sName)
	{
		this.sName = sName;
	}

}
