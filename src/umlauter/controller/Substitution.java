package umlauter.controller;

public class Substitution
{
	public String inputString;
	public String outputString;
		
	private int progressCounter = 0;

	
	public Substitution(String inputString, String outputString)
	{
		this.inputString = inputString;
		this.outputString = outputString;
	}
	
	public int neededBackspaces()
	{
		String copy = inputString;
		return copy.length();
	}
	
	public boolean progress(String key)
	{		
		String neededCharacter = inputString.substring(progressCounter, progressCounter+1);
		
		if (neededCharacter.equals(key))
			progressCounter++;
		else
			progressCounter = 0;
		
		if (progressCounter > inputString.length()-1)
		{
			progressCounter = 0;
			return true;
		}
		else
		{
			return false;
		}
	}
}
