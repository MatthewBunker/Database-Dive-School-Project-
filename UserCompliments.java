import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.event.*;

public class UserCompliments{
	public static void main(Connection conn, String user_id, boolean user_bool) {
		try {
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
				JLabel title = new JLabel("Search for Top 10 Users for Most Compliments received in: ");
				JButton useful_btn = new JButton("Useful");
				JButton funny_btn = new JButton("Funny");
				JButton cool_btn = new JButton("Cool");
				Object[] searchFields = {title, useful_btn, funny_btn, cool_btn};
				
				useful_btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						search_dialog.dispose();
						output_method(conn, "Useful");
					}
				});
				funny_btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						search_dialog.dispose();
						output_method(conn, "Funny");
					}
				});
				cool_btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						search_dialog.dispose();
						output_method(conn, "Cool");
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
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error accessing Database.");
		} 
	}

	public static void output_method(Connection conn, String choice){
		try{
			JDialog output_dialog = new JDialog();
			Statement stmt = conn.createStatement();
			String sqlStatement = String.format("SELECT \"User\".\"User_ID\", \"Name\", \"%s\" FROM \"User\" LEFT JOIN \"User Compliments\" ON \"User\".\"User_ID\"=\"User Compliments\".\"User_ID\" ORDER BY \"User Compliments\".\"%s\" DESC LIMIT 10", choice, choice);
			ResultSet result = stmt.executeQuery(sqlStatement);
			String output = "";
			output += "Top 10 Users received the most "+choice+" compliments\n";
			output += "Place |   Name    | Compliments received |              User_ID               \n";
			int x = 1;
			while (result.next()){
				output += "No. "+x+" | "+result.getString("Name")+" | "+result.getString(choice)+" | "+result.getString("User_ID")+"\n";
				x = x+1;
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
}