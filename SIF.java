package Suizo;

import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class SIF extends JFrame{

	
	JLabel lblna,lblcour,lblsec;
	JTextField txtna,txtcour,txtsec;
	JButton btnadd,btnup,btndel,btnclear;
	DefaultTableModel dtm;
	JTable tbl;
	DefaultTableModel mdl;
	JScrollPane sp;
	
	String line;
	SIF(){
	
	lblna = new JLabel("Name");
	lblna.setBounds(10,20,80,10);
	add(lblna);
	
	txtna = new JTextField();
	txtna.setBounds(10,40,130,20);
	add(txtna);
	
	lblcour = new JLabel("Course");
	lblcour.setBounds(150,20,80,10);
	add(lblcour);
	
	txtcour = new JTextField();
	txtcour.setBounds(150,40,130,20);
	add(txtcour);
	
	lblsec = new JLabel("Section");
	lblsec.setBounds(290,20,80,10);
	add(lblsec);
	
	txtsec = new JTextField();
	txtsec.setBounds(290,40,130,20);
	add(txtsec);
	
	btnadd = new JButton("Add");
	btnadd.setBounds(20, 70, 90, 20);
	add(btnadd);
	
	btnup = new JButton("Update");
	btnup.setBounds(119, 70, 90, 20);
	add(btnup);
	
	btndel = new JButton("Delete");
	btndel.setBounds(219, 70, 90, 20);
	add(btndel);	
	
	btnclear = new JButton("Clear");
	btnclear.setBounds(319, 70, 90, 20);
	add(btnclear);
		
	mdl = new DefaultTableModel (new String [] {"Name","Course","Section"},0);	
	tbl = new JTable(mdl);	
	tbl.getTableHeader() .setReorderingAllowed(false);
	sp = new JScrollPane (tbl);
	sp.setBounds(10,100,410,265);
	add(sp);
	
	tbl.addMouseListener(new MouseAdapter (){
		public void mouseClicked(MouseEvent e) {
			int row = tbl.getSelectedRow();			
			if (row!= -1) {
				txtna.setText(mdl.getValueAt(row, 0).toString());
				txtcour.setText(mdl.getValueAt(row, 1).toString());
				txtsec.setText(mdl.getValueAt(row, 2).toString());
			}
		}
	}
	);
	
	btnadd.addActionListener(e->{add();});
	btnup.addActionListener(e->{update();});
	btndel.addActionListener(e->{delete();});
	btnclear.addActionListener(e->{clear();});
		
	setDefaultCloseOperation(EXIT_ON_CLOSE);	
	setTitle("Student Information Form");	
	setLayout(null);	
	setVisible(true);
	setResizable(false);
	setSize(445,405);
	loadFromFile();
	}
	
	void add ()	{
		
		String name = txtna.getText();
		String course = txtcour.getText();
		String section = txtsec.getText();
		
		
		mdl.addRow(new String [] {name,course,section});
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("student.txt"))) {
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
	
	void update () {
		int selectedRow = tbl.getSelectedRow();
		if (selectedRow == -1) {
		JOptionPane.showMessageDialog(null, "Select a student to update");
		return;
		}
		
		ArrayList<String> lines = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader("student.txt"))){
			String line;
			int indexrow =0;
			while ((line = br.readLine()) != null) {
				if (indexrow == selectedRow) {
					String update = txtna.getText() + "#" + txtcour.getText() + "#" + txtsec.getText();
					lines.add(update);
					}else {
					lines.add(line);
					}
				indexrow++;
			}
		}catch(IOException e) {
			System.err.println(e);
		}
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("student.txt"))) {
			for (String record : lines) {
			bw.write(record);
			bw.newLine();
			}
			} catch (IOException z) {
			System.err.println(z);
			}
			loadFromFile();
			JOptionPane.showMessageDialog(null, "Student Updated Successfully");
			clear();
			}

	void delete () {
		int selectedRow = tbl.getSelectedRow();
		if (selectedRow == -1) {
		JOptionPane.showMessageDialog(null, "Select a record to delete");
		return;
		}

		int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this record?","Confirm Delete",
		JOptionPane.YES_NO_OPTION);
		
		if (confirm != JOptionPane.YES_OPTION) return;
		
		ArrayList<String> lines = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader("student.txt"))) {
			
			String line;
			int rowIndex = 0;
			while ((line = br.readLine()) != null) {
				if (rowIndex != selectedRow) lines.add(line);
				rowIndex++;
			}
		} catch (IOException z) {
		System.err.println(z);
		}
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("student.txt"))) {
			for (String record : lines) {
				bw.write(record);
				bw.newLine();
		}
			
		} catch (IOException z) {
		System.err.println(z);
		}
		
		loadFromFile();
		JOptionPane.showMessageDialog(null, "Student Deleted Successfully");
		clear();
		}
		
	void clear() {
		txtna.setText("");
		txtcour.setText("");
		txtsec.setText("");
		
	}
	
	void loadFromFile(){
		
			mdl.setRowCount(0);
			try (BufferedReader br = new BufferedReader(new FileReader("student.txt"))) {
			String line;
				while ((line = br.readLine()) != null) {
					String[] data = line.split("#", -1);
					mdl.addRow(data);
				}
			}catch (Exception e) {

			e.printStackTrace();
			}
	}
	
	public static void main(String[] args) {
		new SIF();

	}

}
