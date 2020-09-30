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
			String option = JOptionPane.showInputDialog(null,"0: Close\n"
										    + "1: I want to check in\n"
											+ "2: I want to see check in record for this business\n"
											+ "Input desired command (enter \"0\" for Close): ");

			int optionInt = Integer.parseInt(option);
			String option2 = null;
			switch(optionInt) {
			case 0:
				option2 = "Close selected";
				break;
			case 1:
					//System.out.println("your checkin time is: ");
					//System.out.println(format.format(new Date()));
					// need to write this record to database
				option2 = "Check in";
				break;
			case 2:
					/*
					System.out.println("Business_ID                | Date");
					System.out.println("_______________________________");
					//while(result.next()){
					System.out.print(business_id);
					System.out.print("    | ");
					System.out.println(format.format(new Date()));
					//}
					*/
				option2 = "Obtain record";
				break;
			default:
				break;
			}

			Statement stmt = conn.createStatement();
			String sqlStatement = String.format("SELECT * FROM \"CheckIn\" WHERE \"Business_ID\"=\'" + business_id + " LIMIT 2",option2);
			ResultSet result = stmt.executeQuery(sqlStatement);	

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//System.out.println(format.format(new Date())); // test
			// when run, use business id: f9NumwFMBDn751xgFiRbNA
			switch(optionInt){
				case 1:
					/*
					while (result.next()) {
						output += result.getString("") + " | " + result.getString("Useful") + "\n";
					}
					*/
					JOptionPane.showMessageDialog(null,"check in at: " +format.format(new Date()));
					
					break;
				case 2:
					while (result.next()) {
						output += result.getString("Business_ID") + " | " + result.getString("Date") + "\n";
					}
					JOptionPane.showMessageDialog(null,"Business_ID | Date\n" + output);
					break;
				default:
					JOptionPane.showMessageDialog(null,"Invalid Argument");
					break;
			}
		} 
		catch (Exception e) {
			System.out.println("Error accessing Database.");
		} 
	} // end of main
} // end of CheckIn
