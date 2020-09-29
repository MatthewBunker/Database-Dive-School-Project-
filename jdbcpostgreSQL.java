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
		   System.out.println("4: Star Rating");
		   System.out.println("5: Review (WIP)");
		   System.out.println("6: User Compliments");
		   System.out.println("7: Tip (WIP)");
		   //Getting input from user
			Scanner input = new Scanner(System.in);
			System.out.println("Input desired command (enter \"1\" for checkin): ");
			int option = input.nextInt();
			switch(option) {
				case 0:
					break;
				case 1:
					System.out.println("Checkin selected");
					initPass = false;
					break;
				case 2:
					System.out.println("Address selected");
					initPass = false;
					break;
				case 3:
					System.out.println("Parking selected");
					initPass = false;
					break;
				case 4:
					System.out.println("Star rating selected");
					initPass = false;
					break;
				case 5:
					System.out.println("Review selected");
					initPass = false;
					break;
				case 6:
					System.out.println("User Compliments selected");
					initPass = false;
					break;
				case 7:
					System.out.println("Tip selected");
					initPass = false;
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
