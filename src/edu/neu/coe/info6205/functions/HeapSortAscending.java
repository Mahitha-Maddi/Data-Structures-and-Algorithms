/*
 * Shiva Mahitha Maddi
 * 001061161
 */
package edu.neu.coe.info6205.functions;

/**
 *
 * @author mahit
 */
public class HeapSortAscending {
     public void sort(int arr[]) 
    { 
        int n = arr.length; 
  
        // Build heap (rearrange array) 
        for (int i = n / 2 ; i >= 0; i--) 
            heapify(arr, n, i); 
  
        // One by one extract an element from heap 
        for (int i=n-1; i>0; i--) 
        { 
            // Move current root to end 
            int temp = arr[0]; 
            arr[0] = arr[i]; 
            arr[i] = temp; 
  
            // call max heapify on the reduced heap 
            heapify(arr, i, 0); 
        } 
    } 
  
    // To heapify a subtree rooted with node i which is 
    // an index in arr[]. n is size of heap 
    void heapify(int arr[], int n, int i) 
    { 
        int largest = i; // Initialize largest as root 
        int l = 2*i + 1; // left = 2*i + 1 
        int r = 2*i + 2; // right = 2*i + 2 
  
        // If left child is larger than root 
        if (l < n && arr[l] > arr[largest]) 
            largest = l; 
  
        // If right child is larger than largest so far 
        if (r < n && arr[r] > arr[largest]) 
            largest = r; 
  
        // If largest is not root 
        if (largest != i) 
        { 
            int swap = arr[i]; 
            arr[i] = arr[largest]; 
            arr[largest] = swap; 
  
            // Recursively heapify the affected sub-tree 
            heapify(arr, n, largest); 
        } 
    } 
  
    // A utility function to print array of size n 
    static void printArray(int arr[]) 
    { 
        int n = arr.length; 
        
        System.out.println("Array after sorting is"); 
        for (int i=0; i<n; ++i) 
            System.out.print(arr[i]+" "); 
        System.out.println(); 
    } 
  
    public static void main(String args[]) 
    { 
        int arr[] = {11, 27, 43, 38, 3, 9, 82, 10, 21, 8, 34, 19, 6}; 
        int n = arr.length; 
          System.out.print("Array before sorting is " );
        for (int i=0; i<n; ++i) 
            System.out.print(arr[i]+" "); 
        System.out.println();
  
        HeapSortAscending ob = new HeapSortAscending(); 
        ob.sort(arr); 
  
        printArray(arr); 
    } 
}
