package jTable;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class Payment extends JFrame {
	ImageIcon Logo = new ImageIcon(new ImageIcon("Logo.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
	Toolkit ToolK = getToolkit();
	Dimension Size = ToolK.getScreenSize();
	JButton[] Butt = new JButton[2];
	JTextField Payment = new JTextField();
	String[] ButtText = {
			"Cancel",
			"Pay"
	};
	
	private POS main;
	
	public Payment(POS main) {
        this.main = main;
    }
	
	private Discount discount;
	
	public void Payment_Design() {
		setSize(500, 230);
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
		
		JLabel Header = new JLabel();
		Header.setOpaque(true);
		Header.setBackground(Color.decode("#292929"));
		Header.setBounds(0, 0, 496, 51);
		
		JLabel Container = new JLabel();
		Container.setOpaque(true);
		Container.setBackground(Color.decode("#6e6e6e"));
		Container.setBounds(4, 4, 492, 222);
		
		JLabel PayTxT = new JLabel("PAYMENT");
		PayTxT.setFont(new Font("Helvetica", Font.BOLD, 30));
		PayTxT.setForeground(Color.white);
		PayTxT.setHorizontalAlignment(SwingConstants.CENTER);
		PayTxT.setOpaque(false);
		PayTxT.setBackground(null);
		PayTxT.setBounds(4, 0, 470, 53);
		
		JLabel PayEntTxT = new JLabel("Enter Payment: ");
		PayEntTxT.setFont(new Font("Helvetica", Font.BOLD, 20));
		PayEntTxT.setForeground(Color.white);
		PayEntTxT.setOpaque(false);
		PayEntTxT.setBackground(null);
		PayEntTxT.setBounds(17, 70, 470, 53);
		
		Payment.setFont(new Font("Helvetica", Font.BOLD, 20));
		Payment.setForeground(Color.white);
		Payment.setHorizontalAlignment(SwingConstants.LEFT);
		Payment.setCaretColor(Color.white);
		Payment.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
		Payment.setOpaque(false);
		Payment.setBackground(null);
		Payment.setBounds(172, 82, 300, 30);
		
		int x = 20;
		for (int i = 0; i < Butt.length; i++) {
			int index = i;
			Butt[i] = new JButton(ButtText[i]);
			Butt[i].setBounds(x, 150, 200, 50);
			Butt[i].setFocusable(false);
			Butt[i].setBorder(null);
			Butt[i].setFont(new Font("Helvetica", Font.BOLD, 20));
			Butt[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			if (index == 0) {
				Butt[index].setBackground(Color.decode("#e34139"));
				Butt[i].addMouseListener(new java.awt.event.MouseAdapter() {
		            public void mouseEntered(java.awt.event.MouseEvent evt) {
		            	Butt[index].setBackground(Color.decode("#d68383"));
		            	Butt[index].setFont(new Font("Helvetica", Font.BOLD, 22));
		            }
		            public void mouseExited(java.awt.event.MouseEvent evt) {
		            	Butt[index].setBackground(Color.decode("#e34139"));
		            	Butt[index].setFont(new Font("Helvetica", Font.BOLD, 20));
		            }
				});
			} else if (index == 1){
				Butt[index].setBackground(Color.decode("#7bb332"));
				Butt[i].addMouseListener(new java.awt.event.MouseAdapter() {
		            public void mouseEntered(java.awt.event.MouseEvent evt) {
		            	Butt[index].setBackground(Color.decode("#b2d683"));
		            	Butt[index].setFont(new Font("Helvetica", Font.BOLD, 22));
		            }
		            public void mouseExited(java.awt.event.MouseEvent evt) {
		            	Butt[index].setBackground(Color.decode("#7bb332"));
		            	Butt[index].setFont(new Font("Helvetica", Font.BOLD, 20));
		            }
				});
			}
			Container.add(Butt[i]);
			x += 250;
		}
		
		Butt[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int Conf = JOptionPane.showConfirmDialog(null, "Cancel Payment and go back to the POS?", "Cancel", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (Conf == JOptionPane.YES_OPTION) {
					Payment.setText("");
					main.setEnabled(true);
	            	setVisible(false);
				}
			}
		});
		
		Butt[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (Payment.getText().matches("^(0|[1-9]\\d*)$")) {
					int Conf = JOptionPane.showConfirmDialog(null, "Pay items for P" + Payment.getText(), "Pay", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (Conf == JOptionPane.YES_OPTION) {
						double Calcu = Double.parseDouble(Payment.getText()) - Double.parseDouble(main.Total.getText());
						if (Calcu >= 0) {
							JDialog Receipt = new JDialog();
							JPanel ReceiptPanel = new JPanel();
							JButton Ok = new JButton();

							JLabel Title = new JLabel(), Dash = new JLabel(), SubTitle = new JLabel(), OrderRec = new JLabel(), Dash2 = new JLabel(), Dash3 = new JLabel();

							JLabel[] Head = new JLabel[5];
							String[] Header = {"Name", "Size", "Qty", "Price", "Total"};
							String[] CostTotal = {"Total", "Cash", "Discount", "Change"};

							// Add the panel to the JScrollPane
							JScrollPane Scroll = new JScrollPane(ReceiptPanel);
							JScrollBar ScrollBar = Scroll.getVerticalScrollBar();
			    			
			    			Receipt.setSize(350, 500);
			    			Receipt.setLocationRelativeTo(null);
			    			Receipt.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
			    			Receipt.getRootPane().setBorder(BorderFactory.createLineBorder(Color.black, 1));
			    			Receipt.setUndecorated(true);
			    			Receipt.setResizable(false);
			    			
			    			Title.setBounds(78, 10, 300, 30);
			    			Title.setText("Shop Receipt");
			    			Title.setFont(new Font("Georgia", Font.BOLD, 28));
			    			Title.setOpaque(false);
			    			
			    			SubTitle.setBounds(70, 35, 300, 30);
			    			SubTitle.setText("Somewhere St, Capas, Tarlac");
			    			SubTitle.setFont(new Font("Helvetica", Font.BOLD, 14));
			    			SubTitle.setOpaque(false);
			    			
			    			JLabel Date = new JLabel();
			    			Date.setBounds(40, 55, 300, 30);
			    			Date.setText(main.Date.getText());
			    			Date.setFont(new Font("Helvetica", Font.BOLD, 14));
			    			Date.setOpaque(false);
			    			
			    			JLabel Time = new JLabel();
			    			Time.setBounds(200, 55, 300, 30);
			    			Time.setText(main.Time.getText());
			    			Time.setFont(new Font("Helvetica", Font.BOLD, 14));
			    			Time.setOpaque(false);
			    			
			    			OrderRec.setBounds(95, 75, 300, 30);
			    			OrderRec.setText("ORDER RECEIPT");
			    			OrderRec.setFont(new Font("Helvetica", Font.BOLD, 18));
			    			OrderRec.setOpaque(false);
			    			
			    			Dash.setBounds(30, 95, 300, 30);
			    			Dash.setText("----------------------------------");
			    			Dash.setFont(new Font("Georgia", Font.BOLD, 20));
			    			Dash.setOpaque(false);
			    			
			    			int x = 10;
			    			for (int i = 0; i < Header.length; i++) {
			    				Head[i] = new JLabel(Header[i]);
			    				Head[i].setFont(new Font("Helvetica", Font.BOLD, 14));
			    				Head[i].setForeground(Color.black);
			    				Head[i].setOpaque(false);
			    				Head[i].setBackground(null);
			    				if (i == 0) {
			    					Head[i].setBounds(x, 105, 470, 53);
			    				} else if (i == 1) {
			    					Head[i].setBounds(x + 25, 105, 470, 53);
			    				} else if (i == 2){
			    					Head[i].setBounds(x + 20, 105, 470, 53);
			    				} else if (i == 3){
			    					Head[i].setBounds(x + 10, 105, 470, 53);
			    				} else if (i == 4){
			    					Head[i].setBounds(x + 15, 105, 470, 53);
			    				}
			    				
			    				x += 65;
			    				ReceiptPanel.add(Head[i]);
			    			}
			    			
			    			int RowPOS = main.Table.getRowCount();
			    			int y = 140;
			    			JLabel[] Name = new JLabel[RowPOS];
			    			JLabel[] Size = new JLabel[RowPOS];
			    			JLabel[] Qty = new JLabel[RowPOS];
			    			JLabel[] Price = new JLabel[RowPOS];
			    			JLabel[] Total = new JLabel[RowPOS];
			    			for (int i = 0; i < RowPOS; i++) {
			    				String RecName = (String) main.Table.getValueAt(i, 1);
			    				Name[i] = new JLabel(RecName);
			    				Name[i].setFont(new Font("Helvetica", Font.BOLD, 12));
			    				Name[i].setForeground(Color.black);
			    				Name[i].setOpaque(false);
			    				Name[i].setBackground(null);
			    				Name[i].setBounds(10, y, 470, 53);
			    				
			    				String RecSize = (String) main.Table.getValueAt(i, 2);
			    				Size[i] = new JLabel(RecSize);
			    				Size[i].setFont(new Font("Helvetica", Font.BOLD, 12));
			    				Size[i].setForeground(Color.black);
			    				Size[i].setOpaque(false);
			    				Size[i].setBackground(null);
			    				Size[i].setBounds(100, y, 470, 53);
			    				
			    				String RecQty = (String) main.Table.getValueAt(i, 4);
			    				Qty[i] = new JLabel(RecQty);
			    				Qty[i].setFont(new Font("Helvetica", Font.BOLD, 12));
			    				Qty[i].setForeground(Color.black);
			    				Qty[i].setOpaque(false);
			    				Qty[i].setBackground(null);
			    				Qty[i].setBounds(165, y, 470, 53);
			    				
			    				String RecPrice = (String) main.Table.getValueAt(i, 3);
			    				Price[i] = new JLabel(RecPrice);
			    				Price[i].setFont(new Font("Helvetica", Font.BOLD, 12));
			    				Price[i].setForeground(Color.black);
			    				Price[i].setOpaque(false);
			    				Price[i].setBackground(null);
			    				Price[i].setBounds(210, y, 470, 53);
			    				
			    				String RecTotal = (String) main.Table.getValueAt(i, 5);
			    				Total[i] = new JLabel(RecTotal);
			    				Total[i].setFont(new Font("Helvetica", Font.BOLD, 12));
			    				Total[i].setForeground(Color.black);
			    				Total[i].setHorizontalAlignment(SwingConstants.RIGHT);
			    				Total[i].setOpaque(false);
			    				Total[i].setBackground(null);
			    				Total[i].setBounds(120, y, 200, 53);
			    				
			    				y += 25;
			    				ReceiptPanel.add(Name[i]);
			    				ReceiptPanel.add(Size[i]);
			    				ReceiptPanel.add(Qty[i]);
			    				ReceiptPanel.add(Price[i]);
			    				ReceiptPanel.add(Total[i]);
			    			}
			    			
			    			Dash2.setBounds(30, y+15, 300, 30);
			    			Dash2.setText("----------------------------------");
			    			Dash2.setFont(new Font("Georgia", Font.BOLD, 20));
			    			Dash2.setOpaque(false);
			    			
			    			y += 40;
			    			
	    					JLabel[] LabelTotal = new JLabel[CostTotal.length];
			    			JLabel[] Price2 = new JLabel[CostTotal.length];
			    			String[] StrPrice = {
			    					main.Total.getText(),
			    					String.format("%.2f", Double.parseDouble(Payment.getText())),
			    					String.format("%.2f", main.discounted),
			    					String.format("%.2f", (Double.parseDouble(Payment.getText()) - (Double.parseDouble(main.Total.getText()))))
			    			};
			    			
			    			JLabel[] Dots2 = new JLabel[CostTotal.length];
			    			for (int i = 0; i < CostTotal.length; i++) {
			    				LabelTotal[i] = new JLabel(CostTotal[i]);
			    				if (i == 0) {
			    					LabelTotal[i].setBounds(10, y-3, 150, 30);
			    					LabelTotal[i].setFont(new Font("Helvetica", Font.BOLD, 16));
			    				} else {
				    			    LabelTotal[i].setBounds(35, y-3, 150, 30);
				    			    LabelTotal[i].setFont(new Font("Helvetica", Font.BOLD, 14));
			    				}
			    			    LabelTotal[i].setOpaque(false);
			    			    ReceiptPanel.add(LabelTotal[i]);
			    			    
			    			    Price2[i] = new JLabel();
			    			    Price2[i].setHorizontalAlignment(SwingConstants.RIGHT);
			    			    Price2[i].setText(StrPrice[i]);
			    			    Price2[i].setBounds(240, y, 80, 30);
			    			    Price2[i].setOpaque(false);
			    			    Price2[i].setFont(new Font("Arial", Font.BOLD, 14));
			    			    ReceiptPanel.add(Price2[i]);
			    			    
			    				Dots2[i] = new JLabel("···························· P ");
				    			Dots2[i].setBounds(120, y, 150, 30);
				    			Dots2[i].setOpaque(false);
				    			Dots2[i].setFont(new Font("Helvetica", Font.BOLD, 12));
				    			ReceiptPanel.add(Dots2[i]);
				    			
				    			y += 30;
			    			}
			    			
			    			y+=5;
			    			Dash3.setBounds(30, y, 300, 30);
			    			Dash3.setText("----------------------------------");
			    			Dash3.setFont(new Font("Georgia", Font.BOLD, 20));
			    			Dash3.setOpaque(false);
			    			
			    			y+=35;
			    			JLabel SubTitle2 = new JLabel();
			    			SubTitle2.setBounds(70, y, 300, 30);
			    			SubTitle2.setText("Thank you for purchasing!");
			    			SubTitle2.setFont(new Font("Helvetica", Font.BOLD, 14));
			    			SubTitle2.setOpaque(false);
			    			
			    			ReceiptPanel.setBackground(Color.white);
							ReceiptPanel.setLayout(null);
	
							y+=40;
			    			Ok.setText("OK");
			    			Ok.setBounds(120, y, 100, 30);
			    			Ok.setFont(new Font("Helvetica", Font.BOLD, 20));
			    			Ok.setForeground(Color.black);
			    			Ok.setBackground(Color.white);
			    			Ok.setOpaque(true);
			    			Ok.setBorder(null);
			    			Ok.setFocusable(false);
			    			Ok.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			    			Ok.addActionListener(new ActionListener(){
			    			    @Override
			    			    public void actionPerformed(ActionEvent e) {
			    			    	JOptionPane.showMessageDialog(null, "Receipt has been printed", "Succeful Transaction", JOptionPane.INFORMATION_MESSAGE);
			    			    	main.posModel.setRowCount(0);
					            	main.Total.setText("0.00");
					            	main.InvoiceN += 1;
					            	main.InvoiceNum.setText(Integer.toString(main.InvoiceN));
			    			    	main.setEnabled(true);
			    			    	setVisible(false);
			    			    	Receipt.dispose();
			    			    }
			    			});
			    			
			    			ReceiptPanel.setPreferredSize(new Dimension(320, y+50));
			    			

			    			// Set the preferred size of the JScrollPane
			    			Scroll.setPreferredSize(new Dimension(350, 350));
			    			Scroll.setViewportView(ReceiptPanel);
			    			Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			    			Scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			    			ScrollBar.setUnitIncrement(20);
			    			ScrollBar.setBlockIncrement(100);
			    			
			    			ReceiptPanel.add(Date);
			    			ReceiptPanel.add(Time);
			    			ReceiptPanel.add(Ok);
			    			ReceiptPanel.add(Title);
			    			ReceiptPanel.add(SubTitle);
			    			ReceiptPanel.add(OrderRec);
			    			ReceiptPanel.add(Dash);
			    			ReceiptPanel.add(Dash2);
			    			ReceiptPanel.add(Dash3);
			    			ReceiptPanel.add(Ok);
			    			ReceiptPanel.add(SubTitle2);
			    			Receipt.add(Scroll);

			    			Receipt.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "Insufficient Payment", "Insufficient Money", JOptionPane.WARNING_MESSAGE); 
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please enter a valid payment", "Invalid Input", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		add(Container);
		Container.add(Header);
		Container.add(Payment);
		Container.add(PayEntTxT);
		Header.add(PayTxT);
		setLayout(null);
		setVisible(true);
	}
}
