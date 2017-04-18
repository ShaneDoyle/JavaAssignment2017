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
	public static String[] BadWords = new String[25000]; //Puts all bad words into an array.
	public static String[] SafeWords = new String[25000]; //Safe words that are ignored by scanner.
	public static String[] SuspectWords = new String [25000]; //Suspicious words are put in here and scanned again.
	public static String[] SuspectWordsEvaluate = new String [25000]; //Suspicious words that will later be evaluated by the user.
	public static int Evaluate = 0; //Tracks suspicious that need evaluation.
	public static String[] PostScan = new String[250];
	public static String[] PostWord = new String[250];
	public static int BadWordCount = 0; //Counts number of bad words in "abuse.txt"
	public static int SafeWordCount = 0; //Counts number of safe words in "safe.txt"
	
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
    	fileExample = new File(fileName);
    	BadWordCount = 0;
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
			System.out.println("Bad Word Count: " + BadWordCount);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("run time error " + e.getMessage());
		}
    }
    
    void readFile2()
    {
    	fileExample = new File(fileName);
    	SafeWordCount = 0;
    	int j = 0;
	    try
		{
	    	myScanner = new Scanner(fileExample); 
			while (myScanner.hasNextLine())
			 {
			      
				 SafeWords[j]  = myScanner.nextLine();
				 SafeWordCount++;
			     //System.out.println(SafeWords[j]);
			     j++;
			 }
			System.out.println("Safe Word Count: " + SafeWordCount);
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
    void AbuseAppend(String input) throws IOException
    {
    	FileWriter fileWriter = null;
    	
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
    
    //Append text to a file.
    void SafeAppend(String input) throws IOException
    {
    	FileWriter fileWriter = null;
    	
	    //Inserts user's word.
    	try
    	{
    		File file = new File("safe.txt");
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
    	boolean check = false; //Ensures a word doesn't get counted twice for suspicious and abusive.
    	int i = 0;
    	int bad = 0;
    	int PostNum = 1;
    	int SafeWord = 0;
    	float AbuseCount = 0; //Counts number of words that program detects as abusive.
    	int SuspectCount = 0; //Counts number of words that are suspicious.
    	float PostWordCount = 0; //Counts number of words in the following post.
	    try
		{
	    	myScanner = new Scanner(fileExample); 
			while (myScanner.hasNextLine())
			 {
				
				PostScan[i]  = myScanner.nextLine();
				//System.out.println(PostScan[i]);
				
				//ArrayString into a string.
				String s = PostScan[i];
				s = s.replace(".", "");
				
				System.out.println(s);
		    
				//Split string into words.
				String[] words = s.split(" ");
				
				for ( String ss : words) 
				{
					PostWordCount++;
				}
				
				//Scanning of words begins.
				for ( String ss : words) 
				{
					//If word matches a word in "safe.txt"
					for (int l = 0; l != SafeWordCount; l++)
					{
						if (ss.toLowerCase().equals(SafeWords[l].toLowerCase()))
						{
							SafeWord++;
							break;
						}
					}
					
					//Begin checking for bad words.
					for (int j = 0; j < BadWordCount; j++ )
					{
						
						if (SafeWord == 1)
						{
							SafeWord--;
							break;
						}
						
						//If word fully matches bad word in "abuse.txt"
						else if(ss.toLowerCase().equals(BadWords[j].toLowerCase()))
				    	{
				    		   AbuseCount++;
				    		   break;
				    	}
						
						//If elements of bad word in "abuse.txt" are in a word (suspicious words).
				    	else if (ss.toLowerCase().contains(BadWords[j].toLowerCase()))
				    	{
				    			for (int l = 0; l < BadWordCount; l++)
				    			{
				    				if (ss.toLowerCase().equals(BadWords[l].toLowerCase()))
				    				{
				    					AbuseCount++;
				    					SuspectCount--;
				    					break;
				    				}
				    			}
				    			SuspectWords[bad] = ss;
				    			SuspectWordsEvaluate[Evaluate] = ss;
				    			Evaluate++;
				    			bad++;
				    			SuspectCount++;
				    			break;
				    	}
						
					}
				}
				
				
					//When blank line is detected.
					float percentage;
					if(PostScan[i].isEmpty())
					{
						PostWordCount -= 1;
						if(PostWordCount != 0 )
						{
							System.out.println("Post " + PostNum + ": " + PostWordCount + " words & " + AbuseCount + " abusive words & " + SuspectCount + " suspicious words" );
							Screen.jta.append("Post " + PostNum + ": " + PostWordCount + " words + " + AbuseCount + " abusive words + " + SuspectCount + " suspicious words \n");
							percentage = (AbuseCount/PostWordCount)*100;
							Screen.jta.append(percentage + "% of the words in this post are potentially offensive\n\n");
							System.out.println(percentage);
							PostWordCount = 0;
							AbuseCount = 0;
							SuspectCount = 0;
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
				    	System.out.println("Post:" + PostNum + " " + PostWordCount + " words & " + AbuseCount + " abusive words & " + SuspectCount + " suspicious words" );
				    	Screen.jta.append("Post " + PostNum + ": " + PostWordCount + " words + " + AbuseCount + " abusive words + " + SuspectCount + " suspicious words \n\n");
						percentage = (AbuseCount/PostWordCount)*100;
						Screen.jta.append(percentage + "% of the words in this post are potentially offensive\n\n");
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