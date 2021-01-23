/*
 *Shiva Mahitha Maddi
 * 001061161
 */
package edu.neu.coe.info6205.functions;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author mahit
 */
public class HuffmanCode_GeekForGeeks {
    	class MinNode {

		int frequency;
		char letter;

		MinNode left;
		MinNode right;
	}

	public void printCode(MinNode root, String s) {

		if (root.left == null && root.right == null && (Character.isLetter(root.letter)) || root.letter == ' ' || root.letter == ',') {
			System.out.println("Code for " + "'" + root.letter + "' is:" + s);
			return;
		}

		printCode(root.left, s + "0");
		printCode(root.right, s + "1");

	}

	public static void main(String args[]) {
		HuffmanCode_GeekForGeeks hfc = new HuffmanCode_GeekForGeeks();

		int size = 14;
		char[] letters = { ' ', 'a', 'y', 'd', 'n', 's', 'e', 'r', 'i', ',', 'h', 'T', 'u', 'w' };
		char[] frequency = { 8, 6, 6, 5, 5, 4, 3, 3, 2, 2, 1, 1, 1, 1 };
		
		System.out.println("String Provided: ");
		System.out.println("'There are sunny days, rainy days, and windy days'");
		System.out.println();

		PriorityQueue<MinNode> minQueue = new PriorityQueue<>(size, new Comparator<MinNode>() {
			@Override
			public int compare(MinNode x, MinNode y) {
				return x.frequency - y.frequency;
			}
		});

		for (int i = 0; i < size; i++) {

			MinNode node = hfc.new MinNode();
			node.frequency = frequency[i];
			node.letter = letters[i];

			node.left = null;
			node.right = null;

			minQueue.add(node);
		}

		MinNode root = null;

		while (minQueue.size() > 1) {

			MinNode n1 = minQueue.peek();
			minQueue.poll();

			MinNode n2 = minQueue.peek();
			minQueue.poll();

			MinNode n = hfc.new MinNode();
			n.frequency = n1.frequency + n2.frequency;
			n.letter = '-';

			n.left = n1;
			n.right = n2;

			root = n;

			minQueue.add(n);
		}
		hfc.printCode(root, "");
	}
}
