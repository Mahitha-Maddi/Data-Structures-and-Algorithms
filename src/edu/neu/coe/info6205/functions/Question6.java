/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.functions;

/**
 *
 * @author mahit
 */


public class Question6 {
    public static class TreeNode {
     char value;
    TreeNode right;
    TreeNode left;

    TreeNode(char value) {
        this.value = value;
        right = null;
        left = null;
    }
}
    public static void main(String[] args) {
        // TODO code application logic 
        TreeNode root = new TreeNode('A');
        root.left = new TreeNode('B');
        root.right = new TreeNode('C');
        root.left.right = new TreeNode('D');
        root.left.right.left = new TreeNode('G');
        root.left.right.left.left = new TreeNode('I');
        root.right.right = new TreeNode('F');
        root.right.left = new TreeNode('E');
        root.right.left.right = new TreeNode('H');
        root.right.left.right.left = new TreeNode('J');
        root.right.left.right.right = new TreeNode('K');

        char value = getMaximum(root);
        System.out.println("The maximum element present in tree:" + value);
    }

    public static char getMaximum(TreeNode root) {
        char maximum = Character.MIN_VALUE;
        char rootValue;
        char leftChild, rightChild;
        if (root != null) {
            rootValue = root.value;
            leftChild = getMaximum(root.left);
            rightChild = getMaximum(root.right);
            if (leftChild > rightChild) {
                maximum = leftChild;
            } else {
                maximum = rightChild;
            }
            if (maximum < rootValue) {
                maximum = rootValue;
            }
        }
        return maximum;
    }

}
