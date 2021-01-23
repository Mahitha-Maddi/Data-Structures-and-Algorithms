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
public class MinCutsBinaryString1 {
     static boolean ispower(long n) 
    { 
        if (n < 125) 
        { 
            return (n == 1 || n == 5 || n == 25); 
        } 
        if (n % 125 != 0) 
        { 
            return false; 
        } 
        else
        { 
            return ispower(n / 125); 
        } 
    } 
  
    // Function to return the decimal 
    // value of binary equivalent 
    static long number(String s, int i, int j)  
    { 
        long ans = 0; 
        for (int x = i; x < j; x++)  
        { 
            ans = ans * 2 + (s.charAt(x) - '0'); 
        } 
        return ans; 
    } 
  
    // Function to return the minimum cuts required 
    static int minCuts(String s, int n) 
    { 
        int[] dp = new int[n + 1]; 
  
        // Alongocating memory for dp[] array 
        Arrays.fill(dp, n+1); 
        dp[0] = 0; 
  
        // From length 1 to n 
        for (int i = 1; i <= n; i++) 
        { 
  
            // If previous character is '0' then ignore 
            // to avoid number with leading 0s. 
            if (s.charAt(i - 1) == '0') 
            { 
                continue; 
            } 
            for (int j = 0; j < i; j++)  
            { 
  
                // Ignore s[j] = '0' starting numbers 
                if (s.charAt(j) == '0')  
                { 
                    continue; 
                } 
  
                // Number formed from s[j....i] 
                long num = number(s, j, i); 
  
                // Check for power of 5 
                if (!ispower(num))  
                { 
                    continue; 
                } 
  
                // Assigning min value to get min cut possible 
                dp[i] = Math.min(dp[i], dp[j] + 1); 
            } 
        } 
  
        // (n + 1) to check if all the Strings are traversed 
        // and no divisible by 5 is obtained like 000000 
        return ((dp[n] < n + 1) ? dp[n] : -1); 
    } 
  
    // Driver code 
    public static void main(String[] args)  
    { 
        String s = "1101"; 
        int n = s.length(); 
        System.out.println(minCuts(s, n)); 
    } 
}
