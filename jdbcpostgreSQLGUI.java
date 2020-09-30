import java.sql.*;
import javax.swing.JOptionPane;
import java.util.Scanner;
//import java.sql.DriverManager;
/*
CSCE 315
9-25-2019
 */
 
 
/* public class jdbcpostgreSQLGUI {
  public static void main(String args[]) {
    dbSetup my = new dbSetup();
    //Building the connection
     Connection conn = null;
     try {
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/db911_group11_theintroverts",
           my.user, my.pswd);
     } catch (Exception e) {
        e.printStackTrace();
        System.err.println(e.getClass().getName()+": "+e.getMessage());
        System.exit(0);
     }//end try catch
     JOptionPane.showMessageDialog(null,"Opened database successfully");
     String cus_lname = "";
     try{
     //create a statement object
       Statement stmt = conn.createStatement();
       //create an SQL statement
       String sqlStatement = "SELECT \"Name\" FROM \"User\" LIMIT 10";
       //send statement to DBMS
       ResultSet result = stmt.executeQuery(sqlStatement);

       //OUTPUT
       JOptionPane.showMessageDialog(null,"10 names from User");
       //System.out.println("______________________________________");
       while (result.next()) {
         //System.out.println(result.getString("cus_lname"));
         cus_lname += result.getString("Name")+"\n";
       }
   } catch (Exception e){
     JOptionPane.showMessageDialog(null,"Error accessing Database.");
   }
   JOptionPane.showMessageDialog(null,cus_lname);
    //closing the connection
    try {
      conn.close();
      JOptionPane.showMessageDialog(null,"Connection Closed.");
    } catch(Exception e) {
      JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
    }//end try catch
  }//end main
}//end Class
 */

public class jdbcpostgreSQLGUI {
  public static void main(String args[]) {
    //dbSetup hides my username and password
    dbSetup my = new dbSetup();
    //Building the connection
	  Connection conn = null;
      try {
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(
          "jdbc:postgresql://csce-315-db.engr.tamu.edu/db911_group11_theintroverts",
           my.user, my.pswd);
     } catch (Exception e) {
        e.printStackTrace();
        System.err.println(e.getClass().getName()+": "+e.getMessage());
        System.exit(0);
     }//end try catch
     //System.out.println("Opened database successfully");
	 JOptionPane.showMessageDialog(null,"Opened database successfully");
     String cus_lname = "";
     try{
		boolean initPass = true;
		while(initPass) {
	   //Printing options for Users, changeable based on design
		   /* System.out.println("0: Close");
		   System.out.println("1: Checkin");
		   System.out.println("2: Address");
		   System.out.println("3: Parking");
		   System.out.println("4: Business");
		   System.out.println("5: Review (WIP)");
		   System.out.println("6: User Compliments");
		   System.out.println("7: Tip (WIP)");
		   System.out.println("8: User");
		   System.out.println("9: Hours"); */
		   //Getting input from user
		   String option = JOptionPane.showInputDialog(null,"0: Close\n"
										    + "1: Checkin\n"
											+ "2: Address\n"
											+ "3: Parking\n"
											+ "4: Business\n"
											+ "5: Review (WIP)\n"
											+ "6: User Compliments\n"
											+ "7: Tip (WIP)\n"
											+ "8: User\n"
											+ "9: Hours\n"
											+ "Input desired command (enter \"1\" for checkin): ");
			//Scanner input = new Scanner(System.in);

			//System.out.println("Input desired command (enter \"1\" for checkin): ");
			//int option = input.nextInt();
			//String option = JOptionPane.showInputDialog(null, "Input desired command (enter \"1\" for checkin): ");
			int optionInt = Integer.parseInt(option);
			String business_name = "";
			boolean name_bool = false;
			String user_id = "";
			boolean user_bool = false;
			if(optionInt != 6 && optionInt != 8 && optionInt != 0){
				//System.out.println("Enter desired business name or enter N/A");
				//Scanner input2 = new Scanner(System.in);
				//business_name = input2.nextLine();
				business_name = JOptionPane.showInputDialog(null, "Enter desired business name or enter N/A\n"
																+ "Examples: \"Burger King\" or \"Starbucks\"");
				
				if(business_name.equals("N/A")){
					name_bool = false;
				}
				else{
					name_bool = true;
				}
			}
			else if(optionInt == 6 || optionInt == 8 && optionInt != 0){
				//System.out.println("Enter desired user ID or enter N/A");
				//Scanner input2 = new Scanner(System.in);
				//user_id = input2.nextLine();
				user_id = JOptionPane.showInputDialog(null, "Enter desired user ID or enter N/A\n"
														  + "Examples: \"--2vR0DIsmQ6WfcSzKWigw\"");
				if(user_id.equals("N/A")){
					user_bool = false;
				}
				else{
					user_bool = true;
				}
			}
	
			switch(optionInt) {
				case 0:
					//System.out.println("Close selected");
					JOptionPane.showMessageDialog(null,"Close selected");
					initPass = false;
					break;
				case 1:
					//System.out.println("Checkin selected");
					JOptionPane.showMessageDialog(null,"Checkin selected");
					initPass = false;
					CheckIn.main(conn, business_name, name_bool);
					break;
				case 2:
					//System.out.println("Address selected");
					JOptionPane.showMessageDialog(null,"Address selected");
					initPass = false;
					Address.main(conn, business_name, name_bool);
					break;
				case 3:
					//System.out.println("Parking selected");
					JOptionPane.showMessageDialog(null,"Parking selected");
					initPass = false;
					Parking.main(conn, business_name, name_bool);
					break;
				case 4:
					//System.out.println("Business selected");
					JOptionPane.showMessageDialog(null,"Business selected");
					initPass = false;
					Business.main(conn, business_name, name_bool);
					break;
				case 5:
					//System.out.println("Review selected");
					JOptionPane.showMessageDialog(null,"Review selected");
					initPass = false;
					Review.main(conn, business_name, name_bool);
					break;
				case 6:
					//System.out.println("User Compliments selected");
					JOptionPane.showMessageDialog(null,"User Compliments selected");
					initPass = false;
					UserCompliments.main(conn, user_id, user_bool);
					break;
				case 7:
					//System.out.println("Tip selected");
					JOptionPane.showMessageDialog(null,"Tip selected");
					initPass = false;
					Tip.main(conn, business_name, name_bool);
					break;
				case 8:
					//System.out.println("User selected");
					JOptionPane.showMessageDialog(null,"User selected");
					initPass = false;
					User.main(conn, user_id, user_bool);
					break;
				case 9:
					//System.out.println("Hours selected");
					JOptionPane.showMessageDialog(null,"Hours selected");
					initPass = false;
					Hours.main(conn, business_name, name_bool);
					break;
				 default:
					//System.out.println("Invalid argument");
					JOptionPane.showMessageDialog(null,"Invalid argument");
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
     //System.out.println("Error accessing Database.");
	 JOptionPane.showMessageDialog(null,"Error accessing Database.");
   }
    //closing the connection
    try {
      conn.close();
      //System.out.println("Connection Closed.");
	  JOptionPane.showMessageDialog(null,"Connection Closed.");
    } catch(Exception e) {
      //System.out.println("Connection NOT Closed.");
	  JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
    }//end try catch
  }//end main
}//end Class
