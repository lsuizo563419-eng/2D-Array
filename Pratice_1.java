package Pratice;

import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;


	public class Pratice_1 extends JFrame{
		
		JLabel lblfullname,lblid,lblprogram,lblstatus;
		JTextField txtfullname,txtid,txtprogram,txtstatus;
		JButton btnadd,btnupdate,btnreload,btndelete,btnexit;
		
		
		
		JTable tbl;
		DefaultTableModel mdl;
		JScrollPane sp;
		
		
		String line;
	Pratice_1()	{
		
		lblfullname = new JLabel("Full Name");
		lblfullname.setBounds(20,330 ,100, 15);
		add(lblfullname);
		
		txtfullname = new JTextField();
		txtfullname.setBounds(20,350,150,20);
		add(txtfullname);
		
		lblid = new JLabel("ID Number");
		lblid.setBounds(190,330,100,15);
		add(lblid);
		
		txtid = new JTextField();
		txtid.setBounds(190,350,150,20);
		add(txtid);
		
		lblprogram = new JLabel("Program");
		lblprogram.setBounds(20,370,100,15);
		add(lblprogram);
		
		txtprogram = new JTextField();
		txtprogram.setBounds(20,390,150,20);
		add(txtprogram);
		
		lblstatus = new JLabel("Status");
		lblstatus.setBounds(190,370,100,15);
		add(lblstatus);
		
		txtstatus = new JTextField();
		txtstatus.setBounds(190,390,150,20);
		add(txtstatus);
		
		btnadd = new JButton("Add");
		btnadd.setBounds(380,350,80,20);
		add(btnadd);
		
		btnupdate = new JButton("Update");
		btnupdate.setBounds(470,350,80,20);
		add(btnupdate);
		
		btnreload = new JButton("Reload");
		btnreload.setBounds(380,385,80,20);
		add(btnreload);
		
		btndelete = new JButton("Delete");
		btndelete.setBounds(470,385,80,20);
		add(btndelete);
		
		btnexit = new JButton("Exit");
		btnexit.setBounds(480,425,80,20);
		add(btnexit);
		
		setTitle("Student Records");
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600,500);
		setVisible(true);
		setResizable(false);
		
		btnadd.addActionListener(e->{add();});
		btnupdate.addActionListener(e->{update();});
		btndelete.addActionListener(e->{delete();});
		btnreload.addActionListener(e->{reload();});
		btnexit.addActionListener(e->{System.exit(0);});
		
		
		mdl = new DefaultTableModel (new String [] {"Full-Name","ID Number","Program","Status"},0);
		tbl = new JTable (mdl);
		tbl.getTableHeader().setReorderingAllowed(false);
		sp = new JScrollPane (tbl);
		sp.setBounds(15, 10, 550, 300);
		add(sp);
		
		tbl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tbl.getSelectedRow();
				if(row != -1) {
					txtfullname.setText(mdl.getValueAt(row, 0).toString());
					txtid.setText(mdl.getValueAt(row, 1).toString());
					txtprogram.setText(mdl.getValueAt(row, 2).toString());
					txtstatus.setText(mdl.getValueAt(row, 3).toString());
				}
			}
			
		});
		

	}
		
	void add () {
		
		String name = txtfullname.getText();
		String id = txtid.getText();
		String program = txtprogram.getText();
		String status = txtstatus.getText();
		
		// Input Condition
		
		
		line = name + "#" + id + "#" + program + "#" + status;
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("info.txt",true))){
			bw.write(line);
			bw.newLine();
			
		}catch(IOException e) {
			System.err.println(e);
		}
		
		JOptionPane.showMessageDialog(null, "Student Successfully Added.");
		
		clear();
	}
	
	void update() {
		int selectedrow = tbl.getSelectedRow();
		if(selectedrow == -1) {
			JOptionPane.showMessageDialog(null, "Select a Student to Update.");
			return;
		}
		
		ArrayList <String> line = new ArrayList<>();	
		
		String name = txtfullname.getText();
		String id = txtid.getText();
		String program = txtprogram.getText();
		String status = txtstatus.getText();
		
		// Input Condition
		
		
		String update = name + "#" + id + "#" + program + "#" + status;
		
		try(BufferedReader br = new BufferedReader(new FileReader("info.txt"))){
				String lines;
				int rowindex = 0;
				while((lines = br.readLine()) != null) {
					if(selectedrow == rowindex) {						
					line.add(update);							
					}else {
					line.add(lines);	
					}
					
				rowindex++;
				}	
		}catch(IOException e) {
			System.err.println(e);
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("info.txt"))){
			for(String record : line) {
				bw.write(record);
				bw.newLine();
			}
		}catch(IOException e) {
			System.err.println(e);
		}
	
		JOptionPane.showMessageDialog(null,"Information Succesfully Updated.");
		clear();
	
		
	}
	
	
	void reload () {
		mdl.setRowCount(0);
		
		try(BufferedReader br = new BufferedReader(new FileReader("info.txt"))){
			String line;
			
			while((line = br.readLine())!= null) {
					String data [] = line.split("#", -1);
					mdl.addRow(data);
			
				}

		}catch(IOException e) {
			System.err.println(e);
		}
		
		
	}
	void delete() {
		
		int selectedrow = tbl.getSelectedRow();		
		if (selectedrow == -1) {
			JOptionPane.showMessageDialog(null, "Select Record to Delete");
			return;
		}
		
		int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete record?","Confirm Delete",JOptionPane.YES_NO_OPTION);
		
		if(confirm != JOptionPane.YES_OPTION) {return;}
		
		
		ArrayList <String> line = new ArrayList <>();
		
		try (BufferedReader br = new BufferedReader(new FileReader("info.txt"))){
			String lines;
			int indextrow = 0;
			
			while((lines = br.readLine()) != null) {
				if (indextrow != tbl.getSelectedRow()) {
					line.add(lines);
					indextrow++;	
				}

			}

		}catch (IOException e) {
			System.err.println(e);
		}
			
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("info.txt"))){
			for(String record:line) {
				bw.write(record);
				bw.newLine();
			}
			
		}catch(IOException e) {
			System.err.println(e);
		}
		
		JOptionPane.showMessageDialog(null, "Record Successfully Deleted.");
		clear();
		
		
	}
	
	
	void clear () {
		txtfullname.setText("");
		txtid.setText("");
		txtprogram.setText("");
		txtstatus.setText("");
	}
	public static void main(String[] args) {
		
		new Pratice_1();
	}

}
