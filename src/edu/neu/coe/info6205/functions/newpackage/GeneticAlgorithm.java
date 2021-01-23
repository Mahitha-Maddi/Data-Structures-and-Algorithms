/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.functions.newpackage;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author mahit
 */
public class GeneticAlgorithm {

    static final int V = 5;

    int[][] graph;
    int[] vertices;
    int popSize;
    int maxGen;
    double pc;
    double pm;
    public int[] shortestPathTree;
    List<EDGE> mst;
    public int alpha;

    List<List<int[][]>> population;
    List<int[][]> initialPopulation;
    int gen;
    Random rand;

    GeneticAlgorithm() {

        // Edge-Weighted Graph
         System.out.println("Input data:**********************************************************************************************");
        vertices = new int[]{0, 1, 2, 3, 4};
         System.out.println("Vertices: "+Arrays.toString(vertices));
       
        this.graph = new int[][]{{0, 2, 0, 9, 3}, {2, 0, 10, 0, 0}, {0, 10, 0, 5, 8}, {9, 0, 5, 0, 6},
        {3, 0, 8, 6, 0}};
        
         System.out.println("Edges: " );
          for (int j = 0; j < graph.length; j++) 
                  {System.out.print("{" );  
            // Loop through all elements of current row 
            for (int k = 0; k < graph.length; k++) 
            {System.out.print(graph[j][k] + " ");} System.out.print("}" );  }
        // Population Size
        this.popSize = 30;

        // Maximum Generations
        this.maxGen = 300;

        // Crossover probability
        this.pc = 0.75;

        // Mutation probability
        this.pm = 0.5;
         System.out.println("\n popSize is 30, maxGen is 300, pc is 0.75, pm is 0.5, alpha is 5. " );
        // Shortest Distance Tree
        Dijkstras dij = new Dijkstras();
        this.shortestPathTree = dij.dijkstra(this.graph, 0);
        this.printSolution(this.shortestPathTree);

        // Minimum Spanning Tree
        Krusks krusks = new Krusks();
        mst = new ArrayList<>();
        mst = krusks.generateMinSpanTree();
        System.out.println("MST contains the edges: " + mst+"\n");

        // Aplha Value
        this.alpha = 5;

        // Initial Values
        this.population = new ArrayList<>();
        this.initialPopulation = new ArrayList<>();
        this.gen = 1;
        rand = new Random();

    }

    void printSolution(int dist[]) {
        System.out.println("Vertex \t\t Shortest Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + " \t\t " + dist[i]);
        }
    }

