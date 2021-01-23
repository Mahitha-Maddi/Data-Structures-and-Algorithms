/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.functions;

import java.util.ArrayList;

/**
 *
 * @author mahit
 */
public class FSMCode {

    int fsm_state;
    char fsm_input;

    boolean is_final_state(int state) {
        return (state == 1 || state == 2) ? true : false;
    }

    int get_start_state() {
        return 0;
    }

    static int move(int state, char input) {
        System.out.println("State: " + state + " and Input: " + input);
        if (input != 'X' && input != 'Y' && input != 'Z') {
            System.out.println("Invalid input: " + input);
            return -1;
        }

        switch (state) {
            case 0:
                if (input == 'X') {
                    return 1;
                } else if (input == 'Y') {
                    return -1;
                } else if (input == 'Z') {
                    return 2;
                }
                break;
            case 1:
                if (input == 'X') {
                    return 1;
                } else if (input == 'Y') {
                    return 1;
                } else if (input == 'Z') {
                    return -1;
                }
                break;
            case 2:
                if (input == 'X' || input == 'Y' || input == 'Z') {
                    return -1;
                }
                break;
            /*case 3:
                if (input == 'a') {
                    return 1;
                } else if (input == 'b') {
                    return 0;
                }
                break;*/
            default: {
                return -1;
            }

        }
        return -1;
    }

    boolean recognize(String str) {
        //if (str == "") return false; 
        
        System.out.println("Input string is "+str);
        int state = get_start_state();
        // string::const_iterator i = str.begin(); 
        // fsm_input input = *i; 
        for (int i = 0; i < str.length(); i++) {

            state = move(state, str.charAt(i));

        }
        if (is_final_state(state)) {
            System.out.println("Reached final state: " + state);
            return true;
        } else {
            return false;
        }
    }

    // simple driver for testing 
    public static void main(String args[]) {

        FSMCode f = new FSMCode();
        String regularExpression = "X(X|Y)*|Z";

        System.out.println("Regular expression is " + regularExpression);
        System.out.println("Strings  Z, X, XX, XY, XXY, XXXY, XYYY satisfy the given regular expression.");
                 System.out.println(" Therefore, they should reach final state when sent as input to recognize function i.e. FSM.");

        System.out.println("Strings XZ, XXZ do not satisfy the given regular expression.");
                 System.out.println(" Therefore, they should not reach final state when sent as input to recognize function i.e. FSM.");
        System.out.println("*******************************************************************************************************************");

        if (f.recognize("Z") == true) {

            System.out.println("Reached accepting/final state");
        } else {

            System.out.println("Didn't reach accepting/final state");
        }

        System.out.println("*******************************************************************************************************************");
        if (f.recognize("XXY") == true) {

            System.out.println("Reached accepting/final state");
        } else {

            System.out.println("Didn't reach accepting/final state");
        }
        System.out.println("*******************************************************************************************************************");
        if (f.recognize("XYYY") == true) {

            System.out.println("Reached accepting/final state");
        } else {

            System.out.println("Didn't reach accepting/final state");
        }
        System.out.println("*******************************************************************************************************************");
        if (f.recognize("XZ") == true) {

            System.out.println("Reached accepting/final state");
        } else {

            System.out.println("Didn't reach accepting/final state");
        }
        System.out.println("*******************************************************************************************************************");
        if (f.recognize("XXZ") == true) {

            System.out.println("Reached accepting/final state");
        } else {

            System.out.println("Didn't reach accepting/final state");
        }
    }

}
