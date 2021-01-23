package edu.neu.coe.info6205.functions.newpackage;

import java.util.*;

class GetGraph {
	class Edge {
		Vertex to;
		int weight;

		Edge(Vertex to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		Vertex getTo() {
			return this.to;
		}

		void setTo(Vertex to) {
			this.to = to;
		}

		int getWeight() {
			return this.weight;
		}

		void setWeight(int weight) {
			this.weight = weight;

		}

		@Override
		public String toString() {
			return String.valueOf(this.to);
		}
	}

	class Vertex {
		int vertex;
		Set<Edge> edges;

		Vertex(int v) {
			this.vertex = v;
			edges = new HashSet<>();
		}

		void setVertex(int v) {
			this.vertex = v;
		}

		int getVertex() {
			return this.vertex;
		}

		List<Edge> getEdges() {
			return new ArrayList<>(this.edges);
		}

		void addEdge(Edge e) {
			this.edges.add(e);
		}

		@Override
		public String toString() {
			return String.valueOf(this.vertex);
		}
	}

	class Graph {
		private Set<Vertex> vertices;

		Graph() {
			this.vertices = new HashSet<>();
		}

		void addVertex(Vertex v) {
			this.vertices.add(v);
		}

		List<Vertex> getVertices() {
			return new ArrayList<Vertex>(vertices);
		}
	}

	int V;
	int[] originalVertices;
	int popSize;
	int[][] originalGraph;
	Random rand;
	int[] shortestPathTree;
	List<Integer> visitedVertices;

	GetGraph(int popSize, int[][] graph, int[] vertices, int V, int[] shortestPathTree) {
		this.popSize = popSize;
		this.originalGraph = graph;
		this.originalVertices = vertices;
		this.V = V;
		rand = new Random();
		this.shortestPathTree = shortestPathTree;
		this.visitedVertices = new ArrayList<>();
	}

	int[][] getGraph() {
		visitedVertices.clear();
		Graph graph = new Graph();
		int rootNode = rand.nextInt(V);
		Vertex rootV = new Vertex(rootNode);
		graph.addVertex(rootV);
		int n = 1;
		visitedVertices.add(rootNode);

		while (n < V) {
			int newNode = rand.nextInt(V);
			if (visitedVertices.indexOf(newNode) < 0) {
				Vertex newV = new Vertex(newNode);
				int w = rand.nextInt(9) + 2;
				newV.addEdge(new Edge(rootV, w));
				rootV.addEdge(new Edge(newV, w));
				graph.addVertex(newV);
				rootV = newV;
				visitedVertices.add(newNode);
				n += 1;
			}

		}

		int[][] finalGraph = new int[V][V];
		for (int j = 0; j < V; j++) {
			finalGraph[j][j] = 0;
		}
		for (Vertex xt : graph.getVertices()) {
			for (Edge eg : xt.getEdges()) {
				Integer xCord = Integer.valueOf(xt.toString());
				Integer yCord = Integer.valueOf(eg.toString());
				finalGraph[xCord][yCord] = eg.getWeight();
//				System.out.println(xt + ": " + eg + " " + eg.getWeight());
			}
//			System.out.println();
		}
		return finalGraph;
	}

}