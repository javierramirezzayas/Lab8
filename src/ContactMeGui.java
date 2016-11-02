import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ContactMeGui extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 400;
	private static final int HEIGHT = 300;

	private JLabel emailL, passwordL,reEnterPassL, messageL;
	private JTextField emailTF, messageTF;
	private JTextField passwordTF,reEnterPassTF; 
	private JButton sendB, exitB, clearB;

	private SendButtonHandler sbHandler;
	private ExitButtonHandler ebHandler;

	private JMenuBar  menuBar;
	private JMenu make;
	private JMenuItem byEmail;
	private JMenuItem byFax;
	
	// Added
	private JLabel nameL; 
	private JTextField nameTF;
	private JMenuItem byCellPhone;
	private JMenuItem byRegularMail;
	private JRadioButton maleB, femaleB;
	ButtonGroup theGroup = new ButtonGroup();
	private ClearButtonHandler clHandler;
	// Added

	public ContactMeGui()
	{
		nameL = new JLabel("Full Name: ", SwingConstants.CENTER); // Added
		emailL = new JLabel("Email: ", SwingConstants.CENTER);
		passwordL = new JLabel("Password: ", SwingConstants.CENTER);
		reEnterPassL= new JLabel("Re-enter Password: ", SwingConstants.CENTER);
		messageL = new JLabel("Addtional Info: ", SwingConstants.CENTER);

		nameTF = new JTextField(); // Added
		emailTF = new JTextField();
		// Changed Variables from JTextField to JPasswordField Type 
		// Note: left the initialized variables as JTextField in order to be able to manipulate the in the SetButtonhandler Method
		passwordTF = new JPasswordField();
		reEnterPassTF = new JPasswordField();
		// Changed
		messageTF = new JTextField();
		
		// Added
		maleB = new JRadioButton("Male", false);
		femaleB = new JRadioButton("Female", false);
		
		// Group the Radio Buttons
		theGroup.add(maleB);
		theGroup.add(femaleB);
		// Added

		//Specify handlers for each button and add (register) ActionListeners to each button.
		sendB = new JButton("Send");
		sbHandler = new SendButtonHandler();
		sendB.addActionListener(sbHandler);

		exitB = new JButton("Exit");
		ebHandler = new ExitButtonHandler();
		exitB.addActionListener(ebHandler);

		clearB = new JButton("Clear");
		// Added
		clHandler = new ClearButtonHandler();
		clearB.addActionListener(clHandler);
		// Added


		menuBar = new JMenuBar();
		make = new JMenu("            Contact Me                 ");
		byEmail = new JMenuItem("by Email");
		byFax = new JMenuItem("by Fax");
		byCellPhone = new JMenuItem("by CellPhone");
		byRegularMail = new JMenuItem("by Regular Mail");
		menuBar.add(make);
		make.add(byEmail);
		make.add(byFax);
		make.add(byCellPhone);
		make.add(byRegularMail);

		setTitle("Registration Form");
		Container pane = getContentPane();
		pane.setLayout(new GridLayout(8, 3));

		//Add things to the pane in the order you want them to appear (left to right, top to bottom)
		pane.add(nameL); // Added
		pane.add(nameTF);
		pane.add(emailL);
		pane.add(emailTF);
		pane.add(passwordL);
		pane.add(passwordTF);
		pane.add(reEnterPassL);
		pane.add(reEnterPassTF);
		pane.add(messageL);
		pane.add(messageTF);

		pane.add(maleB);
		pane.add(femaleB);
		
		pane.add(menuBar);

		pane.add(clearB);
		pane.add(sendB);
		pane.add(exitB);

		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private class SendButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//We use the getText & setText methods to manipulate the data entered into those fields.
			String inputEmail= emailTF.getText();
			String inputPassword= passwordTF.getText();
			String reinputPassword= reEnterPassTF.getText();
			String inputMessage= messageTF.getText();
			
			System.out.println("inputPassword: "+inputPassword);
			System.out.println("reinputPassword: "+reinputPassword);


			if(inputEmail.equals("") || inputPassword.equals("") || reinputPassword.equals("")){

				//Display error message
				JOptionPane.showMessageDialog(null,
						"Wrong input. Rememeber to fill all the spaces",
						"Something is missing!!!",
						JOptionPane.WARNING_MESSAGE);
			}
			
			//Added
			// Missing @ in email
			else if(inputEmail.indexOf('@') == -1){
				JOptionPane.showMessageDialog(null,"Invalid Email Address.","Wrong input", JOptionPane.WARNING_MESSAGE);
			}
			
			// Input Password and ReInput Password are not the same
			else if(!(inputPassword.equals(reinputPassword))){
				JOptionPane.showMessageDialog(null,"Password Mismatch.","Wrong input", JOptionPane.WARNING_MESSAGE);
			}

			//the password is too long
			else if(inputPassword.length() > 11){
				JOptionPane.showMessageDialog(null,"Wrong password. Rememeber it is 10 characters long",
						"Wrong Password Input!!!", JOptionPane.WARNING_MESSAGE);
			}

			//the message is too long
			else if(inputMessage.length() > 81){
				JOptionPane.showMessageDialog(null,"Wrong Message. Rememeber it is 80 characters long",
						"Wrong Message Input!!!", JOptionPane.WARNING_MESSAGE);
			}
			//all is correct
			else
			{
				JOptionPane.showMessageDialog(null,
						"Your message has been sent.");
			}

			//massageTF.setText("" + area);
		}
	}
	
	private class ClearButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			nameTF.setText("");
			emailTF.setText("");
			passwordTF.setText("");
			reEnterPassTF.setText("");
			messageTF.setText("");
			theGroup.clearSelection();
		}
	}

	public class ExitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}

	public static void main(String[] args)
	{
		ContactMeGui contactMe = new ContactMeGui();
	}
}