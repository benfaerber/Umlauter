package umlauter.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileLoader
{
	public ArrayList<String> read(String filePath)
	{
	    try(BufferedReader br = new BufferedReader(new FileReader(currentDirectory() + filePath)))
	    {
	        ArrayList<String> lines = new ArrayList<String>();
	        String line = null;
	        while ((line = br.readLine()) != null) 
	            lines.add(line);
	        return lines;
	    } catch (IOException e) { }
	    return null;
	}
	
	public String currentDirectory()
	{
		return System.getProperty("user.dir") + "\\";
	}
}
