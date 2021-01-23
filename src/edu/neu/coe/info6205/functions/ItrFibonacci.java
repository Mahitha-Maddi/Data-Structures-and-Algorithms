/*
 * Shiva Mahitha Maddi
 * 001061161
 */
package edu.neu.coe.info6205.functions;

/**
 *
 * @author mahit
 */
public class ItrFibonacci {
    int fib(int n) {
		if (n == 0) {
			return 0;
		}
		int prev, current = 0, res = 1;
		for (int i = 1; i < n; i++) {
			prev = current;
			current = res;
			res = prev + current;
		}
		return res;
	}

	public static void main(String args[]) {
		System.out.println("Calculating Fibonacci Series when n = 5 iteratively");
		System.out.println("Result: " + new ItrFibonacci().fib(5));
	}
}
