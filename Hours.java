import java.sql.*;
import java.util.Scanner;
import java.util.Vector;

public class Hours{
	public static void main(Connection conn, String business_name, boolean name_bool) {
		try{
			Scanner input = new Scanner(System.in);
			if(name_bool == true){
				System.out.println("1: Open? for: " + business_name);
				System.out.println("2: Hours for: " + business_name);
				System.out.println("3: Business ID for: " + business_name);
			}
			else{
				System.out.println("1: Open? for first 10 companies ");
				System.out.println("2: Hours for first 10 companies");
				System.out.println("3: Business ID for first 10 companies");
			}
			int option = input.nextInt();

			//create a statement object
			Statement stmt = conn.createStatement();

			if(name_bool == true){
				String sqlStatement = "SELECT * FROM \"business\" FULL OUTER JOIN \"Hours\" ON \"Hours\".\"Business_ID\" = \"business\".\"business_id\" WHERE \"business\".\"Name\"=\'" + business_name + "\'";
				ResultSet result = stmt.executeQuery(sqlStatement);	

				switch(option){
					case 1:
						System.out.println("Business Name                | Is Open");
						System.out.println("_______________________________");

						while(result.next()){
							System.out.print(business_name);
							System.out.print(" | ");
							System.out.println(result.getString("Is Open"));
						}
						break;
                    case 2:
                        String output="";
						System.out.println("Business Name                | Hours");
						System.out.println("_______________________________");

						while(result.next()){
                            output += business_name + " | " + result.getString("Monday") + " | " + result.getString("Tuesday") + " | " + result.getString("Wednesday") + " | " + result.getString("Thursday")
                            + " | " + result.getString("Friday") + " | " + result.getString("Saturday") + " | " + result.getString("Sunday") + "\n";
                        }
                        System.out.println(business_name + " | Monday | Tuesday | Wednesday | Thursday | Friday | Saturday | Sunday");
                        System.out.println(output);
						break;
					case 3:
						System.out.println("Business Name                | Business ID");
						System.out.println("_______________________________");

						while(result.next()){
							System.out.print(business_name);
							System.out.print(" | ");
							System.out.println(result.getString("Business_ID"));
						}
						break;
					default:
						System.out.println("Invalid Argument: Closing");
						break;
				}
			}
			else{
                String sqlStatement = "SELECT * FROM \"Hours\" FULL OUTER JOIN \"business\" ON \"Hours\".\"Business_ID\" = \"business\".\"business_id\" LIMIT 10";
                System.out.println(sqlStatement);
				ResultSet result = stmt.executeQuery(sqlStatement);

				switch(option){
					case 1:
						System.out.println("Business Name                | Is Open");
						System.out.println("_______________________________");

						while(result.next()){
							System.out.print(result.getString("Name"));
							System.out.print(" | ");
							System.out.println(result.getString("Is Open"));
						}
						break;
                    case 2:
                        String output = "";
						System.out.println("Business Name                | Hours");
						System.out.println("_______________________________");

						while(result.next()){
                            output += result.getString("Name") + " | " + result.getString("Monday") + " | " + result.getString("Tuesday") + " | " + result.getString("Wednesday") + " | " + result.getString("Thursday")
                            + " | " + result.getString("Friday") + " | " + result.getString("Saturday") + " | " + result.getString("Sunday") + "\n";
                        }
                        System.out.println(business_name + " | Monday | Tuesday | Wednesday | Thursday | Friday | Saturday | Sunday");
                        System.out.println(output);
						break;
					case 3:
						System.out.println("Business Name                | Business ID");
						System.out.println("_______________________________");

						while(result.next()){
							System.out.print(result.getString("Name"));
							System.out.print(" | ");
							System.out.println(result.getString("Business_ID"));
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
