import java.sql.*;
import java.util.Scanner;

public class User{
	public static void main(Connection conn, String user_id, boolean user_bool) {
		try{
			Scanner input = new Scanner(System.in);
			if(user_bool == true){
				System.out.println("1: Name of User with ID: " + user_id);
			}
			else{
                System.out.println("1: First 10 User ID's");
                System.out.println("2: First 10 User Names");
			}
			int option = input.nextInt();

			//create a statement object
			Statement stmt = conn.createStatement();

			if(user_bool == true){
				String sqlStatement = "SELECT * FROM \"User\" WHERE \"User_ID\"=\'" + user_id + "\'";
				ResultSet result = stmt.executeQuery(sqlStatement);	

				switch(option){
					case 1:
						System.out.println("User ID                | User Name");
						System.out.println("_______________________________");

						while(result.next()){
							System.out.print(result.getString("USER_ID"));
							System.out.print(" | ");
							System.out.println(result.getString("Name"));
						}
						break;
					default:
						System.out.println("Invalid Argument: Closing");
						break;
				}
			}
			else{
				String sqlStatement = "SELECT * FROM \"User\" LIMIT 10";
				ResultSet result = stmt.executeQuery(sqlStatement);

				switch(option){
					case 1:
						System.out.println("User ID");
						System.out.println("_______________________________");

						while(result.next()){
							System.out.println(result.getString("User_ID"));
						}
						break;
					case 2:
						System.out.println("User Name");
						System.out.println("_______________________________");

						while(result.next()){
							System.out.println(result.getString("Name"));
						}
						break;
					default:
						System.out.println("Invalid Argument: Closing");
						break;
				}
			}				
		}
		catch(Exception e){
			System.out.println("Error accessing Database.");
		}
	}
}
