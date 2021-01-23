/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.functions;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author mahit
 */
public class Huffman {
  
    
    
 
public static class Node{
		
		public int frequency;
		public char c;
		
		public Node left;
		public Node right;
		
	}
	
	public static class MyComparator implements Comparator<Node> { 
		
	    public int compare(Node x, Node y) 
	    { 
	        return x.frequency - y.frequency; 
	    } 
	    
	}
	
	public static void printCode(Node root, String binary) {
		//if leaf print
		if(root.left == null && root.right == null && (Character.isLetter(root.c) || root.c == ' ' || root.c == ',')) {
			System.out.println("'" + root.c + "'" + ":" + binary); 
			return;
		}
		//loops till end of the tree 
		printCode(root.left, binary + "0"); 
        printCode(root.right, binary + "1"); 
		
	}
	
	public static void main(String[] args) {
		String str = "'There are sunny days, rainy days, and windy days'";
		//number of unique charectors in the string
		int n = 14; 
	    char[] charArray = { ' ', 'a', 'y', 'd', 'n', 's', 'e', 'r', 'i', ',', 'h', 'T', 'u', 'w' };
	    int[] charfreq = { 8, 6, 6, 5, 5, 4, 3, 3, 2, 2, 1, 1, 1, 1 };
		/*
		 * charArray = str.toCharArray();
		 * 
		 * HashSet<Character> uniqueCharSet = new HashSet<Character>(); for(char each :
		 * charArray){ if(each != ' ') uniqueCharSet.add(each); } HashSet<Integer>
		 * uniqueCharFreq = new HashSet<Integer>(); for(char each : uniqueCharSet){
		 * uniqueCharFreq.add((int) str.chars().filter(num -> num == each).count()); }
		 * 
		 * 
		 * for (int i = 0; i < str.length(); i++){ char c = str.charAt(i);
		 * if(!Arrays.asList(charArray).contains(c) && c != ' ') { charArray[i] = c;
		 * charfreq[i] = (int) str.chars().filter(num -> num == c).count(); } }
		 */
	    
	    
        PriorityQueue<Node> q 
        = new PriorityQueue<Node>(n, new MyComparator());
        
        for (int i = 0; i < n; i++) { 
        	
            Node hn = new Node(); 
  
            hn.c = charArray[i]; 
            hn.frequency = charfreq[i]; 
  
            hn.left = null; 
            hn.right = null; 
  
            q.add(hn); 
        } 
        
        Node root = null; 
        while (q.size() > 1) {
        	Node x = q.peek(); 
            q.poll(); 
            Node y = q.peek(); 
            q.poll(); 
            
            Node f = new Node();
            f.frequency = x.frequency + y.frequency; 
            f.c = '-'; 
            f.left = x; 
            f.right = y; 
            root = f; 
            q.add(f); 
        }
        printCode(root, ""); 

	}
}
