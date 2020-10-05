import java.sql.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Hours{
	public static void main(Connection conn, String business_name, String business_id, boolean name_bool) {
		try{
            String output = "";
            String txtoption = "";
			Scanner input = new Scanner(System.in);
			if(name_bool == true){
                txtoption = JOptionPane.showInputDialog(null, "1: Open? for: " + business_name + "\n"
														+ "2: Hours for: " + business_name + "\n"
														+ "3: Business ID for: " + business_name);
				/*System.out.println("1: Open? for: " + business_name);
				System.out.println("2: Hours for: " + business_name);
				System.out.println("3: Business ID for: " + business_name);*/
			}
			else{
                txtoption = JOptionPane.showInputDialog(null, "1: Open? for first 10 companies: " + business_name + "\n"
														+ "2: Hours for first 10 companies: " + business_name + "\n"
														+ "3: Business ID for first 10 companies: " + business_name);
				/*System.out.println("1: Open? for first 10 companies ");
				System.out.println("2: Hours for first 10 companies");
				System.out.println("3: Business ID for first 10 companies");*/
			}
			int option = Integer.parseInt(txtoption);

			//create a statement object
			Statement stmt = conn.createStatement();

			if(name_bool == true){
				String sqlStatement = "SELECT * FROM \"business\" FULL OUTER JOIN \"Hours\" ON \"Hours\".\"Business_ID\" = \"business\".\"business_id\" WHERE \"business\".\"Name\"=\'" + business_name + "\'";
				ResultSet result = stmt.executeQuery(sqlStatement);	

				switch(option){
                    case 1:
                    output += "Business Name                | Is Open\n" +
                    "_______________________________\n";
						//System.out.println("Business Name                | Is Open");
						//System.out.println("_______________________________");

						while(result.next()){
                            output += business_name + " | " + result.getString("Is Open") + "\n"; 
							/*System.out.print(business_name);
							System.out.print(" | ");
							System.out.println(result.getString("Is Open"));*/
                        }
                        JOptionPane.showMessageDialog(null, output);
						break;
                    case 2:
                        output += "Business Name                | Hours\n" +
                        "_______________________________\n";
						//System.out.println("Business Name                | Hours");
						//System.out.println("_______________________________");
                        output += business_name + " | Monday | Tuesday | Wednesday | Thursday | Friday | Saturday | Sunday\n";
						while(result.next()){
                            output += business_name + " | " + result.getString("Monday") + " | " + result.getString("Tuesday") + " | " + result.getString("Wednesday") + " | " + result.getString("Thursday")
                            + " | " + result.getString("Friday") + " | " + result.getString("Saturday") + " | " + result.getString("Sunday") + "\n";
                        }
                        //System.out.println(business_name + " | Monday | Tuesday | Wednesday | Thursday | Friday | Saturday | Sunday");
                        //System.out.println(output);
                        JOptionPane.showMessageDialog(null, output);
						break;
                    case 3:
                        output += "Business Name                | Business ID\n" +
                        "_______________________________\n";
						//System.out.println("Business Name                | Business ID");
						//System.out.println("_______________________________");

						while(result.next()){
                            output += business_name + " | " + result.getString("Business_ID") + "\n";
							/*System.out.print(business_name);
							System.out.print(" | ");
							System.out.println(result.getString("Business_ID"));*/
                        }
                        JOptionPane.showMessageDialog(null, output);
						break;
                    default:
                        output += "Invalid Argument: Closing\n";
                        //System.out.println("Invalid Argument: Closing");
                        JOptionPane.showMessageDialog(null, output);
						break;
				}
			}
			else{
                String sqlStatement = "SELECT * FROM \"Hours\" FULL OUTER JOIN \"business\" ON \"Hours\".\"Business_ID\" = \"business\".\"business_id\" LIMIT 10";
				ResultSet result = stmt.executeQuery(sqlStatement);

                switch(option){
                    case 1:
                    output += "Business Name                | Is Open\n" +
                    "_______________________________\n";
						//System.out.println("Business Name                | Is Open");
						//System.out.println("_______________________________");

						while(result.next()){
                            output += result.getString("Name") + " | " + result.getString("Is Open") + "\n"; 
							/*System.out.print(business_name);
							System.out.print(" | ");
							System.out.println(result.getString("Is Open"));*/
                        }
                        JOptionPane.showMessageDialog(null, output);
						break;
                    case 2:
                        output += "Business Name                | Hours\n" +
                        "_______________________________\n";
						//System.out.println("Business Name                | Hours");
						//System.out.println("_______________________________");
                        output += "Business" + " | Monday | Tuesday | Wednesday | Thursday | Friday | Saturday | Sunday\n";
						while(result.next()){
                            output += result.getString("Name") + " | " + result.getString("Monday") + " | " + result.getString("Tuesday") + " | " + result.getString("Wednesday") + " | " + result.getString("Thursday")
                            + " | " + result.getString("Friday") + " | " + result.getString("Saturday") + " | " + result.getString("Sunday") + "\n";
                        }
                        //System.out.println(business_name + " | Monday | Tuesday | Wednesday | Thursday | Friday | Saturday | Sunday");
                        //System.out.println(output);
                        JOptionPane.showMessageDialog(null, output);
						break;
                    case 3:
                        output += "Business Name                | Business ID\n" +
                        "_______________________________\n";
						//System.out.println("Business Name                | Business ID");
						//System.out.println("_______________________________");

						while(result.next()){
                            output += result.getString("Name") + " | " + result.getString("Business_ID") + "\n";
							/*System.out.print(business_name);
							System.out.print(" | ");
							System.out.println(result.getString("Business_ID"));*/
                        }
                        JOptionPane.showMessageDialog(null, output);
						break;
                    default:
                        output += "Invalid Argument: Closing\n";
                        //System.out.println("Invalid Argument: Closing");
                        JOptionPane.showMessageDialog(null, output);
						break;
				}
                /*
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
				}*/
			}				
		}
		catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error accessing Database.");
			//System.out.println("Error accessing Database.");
		}
	}
}
