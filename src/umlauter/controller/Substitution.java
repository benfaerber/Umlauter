package umlauter.controller;

public class Substitution
{
	public String inputString;
	public String outputString;
	
	private boolean onlyFirstCaseMatters;
	private int progressCounter = 0;

	
	public Substitution(String inputString, String outputString)
	{
		this.inputString = inputString;
		this.outputString = outputString;
		this.onlyFirstCaseMatters = false;
	}
	
	public Substitution(String inputString, String outputString, boolean onlyFirstCaseMatters)
	{
		this.inputString = inputString;
		this.outputString = outputString;
		this.onlyFirstCaseMatters = onlyFirstCaseMatters;
	}
	
	public int neededBackspaces()
	{
		return inputString.length();
	}
	
	public boolean progress(String key)
	{		
		String neededCharacter = inputString.substring(progressCounter, progressCounter+1);
		
		if (onlyFirstCaseMatters && progressCounter != 0)
			key = key.toLowerCase();
		
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
