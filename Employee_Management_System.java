package Suizo5;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Employee_Management_System extends JFrame {
	JLabel lblcor, lblid, lblna, lblbr, lblage, lblcvl, lblnat, lblgen, lblcon, lblem, lbldep, lbljt;
	JTextField txtid, txtna, txtbr, txtage, txtnat, txtcon, txtem, txtdep, txtjt;
	JTable tbl;
	DefaultTableModel mdl;
	JComboBox cmb;
	JRadioButton m, f;
	String[] civil = { "Single", "Married", "Widowed", "Separated", "Divorced" };
	JButton add,delete,update;
	ButtonGroup Gender;

	public Employee_Management_System() {
		setLayout(null);
		lblcor = new JLabel("LGS INC");
		lblcor.setBounds(20, 10, 200, 30);
		add(lblcor);

		lblid = new JLabel("Employee ID");
		lblid.setBounds(20, 50, 200, 30);
		add(lblid);

		txtid = new JTextField();
		txtid.setBounds(20, 75, 200, 20);
		add(txtid);

		lblna = new JLabel("Fullname");
		lblna.setBounds(20, 100, 200, 30);
		add(lblna);

		txtna = new JTextField();
		txtna.setBounds(20, 125, 200, 20);
		add(txtna);

		lblbr = new JLabel("Date of Birth");
		lblbr.setBounds(20, 150, 200, 30);
		add(lblbr);

		txtbr = new JTextField();
		txtbr.setBounds(20, 175, 200, 20);
		add(txtbr);

		lblage = new JLabel("Age");
		lblage.setBounds(250, 50, 200, 30);
		add(lblage);

		txtage = new JTextField();
		txtage.setBounds(250, 75, 200, 20);
		add(txtage);

		lblcvl = new JLabel("Civil Status");
		lblcvl.setBounds(250, 100, 200, 30);
		add(lblcvl);

		cmb = new JComboBox(civil);
		cmb.setBounds(250, 125, 200, 20);
		add(cmb);

		lblnat = new JLabel("Nationality");
		lblnat.setBounds(250, 150, 200, 30);
		add(lblnat);

		txtnat = new JTextField();
		txtnat.setBounds(250, 175, 200, 20);
		add(txtnat);

		lblgen = new JLabel("Gender");
		lblgen.setBounds(480, 50, 200, 30);
		add(lblgen);

		m = new JRadioButton("Male");
		m.setBounds(480, 75, 60, 20);
		add(m);

		f = new JRadioButton("Female");
		f.setBounds(540, 75, 70, 20);
		add(f);

		Gender = new ButtonGroup();
		Gender.add(m);
		Gender.add(f);

		lblcon = new JLabel("Contact Number");
		lblcon.setBounds(480, 100, 200, 20);
		add(lblcon);

		txtcon = new JTextField();
		txtcon.setBounds(480, 125, 200, 20);
		add(txtcon);

		lblem = new JLabel("Email");
		lblem.setBounds(480, 150, 200, 20);
		add(lblem);

		txtem = new JTextField();
		txtem.setBounds(480, 175, 200, 20);
		add(txtem);

		lbldep = new JLabel("Department");
		lbldep.setBounds(710, 100, 200, 20);
		add(lbldep);

		txtdep = new JTextField();
		txtdep.setBounds(710, 125, 200, 20);
		add(txtdep);

		lbljt = new JLabel("Job Title/Position");
		lbljt.setBounds(710, 150, 200, 20);
		add(lbljt);

		txtjt = new JTextField();
		txtjt.setBounds(710, 175, 200, 20);
		add(txtjt);

		add = new JButton("Add Employee");
		add.setBounds(275, 215, 150, 20);
		add(add);
		
		update = new JButton("Update Employee");
		update.setBounds(505, 215, 150, 20);
		add(update);
		
		delete = new JButton("Remove Employee");
		delete.setBounds(735, 215, 150, 20);
		add(delete);
		mdl = new DefaultTableModel(new String[] { "Employee ID", "Full Name", "Birth","Age","Civil Status", "Nationality",
												   "Gender","Contact Number", "Email", "Department", "Job Title / Position" }, 0);
		tbl = new JTable(mdl);
		tbl.getTableHeader().setReorderingAllowed(false);

		JScrollPane sp = new JScrollPane(tbl);
		sp.setBounds(15, 250, 950, 300);
		add(sp);
		
		add.addActionListener(e -> {
			addRecord();
		});

		tbl.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) { 
			   int row = tbl.getSelectedRow();

			   if (row != -1) {
			            txtid.setText(mdl.getValueAt(row, 0).toString());
			            txtna.setText(mdl.getValueAt(row, 1).toString());
			            txtbr.setText(mdl.getValueAt(row, 2).toString());
			            txtage.setText(mdl.getValueAt(row, 3).toString());

			           
			            cmb.setSelectedItem(mdl.getValueAt(row, 4).toString());

			            txtnat.setText(mdl.getValueAt(row, 5).toString());

			          
			            String gender = mdl.getValueAt(row, 6).toString();
			            if (gender.equals("Male")) m.setSelected(true);
			            else if (gender.equals("Female")) f.setSelected(true);

			            txtcon.setText(mdl.getValueAt(row, 7).toString());
			            txtem.setText(mdl.getValueAt(row, 8).toString());
			            txtdep.setText(mdl.getValueAt(row, 9).toString());
			            txtjt.setText(mdl.getValueAt(row, 10).toString());
			        }
			    }
			});
			
		

		delete.addActionListener(e -> {
			    int selectedRow = tbl.getSelectedRow();

			    if (selectedRow == -1) {
			        JOptionPane.showMessageDialog(null, "Select a record to delete");
			        return;
			    }

			    int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this record?","Confirm Delete",JOptionPane.YES_NO_OPTION);

			    if (confirm != JOptionPane.YES_OPTION) return;

			    ArrayList<String> lines = new ArrayList<>();

			    try (BufferedReader br = new BufferedReader(new FileReader("Employees.txt"))) {
			        String line;
			        int rowIndex = 0;

			        while ((line = br.readLine()) != null) {
			            if (rowIndex != selectedRow) lines.add(line);
			            rowIndex++;
			        }
			    } catch (IOException z) {
			        System.err.println(z);
			    }

			    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Employees.txt"))) {
			        for (String record : lines) {
			            bw.write(record);
			            bw.newLine();
			        }
			    } catch (IOException z) {
			        System.err.println(z);
			    }
			    loadFromFile();
			    JOptionPane.showMessageDialog(null, "Record Deleted Successfully");
			    clear();
			});
		

		update.addActionListener(e -> {
		    int selectedRow = tbl.getSelectedRow();

		    if (selectedRow == -1) {
		        JOptionPane.showMessageDialog(null, "Select a record to update");
		        return;
		    }

		    ArrayList<String> lines = new ArrayList<>();

		    try (BufferedReader br = new BufferedReader(new FileReader("Employees.txt"))) {
		        String line;
		        int rowIndex = 0;

		        while ((line = br.readLine()) != null) {
		            if (rowIndex == selectedRow) {
		            	String gender = "";
		        		if (m.isSelected()) gender = "Male";
		        		else if (f.isSelected()) gender = "Female";
		            	String update =  txtid.getText() + "#" + txtna.getText() + "#" + txtbr.getText() + "#" + txtage.getText()
		                + "#" + cmb.getSelectedItem().toString() + "#" + txtnat.getText() + "#" + gender + "#"
		                + txtcon.getText() + "#" + txtem.getText() + "#" + txtdep.getText() + "#" + txtjt.getText();
		            	
		            	lines.add(update);
		            }else {
		            	lines.add(line);
		            }
		            rowIndex++;
		        }
		    } catch (IOException z) {
		        System.err.println(z);
		    }

		    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Employees.txt"))) {
		        for (String record : lines) {
		            bw.write(record);
		            bw.newLine();
		        }
		    } catch (IOException z) {
		        System.err.println(z);
		    }
		    loadFromFile();
		    JOptionPane.showMessageDialog(null, "Record Updated Successfully");
		    clear();
		});			
		   
		loadFromFile();
		
		setTitle("Employee Mnagement System");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 600);
		setVisible(true);
	}

	public void addRecord() {

		String id = txtid.getText();
		String na = txtna.getText();
		String br = txtbr.getText();
		String age = txtage.getText();
		String nat = txtnat.getText();
		String cvl = cmb.getSelectedItem().toString();
		String gender = "";
		if (m.isSelected()) gender = "Male";
		else if (f.isSelected()) gender = "Female";
		String con = txtcon.getText();
		String eml = txtem.getText();	
		String dep = txtdep.getText();
		String jbt = txtjt.getText();
		
		mdl.addRow(new String[] {id, na, br, age, cvl, nat, gender, con, eml, dep, jbt});

		saveToFile();

		JOptionPane.showMessageDialog(null, "Record Saved Successfully.");
	}

	public void saveToFile() {
		    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Employees.txt"))) {

		        for (int i = 0; i < mdl.getRowCount(); i++) {
		            for (int j = 0; j < mdl.getColumnCount(); j++) {

		                bw.write(String.valueOf(mdl.getValueAt(i, j)));

		                if (j < mdl.getColumnCount() - 1) {
		                    bw.write("#");
		                }
		            }
		            bw.newLine();
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		   clear();
		}
	
	public void loadFromFile() {

        mdl.setRowCount(0);

        try (BufferedReader br = new BufferedReader(new FileReader("Employees.txt"))) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split("#", -1);
                mdl.addRow(data);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	 
	public void clear() {
		 
		 	txtid.setText("");
		    txtna.setText("");
		    txtbr.setText("");
		    txtage.setText("");
		    txtnat.setText("");
		    txtcon.setText("");
		    txtem.setText("");
		    txtdep.setText("");
		    txtjt.setText("");

		    cmb.setSelectedIndex(0);
		    Gender.clearSelection();
	 }

	public static void main(String[] args) {
		new Employee_Management_System();

	}

	}


