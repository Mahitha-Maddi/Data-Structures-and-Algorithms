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
public class QuickSort {
     int partition(int arr[], int low, int high) 
    { 
        int pivot = arr[high];  
        int i = low; // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            // If current element is smaller than the pivot 
            if (arr[j] < pivot) 
            { 
               // swap arr[i] and arr[j] 
                int temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
                 i++; 
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = arr[i]; 
        arr[i] = arr[high]; 
        arr[high] = temp; 
  
        return i; 
    } 
  
  
    /* The main function that implements QuickSort() 
      arr[] --> Array to be sorted, 
      low  --> Starting index, 
      high  --> Ending index */
    void sort(int arr[], int low, int high) 
    { 
        if (low < high) 
        { 
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = partition(arr, low, high); 
  
            // Recursively sort elements before 
            // partition and after partition 
            sort(arr, low, pi-1); 
            sort(arr, pi+1, high); 
        } 
    } 
  
    /* A utility function to print array of size n */
    static void printArray(int arr[]) 
    { 
            System.out.print(Arrays.toString(arr)); 
        System.out.println(); 
    } 
    
     public static void main(String args[]) 
    { 
        int arr[] = {11, 27, 43, 38, 3, 9, 82, 10, 21, 8, 34, 19, 6,100}; 
        int n = arr.length; 
  
        System.out.println("Before sorting"); 
        printArray(arr);
        QuickSort ob = new QuickSort(); 
        ob.sort(arr, 0, n-1); 
  
        System.out.println("sorted array"); 
        printArray(arr); 
    } 
  
}