    void testGeneticAlgorithm() {

        // Generate Initial Population
        InitialPopulation ip = new InitialPopulation(V);
        this.initialPopulation = ip.generatePopulation(popSize, graph, vertices, shortestPathTree, alpha);
        population.add(initialPopulation);

        while (gen < maxGen) {
            List<int[][]> pop = population.get(gen - 1);
            List<List<int[][]>> ofs = new ArrayList<>();
            List<int[][]> mutationOfs = new ArrayList<>();
            List<int[][]> finalOfs = new ArrayList<>();

            Map<Integer, Integer> weights = new HashMap<>();
            int minWeightC1 = 0;

            List<int[][]> pop0 = new ArrayList<>();
            Map<Integer, Integer> weights0 = new HashMap<>();
            int minWeightC0 = 0;

            List<int[][]> pop2 = new ArrayList<>();
            int minWeightC2 = 0;
            Map<Integer, Integer> weights2 = new HashMap<>();

            for (int i = 1; i < popSize; i++) {
                int randomSelectionNumber1 = rand.nextInt(20);
                int randomSelectionNumber2;
                int[][] chromosome1 = pop.get(randomSelectionNumber1);

                do {
                    randomSelectionNumber2 = rand.nextInt(20);
                } while (randomSelectionNumber2 == randomSelectionNumber1);

                int[][] chromosome2 = pop.get(randomSelectionNumber2);

                // Crossover Function
                double crossoverNumber;
                do {
                    crossoverNumber = rand.nextDouble();
                } while (crossoverNumber == 0);

                if (crossoverNumber < pc) {

                    ofs.add(applyCrossoverFunction(chromosome1, chromosome2));
                }

                // Mutation Function
                int[][] mutationC = pop.get(i);
                for (int s = 0; s < V; s++) {
                    double mutationNumber;
                    do {
                        mutationNumber = rand.nextDouble();
                    } while (mutationNumber == 0);
                    if (mutationNumber < pm) {
                        int[][] temp = new int[V][V];
                        if (s == V - 1) {
                            temp[0] = mutationC[s];
                            mutationC[s] = mutationC[s - 1];
                            mutationC[s - 1] = temp[0];
                        } else {
                            temp[0] = mutationC[s];
                            mutationC[s] = mutationC[s + 1];
                            mutationC[s + 1] = temp[0];
                        }
                        mutationOfs.add(mutationC);
                    }
                }
            }

            // Next Generation Selection
            for (int i = 0; i < popSize; i++) {
                finalOfs.add(mutationOfs.get(i));
            }
            for (int i = 0; i < ofs.size(); i++) {
                finalOfs.add(ofs.get(i).get(0));
                finalOfs.add(ofs.get(i).get(1));
            }
            for (int i = 0; i < finalOfs.size(); i++) {
//				Arrays.sort(finalOfs.get(i), (a, b) -> Integer.compare(a[0], b[0]));
            }
            finalOfs = finalOfs.subList(0, popSize);

            // Adding a Generation
            population.add(finalOfs);

            // Mimimum Total Weight Chromosome
            for (int i = 0; i < popSize; i++) {
                int[][] arr = pop.get(i);
                int weight = 0;
                for (int k = 0; k < arr.length; k++) {
                    for (int j = 0; j < arr.length; j++) {
                        weight = weight + arr[k][j];
                    }
                }
                weights.put(i, weight);
            }
            Entry<Integer, Integer> min1 = Collections.min(weights.entrySet(),
                    new Comparator<Entry<Integer, Integer>>() {
                public int compare(Entry<Integer, Integer> entry1, Entry<Integer, Integer> entry2) {
                    return entry1.getValue().compareTo(entry2.getValue());
                }
            });
            minWeightC1 = min1.getValue();

            if (gen >= 2) {
                pop0 = population.get(gen - 2);
                for (int i = 0; i < popSize; i++) {
                    int[][] arr = pop0.get(i);
                    int weight = 0;
                    for (int k = 0; k < arr.length; k++) {
                        for (int j = 0; j < arr.length; j++) {
                            weight = weight + arr[k][j];
                        }
                    }
                    weights0.put(i, weight);
                }
            Entry<Integer, Integer> min0 = Collections.min(weights.entrySet(),
                        new Comparator<Entry<Integer, Integer>>() {
                    public int compare(Entry<Integer, Integer> entry1, Entry<Integer, Integer> entry2) {
                        return entry1.getValue().compareTo(entry2.getValue());
                    }
                });
            minWeightC0 = min0.getValue();

                pop2 = population.get(gen);
                for (int i = 0; i < popSize; i++) {
                    int[][] arr = pop0.get(i);
                    int weight = 0;
                    for (int k = 0; k < arr.length; k++) {
                        for (int j = 0; j < arr.length; j++) {
                            weight = weight + arr[k][j];
                        }
                    }
                    weights2.put(i, weight);
                }
                Entry<Integer, Integer> min2 = Collections.min(weights.entrySet(),
                        new Comparator<Entry<Integer, Integer>>() {
                    public int compare(Entry<Integer, Integer> entry1, Entry<Integer, Integer> entry2) {
                        return entry1.getValue().compareTo(entry2.getValue());
                    }
                });
            minWeightC2 = min2.getValue();

            }
            if (minWeightC0 == minWeightC1 && minWeightC1 == minWeightC2) {
                System.out.println("Break: Min weights of 3 consecutive generations gen, gen-1 and gen-2 " +gen+","+(gen-1)+","+(gen-2) +" respectively " +"are same i.e. "+ 
                minWeightC0 + " " + minWeightC1 + " " +minWeightC2 + ".\n Generation at which this break occurred is " + gen);
                break;
            }
            gen++;
        }
          System.out.println("Population of this output generation is as follows: ");
        
	    for (int[][] x : population.get(gen)) {
               for (int j = 0; j < x.length; j++) {
                System.out.print("{ ");
                // Loop through all elements of current row 
                for (int k = 0; k < x.length; k++) {
                    System.out.print(+x[j][k] + " ");
                }
                System.out.print("}");

            }
               System.out.print("\n");
        }
    }

    List<int[][]> applyCrossoverFunction(int[][] c1, int[][] c2) {

        List<int[][]> ofs = new ArrayList<>();
        int[][] of1 = new int[V][V];
        int[][] of2 = new int[V][V];
        int cp1, cp2;
        do {
            cp1 = rand.nextInt(V);
        } while (cp1 == 0);
        do {
            cp2 = rand.nextInt(V);
        } while (cp2 == 0);

        if (cp1 > cp2) {
            int temp = cp1;
            cp1 = cp2;
            cp2 = temp;
        }

        for (int i = 0; i < V; i++) {
            if (i <= cp1 || i > cp2) {
                of1[i] = c1[i];
                of2[i] = c2[i];
            } else {
                of1[i] = c2[i];
                of2[i] = c1[i];
            }
        }
        ofs.add(of1);
        ofs.add(of2);
        return ofs;

    }

    public static void main(String args[]) {
        GeneticAlgorithm ga = new GeneticAlgorithm();
        System.out.println("Output data:*************************************************************************************************************");
        ga.testGeneticAlgorithm();

    }

}
