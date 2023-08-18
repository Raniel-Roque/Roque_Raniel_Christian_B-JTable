package jTable;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.stream.Stream;

public class Table extends JFrame {
	ImageIcon Logo = new ImageIcon(new ImageIcon("Logo.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
	ImageIcon HomeImg = new ImageIcon(new ImageIcon("Home.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
	ImageIcon HomeImg2 = new ImageIcon(new ImageIcon("Home.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
	Toolkit ToolK = getToolkit();
	Dimension Size = ToolK.getScreenSize();

	JLabel Info, LabelSearch;
	JTextField SearchField;
	JLabel[] Label = new JLabel[8]; //LABELS BESIDE TEXTBOX
	JTextField[] TField = new JTextField[8];
	JButton[] Buttons1 = new JButton[3], Buttons2 = new JButton[3]; //STOCK IN, ADD, EDIT
	JButton Home;
	
	//BUTTON NAMES
	String[] ButtonText = {
			"Stock In",
			"Add",
			"Edit",
			"Delete",
			"Save",
			"Cancel"
	};
	
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
	Vector<String> r = new Vector<String>();
	TableRowSorter<TableModel> sort = new TableRowSorter<>(Table.getModel());

	int Code = 11; //COUNTER FOR ITEM CODE
	
	void Table_Design(){
		setSize(900, 650);
		setLocation(Size.width/2 - getWidth()/2, Size.height/2 - getHeight()/2);
		getContentPane().setBackground(Color.decode("#424242"));
		setResizable(false);
		setTitle("Inventory");
		setIconImage(Logo.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Info = new JLabel("INFORMATION DETAILS:");
		Info.setBounds(15, 10, 300, 30);
		Info.setFont(new Font("Arial", Font.BOLD, 18));
		Info.setForeground(Color.white);
		
		LabelSearch = new JLabel("SEARCH ITEM: ");
		LabelSearch.setBounds(15, 300, 200, 30);
		LabelSearch.setFont(new Font("Arial", Font.BOLD, 16));
		LabelSearch.setForeground(Color.white);
		
		SearchField = new JTextField();
		SearchField.setBounds(145, 303, 722, 25);
		SearchField.setFont(new Font("Arial", Font.PLAIN, 14));
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
		
		
		int y = 50; //Y Of LABELS
		for (int i = 0; i < Label.length; i++) {
			Label[i] = new JLabel(Row[i] + ":");
			Label[i].setBounds(40, y-4, 200, 30);
			Label[i].setFont(new Font("Arial", Font.PLAIN, 16));
			Label[i].setForeground(Color.white);
			add(Label[i]);
			
			TField[i] = new JTextField();
			TField[i].setBounds(190, y, 340, 25);
			TField[i].setFont(new Font("Arial", Font.PLAIN, 14));
			TField[i].setForeground(Color.black);
			TField[i].setBorder(BorderFactory.createLineBorder(Color.black, 1));
			TField[i].setEditable(false);
			int index = i;
		    TField[i].addKeyListener(new java.awt.event.KeyAdapter() {
		        public void keyPressed(java.awt.event.KeyEvent e) {
		            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		                if (index < TField.length - 2) {
		                    TField[index+1].requestFocus();
		                }
		            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		                if (index < TField.length - 2) {
		                    TField[index+1].requestFocus();
		                }
		            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
		                if (index > 1) {
		                    TField[index-1].requestFocus();
		                }
		            }
		        }
		    });
			add(TField[i]);
			
			y += 30;
		}
		
		TField[6].addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		            Buttons2[1].doClick();
		        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
		            Buttons2[2].doClick();
		        }
		    }
		});
		

		int y1 = 60; //Y FOR BUTTONS
		for (int i = 0; i < Buttons1.length; i++) {
			Buttons1[i] = new JButton(ButtonText[i]);
			Buttons2[i] = new JButton(ButtonText[i+3]);
			Buttons1[i].setBounds(570, y1, 125, 50);
			Buttons2[i].setBounds(720, y1, 125, 50);
			
			Buttons1[i].setFont(new Font("Arial", Font.PLAIN, 14));
			Buttons1[i].setForeground(Color.black);
			Buttons2[i].setFont(new Font("Arial", Font.PLAIN, 14));
			Buttons2[i].setForeground(Color.black);
			Buttons1[i].setFocusable(false);
			Buttons2[i].setFocusable(false);
			Buttons1[i].setBackground(Color.white);
			Buttons2[i].setBackground(Color.white);
			Buttons1[i].setOpaque(true);
			Buttons2[i].setOpaque(true);
			Buttons1[i].setEnabled(true);
			Buttons2[i].setEnabled(true);
			Buttons1[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			Buttons2[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			int index = i;
			Buttons1[i].addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseEntered(java.awt.event.MouseEvent evt) {
	            	if (Buttons1[index].isEnabled() == true) {
	            		Buttons1[index].setFont(new Font("Arial", Font.PLAIN, 18));
	            	}
	            }
	            public void mouseExited(java.awt.event.MouseEvent evt) {
	            	Buttons1[index].setFont(new Font("Arial", Font.PLAIN, 14));
	            }
			});
			
			Buttons2[i].addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseEntered(java.awt.event.MouseEvent evt) {
	            	if (Buttons2[index].isEnabled() == true) {
	            		Buttons2[index].setFont(new Font("Arial", Font.PLAIN, 18));
	            	} 
	            }
	            public void mouseExited(java.awt.event.MouseEvent evt) {
	            	Buttons2[index].setFont(new Font("Arial", Font.PLAIN, 14));
	            }
			});
			
			
			y1 += 80;
			add(Buttons1[i]);
			add(Buttons2[i]);
		}
		
		//TOOLTIP EDITS
		UIManager.put("ToolTip.background", Color.white);
		UIManager.put("ToolTip.foreground", Color.black);
		UIManager.put("ToolTip.border", BorderFactory.createLineBorder(Color.black, 1));
		UIManager.put("ToolTip.font", new Font("Arial", Font.BOLD, 12));
	    int initialDelay = ToolTipManager.sharedInstance().getInitialDelay();
	    ToolTipManager.sharedInstance().setInitialDelay(10);
			    
		Home = new JButton();
		Home.setIcon(HomeImg);
		Home.setBackground(null);
		Home.setOpaque(false);
		Home.setBounds(835, 5, 45, 45);
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
            	Home.setBounds(835, 5, 45, 45);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	Home.setIcon(HomeImg);
            	Home.setBounds(835, 5, 45, 45);
            }
		});
		
		//CLICK AN ITEM FROM THE TABLE IT SHOWS IN THE TEXTFIELDS
		Table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        if (Buttons2[1].isEnabled() == false && Buttons2[2].isEnabled() == false) {
		            int selectedRow = Table.getSelectedRow();
		            if (selectedRow >= 0) {
		                for (int i = 0; i < Model.getColumnCount(); i++) {
		                    TField[i].setText((String) Table.getValueAt(selectedRow, i));
		                }
		            }
		        }
		    }
		});
		
		Buttons1[0].addActionListener(new ActionListener() { //STOCK IN
			public void actionPerformed(ActionEvent e) {
				int selectedRow = Table.getSelectedRow(); //GETS SELECTED ROW
				if (selectedRow >= 0) { //IF MERON THEN EXECUTE BELOW
					
					//INPUT MESSAGE
					String Conf = JOptionPane.showInputDialog(null, "Enter new stock to be added.", "Stock Count", JOptionPane.INFORMATION_MESSAGE);
					
					if (Conf == null || Conf == "") {}
					else if (Conf.matches("[0-9]+")) {
						int Conf2 = JOptionPane.showConfirmDialog(null, "Are you sure you want to add " + Conf + " stocks to " + TField[1].getText() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
						if (Conf2 == JOptionPane.YES_OPTION) {
							Long Added = Long.parseLong(Conf) + Long.parseLong((String) Table.getValueAt(selectedRow, 5));
							Table.setValueAt(String.valueOf(Added), selectedRow, 5); //CHANGE STOCKS OF SELECTED ROW
							
							if (Added > Long.parseLong((String) Table.getValueAt(selectedRow, 6))) {
								Table.setValueAt("High Stock", selectedRow, 7);
							} else {
								Table.setValueAt("Low Stock", selectedRow, 7);
							}
							
							for (int i = 0; i < Model.getColumnCount(); i++) { //UPDATES THE TEXTFIELDS
				               TField[i].setText((String) Table.getValueAt(selectedRow, i));
				            }  
						}	   
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Input", "Invalid", JOptionPane.WARNING_MESSAGE);
					}
				} else { //IF WALA THEN EXECUTE BELOW
					JOptionPane.showMessageDialog(null, "Select an Item first", "No Item selected", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
				
		Buttons1[1].addActionListener(new ActionListener() { //ADD
			public void actionPerformed(ActionEvent e) {
				
				//ENABLES ONLY SAVE AND CANCEL
				Buttons1[0].setEnabled(false);
				Buttons1[1].setEnabled(false);
				Buttons1[2].setEnabled(false);
				Buttons2[0].setEnabled(false);
				Buttons2[1].setEnabled(true);
				Buttons2[2].setEnabled(true);
	
				//ADDS THE INITIAL ITEM CODE FOR YOU AND ENABLES ALL TEXTFIELD BUT ITEM CODE AND REMARK
				TField[0].setText(String.format("%05d", Code));
				
				for (int i = 0; i < TField.length; i++) {
				    if (i == 0 || i == 7) {
				        TField[i].setEditable(false);
				    } else {
				    	TField[i].setText("");
				        TField[i].setEditable(true);
				    }
				}
				
				TField[7].setText("");
				TField[1].requestFocus();
				
				Buttons2[1].addActionListener(new ActionListener() { //SAVE
					public void actionPerformed(ActionEvent e) {
					if (Stream.of(TField).limit(7).filter(tf -> !tf.equals(TField[7])).anyMatch(tf -> tf.getText().isBlank())) {
						JOptionPane.showMessageDialog(null, "Please fill in the missing information.", "Missing Information", JOptionPane.WARNING_MESSAGE);
					} else if (Stream.of(TField[3]).anyMatch(tf -> !tf.getText().matches("^\\d*\\.?\\d+$")) || Stream.of(TField[5], TField[6]).anyMatch(tf -> !tf.getText().matches("^\\d+$"))) {
					    JOptionPane.showMessageDialog(null, "Price, Stocks, and Re-order Point can only be numbers.", "Incorrect Information", JOptionPane.WARNING_MESSAGE); 
					} else {
						int Conf = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this item?", "Save", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
						if (Conf == JOptionPane.YES_OPTION) {
							//ADDS THE INITIAL ITEM CODE FOR YOU IN THE VECTOR AND INCREASES BY 1 THEN GRABS THE REST OF THE DATA
								for (int i = 0; i < TField.length - 1; i++) {
								    if (i == 0) {
								        r.add(String.format("%05d", Code));
								        Code += 1;
								    } else if (i == 3) {
								        String price = TField[3].getText();
								        if (!price.contains(".")) {
								            price += ".00";
								        }
								        r.add(price);
								    } else {
								        r.add(TField[i].getText());
								    }
								}
								
							    if (Long.parseLong(TField[5].getText()) > Long.parseLong(TField[6].getText())) {
							        r.add("High Stock");
							    } else {
							        r.add("Low Stock");
							    }
		
							    //ADDS ROW AND CLEARS VECTOR DATA
							    Model.addRow(r);
							    r = new Vector<String>();
		
							    //DISABLES ALL TEXTFIELD AND CLEARS
							    for (int i = 0; i < TField.length; i++) {
							        TField[i].setEditable(false);
							        TField[i].setText("");
							    }
		
							    //ENABLES ALL BUTTONS BUT SAVE AND CANCEL THEN REMOVE THE ACTION LISTENER FOR SAVE AND CANCEL
							    Buttons1[0].setEnabled(true);
							    Buttons1[1].setEnabled(true);
							    Buttons1[2].setEnabled(true);
							    Buttons2[0].setEnabled(true);
							    Buttons2[1].setEnabled(false);
							    Buttons2[2].setEnabled(false);
							    Buttons2[1].removeActionListener(Buttons2[1].getActionListeners()[0]);
							    Buttons2[2].removeActionListener(Buttons2[2].getActionListeners()[0]);
							}
						}
					}
				});
				
				Buttons2[2].addActionListener(new ActionListener() { //CANCEL
					public void actionPerformed(ActionEvent e) {
						//UPDATES TEXTFIELD/GETS THE OLD DATA YOU SELECTED
						//IF NONE THEN CLEAR TEXTFIELD
						int Conf = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel?", "Cancel", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
						if (Conf == JOptionPane.YES_OPTION) {
							int selectedRow = Table.getSelectedRow();
							if (selectedRow >= 0) { //MAKES SURE THAT THERE IS SOMETHING SELESCTED
								for (int i = 0; i < Model.getColumnCount(); i++) {
					                TField[i].setText((String) Table.getValueAt(selectedRow, i));
					                TField[i].setEditable(false);
					            }
							} else {
								for (int i =0; i < TField.length-1; i++) {
									TField[i].setEditable(false);
									TField[i].setText("");
								}
							}
							
							//ENABLES ALL BUTTONS BUT SAVE AND CANCEL THEN REMOVE THE ACTION LISTENER FOR SAVE AND CANCEL
							Buttons1[0].setEnabled(true);
							Buttons1[1].setEnabled(true);
							Buttons1[2].setEnabled(true);
							Buttons2[0].setEnabled(true);
							Buttons2[1].setEnabled(false);
							Buttons2[2].setEnabled(false);
							Buttons2[1].removeActionListener(Buttons2[1].getActionListeners()[0]);
							Buttons2[2].removeActionListener(Buttons2[2].getActionListeners()[0]);
						}
					}
				});
			}
		});
		
		Buttons1[2].addActionListener(new ActionListener() { //EDIT
		    public void actionPerformed(ActionEvent e) {
		        
		    	//GETS SELECTED DATA
				//IF NONE THEN CLEAR TEXTFIELD
		    	int selectedRow = Table.getSelectedRow();
		        if (selectedRow >= 0) { //MAKES SURE THAT THERE IS SOMETHING SELESCTED
		        	int Conf = JOptionPane.showConfirmDialog(null, "Edit " + Table.getValueAt(selectedRow, 1) + "?", "Edit Item?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		            if (Conf == JOptionPane.YES_OPTION) {
		            	for (int i = 0; i < Model.getColumnCount(); i++) {
			                TField[i].setText((String) Table.getValueAt(selectedRow, i));
			            }
			            
			            //ENABLES TEXTFIELDS BUT ITEM CODE AND REMARK
			            for (int i = 0; i < TField.length; i++) {
			                if (i == 0 || i == 5 || i == 7) {
			                    TField[i].setEditable(false);
			                    TField[i].removeKeyListener(null);
			                } else {
			                    TField[i].setEditable(true);
			                    TField[i].removeKeyListener(null);
			                }
			            }
			            

						TField[1].requestFocus();
						
			            for (int i = 0; i < TField.length; i++) {
			                int index = i;
			                TField[i].addKeyListener(new java.awt.event.KeyAdapter() {
			                    public void keyPressed(java.awt.event.KeyEvent e) {
			                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			                            if (index < TField.length - 2 && index != 4) {
			                                TField[index+1].requestFocus();
			                            } else if (index == 4) {
			                                TField[index+2].requestFocus();
			                            }
			                        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			                            if (index < TField.length - 2 && index != 4) {
			                                TField[index+1].requestFocus();
			                            } else if (index == 4) {
			                                TField[index+2].requestFocus();
			                            }
			                        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
			                            if (index > 1 && index != 6) {
			                                TField[index-1].requestFocus();
			                            } else if (index == 6) {
			                                TField[index-2].requestFocus();
			                            }
			                        }
			                    }
			                });
			            }

			            
			        
			            //DISABLES ALL BUTTONS BUT SAVE AND CANCEL
				        Buttons1[0].setEnabled(false);
						Buttons1[1].setEnabled(false);
						Buttons1[2].setEnabled(false);
						Buttons2[0].setEnabled(false);
						Buttons2[1].setEnabled(true);
						Buttons2[2].setEnabled(true);
						
							Buttons2[1].addActionListener(new ActionListener() { //SAVE
								public void actionPerformed(ActionEvent e) {				
									//GETS THE VALUES AND CHANGES IT BASED ON THE SELECTED ROW AND THE COLUMN OF THE CELL DATA
									if (Stream.of(TField).anyMatch(tf -> tf.getText().isBlank())) {
										JOptionPane.showMessageDialog(null, "Please fill in the missing information.", "Missing Information", JOptionPane.WARNING_MESSAGE);
									} else if (Stream.of(TField[3]).anyMatch(tf -> !tf.getText().matches("^\\d*\\.?\\d+$")) || Stream.of(TField[5], TField[6]).anyMatch(tf -> !tf.getText().matches("^\\d+$"))) {
									    JOptionPane.showMessageDialog(null, "Price, and Re-order Point can only be numbers.", "Incorrect Information", JOptionPane.WARNING_MESSAGE); 
									} else {
										int Conf = JOptionPane.showConfirmDialog(null, "Save changes?", "Save", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
										if (Conf == JOptionPane.YES_OPTION) {
											Table.setValueAt(TField[1].getText(), selectedRow, 1);
											Table.setValueAt(TField[2].getText(), selectedRow, 2);
											String price = TField[3].getText();
											if (!price.contains(".")) {
											    price += ".00";
											}
											Table.setValueAt(price, selectedRow, 3);
											Table.setValueAt(TField[4].getText(), selectedRow, 4);
											Table.setValueAt(TField[6].getText(), selectedRow, 6);
											
											if (Long.parseLong(TField[5].getText()) > Long.parseLong((String) Table.getValueAt(selectedRow, 6))) {
												Table.setValueAt("High Stock", selectedRow, 7);
											} else {
												Table.setValueAt("Low Stock", selectedRow, 7);
											}
											
											for (int i =0; i < TField.length; i++) {
												TField[i].setEditable(false);
											}
											
											for (int i = 0; i < Model.getColumnCount(); i++) {
								                TField[i].setText((String) Table.getValueAt(selectedRow, i));
								            }
											
											//ENABLES ALL BUTTONS BUT SAVE AND CANCEL THEN REMOVE THE ACTION LISTENER FOR SAVE AND CANCEL
											Buttons1[0].setEnabled(true);
											Buttons1[1].setEnabled(true);
											Buttons1[2].setEnabled(true);
											Buttons2[0].setEnabled(true);
											Buttons2[1].setEnabled(false);
											Buttons2[2].setEnabled(false);
											Buttons2[1].removeActionListener(Buttons2[1].getActionListeners()[0]);
											Buttons2[2].removeActionListener(Buttons2[2].getActionListeners()[0]);
											
											for (int i = 0; i < TField.length; i++) {
												TField[i].removeKeyListener(null);
												
											    final int index = i;
											    TField[i].addKeyListener(new java.awt.event.KeyAdapter() {
											        public void keyPressed(java.awt.event.KeyEvent e) {
											            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
											                if (index < TField.length - 2) {
											                    TField[index+1].requestFocus();
											                }
											            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
											                if (index < TField.length - 2) {
											                    TField[index+1].requestFocus();
											                }
											            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
											                if (index > 1) {
											                    TField[index-1].requestFocus();
											                }
											            }
											        }
											    });
											}

										}
									}
								}
							});
						
							Buttons2[2].addActionListener(new ActionListener() { //CANCEL
								public void actionPerformed(ActionEvent e) {
									
									int Conf = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel?", "Cancel", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
									if (Conf == JOptionPane.YES_OPTION) {
										//UPDATES TEXTFIELD DATA 
										int selectedRow = Table.getSelectedRow();
								        if (selectedRow >= 0) { 
								            for (int i = 0; i < Model.getColumnCount(); i++) {
								                TField[i].setText((String) Table.getValueAt(selectedRow, i));
								            }
								        }
										
								        //DISABLES TEXTFIELDS
								        for (int i =0; i < TField.length; i++) {
								        	TField[i].setEditable(false);
										}
								        
								      //ENABLES ALL BUTTONS BUT SAVE AND CANCEL THEN REMOVE THE ACTION LISTENER FOR SAVE AND CANCEL
										Buttons1[0].setEnabled(true);
										Buttons1[1].setEnabled(true);
										Buttons1[2].setEnabled(true);
										Buttons2[0].setEnabled(true);
										Buttons2[1].setEnabled(false);
										Buttons2[2].setEnabled(false);
										Buttons2[1].removeActionListener(Buttons2[1].getActionListeners()[0]);
										Buttons2[2].removeActionListener(Buttons2[2].getActionListeners()[0]);
										
										for (int i = 0; i < TField.length; i++) {
											TField[i].removeKeyListener(null);
											
										    final int index = i;
										    TField[i].addKeyListener(new java.awt.event.KeyAdapter() {
										        public void keyPressed(java.awt.event.KeyEvent e) {
										            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
										                if (index < TField.length - 2) {
										                    TField[index+1].requestFocus();
										                }
										            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
										                if (index < TField.length - 2) {
										                    TField[index+1].requestFocus();
										                }
										            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
										                if (index > 1) {
										                    TField[index-1].requestFocus();
										                }
										            }
										        }
										    });
										}
										
									}
								}
							});
		            }
		        } else {
		        	JOptionPane.showMessageDialog(null, "Select an Item first", "No Item selected", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});
		
		Buttons2[0].addActionListener(new ActionListener() { //DELETE
			public void actionPerformed(ActionEvent e) {
			    int selectedRow = Table.getSelectedRow();
			    if (selectedRow >= 0) {
			    	int Conf = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?", "Delete Item", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
			        if (Conf == JOptionPane.YES_OPTION) {
			        	Model.removeRow(selectedRow); //REMOVES SELECTED ROW
				        for (int i = 0; i < TField.length; i++) { //REMOVES ALL PREVIOUS DATA FROM TEXTFIELD KASI ERROR PAG HINDI!!!!!!
				            TField[i].setText("");
				        }
			        }
			    } else {
			        JOptionPane.showMessageDialog(null, "Select an Item first", "No Item selected", JOptionPane.WARNING_MESSAGE);
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
			70, 150, 220, 80, 110, 70, 100, 90 //ONLY EDIT THIS 
		};
		
		for (int i = 0; i < Wid.length; i++) {
			Colm.getColumn(i).setPreferredWidth(Wid[i]);
		}
		
		//OTHERS
		Buttons2[1].setEnabled(false);
		Buttons2[2].setEnabled(false);
		Panel.setBounds(17, 340, 850, 255);
		Panel.add(Scroll);
		Table.setRowSorter(sort);
		
		add(Panel);
		add(Info);
		add(LabelSearch);
		add(SearchField);
		add(Home);
		setLayout(null);
		setVisible(true);
	}
}
