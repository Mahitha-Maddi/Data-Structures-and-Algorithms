/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.functions;

import edu.neu.coe.info6205.functions.Graph15.Edge1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mahit
 */

class Graph15 {

    // A List of Lists to represent an adjacency list
    List<List<Integer>> adjList = null;
    List<Edge1> edges;

    // Constructor
    Graph15(List<Edge1> edges, int N) {
        this.edges = edges;

        adjList = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            adjList.add(new ArrayList<>());
        }

        // add edges to the undirected graph
        for (Edge1 edge : edges) {
            int src = edge.from;
            int dest = edge.to;
            // Edge e=new Edge(dest,src,edge.weight);

            // System.out.println(src+"->"+dest);
            adjList.get(src).add(dest);
            adjList.get(dest).add(src);
        }

    }

    Edge1 getEdge(int u, int v) {
        for (Edge1 edge : this.edges) {

            int src = edge.from;
            int dest = edge.to;
            if (src == u && dest == v) {
                return edge;
            }
             else if((dest==u && src==v)){
                            //edge.from=u;
                            //edge.to=v;
                            return edge;
                        }

        }
        return null;
    }

    static class Edge1 implements Comparable<Edge1> {

        int from, to;
        int weight;

        Edge1(int f, int t, int w) {
            from = f;
            to = t;
            weight = w;

        }

        int getWeight() {
            return this.weight;
        }

        @Override
        public int compareTo(Edge1 o) {
            return this.weight - o.weight;
        }

        @Override
        public String toString() {
            return "[" + from + ", " + to + "]";
        }

    }}
public class DijkstraShortestPath 

{
    Map<Integer, Integer> parent1; 
    
    public Map<Integer,Integer> shortestPath(Graph15 graph, Integer sourceVertex,int v){

        //heap + map data structure
        BinaryMinHeap1<Integer> minHeap = new BinaryMinHeap1<>();

        //stores shortest distance from root to every vertex
        Map<Integer,Integer> distance = new HashMap<>();

        //stores parent of every vertex in shortest distance
        Map<Integer, Integer> parent = new HashMap<>();
       this.parent1=parent;
        //initialize all vertex with infinite distance from source vertex
          for (int i = 1; i <= v; i++) {
            minHeap.add(Integer.MAX_VALUE, i);
        }

          
       
        //set distance of source vertex to 0
        minHeap.decrease(sourceVertex, 0);

        //put it in map
        distance.put(sourceVertex, 0);

        //source vertex parent is null
        parent.put(sourceVertex, null);

        //iterate till heap is not empty
        while(!minHeap.empty()){
            //get the min value from heap node which has vertex and distance of that vertex from source vertex.
            BinaryMinHeap1<Integer>.Node heapNode = minHeap.extractMinNode();
            Integer current = heapNode.key;

            //update shortest distance of current vertex from source vertex
            distance.put(current, heapNode.weight);

            //iterate through all edges of current vertex
             for (int adjVertex : graph.adjList.get(current)) {
                // Integer adjacent = getVertexForEdge(current, edge);
                //check if adjacent vertex exist in heap + map and weight attached with this vertex is greater than this edge weight

                Edge1 edge1 = graph.getEdge(current, adjVertex);
                //get the adjacent vertex
                //Integer adjacent = getVertexForEdge(current, edge);

                //if heap does not contain adjacent vertex means adjacent vertex already has shortest distance from source vertex
                if(!minHeap.containsData(adjVertex)){
                    continue;
                }

                //add distance of current vertex to edge weight to get distance of adjacent vertex from source vertex
                //when it goes through current vertex
                int newDistance = distance.get(current) + edge1.getWeight();

                //see if this above calculated distance is less than current distance stored for adjacent vertex from source vertex
                if(minHeap.getWeight(adjVertex) > newDistance) {
                    minHeap.decrease(adjVertex, newDistance);
                    parent.put(adjVertex, current);
                }
            }
        }
        return distance;
    }

   
      class BinaryMinHeap1<T> {

        private List<Node> allNodes;
        private Map<T, Integer> nodePosition;

        BinaryMinHeap1() {
            allNodes = new ArrayList<>();
            nodePosition = new HashMap<>();
        }

        public class Node {

            int weight;
            T key;
        }

        /**
         * Checks where the key exists in heap or not
         */
        public boolean containsData(T key) {
            return nodePosition.containsKey(key);
        }

