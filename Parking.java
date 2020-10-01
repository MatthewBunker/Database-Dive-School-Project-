import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.*;

public class Parking{
	public static void main(Connection conn, String business_name, boolean name_bool) {
		try {
			//create a statement object
			if (name_bool == true){
				Statement stmt = conn.createStatement();
				String sqlStatement = String.format("SELECT \"Street\", \"Garage\", \"Validated\", \"Lot\", \"Valet\" FROM \"business\" LEFT JOIN \"Parking\" ON \"Parking\".\"Business_ID\"=\"business\".\"business_id\" WHERE \"business\".\"Name\"=\'%s\'", business_name);
				ResultSet result = stmt.executeQuery(sqlStatement);
				String output = "";
				while(result.next()) {
					output += "Validate Parking: "+result.getString("Validated")+"\n"+"Valet Parking: "+result.getString("Valet")+"\n"+"Street Parking: "+result.getString("Street")+"\n"+"Garage Parking: "+result.getString("Garage")+"\n"+"Lot Parking: "+result.getString("Lot")+"\n";
				}
				// System.out.println(result.getString("address"));
				JOptionPane.showMessageDialog(null, "Parking information for "+business_name + "\n" + output);
			} else {
				String option = JOptionPane.showInputDialog(null,"0: Close\n"
										    + "1: Top 10 rating businesses search by city\n"
											+ "2: Top 10 rating businesses search by state\n"
											+ "3: Top 10 rating businesses search by postal code\n"
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
				Box checkbox = Box.createVerticalBox();
				JLabel title = new JLabel("Select the parking options");
				JCheckBox check1 = new JCheckBox("Validate parking");
				JCheckBox check2 = new JCheckBox("Valet parking");
				JCheckBox check3 = new JCheckBox("Street parking");
				JCheckBox check4 = new JCheckBox("Garage parking");
				JCheckBox check5 = new JCheckBox("Lot parking");
				checkbox.add(title);
				checkbox.add(check1);
				checkbox.add(check2);
				checkbox.add(check3);
				checkbox.add(check4);
				checkbox.add(check5);
				JOptionPane.showMessageDialog(null,checkbox);
				String statement = "";
				if(check1.isSelected()){
					statement += " AND \"Parking\".\"Validated\" = 't'";
				}
				if(check2.isSelected()){
					statement += " AND \"Parking\".\"Valet\" = 't'";
				}
				if(check3.isSelected()){
					statement += " AND \"Parking\".\"Street\" = 't'";
				}
				if(check4.isSelected()){
					statement += " AND \"Parking\".\"Garage\" = 't'";
				}
				if(check5.isSelected()){
					statement += " AND \"Parking\".\"Lot\" = 't'";
				}
				Statement stmt = conn.createStatement();
				String sqlStatement = String.format("SELECT \"business\".\"Name\" FROM ((\"business\" INNER JOIN \"Parking\" ON \"Parking\".\"Business_ID\"=\"business\".\"business_id\") INNER JOIN \"Address\" ON \"Address\".\"Business_ID\"=\"business\".\"business_id\") WHERE \"Address\".\"%s\"=\'%s\'%s ORDER BY \"business\".\"rating\" DESC LIMIT 10", option2, input, statement);
				ResultSet result = stmt.executeQuery(sqlStatement);
				String output = "";
				output += "Top 10 business in "+option2+" with selected parking option avaliable.\n";
				while (result.next()){
					output += result.getString("Name")+"\n";
				}
				JOptionPane.showMessageDialog(null, output);
			}
		} catch (Exception e) {
			System.out.println("Error accessing Database.");
		}
	}
}