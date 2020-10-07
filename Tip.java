import java.sql.*;
import javax.swing.JOptionPane;
import java.io.FileWriter;

public class Tip{
	public static void main(Connection conn, String user_id, String business_id) {
		try {
			Statement stmt = conn.createStatement();
			String sqlStatement = "";
			String title = "";
			String choice = "";
			if(!user_id.isBlank()){
				sqlStatement = String.format("SELECT * FROM \"tip\" WHERE \"User_ID\"=\'%s\'", user_id);
				title += "Tips from User: "+user_id+"\n";
				choice = "Business_ID";
			}
			if(!business_id.isBlank()){
				sqlStatement = String.format("SELECT * FROM \"tip\" WHERE \"Business_ID\"=\'%s\'", business_id);
				title += "Tips to Business: "+business_id+"\n";
				choice = "User_ID";
			}
			ResultSet result = stmt.executeQuery(sqlStatement);
			FileWriter op_file = new FileWriter("tip_output_file.txt");
			op_file.write(title);
			op_file.write(String.format("        %s         |       Date       |                                    Text                                    \n",choice));
			while(result.next()){
				op_file.write(result.getString(choice)+" | "+result.getString("Date")+" | "+result.getString("Text")+"\n");
			}
			op_file.close();
			JOptionPane.showMessageDialog(null, "File \"tip_output_file.txt\" has been created.", "Tip Info", JOptionPane.INFORMATION_MESSAGE);
			jdbcpostgreSQLGUI.close_conn(conn);
		} catch (Exception e) {
			System.out.println("Error accessing Database.");
		}
	}
}