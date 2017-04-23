package assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.IOException;

public class FileManager 
{
	String fileName;
	File fileExample;
	Scanner myScanner;
    PrintWriter pwInput;
    
    //Variables for "Screen.java"
    public static boolean insertion = false;
    
	public static String[] BadWords = new String[25000]; //Puts all bad words into an array.
	public static String[] DisplayBadWords = new String[25000]; //Puts detected bad words to show user later.
	public static String[] SafeWords = new String[25000]; //Safe words that are ignored by scanner.
	public static String[] SuspectWords = new String [25000]; //Suspicious words are put in here and scanned again.
	public static String[] SuspectWordsEvaluate = new String [25000]; //Suspicious words that will later be evaluated by the user.
	public static int Evaluate = 0; //Tracks suspicious that need evaluation.
	public static String[] PostScan = new String[250];
	public static String[] PostWord = new String[250];
	public static int BadWordCount = 0; //Counts number of bad words in "abuse.txt"
	public static int SafeWordCount = 0; //Counts number of safe words in "safe.txt"
	int Duplicate = 0; //Duplicate suspicious words.
	int DisplayBad = 0; //Used to put words into displayed bad words.
	
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

	//Read the "abuse.txt" file, returning a string of lines
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
				j++;
			 }
		}
	    
		catch (FileNotFoundException e)
		{
			System.out.println("run time error " + e.getMessage());
		}
    }
    
	//Read the "safe.txt" file, returning a string of lines
    void readFile2()
    {
    	fileExample = new File(fileName);
    	SafeWordCount = 0;
    	int j = 0;
	    try
		{
	    	myScanner = new Scanner(fileExample); 
			while (myScanner.hasNext())
			 {
				 SafeWords[j]  = myScanner.next();
				 SafeWordCount++;
			     j++;
			 }
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Run Time Error " + e.getMessage());
			
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

	//Write a string to the file.
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
        			System.out.println("Run Time Error " + e.getMessage());
        		}
            	
            	finally
            	{
            		fileWriter.close();
            	}
    		}
    		else
    		{
        		insertion = true; //Removal was successful.
    		}
    	}
    }	
    
    
    //Recreate "safe.txt" for removing bad word.
    void SafeRemove(String input) throws IOException
    {
    	FileWriter fileWriter = null;
		File file = new File("safe.txt");
		
		
		for(int j = 0 ; j != SafeWordCount; j++)
    	{
    		if (!input.toLowerCase().equals(SafeWords[j].toLowerCase()))
    		{
            	try
            	{
            		fileWriter = new FileWriter(file, true);
            		fileWriter.write(SafeWords[j]);
            		if(j != SafeWordCount-1)
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
        		insertion = true; //Removal was successful.
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
    
    //Append bad words to "abuse.txt"
    void AbuseAppend(String input) throws IOException
    {
    	FileWriter fileWriter = null;
    	boolean allowinsert = false;
    	
		//Check that safe word is not in "abuse.txt".
		for(int j = 0 ; j != SafeWordCount; j++)
    	{
    		if (input.toLowerCase().equals(SafeWords[j].toLowerCase()))
    		{
    			Screen.label4.setText("Word exists as a safe word!");
    			return;
    		}
    	}
    	
    	for(int j = 0 ; j != BadWordCount; j++)
    	{
    		if (input.toLowerCase().equals(BadWords[j].toLowerCase()))
    		{
	    		allowinsert = true;
	    		Screen.label4.setText("Word already exists!");
    		}
    	}
    	
    	if (allowinsert == false)
    	{
        	try
        	{
        		File file = new File("abuse.txt");
        		fileWriter = new FileWriter(file, true);
        		fileWriter.write("\n");
        		fileWriter.write(input);
        		insertion = true; //Insertion was successful.
        	}
        	
    		catch (FileNotFoundException e)
    		{
    			System.out.println("Run time error " + e.getMessage());
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

    
    //Append safe words to "safe.txt"
    void SafeAppend2(String input) throws IOException
    {
    	FileWriter fileWriter = null;
    	boolean allowinsert = false;
    	
		//Check that safe word is not in "abuse.txt".
		for(int j = 0 ; j != BadWordCount; j++)
    	{
    		if (input.toLowerCase().equals(BadWords[j].toLowerCase()))
    		{
    			Screen.label5.setText("Word exists as a bad word!");
    			return;
    		}
    	}
    	
    	
    	for(int j = 0 ; j != SafeWordCount; j++)
    	{
    		if (input.toLowerCase().equals(SafeWords[j].toLowerCase()))
    		{
	    		allowinsert = true;
	    		Screen.label5.setText("Word already exists!");
    		}
    	}
    	
    	if (allowinsert == false)
    	{
        	try
        	{
        		File file = new File("safe.txt");
        		fileWriter = new FileWriter(file, true);
        		fileWriter.write("\n");
        		fileWriter.write(input);
        		insertion = true; //Insertion was successful.
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

    //Scan Posts
    void scanPost()
    {
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
			while (myScanner.hasNextLine())
			 {
				//Put each line into "PostScan"
				PostScan[i]  = myScanner.nextLine();
				
				//ArrayString into a string.
				String s = PostScan[i];
				
				//Strip away special characters.
				s = s.replaceAll("[^a-zA-Z0-9 ]+","");
				    
				//Split string into words.
				String[] words = s.split(" ");
				
				for ( @SuppressWarnings("unused") String ss : words) 
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
							DisplayBadWords[DisplayBad] = ss;
							DisplayBad++;
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
							}
							
							SuspectWords[bad] = ss;
							SuspectWordsEvaluate[Evaluate] = ss;
							Evaluate++;
							bad++;
							SuspectCount++;

							for (int l = 0; l < BadWordCount; l++)
							{
								if (ss.toLowerCase().equals(BadWords[l].toLowerCase()))
								{
									SuspectCount--;
									Evaluate--;
									bad--;
									l = BadWordCount;
									SuspectWords[bad] = "";
									SuspectWordsEvaluate[Evaluate] = "";
								}
							}
				    	}
					}
				}

				//When blank line is detected.
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
    }
    
    //Display results of the previous scan.
    void results(int PostNum, float PostWordCount, float AbuseCount, float SuspectCount)
    {
    	//Calculate % of abusive content.
		float results = (AbuseCount * 150 / PostWordCount );
		if (results > 100)
		{
			results = 100;
		}
		//Used to display numbers to 2 decimal places.
		DecimalFormat df = new DecimalFormat("#.00");
		
		//Normal Scan.
    	if (Screen.simplecheck == false && Screen.childcheck == false)
    	{
    		
    		Screen.jta.append("Post " + PostNum + ":\nWords: " + (int)PostWordCount + " (Abusive: " + (int)AbuseCount + ") (Suspicious: " + (int)SuspectCount + ")\n");
    		
    		if (DisplayBad != 0)
    		{
        		Screen.jta.append("Abusive Words in this Post:\n");
        		for (int j = 0; j != DisplayBad; j++)
        		{
            		Screen.jta.append("- - - " + DisplayBadWords[j] + "\n");
        		}
        		
        		Screen.jta.append("");
				DisplayBad = 0;
    		}
    		
    		//Display results.
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
    			Screen.jta.append("No abuse detected. This sentence is not abusive!\n\n");
    		}
    		else if (results < 7.5)
    		{
    			Screen.jta.append("Tiny amounts of abusive words detected. This post\nis probably not abusive!\n\n");
    		}
    		else if (results < 15.0)
    		{
    			Screen.jta.append("Small amount of abusive content have been detected. This post \nhas a low chance of being abusive!\n\n");
    		}
    		else if (results < 30.0)
    		{
    			Screen.jta.append("Medium amount of abusive content has been detected. This post \nis probably abusive!\n\n");
    		}
    		else if (results < 50)
    		{
    			Screen.jta.append("High amount of abusive content has been detected. This post \nis abusive!\n\n");
    		}
    		else
    		{
    			Screen.jta.append("Extremely high amount of abusive content has been detected.\nThis post is very abusive!\n\n");
    		}
    	}
    	
    	
    	//Child filter option.
    	else if (Screen.childcheck == true)
    	{
    		//Display results
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
    		
    	//Simplify option.
    	{
    		//Results
    		Screen.jta.append("Post " + PostNum + ": "); 
    		
    		if (results == 0)
    		{
    			Screen.jta.append("No abuse.");
    		}
    		else if (results < 7.5)
    		{
    			Screen.jta.append("Tiny amount of abuse.");
    		}
    		else if (results < 15.0)
    		{
    			Screen.jta.append("Small amount of abuse.");
    		}
    		else if (results < 30.0)
    		{
    			Screen.jta.append("Medium amount of abuse.");
    		}
    		else if (results < 50)
    		{
    			Screen.jta.append("High amount of abuse.");
    		}
    		else
    		{
    			Screen.jta.append("Extremely high amount of abuse.");
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
    	Screen.jta.append("///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////\n\n");
    	//If suspicious words exist, prompt user to verify them.
    	if (Evaluate > 0)
    	{
    		int Display = Evaluate - Duplicate;
        	Screen.jta2.setText(Display + " word(s) need to be verified.\nPlease verify these and rescan the posts!\n");
    	}
    }
}