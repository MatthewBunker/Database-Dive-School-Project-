import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Parking{
	public static void main(Connection conn, String business_name, String business_id, boolean name_bool) {
		try {
			JDialog parking_dialog = new JDialog();
			JOptionPane optionPane;
			if (name_bool == false){
				String output = "";
				if(business_name != ""){
					Statement stmt = conn.createStatement();
					String sqlStatement = String.format("SELECT \"Street\", \"Garage\", \"Validated\", \"Lot\", \"Valet\" FROM \"business\" LEFT JOIN \"Parking\" ON \"Parking\".\"Business_ID\"=\"business\".\"business_id\" WHERE \"business\".\"Name\"=\'%s\'", business_name);
					ResultSet result = stmt.executeQuery(sqlStatement);
					while(result.next()) {
						output += "Validate Parking: "+result.getString("Validated")+"\n"+"Valet Parking: "+result.getString("Valet")+"\n"+"Street Parking: "+result.getString("Street")+"\n"+"Garage Parking: "+result.getString("Garage")+"\n"+"Lot Parking: "+result.getString("Lot")+"\n";
					}
				}
				if (business_id != ""){
					Statement stmt = conn.createStatement();
					String sqlStatement = String.format("SELECT \"Street\", \"Garage\", \"Validated\", \"Lot\", \"Valet\" FROM \"business\" LEFT JOIN \"Parking\" ON \"Parking\".\"Business_ID\"=\"business\".\"business_id\" WHERE \"business\".\"business_id\"=\'%s\'", business_id);
					System.out.println(sqlStatement);
					ResultSet result = stmt.executeQuery(sqlStatement);
					while(result.next()) {
						output += "Validate Parking: "+result.getString("Validated")+"\n"+"Valet Parking: "+result.getString("Valet")+"\n"+"Street Parking: "+result.getString("Street")+"\n"+"Garage Parking: "+result.getString("Garage")+"\n"+"Lot Parking: "+result.getString("Lot")+"\n";
					}
				}
				Object[] inputFields = {output};
				optionPane = new JOptionPane(inputFields, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
				parking_dialog.setContentPane(optionPane);
				parking_dialog.setTitle("Parking Info");
				parking_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				parking_dialog.pack();
				parking_dialog.setLocationRelativeTo(null);
				parking_dialog.setVisible(true);
			} else {
				JDialog input_dialog = new JDialog();
				JDialog checkbox_dialog = new JDialog();
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
							input_dialog.dispose();
							checkbox_dialog.setVisible(true);
						}
					}
				});

				input_dialog.setTitle("Parking Input");
				input_dialog.setContentPane(inputPane);
				input_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				input_dialog.pack();
				input_dialog.setLocationRelativeTo(null);
				input_dialog.setVisible(true);

				JLabel title = new JLabel("Select the parking options");
				JCheckBox check1 = new JCheckBox("Validate parking");
				JCheckBox check2 = new JCheckBox("Valet parking");
				JCheckBox check3 = new JCheckBox("Street parking");
				JCheckBox check4 = new JCheckBox("Garage parking");
				JCheckBox check5 = new JCheckBox("Lot parking");
				JButton check_search = new JButton("Search");
				check_search.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						checkbox_dialog.dispose();
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
						String statement = "";
						if(check1.isSelected()){
							statement += " AND \"Parking\".\"Validated\" = 't'";
						}
						if(check2.isSelected()){
							statement += " AND \"Parking\".\"Valet\" = 't'";
						}
						if(check3.isSelected()){
							statement += " AND \"Parking\".\"Street\" = 't'";
						}
						if(check4.isSelected()){
							statement += " AND \"Parking\".\"Garage\" = 't'";
						}
						if(check5.isSelected()){
							statement += " AND \"Parking\".\"Lot\" = 't'";
						}
						output_method(conn, area, area_name, statement);
					}
				});

				Object[] checkbox = {title, check1, check2, check3, check4, check5, check_search};
				JOptionPane checkPane = new JOptionPane(checkbox, JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
				checkbox_dialog.setContentPane(checkPane);
				checkbox_dialog.setTitle("Parking Input");
				checkbox_dialog.setContentPane(checkPane);
				checkbox_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				checkbox_dialog.pack();
				checkbox_dialog.setLocationRelativeTo(null);
			}
		} catch (Exception e) {
			System.out.println("Error accessing Database.");
		}
	}

	public static void output_method(Connection conn, String area, String area_name, String statement){
		try{
			JDialog output_dialog = new JDialog();
			Statement stmt = conn.createStatement();
			String sqlStatement = String.format("SELECT \"business\".\"Name\" FROM ((\"business\" INNER JOIN \"Parking\" ON \"Parking\".\"Business_ID\"=\"business\".\"business_id\") INNER JOIN \"Address\" ON \"Address\".\"Business_ID\"=\"business\".\"business_id\") WHERE \"Address\".\"%s\"=\'%s\'%s ORDER BY \"business\".\"rating\" DESC LIMIT 10", area, area_name, statement);
			ResultSet result = stmt.executeQuery(sqlStatement);
			String output = "";
			output += "Top 10 business in "+area_name+" with selected parking option avaliable.\n";
			while (result.next()){
				output += result.getString("Name")+"\n";
			}
			JOptionPane outputPane = new JOptionPane(output, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
			output_dialog.setContentPane(outputPane);
			output_dialog.setTitle("Parking Info");
			output_dialog.setContentPane(outputPane);
			output_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			output_dialog.pack();
			output_dialog.setLocationRelativeTo(null);
			output_dialog.setVisible(true);
		} catch (Exception e){
			System.out.println("Error accessing Database.");
		}
	}
}