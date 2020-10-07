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
            JButton pop_search_button = new JButton("Popular search");
	
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
					review_info(conn);
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
					user_info(0, conn);
				}
			});
	
			tip_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					menu_dialog.dispose();
					tip_info(conn);
				}
			});
	
			hours_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					menu_dialog.dispose();
					business_info(4, conn);
				}
            });
            
            pop_search_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					menu_dialog.dispose();
					pop_search_button(conn);
				}
			});
	
			Object[] inputfield = {title, "\n",direction, business_button, address_button, parking_button, checkin_button, review_button, usercompliments_button, user_button, hours_button, tip_button, pop_search_button};
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
	}
	
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
							Hours.main(conn, name.getText(), id.getText(), checkBox.isSelected());
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
        JTextField id = new JTextField();
        JCheckBox checkBox = new JCheckBox("Continue without User ID");
        JButton searchButton = new JButton("Search");

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
                    checkBox.setEnabled(true);
                } else {
                    checkBox.setEnabled(false);
                }
            }
        });

        checkBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (checkBox.isSelected()) {
                    id.setEnabled(false);
                } else {
                    id.setEnabled(true);
                }
            }
        });

        Object[] inputFields = {"Enter User ID: ", id, checkBox, searchButton};
        
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				if(!id.getText().isBlank() || checkBox.isSelected()){
					user_dialog.dispose();
					switch(entity){
						case 0:
							User.main(conn, id.getText(), checkBox.isSelected());
							break;
						case 1:
							UserCompliments.main(conn, id.getText(), checkBox.isSelected());
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

    public static void tip_info(Connection conn){
        JDialog tip_dialog = new JDialog();
        JTextField user_id = new JTextField();
        JTextField business_id = new JTextField();
        JButton searchButton = new JButton("Search");

        user_id.getDocument().addDocumentListener(new DocumentListener() {
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
                if(user_id.getText().equals("")){
                    business_id.setEnabled(true);
                } else {
                    business_id.setEnabled(false);
                }
            }
        });

        business_id.getDocument().addDocumentListener(new DocumentListener() {
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
                if(business_id.getText().equals("")){
                    user_id.setEnabled(true);
                } else {
                    user_id.setEnabled(false);
                }
            }
        });

        Object[] inputFields = {"Enter User ID: ", user_id, "Enter Business ID: ", business_id, searchButton};
        
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				if(!user_id.getText().isBlank() || !business_id.getText().isBlank()){
					tip_dialog.dispose();
					Tip.main(conn, user_id.getText(), business_id.getText());
				}
            }
        });

        JOptionPane optionPane = new JOptionPane(inputFields, JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        tip_dialog.setContentPane(optionPane);
        tip_dialog.setTitle("Business Input");
        tip_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        tip_dialog.pack();
        tip_dialog.setLocationRelativeTo(null);
        tip_dialog.setVisible(true);
    }

    public static void review_info(Connection conn){
        JDialog review_dialog = new JDialog();
        JTextField user_id = new JTextField();
        JTextField business_id = new JTextField();
        JTextField review_id = new JTextField();
        JButton searchButton = new JButton("Search");

        user_id.getDocument().addDocumentListener(new DocumentListener() {
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
                if(user_id.getText().equals("")){
                    business_id.setEnabled(true);
                    review_id.setEnabled(true);
                } else {
                    business_id.setEnabled(false);
                    review_id.setEnabled(false);
                }
            }
        });

        business_id.getDocument().addDocumentListener(new DocumentListener() {
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
                if(business_id.getText().equals("")){
                    user_id.setEnabled(true);
                    review_id.setEnabled(true);
                } else {
                    user_id.setEnabled(false);
                    review_id.setEnabled(false);
                }
            }
        });

        review_id.getDocument().addDocumentListener(new DocumentListener() {
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
                if(review_id.getText().equals("")){
                    user_id.setEnabled(true);
                    business_id.setEnabled(true);
                } else {
                    user_id.setEnabled(false);
                    business_id.setEnabled(false);
                }
            }
        });

        Object[] inputFields = {"Enter User ID: ", user_id, "Enter Business ID: ", business_id, "Enter Review ID: ", review_id, searchButton};
        
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				if(!user_id.getText().isBlank() || !business_id.getText().isBlank() ||!review_id.getText().isBlank()){
					review_dialog.dispose();
					Review.main(conn, user_id.getText(), business_id.getText(), review_id.getText());
				}
            }
        });

        JOptionPane optionPane = new JOptionPane(inputFields, JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        review_dialog.setContentPane(optionPane);
        review_dialog.setTitle("Business Input");
        review_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        review_dialog.pack();
        review_dialog.setLocationRelativeTo(null);
        review_dialog.setVisible(true);
    }

    public static void pop_search_button(Connection conn){
        JDialog pop_search_dialog = new JDialog();
        JLabel directions = new JLabel("Select one of the Queries below: ");
        JButton shortest_chain_button = new JButton("Shortest Chain of patrons");
        JButton user_review_button = new JButton("User Review");
        JButton franchise_restaurant_button = new JButton("Franchise Restaurant");
        JButton local_restaurant_button = new JButton("Local Restaurant");
        JButton rated_business_button = new JButton("Highest Rated Business");

        Object[] inputfield = {directions, "\n", shortest_chain_button, user_review_button, franchise_restaurant_button, local_restaurant_button, rated_business_button};

        final JOptionPane optionPane = new JOptionPane(inputfield, JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        pop_search_dialog.setTitle("Popular Search Options");
        pop_search_dialog.setContentPane(optionPane);
        pop_search_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pop_search_dialog.pack();
        pop_search_dialog.setLocationRelativeTo(null);
        pop_search_dialog.setVisible(true);

        shortest_chain_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pop_search_dialog.dispose();
                shortest_chain_info(conn);
            }
        });

        user_review_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pop_search_dialog.dispose();
                user_review_info(conn);
            }
        });

        franchise_restaurant_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pop_search_dialog.dispose();
                franchise_restaurant_info(conn);
            }
        });

        local_restaurant_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pop_search_dialog.dispose();
                local_restaurant_info(conn);
            }
        });

        rated_business_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pop_search_dialog.dispose();
                rated_business_info(conn);
            }
        });
    }

    public static void shortest_chain_info (Connection conn){
        JDialog shortest_chain_dialog = new JDialog();
        JLabel title = new JLabel("Given 2 restaurants, we will return the shortest chain of patrons that gave a review of 3 stars or better\n");
        JLabel directions = new JLabel("Fill in both boxes with a business name\n");
        JLabel business_name_1 = new JLabel("Business 1: ");
        JLabel business_name_2 = new JLabel("Business 2: ");
        JTextField business_1 = new JTextField();
        JTextField business_2 = new JTextField();
        JButton submit = new JButton("Submit");

        Object[] inputfield = {title, directions, business_name_1, business_1, business_name_2, business_2, submit};
        final JOptionPane optionPane = new JOptionPane(inputfield, JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        shortest_chain_dialog.setTitle("Shortest Chain");
        shortest_chain_dialog.setContentPane(optionPane);
        shortest_chain_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        shortest_chain_dialog.pack();
        shortest_chain_dialog.setLocationRelativeTo(null);
        shortest_chain_dialog.setVisible(true);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                event(e);
            }

            private void event(ActionEvent e){
                if(!business_1.getText().trim().equals("") && !business_2.getText().trim().equals("")){
                    shortest_chain_dialog.dispose();
                    Business.shortest_chain_main(conn, business_1.getText().trim(), business_2.getText().trim());
                }
            }
        });
    }

    public static void user_review_info (Connection conn){
        JDialog user_review_dialog = new JDialog();
        JLabel title = new JLabel("Given a user, summarize average star ratings and comments made by user, and then summarize\n the Yelp react statistics on their reviews from other users\n");
        JLabel directions = new JLabel("Fill in the box with a user name\n");
        JLabel user_name = new JLabel("User Name: ");
        JTextField user = new JTextField();
        JButton submit = new JButton("Submit");

        Object[] inputfield = {title, directions, user_name, user, submit};
        final JOptionPane optionPane = new JOptionPane(inputfield, JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        user_review_dialog.setTitle("User Review Statistics");
        user_review_dialog.setContentPane(optionPane);
        user_review_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        user_review_dialog.pack();
        user_review_dialog.setLocationRelativeTo(null);
        user_review_dialog.setVisible(true);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                event(e);
            }

            private void event(ActionEvent e){
                if(!user.getText().trim().equals("")){
                    user_review_dialog.dispose();
                    User.user_review_main(conn, user.getText().trim());
                }
            }
        });
    }

    public static void franchise_restaurant_info (Connection conn){
        JDialog franchise_restaurant_dialog = new JDialog();
        JLabel title = new JLabel("Given a US state, find the 5 restaurant franchises that have an average rating of 3.5 with the furthest location spread\n");
        JLabel directions = new JLabel("Fill in the box with a State\n");
        JLabel state_name = new JLabel("State: ");
        JTextField state = new JTextField();
        JButton submit = new JButton("Submit");

        Object[] inputfield = {title, directions, state_name, state, submit};
        final JOptionPane optionPane = new JOptionPane(inputfield, JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        franchise_restaurant_dialog.setTitle("Franchise Restaurant rating");
        franchise_restaurant_dialog.setContentPane(optionPane);
        franchise_restaurant_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        franchise_restaurant_dialog.pack();
        franchise_restaurant_dialog.setLocationRelativeTo(null);
        franchise_restaurant_dialog.setVisible(true);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                event(e);
            }

            private void event(ActionEvent e){
                if(!state.getText().trim().equals("")){
                    franchise_restaurant_dialog.dispose();
                    Business.franchise_restaurant_main(conn, state.getText().trim());
                }
            }
        });
    }

    public static void local_restaurant_info (Connection conn){
        JDialog local_restaurant_dialog = new JDialog();
        JLabel title = new JLabel("Given a city, find the non-franchise restaurant that recieves the most tips\n");
        JLabel directions = new JLabel("Fill in the box with a city\n");
        JLabel city_name = new JLabel("City: ");
        JTextField city = new JTextField();
        JButton submit = new JButton("Submit");

        Object[] inputfield = {title, directions, city_name, city, submit};
        final JOptionPane optionPane = new JOptionPane(inputfield, JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        local_restaurant_dialog.setTitle("Best Local Restaurant");
        local_restaurant_dialog.setContentPane(optionPane);
        local_restaurant_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        local_restaurant_dialog.pack();
        local_restaurant_dialog.setLocationRelativeTo(null);
        local_restaurant_dialog.setVisible(true);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                event(e);
            }

            private void event(ActionEvent e){
                if(!city.getText().trim().equals("")){
                    local_restaurant_dialog.dispose();
                    Business.local_restaurant_main(conn, city.getText().trim());
                }
            }
        });
    }

    public static void rated_business_info (Connection conn){
        JDialog rated_business_dialog = new JDialog();
        JLabel title = new JLabel("Given a category, and city, find the group of the 10 highest rated businesses within a 1 mile radius of each other\n");
        JLabel directions = new JLabel("Fill in both boxes with a category and city name respectively\n");
        JLabel category_name = new JLabel("Category: ");
        JLabel city_name = new JLabel("City: ");
        JTextField category = new JTextField();
        JTextField city = new JTextField();
        JButton submit = new JButton("Submit");

        Object[] inputfield = {title, directions, category_name, category, city_name, city, submit};
        final JOptionPane optionPane = new JOptionPane(inputfield, JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        rated_business_dialog.setTitle("Highest Rated business");
        rated_business_dialog.setContentPane(optionPane);
        rated_business_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        rated_business_dialog.pack();
        rated_business_dialog.setLocationRelativeTo(null);
        rated_business_dialog.setVisible(true);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                event(e);
            }

            private void event(ActionEvent e){
                if(!category.getText().trim().equals("") && !city.getText().trim().equals("")){
                    rated_business_dialog.dispose();
                    Business.rated_business_main(conn, category.getText().trim(), city.getText().trim());
                }
            }
        });
    }

	public static void close_conn(Connection conn) {
		try {
			conn.close();
		} catch (Exception ex) {
			System.out.println("Error closing Database.");
		}
	}
}//end Class
