/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.functions;

import edu.neu.coe.info6205.functions.GraphBFS.Edge;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author mahit
 */
public class GraphBFS {
    // A List of Lists to represent an adjacency list
	List<List<Integer>> adjList = null;
   static  class Edge
{
	int source, dest;

	public Edge(int source, int dest) {
		this.source = source;
		this.dest = dest;
	}
}

// class to represent a graph object

	

	// Constructor
	GraphBFS(List<Edge> edges, int N)
	{
		adjList = new ArrayList<>();

		for (int i = 0; i < N+1; i++) {
			adjList.add(new ArrayList<>());
		}

		// add edges to the undirected graph
		for (Edge edge: edges)
		{
			int src = edge.source;
			int dest = edge.dest;

			adjList.get(src).add(dest);
			adjList.get(dest).add(src);
		}
	}
}

class Main
{
	// Perform BFS on graph starting from vertex v
	public static void BFS(GraphBFS graph, int v, boolean[] discovered)
	{
		// create a queue used to do BFS
		Queue<Integer> q = new ArrayDeque<>();

		// mark source vertex as discovered
		discovered[v] = true;

		// push source vertex into the queue
		q.add(v);

		// run till queue is not empty
		while (!q.isEmpty())
		{
			// pop front node from queue and print it
			v = q.poll();
			System.out.print(v + " ");

			// do for every edge (v -> u)
			for (int u : graph.adjList.get(v)) {
				if (!discovered[u]) {
					// mark it discovered and push it into queue
					discovered[u] = true;
					q.add(u);
				}
			}
		}
	}
        
        // Perform BFS recursively on graph
	public static void recursiveBFS(GraphBFS graph, Queue<Integer> q, boolean[] discovered)
	{
		if (q.isEmpty())
			return;

		// pop front node from queue and print it
		int v = q.poll();
		System.out.print(v + " ");

		// do for every edge (v -> u)
		for (int u : graph.adjList.get(v))
		{
			if (!discovered[u])
			{
				// mark it discovered and push it into queue
				discovered[u] = true;
				q.add(u);
			}
		}

		recursiveBFS(graph, q, discovered);
	}

	// Iterative Java implementation of Breadth first search
	public static void main(String[] args)
	{
		// List of graph edges as per above diagram
		List<Edge> edges = Arrays.asList(
				new Edge(5, 6), new Edge(4, 6), new Edge(3, 7),
				new Edge(6, 7), new Edge(5, 7), new Edge(1, 4),
				new Edge(2, 4), new Edge(2, 3), new Edge(4, 7),
				new Edge(4, 8), new Edge(5, 9)
				// vertex 0, 13 and 14 are single nodes
		);

		// Set number of vertices in the graph
		final int N = 9;

		// create a graph from edges
		GraphBFS graph = new GraphBFS(edges, N);

		// stores vertex is discovered or not
		boolean[] discovered = new boolean[N+1];
               //**************************************************************************
               // Iterative solution
               System.out.print("Iterative Solution: ");
		// Do BFS traversal from all undiscovered nodes to
		// cover all unconnected components of graph 
		for (int i = 1; i < N+1; i++) {
			if (discovered[i] == false) {
				// start BFS traversal from vertex i
				BFS(graph, i, discovered);
			}
		}
                
                
                
                 //************************************************************************
                 
                 for (int i = 0; i < N+1; i++) {
			discovered[i] = false;
                 }
                 
                 //************************************************************************
               // Recursive solution
               System.out.print("\nRecursive Solution: ");
               
		// create a queue used to do BFS
		Queue<Integer> q = new ArrayDeque<>();
                // Do BFS traversal from all undiscovered nodes to
		// cover all unconnected components of graph
                
		for (int i = 1; i < N+1; i++)
		{
			if (discovered[i] == false)
			{
				// mark source vertex as discovered
				discovered[i] = true;
				// push source vertex into the queue
				q.add(i);

				// start BFS traversal from vertex i
				recursiveBFS(graph, q, discovered);
			}
		}
                
                
                
	}
}
