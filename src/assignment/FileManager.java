package assignment;

import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

import com.sun.org.apache.xpath.internal.operations.Equals;
import com.sun.prism.paint.Color;

import java.io.IOException;
import java.util.Arrays;

public class FileManager 
{
	String fileName;
	File fileExample;
	Scanner myScanner;
	Scanner duplicate;
    PrintWriter pwInput;
    
    //Variables for "Screen.java"
    public static boolean insertion = false;
    
	public static String[] BadWords = new String[25000]; //Puts all bad words into an array.
	public static String[] SafeWords = new String[25000]; //Safe words that are ignored by scanner.
	public static String[] SuspectWords = new String [25000]; //Suspicious words are put in here and scanned again.
	public static String[] SuspectWordsEvaluate = new String [25000]; //Suspicious words that will later be evaluated by the user.
	public static int Evaluate = 0; //Tracks suspicious that need evaluation.
	int Duplicate = 0; //Duplicate suspicious words.
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
			while (myScanner.hasNext())
			 {
					BadWords[j]  = myScanner.next();
					BadWordCount++;
			     //System.out.println(BadWords[j]);
			     j++;
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
    
	//Recreate "abuse.txt" for removing bad word.
    void AbuseRemove(String input) throws IOException
    {
    	FileWriter fileWriter = null;
		File file = new File("abuse.txt");
    	
		for(int j = 0 ; j != BadWordCount; j++)
    	{
    		if (!input.toLowerCase().equals(BadWords[j].toLowerCase()))
    		{
            	try
            	{
            		fileWriter = new FileWriter(file, true);
            		fileWriter.write(BadWords[j]);
            		if(j != BadWordCount-1)
            		{
                		fileWriter.write("\n");
            		}
            	}
            	
        		catch (FileNotFoundException e)
        		{
        			System.out.println("run time error " + e.getMessage());
        		}
            	
            	finally
            	{
            		fileWriter.close();
            	}
    		}
    		else
    		{
        		insertion = true;
    		}

    		
    	}
    	
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
    	boolean insert = false;
    	int lastline = 0;
    	
    	
    	for(int j = 0 ; j != BadWordCount; j++)
    	{
    		if (input.toLowerCase().equals(BadWords[j].toLowerCase()))
    		{
	    		Screen.label4.setText("Word already exists!");
	    		insert = true;
	    		lastline = j;
    		}
    	}
    	
    	if (insert == false)
    	{
        	try
        	{
        		File file = new File("abuse.txt");
        		fileWriter = new FileWriter(file, true);
        		fileWriter.write("\n");
        		fileWriter.write(input);
        		insertion = true;
        	}
        	
    		catch (FileNotFoundException e)
    		{
    			System.out.println("run time error " + e.getMessage());
    		}
        	
        	finally
        	{
        		if (fileWriter != null)
        		{
        			//Close filewriter.
        			fileWriter.close();
        		}
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
    		fileWriter.write(input);
    		fileWriter.write("\n");
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
    	int PostNum = 0;
    	int SafeWord = 0;
    	float AbuseCount = 0; //Counts number of words that program detects as abusive.
    	float SuspectCount = 0; //Counts number of words that are suspicious.
    	float PostWordCount = 0; //Counts number of words in the following post.
	    try
		{
	    	myScanner = new Scanner(fileExample); 
	    	duplicate = new Scanner(fileExample); 
			while (myScanner.hasNextLine())
			 {
				
				PostScan[i]  = myScanner.nextLine();
				//System.out.println(PostScan[i]);
				
				//ArrayString into a string.
				String s = PostScan[i];
				
				//Strip away special characters.
				s = s.replaceAll("[^a-zA-Z0-9 ]+","");
				//s = s.replace("!", "");
				//s = s.replace("?", "");
				//s = s.replace("[", "");
				//s = s.replace("]", "");
				//s = s.replace("!", "");
				
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
					for (int j = 0; j != BadWordCount; j++ )
					{
						
						if (SafeWord == 1)
						{
							SafeWord--;
							break;
						}
						
						//If word fully matches bad word in "abuse.txt"
						if(ss.toLowerCase().equals(BadWords[j].toLowerCase()))
				    	{
				    		   AbuseCount++;
				    		   j = BadWordCount;
				    		   check = true;
				    		   break;
				    	}
						
						//If elements of bad word in "abuse.txt" are in a word (suspicious words).
						if (ss.toLowerCase().contains(BadWords[j].toLowerCase()))
				    	{
							
							//Scans for duplicate suspicious words.
							for (int k = 0; k != Evaluate; k++)
							{
								if(ss.toLowerCase().equals(SuspectWordsEvaluate[k].toLowerCase()))
								{
									SuspectWordsEvaluate[k] = "";
									Duplicate++;
									break;
								}
								else
								{
									
								}
							}
							
					    		SuspectWords[bad] = ss;
					    		SuspectWordsEvaluate[Evaluate] = ss;
					    		Evaluate++;
					    		bad++;
					    		SuspectCount++;
					    		System.out.println(Evaluate);

					    		for (int l = 0; l < BadWordCount; l++)
					    		{
					    			if (ss.toLowerCase().equals(BadWords[l].toLowerCase()))
					    			{
					    				System.out.println(ss);
					    				SuspectCount--;
					    				Evaluate--;
					    				bad--;
					    				l = BadWordCount;
							    		SuspectWords[bad] = "";
							    		SuspectWordsEvaluate[Evaluate] = "";
							    		System.out.println(Evaluate);
					    			}
					    		}
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
							PostNum++;
							results(PostNum, PostWordCount, AbuseCount, SuspectCount);
							PostWordCount = 0;
							AbuseCount = 0;
							SuspectCount = 0;
						}
					}
					
					//If no next line is detected (EOF)
					if(!myScanner.hasNextLine())
					{
						if(PostWordCount != 0 )
						{
							PostNum++;
							results(PostNum, PostWordCount, AbuseCount, SuspectCount);
							PostWordCount = 0;
							AbuseCount = 0;
							SuspectCount = 0;
						}
					}			
			 }			
		}
		catch (FileNotFoundException e)
		{
			System.out.println("run time error " + e.getMessage());
		}
	    finally
	    {
	    	//Do nothing
	    }
    }
    
    void results(int PostNum, float PostWordCount, float AbuseCount, float SuspectCount)
    {
    	//Calculate % of abusive content.
		float results = (AbuseCount * 200 / PostWordCount );
		if (results > 100)
		{
			results = 100;
		}
		
		//Used to display numbers to 2 decimal places.
		DecimalFormat df = new DecimalFormat("#.00");
    	if (Screen.simplecheck == false && Screen.childcheck == false)
    	{
    		
    		System.out.println("Post:" + PostNum + " " + PostWordCount + " words & " + AbuseCount + " abusive words & " + SuspectCount + " suspicious words" );
    		Screen.jta.append("Post " + PostNum + ":\n" + (int)PostWordCount + " words + " + (int)AbuseCount + " abusive words + " + (int)SuspectCount + " suspicious words \n");
    		
    		
    		//Display results percentages.
    		if(results == 0)
    		{
    			Screen.jta.append("Results: 0% offensive.\n");
    		}
    		else
    		{
    			Screen.jta.append("Results: " + (df.format(results)) + "% offensive.\n");
    		}
    	
    		//Tell user if post is abusive.
    		if (results == 0)
    		{
    			Screen.jta.append("No abuse detected, this sentence is not abusive!\n\n");
    		}
    		else if (results < 7.5)
    		{
    			Screen.jta.append("Traces amount of abusive words detected. This post\nis probably not abusive!\n\n");
    		}
    		else if (results < 15.0)
    		{
    			Screen.jta.append("Small of abusive content have been found. This post \n has a low chance of being abusive!\n\n");
    		}
    		else if (results < 25.0)
    		{
    			Screen.jta.append("Abusive content has been found. This post \n is probably abusive!\n\n");
    		}
    		else if (results < 50)
    		{
    			Screen.jta.append("Much abusive content has been found. This post \n is abusive!\n\n");
    		}
    		else
    		{
    			Screen.jta.append("A lot of abusive content has been found. This post \n is very abusive!\n\n");
    		}
    	}
    	
    	
    	//Child option.
    	else if (Screen.childcheck == true)
    	{
    		Screen.jta.append("Post " + PostNum + ": ");
    		if (results != 0)
    		{
    			Screen.jta.append("Abusive content detected. Please check this post.");
    		}
    		else
    		{
    			Screen.jta.append("No abuse detected.");
    		}
    		Screen.jta.append("\n");
    	}
    	
    	
    	else
    	{
    		Screen.jta.append("Post " + PostNum + ": "); 
    		//Tell user if post is abusive.
    		if (results == 0)
    		{
    			Screen.jta.append("No abuse.");
    		}
    		else if (results < 7.5)
    		{
    			Screen.jta.append("Traces of abuse.");
    		}
    		else if (results < 15.0)
    		{
    			Screen.jta.append("Small amount of abuse.");
    		}
    		else if (results < 25.0)
    		{
    			Screen.jta.append("Probably abusive.");
    		}
    		else if (results < 50)
    		{
    			Screen.jta.append("Abusive.");
    		}
    		else
    		{
    			Screen.jta.append("Very Aabusive");
    		}
    		if (results == 0)
    		{
    			Screen.jta.append(" (0% abusive)\n");
    		}
    		else
    		{
        		Screen.jta.append(" (" + (df.format(results)) + "% abusive)" + "\n");	
    		}
    	}
    	//If suspicious words exist, prompt user to verify them.
    	if (Evaluate > 0)
    	{
    		int Display = Evaluate - Duplicate;
        	Screen.jta2.setText(Display + " word(s) need to be verified.\nPlease verify these and rescan the posts!\n");
    	}
    }
}