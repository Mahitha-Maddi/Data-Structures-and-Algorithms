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
public class Graph {
    static void addEdge(ArrayList<ArrayList<Integer> > adj, int u, int v) 
    { 
        adj.get(u).add(v); 
        //adj.get(v).add(u); 
    } 
  
    // A utility function to print the adjacency list 
    // representation of graph 
    static void printGraph(ArrayList<ArrayList<Integer> > adj) 
    { 
        for (int i = 0; i < adj.size(); i++) { 
            System.out.println("\nAdjacency list of vertex" + i); 
            for (int j = 0; j < adj.get(i).size(); j++) { 
                System.out.print(" -> "+adj.get(i).get(j)); 
            } 
            System.out.println(); 
        } 
    } 
  
    // Driver Code 
    public static void main(String[] args) 
    { 
        // Creating a graph with 5 vertices 
        int V = 13; 
        ArrayList<ArrayList<Integer> > adj  
                    = new ArrayList<ArrayList<Integer> >(V); 
          
        for (int i = 0; i < V; i++) 
            adj.add(new ArrayList<Integer>()); 
  
        // Adding edges one by one 
        addEdge(adj, 0, 1); 
        addEdge(adj, 0, 5); 
        addEdge(adj, 2, 0); 
        addEdge(adj, 2, 3); 
        addEdge(adj, 3, 2); 
        addEdge(adj, 3, 5); 
        addEdge(adj, 4, 2); 
        addEdge(adj, 4, 3); 
        addEdge(adj, 5, 4); 
        addEdge(adj, 6, 0); 
        addEdge(adj, 6, 4); 
        addEdge(adj, 6, 8); 
        addEdge(adj, 6, 9); 
        addEdge(adj, 7, 6); 
        addEdge(adj, 7, 9); 
        addEdge(adj, 8, 6);
         addEdge(adj, 9, 10); 
        addEdge(adj, 9, 11); 
        addEdge(adj, 10, 12); 
        addEdge(adj, 11, 4); 
        addEdge(adj, 11, 12); 
        addEdge(adj, 12, 9); 
        
          
        printGraph(adj); 
    } 
}
