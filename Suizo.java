package Suizo;

import java.util.*;


public class Suizo {

	public static void main(String[] args) {
	
	Scanner sc = new Scanner(System.in);
	
	
	int [][] num = {{120,250,789,7},{58,79,31,87},{8,1,2,3},{99,98,97,96}};
	
	for (int i =0; i< num.length; i++) {
		for(int j=0; j<num[i].length; j++) {
			System.out.printf("%5d",num[i][j]);
		}
		
	System.out.println();	
	}
	
	
	for (int i =0; i< num.length; i++) {
		for(int j=0; j<num[i].length; j++) {
		if(num[i][j] == 1) {
			System.out.println("\nNumber 1 is found at indices " + i + " and " + j + ".");
			num[i][j] = 50;	
		}
		}	
	}
	System.out.println();
	

	for (int i =0; i< num.length; i++) {
		for(int j=0; j<num[i].length; j++) {
			System.out.printf("%5d",num[i][j]);
	
	
		}
		System.out.println();
	}
	
	for (int i =0; i< num.length; i++) {
		for(int j=0; j<num[i].length; j++) {
			num[1][j] = 0;	
		}
		
	}	
	System.out.println();
	

	for (int i =0; i< num.length; i++) {
		for(int j=0; j<num[i].length; j++) {
			System.out.printf("%5d",num[i][j]);
	
	
		}
		System.out.println();
	}

	
	
		
		
//	System.out.print("Enter Number of Rows: ");
//	int a = sc.nextInt();
//	
//	System.out.print("Enter Number of Column: ");
//	int b = sc.nextInt();
//	
//	int [][] nums = new int [a]	[b];
//		
//	for (int i =0; i< nums.length; i++) {
//		for(int j=0; j<nums[i].length; j++) {
//			System.out.print("Enter numbers: ");
//			nums[i][j] = sc.nextInt();
//			
//		}	
//	}	
//		
//	for (int i =0; i< nums.length; i++) {
//		for(int j=0; j<nums[i].length; j++) {
//			System.out.print(nums[i][j] + " ");
//			 	
//		}
//		System.out.println("");
//	}		
//		
//		
//		
//		
	

	
	
	
	
	
	}
	
	
	}
