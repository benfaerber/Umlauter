package umlauter.controller;

public class Controller
{
	public RobotController robot;
	public Listener listener;
	
	public Substitution[] subs;
	
	public Controller()
	{
		subs = new Substitution[] {
				new Substitution("o:", "�"),
				new Substitution("O:", "�"),
				new Substitution("a:", "�"),
				new Substitution("A:", "�"),
				new Substitution("u:", "�"),
				new Substitution("U:", "�"),
				new Substitution("ssb", "�"),
				new Substitution("oee", "�"),
				new Substitution("OEE", "�"),
				new Substitution("aee", "�"),
				new Substitution("AEE", "�"),
				new Substitution("uee", "�"),
				new Substitution("UEE", "�")
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
