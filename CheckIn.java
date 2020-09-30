import java.sql.*;
import java.util.Scanner;
import java.text.SimpleDateFormat;  
import java.util.Date;
import javax.swing.JOptionPane;

//display parking record(s) of corresponding business(es)
public class CheckIn{
	public static void main(Connection conn, String business_id, boolean id_bool) {
		try {
			String output = "";
			JOptionPane.showInputDialog(null,"Welcome! Press any key to continue.");

			// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			Statement stmt = conn.createStatement();
			String sqlStatement = String.format("SELECT * FROM \"Checkin\" LIMIT 2"); // SELECT * FROM "Checkin" LIMIT 2;
			ResultSet result = stmt.executeQuery(sqlStatement);	

			output += "Business_ID          | Date\n";

			while (result.next()){
				output += result.getString("Business_ID") + " | " + result.getString("Date") + "\n"; 
			}

			JOptionPane.showMessageDialog(null, output);
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error accessing Database.");
		} 
	} // end of main
} // end of CheckIn
