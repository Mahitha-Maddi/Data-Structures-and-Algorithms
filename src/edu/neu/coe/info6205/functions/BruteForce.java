/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.functions;

/**
 *
 * @author mahit
 */
public class BruteForce {
    public static int search(String text, String pattern) {

		int lengthOfText = text.length();
		int lengthOfPattern = pattern.length();

		for (int i = 0; i <= lengthOfText - lengthOfPattern; i++) {

			int j;

			for (j = 0; j < lengthOfPattern; j++) {
				if (text.charAt(i + j) != pattern.charAt(j)) {
					break;
				}
			}

			if (j == lengthOfPattern)
				return i;
		}

		return lengthOfText;
	}

	public static void main(String args[]) {
		String text = "ABCADCBABABCDABCD";
		String pattern = "BCD";
		int patternAt = search(text, pattern);
		System.out.println("Input String: " + text);
		System.out.println("Pattern String: " + pattern);
		System.out.println("Pattern found at index: " + patternAt);
	}
}
