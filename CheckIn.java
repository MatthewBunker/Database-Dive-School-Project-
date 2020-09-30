import java.sql.*;
import java.util.Scanner;
import java.text.SimpleDateFormat;  
import java.util.Date;  

//display parking record(s) of corresponding business(es)
public class CheckIn{
	public static void main(Connection conn, String business_id, boolean id_bool) {
		try {
			if (id_bool == true){
				System.out.println("0: Close");
		   		System.out.println("1: I want to check in");
		   		System.out.println("2: I want to see check in record");

		   		Scanner input = new Scanner(System.in);
		   		System.out.println("Input desired command (enter \"0\" for Close): ");
				int option = input.nextInt();

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
					System.out.println("look for checkin record of " + business_id);
					// need to print out record for business_name
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
