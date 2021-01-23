package edu.neu.coe.info6205.functions.newpackage;

public class EDGE implements Comparable<EDGE> {
	int from, to;
	int weight;

	EDGE(int f, int t, int w) {
		from = f;
		to = t;
		weight = w;
	}

	@Override
	public int compareTo(EDGE o) {
		return weight < o.weight ? -1 : (weight > o.weight ? 1 : 0);
	}

	@Override
	public String toString() {
		return "[" + from + ", " + to + "]";
	}
}
