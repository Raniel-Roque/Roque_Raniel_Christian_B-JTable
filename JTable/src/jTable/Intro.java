package jTable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Intro extends JFrame implements ActionListener{
	
	ImageIcon Logo = new ImageIcon(new ImageIcon("Logo.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
	Toolkit ToolK = getToolkit();
	Dimension Size = ToolK.getScreenSize();
	
	JButton Enter = new JButton();
	void Intro_Design(){	
		setSize(395, 250);
		setLocation(Size.width/2 - getWidth()/2, Size.height/2 - getHeight()/2);
		getContentPane().setBackground(Color.decode("#424242"));
		setResizable(false);
		setTitle("Account Enter");
		setIconImage(Logo.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel Image = new JLabel();
		Image.setBounds(35, 30, 100, 100);
		Image.setIcon(Logo);
		
		JLabel Shop = new JLabel("Inventory &");
		Shop.setBounds(145, 5, 300, 100);
		Shop.setFont(new Font("Helvetica", Font.BOLD, 30));
		Shop.setForeground(Color.WHITE);
		
		JLabel POS = new JLabel("Point-Of-Sales");
		POS.setBounds(145, 50, 300, 100);
		POS.setFont(new Font("Helvetica", Font.BOLD, 26));
		POS.setForeground(Color.WHITE);
		
		Enter.setText("Enter");
		Enter.setBounds(90, 150, 200, 35);
		Enter.setFont(new Font("Helvetica", Font.BOLD, 22));
		Enter.setForeground(Color.white);
		Enter.setBackground(Color.decode("#424242"));
		Enter.setOpaque(true);
		Enter.setBorder(BorderFactory.createLineBorder(Color.white, 2));
		Enter.setFocusable(false);
		Enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Login Main = new Login();
				Main.Login_Design();
				dispose();
			}
			
		});
		Enter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Enter.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	Enter.setBackground(Color.decode("#a3a098"));
		    	Enter.setFont(new Font("Helvetica", Font.BOLD, 26));
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	Enter.setBackground(Color.decode("#424242"));
		    	Enter.setFont(new Font("Helvetica", Font.BOLD, 22));
		    }
		});
		
		add(POS);
		add(Shop);
		add(Image);
		add(Enter);
		setLayout(null);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
