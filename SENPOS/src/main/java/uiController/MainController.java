package uiController;

import java.util.LinkedList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import main.AppComponents;
import main.CommandSetter;
import main.CommunicationManager;
import main.CommunicationManager.ConnectResult;
import main.CommunicationManager.DisconnectResult;

public class MainController {
	   
		@FXML
	    private TextField addressField;

	    @FXML
	    private TextField DataField;

	    @FXML
	    private TextField NameField;

	    @FXML
	    private Button Button_Start;

	    @FXML
	    private Button Button_Stop;

	    @FXML
	    private Button Button_1ns;

	    @FXML
	    private Button Button_1rs;

	    @FXML
	    private Button Button_2ns;

	    @FXML
	    private Button Button_2rs;
	    
	    @FXML
	    private Button Button_3ns;
	    
		@FXML
	    private Button Button_3reset;

	    @FXML
	    private Button Button_4ns;

	    @FXML
	    private Button Button_4rs;

	    @FXML
	    private Button Button_5ns;
	    
	    @FXML
	    private Button Button_5rs;

	    @FXML
	    private ChoiceBox<String> FunctionField;

	    @FXML
	    private ChoiceBox<String> IDField;

	    @FXML
	    private Button Button_Send;

	    @FXML
	    private Button Button_Save;

	    @FXML
	    private Button user1;

	    @FXML
	    private Button user15;

	    @FXML
	    private Button user2;

	    @FXML
	    private Button user3;

	    @FXML
	    private Button user4;

	    @FXML
	    private Button user5;

	    @FXML
	    private Button user6;

	    @FXML
	    private Button user7;

	    @FXML
	    private Button user8;

	    @FXML
	    private Button user9;

	    @FXML
	    private Button user10;

	    @FXML
	    private Button user11;

	    @FXML
	    private Button user12;

	    @FXML
	    private Button user13;

	    @FXML
	    private Button user14;

	    @FXML
	    private Button user16;

	    @FXML
	    private Button user17;

	    @FXML
	    private Button user18;

	    @FXML
	    private Button user19;

	    @FXML
	    private Button user20;

	    @FXML
	    private Button user21;

	    @FXML
	    private Button user22;

	    @FXML
	    private Button user23;

	    @FXML
	    private Button user24;

	    @FXML
	    private Button user25;

	    @FXML
	    private Button user26;

	    @FXML
	    private Button user27;

	    @FXML
	    private Button user28;
	    
	    @FXML
	    private Button Button_Scan;

	    @FXML
	    private Button Button_Con;

	    @FXML
	    private ComboBox<String> Combo_COM;

	    @FXML
	    private ListView<String> ListView_Console;

	    @FXML
	    private Label Status_Left;

	    @FXML
	    private Font x3;

	    @FXML
	    private Color x4;

	    @FXML
	    private Label Status_Right;

	    
	//Variables
	private CommandSetter commandSetter = new CommandSetter();
	
	private String sTempID;
	private String sTempName;
	private String[] sUserCommandArray = new String[30];
	
	boolean connectedToDevice = false;
	private CommunicationManager communicationManager = new CommunicationManager();
	
	//Start process
	public MainController()
	{
		System.out.println("Main controller init");
		AppComponents.setMainController(this);
	}
	
