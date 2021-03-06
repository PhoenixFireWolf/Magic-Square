package magicSquare;

import java.util.Random;

/**
 * <p>
 * Fredrick Kaup
 * CS 311 Fall 2018
 * Assignment 4
 * </p>
 * <p>
 * Description: After a randomized sleep interval between 1 and 5 seconds.
 * The ColumnChecker class takes a possible magic square and sums the column it was assinged. 
 * Then places that value into the sums array in the MagicSquare class. 
 * </p>
 * 
 * @author Fred Kaup
 */
public class ColumnChecker {
	
	/**
	 * <p>
	 * Given a 2D array with equal dimensions and an integer 
	 * designating the column you wish it to work on.
	 * This method will calculate the sum of all elements in the column specified.
	 * </p>
	 * <p>
	 * Note: Number given in Column is actually a specific thread in the thread[] 
	 * column corresponds to the column counted from left to right with a zero index.
	 * Threads 0 - ([size of 1D of the 2D Array]-1) is designated to Rows.
	 * Threads [size of 1D of the 2D Array] - (2*[size of 1D of the 2D Array]-1) is designated to Columns.
	 * The last 2 threads are designated to Diagonals.
	 * </p>
	 *
	 * @param square - This is the potential Magic Square, as a 2D array.
	 * @param column - This is the thread number that will be working on the specified column.
	 */
	public static void columnChecker(int[][] square, int column) {
		int sum = 0;
		int size = square.length;
		
		//randomly determins the amount of time the thread will wait before it does its work.
		Random rdm = new Random();
		int wait = rdm.nextInt(5) * 1000;
		
		try {
			System.out.println("Thread column checker " + (column-size) + " is taking a nap! zzz...");
			Thread.sleep(wait);
			System.out.println("Thread column checker " + (column-size) + " snorts and wakes up!");
		} catch (InterruptedException e) {
			System.out.println("There was a problem with the Sleep cycle");
			e.printStackTrace();
		}
		
		for(int x = 0; x < size; x++) {
			sum += square[x][column-size];
		}
		
		System.out.println("Thread column checker " + (column-size) + ": sum is " + sum);
		MagicSquare.sums[column] = sum;
	}
	
}
