import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
// import java.io.File;
// import java.io.IOException;

//import java.sql.DriverManager;
/*
CSCE 315
9-25-2019
 */

public class jdbcpostgreSQLGUI {
  	public static void main(String args[]) {
    	//Building the connection
	  	Connection temp = null;
      	try {
        	Class.forName("org.postgresql.Driver");
        	temp = DriverManager.getConnection(
    		"jdbc:postgresql://csce-315-db.engr.tamu.edu/db911_group11_theintroverts",
           	dbSetup.user, dbSetup.pswd);
     	} catch (Exception e) {
        	e.printStackTrace();
        	System.err.println(e.getClass().getName()+": "+e.getMessage());
        	System.exit(0);
		}//end try catch
		final Connection conn = temp;
     	try{
			final JDialog menu_dialog = new JDialog();
			JLabel title = new JLabel("Welcome to \"The Introverts\" GUI");
			JLabel direction = new JLabel("Select an option: ");
			JButton business_button = new JButton("Business");
			JButton address_button = new JButton("Address");
			JButton parking_button = new JButton("Parking");
			JButton checkin_button = new JButton("Check-in");
			JButton review_button = new JButton("Review");
			JButton usercompliments_button = new JButton("User Compliment");
			JButton tip_button = new JButton("Tip");
			JButton user_button = new JButton("User");
			JButton hours_button = new JButton("Hours");
	
			business_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					menu_dialog.dispose();
					business_info(0, conn);
				}
			});
	
			address_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					menu_dialog.dispose();
					business_info(1, conn);
				}
			});
	
			parking_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					menu_dialog.dispose();
					business_info(2, conn);
				}
			});
	
			checkin_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					menu_dialog.dispose();
					business_info(3, conn);
				}
			});
	
			review_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					menu_dialog.dispose();
					business_info(4, conn);
				}
			});
	
			usercompliments_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					menu_dialog.dispose();
					user_info(1, conn);
				}
			});
	
			user_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					menu_dialog.dispose();
					user_info(2, conn);
				}
			});
	
			tip_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					menu_dialog.dispose();
					business_info(5, conn);
				}
			});
	
			hours_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					menu_dialog.dispose();
					business_info(6, conn);
				}
			});
	
			Object[] inputfield = {title, "\n",direction, business_button, address_button, parking_button, checkin_button, review_button, usercompliments_button, user_button, hours_button, tip_button};
			final JOptionPane optionPane = new JOptionPane(inputfield, JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
			menu_dialog.setTitle("Menu GUI");
			menu_dialog.setContentPane(optionPane);
			menu_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			menu_dialog.pack();
			menu_dialog.setLocationRelativeTo(null);
			menu_dialog.setVisible(true);
		} catch (Exception e){
	 		JOptionPane.showMessageDialog(null,"Error accessing Database.");
   		}
    	// //closing the connection
    	// try {
		// 	conn.close();
		// 	System.out.println("Connection Closed");
	 	// 	// JOptionPane.showMessageDialog(null,"Connection Closed.");
    	// } catch(Exception e) {
		// 	System.out.println("Connection Not Closed");
	  	// 	// JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
    	// }//end try catch
	}//end main
	
	public static void business_info(int entity, Connection conn){
        JDialog business_dialog = new JDialog();
        JTextField name = new JTextField();
        JTextField id = new JTextField();
        JCheckBox checkBox = new JCheckBox("Continue without Business Name or ID");
        JButton searchButton = new JButton("Search");

        name.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de){
               event(de);
            }
        
            @Override
            public void removeUpdate(DocumentEvent de) {
                event(de);
            }
        
            @Override
            public void changedUpdate(DocumentEvent de){
                event(de);
            }
        
            private void event(DocumentEvent de){
                if(name.getText().equals("")){
                    id.setEnabled(true);
                    checkBox.setEnabled(true);
                } else {
                    id.setEnabled(false);
                    checkBox.setEnabled(false);
                }
            }
        });

        id.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de){
               event(de);
            }
        
            @Override
            public void removeUpdate(DocumentEvent de) {
                event(de);
            }
        
            @Override
            public void changedUpdate(DocumentEvent de){
                event(de);
            }
        
            private void event(DocumentEvent de){
                if(id.getText().equals("")){
                    name.setEnabled(true);
                    checkBox.setEnabled(true);
                } else {
                    name.setEnabled(false);
                    checkBox.setEnabled(false);
                }
            }
        });

        checkBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (checkBox.isSelected()) {
                    name.setEnabled(false);
                    id.setEnabled(false);
                } else {
                    name.setEnabled(true);
                    id.setEnabled(true);
                }
            }
        });

        Object[] inputFields = {"Enter Business Name: ", name, "Enter Business ID: ", id, checkBox, searchButton};
        
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				if(!name.getText().isBlank() || !id.getText().isBlank() || checkBox.isSelected()){
					business_dialog.dispose();
					switch (entity){
						case 0:
							Business.main(conn, name.getText(), id.getText(), checkBox.isSelected());
							break;
						case 1:
							Address.main(conn, name.getText(), id.getText(), checkBox.isSelected());
							break;
						case 2:
							Parking.main(conn, name.getText(), id.getText(), checkBox.isSelected());
							break;
						case 3:
							CheckIn.main(conn, name.getText(), id.getText(), checkBox.isSelected());
							break;
						case 4:
							// Review.main(conn, name.getText(), id.getText(), checkBox.isSelected());
							break;
						case 5:
							Hours.main(conn, name.getText(), id.getText(), checkBox.isSelected());
							break;
						case 6:
							// Tip.main(conn, name.getText(), id.getText(), checkBox.isSelected());
							break;
					}
				}
            }
        });

        JOptionPane optionPane = new JOptionPane(inputFields, JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        business_dialog.setContentPane(optionPane);
        business_dialog.setTitle("Business Input");
        business_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        business_dialog.pack();
        business_dialog.setLocationRelativeTo(null);
        business_dialog.setVisible(true);
	}
	
	public static void user_info(int entity, Connection conn){
        JDialog user_dialog = new JDialog();
        JTextField name = new JTextField();
        JTextField id = new JTextField();
        JCheckBox checkBox = new JCheckBox("Continue without User Name or ID");
        JButton searchButton = new JButton("Search");

        name.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de){
               event(de);
            }
        
            @Override
            public void removeUpdate(DocumentEvent de) {
                event(de);
            }
        
            @Override
            public void changedUpdate(DocumentEvent de){
                event(de);
            }
        
            private void event(DocumentEvent de){
                if(name.getText().equals("")){
                    id.setEnabled(true);
                    checkBox.setEnabled(true);
                } else {
                    id.setEnabled(false);
                    checkBox.setEnabled(false);
                }
            }
        });

        id.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de){
               event(de);
            }
        
            @Override
            public void removeUpdate(DocumentEvent de) {
                event(de);
            }
        
            @Override
            public void changedUpdate(DocumentEvent de){
                event(de);
            }
        
            private void event(DocumentEvent de){
                if(id.getText().equals("")){
                    name.setEnabled(true);
                    checkBox.setEnabled(true);
                } else {
                    name.setEnabled(false);
                    checkBox.setEnabled(false);
                }
            }
        });

        checkBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (checkBox.isSelected()) {
                    name.setEnabled(false);
                    id.setEnabled(false);
                } else {
                    name.setEnabled(true);
                    id.setEnabled(true);
                }
            }
        });

        Object[] inputFields = {"Enter User Name: ", name, "Enter User ID: ", id, checkBox, searchButton};
        
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				if(!name.getText().isBlank() || !id.getText().isBlank() || checkBox.isSelected()){
					user_dialog.dispose();
					switch(entity){
						case 0:
							User.main(conn, name.getText(), id.getText(), checkBox.isSelected());
							break;
						case 1:
							UserCompliments.main(conn, name.getText(), id.getText(), checkBox.isSelected());
							break;
					}
				}
            }
        });

        JOptionPane optionPane = new JOptionPane(inputFields, JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        user_dialog.setContentPane(optionPane);
        user_dialog.setTitle("User Input");
        user_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        user_dialog.pack();
        user_dialog.setLocationRelativeTo(null);
        user_dialog.setVisible(true);
    }
}//end Class
