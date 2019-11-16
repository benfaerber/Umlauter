package umlauter.controller;

import java.awt.AWTException; 
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent; 
  
public class RobotController 
{ 
	public Listener listener;

	private Robot robot;
	private Clipboard clipboard;
	
	public RobotController() throws AWTException
	{
		robot = new Robot();
		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	}
	
    public void type(String toType, int backspaces)
    {
    	for (int i = 0; i < backspaces; i++)
    	{
    		robot.keyPress(KeyEvent.VK_BACK_SPACE);
    		robot.keyRelease(KeyEvent.VK_BACK_SPACE);
    	}
    		
    	// Adds chosen string to clipboard
    	StringSelection stringSelection = new StringSelection(toType);
    	clipboard.setContents(stringSelection, stringSelection);
    	
    	boolean hasCaps = toType.contains(":");
    	if (!hasCaps)
    		hasCaps = !toType.equals(toType.toLowerCase());
    	
    	if (listener.isHoldingShift)
    		hasCaps = true;
    		
    	try
		{
    		if (hasCaps)
    			Thread.sleep(1000);
    		else
    			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

    	paste();
    }
    
    public void paste()
    {
    	robot.keyPress(KeyEvent.VK_CONTROL);
    	robot.keyPress(KeyEvent.VK_V);
    	robot.keyRelease(KeyEvent.VK_V);
    	robot.keyRelease(KeyEvent.VK_CONTROL);
    }
}