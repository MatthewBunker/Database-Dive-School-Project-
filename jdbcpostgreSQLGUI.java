import java.sql.*;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
//import java.sql.DriverManager;
/*
CSCE 315
9-25-2019
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
	 JOptionPane.showMessageDialog(null,"Opened database successfully");
     String cus_lname = "";
     try{
		boolean initPass = true;
		while(initPass) {
		//Printing options for Users, changeable based on design
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
			int textOpt = JOptionPane.showConfirmDialog(null, "Print to a txt file?");
			int optionInt = Integer.parseInt(option);
			String business_name = "";
			boolean name_bool = false;
			String user_id = "";
			boolean user_bool = false;
			if(optionInt != 6 && optionInt != 8 && optionInt != 0){
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
					JOptionPane.showMessageDialog(null,"Close selected");
					initPass = false;
					break;
				case 1:
					JOptionPane.showMessageDialog(null,"Checkin selected");
					initPass = false;
					CheckIn.main(conn, business_name, name_bool);
					break;
				case 2:
					JOptionPane.showMessageDialog(null,"Address selected");
					initPass = false;
					Address.main(conn, business_name, name_bool);
					break;
				case 3:
					JOptionPane.showMessageDialog(null,"Parking selected");
					initPass = false;
					Parking.main(conn, business_name, name_bool);
					break;
				case 4:
					JOptionPane.showMessageDialog(null,"Business selected");
					initPass = false;
					Business.main(conn, business_name, name_bool);
					break;
				case 5:
					JOptionPane.showMessageDialog(null,"Review selected");
					initPass = false;
					Review.main(conn, business_name, name_bool);
					break;
				case 6:
					JOptionPane.showMessageDialog(null,"User Compliments selected");
					initPass = false;
					UserCompliments.main(conn, user_id, user_bool);
					break;
				case 7:
					JOptionPane.showMessageDialog(null,"Tip selected");
					initPass = false;
					Tip.main(conn, business_name, name_bool);
					break;
				case 8:
					JOptionPane.showMessageDialog(null,"User selected");
					initPass = false;
					User.main(conn, user_id, user_bool);
					break;
				case 9:
					JOptionPane.showMessageDialog(null,"Hours selected");
					initPass = false;
					Hours.main(conn, business_name, name_bool);
					break;
				 default:
					JOptionPane.showMessageDialog(null,"Invalid argument");
					initPass = false;
					break;
			}
		}
		
   } catch (Exception e){
	 JOptionPane.showMessageDialog(null,"Error accessing Database.");
   }
    //closing the connection
    try {
      conn.close();
	  JOptionPane.showMessageDialog(null,"Connection Closed.");
    } catch(Exception e) {
	  JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
    }//end try catch
  }//end main
}//end Class
