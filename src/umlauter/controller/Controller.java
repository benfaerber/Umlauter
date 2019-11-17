package umlauter.controller;

import java.util.ArrayList;

public class Controller
{
	public RobotController robot;
	public Listener listener;
	
	public ArrayList<Substitution> subs = new ArrayList<Substitution>();

	public void keyPressed(String key)
	{
		if (!listener.isHoldingShift)
			key = key.toLowerCase();
		
		// Lol fix this later
		if (key.equalsIgnoreCase("semicolon"))
			key = ":";
		if (key.equalsIgnoreCase("quote"))
			key = "'";
		if (key.equalsIgnoreCase("back quote"))
			key = "`";
		if (key.equalsIgnoreCase("slash"))
			key = "/";
		if (key.equalsIgnoreCase("period"))
			key = ".";

		System.out.println(key);
		for (int i = 0; i < subs.size(); i++)
		{
			if (subs.get(i).progress(key))
				robot.type(subs.get(i).outputString, subs.get(i).neededBackspaces());	
		}
	}
	
	public void start()
	{
		FileLoader fl = new FileLoader();
		ArrayList<String> settings = fl.read("settings.txt");
		if (settings == null)
			settings = fl.read("builds/settings.txt");
		
		for (String s : settings)
		{
			if (s.length() > 0)
			{
				if (!s.substring(0, 1).equals("#") && s.contains("="))
				{
					subs.add(new Substitution(s));
				}
			}
		}
	}
}
