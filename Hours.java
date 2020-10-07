import java.sql.*;
import javax.swing.JOptionPane;

public class Hours{
	public static void main(Connection conn, String business_name, String business_id, boolean name_bool) {
		try{
			if(name_bool == false){
				Statement stmt = conn.createStatement();
				String sqlStatement = "";
				if(!business_name.isBlank()){
					sqlStatement = String.format("SELECT \"business\".\"Name\", \"Hours\".\"Monday\", \"Hours\".\"Tuesday\", \"Hours\".\"Wednesday\", \"Hours\".\"Thursday\", \"Hours\".\"Friday\", \"Hours\".\"Saturday\", \"Hours\".\"Sunday\", \"Hours\".\"Is Open\" FROM \"business\" LEFT JOIN \"Hours\" ON \"Hours\".\"Business_ID\"=\"business\".\"business_id\" WHERE \"business\".\"Name\"=\'%s\'", business_name);
				}
				if(!business_id.isBlank()){
					sqlStatement = String.format("SELECT \"business\".\"Name\", \"Hours\".\"Monday\", \"Hours\".\"Tuesday\", \"Hours\".\"Wednesday\", \"Hours\".\"Thursday\", \"Hours\".\"Friday\", \"Hours\".\"Saturday\", \"Hours\".\"Sunday\", \"Hours\".\"Is Open\" FROM \"business\" LEFT JOIN \"Hours\" ON \"Hours\".\"Business_ID\"=\"business\".\"business_id\" WHERE \"business\".\"business_id\"=\'%s\'", business_id);
				}
				ResultSet result = stmt.executeQuery(sqlStatement);
				String output = "";
				while(result.next()){
					output += "Business Name: "+result.getString("Name")+"\n";
					if(result.getString("Is Open").equals("1")){
						output += "Is Open: Yes\n";
					} else {
						output += "Is Open: No\n";
					}
					output += "Monday: "+result.getString("Monday")+"\n";
					output += "Tuesday: "+result.getString("Tuesday")+"\n";
					output += "Wednesday: "+result.getString("Wednesday")+"\n";
					output += "Thursday: "+result.getString("Thursday")+"\n";
					output += "Friday: "+result.getString("Friday")+"\n";
					output += "Saturday: "+result.getString("Saturday")+"\n";
					output += "Sunday: "+result.getString("Sunday")+"\n";
				}
				JOptionPane.showMessageDialog(null, "Hours Info\n" + output);
				jdbcpostgreSQLGUI.close_conn(conn);
			} else {
				JOptionPane.showMessageDialog(null, "Please Enter Business Name or ID!", "Check-in Info", JOptionPane.INFORMATION_MESSAGE);
				jdbcpostgreSQLGUI.close_conn(conn);
			}
		}catch(Exception e){
			System.out.println("Error accessing Database.");
		}
	}
}