import java.sql.*;
import java.util.Scanner;
//import javax.swing.JOptionPane;

/*
CSCE 315
9-25-2019 Original
2/7/2020 Update for AWS
 */
public class jdbcpostgreSQL {
  public static void main(String args[]) {
    //dbSetup hides my username and password
    dbSetup my = new dbSetup();
    //Building the connection
	  Connection conn = null;
      try {
        //Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(
          "jdbc:postgresql://csce-315-db.engr.tamu.edu/db911_group11_theintroverts",
           my.user, my.pswd);
     } catch (Exception e) {
        e.printStackTrace();
        System.err.println(e.getClass().getName()+": "+e.getMessage());
        System.exit(0);
     }//end try catch
     System.out.println("Opened database successfully");
     String cus_lname = "";
     try{
		boolean initPass = true;
		while(initPass) {
	   //Printing options for Users, changeable based on design
		   System.out.println("0: Close");
		   System.out.println("1: Checkin");
		   System.out.println("2: Address");
		   System.out.println("3: Parking");
		   System.out.println("4: Business");
		   System.out.println("5: Review (WIP)");
		   System.out.println("6: User Compliments");
		   System.out.println("7: Tip (WIP)");
		   System.out.println("8: User");
		   System.out.println("9: Hours");
		   //Getting input from user
			Scanner input = new Scanner(System.in);

			System.out.println("Input desired command (enter \"1\" for checkin): ");
			int option = input.nextInt();

			String business_name = "";
			boolean name_bool = false;
			String user_id = "";
			boolean user_bool = false;
			if(option != 1 && option != 6 && option != 8 && option != 0){
				System.out.println("Enter desired business name or enter N/A: ");
				Scanner input2 = new Scanner(System.in);
				business_name = input2.nextLine();

				if(business_name.equals("N/A")){
					name_bool = false;
				}
				else{
					name_bool = true;
				}
			}
			else if(option == 6 || option == 8 && option != 0){
				System.out.println("Enter desired user ID or enter N/A");
				Scanner input2 = new Scanner(System.in);
				user_id = input2.nextLine();

				if(user_id.equals("N/A")){
					user_bool = false;
				}
				else{
					user_bool = true;
				}
			}
	
			switch(option) {
				case 0:
					System.out.println("Close selected");
					initPass = false;
					break;
				case 1:
					System.out.println("Checkin selected");
					initPass = false;
					CheckIn.main(conn);
					break;
				case 2:
					System.out.println("Address selected");
					initPass = false;
					Address.main(conn, business_name, name_bool);
					break;
				case 3:
					System.out.println("Parking selected");
					initPass = false;
					Parking.main(conn, business_name, name_bool);
					break;
				case 4:
					System.out.println("Business selected");
					initPass = false;
					Business.main(conn, business_name, name_bool);
					break;
				case 5:
					System.out.println("Review selected");
					initPass = false;
					Review.main(conn, business_name, name_bool);
					break;
				case 6:
					System.out.println("User Compliments selected");
					initPass = false;
					UserCompliments.main(conn, user_id, user_bool);
					break;
				case 7:
					System.out.println("Tip selected");
					initPass = false;
					Tip.main(conn, business_name, name_bool);
					break;
				case 8:
					System.out.println("User selected");
					initPass = false;
					User.main(conn, user_id, user_bool);
					break;
				case 9:
					System.out.println("Hours selected");
					initPass = false;
					Hours.main(conn, business_name, name_bool);
					break;
				 default:
					System.out.println("Invalid argument");
					initPass = false;
					break;
			}
		}
	   //create a statement object
       //Statement stmt = conn.createStatement();
       //create an SQL statement
       //String sqlStatement = "SELECT \"Name\" FROM \"User\" LIMIT 10";
       //send statement to DBMS
       //ResultSet result = stmt.executeQuery(sqlStatement);

       //OUTPUT
       //System.out.println("Output");
       //System.out.println("______________________________________");
       //while (result.next()) {
       //  System.out.println(result.getString("Name"));
       //}
   } catch (Exception e){
     System.out.println("Error accessing Database.");
   }
    //closing the connection
    try {
      conn.close();
      System.out.println("Connection Closed.");
    } catch(Exception e) {
      System.out.println("Connection NOT Closed.");
    }//end try catch
  }//end main
}//end Class
