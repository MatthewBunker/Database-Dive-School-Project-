import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Address{
	public static void main(Connection conn, String business_name, String business_id, boolean name_bool) {
		try {
			//create a statement object
			if (name_bool == false){
				Statement stmt = conn.createStatement();
				String sqlStatement = "";
				if(!business_name.isBlank()){
					sqlStatement = String.format("SELECT \"address\", \"city\", \"state\", \"postal code\" FROM \"business\" LEFT JOIN \"Address\" ON \"Address\".\"Business_ID\"=\"business\".\"business_id\" WHERE \"business\".\"Name\"=\'%s\'", business_name);
				}
				if(!business_id.isBlank()){
					sqlStatement = String.format("SELECT \"address\", \"city\", \"state\", \"postal code\" FROM \"business\" LEFT JOIN \"Address\" ON \"Address\".\"Business_ID\"=\"business\".\"business_id\" WHERE \"business\".\"business_id\"=\'%s\'", business_id);
				}
				ResultSet result = stmt.executeQuery(sqlStatement);
				String output = "";
				while(result.next()) {
					output += result.getString("address")+", "+result.getString("city")+", "+result.getString("state")+" "+result.getString("postal code")+"\n";
				}
				JOptionPane.showMessageDialog(null, "Address information for "+business_name + "\n" + output);
				jdbcpostgreSQLGUI.close_conn(conn);
			} else {
				JDialog input_dialog = new JDialog();
				JLabel input_title = new JLabel("Search for Businesses within: ");
				JLabel ct_title = new JLabel("City Name: ");
				JTextField ct_input = new JTextField();
				JLabel st_title = new JLabel("State Abbreviation: ");
				JTextField st_input = new JTextField();
				JLabel pc_title = new JLabel("Postal Code: ");
				JTextField pc_input = new JTextField();
				JButton input_search = new JButton("Search");
				Object[] inputFields = {input_title, ct_title, ct_input, st_title, st_input, pc_title, pc_input, input_search};
				JOptionPane inputPane = new JOptionPane(inputFields, JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

				ct_input.getDocument().addDocumentListener(new DocumentListener() {
					@Override
					public void insertUpdate(DocumentEvent de){
					   event(de);
					}
				
					@Override
					public void removeUpdate(DocumentEvent de) {
						event(de);
					}
				
					@Override
					public void changedUpdate(DocumentEvent de){
						event(de);
					}
				
					private void event(DocumentEvent de){
						if(ct_input.getText().equals("")){
							st_input.setEnabled(true);
							pc_input.setEnabled(true);
						} else {
							st_input.setEnabled(false);
							pc_input.setEnabled(false);
						}
					}
				});
				st_input.getDocument().addDocumentListener(new DocumentListener() {
					@Override
					public void insertUpdate(DocumentEvent de){
					   event(de);
					}
				
					@Override
					public void removeUpdate(DocumentEvent de) {
						event(de);
					}
				
					@Override
					public void changedUpdate(DocumentEvent de){
						event(de);
					}
				
					private void event(DocumentEvent de){
						if(st_input.getText().equals("")){
							ct_input.setEnabled(true);
							pc_input.setEnabled(true);
						} else {
							ct_input.setEnabled(false);
							pc_input.setEnabled(false);
						}
					}
				});
				pc_input.getDocument().addDocumentListener(new DocumentListener() {
					@Override
					public void insertUpdate(DocumentEvent de){
					   event(de);
					}
				
					@Override
					public void removeUpdate(DocumentEvent de) {
						event(de);
					}
				
					@Override
					public void changedUpdate(DocumentEvent de){
						event(de);
					}
				
					private void event(DocumentEvent de){
						if(pc_input.getText().equals("")){
							ct_input.setEnabled(true);
							st_input.setEnabled(true);
						} else {
							ct_input.setEnabled(false);
							st_input.setEnabled(false);
						}
					}
				});
				input_search.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!ct_input.getText().isBlank() || !st_input.getText().isBlank() || !pc_input.getText().isBlank()){
							String area = "";
							String area_name = "";
							if(!ct_input.getText().isBlank()){
								area = "city";
								area_name = ct_input.getText();
							}
							if(!st_input.getText().isBlank()){
								area = "state";
								area_name = st_input.getText();
							}
							if(!pc_input.getText().isBlank()){
								area = "postal code";
								area_name = pc_input.getText();
							}
							output_method(conn, area, area_name);
							input_dialog.dispose();
						}
					}
				});

				input_dialog.setTitle("Parking Input");
				input_dialog.setContentPane(inputPane);
				input_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				input_dialog.pack();
				input_dialog.setLocationRelativeTo(null);
				input_dialog.setVisible(true);
			}
		} catch (Exception e) {
			System.out.println("Error accessing Database.");
		}
	}

	public static void output_method(Connection conn, String area, String area_name){
		try{
			JDialog output_dialog = new JDialog();
			Statement stmt = conn.createStatement();
			String sqlStatement = String.format("SELECT \"business\".\"Name\", \"address\", \"city\", \"state\", \"postal code\" FROM \"business\" LEFT JOIN \"Address\" ON \"Address\".\"Business_ID\"=\"business\".\"business_id\" WHERE \"%s\"=\'%s\' ORDER BY \"business\".\"rating\" DESC LIMIT 10", area, area_name);
			ResultSet result = stmt.executeQuery(sqlStatement);
			String output = "Top 10 businesses in "+area_name+"\n";
			while (result.next()){
				output += result.getString("Name")+"\n"+result.getString("address")+", "+result.getString("city")+", "+result.getString("state")+" "+result.getString("postal code")+"\n\n";
			}
			JOptionPane outputPane = new JOptionPane(output, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
			output_dialog.setContentPane(outputPane);
			output_dialog.setTitle("Parking Info");
			output_dialog.setContentPane(outputPane);
			output_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			output_dialog.pack();
			output_dialog.setLocationRelativeTo(null);
			output_dialog.setVisible(true);
			jdbcpostgreSQLGUI.close_conn(conn);
		} catch (Exception e){
			System.out.println("Error accessing Database.");
		}
	}
}