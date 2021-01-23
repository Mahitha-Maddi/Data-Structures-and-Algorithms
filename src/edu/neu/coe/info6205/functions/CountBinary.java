/*
 * Shiva Mahitha Maddi
 * 001061161
 */
package edu.neu.coe.info6205.functions;

/**
 *
 * @author mahit
 */
public class CountBinary {
    public static int numStrings(int n)
{
// break cases
if(n == 0) // 1 string of length 0
return 1;
if(n == 1) // 2 strings: 1 and 0
return 2;

// The number of such strings is the number of strings that start with a 0 plus the number of strings that start with 10.
return numStrings(n-1) + numStrings(n-2);
}
    
    
     public static void main(String[] args) {
         
         System.out.println(" number of binary strings of length n = 4 that do not have two consecutive 0's is " + numStrings(4));
     }
}
