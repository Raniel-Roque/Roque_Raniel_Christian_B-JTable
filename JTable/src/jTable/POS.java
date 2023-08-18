package jTable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class POS extends JFrame{
	
	public void updateTotal() {
	    double TotalAdd = 0, Discount = 0;
	    for (int i = 0; i < Table.getRowCount(); i++) {
	        double Add = Double.parseDouble((String) Table.getValueAt(i, 5));
	        TotalAdd += Add;
	    }    
	    JRadioButton selectedButton = null;

	    for (int i = 0; i < discount.RadButt.length; i++) {
	        if (discount.RadButt[i].isSelected()) {
	            selectedButton = discount.RadButt[i];
	            break;
	        }
	    }

	    double discountPercentage = 0.0;
	    if (selectedButton == discount.RadButt[1]) {
	        discountPercentage = 0.03;
	    } else if (selectedButton == discount.RadButt[2]) {
	        discountPercentage = 0.25;
	    } else if (selectedButton == discount.RadButt[3]) {
	        discountPercentage = 0.2;
	    } else if (selectedButton == discount.RadButt[4]) {
	        discountPercentage = 0.15;
	    }

	    discounted = TotalAdd * discountPercentage;
	    double discountedTotal = TotalAdd - (TotalAdd * discountPercentage);
	    Total.setText(String.format("%.2f", discountedTotal));
	}
	
	ImageIcon Logo = new ImageIcon(new ImageIcon("Logo.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
	ImageIcon HomeImg = new ImageIcon(new ImageIcon("Home.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
	ImageIcon HomeImg2 = new ImageIcon(new ImageIcon("Home.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
	ImageIcon SearchImg = new ImageIcon(new ImageIcon("Search.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
	ImageIcon SearchImg2 = new ImageIcon(new ImageIcon("Search.png").getImage().getScaledInstance(47, 47, Image.SCALE_SMOOTH));
	ImageIcon UserImg = new ImageIcon(new ImageIcon("User.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
	
	Toolkit ToolK = getToolkit();
	Dimension Size = ToolK.getScreenSize();
	
	//HEADER NAMES
	String[] Row = new String [] {
		"Item Code",
		"Item Name",
		"Size",
		"Price (₱)",
		"Quantity",
		"Total"
	};
	
	//EXTRA BLANK LINES SO IT CENTERS THE WHOLE THING IDK WHY BUT IF IT WORKS IT WORKS 
	String[][] Column = new String [][] {};
	
	//TABLEMODEL THAT IS ALSO DISABLED FROM EDITING INSIDE THE TABLE
	DefaultTableModel Model = new DefaultTableModel(Column, Row){
	    @Override
	    public boolean isCellEditable(int row, int column) {
	        return false;
	    }
	};
	
	//PANEL
	JTable Table = new JTable(Model);
	JScrollPane Scroll = new JScrollPane(Table);
	JPanel Panel = new JPanel(new BorderLayout());
	Vector<String> r = new Vector<String>();
	TableRowSorter<TableModel> sort = new TableRowSorter<>(Table.getModel());
		
	JButton Home, Search;
	JButton[] Butt = new JButton[4];
	String[] ButtText = {
			"Remove Item",
			"Discount",
			"Payment",
			"Cancel"
	};
	JTextField QuantiIn;
	JLabel InvoiceNum, Total, Date, Time;
	private InvTable inventory;
	private Discount discount;
	private Payment payment;
	double discounted = 0.00;
	DefaultTableModel posModel = (DefaultTableModel) Table.getModel();
	
	int InvoiceN = 1000000;
	public POS(){
		setSize(900, 650);
		setLocation(Size.width/2 - getWidth()/2, Size.height/2 - getHeight()/2);
		getContentPane().setBackground(Color.decode("#424242"));
		setResizable(false);
		setTitle("Point-Of-Sale");
		setIconImage(Logo.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel Header = new JLabel();
		Header.setOpaque(true);
		Header.setBackground(Color.decode("#292929"));
		Header.setBounds(0, 0, 900, 55);
		
		JLabel Header2 = new JLabel();
		Header2.setOpaque(true);
		Header2.setBackground(Color.decode("#292929"));
		Header2.setBounds(0, 0, 540, 53);
		
		JLabel Header3 = new JLabel();
		Header3.setOpaque(true);
		Header3.setBackground(Color.decode("#292929"));
		Header3.setBounds(0, 0, 270, 53);
		
		JLabel Header4 = new JLabel();
		Header4.setOpaque(true);
		Header4.setBackground(Color.decode("#292929"));
		Header4.setBounds(0, 0, 270, 53);
		
		JLabel Invoice = new JLabel("Invoice number: ");
		Invoice.setFont(new Font("Helvetica", Font.PLAIN, 18));
		Invoice.setForeground(Color.white);
		Invoice.setOpaque(false);
		Invoice.setBackground(null);
		Invoice.setBounds(34, 0, 270, 53);
		
		InvoiceNum = new JLabel(Integer.toString(InvoiceN));
		InvoiceNum.setFont(new Font("Helvetica", Font.BOLD, 18));
		InvoiceNum.setForeground(Color.white);
		InvoiceNum.setOpaque(false);
		InvoiceNum.setBackground(null);
		InvoiceNum.setBounds(164, 0, 270, 53);
		
		JLabel Container4 = new JLabel();
		Container4.setOpaque(true);
		Container4.setBackground(Color.decode("#6e6e6e"));
		Container4.setBounds(5, 58, 260, 100);
		
		JLabel TotalTxT = new JLabel("TOTAL");
		TotalTxT.setFont(new Font("Helvetica", Font.BOLD, 30));
		TotalTxT.setForeground(Color.white);
		TotalTxT.setHorizontalAlignment(SwingConstants.CENTER);
		TotalTxT.setOpaque(false);
		TotalTxT.setBackground(null);
		TotalTxT.setBounds(5, 0, 260, 53);
		
		Total = new JLabel("0.00");
		Total.setFont(new Font("Helvetica", Font.BOLD, 35));
		Total.setHorizontalAlignment(SwingConstants.CENTER);
		Total.setForeground(Color.white);
		Total.setOpaque(false);
		Total.setBackground(null);
		Total.setBounds(0, 20, 260, 50);
		
		//TOOLTIP EDITS
		UIManager.put("ToolTip.background", Color.white);
		UIManager.put("ToolTip.foreground", Color.black);
		UIManager.put("ToolTip.border", BorderFactory.createLineBorder(Color.black, 1));
		UIManager.put("ToolTip.font", new Font("Helvetica", Font.BOLD, 14));
	    int initialDelay = ToolTipManager.sharedInstance().getInitialDelay();
	    ToolTipManager.sharedInstance().setInitialDelay(10);
			  
		Home = new JButton();
		Home.setIcon(HomeImg);
		Home.setBackground(null);
		Home.setOpaque(false);
		Home.setBounds(830, 5, 45, 45);
		Home.setToolTipText("Go back to the home screen?");
		Home.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Home.setFocusable(false);
		Home.setBorder(null);
		Home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int Conf = JOptionPane.showConfirmDialog(null, "Go back home?", "Go back?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (Conf == JOptionPane.YES_OPTION) {
					Home Main = new Home();
					Main.Home_Design();
					dispose();
				}
			}			
		});
		Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	Home.setIcon(HomeImg2);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	Home.setIcon(HomeImg);
            }
		});

		JLabel QuantiSearch = new JLabel("QUANTITY: ");
		QuantiSearch.setBounds(15, 10, 200, 30);
		QuantiSearch.setFont(new Font("Helvetica", Font.BOLD, 16));
		QuantiSearch.setForeground(Color.white);
		
		QuantiIn = new JTextField();
		QuantiIn.setBounds(110, 10, 200, 32);
		QuantiIn.setFont(new Font("Helvetica", Font.BOLD, 16));
		QuantiIn.setForeground(Color.white);
		QuantiIn.setOpaque(false);
		QuantiIn.setBackground(null);
		QuantiIn.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		QuantiIn.setCaretColor(Color.white);
		QuantiIn.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
		
		Search = new JButton();
		Search.setIcon(SearchImg);
		Search.setBounds(488, 0, 50, 50);
		Search.setOpaque(false);
		Search.setBackground(null);
		Search.setFocusable(false);
		Search.setBorder(null);
		Search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Search.setToolTipText("Search Item");
		Search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	Search.setIcon(SearchImg2);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	Search.setIcon(SearchImg);
            }
		});
		
		inventory = new InvTable(this);
        inventory.InvTable_Design();
        inventory.setVisible(false);
        
        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	if(Double.parseDouble(Total.getText()) == 0.00) {
            		discount.RadButt[0].setSelected(true);
            	}
            	
            	if(QuantiIn.getText().isEmpty()) {
            	    JOptionPane.showMessageDialog(null, "Input a quantity first", "No quantity inputted", JOptionPane.WARNING_MESSAGE);
            	} else if (QuantiIn.getText().matches("^(0|[1-9]\\d*)$")) {
            	    inventory.setVisible(true);
            	    setEnabled(false);
            	} else {
            	    JOptionPane.showMessageDialog(null, "You can only use numbers equal or more than 0", "Invalid Quantity", JOptionPane.WARNING_MESSAGE);
            	}
            }
        });
		
		JLabel Icon = new JLabel();
		Icon.setIcon(UserImg);
		Icon.setBounds(15, 5, 45, 45);
		
		JLabel User = new JLabel("   Raniel Roque");
		User.setBounds(60, 5, 200, 45);
		User.setBorder(new LineBorder(Color.white, 5, true) {
		    @Override
		    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		        Graphics2D g2d = (Graphics2D) g.create();
		        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        Shape shape = new RoundRectangle2D.Double(x+10, y+5, width-50, height-10, 30, 30);
		        g2d.setStroke(new BasicStroke(getThickness()));
		        g2d.setColor(getLineColor());
		        g2d.draw(shape);
		        g2d.dispose();
		    }
		});
		User.setFont(new Font("Helvetica", Font.BOLD, 20));
		User.setForeground(Color.white);
		
		JLabel Dot = new JLabel("|");
		Dot.setBounds(240, 2, 200, 45);
		Dot.setFont(new Font("Helvetica", Font.BOLD, 30));
		Dot.setForeground(Color.white);
		
		JLabel POS = new JLabel("Point-of-Sale");
		POS.setBounds(270, 5, 200, 45);
		POS.setFont(new Font("Helvetica", Font.BOLD, 24));
		POS.setForeground(Color.white);
		
		JLabel Dot2 = new JLabel("|");
		Dot2.setBounds(440, 2, 200, 45);
		Dot2.setFont(new Font("Helvetica", Font.BOLD, 30));
		Dot2.setForeground(Color.white);
		
		Date = new JLabel();
		Date.setBounds(295, 5, 500, 45);
		Date.setFont(new Font("Helvetica", Font.BOLD, 18));
		Date.setForeground(Color.white);
		Date.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel Dot3 = new JLabel("|");
		Dot3.setBounds(645, 2, 200, 45);
		Dot3.setFont(new Font("Helvetica", Font.BOLD, 30));
		Dot3.setForeground(Color.white);
		
		Time = new JLabel();
        Time.setBounds(675, 5, 500, 45);
        Time.setFont(new Font("Helvetica", Font.BOLD, 18));
        Time.setForeground(Color.white);
        
        Timer timer = new Timer(1000, e -> {
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();

            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
            timeFormat.setDateFormatSymbols(new DateFormatSymbols(Locale.US) {
                @Override
                public String[] getAmPmStrings() {
                    return new String[] {"AM", "PM"};
                }
            });
            String time = timeFormat.format(now);
            Time.setText(time);

            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM dd, yyyy");
            dateFormat.setDateFormatSymbols(new DateFormatSymbols(Locale.US) {
                @Override
                public String[] getMonths() {
                    return new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
                }

                @Override
                public String[] getShortWeekdays() {
                    return new String[] {"", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
                }
            });
            String date = dateFormat.format(now);
            Date.setText(date);
        });
        timer.start();
        
        JLabel Dot4 = new JLabel("|");
        Dot4.setBounds(805, 2, 200, 45);
        Dot4.setFont(new Font("Helvetica", Font.BOLD, 30));
        Dot4.setForeground(Color.white);
        
		JLabel Container = new JLabel();
		Container.setOpaque(true);
		Container.setBackground(Color.decode("#4f4f4f"));
		Container.setBounds(25, 75, 540, 520);

		JLabel Container2 = new JLabel();
		Container2.setOpaque(true);
		Container2.setBackground(Color.decode("#4f4f4f"));
		Container2.setBounds(590, 75, 270, 165);
		
		JLabel Container3 = new JLabel();
		Container3.setOpaque(true);
		Container3.setBackground(Color.decode("#4f4f4f"));
		Container3.setBounds(590, 250, 270, 345);
		
		int y = 68;
		for (int i = 0; i < Butt.length; i++) {
			int index = i;
			Butt[i] = new JButton(ButtText[i]);
			Butt[i].setBounds(35, y, 200, 50);
			Butt[i].setFocusable(false);
			Butt[i].setBorder(null);
			Butt[i].setBackground(Color.decode("#adacac"));
			Butt[i].setFont(new Font("Helvetica", Font.BOLD, 20));
			Butt[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			if (index < 2) {
				Butt[i].addMouseListener(new java.awt.event.MouseAdapter() {
		            public void mouseEntered(java.awt.event.MouseEvent evt) {
		            	Butt[index].setBackground(Color.decode("#d1d1d1"));
		            	Butt[index].setFont(new Font("Helvetica", Font.BOLD, 22));
		            }
		            public void mouseExited(java.awt.event.MouseEvent evt) {
		            	Butt[index].setBackground(Color.decode("#adacac"));
		            	Butt[index].setFont(new Font("Helvetica", Font.BOLD, 20));
		            }
				});
			} else if (index == 2) {
				Butt[i].addMouseListener(new java.awt.event.MouseAdapter() {
		            public void mouseEntered(java.awt.event.MouseEvent evt) {
		            	Butt[index].setBackground(Color.decode("#b2d683"));
		            	Butt[index].setFont(new Font("Helvetica", Font.BOLD, 22));
		            }
		            public void mouseExited(java.awt.event.MouseEvent evt) {
		            	Butt[index].setBackground(Color.decode("#adacac"));
		            	Butt[index].setFont(new Font("Helvetica", Font.BOLD, 20));
		            }
				});
			} else {
				Butt[i].addMouseListener(new java.awt.event.MouseAdapter() {
		            public void mouseEntered(java.awt.event.MouseEvent evt) {
		            	Butt[index].setBackground(Color.decode("#d68383"));
		            	Butt[index].setFont(new Font("Helvetica", Font.BOLD, 22));
		            }
		            public void mouseExited(java.awt.event.MouseEvent evt) {
		            	Butt[index].setBackground(Color.decode("#adacac"));
		            	Butt[index].setFont(new Font("Helvetica", Font.BOLD, 20));
		            }
				});
			}
			
			y += 70;
			Container3.add(Butt[i]);
		}
		
		 Butt[0].addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Get the selected row in the POS table
	                int selectedRow = Table.getSelectedRow();
	                if (selectedRow >= 0) {
		            	int Conf = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + Table.getValueAt(selectedRow, 1), "Delete Item", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		            	if (Conf == JOptionPane.YES_OPTION) {
		            		// Get the ID of the item in the selected row
		                    String ID = (String) Table.getValueAt(selectedRow, 0);
		                    
		                    // Add back the deducted stock to the inventory table
		                    DefaultTableModel invModel = (DefaultTableModel) inventory.Table.getModel();
		                    int invRowIndex = -1;
		                    for (int i = 0; i < invModel.getRowCount(); i++) {
		                        if (invModel.getValueAt(i, 0).equals(ID)) {
		                            invRowIndex = i;
		                            break;
		                        }
		                    }
	                        
		                    if (invRowIndex >= 0) {
			                    int deductedStock = Integer.parseInt((String) Table.getValueAt(selectedRow, 4));
			                    int currentStock = Integer.parseInt(invModel.getValueAt(invRowIndex, 5).toString());
		                        invModel.setValueAt(Integer.toString(currentStock + deductedStock), invRowIndex, 5);
		                        
		                        if (Long.parseLong(invModel.getValueAt(invRowIndex, 5).toString()) > Long.parseLong(invModel.getValueAt(invRowIndex, 6).toString())) {
		                            invModel.setValueAt("High Stock", invRowIndex, 7);
		                        } else {
		                            invModel.setValueAt("Low Stock", invRowIndex, 7);
		                        }
		                        
		                    }
		                    
		                    // Remove the selected row from the POS table
		                    DefaultTableModel posModel = (DefaultTableModel) Table.getModel();
		                    posModel.removeRow(selectedRow);
		            	}
	                } else {
	                	JOptionPane.showMessageDialog(null, "Choose an item first", "Choose Item", JOptionPane.WARNING_MESSAGE);
	                }
	                updateTotal();
	            }
	        });
		 
		 discount = new Discount(this);
		 discount.Discount_Design();
		 discount.setVisible(false);
		 
		 Butt[1].addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	if(Double.parseDouble(Total.getText()) == 0.00) {
	            		discount.RadButt[0].setSelected(true);
	            	}
	            	
	            	discount.setVisible(true);
            	    setEnabled(false);
	            }
	        });
		 
		 payment = new Payment(this);
		 payment.Payment_Design();
		 payment.setVisible(false);
		 
		 Butt[2].addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	if(Double.parseDouble(Total.getText()) == 0.00) {
	            		discount.RadButt[0].setSelected(true);
	            	}
	            	
	            	if (Table.getRowCount() > 0) {
	            		payment.setVisible(true);
		            	setEnabled(false);
	            	} else {
	            		JOptionPane.showMessageDialog(null, "Add an Item first", "No Items", JOptionPane.WARNING_MESSAGE);
	            	}
	            }
	        });
		 
		 Butt[3].addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	int Conf = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel and clear the list?", "Cancel Order", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (Conf == JOptionPane.YES_OPTION) {
		            	// Loop through all rows in POS table
		            	for (int i = 0; i < posModel.getRowCount(); i++) {
		            	    // Get the ID of the item in the selected row
		            	    String ID = (String) posModel.getValueAt(i, 0);
		            	    
		            	    // Add back the deducted stock to the inventory table
		            	    DefaultTableModel invModel = (DefaultTableModel) inventory.Table.getModel();
		            	    int invRowIndex = -1;
		            	    for (int j = 0; j < invModel.getRowCount(); j++) {
		            	        if (invModel.getValueAt(j, 0).equals(ID)) {
		            	            invRowIndex = j;
		            	            break;
		            	        }
		            	    }
	
		            	    if (invRowIndex >= 0) {
		            	        int deductedStock = Integer.parseInt((String) posModel.getValueAt(i, 4));
		            	        int currentStock = Integer.parseInt(invModel.getValueAt(invRowIndex, 5).toString());
		            	        invModel.setValueAt(Integer.toString(currentStock + deductedStock), invRowIndex, 5);
	
		            	        if (Long.parseLong(invModel.getValueAt(invRowIndex, 5).toString()) > Long.parseLong(invModel.getValueAt(invRowIndex, 6).toString())) {
		            	            invModel.setValueAt("High Stock", invRowIndex, 7);
		            	        } else {
		            	            invModel.setValueAt("Low Stock", invRowIndex, 7);
		            	        }
	
		            	    }
	
		            	}
	
		            	// Remove all rows from POS table
		            	posModel.setRowCount(0);
		            	Total.setText("0.00");
		            	discount.RadButt[0].setSelected(true);
					}
	            }
	        });
        
		Table.getTableHeader().setEnabled(false); //DISABLES HEADER
		
		//CENTER DATA
		DefaultTableCellRenderer Center = new DefaultTableCellRenderer(); 
		Center.setHorizontalAlignment(JLabel.CENTER); 
		
		for (int i = 0; i < Row.length; i++) {
		    Table.getColumnModel().getColumn(i).setCellRenderer(Center);
		}
		
		/* ----------------FOR PRICE LEFT-----------------
		DefaultTableCellRenderer center = new DefaultTableCellRenderer(), right = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(JLabel.CENTER);
        right.setHorizontalAlignment(JLabel.RIGHT);
		        
		for (int i = 0; i < Row.length; i++) {
		    if (i != 3) {
		        Table.getColumnModel().getColumn(i).setCellRenderer(center);
		    } else {
		        Table.getColumnModel().getColumn(i).setCellRenderer(right);
		    }
		}
		*/

		Table.setRowHeight(22); //CHANGE TO CHANGE HOW THICK CELLS ARE
		
		//CHANGE HOW LONG THEY ARE
		TableColumnModel Colm = Table.getColumnModel();
		int[] Wid = {
			70, 150, 80, 90, 70, 100 //ONLY EDIT THIS 
		};
		
		for (int i = 0; i < Wid.length; i++) {
			Colm.getColumn(i).setPreferredWidth(Wid[i]);
		}
		
		//OTHERS
		Panel.setBounds(5, 60, 529, 453);
		Panel.add(Scroll);
		Table.setRowSorter(sort);
		
		Container.add(Panel);
		Container.add(Header2);
		Header2.add(QuantiSearch);
		Header2.add(QuantiIn);
		Header2.add(Search);
		
		Container2.add(Header3);
		Container2.add(Container4);
		Header3.add(TotalTxT);
		Container4.add(Total);
		
		add(Container3);
		Container3.add(Header4);
		Header4.add(Invoice);
		Header4.add(InvoiceNum);
		
		add(Header);
		Header.add(Time);
		Header.add(Date);
		Header.add(Dot);
		Header.add(Dot2);
		Header.add(Dot3);
		Header.add(Dot4);
		Header.add(POS);
		Header.add(Icon);
		Header.add(User);
		Header.add(Home);
		
		add(Container);
		add(Container2);
		setLayout(null);
		setVisible(true);
		
	}
}

