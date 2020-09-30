import java.sql.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class User{
	public static void main(Connection conn, String user_id, boolean user_bool) {
		try{
            String output = "";
            String txtoption = "";
			Scanner input = new Scanner(System.in);
			if(user_bool == true){
                txtoption = JOptionPane.showInputDialog(null, "1: Name of User with ID: " + user_id);
				//System.out.println("1: Name of User with ID: " + user_id);
			}
			else{
                txtoption = JOptionPane.showInputDialog(null, "1: First 10 User ID's \n" 
															+ "2: First 10 User Names");
                //System.out.println("1: First 10 User ID's");
                //System.out.println("2: First 10 User Names");
			}
			int option = Integer.parseInt(txtoption);

			//create a statement object
			Statement stmt = conn.createStatement();

			if(user_bool == true){
				String sqlStatement = "SELECT * FROM \"User\" WHERE \"User_ID\"=\'" + user_id + "\'";
				ResultSet result = stmt.executeQuery(sqlStatement);	

				switch(option){
                    case 1:
                        output += "User ID                | User Name\n" +
						"_______________________________\n";
						//System.out.println("User ID                | User Name");
						//System.out.println("_______________________________");

						while(result.next()){
                            output += result.getString("USER_ID") + " | " + result.getString("Name") + "\n";
							/*System.out.print(result.getString("USER_ID"));
							System.out.print(" | ");
                            System.out.println(result.getString("Name"));
                            */
                        }
                        JOptionPane.showMessageDialog(null, output);
						break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid Argument: Closing");
						//System.out.println("Invalid Argument: Closing");
						break;
				}
			}
			else{
				String sqlStatement = "SELECT * FROM \"User\" LIMIT 10";
				ResultSet result = stmt.executeQuery(sqlStatement);

				switch(option){
                    case 1:
                        output += "User ID\n" +
						"_______________________________\n";
						//System.out.println("User ID");
						//System.out.println("_______________________________");

						while(result.next()){
                            output += result.getString("User_ID") + "\n";
							//System.out.println(result.getString("User_ID"));
                        }
                        JOptionPane.showMessageDialog(null, output);
						break;
                    case 2:
                        output += "User Name\n" +
						"_______________________________\n";
						//System.out.println("User Name");
						//System.out.println("_______________________________");

						while(result.next()){
                            output += result.getString("Name") + "\n";
							//System.out.println(result.getString("Name"));
                        }
                        JOptionPane.showMessageDialog(null, output);
						break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid Argument: Closing");
						//System.out.println("Invalid Argument: Closing");
						break;
				}
			}				
		}
		catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error accessing Database.");
			//System.out.println("Error accessing Database.");
		}
	}
}
