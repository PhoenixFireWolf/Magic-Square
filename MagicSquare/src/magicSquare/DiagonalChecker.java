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
 * The DiagonalChecker class takes a possible magic square and sums the Diagonal it was assinged. 
 * Then places that value into the sums array in the MagicSquare class.
 * </p>
 * 
 * @author Fred Kaup
 */
public class DiagonalChecker {
	
	/**
	 * <p>
	 * Given a 2D array with equal dimensions and an integer 
	 * designating the Diagonal you wish it to work on.
	 * This method will calculate the sum of all elements in the Diagonal specified.
	 * </p>
	 * <p>
	 * Note: Number given in Diagonal is actually a specific thread in the thread[] 
	 * diagonal corresponds to the Diagonal counted from left to right with a zero index.
	 * Threads 0 - ([size of 1D of the 2D Array]-1) is designated to Rows.
	 * Threads [size of 1D of the 2D Array] - (2*[size of 1D of the 2D Array]-1) is designated to Diagonals.
	 * The last 2 threads are designated to Diagonals.
 	 * </p>
	 *
	 * @param square - This is the potential Magic Square, as a 2D array.
	 * @param diagonal - This is the thread number that will be working on the specified Diagonal.
	 */
	public static void diagonalChecker(int[][] square, int diagonal) {
		int sum = 0;
		int size = square.length;
		
		//randomly determins the amount of time the thread will wait before it does its work.
		Random rdm = new Random();
		int wait = rdm.nextInt(5) * 1000;
		
		//given the thread(diagonal) determins which diagonal it is supposed to work on.
		int diag = diagonal - size*2;
		
		try {
			System.out.println("Thread diagonal checker " + diag + " is taking a nap! zzz...");
			Thread.sleep(wait);
			System.out.println("Thread diagonal checker " + diag + " snorts and wakes up!");
		} catch (InterruptedException e) {
			System.out.println("There was a problem with the Sleep cycle");
			e.printStackTrace();
		}
		
		if(diag == 0) {
			for(int x = 0; x < size; x++) {
				sum += square[x][x];
			}
		} else {
			for(int x = 0; x < size; x++) {
				sum += square[size-1-x][size-1-x];
			}
		}
		
		System.out.println("Thread diagonal checker " + diag + ": sum is " + sum);
		MagicSquare.sums[diagonal] = sum;
	}
	
}
