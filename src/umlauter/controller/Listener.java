package umlauter.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class Listener implements NativeKeyListener
{
	public Controller controller;
	public boolean isHoldingShift = false;
	
	private String lastTyped = "";

	public void nativeKeyPressed(NativeKeyEvent e)
	{
		String typed = NativeKeyEvent.getKeyText(e.getKeyCode());
		
		if (typed.equals("Shift"))
		{
			isHoldingShift = true;
		}
		else
		{
			if (!typed.equals(lastTyped))
				controller.keyPressed(typed);
		}
		lastTyped = typed;
	}
	
	public void nativeKeyReleased(NativeKeyEvent e)
	{
		String typed = NativeKeyEvent.getKeyText(e.getKeyCode());
		if (typed.equals("Shift"))
			isHoldingShift = false;
		
		lastTyped = "";
	}
	
	public void nativeKeyTyped(NativeKeyEvent e) { }

	public void start()
	{
		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		GlobalScreen.addNativeKeyListener(this);
		
		// Get the logger for "org.jnativehook" and set the level to warning.
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.WARNING);
		logger.setUseParentHandlers(false);
	}
}