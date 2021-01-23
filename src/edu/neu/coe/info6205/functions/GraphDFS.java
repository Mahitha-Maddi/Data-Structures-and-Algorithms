/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.functions;

import edu.neu.coe.info6205.functions.GraphDFS.Edge;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author mahit
 */
public class GraphDFS {
    // Data structure to store graph edges
static class Edge
{
	int source, dest;

	public Edge(int source, int dest) {
		this.source = source;
		this.dest = dest;
	}
}

	// A List of Lists to represent an adjacency list
	List<List<Integer>> adjList = null;

	// Constructor
	GraphDFS(List<Edge> edges, int N) {
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

class Main1
{
	// Function to perform DFS Traversal
	public static void DFS(GraphDFS graph, int v, boolean[] discovered)
	{
		// mark current node as discovered
		discovered[v] = true;

		// print current node
		System.out.print(v + " ");

		// do for every edge (v -> u)
		for (int u : graph.adjList.get(v))
		{
			// u is not discovered
			if (!discovered[u]) {
				DFS(graph, u, discovered);
			}
		}
	}

        // Perform iterative DFS on graph g starting from vertex v
	public static void iterativeDFS(GraphDFS graph, int v, boolean[] discovered)
	{
		// create a stack used to do iterative DFS
		Stack<Integer> stack = new Stack<>();

		// push the source node into stack
		stack.push(v);

		// run till stack is not empty
		while (!stack.empty())
		{
			// Pop a vertex from stack
			v = stack.pop();

			// if the vertex is already discovered yet, ignore it
			if (discovered[v])
				continue;

			// we will reach here if the popped vertex v
			// is not discovered yet. We print it and process
			// its undiscovered adjacent nodes into stack
			discovered[v] = true;
			System.out.print(v + " ");

			// do for every edge (v -> u)
			List<Integer> adj = graph.adjList.get(v);
			for (int i = adj.size() - 1; i >= 0; i--)
			{
				int u = adj.get(i);
				if (!discovered[u]) {
					stack.push(u);
				}
			}
		}
	}
	// Recursive Java implementation of Depth first search
	public static void main(String[] args)
	{
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
		GraphDFS graph = new GraphDFS(edges, N);

		// stores vertex is discovered or not
		boolean[] discovered = new boolean[N+1];
                
                 //**************************************************************************
               // Iterative solution
               System.out.print("Iterative Solution: ");
               // Do iterative DFS traversal from all undiscovered nodes to
		// cover all unconnected components of graph
		for (int i = 1; i < N+1; i++) {
			if (!discovered[i]) {
				iterativeDFS(graph, i, discovered);
			}
		}
               
                
                //************************************************************************
                 
                 for (int i = 0; i < N+1; i++) {
			discovered[i] = false;
                 }
                 
                
                //************************************************************************
               // Recursive solution
               System.out.print("\nRecursive Solution: ");


		// Do DFS traversal from all undiscovered nodes to
		// cover all unconnected components of graph
		for (int i = 1; i < N+1; i++) {
			if (!discovered[i]) {
				DFS(graph, i, discovered);
			}
		}
	}
}
