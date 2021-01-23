package edu.neu.coe.info6205.functions.newpackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InitialPopulation {

	int V;

	InitialPopulation(int V) {
		this.V = V;
	}

	List<int[][]> generatePopulation(int popSize, int[][] graph, int[] vertices, int[] shortestPathTree, int alp) {
		List<int[][]> initialPopulation = new ArrayList<>();
		while (initialPopulation.size() < popSize) {
			GetGraph g = new GetGraph(popSize, graph, vertices, V, shortestPathTree);
			int[][] newGraph = g.getGraph();
			for (int i = 0; i < newGraph.length; i++) {
				for (int j = 0; j < newGraph.length; j++) {
//					System.out.print(newGraph[i][j] + " ");
				}
//				System.out.println();
			}
			int[] mspTree;
			int validTree = 0;
			Dijkstras dij = new Dijkstras();
			mspTree = dij.dijkstra(newGraph, 0);
//			System.out.println("Vertex \t\t Distance from Source");
			for (int i = 0; i < V; i++) {
//				System.out.println(i + " \t\t " + mspTree[i]);
				if (mspTree[i] <= alp * shortestPathTree[i]) {
					validTree++;
				}
			}
			if (validTree == 5) {
				initialPopulation.add(newGraph);
                        }
//			System.out.println(validTree);

		}
		System.out.println("\nInitial population size is "+ initialPopulation.size());
            /*    for(int i=0; i <initialPopulation.size();i++ ){
                   int x[][]= initialPopulation.get(i);
                    for (int j = 0; j < x.length; j++) 
                    {
            // Loop through all elements of current row 
            for (int k = 0; k < x[j].length; k++) 
                System.out.print(i+":" +x[j][k] + " "); 
    } System.out.println(" "); 
                
                }*/
		//System.out.println("Initial population is " + );
		return initialPopulation;
	}
}
