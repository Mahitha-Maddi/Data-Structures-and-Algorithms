/*
 * Shiva Mahitha Maddi
 * 001061161
 */
package edu.neu.coe.info6205.functions;

/**
 *
 * @author mahit
 */
public class MaxElement {
    private static class Node{
	    int data; 
	    Node left; 
	    Node right;
	}
	
	static Node insert(Node node, int data) 
	{ 
	    /* 1. If the tree is empty, return a new,      
	    single node */
	    if (node == null) {
	    	Node n = new Node();
	    	n.data = data;
	    	n.left = null;
	    	n.right = null;
	    	return n;
	    }
	    else 
	    { 
	        /* 2. Otherwise, recur down the tree */
	        if (data <= node.data) 
	            node.left = insert(node.left, data); 
	        else
	            node.right = insert(node.right, data); 
	  
	        /* return the (unchanged) node pointer */
	        return node; 
	    } 
	}
	
	static int maxValue(Node node) 
	{  
	    /* loop down to find the rightmost leaf */
	    Node current = node; 
	    while (current.right != null)  
	        current = current.right; 
	      
	    return (current.data); 
	} 
	
	static char[] convert(String s) {
            
            
           char[] arr=s.toCharArray();
		//int[] arr = new int[s.length()];
		/*for (int i = 0; i < s.length(); i++){
		    char ch = s.charAt(i);
		    int n = ch - 'a';
		    arr[i] = n;
		}*/
		
		return arr;
	}

	public static void main(String[] args) {
		char[] input = convert("ABCDEFGHIJK");
		Node root = null; 
	    root = insert(root, input[0]); 
	    for (int i = 1; i < input.length; i++) {
	    	insert(root, input[i]); 
		}
	    char max =  (char)maxValue(root);
            
            System.out.println("Input string is ABCDEFGHIJK"); 
            System.out.println("Maximum value in BST is " +max); 
	    //System.out.println("Maximum value in BST is " + String.valueOf((char)(max + 'A' - 1))); 
	}
}
