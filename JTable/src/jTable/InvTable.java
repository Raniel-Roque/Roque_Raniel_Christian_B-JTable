package jTable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class InvTable extends JFrame {
	ImageIcon Logo = new ImageIcon(new ImageIcon("Logo.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
	Toolkit ToolK = getToolkit();
	Dimension Size = ToolK.getScreenSize();
	
	//HEADER NAMES
	String[] Row = new String [] {
		"Item Code",
		"Item Name",
		"Item Description",
		"Price (₱)",
		"Size",
		"Stocks",
		"Re-Order Point",
		"Remarks",
	};
	
	//EXTRA BLANK LINES SO IT CENTERS THE WHOLE THING IDK WHY BUT IF IT WORKS IT WORKS 
	String[][] Column = new String [][] {
	    {"00001", "JJamppong", "Noodles", "100.00", "550g", "154", "70", "High Stock"},
	    {"00002", "Sunsilk", "Shampoo", "200.25", "500mL", "64", "80", "Low Stock"},
	    {"00003", "Eden", "Cheese", "100.10", "200g", "183", "30", "High Stock"},
	    {"00004", "TV", "47.9'' x 27'' Smart TV", "1000.99", "30Kg", "105", "60", "High Stock"},
	    {"00005", "Oreo", "Double-Stuffed Cookies", "100.12", "350g", "402", "120", "High Stock"},
	    {"00006", "Gatorade", "Sports drinks", "30.15", "350mL", "18", "80", "Low Stock"},
	    {"00007", "Coke", "Carbonated Drink", "50.55", "1L", "48", "50", "Low Stock"},
	    {"00008", "Pochi", "Gummy Candy", "10.10", "5g", "842", "100", "High Stock"},
	    {"00009", "Oishi", "Spicy Snack", "20.50", "70g", "239", "70", "High Stock"},
	    {"00010", "Chicken", "Frozen Magnolia ", "300.20", "3Kg", "97", "20", "Low Stock"},
	};
	
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
	Vector<String> s = new Vector<String>();
	TableRowSorter<TableModel> sort = new TableRowSorter<>(Table.getModel());
	
	JTextField SearchField;
	JButton[] Butt = new JButton[2];
	String[] ButtText = {
		"Add",
		"Cancel"
	};
	
	private POS main;
	
	public InvTable(POS main) {
        this.main = main;
    }
	
	public void InvTable_Design() {
		setSize(900, 340);
		setLocation(Size.width/2 - getWidth()/2, Size.height/2 - getHeight()/2);
		getContentPane().setBackground(Color.black);
		setIconImage(Logo.getImage());
		setResizable(false);
		setUndecorated(true);
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		    	SearchField.setText("");
                main.QuantiIn.setText("");
		        main.setEnabled(true);
		    }
		});
		
		JLabel Header = new JLabel();
		Header.setOpaque(true);
		Header.setBackground(Color.decode("#292929"));
		Header.setBounds(0, 0, 896, 51);
		
		JLabel Container = new JLabel();
		Container.setOpaque(true);
		Container.setBackground(Color.decode("#6e6e6e"));
		Container.setBounds(4, 4, 892, 332);
		
		JLabel LabelSearch = new JLabel("SEARCH ITEM: ");
		LabelSearch.setBounds(15, 15, 200, 30);
		LabelSearch.setFont(new Font("Arial", Font.BOLD, 16));
		LabelSearch.setForeground(Color.white);
		
		SearchField = new JTextField();
		SearchField.setBounds(145, 15, 430, 25);
		SearchField.setFont(new Font("Helvetica", Font.PLAIN, 14));
		SearchField.setForeground(Color.black);
		SearchField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		SearchField.getDocument().addDocumentListener(new DocumentListener(){
			
		@Override
		public void insertUpdate(DocumentEvent e) {
			String str = SearchField.getText();
			if (str.trim().length() == 0) {
				sort.setRowFilter(null);
			} else {
				sort.setRowFilter(RowFilter.regexFilter("(?i)" + str));
			}
		}
		
		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = SearchField.getText();
			if (str.trim().length() == 0) {
				sort.setRowFilter(null);
			} else {
				sort.setRowFilter(RowFilter.regexFilter("(?i)" + str));
			}
		}
		
		@Override
		public void changedUpdate(DocumentEvent e) {}
		});
		
		SearchField.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	SearchField.setText("");
		    }
		});
		
		Table.getTableHeader().setEnabled(false); //DISABLES HEADER
		
		//CENTER DATA
		DefaultTableCellRenderer Center = new DefaultTableCellRenderer(); 
		Center.setHorizontalAlignment(JLabel.CENTER); 
		
		for (int i = 0; i < Row.length; i++) {
		    Table.getColumnModel().getColumn(i).setCellRenderer(Center);
		}
		
		int x = 595;
		for (int i = 0; i < Butt.length; i++) {
			int index = i;
			Butt[i] = new JButton(ButtText[i]);
			Butt[i].setBounds(x, 13, 130, 30);
			Butt[i].setFocusable(false);
			Butt[i].setBorder(null);
			Butt[i].setBackground(Color.decode("#adacac"));
			Butt[i].setFont(new Font("Helvetica", Font.BOLD, 14));
			Butt[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			if (index == 0) {
				Butt[i].addMouseListener(new java.awt.event.MouseAdapter() {
		            public void mouseEntered(java.awt.event.MouseEvent evt) {
		            	Butt[index].setBackground(Color.decode("#b2d683"));
		            	Butt[index].setFont(new Font("Helvetica", Font.BOLD, 16));
		            }
		            public void mouseExited(java.awt.event.MouseEvent evt) {
		            	Butt[index].setBackground(Color.decode("#adacac"));
		            	Butt[index].setFont(new Font("Helvetica", Font.BOLD, 14));
		            }
				});
			} else {
				Butt[i].addMouseListener(new java.awt.event.MouseAdapter() {
		            public void mouseEntered(java.awt.event.MouseEvent evt) {
		            	Butt[index].setBackground(Color.decode("#d68383"));
		            	Butt[index].setFont(new Font("Helvetica", Font.BOLD, 16));
		            }
		            public void mouseExited(java.awt.event.MouseEvent evt) {
		            	Butt[index].setBackground(Color.decode("#adacac"));
		            	Butt[index].setFont(new Font("Helvetica", Font.BOLD, 14));
		            }
				});
			}
			
			x += 140;
			Header.add(Butt[i]);
		}
		
		Butt[0].addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = Table.getSelectedRow();

		        if (selectedRow >= 0) {
		            int Conf = JOptionPane.showConfirmDialog(null, "Are you sure you want to add " + main.QuantiIn.getText() + " " + Table.getValueAt(selectedRow, 1), "Add item", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		            if (Conf == JOptionPane.YES_OPTION) {
		                String ID = (String) Table.getValueAt(selectedRow, 0);
		                String Name = (String) Table.getValueAt(selectedRow, 1);
		                String Price = (String) Table.getValueAt(selectedRow, 3);
		                String Size = (String) Table.getValueAt(selectedRow, 4);
		                int Quantity = Integer.parseInt(main.QuantiIn.getText());

		                // Check if the item already exists in the POS table
		                int currentStock = Integer.parseInt((String) Table.getValueAt(selectedRow, 5));
		                int newStock = currentStock - Quantity;
		                
		                DefaultTableModel model = (DefaultTableModel) main.Table.getModel();
		                for (int i = 0; i < model.getRowCount(); i++) {
		                    if (ID.equals(model.getValueAt(i, 0))) {
		                        if (newStock < 0) {
				                    JOptionPane.showMessageDialog(null, "Stock is lower than requested amount", "Invalid Amount of Stock", JOptionPane.WARNING_MESSAGE);
				                    return;
		                        } else {
				                	// Update the quantity and price of the existing row
			                        int newQuantity = Integer.parseInt((String) model.getValueAt(i, 4)) + Quantity;
			                        double newTotal = Double.parseDouble(Price) * newQuantity;
			                        model.setValueAt(Integer.toString(newQuantity), i, 4);
			                        model.setValueAt(String.format("%.2f", newTotal), i, 5);
			                        
				                	Table.setValueAt(Integer.toString(newStock), selectedRow, 5);
				                    if (newStock > Long.parseLong((String) Table.getValueAt(selectedRow, 6))) {
				                        Table.setValueAt("High Stock", selectedRow, 7);
				                    } else {
				                        Table.setValueAt("Low Stock", selectedRow, 7);
				                    }
				                    
				                    SearchField.setText("");
				                    main.QuantiIn.setText("");
				                    main.updateTotal();
				                    main.setEnabled(true);
				                    setVisible(false);
			                        return;
				                }    
		                    }
		                }
		                
		                if (newStock < 0) {
		                    JOptionPane.showMessageDialog(null, "Stock is lower than requested amount", "Invalid Amount of Stock", JOptionPane.WARNING_MESSAGE);
		                } else {
		                    Table.setValueAt(Integer.toString(newStock), selectedRow, 5);
		                    if (newStock > Long.parseLong((String) Table.getValueAt(selectedRow, 6))) {
		                        Table.setValueAt("High Stock", selectedRow, 7);
		                    } else {
		                        Table.setValueAt("Low Stock", selectedRow, 7);
		                    }

		                    double Total = Double.parseDouble(Price) * Quantity;
		                    SearchField.setText("");
		                    main.QuantiIn.setText("");
		                    model.addRow(new Object[]{ID, Name, Size, Price, Integer.toString(Quantity), String.format("%.2f", Total)});
			                main.updateTotal();
		                    main.setEnabled(true);
		                    setVisible(false);
		                }
		            } 
		        } else {
	                JOptionPane.showMessageDialog(null, "Select an item first", "No Item Selected", JOptionPane.WARNING_MESSAGE);
	            }
		    }
		});
		
		Butt[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int Conf = JOptionPane.showConfirmDialog(null, "Cancel?", "Cancel", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(Conf == JOptionPane.YES_OPTION) {
                	SearchField.setText("");
                    main.QuantiIn.setText("");
                    main.setEnabled(true);
                    setVisible(false);
                }
            }
        });
		
		Table.setRowHeight(22); //CHANGE TO CHANGE HOW THICK CELLS ARE
		
		//CHANGE HOW LONG THEY ARE
		TableColumnModel Colm = Table.getColumnModel();
		int[] Wid = {
			70, 150, 220, 80, 110, 70, 100, 90 //ONLY EDIT THIS 
		};
		
		for (int i = 0; i < Wid.length; i++) {
			Colm.getColumn(i).setPreferredWidth(Wid[i]);
		}

		Panel.setBounds(17, 60, 850, 255);
		Panel.add(Scroll);
		Table.setRowSorter(sort);
		add(Container);
		Container.add(Header);
		Container.add(Panel);
		Header.add(LabelSearch);
		Header.add(SearchField);
		setLayout(null);
		setVisible(true);
	}
}
