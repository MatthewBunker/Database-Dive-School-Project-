import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.*;

public class CheckIn{
	public static void main(Connection conn, String business_name, String business_id, boolean name_bool) {
		try {
			if (name_bool == false){
				Statement stmt = conn.createStatement();
				String sqlStatement = "";
				JLabel title = new JLabel();
				if(!business_name.isBlank()){
					title.setText("Check-in Info for business "+business_name);
					sqlStatement = String.format("SELECT \"Checkin\".\"Date\" FROM \"business\" LEFT JOIN \"Checkin\" ON \"Checkin\".\"Business_ID\"=\"business\".\"business_id\" WHERE \"business\".\"Name\"=\'%s\'", business_name);
				}
				if(!business_id.isBlank()){
					title.setText("Check-in Info for business "+business_id);
					sqlStatement = String.format("SELECT \"Checkin\".\"Date\" FROM \"business\" LEFT JOIN \"Checkin\" ON \"Checkin\".\"Business_ID\"=\"business\".\"business_id\" WHERE \"business\".\"business_id\"=\'%s\'", business_id);
				}
				ResultSet result = stmt.executeQuery(sqlStatement);
				String[] arr = {};
				while(result.next()) {
					arr = result.getString("Date").split(", ", 0);
				}
				JList text = new JList<String>(arr);
				JScrollPane scrollPane = new JScrollPane(text);
				Box content = Box.createVerticalBox();
				content.add(title);
				content.add(scrollPane);
				JOptionPane.showMessageDialog(null, content, "Check-in Info", JOptionPane.INFORMATION_MESSAGE);
				jdbcpostgreSQLGUI.close_conn(conn);
			} else {
				JOptionPane.showMessageDialog(null, "Please Enter Business Name or ID!", "Check-in Info", JOptionPane.INFORMATION_MESSAGE);
				jdbcpostgreSQLGUI.close_conn(conn);
			}
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error accessing Database.");
		} 
	}
}
