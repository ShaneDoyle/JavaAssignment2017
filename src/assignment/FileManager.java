package assignment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException;

public class FileManager 
{
	String fileName;
	File fileExample;
	Scanner myScanner;
    PrintWriter pwInput;
    
    //Variables for "Screen.java"
    public static int lines; 						  //Counts amount of lines.
	public static String[] BadWords = new String[25]; //Puts all bad words into an array.


	// Constructor
	FileManager (String fileName)
	{
		
		this.fileName = fileName;
		
	}
	
	//Get a connection to the file
	void connectToFile()
	{
		fileExample = new File(fileName);
	}

	// Read the file, returning a string of lines
	int j = 0;
    void readFile()
    {
	    try
		{
	    	myScanner = new Scanner(fileExample); 
			while (myScanner.hasNextLine())
			 {
			      
				 BadWords[j]  = myScanner.nextLine();
			     //System.out.println(BadWords[j]);
			     j++;
			     lines++;
			    }
		}
		catch (FileNotFoundException e)
		{
			System.out.println("run time error " + e.getMessage());
		}
	    /*finally
	    {
	        return values;
	    }
	    */
    }
    
	//Get hold of a Print writer object
    void getFileWriter()
    {
    	try
    	{
    		pwInput = new PrintWriter(fileExample);
    	}
  		catch (FileNotFoundException e)
  		{
  			System.out.println("run time error " + e.getMessage());
  		}
    	
    }	

	//Write a string to the file
    void writeLineToFile(String line)
    {
       System.out.println(line);
       pwInput.println(line);
    }	
    
    void closeReadFile()
    {
		 myScanner.close();
    }

    void closeWriteFile()
    {
		 pwInput.close();
    }
   
    //Append text to a file.
    void append(String input) throws IOException
    {
    	FileWriter fileWriter = null;
    	
    	//Check if word already exists within text file.
		String search = FileManager.BadWords[0];
	    for(int j=0; j<FileManager.lines+1; j++)
	    {
	    	if (input.toLowerCase().indexOf(search.toLowerCase()) != -1 ) 
	    	{
	    		Screen.label4.setText("Word already exists!");
	    		return;
	    	} 
	    	else 
	    	{

	    	}
	    }
		
		//Ensures only character input.
		while(!input.matches("[a-zA-Z]+"))
		{
		    Screen.label4.setText("Invalid syntax. Only characters please.");
		    return;
		}
		
	    //Inserts user's word.
    	try
    	{
    		File file = new File("abuse.txt");
    		fileWriter = new FileWriter(file, true);
    		fileWriter.write("\n");
    		fileWriter.write(input);
    		Screen.label4.setText("Word inserted!");
    	}
		catch (FileNotFoundException e)
		{
			System.out.println("run time error " + e.getMessage());
		}
    	finally
    	{
    		if (fileWriter != null)
    		{
    			fileWriter.close();
    		}
    	}
    }
}