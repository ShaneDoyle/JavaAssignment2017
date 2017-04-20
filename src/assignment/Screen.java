package assignment;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;
import javax.swing.JCheckBox;

import assignment.FileManager;

public class Screen extends JFrame implements ActionListener
{
//Buttons
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JButton button7;
	JButton button8;
	JButton button9;
	JButton button10;
   
	//Labels
	JLabel label3;
	static JLabel label4;
	static JLabel label5;
	JLabel label6;
   
	//Process Builder.
	ProcessBuilder pb;
   
   //Text Fields.
	JTextField textfield2;
	JTextField textfield4;
	
	//Check Boxes
	JCheckBox simple;
	JCheckBox child;
   
	//TextAreas with Scroller.
	static JTextArea jta;
	static JTextArea jta2;
	static JScrollPane jsp;
	static JScrollPane jsp2;
	
	//Variables.
	int Verify = 0 ; //Verifying suspicious words.
	public static boolean simplecheck = false; //Simple option.
	public static boolean childcheck = false; //Child filter option.
	
	//Constructor
	Screen(String title)
	{
		//Frame settings.
		int xsize = 800;
	   	int ysize = 500;
	   	JFrame window = new JFrame("Language Filter");
	   	window.setSize(xsize,ysize);
	   	window.setResizable(false);
	   	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   	//Panel
	   	JPanel panel = new JPanel();
	   	panel.setLayout(null);
	   	window.add(panel);
	   
	   
	   	//Buttons
	   	button1 = new JButton("Verify Suspicious Words"); //Verify suspicious words.
	   	button1.setBounds(500,40,200,25);
	   	panel.add(button1);
	   	button1.addActionListener(this);
	   
	   	button2 = new JButton("Add"); //Abuse
	   	button2.setBounds(650,245,89,20);
	   	panel.add(button2);
	   	button2.addActionListener(this);
	   
	   	button3 = new JButton("Scan Posts"); //Scan social media posts.
	   	button3.setBounds(10,40,200,25);
	   	panel.add(button3);
	   	button3.addActionListener(this);
	   
	   	button4 = new JButton("Abusive"); //Verify as Abuse.
	  	button4.setBounds(450,120,130,25);
	  	panel.add(button4);
	  	button4.addActionListener(this);
	   
	  	button5 = new JButton("Safe"); //Verify as Safe
	  	button5.setBounds(609,120,130,25);
	  	panel.add(button5);
	  	button5.addActionListener(this);
	   
	  	button8 = new JButton("Remove"); //Abuse
	  	button8.setBounds(650,274,89,20);
	  	panel.add(button8);
	  	button8.addActionListener(this);
	   
	  	button7 = new JButton("Edit Posts"); //Edit Post
	  	button7.setBounds(145,10,110,25);
	  	panel.add(button7);
	  	button7.addActionListener(this);
	   
	  	button9 = new JButton("Add"); //Safe
	  	button9.setBounds(650,345,89,20);
	  	panel.add(button9);
	  	button9.addActionListener(this);
	   
	  	button10 = new JButton("Remove"); //Safe
	  	button10.setBounds(650,374,89,20);
	  	panel.add(button10);
	  	button10.addActionListener(this);

	  	button1.setEnabled(false); 	   //Disable verifying buttons.
	  	button4.setEnabled(false);
	  	button5.setEnabled(false);
	   
	  	
	  	//Labels.
	  	label3 = new JLabel("Bad Word (Add or Remove)"); // Adding / Removing new safe words.
	  	label3.setBounds(470,220,200,25);
	  	panel.add(label3);
	   
	  	label4 = new JLabel(""); //Results of adding/removing bad words.
	  	label4.setBounds(460,290,400,25);
	  	panel.add(label4);
	   
	  	label5 = new JLabel(""); //Results of adding/removing safe words.
	  	label5.setBounds(460,390,400,25);
	  	panel.add(label5);
	   
	  	label6 = new JLabel("Safe Word (Add or Remove)"); // Adding / Removing new bad words.
	  	label6.setBounds(470,320,200,25);
	  	panel.add(label6);
	   
	  	
	  	//Text Fields
	  	textfield2 = new JTextField (10); //Bad Word Text Box
	  	textfield2.setBounds(450,245,195,50);
	  	panel.add(textfield2);

	  	textfield4 = new JTextField (10); //Safe Word Text Box
	  	textfield4.setBounds(450,345,195,50);
	  	panel.add(textfield4);
	   
	   
	  	//Text Areas with scrolls.
	  	jta = new JTextArea(); //Post results text area.
	  	JScrollPane scroll = new JScrollPane (jta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	  	scroll.setBounds(10,70,380,350);
	  	jta.setEditable(false);
	  	panel.add(scroll);
	  	panel.setVisible (true);
	   
	  	jta2 = new JTextArea();	//Verification text area.
	  	JScrollPane scroll2 = new JScrollPane (jta2);
	  	scroll2.setBounds(450,70,290,50);
	  	jta2.setEditable(false);
	  	panel.add(scroll2);
	  	panel.setVisible (true);
	   
	  	
	  	//Check Boxes
	  	simple = new JCheckBox("Simplify"); //Simplify results.
	  	simple.setBounds(220,40,70,25);
	  	panel.add(simple);
	  	simple.addActionListener(this);
	   
	  	child = new JCheckBox("Child"); 	   //Child filter results.
	  	child.setBounds(290,40,70,25);
	  	panel.add(child);
	  	child.addActionListener(this);
	   
	  	//Making it visible now ensures all GUI components load.
	  	window.setVisible(true);
	   
	   	//Open and close file.
	   	FileManager f1 = new FileManager("abuse.txt");
	   	FileManager f2 = new FileManager("safe.txt");
	   	f1.connectToFile();
	   	f1.readFile();
	   	f2.connectToFile();
	   	f2.readFile2();
	}
   
  	boolean SuspectButtons = false; //Users can't mark suspicious words while this is false.
	//Event Handler
   	public void actionPerformed(ActionEvent action)
   	{
   		//Text Files.
		FileManager f1 = new FileManager("abuse.txt");
		FileManager f2 = new FileManager("posts.txt");
   		FileManager f3 = new FileManager("suspect.txt");
   		FileManager f4 = new FileManager("safe.txt");
   			
   	   	//Button 1 (Verify Suspicious Words)
   		if(action.getSource() == button1)
   		{
   			button1.setEnabled(false); //Disable button while user is verifying.
   			while (FileManager.SuspectWordsEvaluate[Verify] == "")
			{
   				Verify++;
			}
   			SuspectButtons = true; //Enable verification.
   			f3.connectToFile(); //Connect to "suspect.txt".
   			f3.getFileWriter();
   			for(int i = 0; i!= FileManager.Evaluate; i++)
   			{
   				f3.writeLineToFile(FileManager.SuspectWordsEvaluate[i]);
   			}
   			f3.closeWriteFile();
   			Screen.jta2.setText("");
   			Screen.jta2.append(FileManager.SuspectWordsEvaluate[Verify]);
   		}

   			
   		//Button 2 (Add new bad word)
   		if(action.getSource() == button2)
   		{
   			String input = textfield2.getText(); //Get inputed word.
   			//Ensures user input is valid.
   			while(!input.matches("[a-zA-Z]+"))
   			{
   				Screen.label4.setText("Invalid syntax. Only characters are permitted.");
   				return;
  				}
  				
  				//Refreshes "Bad Words"
   			f1.connectToFile();
   			f1.readFile();
   			
   			try 
   			{
   				f1.AbuseAppend(input);
   			} 
   			
   			catch (IOException e) 
   			{
   				e.printStackTrace();
   			}
   			
   			finally
   			{
   				if (FileManager.insertion == true) //If word insertion was successful.
   				{
   					Screen.label4.setText("Word inserted!");
   					FileManager.insertion = false;
   				}
   			}
   			
   		   //Refreshes "BadWords" array.
   		   f1.readFile();
   		   f1.closeReadFile();
   		}
   	   
   			
   			
   		//Button 3 (Scan posts in "posts.txt" for abusive content)
   		if(action.getSource() == button3)
   	   	{
   			//Clear other text boxes.
   		   	Screen.jta.setText("");
   		   	Screen.jta2.setText("");
   		   	FileManager.Evaluate = 0;
   		   	
   		   	//Refresh "SafeWords".
   		   	f4.connectToFile();
   		   	f4.readFile2();
   		   	f4.closeReadFile();
   		   
   		   	//Connect to "posts.txt" and scan it.
   		   	f2.connectToFile();
   		   	f2.scanPost();
   		   	f2.closeReadFile();
   	   	}
		   
   		
   			
   			
   		//Button 4 (User verifies the suspicious word as "Abusive")
   		if(action.getSource() == button4)
   		{
   			
   			if (SuspectButtons == false)
   			{
			   Screen.jta2.setText("Please select verify button above.");
			   return;
   			}
   			
   			else if (Verify >= FileManager.Evaluate)
   			{
   				VerifyComplete();
   			}

   			else
   			{
   				//Refresh "BadWords".
   				f1.connectToFile();
   				f1.readFile();
   				f1.closeReadFile();

   				try 
   				{
   					f1.AbuseAppend((FileManager.SuspectWordsEvaluate[Verify]));
   				} 
   				catch (IOException e) 
   				{
   					e.printStackTrace();
   				}
		   
   				finally
   				{
			   		Verify++;
				   	Screen.jta2.setText("");
				   	Screen.jta2.append(FileManager.SuspectWordsEvaluate[Verify]);
			   		f1.readFile();
			   		if (Verify == FileManager.Evaluate)
			   		{
			   			VerifyComplete();
			   		}
   				}
   			}
   		}
		   
   			
   			
		//Button 5( User marks the word as "Safe")
   		if(action.getSource() == button5)
		{
		   	if (SuspectButtons == false)
		   	{
			   	Screen.jta2.setText("Please select verify button above.");
			   	return;
			}
			   
			//End verification.
			else if (Verify >= FileManager.Evaluate)
			{
				VerifyComplete();
			}

			else
			{
				//Refresh "SafeWords".
				f4.connectToFile();
				f4.readFile2();
					  
				try 
			   	{
			   		f4.SafeAppend2((FileManager.SuspectWordsEvaluate[Verify]));
			   	} 
				   
			   	catch (IOException e) 
			   	{
				  	e.printStackTrace();
			   	}
			   
			   	finally
			   	{
			   		Verify++;
				   	Screen.jta2.append(FileManager.SuspectWordsEvaluate[Verify]);
				   	Screen.jta2.setText(""); //Clear text box.
				   	
				    //Refresh SafeWords.
			 		f4.readFile2();
			   		f4.closeReadFile();
				   		
				   	if (Verify == FileManager.Evaluate)
				   	{
				   		VerifyComplete();
				   	}
			   	}
			}
		}
		   
	  
	   
   		//Button 7 (Edit posts)
   		if(action.getSource() == button7)
   		{
   			pb = new ProcessBuilder("notepad.exe", "posts.txt");//Makes a process builder that opens "posts.txt" with "notepad".
   			try 
   			{
   				pb.start();
   			} 
   			catch (IOException e) 
   			{
   				e.printStackTrace();
   			}
   		}
	   
		   
	//Button 8 (Remove Bad Word)
   		if(action.getSource() == button8)
   		{
   			String input = textfield2.getText();
		   
   			//Ensures correct user input.
   			while(!input.matches("[a-zA-Z]+"))
   			{
   				Screen.label4.setText("Invalid syntax. Only characters are permitted.");
   				return;
   			}
			   
   			//Refresh "BadWords".
   			f1.connectToFile();
   			f1.readFile();
   			f1.getFileWriter();
			   	
   			try
			{
   				f1.AbuseRemove(input);
			}
   			catch (IOException e) 
   			{
   				e.printStackTrace();
   			}
   			finally
   			{
   				//Refreshes "BadWords" array.
   				f1.readFile();
   				f1.closeReadFile();
   				f1.closeWriteFile();
   				if (FileManager.insertion == false)
   				{
   					Screen.label4.setText("Word does not exist!");
   				}
   				else
   				{
   					Screen.label4.setText("Word removed!");
   					FileManager.insertion = false;
   				}
   			}
   		}
	   
	   
	   
   		//Button 9 (Add new Safe Word)
   		if(action.getSource() == button9)
   		{
   				
   			String input = textfield4.getText();
		   
   			//Ensures correct user input.
   			while(!input.matches("[a-zA-Z]+"))
			{
   				Screen.label5.setText("Invalid syntax. Only characters are permitted.");
   				return;
			}
		   
   			//Refresh "SafeWords".
   			f4.connectToFile();
   			f4.readFile2();
   			
   			try 
   			{
   				f4.SafeAppend2(input);
   			} 
			   
   			catch (IOException e) 
   			{
   				e.printStackTrace();
   			}
   			finally
   			{
   				if (FileManager.insertion == true)
   				{
   					Screen.label5.setText("Word inserted!");
   					FileManager.insertion = false;
   				}
   			}
			   
   			//Refreshes "BadWords".
   			f4.readFile2();
   			f4.closeReadFile();
   		}
	   
	   
   		//Button 10 (Remove Safe Word)
   		if(action.getSource() == button10)
   		{
   			String input = textfield4.getText();
				   
   			//Ensures correct user input.
   			while(!input.matches("[a-zA-Z]+"))
   			{
   				Screen.label5.setText("Invalid syntax. Only characters are permitted.");
   				return;
   			}
		   
   			//Refreshes "BadWords"
   			f4.connectToFile();
   			f4.readFile2();
   			f4.getFileWriter();
   			try
   			{
   				f1.SafeRemove(input);
   			}
   			catch (IOException e) 
   			{
   				e.printStackTrace();
   			}
   			finally
		   			{
   				f4.closeWriteFile();
   				//Refreshes "BadWords" array.
   				f4.readFile();
   				if (FileManager.insertion == false)
   				{
   					Screen.label5.setText("Word does not exist!");
   				}
   				else
   				{
   					Screen.label5.setText("Word removed!");
   					FileManager.insertion = false;
   				}
		   	}
   		}
	   
	   
		if (FileManager.Evaluate > 0)
		{
			//Enables verification buttons.
			button1.setEnabled(true);
			button4.setEnabled(true);
			button5.setEnabled(true);
		   
			//Disables adding/removing of bad/safe words.
			button2.setEnabled(false);
			button8.setEnabled(false);
			button9.setEnabled(false);
			button10.setEnabled(false);
			Screen.label4.setText("");
			Screen.label5.setText("");
		}
		else
		{
			//Disables verification buttons.
			button1.setEnabled(false);
			button4.setEnabled(false);
			button5.setEnabled(false);
		   
			//Enables adding/removing of bad/safe words.
			button2.setEnabled(true);
			button8.setEnabled(true);
			button9.setEnabled(true);
			button10.setEnabled(true);
		}
	   
		//Scan posts in "posts.txt" for abusive content.
		if(action.getSource() == simple)
		{
			if(simple.isSelected())
			{
				simplecheck = true;
				child.setSelected(false);
				childcheck = false;
			}
			else
			{
				simplecheck = false;
			}
			Screen.jta.setText("");
		}
	   
		if(action.getSource() == child)
		{
			if(child.isSelected())
			{
				childcheck = true;
				simple.setSelected(false);
				simplecheck = false;
			}
			else
			{
				childcheck = false;
			}
				Screen.jta.setText("");
			}
   		}

   		void VerifyComplete()
   		{
   				Screen.jta2.setText("Verification complete!\nPlease rescan posts!");
   				FileManager.Evaluate = 0;
   				button2.setEnabled(true); //Bad Word option is enabled.
   		}
	}