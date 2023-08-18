package jTable;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;

public class Login extends JFrame implements ActionListener{
	
	ImageIcon Logo = new ImageIcon(new ImageIcon("Logo.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
	Toolkit ToolK = getToolkit();
	Dimension Size = ToolK.getScreenSize();
	
	JTextField User = new JTextField();
	JPasswordField Pass = new JPasswordField();
	JButton Login = new JButton();

	String FinalUser = "admin", FinalPass = "12345";
	
	void Login_Design(){	
		setSize(395, 250);
		setLocation(Size.width/2 - getWidth()/2, Size.height/2 - getHeight()/2);
		getContentPane().setBackground(Color.decode("#424242"));
		setResizable(false);
		setTitle("Account Login");
		setIconImage(Logo.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Labels - Main
		JLabel LogText = new JLabel();
		JLabel UserText = new JLabel();
		JLabel PassText = new JLabel();	
		JButton ShowPass = new JButton();
		
		
		//Login
		
		LogText.setBounds(70, 8, 250, 50);
		LogText.setText("LOGIN TO SYSTEM");
		LogText.setFont(new Font("Helvetica", Font.BOLD, 26));
		LogText.setForeground(Color.white);
		
		UserText.setBounds(20, 53, 250, 50);
		UserText.setText("Username: ");
		UserText.setFont(new Font("Helvetica", Font.BOLD, 17));
		UserText.setForeground(Color.white);
		
		User.setBounds(115, 60, 240, 35);
		User.setFont(new Font("Helvetica", Font.PLAIN, 18));
		User.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
		User.setBackground(null);
		User.setForeground(Color.white);
		User.setCaretColor(Color.white);
		User.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		User.addKeyListener(new java.awt.event.KeyAdapter() {
		    public void keyPressed(java.awt.event.KeyEvent e) {
		    if (e.getKeyCode() == KeyEvent.VK_ENTER ) {
		    	Pass.requestFocus();
		     } else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
		    	Pass.requestFocus();
		    }
		  }
		});
		
		
		PassText.setBounds(20, 98, 250, 50);
		PassText.setText("Password: ");
		PassText.setFont(new Font("Helvetica", Font.BOLD, 17));
		PassText.setForeground(Color.white);
		
		Pass.setBounds(115, 103, 240, 35);
		Pass.setFont(new Font("Helvetica", Font.BOLD, 18));
		Pass.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
		Pass.setBackground(null);
		Pass.setEchoChar('∗');
		Pass.setForeground(Color.white);
		Pass.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		Pass.setCaretColor(Color.white);
		Pass.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		        	Login.doClick();
		        } else if (e.getKeyCode() == KeyEvent.VK_UP ) {
	 		    	User.requestFocus();
	 		    }
		    }
		});
		
		Login.setText("LOGIN");
		Login.setBounds(20, 160, 340, 35);
		Login.setFont(new Font("Helvetica", Font.BOLD, 12));
		Login.setForeground(Color.white);
		Login.setBackground(Color.decode("#228b22"));
		Login.setOpaque(true);
		Login.setBorder(null);
		Login.setFocusable(false);
		Login.addActionListener(this);
		Login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Login.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	Login.setBackground(Color.decode("#02bf34"));
		    	Login.setFont(new Font("Helvetica", Font.BOLD, 16));
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	Login.setBackground(Color.decode("#228b22"));
		    	Login.setFont(new Font("Helvetica", Font.BOLD, 12));
		    }
		});
		
		//Add
		add(LogText);
		add(UserText);
		add(User);
		add(PassText);
		add(Pass);
		add(Login);
		
		setLayout(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//CHECKS USER, AND PASS
		if(e.getSource() == Login) {
			if((User.getText().equals(null) || User.getText().equals("")) && (new String(Pass.getPassword()).equals(null) || new String(Pass.getPassword()).equals(""))) {
				JOptionPane.showMessageDialog(this, "Please enter your username and password", "Unsuccessful Login", JOptionPane.WARNING_MESSAGE);
			} else if(User.getText().equals(null) || User.getText().equals("")){
				JOptionPane.showMessageDialog(this, "Please enter your username", "Unsuccessful Login", JOptionPane.WARNING_MESSAGE);
			} else if(new String(Pass.getPassword()).equals(null) || new String(Pass.getPassword()).equals("")){
				JOptionPane.showMessageDialog(this, "Please enter your password", "Unsuccessful Login", JOptionPane.WARNING_MESSAGE);
			} else if(User.getText().equals(FinalUser) && new String(Pass.getPassword()).equals(FinalPass)) {
				JOptionPane.showMessageDialog(this, "Successfully Logged In!", "Successful Login", JOptionPane.INFORMATION_MESSAGE);
				Home Main = new Home();
				Main.Home_Design();
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Incorrect username or password", "Incorrect Account Details", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
}
