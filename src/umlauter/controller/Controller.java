package umlauter.controller;

public class Controller
{
	public RobotController robot;
	public Listener listener;
	
	public Substitution[] subs;
	
	public Controller()
	{
		subs = new Substitution[] {
				new Substitution("a:", "ä"),
				new Substitution("u:", "ü"),
				new Substitution("o:", "ö"),
				
				new Substitution("A:", "Ä"),
				new Substitution("U:", "Ü"),
				new Substitution("O:", "Ö"),
				
				new Substitution("oee", "ö"),
				new Substitution("aee", "ä"),
				new Substitution("uee", "ü"),
				
				// Only the first letter's case matters
				new Substitution("Aee", "Ä", true),
				new Substitution("Uee", "Ü", true),
				new Substitution("Oee", "Ö", true),
				
				new Substitution("ssb", "ß")
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
