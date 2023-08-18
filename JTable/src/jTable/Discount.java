package jTable;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Discount extends JFrame{
	ImageIcon Logo = new ImageIcon(new ImageIcon("Logo.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
	Toolkit ToolK = getToolkit();
	Dimension Size = ToolK.getScreenSize();
	
	private POS main;
	
	public Discount(POS main) {
        this.main = main;
    }
	
	JButton OK;
	JRadioButton[] RadButt = new JRadioButton[5];
	String[] Rad = {
		"None (0%)",
		"Student (3%)",
		"Regular Customer (25%)",
		"Senior / PWD (20%)",
		"Employee (15%)",
	};
	ButtonGroup ButtGroup = new ButtonGroup();
	
	public void Discount_Design() {
		setSize(500, 300);
		setLocation(Size.width/2 - getWidth()/2, Size.height/2 - getHeight()/2);
		getContentPane().setBackground(Color.black);
		setIconImage(Logo.getImage());
		setResizable(false);
		setUndecorated(true);
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        main.setEnabled(true);
		    }
		});
		
		JLabel DiscTxT = new JLabel("DISCOUNTS");
		DiscTxT.setFont(new Font("Helvetica", Font.BOLD, 30));
		DiscTxT.setForeground(Color.white);
		DiscTxT.setHorizontalAlignment(SwingConstants.CENTER);
		DiscTxT.setOpaque(false);
		DiscTxT.setBackground(null);
		DiscTxT.setBounds(4, 0, 470, 53);
		
		JLabel Header = new JLabel();
		Header.setOpaque(true);
		Header.setBackground(Color.decode("#292929"));
		Header.setBounds(0, 0, 496, 51);
		
		JLabel Container = new JLabel();
		Container.setOpaque(true);
		Container.setBackground(Color.decode("#6e6e6e"));
		Container.setBounds(4, 4, 492, 292);
		
		int x = 20, y = 70;
		int width = 250, height = 40;
		int gap = 15;

		for (int i = 0; i < RadButt.length; i++) {
		    RadButt[i] = new JRadioButton(Rad[i]);
		    RadButt[i].setFont(new Font("Helvetica", Font.BOLD, 16));
		    RadButt[i].setForeground(Color.white);
		    RadButt[i].setFocusable(false);
		    RadButt[i].setOpaque(false);
		    RadButt[i].setBackground(null);
		    if (i == 4) {
		        RadButt[i].setBounds(((getWidth() - width) / 2) + 35, y + 2 * (height + gap), width, height);
		    } else {
		        RadButt[i].setBounds(x + ((i % 2) * (width + gap)), y + ((i / 2) * (height + gap)), width, height);
		    }
		    ButtGroup.add(RadButt[i]);
		    Container.add(RadButt[i]);
		}	
		
		RadButt[0].setSelected(true);
		
		OK = new JButton("OK");
		OK.setBounds(140, 235, 200, 35);
		OK.setFont(new Font("Helvetica", Font.BOLD, 12));
		OK.setForeground(Color.white);
		OK.setBackground(Color.decode("#228b22"));
		OK.setOpaque(true);
		OK.setBorder(null);
		OK.setFocusable(false);
		OK.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				main.updateTotal();
				main.setEnabled(true);
				setVisible(false);
			}
			
		});
		
		OK.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		OK.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	OK.setBackground(Color.decode("#02bf34"));
		    	OK.setFont(new Font("Helvetica", Font.BOLD, 16));
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	OK.setBackground(Color.decode("#228b22"));
		    	OK.setFont(new Font("Helvetica", Font.BOLD, 12));
		    }
		});
		
		add(Container);
		Container.add(OK);
		Container.add(Header);
		Header.add(DiscTxT);
		setLayout(null);
		setVisible(true);
	}
}
