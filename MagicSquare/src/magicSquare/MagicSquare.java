package magicSquare;

import java.io.*;
import java.util.*;

/**
 * <p>
 * Fredrick Kaup
 * CS 311 Fall 2018
 * Assignment 4
 * </p>
 * <p>
 * Description: The MagicSquare class is our main class.
 * Given a file square.txt it will determine
 * if it contains a magic square or not using 1 thread
 * for each row, column, and diagonal.
 * If file does not exist the program will inform
 * you of that and tell you where to place the file.
 * </p>
 * <pre>
 * expected file format
 * x
 * n n n n
 * n n n n
 * n n n n
 * n n n n
 * </pre>
 * <p>
 * where x is the size of each side of the square and
 * can be any integer.
 * and n is any integer.
 * </p>
 * 
 * @author Fred Kaup
 */
public class MagicSquare {

	static int threadsNeeded;
	static int[] sums;
	static int[][] square;
	static int size;

	/**
	 * @param args - no args taken for this program.
	 */
	public static void main(String[] args) {
		Scanner input = null;
		try {
			input = new Scanner(new File("square.txt"));
		} catch (FileNotFoundException e){
			File currentDir = new File("");
			System.out.println("Square.txt has not been placed in the proper folder. Please place square.txt file in " + currentDir.getAbsolutePath());
			System.exit(0);
		}
		if(input != null) {
			size = input.nextInt();
		}
		square = new int[size][size];
		threadsNeeded = ((size * 2) + 2);
		sums = new int[threadsNeeded];

		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				square[x][y] = input.nextInt();
			}
		}
		
		if(input != null) {
			input.close();
		}
		
		System.out.println("~*~*~* MAGIC SQUARE VALIDATION PROGRAM *~*~*~\n\nIs this a Magic Square?\n");
		for (int x = 0; x < size; x++) {
			int[] line = square[x];
			for (int el : line) {
				System.out.print(el + " ");
			}
			System.out.println();
		}
		System.out.println();

		List<WorkerBees> threadList = new ArrayList<WorkerBees>();
		
		for (int i = 0; i < threadsNeeded; i++) {
			WorkerBees workerBees = new WorkerBees(square, i, size);
			workerBees.start();
			threadList.add(workerBees);
		}
		
		for (int i = 0; i < threadsNeeded; i++) {
			try {
				((Thread) threadList.get(i)).join();
			} catch (InterruptedException e) {
				System.out.println("Caught an Exception!");
				e.printStackTrace();
			}
		}
		
		
		System.out.println("\n=== All threads completed ===\n\nSummary\n----------------\n#   Row   Column\n----------------");
		
		for(int i = 0; i < size; i++) {
			System.out.println(i + "   " + sums[i] + "    " + sums[size + i]);
		}
		
		System.out.println("\nDiagonals\n---------");
		for(int i = size*2; i < sums.length; i++) {
			System.out.println((i-size*2) + "   " + sums[i]);
		}
		
		System.out.println("\n~*~*~* Result *~*~*~\n");
		
		for (int x = 0; x < size; x++) {
			int[] line = square[x];
			for (int el : line) {
				System.out.print(el + " ");
			}
			System.out.println();
		}
		System.out.println();
		Boolean isOrNot = true;
		
		for(int el : sums) {
			if(el != sums[0]) {
				isOrNot = false;
			}
		}
		if(isOrNot) {
			System.out.println("IS a magic square!\nmagic sum = " + sums[0]);
		} else {
			System.out.println("IS NOT a magic square!\nthere is no magic number");
		}
	}
}

/**
 * Description: WorkerBees is the class used to set up our runnable threads.
 * 
 * @author Fred Kaup
 */
class WorkerBees extends Thread {

	private int[][] square;
	private int thread;
	private int size;

	public WorkerBees(int[][] square, int thread, int size) {
		this.square = square;
		this.thread = thread;
		this.size = size;
	}

	public void run() {

		if (thread < size) {
			RowChecker.rowChecker(square, thread);
		} else if (thread < size*2) {
			ColumnChecker.columnChecker(square, thread);
		} else {
			DiagonalChecker.diagonalChecker(square, thread);
		}
	}
}
