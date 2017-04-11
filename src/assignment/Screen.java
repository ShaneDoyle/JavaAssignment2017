package assignment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
   JTextField textfield1;
   JTextField textfield2;
   JLabel label1;
   JLabel label2;
   JLabel label3;
   static JLabel label4;
   JRadioButton radiobutton1;
   
   JComboBox NameList = new JComboBox();
   JLabel picture;
   
   //Constructor
   Screen(String title)
   {
	   JFrame window = new JFrame("Language Filter");
	   window.setSize(1000,800);
	   window.setResizable(false);
	   window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	   JPanel panel = new JPanel();
	   panel.setLayout(null);
	   window.add(panel);
	   
	   window.setVisible(true);

	   //Abuse detector.
	   label1 = new JLabel("Enter a string to see if it is abusive.");
	   label1.setBounds(450,0,200,25);
	   panel.add(label1);
	   
	   label2 = new JLabel();
	   label2.setBounds(450,100,500,25);
	   panel.add(label2);
	   
	   textfield1 = new JTextField (10);
	   textfield1.setBounds(450,20,200,25);
	   panel.add(textfield1);
	   
	   button1 = new JButton("Enter!");
	   button1.setBounds(450,50,200,25);
	   panel.add(button1);
	   button1.addActionListener(this);
	   
	   //Adding new bad words
	   label3 = new JLabel("Add new bad word!");
	   label3.setBounds(150,0,400,25);
	   panel.add(label3);
	   
	   textfield2 = new JTextField (10);
	   textfield2.setBounds(150,25,150,25);
	   panel.add(textfield2);
	   
	   button2 = new JButton("Insert Word!");
	   button2.setBounds(150,50,150,25);
	   panel.add(button2);
	   button2.addActionListener(this);
	   
	   label4 = new JLabel();
	   label4.setBounds(150,75,500,25);
	   panel.add(label4);
	   
	   //Open and close file.
	   FileManager f1 = new FileManager("abuse.txt");
	   f1.connectToFile();
	   f1.readFile();
	   f1.closeReadFile();
   }
   
   //Event Handler
   public void actionPerformed(ActionEvent button)
   {
	   
	   //Abuse detector.
	   if(button.getSource() == button1)
	   {
		   String input = textfield1.getText();
		   label2.setText(input);
			
		   //Compares strings to the "abuse.txt" file.
		   String search = FileManager.BadWords[0];
	       for(int j=0; j<FileManager.lines+1; j++)
	       {
	    	   if (input.toLowerCase().indexOf(search.toLowerCase()) != -1 )  //Input matches text file.
	    	   {
	    		   label2.setText("Abusive text detected. Word detected: " + input);
	    	   } 
	    	   else //No match.
	    	   {
	    		   label2.setText("No abuse detected.");
	    		   search = FileManager.BadWords[j];
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
			   f1.append(input);
		   } 
		   catch (IOException e) 
		   {
			e.printStackTrace();
		   }
	   }
   }
}