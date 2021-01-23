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
public class Main2 {
     public static void main(String[] args) 
    { 
        ArrayList<Integer> x =new ArrayList<Integer>(); 
        x.add(20);
        change(x); 
        System.out.println(x); 
    } 
    public static void change(  ArrayList<Integer> x) 
    { 
        x.add (10); 
    } 

}
