/*
 * Student UCI ID: huanjial
 * Name: Huanjia Liang
 * Student ID No.: 10244014
 * 
 * This CrapsMetricsMonitor.java file handles the information of the statistics
 * during running the game. It sets all those statistic variables and define some
 * of their's getters and setters so that they can be accessed during the game
 * and do the statistics.
 */

package lab4;

import java.io.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CrapsMetricsMonitor {
	private int numOfGamesPlayed;
	private int numOfGamesWon;
	private int numOfGamesLost;
	private int maxNumOfRollsInSingleGame;
	private int naturalRollsCount;
	private int crapsRollsCount;
	private int maxWinningStreak;
	private int maxLosingStreak;
	private double maxBalance;
	private int gameNumberOfMaxBalance;
	private String simulationEndTime;
	
	CrapsMetricsMonitor(){
		this.numOfGamesPlayed = 0;
		this.numOfGamesWon = 0;
		this.numOfGamesLost = 0;
		this.maxNumOfRollsInSingleGame = 0;
		this.naturalRollsCount = 0;
		this.crapsRollsCount = 0;
		this.maxWinningStreak = 0;
		this.maxLosingStreak = 0;
		this.maxBalance = 0;
		this.gameNumberOfMaxBalance = 0;
		this.simulationEndTime = "";
	}
	
	//This function increases the numOfGamesPlayed variable by 1
	public void increaseNumOfGamesPlayed() {
		numOfGamesPlayed += 1;
	}
	
	//This function increases the numOfGamesWon variable by 1
	public void increaseNumOfGamesWon() {
		numOfGamesWon += 1;
	}
	
	//This function increases the numOfGamesLost variable by 1
	public void increaseNumOfGamesLost() {
		numOfGamesLost += 1;
	}
	
	//This function is a setter function to assign the coutingNumber to the variable maxNumOfRollsInSingleGame
	public void setMaxNumOfRollsInSingleGame(int countingNumber) {
		maxNumOfRollsInSingleGame = countingNumber;
	}
	
	public int getMaxNumOfRollsInSingleGame() {
		return maxNumOfRollsInSingleGame;
	}
	
	//This function increases the naturalRollsCount variable by 1
	public void increaseNaturalRollsCount() {
		naturalRollsCount += 1;
	}
	
	//This function increases the crapsRollsCount variable by 1
	public void increaseCrapsRollsCount() {
		crapsRollsCount += 1;
	}
	
	//This is the setter to set the private variable maxWinningStreak.
	public void setMaxWinningStreak(int countingNumber) {
		maxWinningStreak = countingNumber;
	}
	
	//Getter to get the maxWinningStreak.
	public int getMaxWinningStreak() {
		return maxWinningStreak;
	}
	
	//Setter to set the maxLosingStreak to the countingNumber
	public void setMaxLosingStreak(int countingNumber) {
		maxLosingStreak = countingNumber;
	}
	
	//Getter to get the maxLosingStreak data.
	public int getMaxLosingStreak() {
		return maxLosingStreak;
	}
	
	//Setter to set the maxBalance to the countingNumber.
	public void setMaxBalance(double countingNumber) {
		maxBalance = countingNumber;
	}
	
	//Getter to get the maxBalance data.
	public double getMaxBalance() {
		return maxBalance;
	}
	
	//Setter to set the gameNumberOfMaxBalance to the gameNumber.
	public void setGameNumberOfMaxBalance(int gameNumber) {
		gameNumberOfMaxBalance = gameNumber;
	}
	
	//Setter to set the gameNumberOfMaxBalance to the thGame.
	public void setNumberOfMaxBalance(int thGame) {
		gameNumberOfMaxBalance = thGame;
	}
	
	//This is the getter to get the private variable
	// numOfGamesPlayed's data.
	public int getTotalGame() {
		return numOfGamesPlayed;
	}
	
	/*
	 * printStatistics() is a method to write the statistics 
	 * into the corresponding PrintWriter pw.
	 * Also it gets the end time at the end to know when
	 * the thread ends.
	 */
	public void printStatistics(PrintWriter pw) {
	pw.println("* * * * * * * * * * * * * * * * * * * * * * *");
	pw.println("* * * * * * SIMULATION STATISTICS * * * * * *");
	pw.println("* * * * * * * * * * * * * * * * * * * * * * *");
	pw.println("Games played: " + numOfGamesPlayed);
	pw.println("Games won: " + numOfGamesWon);
	pw.println("Games lost: " + numOfGamesLost);
	pw.println("Maximum Rolls in a single game: " + maxNumOfRollsInSingleGame);
	pw.println("Natural Count: " + naturalRollsCount);
	pw.println("Craps Count: " + crapsRollsCount);
	pw.println("Maximum Winning Streak: " + maxWinningStreak);
	pw.println("Maximum Losing Streak: " + maxLosingStreak);
	pw.println("Maximum balance: " + maxBalance + " during game " + gameNumberOfMaxBalance);
	this.simulationEndTime = getTime();
	}
	
	//This is the getter to get the data of the private
	//variable simulationEndTime.
	public String getSimulationEndTime() {
		return this.simulationEndTime;
	}
	
	
	
	/* reset() is a method to reset all statistics if a user
	 * wants to restart a game
	 */
	public void reset() {
		this.numOfGamesPlayed = 0;
		this.numOfGamesWon = 0;
		this.numOfGamesLost = 0;
		this.maxNumOfRollsInSingleGame = 0;
		this.naturalRollsCount = 0;
		this.crapsRollsCount = 0;
		this.maxWinningStreak = 0;
		this.maxLosingStreak = 0;
		this.maxBalance = 0;
		this.gameNumberOfMaxBalance = 0;
	}
	
	//getTime is a function that gets the right now time information. 
	//It returns a string that represents the current time.
	private String getTime() {
		String currentDateAndTime = Instant.now().atZone(ZoneId.systemDefault()).format( DateTimeFormatter.ISO_LOCAL_DATE_TIME ).replace("T"," ");
		return currentDateAndTime;
		}
	
	
	
}
