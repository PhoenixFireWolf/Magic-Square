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
 * The RowChecker class takes a possible magic square and sums the Row it was assigned. 
 * Then places that value into the sums array in the MagicSquare class.
 * </p>
 * 
 * @author Fred Kaup
 */
public class RowChecker {
	
	/**
	 * <p>
	 * Given a 2D array with equal dimensions and an integer 
	 * designating the Row you wish it to work on.
	 * This method will calculate the sum of all elements in the Row specified.
	 * </p>
	 * <p>
	 * Note: Number given in Row is actually a specific thread in the thread[] 
	 * Row corresponds to the Row counted from left to right with a zero index.
	 * Threads 0 - ([size of 1D of the 2D Array]-1) is designated to Rows.
	 * Threads [size of 1D of the 2D Array] - (2*[size of 1D of the 2D Array]-1) is designated to Rows.
	 * The last 2 threads are designated to Rows.
	 * </p>
	 * 
	 * 
	 * @param square - This is the potential Magic Square, as a 2D array.
	 * @param row - This is the thread number that will be working on the specified row.
	 */
	public static void rowChecker(int[][] square, int row) {
		int sum = 0;
		int size = square[row].length;
		
		//randomly determins the amount of time the thread will wait before it does its work.
		Random rdm = new Random();
		int wait = rdm.nextInt(5) * 1000;
		
		try {
			System.out.println("Thread row checker " + row + " is taking a nap! zzz...");
			Thread.sleep(wait);
			System.out.println("Thread row checker " + row + " snorts and wakes up!");
		} catch (InterruptedException e) {
			System.out.println("There was a problem with the Sleep cycle");
			e.printStackTrace();
		}
		
		for(int x = 0; x < size; x++) {
			sum += square[row][x];
		}
		
		System.out.println("Thread row checker " + row + ": sum is " + sum);
		MagicSquare.sums[row] = sum;
	}
}
