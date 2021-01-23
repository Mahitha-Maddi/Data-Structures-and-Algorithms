/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.functions;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author mahit
 */
public class GraphBagDataStructures {

    static class Bag<T> {

        private final int INITIAL_CAPACITY = 50;   // default capacity of bag
        private int capacity;  // number of T objects the bag can hold 
        private T[] data;  // array to hold objects of type T in the Bag
        private int count; // number of integers stored in the array

        /**
         * Default constructor creates an empty Bag
         */
        @SuppressWarnings("unchecked")
        public Bag() {
            // create array of T elements
            setCapacity(INITIAL_CAPACITY);
            data = (T[]) new Object[capacity];
            count = 0;
        }

        /**
         * Mutator for capacity of the bag.
         *
         * @param (int) newCapacity - integer representing new size of bag
         */
        public void setCapacity(int newCapacity) {
            capacity = newCapacity;
        }

        public int size() {
            return count;
        }

        /**
         * Adds a new element to the bag.
         *
         * @param element (T) object to be added to the bag
         */
        public void add(T element) {
            // if current number of elements is at capacity
            if (count >= capacity) {
                resize();		// expand size of bag 
            } // add item at end of the array
            else {
                data[count] = element;
                count++;
            }
        }

        /**
         * Increases size of the bag by one half.
         */
        private void resize() {
            capacity *= 1.5;
            data = Arrays.copyOf(data, capacity);
        }

        /*
         * Print elements of the bag.
         
        public void print() {
            // print out number of elements
            System.out.println("The bag has " + count + " elements:");
            // if not empty
            if (!isEmpty()) {
                // uses toString method of objects to print them
                // separated by commas
                System.out.print(data[0].toString());
                for (int i = 1; i < count; i++) {
                    System.out.print(", " + data[i]);
                }
                System.out.println();
            }
        }*/

        public boolean contains(T target) {
            Boolean found = false;
            int i = 0; 	// loop variable

            // iterate through array looking for element
            while (i < count && !found) {
                // if found, set found to true 
                if (data[i] == target) {
                    found = true;
                }
                i++;
            }
            return found;
        }

        /**
         * Whether bag is empty or not.
         *
         * @return (boolean) representing whether empty or not
         */
        public boolean isEmpty() {
            // empty if count equals 0, not otherwise 
            return (count == 0);
        }

        /**
         * Returns reference to element at given index.
         *
         * @param index (int) index in the array
         * @return (T) reference to element at that index
         * @throws IndexOutOfBoundsException
         */
        public T get(int index) {
            T target = null;
            // if try to access index that contains an element
            if (index < 0 || index >= count) {
                throw new IndexOutOfBoundsException("Bag - get(index)");
            }
            target = data[index];
            return target;
        }

    }
    static final int v =10 ;
    public Bag<Integer>[] adj;
 public Bag<Integer>[] adj1;
    public GraphBagDataStructures() {
        //this.v =v;
        adj = (Bag<Integer>[]) new Bag[v];
        adj1=(Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Integer>();
        }
         for (int i = 0; i < v; i++) {
            adj1[i] = new Bag<Integer>();
        }

    }

     void addEdge(int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
    }
     
       void addEdgeDirected(int u, int v) {
        adj1[u].add(v);
       // adj[v].add(u);
    }

    // A utility function to print the adjacency list 
    // representation of graph 
     void printGraph(Bag<Integer>[] adj) {
        for (int i = 1; i < adj.length; i++) {
            //System.out.println("\nAdjacency list of vertex" + i);
            for (int j = 0; j < adj[i].size(); j++) {
                System.out.print("\nAdjacency list of vertex" + i+" -> " + adj[i].get(j));
            }
            System.out.println();
        }
    }
     
         void printDirectedGraph(Bag<Integer>[] adj1) {
        for (int i = 1; i < adj1.length; i++) {
           // System.out.println("\nAdjacency list of vertex" + i);
            for (int j = 0; j < adj1[i].size(); j++) {
                System.out.print("\nAdjacency list of vertex" + i+" -> " + adj1[i].get(j));
            }
            System.out.println();
        }
    }

    // Driver Code 
    public static void main(String[] args) {

    System.out.println("\nUndirected Graph: " );
    
        GraphBagDataStructures graph = new GraphBagDataStructures();
        // Adding edges one by one 
        graph.addEdge(5, 6);
        graph.addEdge( 4, 6);
        graph.addEdge( 3, 7);
        graph.addEdge( 6, 7);
        graph.addEdge( 5, 7);
        graph.addEdge( 1, 4);
        graph.addEdge( 2, 4);
        graph.addEdge( 2, 3);
        graph.addEdge( 4, 7);
        graph.addEdge( 4, 8);
        graph.addEdge(5, 9);
        

        graph.printGraph(graph.adj);
        
         System.out.println("**********************************************************************************" );
         System.out.println("\nDirected Graph: " );
         
          GraphBagDataStructures graph1 = new GraphBagDataStructures();
        // Adding edges one by one 
        graph1.addEdgeDirected(5, 6);
        graph1.addEdgeDirected(4, 6);
        graph1.addEdgeDirected(3, 7);
        graph1.addEdgeDirected(6, 7);
        graph1.addEdgeDirected(5, 7);
        graph1.addEdgeDirected(1, 4);
        graph1.addEdgeDirected(2, 4);
        graph1.addEdgeDirected(2, 3);
        graph1.addEdgeDirected(4, 7);
        graph1.addEdgeDirected(4, 8);
        graph1.addEdgeDirected(5, 9);
        

        graph1.printDirectedGraph(graph1.adj1);
    }
}
