package Account_Manage;

import java.awt.*;
import java.io.*;
import javax.swing.*;

public class Login extends JFrame{
	
	
	JLabel lbllog,lblname,lblpass;
	JTextField txtn;
	JPasswordField txtpass;
	JButton blog,breg,bexi;
	
	
	
	Login(){
		
		lbllog = new JLabel("LOG IN");
		lbllog.setFont(new Font("Arial Black",Font.PLAIN,20));
		lbllog.setBounds(150,50, 100, 15);
		add(lbllog);
		
		
		lblname = new JLabel("Username: ");
		lblname.setBounds(70,100,100, 15);
		add(lblname);
		
		txtn = new JTextField();
		txtn.setBounds(140,100,150,20);
		add(txtn);
		
		lblpass = new JLabel("Password:");
		lblpass.setBounds(70, 140, 100, 15);
		add(lblpass);
		
		txtpass = new JPasswordField();
		txtpass.setBounds(140, 140, 150, 20);
		add(txtpass);
		
		blog = new JButton("Login");
		blog.setBounds(150,180,90,20);
		add(blog);
		
		breg = new JButton("Register");
		breg.setBounds(10,310, 90, 20);
		add(breg);
		
		bexi = new JButton("Exit");
		bexi.setBounds(280,310, 90, 20);
		add(bexi);
				
		blog.addActionListener(e->{login();});
		breg.addActionListener(e -> {new Register();dispose();});
		bexi.addActionListener(e->{dispose();});
		
		
		
		setLayout(null);
		setTitle("Account Management System");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setSize(400,400);
	}
	
	void login() {

	    String username = txtn.getText().trim();
	    String password = new String(txtpass.getPassword());

	    if (username.isEmpty()) {

	        JOptionPane.showMessageDialog(null,
	                "Username required");

	        return;
	    }

	    if (password.isEmpty()) {

	        JOptionPane.showMessageDialog(null,
	                "Password required");

	        return;
	    }

	    try (BufferedReader br =
	            new BufferedReader(new FileReader("users.txt"))) {

	        String line;

	        while ((line = br.readLine()) != null) {

	            String[] data = line.split("#");

	            if (data.length == 3) {

	                String storedUser = data[1];
	                String storedPass = data[2];

	                if (username.equals(storedUser)
	                        && password.equals(storedPass)) {

	                    JOptionPane.showMessageDialog(null,
	                            "Login Successful");

	                    new Main_Interface();

	                    dispose();

	                    return;
	                }
	            }
	        }

	        JOptionPane.showMessageDialog(null,
	                "Invalid Username or Password");

	    } catch (Exception e) {

	        JOptionPane.showMessageDialog(null,
	                "users.txt not found");
	    }
	}

	public static void main(String[] args) {
		
		new Login();
		

	}

}
