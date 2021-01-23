/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.functions;

import java.util.Scanner;

/**
 *
 * @author mahit
 */
public class subarray_sum {
    public static void main (String[] args) {
		//code
		
		Scanner obj=new Scanner(System.in);
		//String s=obj.nextLine();
		String l1=obj.nextLine();
		String l2=obj.nextLine();
		
		String[] str=l1.split(" ");
		int n=Integer.parseInt(str[0]);
		int sum=Integer.parseInt(str[1]);
		String[] str1=l2.split(" ");
		
		for(int i=0;i<n;i++){
		    int sum1=Integer.parseInt(str1[i]);
		    for(int j=i+1;j<n;j++){
		       sum1=sum1+ Integer.parseInt(str1[j]);
		       if(sum1==sum){
		           System.out.println((i+1)+" "+(j+1));
		           break;
		       }
		    }
		}
	}
}
