package Suizo;

import java.util.*;

public class Suizo {

	public static void main(String[] args) {
		
		System.out.print("-----------------------------------\n");
		System.out.print("   Hotel Room Reservation System\n");
		System.out.print("-----------------------------------\n");
		
		Scanner sc = new Scanner(System.in);
		
		
		int [][] hotel = new int [7] [5];
		boolean exit = true;
		
		
		
	while(exit) {
	
	System.out.print(  	  "\nMENU\n"
						+ "[1]View Rooms\n"
						+ "[2]Check In\n"
						+ "[3]Check Out\n"
						+ "[4]Exit\n"
						+ "\nSelect: " );
	int option = sc.nextInt();sc.nextLine();
		
	if(option >= 1 && option <=4) {
		
		
		switch(option) {
		
		case 1:
			
			System.out.println("\n0 = Available 1 = Not Available\n");
			for (int i =0; i< hotel.length; i++) {
				System.out.print("Floor " + (i+1) +" : ");
				for(int j=0; j<hotel[i].length; j++) {
				System.out.print("[" + hotel[i][j] + "]");
			}
			
		System.out.println();	
		}	
		break;
		
		case 2:
			boolean check = true;
		
		while(check) {
		

			System.out.print("Enter floor (1-7): ");
			int floor = sc.nextInt() -1 ;
			
			System.out.print("Enter room (1-5): ");
			int room = sc.nextInt() -1 ; sc.nextLine();
			
			if(hotel [floor][room] == 0) {
			   hotel [floor][room] = 1;
			   System.out.println("Check-in successful!");
			   
			   check = false;
			}
			
		 	else {
			System.out.print("\nRoom already occupied.\n");
			System.out.print("Pls Reselect Floor and Room No.\n");
			
			}
			
			
		}
			
		break;
		
		case 3:
			boolean check1 = true;
			
		while(check1) {

			System.out.print("Enter floor (1-7): ");
			int nfloor = - 1 + sc.nextInt();
			
			System.out.print("Enter room (1-5): ");
			int nroom = - 1 + sc.nextInt(); sc.nextLine();
			
			if(hotel [nfloor][nroom] == 1) {
			   hotel [nfloor][nroom] = 0;
			   System.out.println("Check-out successful!");
			   check1 = false;
			}
			else {
			System.out.print("Room already empty\n");
			System.out.print("Pls Reselect Floor and Room No.\n");	
			}
				}
			
		break;
		
		case 4:
		
			System.out.print("...Thank you for staying...");
			exit = false;
			sc.close();
			
		break;
		
	}
		
	}else{
		System.out.print("Invalid Input. Try Again.\n");
		continue;
	}
		
		
		}
		
			
		
}
	

}


