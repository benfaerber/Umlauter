package umlauter.controller;

import java.awt.AWTException;

public class Runner
{
	public static void main(String[] args) throws AWTException
	{
		Listener listener = new Listener();
		Controller controller = new Controller();
		RobotController robot = new RobotController();
		
		robot.listener = listener;
		listener.controller = controller;
		controller.listener = listener;
		controller.robot = robot;

		listener.start();
		controller.start();
	}
}