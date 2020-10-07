import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.event.*;

public class User{
	public static void main(Connection conn, String user_id, boolean user_bool) {
		try{
			if (user_bool == false){
				Statement stmt = conn.createStatement();
				String sqlStatement = String.format("SELECT * FROM \"User\" LEFT JOIN \"User Compliments\" ON \"User\".\"User_ID\"=\"User Compliments\".\"User_ID\" WHERE \"User\".\"User_ID\"=\'%s\'", user_id);
				ResultSet result = stmt.executeQuery(sqlStatement);
				String output = "";
				while(result.next()) {
					output += "User ID: "+user_id+"\n"+"User Name: "+result.getString("Name")+"\n"+"Useful comliments received: "+result.getString("Useful")+"\n"+"Funny compliments received: "+result.getString("Funny")+"\n"+"Cool compliments received: "+result.getString("Cool")+"\n";
				}
				Object[] inputFields = {output};
				JDialog user_dialog = new JDialog();
				JOptionPane optionPane;
				optionPane = new JOptionPane(inputFields, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
				user_dialog.setContentPane(optionPane);
				user_dialog.setTitle("User Info");
				user_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				user_dialog.pack();
				user_dialog.setLocationRelativeTo(null);
				user_dialog.setVisible(true);
				jdbcpostgreSQLGUI.close_conn(conn);
			} else {
				JDialog search_dialog = new JDialog();
				JLabel title = new JLabel("Search for User by Name: ");
				JTextField name = new JTextField();
				JButton search_btn = new JButton("Search");
				Object[] searchFields = {title, name, search_btn};
				
				search_btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!name.getText().isBlank()){
							search_dialog.dispose();
							output_method(conn, name.getText());
						}
					}
				});

				JOptionPane searchPane = new JOptionPane(searchFields, JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
				search_dialog.setContentPane(searchPane);
				search_dialog.setTitle("User Search");
				search_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				search_dialog.pack();
				search_dialog.setLocationRelativeTo(null);
				search_dialog.setVisible(true);
			}		
		}
		catch(Exception e){
			System.out.println("Error accessing Database.");
		}
	}

	public static void output_method(Connection conn, String name){
		try{
			JDialog output_dialog = new JDialog();
			Statement stmt = conn.createStatement();
			String sqlStatement = String.format("SELECT * FROM \"User\" WHERE \"Name\"=\'%s\'", name);
			ResultSet result = stmt.executeQuery(sqlStatement);
			String output = "";
			output += "Users with Name: "+name+"\n";
			output += "   Name    |                 User_ID                  \n";
			while (result.next()){
				output += result.getString("Name")+" | "+result.getString("User_ID")+"\n";
			}
			JOptionPane outputPane = new JOptionPane(output, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
			output_dialog.setContentPane(outputPane);
			output_dialog.setTitle("User Info");
			output_dialog.setContentPane(outputPane);
			output_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			output_dialog.pack();
			output_dialog.setLocationRelativeTo(null);
			output_dialog.setVisible(true);
			jdbcpostgreSQLGUI.close_conn(conn);
		} catch (Exception e){
			System.out.println("Error accessing Database.");
		}
	}

	public static void user_review_main(Connection conn, String user_id){
		try{
			int count = 0;
			String N = JOptionPane.showInputDialog(null, "Enter number of records to query (recommended: <= 100k)");
			String output = "";
			Statement stmt = conn.createStatement();
			String sqlStatement = "SELECT \"Review_ID\",\"User_ID\",stars,useful,funny,cool FROM \"Review\" FULL OUTER JOIN reviewstars on \"Review\".\"Review_ID\" = reviewstars.review_id LIMIT " + N;
			System.out.println(sqlStatement);
			ResultSet result = stmt.executeQuery(sqlStatement);
			
			// check if user_id has >= 5 reviews
			while(result.next()){
				if (result.getString("User_ID").equals(user_id)) {
					++count;
				}
			}
			if (count < 5) {
				JOptionPane.showMessageDialog(null, "User has less than 5 reviews.");
				jdbcpostgreSQLGUI.close_conn(conn);
			}
			
			// get user_compliment data
			output += result.getString("useful") + " | " + result.getString("funny") + " | " + result.getString("cool") + "\n";
			JOptionPane.showMessageDialog(null, output);
			
			// get avg of star rating
			
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error accessing Database.");
		}
	}
}