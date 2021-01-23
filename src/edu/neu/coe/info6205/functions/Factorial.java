
/*
 * Shiva Mahitha Maddi
 * 001061161
 */
package edu.neu.coe.info6205.functions;

/**
 *
 * @author mahit
 */
public class Factorial {
    static int fact(int n) {
		if (n == 1 || n == 0) {
			return 1;
		}
		return n * fact(n - 1);
	}

	public static void main(String args[]) {
		System.out.println("Input: 5!");
		System.out.println("Result: " + fact(5));
	}
}
