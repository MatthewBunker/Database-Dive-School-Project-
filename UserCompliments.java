import java.sql.*;
import java.util.Scanner;
import java.util.*;

public class UserCompliments{
	public static void main(Connection conn, String user_id, boolean user_bool) {
		//Connection conn = null;
		try {	
			Scanner input = new Scanner(System.in);
			System.out.println("1: Top 10 Useful Users");
			System.out.println("2: Top 10 Funny Users");
			System.out.println("3: Top 10 Cool Users");
			int option = input.nextInt();
			String option2 = null;
			switch (option) {
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
			switch (option) {
				case 1:
					System.out.println("User ID                | Useful");
					System.out.println("_______________________________");
					while (result.next()) {
						System.out.print(result.getString("User_ID"));
						System.out.print(" | ");
						System.out.println(result.getString("Useful"));
					}
					break;
				case 2:
					System.out.println("User ID                | Funny");
					System.out.println("_______________________________");
					while (result.next()) {
						System.out.print(result.getString("User_ID"));
						System.out.print(" | ");
						System.out.println(result.getString("Funny"));
					}
					break;
				case 3:
					System.out.println("User ID                | Cool");
					System.out.println("_______________________________");
					while (result.next()) {
						System.out.print(result.getString("User_ID"));
						System.out.print(" | ");
						System.out.println(result.getString("Cool"));
					}
					break;
				default:
					System.out.println("Invalid Argument: Closing");
					break;
			}
		} catch (Exception e) {
			System.out.println("Error accessing Database.");
		}
	}
}