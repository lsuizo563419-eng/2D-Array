package Suizo5;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
 
public class Hotel_GUI_System extends JFrame{
	JLabel lblgn,lblrt,lblcid,lblcod;
	JTextField txtgn,txtrt,txtcid,txtcod;
	JButton add,up,del,ex;
	JTable tbl;
	DefaultTableModel mdl;
	JScrollPane tblp;
	String [] head = {"Guest Name","Room Type","Check in Date","Check out Date"};
	
	Hotel_GUI_System() {
		
		lblgn = new JLabel("Guest Name");
		lblgn.setBounds(25,520,100,15);
		add(lblgn);
		
		txtgn = new JTextField();
		txtgn.setBounds(25,540,150,20);
		add(txtgn);
		
		lblrt = new JLabel("Room Type");
		lblrt.setBounds(200, 520, 100, 15);
		add(lblrt);
		
		txtrt = new JTextField();
		txtrt.setBounds(200, 540, 150, 20);
		add(txtrt);
		
		lblcid = new JLabel("Check in Date");
		lblcid.setBounds(375,520,150,20);
		add(lblcid);
				
		txtcid = new JTextField();
		txtcid.setBounds(375,540,150,20);
		add(txtcid);
		
		lblcod = new JLabel("Check out Date");
		lblcod.setBounds(550,520,150,20);
		add(lblcod);
		
		txtcod = new JTextField();
		txtcod.setBounds(550,540,150,20);
		add(txtcod);
		
		add = new JButton ("Add");
		add.setBounds(720, 540, 75, 20);
		add(add);
		
		up = new JButton ("Update");
		up.setBounds(720, 565, 75, 20);
		add(up);
		
		del = new JButton ("Delete");
		del.setBounds(720, 590, 75, 20);
		add(del);
		
		ex = new JButton ("Exit");
		ex.setBounds(720, 615, 75, 20);
		add(ex);
		
		mdl = new DefaultTableModel(new String[] {"Guest Name","Room Type","Check in Date","Check out Date" }, 0);
		tbl = new JTable(mdl);
		tbl.getTableHeader().setReorderingAllowed(false);
		JScrollPane sp = new JScrollPane(tbl);
		sp.setBounds(10, 10, 800, 500);
		add(sp);
		
		tbl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) { 
			int row = tbl.getSelectedRow();
				if (row != -1) {
					txtgn.setText(mdl.getValueAt(row, 0).toString());
					txtrt.setText(mdl.getValueAt(row, 1).toString());
					txtcid.setText(mdl.getValueAt(row, 2).toString());
					txtcod.setText(mdl.getValueAt(row, 3).toString());
			}
			
			}
			});
		
		add.addActionListener(e->{add();});
		up.addActionListener(e -> {update();}); 
		del.addActionListener(e -> {delete();});
		ex.addActionListener(e->{exit();});
		addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) {exit();}});
		
	
	setLayout(null);
	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);	
	setTitle("Hotel Reservation System");	
	setSize(840,730);
	setResizable(false);
	setVisible(true);	
	loadFromFile();	
		

		}
	
	void add () {
		
		String name = txtgn.getText();
		String type = txtrt.getText();
		String in = txtcid.getText();
		String out = txtcod.getText();
		
		if (!validinput()) return;
		
		mdl.addRow(new String[] {name,type,in,out});
		
		savetofile();
		
	}
	
	
	void update () {
		int selectedRow = tbl.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(null, "Select a record to update");
		return;
		}
		
		if (!validinput()) return;
		
	ArrayList<String> lines = new ArrayList<>();
		
	try (BufferedReader br = new BufferedReader(new FileReader("Customer.txt"))) {
			String line;
			int rowIndex = 0;
		while ((line = br.readLine()) != null) {
			
		if (rowIndex == selectedRow) {
			String update = txtgn.getText() + "#" + txtrt.getText() + "#" + txtcid.getText() + "#" + txtcod.getText();				
			lines.add(update);
		}else {
			
			lines.add(line);
			
		}
		rowIndex++;
		}
	} catch (IOException z) {
		System.err.println(z);
		}
	try (BufferedWriter bw = new BufferedWriter(new FileWriter("Customer.txt"))) {
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
			
	}
	
	
	void delete () {
		int selectedRow = tbl.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(null, "Select a record to delete");
		return;
		}
			int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this record?","Confirm Delete",JOptionPane.YES_NO_OPTION);
			if (confirm != JOptionPane.YES_OPTION) return;
		
	ArrayList<String> lines = new ArrayList<>();
	try (BufferedReader br = new BufferedReader(new FileReader("Customer.txt"))) {
		
		String line;
		int rowIndex = 0;
		while ((line = br.readLine()) != null) {
			if (rowIndex != selectedRow) lines.add(line);
			rowIndex++;
		}
	} catch (IOException z) {
		System.err.println(z);
		}
	try (BufferedWriter bw = new BufferedWriter(new FileWriter("Customer.txt"))) {
		
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
	}
	
	boolean validinput() {
		if (txtgn.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Guest Name required");
	        return false;
	    }

	    if (txtrt.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Room Type required");
	        return false;
	    }

	    if (!txtcid.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {
	        JOptionPane.showMessageDialog(this, "Check-in must be (YYYY-MM-DD)");
	        return false;
	    }

	    if (!txtcod.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {
	        JOptionPane.showMessageDialog(this, "Check-out must be (YYYY-MM-DD)");
	        return false;
	    }

	    return true;	
	}
	
	void exit() {
		int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to Exit?","Confirm Exit",JOptionPane.YES_NO_OPTION);
		if (confirm != JOptionPane.YES_OPTION) return;
		dispose();
	}
	
	void savetofile () {
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("Customer.txt"))) {
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
	
	void loadFromFile() {
		mdl.setRowCount(0);
		try (BufferedReader br = new BufferedReader(new FileReader("Customer.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split("#", -1);
				mdl.addRow(data);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	
	void clear (){
		txtgn.setText("");
		txtrt.setText("");
		txtcid.setText("");
		txtcod.setText("");
	}
	
	public static void main(String[] args) {
		new Hotel_GUI_System();

	}

}