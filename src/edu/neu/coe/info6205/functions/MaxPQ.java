package edu.neu.coe.info6205.functions;
/*
 *Shiva Mahitha Maddi
 * 001061161
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author mahit
 */
public class MaxPQ {
    private Comparable[] pq;     // store element at indices 1 to N
    private int N; // number of elements on priority queue
    ArrayList<Integer> list;

    // set inititial capacity of heap to hold given number of elements
    public MaxPQ(int capacity) {
        this.pq = new Comparable[capacity + 1];
        this.N = 0;
        this.pq[0] = Integer.MAX_VALUE;
        list = new ArrayList<Integer>();
        
    }

    // set inititial capacity of heap to hold 0 elements
    public MaxPQ() { this(0); }

    public boolean isEmpty() { return N == 0; }    // is the PQ empty?
    public int size()        { return N;      }    // how many elements on PQ?
    public Comparable max()  { return pq[1];  }    // largest element always at index 1

    // helper function to double the size of the heap array
    private void resize() {
        Comparable[] temp = new Comparable[2*pq.length];
        for (int i = 0; i <= N; i++) temp[i] = pq[i];
        pq = temp;
    }

    // add a new element to the priority queue
    public void insert(Comparable x) {

        // double size of array if necessary
        if (N >= pq.length - 1) resize();
/*
        pq[++N] = x;
         int current = N; 
        while (less(parent(current),current)) { 
            swap(current, parent(current)); 
            current = parent(current); 
        } */
        // add x, and percolate it up to maintain heap invariant less(parent(k), k)
        pq[++N] = x;
        swim(N);
    }

    // delete and return the maximum element, restoring the heap-order invariant
    public Comparable delMax() {
        if (N == 0) throw new RuntimeException("Priority queue underflow");
        swap(1, N);
        Comparable max = pq[N--];
        sink(1);
        pq[N + 1] = null;             // to help with garbage collection
        return max;
    }

    
     public void print() 
    { 
        for (int i = 1; i <= N / 2; i++) { 
            System.out.print(" PARENT : " + pq[i] + " LEFT CHILD : " + 
                      pq[2 * i] + " RIGHT CHILD :" + pq[2 * i + 1]); 
            System.out.println(); 
        } 
    } 
  
  /********************************************************************
   * 
   * 
   */
    
    // Returns position of parent 
    private int parent(int pos) 
    { 
        return pos / 2; 
    } 
  
    // Below two functions return left and 
    // right children. 
    private int leftChild(int pos) 
    { 
        return (2 * pos); 
    } 
    private int rightChild(int pos) 
    { 
        return (2 * pos) + 1; 
    } 
  
    // Returns true of given node is leaf 
    private boolean isLeaf(int pos) 
    { 
        if (pos >= (N / 2) && pos <= N) { 
            return true; 
        } 
        return false; 
    } 
   /***********************************************************************
    * Helper functions to restore the heap invariant.
    **********************************************************************/
   
    private void swim(int k) {
        while (k >1 && less(parent(k), k)) {
            swap(k, k/2);
            k = parent(k);
        }
    }

    private void sink(int pos) { // MaxHeapify process
      /*  
         if (2*pos <= N && pos<=N) 
            return;
        if (pq[pos].compareTo(pq[leftChild(pos)])<0 ||  pq[pos].compareTo(pq[rightChild(pos)])<0) { 
   if(less(leftChild(pos),rightChild(pos)))
            //if (pq[leftChild(pos)].compareTo(pq[rightChild(pos)])<0)
   { 
                swap(pos, leftChild(pos)); 
               sink(leftChild(pos)); 
            } 
            else { 
                swap(pos, rightChild(pos)); 
                sink(rightChild(pos)); 
            } 
        } 
 */
        
        while (2*pos <= N) { //while position is not any of the leaf nodes
            int j = 2*pos;
            if (j < N && less(j, j+1)) j++;
            if (!less(pos, j)) break;
            swap(pos, j);
            pos = j;
        }
    }

   /***********************************************************************
    * Helper functions for comparisons and swaps.
    **********************************************************************/
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void swap(int i, int j) {
        Comparable swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

   /**************************************************************************
    * Reading input data
    */
    
    
    public void readFile() {
        Scanner s;
        try {
            s = new Scanner(new File("C:\\Users\\mahit\\Downloads\\inputpq.txt"));

            while (s.hasNextLine()) {
                list.add(Integer.parseInt(s.next()));
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }}
   /***********************************************************************
    * Test routine.
    **********************************************************************/
    public static void main(String[] args) {
        MaxPQ pq = new MaxPQ(15);
        pq.readFile();
        System.out.println("Priority Queue is Empty: " + pq.isEmpty());
        System.out.println();
        System.out.println("\nInserting all elements into Priority queue");
        
        
        for (int i = 0; i < pq.list.size(); i++) {
            pq.insert(pq.list.get(i));
        }
        
        pq.print();
        System.out.println("Priority Queue is Empty: " + pq.isEmpty());
        System.out.println();
        System.out.println("\nDeleting all elements from Priority queue");
        
     
       while (!pq.isEmpty())
            System.out.println(pq.delMax());
    
    
     System.out.println("Priority Queue is Empty: " + pq.isEmpty());
        System.out.println();
        System.out.println("\nInserting all elements into Priority queue");
        
        
        for (int i = 0; i < pq.list.size(); i++) {
            pq.insert(pq.list.get(i));
        }
    
        
        System.out.println("Priority Queue is Empty: " + pq.isEmpty());
        System.out.println();
        
        pq.print();
         System.out.println("\nSize of Priority Queue " + pq.size());
        System.out.println();
         System.out.println("\nMax element of Priority Queue " + pq.max());
        System.out.println();
        
    

    }

}
