/*
 * Student UCI ID: huanjial
 * Name: Huanjia Liang
 * Student ID No.: 10244014
 * 
 * This file has the class of the CrapsSimulation.
 * It constructs a crapsMetricsMonitor object and crapsGame object
 * and handles everything that is needed to simulate the craps game
 * continuously.
 */

package lab4;


import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class CrapsSimulation implements Runnable{
	private CrapsMetricsMonitor cmm;
	private CrapsGame crapsGame;
	
	private String name;
	private double bet;
	private double balance;
	private boolean playGame;
	private int loseStreak; // This is the current lose streak
	private int winStreak; // This is the current win streak
	private boolean firstRound;
	private boolean previousGameSituation;
	private int thGame;
	private double originalBet;
	private PrintWriter pw;
	
	//This is the constructor of the CrapsSimulation. It takes name, money, bet, balance, originalBet, and a
	//PrintWriter as parameters and inside the constructor those local variables will be initialized.
	CrapsSimulation(String name, int money, double bet, double balance, double originalBet, PrintWriter pw){
		this.name = name;
		//this.money = money;
		this.bet = bet;
		this.balance = balance;
		this.playGame = true;
		this.loseStreak = 0; // This is the current lose streak
		this.winStreak = 0; // This is the current win streak
		this.firstRound = true;
		this.previousGameSituation = true;
		this.thGame = 0;
		this.originalBet = originalBet;	
		this.pw = pw;
		this.cmm = new CrapsMetricsMonitor();
		this.crapsGame = new CrapsGame(cmm);
		
	}

	//the start() method is to use while loops to run the craps game's simulation
	//this method handles the whole simulation of the game.
	public void run() {
		while (playGame == true) {
			renewMaxBalanceAndThRound(cmm, thGame, balance);
			while (balance > 0) {
				if (balance < originalBet) {
					bet = balance;
				}
				else {
					bet = originalBet;
				}
				this.pw.println(this.name + " bets $" + this.bet + " at " +  getTime()+"\n");
				boolean singleRunResult = crapsGame.playGame(this.pw);
				thGame += 1;
				cmm.increaseNumOfGamesPlayed();
				// ================== lose
				if (singleRunResult == false) {
					losingCondition();
				}
				// ================= win	
				else {
					winningCondition();
				}
				renewMaxBalanceAndThRound(cmm, thGame, balance);
				resetGameResultTerms();
				if (balance > 0) {
				this.pw.println("\n" + name + "'s balance: " + balance + ". Playing a new game...");
				}
			}
			printEndingBalance(name, balance);

			cmm.printStatistics(this.pw);
			
			playGame = false;

		}
	}	
	
	
	
	//The printEndingBalance(String name, int balance) is a method to print out the player's balance at the
	//end of the game when the player loses all the money. Because it doesn't have the "Playing a new game..." sentence,
	//so it is supposed to be put at the end of the game that when the player loses all the money.
	private void printEndingBalance(String name, double balance) {
		this.pw.println(name + "'s balance: " + balance + "\n\n");
	}
	
	//losingCondition() is a method that handles the situation that player loses in a single game.
	//first it will check if this round's game is first round. 
	//If it is, then because the game situation is lost, so the previousGameSituation will be set as false.
	//Doing this is to have a comparison between the first game and the next one, to give it the initialized game situation. 
	//then it will check is this game has the same result as the last game. If they have the same result, then it means the 
	//user keeps losing, the loseStreak increases 1. If it's not, then it means the player's last game is winning, and this game
	//is losing, then it will first check the winStreak to see if it is bigger then the max winStreak in the crapsMetricsMonitor.
	//if it is, then the max winStreak be updated to this winStreak.
	//if not, it does nothing.
	//Also, it resets the loseStreak as 1, restart counting the loseStreak, and set the previousGameSituation as false, so
	//we can use the next round game to compare with current losing game.
	//After dealing with the loseStreak, the code subtract the balance with the bet.
	//After that it will check out the private variables crapOut and craps in the crapsGame, to figure out whether it loses 
	//because of craps or craps out, and then print out the corresponding statement.
	private void losingCondition() {
		if (firstRound == true) {
			previousGameSituation = false;
			}	
		firstRound = false;
		if (previousGameSituation == false) {
			loseStreak += 1;
			if (loseStreak > cmm.getMaxLosingStreak()) {
				cmm.setMaxLosingStreak(loseStreak);
				}
			}
		else {
			if (winStreak > cmm.getMaxWinningStreak()) {
				cmm.setMaxWinningStreak(winStreak);
				}
			loseStreak = 1;
			if (loseStreak > cmm.getMaxLosingStreak()) {
				cmm.setMaxLosingStreak(loseStreak);
				}
			previousGameSituation = false;
			}
		balance -= bet;
		if (crapsGame.isCrapOut() == true) {
			this.pw.println("***** Crap out! You lose. *****");
			}
		
		else if (crapsGame.isCraps() == true) {
			this.pw.println("***** Craps! You lose. *****");
			}
	}
	
	//winningCondition() is a method that handles the situation that player wins in a single game.
	//first it will check if this round's game is first round. 
	//If it is, then because the game situation is winning, so the previousGameSituation will be set as true.
	//Doing this is to have a comparison between the first game and the next one, to give it the initialized game situation. 
	//then it will check is this game has the same result as the last game. If they have the same result, then it means the 
	//user keeps winning, the winStreak increases 1. If it's not, then it means the player's last game is losing, and this game
	//is winning, then it will first compare the loseStreak to the max one in the crapsMetricsmonitor to see if it's bigger.
	//if it is bigger, then the max loseStreak will update with this one.
	//if not, it will do nothing.
	//what it also does is reseting the winStreak as 1, restart counting the winStreak, and set the previousGameSituation as true, 
	//so we can use the next round game to compare with current winning game.
	//After dealing with the winStreak, the code add the balance with the bet.
	//After that it will check out the private variables natural and rollPoint in the crapsGame, to figure out whether it wins 
	//because of natural or rollPoint, and then print out the corresponding statement.
	private void winningCondition() {
		if (firstRound == true) {
			previousGameSituation = true;
			}
		firstRound = false;
		if (previousGameSituation == true) {
			winStreak += 1;
			if (winStreak > cmm.getMaxWinningStreak()) {
				cmm.setMaxWinningStreak(winStreak);
			}
			}
		else {
			if (loseStreak > cmm.getMaxLosingStreak()) {
				cmm.setMaxLosingStreak(loseStreak);
				}
			previousGameSituation = true;
			winStreak = 1;
			if (winStreak > cmm.getMaxWinningStreak()) {
				cmm.setMaxWinningStreak(winStreak);
			}
			}
		balance += bet;
		if (crapsGame.isNatural() == true) {
			this.pw.println("***** Natural! You win! *****");
		}
		else if (crapsGame.isRollPoint() == true) {
			this.pw.println("***** Roll the point! You win! ***** ");
		}
	}
	
	//renewMaxBalanceAndThRound() is to check whether the balance bigger then the crapsMetricsMonitor's.
	//if it is bigger, then the maxBalance and gameNumberOfMaxBalance in the crapsMetricsMonitor will be updated
	//to the current number of game and it's balance.
	private void renewMaxBalanceAndThRound(CrapsMetricsMonitor cmm, int thGame, double balance) {
		if (balance > cmm.getMaxBalance()) {
			cmm.setMaxBalance(balance);
			cmm.setNumberOfMaxBalance(thGame);
		}
	}
	
	//getTime is a function that gets the right now time information. 
	//It returns a string that represents the current time.
	private String getTime() {
		String currentDateAndTime = Instant.now().atZone(ZoneId.systemDefault()).format( DateTimeFormatter.ISO_LOCAL_DATE_TIME ).replace("T"," ");
		return currentDateAndTime;
		}
	
	//This is a getter to get the cmm's numberOfGamePlayed data,
	//so it is able to sum each simulation's played game.
	public int getCmmTotalGame() {
		return this.cmm.getTotalGame();
	}
	
	//This is a getter to get the smm's simulationEndTime data
	//so the end time can be printed out and written to corresponding 
	//PrintWriter.
	public String getCmmSimulationEndTime() {
		return this.cmm.getSimulationEndTime();
	}
	
	
	//resetGameResultTerms() is to reset the value of the crapOut, craps, natural, and rollPoint private variables
	//in the crapsGame object by using their setter functions.
	//Doing so is because in every single game, one of the terms will be set to be true so the simulator knows
	//what exactly reason the user wins or loses. After that, those terms should be reset to false to get prepared
	//for the next round.
	private void resetGameResultTerms() {
		crapsGame.setCrapOut();
		crapsGame.setCraps();
		crapsGame.setNatural();
		crapsGame.setRollPoint();
	}
}

