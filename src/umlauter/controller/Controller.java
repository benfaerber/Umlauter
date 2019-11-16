package umlauter.controller;

public class Controller
{
	public RobotController robot;
	public Listener listener;
	
	public Substitution[] subs;
	
	public Controller()
	{
		subs = new Substitution[] {
				new Substitution("a:", "�"),
				new Substitution("u:", "�"),
				new Substitution("o:", "�"),
				
				new Substitution("A:", "�"),
				new Substitution("U:", "�"),
				new Substitution("O:", "�"),
				
				new Substitution("oee", "�"),
				new Substitution("aee", "�"),
				new Substitution("uee", "�"),
				
				// Only the first letter's case matters
				new Substitution("Aee", "�", true),
				new Substitution("Uee", "�", true),
				new Substitution("Oee", "�", true),
				
				new Substitution("ssb", "�")
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
				robot.type(subs[i].outputString, subs[i].neededBackspaces());
		}
	}
	
	public void start()
	{
		
	}
}
