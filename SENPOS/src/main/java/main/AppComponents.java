package main;

import uiController.MainController;

public class AppComponents
{
	private static MainController mainCointroller = null;
	private static boolean flagGeneralError = false;
	private static kernel kernel = null;

	public static kernel getKernel()
	{
		return kernel;
	}
	
	public static void setKernel(kernel Kernel) {
		if(kernel == null) {
			kernel = Kernel;
		} else {
			flagGeneralError = true;
		}
	}
	
	public static MainController getMainController()
	{
		return mainCointroller;
	}
	
	public static void setMainController(MainController controller)
	{
		if (mainCointroller == null)
		{
			mainCointroller = controller;
		}
		else
		{
			flagGeneralError = true;
		}
	}
}
