package edu.neu.coe.info6205.functions.newpackage;


import java.util.*;

class Krusks {

	private static Map<Integer, Integer> PARENT;
	private static Map<Integer, Integer> RANKS; // to store the depths

	public static void initialize(int[] universe) {
		PARENT = new HashMap<>();
		RANKS = new HashMap<>();
		for (int x : universe) {
			PARENT.put(x, x);
			RANKS.put(x, 1);
		}
	}

	public static int FindSet(int item) {
		int parent = PARENT.get(item);
		if (parent == item)
			return item;
		else
			return FindSet(parent);
	}

	public static void Union(int setA, int setB) {
		int pA, pB;
		while ((pA = PARENT.get(setA)) != setA) {
			setA = pA;
		}
		while ((pB = PARENT.get(setB)) != setB) {
			setB = pB;
		}

		int rankFirst = RANKS.get(setA), rankSecond = RANKS.get(setB);
		if (rankFirst > rankSecond) {
			PARENT.put(setB, setA);
			updateRanksUpward(setB);
		} else if (rankSecond > rankFirst) {
			PARENT.put(setA, setB);
			updateRanksUpward(setA);
		} else {
			PARENT.put(setB, setA);
			updateRanksUpward(setB);
		}
	}

	public static void updateRanksUpward(int current) {
		int currentDepth = RANKS.get(current);
		int currentsParent = PARENT.get(current);
		int parentsDepth = RANKS.get(currentsParent);
		if (!(currentDepth < parentsDepth || currentsParent == current)) {
			RANKS.put(currentsParent, currentDepth + 1);
			updateRanksUpward(currentsParent);
		}
	}

	ArrayList<EDGE> generateMinSpanTree() {
		int[] vertices = new int[] { 0, 1, 2, 3, 4 };
		EDGE[] edges = new EDGE[7];
		edges[0] = new EDGE(0, 1, 2);
		edges[1] = new EDGE(0, 4, 3);
		edges[2] = new EDGE(2, 3, 5);
		edges[3] = new EDGE(3, 4, 6);
		edges[4] = new EDGE(2, 4, 8);
		edges[5] = new EDGE(0, 3, 9);
		edges[6] = new EDGE(1, 2, 10);

		ArrayList<EDGE> mst = new ArrayList<>();
		mst = Kruskal(vertices, edges);
		return mst;
	}

	public static ArrayList<EDGE> Kruskal(int[] vertices, EDGE[] edges) {

		ArrayList<EDGE> mst = new ArrayList<>();

		initialize(vertices);

		Arrays.sort(edges);

		for (EDGE edge : edges) {

			if (FindSet(edge.from) != FindSet(edge.to)) {

				mst.add(edge);

				Union(edge.from, edge.to);
			}
		}
		return mst;
	}
}
