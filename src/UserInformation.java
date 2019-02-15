/*
 * Student UCI ID: huanjial
 * Name: Huanjia Liang
 * Student ID No.: 10244014
 * 
 * This file has the class of the UserInformation.
 * This object mainly asks the user for their information input such as 
 * name, balance, bet, and replay option. While they enter their information,
 * those information will also be printed and written into different files.
 */

package lab4;

import java.util.Scanner;

/*import lab3.negativeBalanceException;
import lab3.zeroMoneyException;*/

import java.io.*;

public class UserInformation {
	
	private Scanner input;
	private PrintWriter pw1;
	private PrintWriter pw2;
	private PrintWriter pw3;
	private PrintWriter pw4;
	private PrintWriter pw5;
	
	
	//This is the UserInformation object's constructor. It takes 5 PrintWriter as parameters so in this 
	//object other functions can use those PrintWriter to write information.
	UserInformation(PrintWriter pw1,PrintWriter pw2,PrintWriter pw3,PrintWriter pw4,PrintWriter pw5){
		this.input = new Scanner(System.in);
		this.pw1 = pw1;
		this.pw2 = pw2;
		this.pw3 = pw3;
		this.pw4 = pw4;
		this.pw5 = pw5;
	}
	

	//gegName() is the function to get the name of the player.
	//It will prevent the player from entering blank space and
	//get their input. Also the prompt and reply will 
	//be written into the files and printed in the console.
	public String getName() {
		System.out.print("Welcome to SimCraps! Enter your user name: ");
		writeInformation("Welcome to SimCraps! Enter your user name: ");

		while(true) {
		String name = this.input.nextLine();
		writeInformation(name+"\n");
		String examine = name.replaceAll(" ","");
		if (examine.length() == 0) {
			System.out.print("Invalid name input, it cannot be blank: ");
			writeInformation("Invalid name input, it cannot be blank: ");
		}
		else {
			System.out.println("Hello " + name + "!");
			writeInformation("Hello " + name + "!" + "\n");
			return name;
			}
		}
	}

	//getMoney() is to get how much the player bring to the table.
	//The money will not be smaller or equal than 1, otherwise 
	//it will asks the user to input again until it's bigger than
	//1.
	public int getMoney() {
		//Scanner input = new Scanner(System.in);
		System.out.print("Enter the amount of money you will bring to the table: ");
		writeInformation("Enter the amount of money you will bring to the table: ");
		int money = 0;
		boolean moneyValidation = false;
		while(moneyValidation == false) {
			money = this.input.nextInt();
			this.input.nextLine();
			System.out.println("Money is: "+ money);
			writeInformation(Integer.toString(money)+"\n");
			if (money <= 1) 
				{
				System.out.print("The money cannot be smaller or equal than 1. Type again: ");
				writeInformation("The money cannot be smaller or equal than 1. Type again: ");
				}
			else 
				{
				moneyValidation = true;
				}		
		}
		return money;
	}

	
	
	
	
	
	//getBet is to get how much the player's bet is during the game.
	//It takes the balance as a parameter to prevent the player from entering
	//a bet that is bigger than the balance.
	//This function will get a valid bet from the player
	//Also the prompt and reply will 
	//be written into the files and printed in the console.
	public double getBet(double balance) {
		System.out.print("Enter the bet amount between $1 and $" + balance + ": ");
		writeInformation("Enter the bet amount between $1 and $" + Double.toString(balance) + ": ");
		double bet = 0;
		boolean validBet = false;
		while (validBet == false) {
			bet = this.input.nextDouble();
			writeInformation(Double.toString(bet)+"\n");
			this.input.nextLine();
			if (bet < 1 || bet > balance) {
				System.out.print("Invalid bet! Please enter a bet between $1 and $" + balance + ": ");
				writeInformation("Invalid bet! Please enter a bet between $1 and $" + Double.toString(balance) + ": ");
				}
			else {
				validBet = true;
				}
		}
		return bet;
	}
	
	//replay() is to prompt the user to enter "y" or "n" 
	// ( upper case or lower case is fine )to
	//let the program realize whether it should restart or
	//terminate the program. Also the prompt and reply will 
	//be written into the files and printed in the console.
	public boolean replay() {
		System.out.print("\nReplay? Enter 'y' or 'n': ");
		while(true) {
			String originalResult = input.nextLine();
			//writeInformation(originalResult + "\n");
			String result = originalResult.toLowerCase();
			if (result.equals("y") || result.equals("n")) {
				writeInformation("\nReplay? Enter 'y' or 'n': " + originalResult + "\n\n");
				if (result.equals("y")) {
					return true;
				}
				else {
					System.out.println("The game is terminated.\n");
					writeInformation("The game is terminated.");
					return false;
				}
			}
			else {
				System.out.print("Invalid input. You can only type 'y' or 'n': ");
			}
		}
	}
	
	//this function take a String content as a parameter and
	//write it to PrintWriter objects. 
	private void writeInformation(String info) {
		this.pw1.print(info);
		this.pw2.print(info);
		this.pw3.print(info);
		this.pw4.print(info);
		this.pw5.print(info);
	}

	
	
	
	
	
	
}
