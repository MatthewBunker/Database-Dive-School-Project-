import java.sql.*;
import java.util.Scanner;

public class Business{
	public static void main(Connection conn, String business_name, boolean name_bool) {
		try{
			Scanner input = new Scanner(System.in);
			if(name_bool == true){
				System.out.println("1: Categories for: " + business_name);
				System.out.println("2: Take out for: " + business_name);
				System.out.println("3: Rating for: " + business_name);
				System.out.println("4: Business ID for: " + business_name);
			}
			else{
				System.out.println("1: Categories for first 10 companies ");
				System.out.println("2: Take out for first 10 companies");
				System.out.println("3: Rating for first 10 companies");
				System.out.println("4: Business ID for first 10 companies");
				System.out.println("5: Name of first 10 companies");
			}
			int option = input.nextInt();

			//create a statement object
			Statement stmt = conn.createStatement();

			if(name_bool == true){
				String sqlStatement = "SELECT * FROM \"business\" WHERE \"Name\"=\'" + business_name + "\'";
				ResultSet result = stmt.executeQuery(sqlStatement);	

				switch(option){
					case 1:
						System.out.println("Business Name                | Category");
						System.out.println("_______________________________");

						while(result.next()){
							System.out.print(result.getString("Name"));
							System.out.print(" | ");
							System.out.println(result.getString("category"));
						}
						break;
					case 2:
						System.out.println("Business Name                | Take out");
						System.out.println("_______________________________");

						while(result.next()){
							System.out.print(result.getString("Name"));
							System.out.print(" | ");
							//take out has invisible space at end of it
							System.out.println(result.getString("take out "));
						}
						break;
					case 3:
						System.out.println("Business Name                | Rating");
						System.out.println("_______________________________");

						while(result.next()){
							System.out.print(result.getString("Name"));
							System.out.print(" | ");
							System.out.println(result.getString("rating"));
						}
						break;
					case 4:
						System.out.println("Business Name                | Business ID");
						System.out.println("_______________________________");

						while(result.next()){
							System.out.print(result.getString("Name"));
							System.out.print(" | ");
							System.out.println(result.getString("business_id"));
						}
						break;
					case 5:
						System.out.println("Business Name");
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
			else{
				String sqlStatement = "SELECT * FROM \"business\" LIMIT 10";
				ResultSet result = stmt.executeQuery(sqlStatement);

				switch(option){
					case 1:
						System.out.println("Business Name                | Category");
						System.out.println("_______________________________");

						while(result.next()){
							System.out.print(result.getString("Name"));
							System.out.print(" | ");
							System.out.println(result.getString("category"));
						}
						break;
					case 2:
						System.out.println("Business Name                | Take out");
						System.out.println("_______________________________");

						while(result.next()){
							System.out.print(result.getString("Name"));
							System.out.print(" | ");
							//take out has invisible space at end of it
							System.out.println(result.getString("take out "));
						}
						break;
					case 3:
						System.out.println("Business Name                | Rating");
						System.out.println("_______________________________");

						while(result.next()){
							System.out.print(result.getString("Name"));
							System.out.print(" | ");
							System.out.println(result.getString("rating"));
						}
						break;
					case 4:
						System.out.println("Business Name                | Business ID");
						System.out.println("_______________________________");

						while(result.next()){
							System.out.print(result.getString("Name"));
							System.out.print(" | ");
							System.out.println(result.getString("business_id"));
						}
						break;
					case 5:
						System.out.println("Business Name");
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