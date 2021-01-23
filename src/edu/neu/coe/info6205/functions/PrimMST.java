/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mahit
 *
 */
class Graph10 {

    // A List of Lists to represent an adjacency list
    List<List<Integer>> adjList = null;
    List<Edge> edges;

    // Constructor
    Graph10(List<Edge> edges, int N) {
        this.edges = edges;

        adjList = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            adjList.add(new ArrayList<>());
        }

        // add edges to the undirected graph
        for (Edge edge : edges) {
            int src = edge.from;
            int dest = edge.to;
            // Edge e=new Edge(dest,src,edge.weight);

            // System.out.println(src+"->"+dest);
            adjList.get(src).add(dest);
            adjList.get(dest).add(src);
        }

    }

    Edge getEdge(int u, int v) {
        for (Edge edge : this.edges) {

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

    static class Edge implements Comparable<Edge> {

        int from, to;
        int weight;

        Edge(int f, int t, int w) {
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

    public List<Edge> primMST(Graph10 graph, int v) {

        //binary heap + map data structure
        BinaryMinHeap<Integer> minHeap = new BinaryMinHeap<>();

        //map of vertex to edge which gave minimum weight to this vertex.
        Map<Integer, Edge> vertexToEdge = new HashMap<>();

        //stores final result
        List<Edge> result = new ArrayList<>();

        //insert all vertices with infinite value initially.
        for (int i = 1; i <= v; i++) {
            minHeap.add(Integer.MAX_VALUE, i);
        }

        //start from any random vertex
        Integer startVertex = 1;
        //for the start vertex decrease the value in heap + map to 0
        minHeap.decrease(startVertex, 0);

        //iterate till heap + map has elements in it
        while (!minHeap.empty()) {
            //extract min value vertex from heap + map
            int current = minHeap.extractMin();

            //get the corresponding edge for this vertex if present and add it to final result.
            //This edge wont be present for first vertex.
            Edge spanningTreeEdge = vertexToEdge.get(current);
            if (spanningTreeEdge != null) {
                result.add(spanningTreeEdge);
            }

            //iterate through all the adjacent vertices
            for (int adjVertex : graph.adjList.get(current)) {
                // Integer adjacent = getVertexForEdge(current, edge);
                //check if adjacent vertex exist in heap + map and weight attached with this vertex is greater than this edge weight

                Edge edge1 = graph.getEdge(current, adjVertex);
                try {
                    if (minHeap.containsData(adjVertex) && minHeap.getWeight(adjVertex) > edge1.getWeight()) {
                        //decrease the value of adjacent vertex to this edge weight.
                        minHeap.decrease(adjVertex, edge1.getWeight());
                        //add vertex->edge mapping in the graph.
                        vertexToEdge.put(adjVertex, edge1);
                    }
                } catch (Exception e) {

                }
            }
        }
        return result;
    }

    /* private Integer getVertexForEdge(Integer v, Edge e){
        return e.getVertex1().equals(v) ? e.getVertex2() : e.getVertex1();
    }
     */
    static class BinaryMinHeap<T> {

        private List<Node> allNodes;
        private Map<T, Integer> nodePosition;

        BinaryMinHeap() {
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

    public static void main(String args[]) {
        List<Edge> edges = Arrays.asList(new Edge(3,5,21),new Edge(4,6,32), new Edge(5,6,37),
  new Edge(3,4,45), new Edge(2,3,47),new Edge(1,2,53),
 new Edge(1,6,55),new Edge(4,5,56),new Edge(3,6,68),
new Edge(2,6,70));

        // Set number of vertices in the graph
        final int N = 6;
        //Arrays.sort(edges);
        // create a graph from 

        Graph10 graph = new Graph10(edges, N);

        // PrimMST prims = new PrimMST();
        Collection<Edge> edges5 = graph.primMST(graph, N);
        System.out.println("Edges included in the MST using Prim's algorithm: ");
        for (Edge edge : edges5) {
            System.out.println(edge);
        }
    }

}
