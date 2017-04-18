package assignment;

import java.awt.Color;
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

import assignment.FileManager;

public class Screen extends JFrame implements ActionListener
{
	
   JButton button1;
   JButton button2;
   JButton button3;
   
   JButton button4;
   JButton button5;
   JButton button6;
   JTextField textfield1;
   JTextField textfield2;
   JTextField textfield3;
   JLabel label1;
   JLabel label2;
   JLabel label3;
   static JLabel label4;
   JLabel label5;
   JRadioButton radiobutton1;
   
   static JTextArea jta;
   static JTextArea jta2;
   static JScrollPane jsp;
   static JScrollPane jsp2;
   int Verify = 0 ; //Verifying suspicious words.
   
   JComboBox NameList = new JComboBox();
   JLabel picture;
   
   //Constructor
   Screen(String title)
   {
	   int xsize = 400;
	   int ysize = 800;
	   JFrame window = new JFrame("Language Filter");
	   window.setSize(xsize,ysize);
	   window.setResizable(false);
	   window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	   JPanel panel = new JPanel();
	   panel.setLayout(null);
	   window.add(panel);
	   
	   window.setVisible(true);

	   //Abuse detector. 
	   
	   label1 = new JLabel("Welcome to the abuse detector!");
	   label1.setBounds(110,0,200,25);
	   panel.add(label1);
	   
	   /*
	   label2 = new JLabel();
	   label2.setBounds(450,100,500,25);
	   panel.add(label2);
	   
	   textfield1 = new JTextField (10);
	   textfield1.setBounds(450,20,200,25);
	   panel.add(textfield1);
	   */
	   button1 = new JButton("Verify Suspicious Words");
	   button1.setBounds(100,270,200,25);
	   panel.add(button1);
	   button1.addActionListener(this);
	   
	   button4 = new JButton("Abusive");
	   button4.setBounds(50,350,89,25);
	   panel.add(button4);
	   button4.addActionListener(this);
	   
	   button5 = new JButton("Safe");
	   button5.setBounds(150,350,89,25);
	   panel.add(button5);
	   button5.addActionListener(this);
	   
	   button6 = new JButton("Ignore");
	   button6.setBounds(250,350,89,25);
	   panel.add(button6);
	   button6.addActionListener(this);
	   
	   jta2 = new JTextArea();
	   JScrollPane scroll2 = new JScrollPane (jta2);
	   scroll2.setBounds(50,300,290,50);
	   jta2.setEditable(false);
	  // textArea.setText();
	   panel.add(scroll2);
	   panel.setVisible (true);
	   
	   //Adding new bad words
	   label3 = new JLabel("Add new bad word!");
	   label3.setBounds(150,600,400,25);
	   panel.add(label3);
	   
	   textfield2 = new JTextField (10);
	   textfield2.setBounds(150,625,150,25);
	   panel.add(textfield2);
	   
	   button2 = new JButton("Insert Word!");
	   button2.setBounds(150,650,150,25);
	   panel.add(button2);
	   button2.addActionListener(this);
	   
	   label4 = new JLabel();
	   label4.setBounds(150,675,500,25);
	   panel.add(label4);
	   
	   //Scan social media posts.
	   button3 = new JButton("Scan Posts");
	   button3.setBounds(100,70,200,25);
	   panel.add(button3);
	   button3.addActionListener(this);
	   
	   label5 = new JLabel("Scan posts.");
	   label5.setBounds(150,170,500,25);
	   //panel.add(label5);
	   
	   jta = new JTextArea();
	   JScrollPane scroll = new JScrollPane (jta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	   scroll.setBounds(10,100,380,150);
	   jta.setEditable(false);
	  // textArea.setText();
	   panel.add(scroll);
	   panel.setVisible (true);
	   
	   textfield3 = new JTextField (10);
	   textfield3.setBounds(10,100,380,75);
	   //panel.add(textfield3);
	   
	   
	   //Open and close file.
	   FileManager f1 = new FileManager("abuse.txt");
	   FileManager f2 = new FileManager("safe.txt");
	   f1.connectToFile();
	   f1.readFile();
	   f2.connectToFile();
	   f2.readFile2();
	   f1.closeReadFile();
   }
   
	   //Event Handler
   		boolean SuspectButtons = false; //Users can't mark suspicious words without clicking  the "Verify" button.
   		public void actionPerformed(ActionEvent button)
   		{
   			FileManager f3 = new FileManager("suspect.txt");
		   

		   //Verify Suspicious Words.
   			if(button.getSource() == button1)
   			{
			   SuspectButtons = true;
			   f3.connectToFile();
			   f3.getFileWriter();
			   for(int i = 0; i!= FileManager.Evaluate; i++)
			   {
				   f3.writeLineToFile(FileManager.SuspectWordsEvaluate[i]);
			   }
			   f3.closeWriteFile();
			   
			   Screen.jta2.setText("");
			   Screen.jta2.append(FileManager.SuspectWordsEvaluate[Verify]);
		   }
		   
   		//User marks the word as "Abusive".
		   if(button.getSource() == button4)
		   {
			   if (SuspectButtons == false)
			   {
				   Screen.jta2.setText("Please select verify button above.");
				   return;
			   }
			   else if (Verify >= FileManager.Evaluate)
			   {
				   Screen.jta2.setText("No more words left!");
			   }

			   else
			   {
				 //Read File
				   FileManager f1 = new FileManager("abuse.txt");
				   f1.connectToFile();
				   f1.readFile();

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
					   	Screen.jta2.setText("");
					   	Screen.jta2.append(FileManager.SuspectWordsEvaluate[Verify]);
				   		Verify++;
				   		f1.readFile();
				   		if (Verify == FileManager.Evaluate)
				   		{
				   			Screen.jta2.setText("No more words left!");
				   		}
				   }
			   }
		   }
		   
		   //User marks the word as "Safe".
		   if(button.getSource() == button5)
		   {
			   if (SuspectButtons == false)
			   {
				   Screen.jta2.setText("Please select verify button above.");
				   return;
			   }
			   else if (Verify >= FileManager.Evaluate)
			   {
				   Screen.jta2.setText("No more words left!");
			   }

			   else
			   {
				   //Read File
				   FileManager f2 = new FileManager("safe.txt");
				   f2.connectToFile();
				   f2.readFile2();

				   try 
				   {
					   f2.SafeAppend((FileManager.SuspectWordsEvaluate[Verify]));
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

				   		f2.readFile2();
				   		if (Verify == FileManager.Evaluate)
				   		{
				   			Screen.jta2.setText("No more words left!");
				   		}
				   }
			   }
		   }
		   
		   if(button.getSource() == button6)
		   {
			   if (SuspectButtons == false)
			   {
				   Screen.jta2.setText("Please select verify button above.");
				   return;
			   }
			   else if (Verify >= FileManager.Evaluate)
			   {
				   Screen.jta2.setText("No more words left!");
			   }
			   else
			   {
				   
			   Verify++;
			   Screen.jta2.append(FileManager.SuspectWordsEvaluate[Verify]);
		   		if (Verify == FileManager.Evaluate)
		   		{
		   			Screen.jta2.setText("No more words left!");
		   		}
			   }
		   }
		   
	   
	   //Add new bad word.
	   if(button.getSource() == button2)
	   {
		   System.out.println("Insert word!");
		   
		   String input = textfield2.getText();
		   
		   FileManager f1 = new FileManager("abuse.txt");
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
	   }
	   
	   //Scan posts in "posts.txt" for abusive content.
	   if(button.getSource() == button3)
	   {
		   Screen.jta2.setText(""); //Clear verify
		   FileManager f1 = new FileManager("posts.txt");
		   Screen.jta.setText(""); //Clears text.
		   f1.connectToFile();
		   f1.scanPost();
		   
	   }
   }
}