package umlauter.controller;

public class Substitution
{
	public String inputString;
	public String outputString;
	
	// CaseModes:
	// 0: Case Matters
	// 1: First letter matters
	// 2: Case doesn't matter
	private int caseMode;
	private int progressCounter = 0;

	public Substitution(String inputString, String outputString, int caseMode)
	{
		this.inputString = inputString;
		this.outputString = outputString;
		this.caseMode = caseMode;
	}
	
	public Substitution(String settingsData)
	{
		String[] parts = settingsData.split(",");
		String[] equation = parts[0].split("=");
		this.inputString = equation[0];
		this.outputString = equation[1];
		this.caseMode = Integer.parseInt(parts[1]);
	}

	public String toString()
	{
		return "in:" + inputString + "\nout:" + outputString + "\ncase mode:" + caseMode;
	}

	public int neededBackspaces()
	{
		return inputString.length();
	}
	
	public boolean progress(String key)
	{		
		String neededCharacter = inputString.substring(progressCounter, progressCounter+1);
		
		if (caseMode == 1 && progressCounter != 0)
			key = key.toLowerCase();
		
		if (caseMode == 2)
		{
			key = key.toLowerCase();
			neededCharacter = neededCharacter.toLowerCase();
		}
		
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