	@FXML
	void initialize()
	{
		appendConsoleSys("Initialization");	
		disableInterface(true);
		
	    FunctionField.getItems().removeAll(FunctionField.getItems());
	    FunctionField.getItems().addAll("03", "07", "10");
	    FunctionField.getSelectionModel().select("03");
	    
	    IDField.getItems().removeAll(IDField.getItems());
	    IDField.getItems().addAll("1", "2", "3", "4", "5", "6", "7");
	    IDField.getSelectionModel().select("1");
		
	//	NameField.setText(commandSetter.getName());
	//	user1.setText(commandSetter.getName());
	    
//	    ListView_Console.Property
	    
	    EventHandler<ActionEvent> handler_Button_Scan = new EventHandler<ActionEvent>() 
	    {
			public void handle(ActionEvent event) 
			{
				String[] ports = SerialPortList.getPortNames();
				
				LinkedList<String> coms = new LinkedList<String>();
				
				for(String s : ports) {
					coms.add(s);
				}		
				
				try {
					for (String s : coms)
					{
						if (Combo_COM.getItems().contains(s) == false)
							Combo_COM.getItems().add(s);
					}
					for (String s : Combo_COM.getItems())
					{
						if (coms.contains(s) == false)
						{
							Combo_COM.getItems().remove(s);
						}
					}
					
				} catch (Exception e) {}
			}
	    };
	    Button_Scan.addEventHandler(ActionEvent.ACTION, handler_Button_Scan);
	    
	    EventHandler<ActionEvent> handler_Button_Con = new EventHandler<ActionEvent>()
	    {
	    	public void handle(ActionEvent event)
	    	{
	    		if (connectedToDevice == false)
	    		{
		    		if (communicationManager.connectToCOM(Combo_COM.getValue()) == ConnectResult.CONNECTED)
		    		{
		    			appendConsoleSys("Connected to device");
		    			Status_Right.setText("Connected");
		    			connectedToDevice = true;
		    			Button_Con.setText("Disconnect");
		    			disableInterface(false);
		    		}
		    		else
		    		{
		    			appendConsoleSys("Connection failed");
		    			communicationManager.disconnectFromCOM();	//TODO doesn't work this way
		    		}
	    		} 
	    		else
	    		{
	    			if (communicationManager.disconnectFromCOM() == DisconnectResult.DISCONNECTED)
	    			{
	    			appendConsoleSys("Disconnected from device");
	    			Status_Right.setText("Disconnected");
	    			connectedToDevice = false;
	    			Button_Con.setText("Connect");
	    			disableInterface(true);
	    			}
	    			else
	    			{
	    				appendConsoleSys("Disconnection failed");
	    			}
    			}
	    	}
		};
		Button_Con.addEventHandler(ActionEvent.ACTION, handler_Button_Con);
		
		EventHandler<ActionEvent> handler_user1 = new EventHandler<ActionEvent>()
		{
			//@Override
			public void handle(ActionEvent event) 
			{
				//Event handler - do not need filing gaps in scene builder
				appendConsoleTx(sUserCommandArray[1]);
				communicationManager.sendString(sUserCommandArray[1]);
			}	
		};
		
		EventHandler<ActionEvent> handler_user2 = new EventHandler<ActionEvent>()
		{
			//@Override
			public void handle(ActionEvent event) 
			{
				//Event handler - do not need filing gaps in scene builder
				appendConsoleTx(sUserCommandArray[2]);
			}	
		};
		
		EventHandler<ActionEvent> handler_user3 = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event) 
			{
				appendConsoleTx(sUserCommandArray[3]);
			}	
		};
		
		user1.addEventHandler(ActionEvent.ACTION, handler_user1);
		user2.addEventHandler(ActionEvent.ACTION, handler_user2);
		user3.addEventHandler(ActionEvent.ACTION, handler_user3);
		
		EventHandler<ActionEvent> handler_buttonSave = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event) 
			{
				saveUserCommand();
				switch (Integer.parseInt(sTempID))
				{
				case 1:
					user1.setText(sTempName);
					setUserCommand(Integer.parseInt(sTempID));
					break;
				case 2:
					user2.setText(sTempName);
					setUserCommand(Integer.parseInt(sTempID));
					break;
				case 3:
					user3.setText(sTempName);
					setUserCommand(Integer.parseInt(sTempID));
					break;
				}
				
				System.out.println("Command Saved");
			}	
		};
		
		Button_Save.addEventHandler(ActionEvent.ACTION, handler_buttonSave);
		
		EventHandler<ActionEvent> handler_buttonSend = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event) 
			{
				System.out.println(commandSetter.saveCommand());
				System.out.println("Command Sent");
			}	
		};
		
		Button_Send.addEventHandler(ActionEvent.ACTION, handler_buttonSend);
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	/**
	 *User command setting methods 
	 */
	@FXML
	public void saveUserCommand() 
	{
		commandSetter.getCommand(addressField.getText(), FunctionField.getValue(), DataField.getText(), NameField.getText(), IDField.getValue());	
		sTempID = commandSetter.saveCommandID();
		sTempName = commandSetter.saveCommandName();
	}
	
	@FXML
	public void setUserName()
	{
		user1.setText(NameField.getText());
		commandSetter.setName(NameField.getText());
	}
	
	public void setUserCommand(Integer iIndex)
	{
		sUserCommandArray[iIndex] = commandSetter.saveCommand();
	}
	////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Console appender
	 * @param sData
	 */
	public void appendConsoleSys(String sData) 
	{		
		ListView_Console.getItems().add("Sys:\t" + sData);
				
		int size = ListView_Console.getItems().size();
		ListView_Console.scrollTo(size - 1);
	}
	
	public void appendConsoleTx(String sData) 
	{		
		ListView_Console.getItems().add("Tx:\t" + sData);
				
		int size = ListView_Console.getItems().size();
		ListView_Console.scrollTo(size - 1);
	}
	
	public void appendConsoleRx(String sData) 
	{		
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				ListView_Console.getItems().add("Rx:\t" + sData);
						
				int size = ListView_Console.getItems().size();
				ListView_Console.scrollTo(size - 1);
			}
		});
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Enables / Disables user interface that is not predefined to indicate com
	 * @param state    true --> Disable  / false  --> Enable
	 */
	public void disableInterface(boolean state)
	{
		Button_Send.setDisable(state);
		Button_Save.setDisable(state);
		user1.setDisable(state);
		user2.setDisable(state);
		user3.setDisable(state);
		user4.setDisable(state);
		user5.setDisable(state);
		user6.setDisable(state);
		user7.setDisable(state);
		user8.setDisable(state);
		user9.setDisable(state);
		user10.setDisable(state);
		user11.setDisable(state);
		user12.setDisable(state);
		user13.setDisable(state);
		user14.setDisable(state);
		user15.setDisable(state);
		user16.setDisable(state);
		user17.setDisable(state);
		user18.setDisable(state);
		user19.setDisable(state);
		user20.setDisable(state);
		user21.setDisable(state);
		user22.setDisable(state);
		user23.setDisable(state);
		user24.setDisable(state);
		user25.setDisable(state);
		user26.setDisable(state);
		user27.setDisable(state);
		user28.setDisable(state);
		Button_1ns.setDisable(state);
		Button_2ns.setDisable(state);
		Button_3ns.setDisable(state);
		Button_4ns.setDisable(state);
		Button_5ns.setDisable(state);
		Button_1rs.setDisable(state);
		Button_2rs.setDisable(state);
		Button_3reset.setDisable(state);
		Button_4rs.setDisable(state);
		Button_5rs.setDisable(state);
		Button_Start.setDisable(state);
		Button_Stop.setDisable(state);
	}
	
}


//user3.addEventHandler(ActionEvent.ACTION, handler_user3);
//user4.addEventHandler(ActionEvent.ACTION, handler_user3);
//user5.addEventHandler(ActionEvent.ACTION, handler_user3);
//user6.addEventHandler(ActionEvent.ACTION, handler_user3);
//user7.addEventHandler(ActionEvent.ACTION, handler_user3);
//user8.addEventHandler(ActionEvent.ACTION, handler_user3);
//user9.addEventHandler(ActionEvent.ACTION, handler_user3);
//user10.addEventHandler(ActionEvent.ACTION, handler_user3);
//user11.addEventHandler(ActionEvent.ACTION, handler_user3);
//user12.addEventHandler(ActionEvent.ACTION, handler_user3);
//user13.addEventHandler(ActionEvent.ACTION, handler_user3);
//user14.addEventHandler(ActionEvent.ACTION, handler_user3);
//user15.addEventHandler(ActionEvent.ACTION, handler_user3);
//user16.addEventHandler(ActionEvent.ACTION, handler_user3);
//user17.addEventHandler(ActionEvent.ACTION, handler_user3);
//user18.addEventHandler(ActionEvent.ACTION, handler_user3);