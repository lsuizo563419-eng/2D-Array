package Suizo;

import javax.swing.*;
import java.awt.Font;
import java.io.*;
import java.util.*;

	

public class Suizo extends JFrame {
	
	
	JTextField txtRecNo,txtStoName,txttotalcost,txttax,txtfinal;
	JLabel lblRec, lblRec2,lblStoName,lbltotcost,lbltax,lblfinal;
	JButton Bclear, BRecord;
	
	public Suizo() {
		
		lblRec = new JLabel("Expense Tracker");
		lblRec.setBounds(160, 10, 200, 30);
		lblRec.setFont(new Font("Arial Black",Font.PLAIN,20));
		add(lblRec);
		
		
		lblRec2 = new JLabel("Receipt Number: ");
		lblRec2.setBounds(40,70,100,30);
		add(lblRec2);
		
		txtRecNo = new JTextField();
		txtRecNo.setBounds(200, 70, 250,30);
		add(txtRecNo);
		
		lblStoName = new JLabel("Store Name: ");
		lblStoName.setBounds(40,110,100,30);
		add(lblStoName);
		
		txtStoName = new JTextField();
		txtStoName.setBounds(200,110,250,30);
		add(txtStoName);
		
		lbltotcost = new JLabel("Total Cost: ");
		lbltotcost.setBounds(40,150,100,30);
		add(lbltotcost);
		
		txttotalcost = new JTextField();
		txttotalcost.setBounds(200,150,250,30);
		add(txttotalcost);
		
		lbltax = new JLabel ("Tax (12%)");
		lbltax.setBounds(40, 250, 100, 30);
		add(lbltax);
		
		txttax = new JTextField();
		txttax.setBounds(200,250,250,30);
		txttax.setEditable(false);
		add(txttax);
		
		lblfinal = new JLabel("Final Amount: ");
		lblfinal.setBounds(40,290,100,30);
		add(lblfinal);
		
		txtfinal = new JTextField();
		txtfinal.setBounds(200, 290, 250, 30);
		txtfinal.setEditable(false);
		add(txtfinal);
		
		BRecord = new JButton("Record");
		BRecord.setBounds(140, 360, 100, 30);
		add(BRecord);
		
		Bclear = new JButton("Clear");
		Bclear.setBounds(260, 360, 100, 30);
		add(Bclear);
		
		 BRecord.addActionListener(e -> {
	            updateTaxAndFinal();
	            addRecord();
	        });
		 
		 Bclear.addActionListener(s -> clear());
		
		
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		setTitle("Expense Tracker");
		setVisible(true);
		
		

	
	
	
}

	public void updateTaxAndFinal() {
        try {
            double totalCost = Double.parseDouble(txttotalcost.getText());

            double taxAmount = totalCost * 0.12;
            txttax.setText(String.format("%.2f", taxAmount));
            double finalAmount = totalCost + taxAmount;
            txtfinal.setText(String.format("%.2f", finalAmount));
        } catch (NumberFormatException e) {
            txttax.setText("");
            txtfinal.setText("");
        }
    }
		
	public void addRecord()	{
	try {
		FileWriter fw = new FileWriter("Expense Tacker.txt",true);
		
		String Reicept =txtRecNo.getText();
		String store = txtStoName.getText();
		String cost = txttotalcost.getText();
		String tax = txttax.getText();
		String fina = txtfinal.getText();
		
		JOptionPane.showMessageDialog(null,"Record Saved Successfully.");
		
		fw.write("Receipt No: " + Reicept + "\n Store Name: " + store + " \n Total Cost: ₱" + cost + "\n Tax(12%): ₱" + tax + "\n Final Amount: ₱" + fina + "\n");
		fw.close();
	}catch (IOException x) {
		JOptionPane.showMessageDialog(null,"Saving Failed" + x);
		
	}
		
		
		
		
	}
		
	public void clear() {
		
		 txtRecNo.setText("");
		 txtStoName.setText("");
		 txttotalcost.setText("");
		 txttax.setText("");
		 txtfinal.setText("");
		
	}
		
	public static double tax(double tax) {
		return tax * 0.12;
	}
	
	public static void main(String[] args) {
		new Suizo();
		
	}

}
