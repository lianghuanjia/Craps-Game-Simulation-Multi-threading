/*
 * Student UCI ID: huanjial
 * Name: Huanjia Liang
 * Student ID No.: 10244014
 * 
 * This file has the class of the MultiThreading.
 * This object creates 5 files, 5 PrintWriter for each file, and simulate 
 * the game in 5 threads. In this class, it also handles each simulation.
 * It asks for user information, and initialize games and threads. Finally,
 * it closes those PrintWriter so they can save those written information in
 * their target files.
 */

package lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class MultiThreading {
	
	private int runningCount;
	private PrintWriter pw1;
	private PrintWriter pw2;
	private PrintWriter pw3;
	private PrintWriter pw4;
	private PrintWriter pw5;
	private PrintWriter pw6;
	
	private CrapsSimulation Sim1;
	private CrapsSimulation Sim2;
	private CrapsSimulation Sim3;
	private CrapsSimulation Sim4;
	private CrapsSimulation Sim5;
	
	MultiThreading(){
	this.runningCount = 1;
	
	}
	
	//This function creates 6 files that are waiting to be written.
	//After that, each file will have a corresponding PrintWriter.
	//After those PrintWriter created, they will be passed into different
	//objects and functions to write some information to their corresponding
	//files. Finally those PrintWriters will be closed and those information will
	//be saved in those corresponding files.
	public void startSimulation() {
	
		boolean simulation = true;
		
		File outputFile1 = new File("src/cs1.txt");
		File outputFile2 = new File("src/cs2.txt");
		File outputFile3 = new File("src/cs3.txt");
		File outputFile4 = new File("src/cs4.txt");
		File outputFile5 = new File("src/cs5.txt");
		File outputFile6 = new File("src/cs6.txt");	
		
		try 
			{
			this.pw1 = new PrintWriter(outputFile1);
			this.pw2 = new PrintWriter(outputFile2);
			this.pw3 = new PrintWriter(outputFile3);
			this.pw4 = new PrintWriter(outputFile4);
			this.pw5 = new PrintWriter(outputFile5);
			this.pw6 = new PrintWriter(outputFile6);
			
			while(simulation == true) 
				{
				UserInformation userInfo = new UserInformation(pw1,pw2,pw3,pw4,pw5);	
				String name = userInfo.getName();
				int money = userInfo.getMoney();
				double balance = money;
				double bet = userInfo.getBet(balance);
				double originalBet = bet;
				
				this.Sim1 = new CrapsSimulation(name,money,bet,balance,originalBet,pw1);
				this.Sim2 = new CrapsSimulation(name,money,bet,balance,originalBet,pw2);
				this.Sim3 = new CrapsSimulation(name,money,bet,balance,originalBet,pw3);
				this.Sim4 = new CrapsSimulation(name,money,bet,balance,originalBet,pw4);
				this.Sim5 = new CrapsSimulation(name,money,bet,balance,originalBet,pw5);
				
				Thread cs1 = new Thread(Sim1);
				cs1.start();
				String currentTime1 = getTime();
				writeStartTime(pw6, "1", currentTime1);
				System.out.println("Craps simulation thread 1 starts at " + currentTime1);
				
				Thread cs2 = new Thread(Sim2);
				cs2.start();
				String currentTime2 = getTime();
				writeStartTime(pw6, "2", currentTime2);
				System.out.println("Craps simulation thread 2 starts at " + currentTime2);
				
				Thread cs3 = new Thread(Sim3);
				cs3.start();
				String currentTime3 = getTime();
				writeStartTime(pw6, "3", currentTime3);
				System.out.println("Craps simulation thread 3 starts at " + currentTime3);

				
				Thread cs4 = new Thread(Sim4);
				cs4.start();
				String currentTime4 = getTime();
				writeStartTime(pw6, "4", currentTime4);
				System.out.println("Craps simulation thread 4 starts at " + currentTime4);
				
				Thread cs5 = new Thread(Sim5);
				cs5.start();
				String currentTime5 = getTime();
				writeStartTime(pw6, "5", currentTime5);
				pw6.println("\n");
				System.out.println("Craps simulation thread 5 starts at " + currentTime5);
				
				
				boolean replayChoice = userInfo.replay();
				if (replayChoice == false) 
					{
					simulation = false;
					}	
				
				writeEndingTime();
				
				int totalGameRunning = Sim1.getCmmTotalGame() + Sim2.getCmmTotalGame() + Sim3.getCmmTotalGame()+Sim4.getCmmTotalGame()+Sim5.getCmmTotalGame();
				System.out.println("Total running game is: " + totalGameRunning);
				pw6.println("Total running game is: " + totalGameRunning);
				
				pw6.println(" ================ No." + runningCount+ " all-thread-simulation finishes ================ " + "\n");
				System.out.println(" ================ No." + runningCount+ " all-thread-simulation finishes ================ " + "\n");
				runningCount++;
				}
			
			pw1.close();
			pw2.close();
			pw3.close();
			pw4.close();
			pw5.close();
			pw6.close();
			}
		
		catch(FileNotFoundException e) 
			{
			System.out.println("Files cannot be found");
			}
	
	}
	
	//This function writes ending time to it's corresponding PrintWriter and print them out on
	//the console.
	private void writeEndingTime() {
		
		pw6.println("Craps simulation thread 1 ends at " + this.Sim1.getCmmSimulationEndTime());
		System.out.println("Craps simulation thread 1 ends at " + this.Sim1.getCmmSimulationEndTime());
		
		pw6.println("Craps simulation thread 2 ends at " + this.Sim2.getCmmSimulationEndTime());
		System.out.println("Craps simulation thread 2 ends at " + this.Sim2.getCmmSimulationEndTime());
		
		pw6.println("Craps simulation thread 3 ends at " + this.Sim3.getCmmSimulationEndTime());
		System.out.println("Craps simulation thread 3 ends at " + this.Sim3.getCmmSimulationEndTime());
		
		pw6.println("Craps simulation thread 4 ends at " + this.Sim4.getCmmSimulationEndTime());
		System.out.println("Craps simulation thread 4 ends at " + this.Sim4.getCmmSimulationEndTime());
		
		pw6.println("Craps simulation thread 5 ends at " + this.Sim5.getCmmSimulationEndTime());
		System.out.println("Craps simulation thread 5 ends at " + this.Sim5.getCmmSimulationEndTime());
		
		pw6.println("\n");
		
	}
	
	//This function takes a PrintWriter, a thread number, and a currentTime as parameters.
	//It write the start time information to the corresponding PrintWriter.
	private void writeStartTime(PrintWriter pw, String threadNo,String currentTime) {
		pw.println("Craps simulation thread " + threadNo  + " starts at " + currentTime);
	}
	
	//getTime is a function that gets the right now time information. 
	//It returns a string that represents the current time.
	private String getTime() {
		String currentDateAndTime = Instant.now().atZone(ZoneId.systemDefault()).format( DateTimeFormatter.ISO_LOCAL_DATE_TIME ).replace("T"," ");
		return currentDateAndTime;
		}
	
}
