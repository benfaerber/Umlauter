package umlauter.controller;

public class Controller
{
	public RobotController robot;
	public Listener listener;
	
	public Substitution[] subs;
	
	public Controller()
	{
		subs = new Substitution[] {
				new Substitution("o:", "ö"),
				new Substitution("O:", "Ö"),
				new Substitution("a:", "ä"),
				new Substitution("A:", "Ä"),
				new Substitution("u:", "ü"),
				new Substitution("U:", "Ü"),
				new Substitution("ssb", "ß"),
				new Substitution("oee", "ö"),
				new Substitution("OEE", "Ö"),
				new Substitution("aee", "ä"),
				new Substitution("AEE", "Ä"),
				new Substitution("uee", "ü"),
				new Substitution("UEE", "Ü")
		};
	}
	
	public void keyPressed(String key)
	{
		if (!listener.isHoldingShift)
			key = key.toLowerCase();
		
		if (key.equalsIgnoreCase("semicolon"))
			key = ":";

		System.out.println(key);
		for (int i = 0; i < subs.length; i++)
		{
			if (subs[i].progress(key))
			{
				robot.type(subs[i].outputString, subs[i].neededBackspaces());
			}
		}
	}
	
	public void start()
	{
		
	}
}
