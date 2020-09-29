import java.sql.*;
import java.util.Scanner;
import java.util.*;

public class UserCompliments{
	public static void main(Connection conn) {
		//Connection conn = null;
		try {	
			Scanner input = new Scanner(System.in);
			System.out.println("1: Top 10 Useful Users");
			System.out.println("2: Top 10 Funny Users");
			System.out.println("3: Top 10 Cool Users");
			int option = input.nextInt();
			//create a statement object
			Statement stmt = conn.createStatement();
			String sqlStatement = "SELECT * FROM \"User Compliments\" ORDER BY \"Useful\" DESC LIMIT 10";
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