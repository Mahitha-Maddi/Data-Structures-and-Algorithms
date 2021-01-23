/*
 * Shiva Mahitha Maddi
 * 001061161
 */
package edu.neu.coe.info6205.functions;

import java.util.Arrays;

/**
 *
 * @author mahit
 */
public class BinarySearchTree {

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

    static void search(int data) {
        searchTree(root, data);
    }

    static void searchTree(Node root, int data) {
        if (root == null) {
            System.out.println("Key not found in BST");
            return;
        } else if (root.data == data) {
            System.out.println("Key found at root: " + root.data);
            return;
        } else {
            if (root.data > data) {
                searchTree(root.left, data);
            } else {
                searchTree(root.right, data);
            }
        }
    }

    public Node searchMin(Node node) {
        if (node.left == null) {
            return node;
        }
        return searchMin(node.left);
    }

    public Node searchMax(Node node) {

        if (node.right == null) {
            return node;
        }
        return searchMax(node.right);
    }

    public void deleteMin() {
        deleteMin(root);
    }

    public void deleteMax() {
        deleteMax(root);
    }

    public Node deleteMin(Node root) {
        if (root == null) {
            return root;
        }
        return delete(root, searchMin(root).data);
    }

    public Node deleteMax(Node root) {
        if (root == null) {
            return root;
        }
        return delete(root, searchMax(root).data);
    }

    public Node delete(Node root, int data) {
        if (root == null) {
            return root;
        }
        if (data < root.data) {
            root.left = delete(root.left, data);
        } else if (data > root.data) {
            root.right = delete(root.right, data);
        } else {
            if (root.left == null && root.right == null) {
                root = null;

            } else if (root.left == null) {
                Node temp = root.right;
                return temp;
            } else if (root.right == null) {
                Node temp = root.left;
                return temp;
            } else {
                Node temp = searchMin(root.right);
                root.data = temp.data;
                root.right = delete(root.right, temp.data);
            }
        }
        return root;
    }

    void preOrder1(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder1(node.left);
            preOrder1(node.right);
        }
    }

    public static void main(String[] args) {
        System.out.println("Binary search tree: ");
        int[] array = {9, 23, 45, 1, 5, 14, 55, 24, 13, 11, 8, 19, 4, 31, 35, 56};
        System.out.println("Input array is " + Arrays.toString(array));
        BinarySearchTree tree = new BinarySearchTree();

        for (int i : array) {
            System.out.println("Inserting element: " +i);
            tree.insert(i);
        }
 System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("Searching element 185 :");
        search(185);
        System.out.println("Searching element 24 :");
        search(24);
        
 System.out.println("-----------------------------------------------------------------------------------");
        System.out.println(" Max element is 56");
        System.out.println(" Min element is 1");
        
 System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("Deleting Max element  :");
        tree.deleteMax();
        System.out.println("Pre-order data after deleting max element :");
        tree.preOrder1(root);
        
 System.out.println("\n-----------------------------------------------------------------------------------");
        System.out.println("Deleting Min element  :");
        tree.deleteMin();
        System.out.println("Pre-order data after deleting min element :");
        tree.preOrder1(root);

    }
}
