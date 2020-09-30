import java.sql.*;
import java.util.Scanner;
import java.util.*;
import javax.swing.JOptionPane;

public class UserCompliments{
	public static void main(Connection conn, String user_id, boolean user_bool) {
		//Connection conn = null;
		try {
			String output = "";
			String option = JOptionPane.showInputDialog(null,"0: Close\n"
										    + "1: Top 10 Useful Users\n"
											+ "2: Top 10 Funny Users\n"
											+ "3: Top 10 Cool Users\n"
											+ "Input desired command (enter \"1\" for Top 10 Useful Users): ");
			int optionInt = Integer.parseInt(option);
			String option2 = null;
			switch (optionInt) {
				case 1:
					option2 = "Useful";
					break;
				case 2:
					option2 = "Funny";
					break;
				case 3:
					option2 = "Cool";
					break;
				default:
					break;
			}
			//create a statement object
			Statement stmt = conn.createStatement();
			String sqlStatement = String.format("SELECT * FROM \"User Compliments\" ORDER BY \"%s\" DESC LIMIT 10", option2);
			ResultSet result = stmt.executeQuery(sqlStatement);
			switch (optionInt) {
				case 1:
					while (result.next()) {
						output += result.getString("User_ID") + " | " + result.getString("Useful") + "\n";
					}
					JOptionPane.showMessageDialog(null,"User ID | Useful\n" + output);
					break;
				case 2:
					while (result.next()) {
						output += result.getString("User_ID") + " | " + result.getString("Funny") + "\n";
					}
					JOptionPane.showMessageDialog(null,"User ID | Funny\n" + output);
					break;
				case 3:
					while (result.next()) {
						output += result.getString("User_ID") + " | " + result.getString("Cool") + "\n";
					}
					JOptionPane.showMessageDialog(null,"User ID | Cool\n" + output);
					break;
				default:
					JOptionPane.showMessageDialog(null,"Invalid Argument");
					break;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error accessing Database.");
		}
	}
}