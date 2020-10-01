import java.sql.*;
import javax.swing.JOptionPane;

public class Address{
	public static void main(Connection conn, String business_name, boolean name_bool) {
		try {
			//create a statement object
			if (name_bool == true){
				Statement stmt = conn.createStatement();
				String sqlStatement = String.format("SELECT \"address\", \"city\", \"state\", \"postal code\" FROM \"business\" LEFT JOIN \"Address\" ON \"Address\".\"Business_ID\"=\"business\".\"business_id\" WHERE \"business\".\"Name\"=\'%s\'", business_name);
				ResultSet result = stmt.executeQuery(sqlStatement);
				String output = "";
				while(result.next()) {
					output += result.getString("address")+", "+result.getString("city")+", "+result.getString("state")+" "+result.getString("postal code")+"\n";
				}
				// System.out.println(result.getString("address"));
				JOptionPane.showMessageDialog(null, "Address information for "+business_name + "\n" + output);
			} else {
				String option = JOptionPane.showInputDialog(null,"0: Close\n"
										    + "1: Top 10 rating business search by city\n"
											+ "2: Top 10 rating business search by state\n"
											+ "3: Top 10 rating business search by postal code\n"
											+ "Input desired command (enter \"1\" for Top 10 rating business search by city): ");
				int optionInt = Integer.parseInt(option);
				String option2 = null;
				String input = "";
				switch (optionInt) {
					case 1:
						option2 = "city";
						input = JOptionPane.showInputDialog(null, "Enter city name: ");
						break;
					case 2:
						option2 = "state";
						input = JOptionPane.showInputDialog(null, "Enter state name: ");
						break;
					case 3:
						option2 = "postal code";
						input = JOptionPane.showInputDialog(null, "Enter postal code: ");
						break;
					default:
						break;
				}
				Statement stmt = conn.createStatement();
				String sqlStatement = String.format("SELECT \"business\".\"Name\", \"address\", \"city\", \"state\", \"postal code\" FROM \"business\" LEFT JOIN \"Address\" ON \"Address\".\"Business_ID\"=\"business\".\"business_id\" WHERE \"%s\"=\'%s\' ORDER BY \"business\".\"rating\" DESC LIMIT 10", option2, input);
				ResultSet result = stmt.executeQuery(sqlStatement);
				String output = "";
				while (result.next()){
					output += result.getString("Name")+"\n"+result.getString("address")+", "+result.getString("city")+", "+result.getString("state")+" "+result.getString("postal code")+"\n\n";
				}
				JOptionPane.showMessageDialog(null, output);
			}
		} catch (Exception e) {
			System.out.println("Error accessing Database.");
		}
	}
}