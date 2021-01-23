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
public class NewClass2 {
    	public static void main(String args[]) {
		String str = "Welcome Students to Class";
		int n = str.length();
		double h = 0.0;
		for (int i = 0; i < n; i++) {
			char c = str.charAt(i);
			int x = (int) c;
			h = (double) h + (x * (Math.pow(31, (n - 1 - i))));
		}
                
                
              /*
                for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			int ascii = (int)ch;
			int power = (int) Math.pow(31, input.length()-i-1);
			result += (int)ascii*power;
			
		}*/
		System.out.println("Input String: '" + str + "'");
		System.out.println("Hashcode: " + h);
	}

}
