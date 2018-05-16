package main;

import java.util.LinkedList;


public class Dispatcher 
{
	public class Subscription
	{
		public int slaveAddress;
		
		private LinkedList<CommandListener> listeners;
		
		public Subscription(int adr)
		{
			slaveAddress = adr;
			listeners = new LinkedList<CommandListener>();
		}
		
		public LinkedList<CommandListener> getListeners()
		{
			return listeners;
		}
		
		public void addListener(CommandListener newListener)
		{
			listeners.add(newListener);
		}	
	}
	
	public void notify(String frame)
	{	
		int address = Integer.valueOf(frame.substring(0, 2));	// cut only address from frame
		
		Subscription sn = getSubscription(address);
		
		for(CommandListener cListener : sn.getListeners())
		{
			cListener.inform(frame);
		}
	}

	private LinkedList<Subscription> subscriptions;

	public Dispatcher() 
	{
		subscriptions = new LinkedList<Subscription>();
	}
	
	
	public void subscribe(int slave_address, CommandListener listener) 
	{
		Subscription sub = getSubscription(slave_address);
		
		if(sub == null) {
			sub = new Subscription(slave_address);
			subscriptions.add(sub);
			sub.addListener(listener);
		} else {
			sub.addListener(listener);
		}
	}

	private Subscription getSubscription(int adr)
	{
		if(subscriptions.size() > 0)
		{
			for(Subscription sub : subscriptions)
			{
				if (sub.slaveAddress == adr)
				{
					return sub;
				}				
			}
		}
		return null;
	}
}


