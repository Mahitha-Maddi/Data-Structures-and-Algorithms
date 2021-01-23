/*
 * Shiva Mahitha Maddi
 * 001061161
 */
package edu.neu.coe.info6205.functions;

import java.time.Duration;
import java.time.Instant;

/**
 *
 * @author mahit
 */
public class ReverseString {

    public static String reverse(String s) {
        String rev = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            rev += s.charAt(i);
        }
        return rev;
    }

    public static String reverseByStringBuilder(String s) {
        StringBuilder rev = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            rev.append(s.charAt(i));
        }
        return rev.toString();
    }

    public static void main(String[] args) {

        String input = "Welcome Students to Class";
        Instant start;
        Instant end;
        start = Instant.now();
        String op1=reverse(input);
        end = Instant.now();
        Duration Time = Duration.between(start, end);
         start = Instant.now();
        String op2=reverseByStringBuilder(input);
        end = Instant.now();
        Duration Time1 = Duration.between(start, end);
        System.out.println("Reversing string by string operations: " + op1 + ", Time taken: " + Time.getNano() );
        
        System.out.println("Reversing string by String Builder: " + op2 + ", Time taken: " + Time1.getNano() );

    }

}
