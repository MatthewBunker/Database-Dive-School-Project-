import java.sql.*;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Vector;
import java.util.Set;

public class Business{
	public static void main(Connection conn, String business_name, String business_id, boolean name_bool) {
		try{
			String output = "";
			String txtoption = "";
			Scanner input = new Scanner(System.in);
			if(name_bool == true){
				txtoption = JOptionPane.showInputDialog(null, "1: Categories for: " + business_name + "\n"
														+ "2: Take out for: " + business_name + "\n"
														+ "3: Rating for: " + business_name + "\n"
														+ "4: Business ID for: " + business_name);
				/*System.out.println("1: Categories for: " + business_name);
				System.out.println("2: Take out for: " + business_name);
				System.out.println("3: Rating for: " + business_name);
				System.out.println("4: Business ID for: " + business_name);*/
			}
			else{
				txtoption = JOptionPane.showInputDialog(null, "1: Categories for first 10 companies \n" 
															+ "2: Take out for first 10 companies\n"
															+ "3: Rating for first 10 companies\n"
															+ "4: Business ID for first 10 companies\n"
															+ "5: Name of first 10 companies");
				/*System.out.println("1: Categories for first 10 companies ");
				System.out.println("2: Take out for first 10 companies");
				System.out.println("3: Rating for first 10 companies");
				System.out.println("4: Business ID for first 10 companies");
				System.out.println("5: Name of first 10 companies");*/
			}
			int option = Integer.parseInt(txtoption);

			//create a statement object
			Statement stmt = conn.createStatement();

			if(name_bool == true){
				String sqlStatement = "SELECT * FROM \"business\" WHERE \"Name\"=\'" + business_name + "\'";
				ResultSet result = stmt.executeQuery(sqlStatement);	

				switch(option){
					case 1:
						output += "Business Name                | Category\n" +
						"_______________________________\n";
						//System.out.println("Business Name                | Category");
						//System.out.println("_______________________________");

						while(result.next()){
							output += result.getString("Name") + " | " + result.getString("category") + "\n"; 
							/*System.out.print(result.getString("Name"));
							System.out.print(" | ");
							System.out.println(result.getString("category"));*/
						}
						JOptionPane.showMessageDialog(null, output);
						break;
					case 2:
						output += "Business Name                | Take out\n" +
						"_______________________________\n";
						//System.out.println("Business Name                | Take out");
						//System.out.println("_______________________________");

						while(result.next()){
							output += result.getString("Name") + " | " + result.getString("take out ") + "\n";
							//System.out.print(result.getString("Name"));
							//System.out.print(" | ");
							//take out has invisible space at end of it
							//System.out.println(result.getString("take out "));
						}
						JOptionPane.showMessageDialog(null, output);
						break;
					case 3:
						output += "Business Name                | Rating\n" +
						"_______________________________\n";
						//System.out.println("Business Name                | Rating");
						//System.out.println("_______________________________");

						while(result.next()){
							output += result.getString("Name") + " | " + result.getString("rating") + "\n";
							/*System.out.print(result.getString("Name"));
							System.out.print(" | ");
							System.out.println(result.getString("rating"));*/
						}
						JOptionPane.showMessageDialog(null, output);
						break;
					case 4:
						output += "Business Name                | Business ID\n" +
						"_______________________________\n";
						//System.out.println("Business Name                | Business ID");
						//System.out.println("_______________________________");

						while(result.next()){
							output += result.getString("Name") + " | " + result.getString("business_id") + "\n";
							/*System.out.print(result.getString("Name"));
							System.out.print(" | ");
							System.out.println(result.getString("business_id"));*/
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
				String sqlStatement = "SELECT * FROM \"business\" LIMIT 10";
				ResultSet result = stmt.executeQuery(sqlStatement);	

				switch(option){
					case 1:
						output += "Business Name                | Category\n" +
						"_______________________________\n";
						//System.out.println("Business Name                | Category");
						//System.out.println("_______________________________");

						while(result.next()){
							output += result.getString("Name") + " | " + result.getString("category") + "\n"; 
							/*System.out.print(result.getString("Name"));
							System.out.print(" | ");
							System.out.println(result.getString("category"));*/
						}
						JOptionPane.showMessageDialog(null, output);
						break;
					case 2:
						output += "Business Name                | Take out\n" +
						"_______________________________\n";
						//System.out.println("Business Name                | Take out");
						//System.out.println("_______________________________");

						while(result.next()){
							output += result.getString("Name") + " | " + result.getString("take out ") + "\n";
							//System.out.print(result.getString("Name"));
							//System.out.print(" | ");
							//take out has invisible space at end of it
							//System.out.println(result.getString("take out "));
						}
						JOptionPane.showMessageDialog(null, output);
						break;
					case 3:
						output += "Business Name                | Rating\n" +
						"_______________________________\n";
						//System.out.println("Business Name                | Rating");
						//System.out.println("_______________________________");

						while(result.next()){
							output += result.getString("Name") + " | " + result.getString("rating") + "\n";
							/*System.out.print(result.getString("Name"));
							System.out.print(" | ");
							System.out.println(result.getString("rating"));*/
						}
						JOptionPane.showMessageDialog(null, output);
						break;
					case 4:
						output += "Business Name                | Business ID\n" +
						"_______________________________\n";
						//System.out.println("Business Name                | Business ID");
						//System.out.println("_______________________________");

						while(result.next()){
							output += result.getString("Name") + " | " + result.getString("business_id") + "\n";
							/*System.out.print(result.getString("Name"));
							System.out.print(" | ");
							System.out.println(result.getString("business_id"));*/
						}
						JOptionPane.showMessageDialog(null, output);
						break;
					case 5:
						output += "Business Name\n_______________________________\n";
						//System.out.println("Business Name");
						//System.out.println("_______________________________");

						while(result.next()){
							output += result.getString("Name") + "\n";
							//System.out.println(result.getString("Name"));
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
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error accessing Database.");
			//System.out.println("Error accessing Database.");
		}
	}

	public static void shortest_chain_main(Connection conn, String business_1, String business_2){
		//h
	}

	public static void franchise_restaurant_main(Connection conn, String state){
		try{
			HashMap<String, Integer> business = new HashMap<String, Integer>();
			Vector <String> business_vec = new Vector<String>();
			Vector <String> franchises = new Vector<String>();
			String output = "";
			Statement stmt = conn.createStatement();
			String sqlStatement = String.format("SELECT * FROM \"business\" FULL JOIN \"Address\" ON \"Address\".\"Business_ID\"=\"business\".\"business_id\" WHERE \"Address\".\"state\"= \'" + state + "\' AND \"business\".\"rating\" >= 3.5");
			System.out.println(sqlStatement);
			ResultSet result = stmt.executeQuery(sqlStatement);

			while(result.next()){
				String temp = result.getString("Name");
				business_vec.add(temp);
			}


			for(String name : business_vec){
				Integer count = business.get(name);
				if(count == null){
					business.put(name, 1);
				}
				else{
					//System.out.println("here");
					business.put(name, ++count);
				}
			}

			for(String name : business.keySet()){
				Integer count = business.get(name);
				if(count > 1){
					franchises.add(name);
				}
			}

			for(int i = 0; i < franchises.size(); i++){
				//sqlstatement = String.format("dfd");
			}

			//JOptionPane.showMessageDialog(null, output);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error accessing Database.");
		}
		
	}

	public static void local_restaurant_main(Connection conn, String city){
		//h
	}

	public static void rated_business_main(Connection conn, String category, String city){
		//h
	}
}