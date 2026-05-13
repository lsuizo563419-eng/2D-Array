package Account_Manage;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Main_Interface extends JFrame{

	JLabel lblreg,lblus,lblna,lblpass;
	JTextField txtus,txtna;
	JPasswordField txtpass;
	JButton breg,bclear,bback;
	JButton add,up,del,out,re;
	JTable tbl;
	DefaultTableModel mdl;
	JScrollPane tblp;
	
	String full;
	
	Main_Interface() {
		
		lblus = new JLabel("Username: ");
		lblus.setBounds(70,540,100, 15);
		add(lblus);
		
		txtus = new JTextField();
		txtus.setBounds(140,540,150,20);
		add(txtus);
		
		lblpass = new JLabel("Password:");
		lblpass.setBounds(70, 580, 100, 15);
		add(lblpass);
		
		txtpass = new JPasswordField();
		txtpass.setBounds(140, 580, 150, 20);
		add(txtpass);
		
		lblna = new JLabel("Full-Name: ");
		lblna.setBounds(70,620,100, 15);
		add(lblna);
		
		txtna = new JTextField();
		txtna.setBounds(140,620,150,20);
		add(txtna);
		

		add = new JButton ("Add");
		add.setBounds(450, 540, 75, 20);
		add(add);
		
		re = new JButton ("Reload");
		re.setBounds(450,565,75,20);
		add(re);
		
		
		up = new JButton ("Update");
		up.setBounds(450, 590, 75, 20);
		add(up);
		
		del = new JButton ("Delete");
		del.setBounds(450, 615, 75, 20);
		add(del);
		
		out = new JButton ("Logout");
		out.setBounds(450, 640, 75, 20);
		add(out);
		
		mdl = new DefaultTableModel(new String[] {"Full-Name","Username","Password" }, 0);
		tbl = new JTable(mdl);
		tbl.getTableHeader().setReorderingAllowed(false);
		JScrollPane sp = new JScrollPane(tbl);
		sp.setBounds(10, 10, 510, 500);
		add(sp);
		
		tbl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) { 
			int row = tbl.getSelectedRow();
				if (row != -1) {
					txtus.setText(mdl.getValueAt(row, 0).toString());
					txtpass.setText(mdl.getValueAt(row, 1).toString());
					txtna.setText(mdl.getValueAt(row, 2).toString());				
			}
			
			}
			});
		
		add.addActionListener(e->{add();});
		re.addActionListener(e->{reload();});
		up.addActionListener(e -> {update();}); 
		del.addActionListener(e -> {delete();});
		out.addActionListener(e->{logout();});
		
		
	
	setLayout(null);
	setDefaultCloseOperation(EXIT_ON_CLOSE);	
	setTitle("Account Management System");	
	setSize(550,730);
	setResizable(false);
	setVisible(true);	
	
		

		}
	
	void add () {
		
		char[] cpass = txtpass.getPassword();
		
		String user = txtus.getText();
		String name = txtna.getText();	
		String pass= new String(cpass);
		
		
		if (!validinput()) return;
		
		mdl.addRow(new String[] {name,user,pass});
		
		savetofile();
		
	}	
	
	void reload () {
		mdl.setRowCount(0);
		try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split("#", -1);
				mdl.addRow(data);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
			
	void update () {
		int selectedRow = tbl.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(null, "Select a account to update");
		return;
		}
		
		if (!validinput()) return;
		
	ArrayList<String> lines = new ArrayList<>();
		
	try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
			String line;
			int rowIndex = 0;
			char[] cpass = txtpass.getPassword();
			String pass = new String(cpass);

		while ((line = br.readLine()) != null) {
			
		if (rowIndex == selectedRow) {
			
			String update = txtna.getText()+ txtus.getText() + "#" + pass + "#" ;				
			lines.add(update);
		}else {
			
			lines.add(line);
			
		}
		rowIndex++;
		}
	} catch (IOException z) {
		System.err.println(z);
		}
	try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt"))) {
		for (String record : lines) {
		bw.write(record);
		bw.newLine();
		}
	} catch (IOException z) {
		System.err.println(z);
		}		
			JOptionPane.showMessageDialog(null, "Account Updated Successfully");
			clear();
			
	}
	
	void delete () {
		int selectedRow = tbl.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(null, "Select a account to delete");
		return;
		}
			int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this record?","Confirm Delete",JOptionPane.YES_NO_OPTION);
			if (confirm != JOptionPane.YES_OPTION) return;
		
	ArrayList<String> lines = new ArrayList<>();
	try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
		
		String line;
		int rowIndex = 0;
		while ((line = br.readLine()) != null) {
			if (rowIndex != selectedRow) lines.add(line);
			rowIndex++;
		}
	} catch (IOException z) {
		System.err.println(z);
		}
	try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt"))) {
		
		for (String record : lines) {
			bw.write(record);
			bw.newLine();
		}
	} catch (IOException z) {
		System.err.println(z);
		}
			JOptionPane.showMessageDialog(null, "Account Deleted Successfully");
			clear();
	}
	
	boolean validinput() {
		if (txtus.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Username required");
	        return false;
	    }
		
		char[] cpass = txtpass.getPassword();
		String pass= new String(cpass);
		
	    if (pass.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Password required");
	        return false;
	    }
	    
	    if (txtna.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Full-Name required");
	        return false;
	    }
	    	return true;
	 }
	
	void logout() {
		int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to Logout?","Confirm Logout",JOptionPane.YES_NO_OPTION);
		if (confirm != JOptionPane.YES_OPTION) return;
		new Login(); 
		this.dispose();   
	}
	
	void savetofile () {
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt"))) {
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
	

	void clear (){
		txtus.setText("");
		txtpass.setText("");
		txtna.setText("");
		
	}	
		

	public static void main(String[] args) {
		new Main_Interface();

	}

}
