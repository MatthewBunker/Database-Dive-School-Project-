import java.sql.*;
import java.util.HashMap;
import java.util.Vector;
import java.util.Collections;
import javax.swing.*;
import java.awt.event.*;

public class Business{
	public static void main(Connection conn, String business_name, String business_id, boolean name_bool) {
		try{
			if(name_bool == false){
				Statement stmt = conn.createStatement();
				String sqlStatement = "";
				if(!business_name.isBlank()){
					sqlStatement = String.format("SELECT * FROM \"business\" WHERE \"business\".\"Name\"=\'%s\'", business_name);
				}
				if (!business_id.isBlank()){
					sqlStatement = String.format("SELECT * FROM \"business\" WHERE \"business_id\"=\'%s\'", business_id);
				}
				ResultSet result = stmt.executeQuery(sqlStatement);
				String output = "";
				while(result.next()) {
					output += "Business Name: "+result.getString("Name")+"\n";
					output += "Business ID: "+result.getString("business_id")+"\n";
					output += "Category: "+result.getString("category")+"\n";
					output += "Rating: "+result.getString("rating")+"\n";
				}
				JOptionPane.showMessageDialog(null, output, "Business Info", JOptionPane.INFORMATION_MESSAGE);
				jdbcpostgreSQLGUI.close_conn(conn);
			} else {
				JDialog input_dialog = new JDialog();
				JTextField input = new JTextField();
				JButton search_btn = new JButton("Search");
				Object[] inputFields = {"Enter the Category of the Business: ", "Ex: Auto Repair",input, search_btn};

				search_btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!input.getText().isBlank()){
							input_dialog.dispose();
							output_method(conn, input.getText());
						}
					}
				});

				JOptionPane inputPane = new JOptionPane(inputFields, JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
				input_dialog.setTitle("Business Category Search");
				input_dialog.setContentPane(inputPane);
				input_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				input_dialog.pack();
				input_dialog.setLocationRelativeTo(null);
				input_dialog.setVisible(true);
			}
		} catch (Exception e){
			System.out.println("Error accessing Database.");
		}
	}

	public static void output_method(Connection conn, String category){
		try {
			Statement stmt = conn.createStatement();
			String sqlStatement = String.format("SELECT \"business_id\", \"Name\", \"rating\" FROM \"business\" WHERE \"category\" LIKE \'%s%s%s\'", "%",category, "%");
			ResultSet result = stmt.executeQuery(sqlStatement);
			Vector<String> arr = new Vector<String>();
			while(result.next()) {
				arr.add(result.getString("business_id")+" | "+result.getString("Name")+" | "+result.getString("rating"));
			}
			JList list = new JList<String>(arr);
			JLabel title = new JLabel("Businesses with "+category+" category");
			JLabel column = new JLabel("       Business ID        |     Name     |  Rating    ");
			JScrollPane scrollPane = new JScrollPane(list);
			Box content = Box.createVerticalBox();
			content.add(title);
			content.add(column);
			content.add(scrollPane);
			JOptionPane.showMessageDialog(null, content, "Business Info", JOptionPane.INFORMATION_MESSAGE);
			jdbcpostgreSQLGUI.close_conn(conn);
		} catch (Exception e) {
			System.out.println("Error accessing Database.");
		}
	}

	public static void shortest_chain_main(Connection conn, String business_1, String business_2){
		try{
			Statement stmt = conn.createStatement();
			String sqlStatement = String.format("SELECT \"User_ID\", \"Review_ID\" FROM \"reviewstars\" WHERE \"business_id\"= business_id_1");
			/*
			SELECT "Name"."User"
			FROM "Review"
			INNER JOIN "User" ON "User"."User_ID"="Review"."User_ID"
			INNER JOIN "reviewstars" ON "reviewstars"."review_id"="Review"."Review_ID"
			WHERE "reviewstars"."stars" >= 3 and "Review"."Business_ID" = business_id_1 and "Review"."Business_ID"= business_id_2;
			*/
			
			ResultSet result = stmt.executeQuery(sqlStatement);

		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error accessing Database.");
		}

	}

	public static void franchise_restaurant_main(Connection conn, String state){
		try{
			HashMap<String, Integer> business = new HashMap<String, Integer>();
			Vector <String> business_vec = new Vector<String>();
			Vector <String> franchises = new Vector<String>();
			Vector<Double> lat = new Vector<Double>();
			Vector<Double> longi = new Vector<Double>();
			Vector<String> franchise_names = new Vector<String>();
			String output = "";
			Statement stmt = conn.createStatement();
			String sqlStatement = String.format("SELECT \"business\".\"Name\" FROM \"business\" FULL JOIN \"Address\" ON \"Address\".\"Business_ID\"=\"business\".\"business_id\" WHERE \"Address\".\"state\"= \'" + state + "\' AND \"business\".\"rating\" >= 3.5");
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
				String company = franchises.elementAt(i);
				StringBuffer sb = new StringBuffer(company);
				for(int k = 0; k< company.length(); k++){
					if(company.charAt(k) == '\''){
						sb.insert(k + 1, "\'");
					}
				}
				sqlStatement = String.format("SELECT \"business\".\"Name\", \"business\".\"latitude\", \"business\".\"longitude\" FROM \"business\" FULL JOIN \"Address\" ON \"business\".\"business_id\"= \"Address\".\"Business_ID\" WHERE \"business\".\"Name\"= \'" + sb + "\' AND \"Address\".\"state\"= \'" + state + "\'");
				result = stmt.executeQuery(sqlStatement);

				while(result.next()){
					Double latitude = Double.parseDouble(result.getString("latitude"));
					Double longitude = Double.parseDouble(result.getString("longitude"));
					lat.add(latitude);
					longi.add(longitude);
					franchise_names.add(result.getString("Name"))	;	
				}
			}

			Vector<String> Top5 = new Vector<String>();
			Top5 = maxDist(lat, longi, franchise_names);

			output = "Top 5 Business with furthest location spread and rating over 3.5\n" + "--------------------------------------------------\n";
			for(int j = 0; j < 5; j++){
				output += Top5.elementAt(j) + "\n";
			}
			JOptionPane.showMessageDialog(null, output);		
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error accessing Database.");
		}	
	}

	// Function calculates distance
		// between two points
		static Double dist(Double x1, Double x2, Double y1, Double y2){
			Double x0 = x1 - x2;
			Double y0 = y1 - y2;
			Double ans = (x0 * x0) + (y0 * y0);
			return ans;	
		}
	
		// Function to find the maximum
		// distance between any two points
		static Vector<String> maxDist(Vector<Double> lat, Vector<Double> longi, Vector<String> franchise_names){
			Vector<String> Top5 = new Vector<String>();
			Vector<Double> Top5_dist = new Vector<Double>();
		
			Top5_dist.add(0, 0.0);
			Top5_dist.add(0, 0.0);
			Top5_dist.add(0, 0.0);
			Top5_dist.add(0, 0.0);
			Top5_dist.add(0, 0.0);

			Top5.add(0, "temp");
			Top5.add(0, "temp");
			Top5.add(0, "temp");
			Top5.add(0, "temp");
			Top5.add(0, "temp");
	
			// Iterate over all possible pairs
			for (int i = 0; i < lat.size() - 1; i++) {
				if(franchise_names.elementAt(i).equals(franchise_names.elementAt(i+1))){
					Double distance = dist(lat.elementAt(i), lat.elementAt(i+1),longi.elementAt(i), longi.elementAt(i+1));
					Double min = Collections.min(Top5_dist);
					if(distance > min && !Top5.contains(franchise_names.elementAt(i))){
						for(int j = 0; j < 5; j++){
							if(Top5_dist.elementAt(j) == min){
								Top5_dist.set(j, distance);
								Top5.set(j, franchise_names.elementAt(i));
							}
						}
					}
				}
			}
			return Top5;
		}

	public static void local_restaurant_main(Connection conn, String city, String state){
		try{
			HashMap<String, Integer> business = new HashMap<String, Integer>();
			Vector <String> business_vec = new Vector<String>();
			Vector <String> business_id = new Vector<String>();
			Vector <String> local = new Vector<String>();
			String output = "";
			Statement stmt = conn.createStatement();
			String sqlStatement = String.format("SELECT \"business\".\"Name\" FROM \"business\" FULL JOIN \"Address\" ON \"Address\".\"Business_ID\"=\"business\".\"business_id\" WHERE \"Address\".\"city\"= \'" + city + "\' AND \"Address\".\"state\"= \'" + state + "\'");
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
					business.put(name, ++count);
				}
			}

			for(String name : business.keySet()){
				Integer count = business.get(name);
				if(count == 1){
					local.add(name);
				}
			}

			for(int i = 0; i < local.size(); i++){
				String company = local.elementAt(i);
				StringBuffer sb = new StringBuffer(company);
				for(int k = 0; k< company.length(); k++){
					if(company.charAt(k) == '\''){
						sb.insert(k + 1, "\'");
					}
				}

				sqlStatement = String.format("SELECT \"business\".\"business_id\" FROM \"business\" WHERE \"business\".\"Name\"= \'" + sb + "\'");
				//System.out.println(sqlStatement);
				result = stmt.executeQuery(sqlStatement);
				while(result.next()){
					business_id.add(result.getString("business_id"));
				}
			}
			//error is somewhere in lines 233-243
			int Max_count = 0;
			int tip_count = 0;
			String best_local = "temp";
			for(int i = 0; i < business_id.size(); i++){
				String local_business_id = business_id.elementAt(i);
				sqlStatement = String.format("SELECT COUNT(\"tip\".\"Business_ID\") FROM \"tip\" WHERE \"tip\".\"Business_ID\"= \'" + local_business_id + "\'");
				//System.out.println(sqlStatement);
				result = stmt.executeQuery(sqlStatement);

				while(result.next()){
					tip_count = Integer.parseInt(result.getString("count"));
					//System.out.println(tip_count);
				}
				
				if(tip_count > Max_count){
					Max_count = tip_count;
					best_local = local_business_id;
				}
			}

			if(!best_local.equals("temp")){
				String ans = "";
				sqlStatement = String.format("SELECT \"business\".\"Name\" FROM \"business\" WHERE \"business\".\"business_id\"= \'" + best_local + "\'");
				result = stmt.executeQuery(sqlStatement);

				while(result.next()){
					ans = result.getString("Name");
				}
				output = "Top Local Business with most Tips\n" + "--------------------------------------------------\n";
				output += "Business: " + ans + "\n" + "Tip Count: " + String.valueOf(Max_count);
				JOptionPane.showMessageDialog(null, output);
				//System.out.println(ans + " " + Max_count);
			}
			else{
				JOptionPane.showMessageDialog(null, "No business in this city were local and had a tip");
				//System.out.println("No business in this city were local and had a tip");
			}
			
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error accessing Database.");
		}
	}

	public static void rated_business_main(Connection conn, String category, String city){
		//h
	}
}