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
    	backspace(backspaces);
    	
    	// Adds chosen string to clipboard
    	StringSelection stringSelection = new StringSelection(toType);
    	clipboard.setContents(stringSelection, stringSelection);
    	
    	// Figures out if caps were ever used
    	boolean hasCaps = toType.contains(":");
    	if (!hasCaps)
    		hasCaps = !toType.equals(toType.toLowerCase());
    	
    	if (listener.isHoldingShift)
    		hasCaps = true;
    		
    	try
		{
    		// For some reason, if the shift key is used it takes longer to copy to the clipboard
    		Thread.sleep(hasCaps ? 1000 : 50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

    	paste();
    }
    
    public void backspace(int count)
    {
    	for (int i = 0; i < count; i++)
    	{
    		robot.keyPress(KeyEvent.VK_BACK_SPACE);
    		robot.keyRelease(KeyEvent.VK_BACK_SPACE);
    	}
    }
    
    public void paste()
    {
    	robot.keyPress(KeyEvent.VK_CONTROL);
    	robot.keyPress(KeyEvent.VK_V);
    	robot.keyRelease(KeyEvent.VK_V);
    	robot.keyRelease(KeyEvent.VK_CONTROL);
    }
}