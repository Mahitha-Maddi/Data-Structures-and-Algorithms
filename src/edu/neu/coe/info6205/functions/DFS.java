/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author mahit
 */
public class DFS {
    static class Node {
		int data;
		boolean visited;
		List<Node> neighbours;

		Node(int data) {
			this.data = data;
			this.neighbours = new ArrayList<>();

		}

		public void addneighbours(Node neighbourNode) {
			this.neighbours.add(neighbourNode);
		}

		public List<Node> getNeighbours() {
			return neighbours;
		}

		public void setNeighbours(List<Node> neighbours) {
			this.neighbours = neighbours;
		}
	}

	// Recursive DFS
	public void dfs(Node node) {
		System.out.print(node.data + " ");
		List<Node> neighbours = node.getNeighbours();
		node.visited = true;
		for (int i = 0; i < neighbours.size(); i++) {
			Node n = neighbours.get(i);
			if (n != null && !n.visited) {
				dfs(n);
			}
		}
	}

	// Iterative DFS using stack
	public void dfsUsingStack(Node node) {
		Stack<Node> stack = new Stack<Node>();
		stack.add(node);
		while (!stack.isEmpty()) {
			Node element = stack.pop();
			if (!element.visited) {
				System.out.print(element.data + " ");
				element.visited = true;
			}

			List<Node> neighbours = element.getNeighbours();
			for (int i = 0; i < neighbours.size(); i++) {
				Node n = neighbours.get(i);
				if (n != null && !n.visited) {
					stack.add(n);
				}
			}

		}
	}

	public static void main(String arg[]) {

		Node node10 = new Node(1);
		Node node20 = new Node(2);
		Node node30 = new Node(3);
		Node node40 = new Node(4);
		Node node50 = new Node(5);
		Node node60 = new Node(6);
		Node node70 = new Node(7);
                Node node80 = new Node(8);
                Node node90 = new Node(9);

		node10.addneighbours(node40);
		node20.addneighbours(node30);
		node20.addneighbours(node40);
		node30.addneighbours(node20);
		node30.addneighbours(node70);
		node40.addneighbours(node10);
		node40.addneighbours(node20);
		node40.addneighbours(node80);
		node40.addneighbours(node70);
		node40.addneighbours(node60);
		node50.addneighbours(node60);
		node50.addneighbours(node70);
		node50.addneighbours(node90);
		node60.addneighbours(node40);
		node60.addneighbours(node50);
		node60.addneighbours(node70);
		node70.addneighbours(node60);
		node70.addneighbours(node50);
                node70.addneighbours(node40);
		node70.addneighbours(node30);
                node80.addneighbours(node40);
                node90.addneighbours(node50);

		DFS dfsExample = new DFS();

		System.out.println("The DFS traversal of the graph using stack ");
		dfsExample.dfsUsingStack(node10);

		System.out.println();

		// Resetting the visited flag for nodes
		node10.visited = false;
		node20.visited = false;
		node30.visited = false;
                node40.visited = false;
                node50.visited = false;
		node60.visited = false;
		node70.visited = false;
                node80.visited = false;
                node90.visited = false;

		System.out.println("The DFS traversal of the graph using recursion ");
		dfsExample.dfs(node10);
	}
}
