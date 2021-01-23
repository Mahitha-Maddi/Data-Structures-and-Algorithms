/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.functions;

import java.util.Arrays;

/**
 *
 * @author mahit
 */
public class GreedyAlgorithm {
    
    static int getTime(int[] a, int t) {
        int currentTime = 0;
		int totalThings = 0;

		Arrays.sort(a);
		for (int i = 0; i < a.length; i++) {
			if (currentTime >= t) {
				break;
			}
			currentTime += a[i];
			totalThings++;
		}
		System.out.println("Total things that can be done in T = 15: " + totalThings);
	return totalThings;

        
    }
    public static void main(String args[]) {
		int freeTime = 15;
		int things[] = { 8, 7, 6, 5, 4, 3, 2, 1 };
                getTime(things,freeTime);

}
}
