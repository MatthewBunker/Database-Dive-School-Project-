import java.sql.*;
import java.util.Scanner;
import java.text.SimpleDateFormat;  
import java.util.Date;  

//display parking record(s) of corresponding business(es)
public class CheckIn{
	public static void main(Connection conn, String business_id, boolean id_bool) {
		try {
			System.out.println("0: Close");
		   	System.out.println("1: I want to check in");
		   	System.out.println("2: I want to see check in record for this business");

		   	Scanner input = new Scanner(System.in);
		   	System.out.println("Input desired command (enter \"0\" for Close): ");
			int option = input.nextInt();

			if (id_bool == true){
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println(format.format(new Date())); // test

				/**
				Statement stmt = conn.createStatement();
				String sqlStatement = "SELECT * FROM \"CheckIn\" WHERE \"Business_ID\"=\'" + business_id + "\'";
				ResultSet result = stmt.executeQuery(sqlStatement);	
				System.out.println("can you see me");//test
				**/

				// when run, use business id: f9NumwFMBDn751xgFiRbNA
				switch(option) {
				case 0:
					System.out.println("Close selected");
					break;
				case 1:
					System.out.println("your checkin time is: ");
					System.out.println(format.format(new Date()));
					// need to write this record to database
					break;
				case 2:
					System.out.println("Business_ID                | Date");
					System.out.println("_______________________________");
					//while(result.next()){
						System.out.print(business_id);
						System.out.print("    | ");
						System.out.println(format.format(new Date()));
					//}
					break;
				default:
					System.out.println("Invalid argument");
					break;
				}
			} // end of if		
		} 
		catch (Exception e) {
			System.out.println("Error accessing Database.");
		} 
	} // end of main
} // end of CheckIn
