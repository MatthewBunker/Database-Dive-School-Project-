import java.sql.*;
import javax.swing.JOptionPane;
import java.io.FileWriter;

public class Review{
	public static void main(Connection conn, String user_id, String business_id, String review_id) {
		try {
			Statement stmt = conn.createStatement();
			String sqlStatement = "";
			String title = "";
			if(!user_id.isBlank()){
				sqlStatement = String.format("SELECT * FROM \"Review\" WHERE \"User_ID\"=\'%s\'", user_id);
				title += "Reviews from User: "+user_id+"\n";
				title += "        Review_ID         |        Business_ID         |                                    Text                                    \n";
			}
			if(!business_id.isBlank()){
				sqlStatement = String.format("SELECT * FROM \"Review\" WHERE \"Business_ID\"=\'%s\'", business_id);
				title += "Reviews to Business: "+business_id+"\n";
				title += "        Review_ID         |        User_ID         |                                    Text                                    \n";
			}
			if(!review_id.isBlank()){
				sqlStatement = String.format("SELECT * FROM \"Review\" WHERE \"Review_ID\"=\'%s\'", review_id);
				title += "Review ID: "+business_id+"\n";
				title += "        User_ID         |        Business_ID         |                                    Text                                    \n";
			}
			ResultSet result = stmt.executeQuery(sqlStatement);
			FileWriter op_file = new FileWriter("review_output_file.txt");
			op_file.write(title);
			while(result.next()){
				if(!user_id.isBlank()){
					op_file.write(result.getString("Review_ID")+" | "+result.getString("Business_ID")+" | "+result.getString("Text")+"\n");
				}
				if(!business_id.isBlank()){
					op_file.write(result.getString("Review_ID")+" | "+result.getString("User_ID")+" | "+result.getString("Text")+"\n");
				}
				if(!review_id.isBlank()){
					op_file.write(result.getString("User_ID")+" | "+result.getString("Business_ID")+" | "+result.getString("Text")+"\n");
				}
			}
			op_file.close();
			JOptionPane.showMessageDialog(null, "File \"review_output_file.txt\" has been created.", "Review Info", JOptionPane.INFORMATION_MESSAGE);
			jdbcpostgreSQLGUI.close_conn(conn);
		} catch (Exception e) {
			System.out.println("Error accessing Database.");
		}
	}
}