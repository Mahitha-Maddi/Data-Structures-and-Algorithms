/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.functions;

import java.util.Arrays;

/**
 *
 * @author mahit
 */
public class BST_DFS {

    static Node root = null;

    static class Node {

        int data;
        Node left;
        Node right;

        Node(int data) {
            this.left = null;
            this.right = null;
            this.data = data;
        }

    }

    static void insert(int data) {
        root = insertRec(root, data);
    }

    static Node insertRec(Node root, int data) {

        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    void preOrder() {
        preOrder1(this.root);
    }

    void preOrder1(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        preOrder1(node.left);
        preOrder1(node.right);

    }

    void postOrder() {
        postOrder1(this.root);
    }

    void postOrder1(Node node) {
        if (node == null) {
            return;
        }
        postOrder1(node.left);
        postOrder1(node.right);

        System.out.print(node.data + " ");

    }

    void inOrder() {
        inOrder1(this.root);
    }

    void inOrder1(Node node) {
        if (node == null) {
            return;
        }
        inOrder1(node.left);

        System.out.print(node.data + " ");
        inOrder1(node.right);

    }

    public static void main(String[] args) {
        System.out.println("Binary search tree: ");
        int[] array = {42, 12, 53, 8, 16, 60, 2, 22, 57, 65,19};
        System.out.println("Input array is " + Arrays.toString(array));
        BST_DFS tree = new BST_DFS();

        for (int i : array) {
            System.out.println("Inserting element: " + i);
            tree.insert(i);
        }
        System.out.println("-----------------------------------------------------------------------------------");
        
            System.out.println("Pre-Order Traversal: " );
            tree.preOrder();
             System.out.println("\n-----------------------------------------------------------------------------------");
        
            System.out.println("In-Order Traversal: " );
            tree.inOrder();
             System.out.println("\n-----------------------------------------------------------------------------------");
        
            System.out.println("Post-Order Traversal: " );
            tree.postOrder();
            
    }
}
