package assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileManager 
{
	String fileName;
	File fileExample;
	Scanner myScanner;
    PrintWriter pwInput;
    
	public static String[] BadWords = new String[10];


	// Constructor
	FileManager (String fileName)
	{
		
		this.fileName = fileName;
		
	}
	
	// get a connection to the file
	void connectToFile()
	{
		fileExample = new File(fileName);
	}

	int j = 0;
	// Read the file, returning a string of lines
    void readFile()
    {
	    try
		{
	    	myScanner = new Scanner(fileExample); 
			while (myScanner.hasNextLine())
			 {
			      
				 BadWords[j]  = myScanner.nextLine();
			     System.out.println(BadWords[j]);
			     j++;
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
    
	// get hold of a Print writer object
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

	// wtite a string to the file
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

}