        /**
         * Add key and its weight to they heap
         */
        public void add(int weight, T key) {
            Node node = new Node();
            node.weight = weight;
            node.key = key;
            allNodes.add(node);
            int size = allNodes.size();
            int current = size - 1;
            int parentIndex = (current - 1) / 2;
            nodePosition.put(node.key, current);

            while (parentIndex >= 0) {
                Node parentNode = allNodes.get(parentIndex);
                Node currentNode = allNodes.get(current);
                if (parentNode.weight > currentNode.weight) {
                    swap(parentNode, currentNode);
                    updatePositionMap(parentNode.key, currentNode.key, parentIndex, current);
                    current = parentIndex;
                    parentIndex = (parentIndex - 1) / 2;
                } else {
                    break;
                }
            }
        }

        /**
         * Get the heap min without extracting the key
         */
        public T min() {
            return allNodes.get(0).key;
        }

        /**
         * Checks with heap is empty or not
         */
        public boolean empty() {
            return allNodes.size() == 0;
        }

        /**
         * Decreases the weight of given key to newWeight
         */
        public void decrease(T data, int newWeight) {
            Integer position = nodePosition.get(data);
            try {
                allNodes.get(position).weight = newWeight;
                int parent = (position - 1) / 2;
                while (parent >= 0) {
                    if (allNodes.get(parent).weight > allNodes.get(position).weight) {
                        swap(allNodes.get(parent), allNodes.get(position));
                        updatePositionMap(allNodes.get(parent).key, allNodes.get(position).key, parent, position);
                        position = parent;
                        parent = (parent - 1) / 2;
                    } else {
                        break;
                    }
                }
            } catch (Exception e) {

            }
        }

        /**
         * Get the weight of given key
         */
        public Integer getWeight(T key) {
            Integer position = nodePosition.get(key);
            if (position == null) {
                return null;
            } else {
                return allNodes.get(position).weight;
            }
        }

        /**
         * Returns the min node of the heap
         */
        public Node extractMinNode() {
            int size = allNodes.size() - 1;
            Node minNode = new Node();
            minNode.key = allNodes.get(0).key;
            minNode.weight = allNodes.get(0).weight;

            int lastNodeWeight = allNodes.get(size).weight;
            allNodes.get(0).weight = lastNodeWeight;
            allNodes.get(0).key = allNodes.get(size).key;
            nodePosition.remove(minNode.key);
            nodePosition.remove(allNodes.get(0));
            nodePosition.put(allNodes.get(0).key, 0);
            allNodes.remove(size);

            int currentIndex = 0;
            size--;
            while (true) {
                int left = 2 * currentIndex + 1;
                int right = 2 * currentIndex + 2;
                if (left > size) {
                    break;
                }
                if (right > size) {
                    right = left;
                }
                int smallerIndex = allNodes.get(left).weight <= allNodes.get(right).weight ? left : right;
                if (allNodes.get(currentIndex).weight > allNodes.get(smallerIndex).weight) {
                    swap(allNodes.get(currentIndex), allNodes.get(smallerIndex));
                    updatePositionMap(allNodes.get(currentIndex).key, allNodes.get(smallerIndex).key, currentIndex, smallerIndex);
                    currentIndex = smallerIndex;
                } else {
                    break;
                }
            }
            return minNode;
        }

        /**
         * Extract min value key from the heap
         */
        public T extractMin() {
            Node node = extractMinNode();
            return node.key;
        }

        private void printPositionMap() {
            System.out.println(nodePosition);
        }

        private void swap(Node node1, Node node2) {
            int weight = node1.weight;
            T data = node1.key;

            node1.key = node2.key;
            node1.weight = node2.weight;

            node2.key = data;
            node2.weight = weight;
        }

        private void updatePositionMap(T data1, T data2, int pos1, int pos2) {
            nodePosition.remove(data1);
            nodePosition.remove(data2);
            nodePosition.put(data1, pos1);
            nodePosition.put(data2, pos2);
        }

        public void printHeap() {
            for (Node n : allNodes) {
                System.out.println(n.weight + " " + n.key);
            }
        }
      }
    public static void main(String args[]){
      List<Edge1> edges = Arrays.asList( new Edge1(1,4,3),new Edge1(4,7,2),new Edge1(7,3,4),
 new Edge1(2,4,4),new Edge1(5,7,2),
new Edge1(2,3,5),new Edge1(4,6,5),
 new Edge1(6,7,1),new Edge1(5,6,2));

        // Set number of vertices in the graph
        final int N = 7;
        //Arrays.sort(edges);
        // create a graph from 

        Graph15 graph = new Graph15(edges, N);
        
        DijkstraShortestPath dsp = new DijkstraShortestPath();
        Integer sourceVertex = 1;
        Map<Integer,Integer> distance = dsp.shortestPath(graph, sourceVertex,N);
        
        System.out.println("Distance of all the vertices from the vertex '0': ");
        System.out.println(distance);
        System.out.println("\nParents of vertices: ");
        System.out.println(dsp.parent1);
    }
    
}
