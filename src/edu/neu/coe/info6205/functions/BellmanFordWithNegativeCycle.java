/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.functions;

import edu.neu.coe.info6205.functions.Graph12.Edge;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mahit
 */

class Graph12 {

    // A List of Lists to represent an adjacency list
    List<List<Integer>> adjList = null;
    List<Edge> edges;

    // Constructor
    Graph12(List<Edge> edges, int N) {
        this.edges = edges;

        adjList = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            adjList.add(new ArrayList<>());
        }

        // add edges to the undirected graph
        /*  for (Edge edge : edges) {
            int src = edge.from;
            int dest = edge.to;
            // Edge e=new Edge(dest,src,edge.weight);

            // System.out.println(src+"->"+dest);
            adjList.get(src).add(dest);
            adjList.get(dest).add(src);
        }*/
    }

    List<Edge> getAllEdges() {
        return this.edges;
    }

    Edge getEdge(char u, char v) {
        for (Edge edge : this.edges) {

            char src = edge.from;
            char dest = edge.to;
            if (src == u && dest == v) {
                return edge;
            } else if ((dest == u && src == v)) {
                //edge.from=u;
                //edge.to=v;
                return edge;
            }

        }
        return null;
    }

    static class Edge implements Comparable<Edge> {

        char from, to;
        int weight;

        Edge(char f, char t, int w) {
            from = f;
            to = t;
            weight = w;

        }

        int getWeight() {
            return this.weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }

        @Override
        public String toString() {
            return "[" + from + ", " + to + "]";
        }

    }
}


public class BellmanFordWithNegativeCycle {
    
    Map<Character, Character> parent1;

    public Map<Character, Integer> getShortestPath(Graph12 graph, Character sourceVertex, char[] vertices) {

        Map<Character, Integer> distance = new HashMap<>();
        Map<Character, Character> parent = new HashMap<>();
        this.parent1 = parent;
        //set distance of every vertex to be infinity initially
        for (int i = 0; i < vertices.length; i++) {
            distance.put(vertices[i], Integer.MAX_VALUE);
            parent.put(vertices[i], null);
        }

        //set distance of source vertex to be 0
        distance.put(sourceVertex, 0);

        int V = vertices.length;

        //relax edges repeatedly V - 1 times
        for (int i = 0; i < V - 1; i++) {
            boolean flag = false;
            for (Edge edge : graph.getAllEdges()) {
                Character u = edge.from;
                Character v = edge.to;
                //relax the edge
                //if we get better distance to v via u then use this distance
                //and set u as parent of v.
                if (distance.get(u) + edge.getWeight() < distance.get(v)) {
                    distance.put(v, distance.get(u) + edge.getWeight());
                    parent.put(v, u);
                    flag = true;
                }
            }
            if (flag == false) {
                break;
            }

        }

        //relax all edges again. If we still get lesser distance it means
        //there is negative weight cycle in the graph. Throw exception in that
        //case
        for (Edge edge : graph.getAllEdges()) {
            Character u = edge.from;
            Character v = edge.to;
            if (distance.get(u) + edge.getWeight() < distance.get(v)) {
                System.out.println("Negative Cycle Detected");
                return null;
            }
        }
        System.out.println("Shortest distances from source vertex");
        return distance;
    }

    public static void main(String args[]) {

        List<Edge> edges = Arrays.asList(new Edge('S', 'A', 3), new Edge('A', 'B', -5),
                new Edge('B', 'C', -3),
                new Edge('C', 'A', 6), new Edge('B', 'D', 1));

        char[] v = {'S', 'A', 'B', 'C', 'D'};
        // Set number of vertices in the graph
        final int N = 5;
        //Arrays.sort(edges);
        // create a graph from 

        Graph12 graph = new Graph12(edges, N);
        BellmanFordWithNegativeCycle shortestPath = new BellmanFordWithNegativeCycle();
        Character startVertex = 'S';
        Map<Character, Integer> distance = shortestPath.getShortestPath(graph, startVertex, v);
       // 
        System.out.println(distance);
        System.out.println("Parents of all the vertices");
        System.out.println(shortestPath.parent1);
    }


}
