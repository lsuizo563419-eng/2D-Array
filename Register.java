package Account_Manage;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.*;

public class Register extends JFrame {
	
	JLabel lblreg,lblus,lblna,lblpass;
	JTextField txtus,txtna;
	JPasswordField txtpass;
	JButton breg,bclear,bback;
	String full;
	
	Register (){
		
		lblreg = new JLabel("REGISTER");
		lblreg.setFont(new Font("Arial Black",Font.PLAIN,20));
		lblreg.setBounds(140,50, 120, 15);
		add(lblreg);
		
		
		lblus = new JLabel("Username: ");
		lblus.setBounds(70,100,100, 15);
		add(lblus);
		
		txtus = new JTextField();
		txtus.setBounds(140,100,150,20);
		add(txtus);
		
		lblpass = new JLabel("Password:");
		lblpass.setBounds(70, 140, 100, 15);
		add(lblpass);
		
		txtpass = new JPasswordField();
		txtpass.setBounds(140, 140, 150, 20);
		add(txtpass);
		
		lblna = new JLabel("Full-Name: ");
		lblna.setBounds(70,180,100, 15);
		add(lblna);
		
		txtna = new JTextField();
		txtna.setBounds(140,180,150,20);
		add(txtna);
		
		breg = new JButton("Register");
		breg.setBounds(80,220,90,20);
		add(breg);
		
		bclear = new JButton("Clear");
		bclear.setBounds(190,220,90,20);
		add(bclear);
		
		bback = new JButton("Back");
		bback.setBounds(10,310, 90, 20);
		add(bback);
	
		breg.addActionListener(e->{add();});
		bclear.addActionListener(e->{clear();});
		bback.addActionListener(e -> {new Login();dispose();});
		
		setLayout(null);
		setTitle("Account Management System");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setSize(400,400);
		
		
		
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
	
	void add () {
		
		char[] cpass = txtpass.getPassword();
	
		String user = txtus.getText();
		String pass= new String(cpass);
		String name = txtna.getText();	
		
		
		if (!validinput()) return;
		
		full =  name + "#" + user + "#" + pass;
		
		savetofile();
		clear();

		JOptionPane.showMessageDialog(this,"Registered Successfully");
		
	}

	void clear () {
		txtus.setText("");
		txtna.setText("");
		txtpass.setText("");
		
	}
	
	void savetofile () {
	
	try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt",true))) {
		bw.write(full);
		bw.newLine();
			
			
			
	} catch (Exception e) {
		e.printStackTrace();
			
		
		}
		
}	
	public static void main(String[] args) {
		new Register();

	}

}
