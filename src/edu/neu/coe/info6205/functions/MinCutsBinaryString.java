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
public class MinCutsBinaryString {

    static boolean isPowerOf(long val, int base) {
        while (val > 1) {
            if (val % base != 0) {
                return false;
            }
            val /= base;
        }

        return true;
    }

    static int numberOfPartitions(String binaryNo) {
        int i, j, n = binaryNo.length();
        long val;
        int dp[] = new int[n];
        dp[n - 1] = (((binaryNo.charAt(n - 1) - '0') == 0) ? -1 : 1);

        for (i = n - 2; i >= 0; i--) {
            val = 0;
            if ((binaryNo.charAt(i) - '0') == 0) {
                dp[i] = -1;
                continue;
            }

            dp[i] = Integer.MAX_VALUE;
            for (j = i; j < n; j++) {
                val = (val * 2) + (long) (binaryNo.charAt(j) - '0');
                if (isPowerOf(val, 5)) {
                    if (j == n - 1) {
                        dp[i] = 1;
                    } else {
                        if (dp[j + 1] != -1) {
                            dp[i] = Math.min(dp[i],
                                    dp[j + 1] + 1);
                        }
                    }
                }
            }
            if (dp[i] == Integer.MAX_VALUE) {
                dp[i] = -1;
            }
        }

        return dp[0];
    }
    
    
    public static void main (String[] args)  
{ 
    String binaryNo = "1101"; 
    System.out.println(numberOfPartitions(binaryNo)); 
} 
}
