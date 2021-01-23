/*
 * Shiva Mahitha Maddi
 * 001061161
 */
package edu.neu.coe.info6205.functions;

/**
 *
 * @author mahit
 */
public class RecFibonacci {
    int fib(int n) {
		if (n <= 1) {
			return n;
		}
		return fib(n - 1) + fib(n - 2);
	}

	public static void main(String args[]) {
		System.out.println("Calculating Fibonacci Series when n = 5 recursively");
		System.out.println("Result: " + new RecFibonacci().fib(5));
	}
}
