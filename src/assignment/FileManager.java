package assignment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException;
import java.util.Arrays;

public class FileManager 
{
	String fileName;
	File fileExample;
	Scanner myScanner;
	Scanner myScanner2;
    PrintWriter pwInput;
    
    //Variables for "Screen.java"
    public static int lines; 						  //Counts amount of lines.
	public static String[] BadWords = new String[25]; //Puts all bad words into an array.
	public static String[] PostScan = new String[250];
	public static String[] PostWord = new String[250];
	public static int BadWordCount; //Counts number of bad words in "abuse.txt"
	
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
    void readFile()
    {
    	int j = 0;
	    try
		{
	    	myScanner = new Scanner(fileExample); 
			while (myScanner.hasNextLine())
			 {
			      
				 BadWords[j]  = myScanner.nextLine();
				 BadWordCount++;
			     //System.out.println(BadWords[j]);
			     j++;
			     lines++;
			 }
			System.out.println(BadWordCount);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("run time error " + e.getMessage());
		}
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
    
    //Scan Posts
    void scanPost()
    {
    	String test;
    	int i = 0;
    	int PostNum = 1;
    	int AbuseCount = 0; //Counts number of words that could are abusive.
    	int PostWordCount = 0; //Counts number of words in the following post.
	    try
		{
	    	myScanner = new Scanner(fileExample); 
			while (myScanner.hasNextLine())
			 {
				PostScan[i]  = myScanner.nextLine();
				//System.out.println(PostScan[i]);
				
				//ArrayString into a string.
				String s = PostScan[i];
				
				//Split string into words.
				String[] words = s.split(" ");
				
				for ( String ss : words) 
				{
					PostWordCount++;
				}
				
				for ( String ss : words) 
				{
					for (int j = 0; j < BadWordCount; j++ )
					{
						if (ss.toLowerCase().indexOf(BadWords[j].toLowerCase()) != -1 )  //Input matches text file.
				    	{
							AbuseCount++;
				    		j = BadWordCount;
				    		   
				    	} 
				    	else
				    	{
				    		   //Do nothing if word isn't abusive.
				    	}
					}
				}
				
					//When blank line is detected.
					if(PostScan[i].isEmpty())
					{
						PostWordCount -= 1;
						//If no words are detected, 
						if(PostWordCount != 0 )
						{
							System.out.println("Post:" + PostNum + " " + PostWordCount + " words & " + AbuseCount + " abusive words");
							PostWordCount = 0;
							AbuseCount = 0;
							PostNum++;
						}
						else
						{
							
						}
				    	
					}
					
					//If no next line is detected (EOF)
					if(!myScanner.hasNextLine())
					{
						//System.out.println("EOF");
				    	System.out.println("Post:" + PostNum + " " + PostWordCount + " words & " + AbuseCount + " abusive words");
						PostNum++;
					}
				
				
			 }


					
		}
		catch (FileNotFoundException e)
		{
			System.out.println("run time error " + e.getMessage());
		}
	    finally
	    {
	    	

	    }
    }
}