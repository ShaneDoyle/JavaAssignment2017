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

import assignment.FileManager;

public class Screen extends JFrame implements ActionListener
{
	
   JButton button1;
   JButton button2;
   JTextField textfield1;
   JLabel label1;
   JLabel label2;
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

	   label1 = new JLabel("Enter a string to see if it is abusive.");
	   label1.setBounds(450,0,200,25);
	   panel.add(label1);
	   
	   label2 = new JLabel();
	   label2.setBounds(450,100,200,25);
	   panel.add(label2);
	   
	   textfield1 = new JTextField (10);
	   textfield1.setBounds(450,30,200,25);
	   panel.add(textfield1);
	   
	   button1 = new JButton("Enter");
	   button1.setBounds(450,60,200,25);
	   panel.add(button1);
	   button1.addActionListener(this);
	   
	   //Open and close file.
	   FileManager f1 = new FileManager("abuse.txt");
	   f1.connectToFile();
	   f1.readFile();
	   f1.closeReadFile();
   }

   //Event Handler
   public void actionPerformed(ActionEvent button)
   {
	   if(button.getSource() == button1)
	   {
		   
		   int i = 0;
		   
		   String input = textfield1.getText();
		   label2.setText(input);
		  
		   String search = FileManager.BadWords[0];
		   System.out.println(search);
		   while (i <=  4)
		   {

		   if (input.toLowerCase().indexOf(search.toLowerCase()) != -1 ) 
		   {
			   label2.setText("Abusive text detected.");
		   } 
		   else 
		   {
			   label2.setText("No abuse detected.");
		   }
		   i++;
		   
		   }
	   }
   }
}