package jTable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Home extends JFrame{
	
	ImageIcon Logo = new ImageIcon(new ImageIcon("Logo.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
	ImageIcon Inventory = new ImageIcon(new ImageIcon("Inventory2.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
	ImageIcon POS = new ImageIcon(new ImageIcon("POS3.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
	ImageIcon Inventory2 = new ImageIcon(new ImageIcon("Inventory2.png").getImage().getScaledInstance(165, 165, Image.SCALE_SMOOTH));
	ImageIcon POS2 = new ImageIcon(new ImageIcon("POS3.png").getImage().getScaledInstance(165, 165, Image.SCALE_SMOOTH));
	ImageIcon LogoutImg = new ImageIcon(new ImageIcon("logout-64.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
	ImageIcon LogoutImg2 = new ImageIcon(new ImageIcon("logout-64.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
	
	Toolkit ToolK = getToolkit();
	Dimension Size = ToolK.getScreenSize();
	
	JLabel Dash = new JLabel("DASHBOARD");
	JLabel[] Label = new JLabel[2];
	JButton[] Butt = new JButton[2];
	JButton Logout = new JButton();
	
	void Home_Design(){
		setSize(460, 320);
		setLocation(Size.width/2 - getWidth()/2, Size.height/2 - getHeight()/2);
		getContentPane().setBackground(Color.decode("#424242"));
		setResizable(false);
		setTitle("Dashboard");
		setIconImage(Logo.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dash.setBounds(122, 8, 250, 50);
		Dash.setFont(new Font("Helvetica", Font.BOLD, 30));
		Dash.setForeground(Color.white);
		
		int x = 40;
		for (int i = 0; i < Label.length; i++) {
			Label[i] = new JLabel();
			Label[i].setFont(new Font("Helvetica", Font.BOLD, 20));
			Label[i].setForeground(Color.white);
			Label[i].setBounds(x+10, 170, 150, 150);
			
			int index = i;
			Butt[i] = new JButton();
			Butt[i].setBounds(x, 70, 150, 150);
			Butt[i].setFocusable(false);
			Butt[i].setOpaque(false);
			Butt[i].setForeground(null);
			Butt[i].setBackground(null);
			Butt[i].setBorder(null);
			Butt[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			x += 200;
			add(Butt[i]);
			add(Label[i]);
		}
		
		Label[0].setText("Point-Of-Sale");
		Label[1].setText("   Inventory");
		Butt[0].setIcon(POS);
		Butt[1].setIcon(Inventory);
		Butt[0].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	Butt[0].setIcon(POS2);
            	Butt[0].setBounds(33, 63, 165, 165);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	Butt[0].setIcon(POS);
            	Butt[0].setBounds(40, 70, 150, 150);
            }
		});
		
		Butt[1].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	Butt[1].setIcon(Inventory2);
            	Butt[1].setBounds(233, 63, 165, 165);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	Butt[1].setIcon(Inventory);
            	Butt[1].setBounds(240, 70, 150, 150);
            }
		});
		
		Butt[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int Conf = JOptionPane.showConfirmDialog(null, "Proceed to Point-Of-Sale System?", "POS", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(Conf == JOptionPane.YES_OPTION) {
					POS Main = new POS();
					Main.setVisible(true);
					dispose();
				}
			}
		});
		
		Butt[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int Conf = JOptionPane.showConfirmDialog(null, "Proceed to the inventory?", "Inventory", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(Conf == JOptionPane.YES_OPTION) {
					Table Main = new Table();
					Main.Table_Design();
					dispose();
				}
			}
		});
		
		//TOOLTIP EDITS
		UIManager.put("ToolTip.background", Color.white);
		UIManager.put("ToolTip.foreground", Color.black);
		UIManager.put("ToolTip.border", BorderFactory.createLineBorder(Color.black, 1));
		UIManager.put("ToolTip.font", new Font("Arial", Font.BOLD, 16));
	    int initialDelay = ToolTipManager.sharedInstance().getInitialDelay();
	    ToolTipManager.sharedInstance().setInitialDelay(10);
			    
		Logout = new JButton();
		Logout.setIcon(LogoutImg);
		Logout.setBackground(null);
		Logout.setOpaque(false);
		Logout.setBounds(395, 10, 45, 45);
		Logout.setToolTipText("Log Out");
		Logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Logout.setFocusable(false);
		Logout.setBorder(null);
		Logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int Conf = JOptionPane.showConfirmDialog(null, "Log out of session?", "Log Out", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (Conf == JOptionPane.YES_OPTION) {
					Login Main = new Login();
					Main.Login_Design();
					dispose();
				}
			}			
		});
		Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	Logout.setIcon(LogoutImg2);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	Logout.setIcon(LogoutImg);
            }
		});
		
		add(Dash);
		add(Logout);
		setLayout(null);
		setVisible(true);
	}
}
