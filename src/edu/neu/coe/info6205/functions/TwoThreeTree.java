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
public class TwoThreeTree {

    private int size;
    private TreeNode root;
    private boolean successfulInsertion;
    private boolean successfulDeletion;
    private boolean split;
    private boolean underflow;
    private boolean first;
    private boolean singleNodeUnderflow;

    public static class TwoThreeNode {

        TwoThreeKey keyList;
        TwoThreeNode left;
        TwoThreeNode middle;
        TwoThreeNode right;

        TwoThreeNode() {
            this.keyList = null;
            this.left = null;
            this.right = null;
            this.middle = null;
        }
    }

    public static class TwoThreeKey {
        int s;
        int l;
        TwoThreeKey() {
            this.s = 0;
            this.l = 0;
        }
    }

    private class Node {

    }

    private class TreeNode extends Node {

        int keys[];
        Node children[];
        int degree;

        TreeNode() {
            keys = new int[2];
            children = new Node[3];
            degree = 0;
        }

        void print() {
            if (degree == 1) {
                System.out.print("(-,-)");
            } else if (degree == 2) {
                System.out.print("(" + keys[0] + ",-) ");
            } else {
                System.out.print("(" + keys[0] + "," + keys[1] + ") ");
            }
        }
    }

    private enum Nodes {
        LEFT, MIDDLE, RIGHT, DUMMY;
    }

    private class LeafNode extends Node {

        int key;

        LeafNode(int key) {
            this.key = key;
        }

        void print() {
            System.out.print(key + " ");
        }
    }

    private void insertKey(int key) {
        Node[] array = new Node[2];
        array = insert(key, root);

        if (array[1] == null) {
            root = (TreeNode) array[0];
        } else {
            TreeNode treeRoot = new TreeNode();
            treeRoot.children[0] = array[0];
            treeRoot.children[1] = array[1];
            updateTree(treeRoot);
            root = treeRoot;
        }
    }

    private Node[] insert(int key, Node n) {

        Node array[] = new Node[2];

        Node catchArray[] = new Node[2];

        TreeNode t = null;

        if (n instanceof TreeNode) {
            t = (TreeNode) n;
        }

        if (root == null && !first) {
            first = true;

            TreeNode newNode = new TreeNode();
            t = newNode;
            t.children[0] = insert(key, t.children[0])[0];
            updateTree(t);

            array[0] = t;
            array[1] = null;

        } else if (t != null && !(t.children[0] instanceof LeafNode)) {

            if (key < t.keys[0]) {

                catchArray = insert(key, t.children[0]);

                t.children[0] = catchArray[0];

                if (split) {

                    if (t.degree <= 2) {

                        split = false;

                        t.children[2] = t.children[1];
                        t.children[1] = catchArray[1];
                        updateTree(t);
                        array[0] = t;
                        array[1] = null;
                    } else if (t.degree > 2) {
                        TreeNode newNode = new TreeNode();
                        newNode.children[0] = t.children[1];
                        newNode.children[1] = t.children[2];
                        updateTree(newNode);
                        t.children[1] = catchArray[1];
                        t.children[2] = null;
                        updateTree(t);
                        array[0] = t;
                        array[1] = newNode;
                    }
                } else {
                    updateTree(t);
                    array[0] = t;
                    array[1] = null;
                }
            } else if (key >= t.keys[0] && (t.children[2] == null || key < t.keys[1])) {
                catchArray = insert(key, t.children[1]);
                t.children[1] = catchArray[0];
                if (split) {
                    if (t.degree <= 2) {
                        split = false;
                        t.children[2] = catchArray[1];
                        updateTree(t);
                        array[0] = t;
                        array[1] = null;
                    } else if (t.degree > 2) {
                        TreeNode newNode = new TreeNode();
                        newNode.children[0] = catchArray[1];
                        newNode.children[1] = t.children[2];
                        updateTree(newNode);
                        t.children[2] = null;
                        updateTree(t);
                        array[0] = t;
                        array[1] = newNode;
                    }
                } else {
                    updateTree(t);
                    array[0] = t;
                    array[1] = null;
                }
            } else if (key >= t.keys[1]) {
                catchArray = insert(key, t.children[2]);
                t.children[2] = catchArray[0];

                if (split) {
                    if (t.degree > 2) {
                        TreeNode newNode = new TreeNode();
                        newNode.children[0] = catchArray[0];
                        newNode.children[1] = catchArray[1];
                        updateTree(newNode);
                        t.children[2] = null;
                        updateTree(t);
                        array[0] = t;
                        array[1] = newNode;

                    }
                } else {
                    updateTree(t);
                    array[0] = t;
                    array[1] = null;
                }
            }
        } else if (t != null && t.children[0] instanceof LeafNode) {
            LeafNode l1 = null, l2 = null, l3 = null;
            if (t.children[0] != null && t.children[0] instanceof LeafNode) {
                l1 = (LeafNode) t.children[0];
            }
            if (t.children[1] != null && t.children[1] instanceof LeafNode) {
                l2 = (LeafNode) t.children[1];
            }
            if (t.children[2] != null && t.children[2] instanceof LeafNode) {
                l3 = (LeafNode) t.children[2];
            }

            if (t.degree <= 2) {
                if (t.degree == 1 && key > l1.key) {
                    LeafNode leaf = new LeafNode(key);
                    t.children[1] = leaf;
                } else if (t.degree == 1 && key < l1.key) {
                    LeafNode leaf = new LeafNode(key);
                    t.children[1] = l1;
                    t.children[0] = leaf;
                } else if (t.degree == 2 && key < l1.key) {
                    LeafNode leaf = new LeafNode(key);
                    t.children[2] = l2;
                    t.children[1] = l1;
                    t.children[0] = leaf;
                } else if (t.degree == 2 && key < l2.key && key > l1.key) {
                    LeafNode leaf = new LeafNode(key);
                    t.children[2] = l2;
                    t.children[1] = leaf;
                } else if (t.degree == 2) {
                    LeafNode leaf = new LeafNode(key);
                    t.children[2] = leaf;
                }

                updateTree(t);
                array[0] = t;
                array[1] = null;
            } else if (t.degree > 2) {
                split = true;
                if (key < l1.key) {
                    LeafNode leaf = new LeafNode(key);
                    TreeNode newNode = new TreeNode();
                    t.children[0] = leaf;
                    t.children[1] = l1;
                    t.children[2] = null;
                    updateTree(t);
                    newNode.children[0] = l2;
                    newNode.children[1] = l3;
                    updateTree(newNode);
                    array[0] = t;
                    array[1] = newNode;
                } else if (key >= l1.key && key < l2.key) {
                    LeafNode leaf = new LeafNode(key);
                    TreeNode newNode = new TreeNode();
                    t.children[1] = leaf;
                    t.children[2] = null;
                    updateTree(t);
                    newNode.children[0] = l2;
                    newNode.children[1] = l3;
                    updateTree(newNode);
                    array[0] = t;
                    array[1] = newNode;
                } else if (key >= l2.key && key < l3.key) {
                    LeafNode leaf = new LeafNode(key);
                    t.children[2] = null;
                    updateTree(t);
                    TreeNode newNode = new TreeNode();
                    newNode.children[0] = leaf;
                    newNode.children[1] = l3;
                    updateTree(newNode);
                    array[0] = t;
                    array[1] = newNode;
                } else if (key >= l3.key) {
                    LeafNode leaf = new LeafNode(key);
                    t.children[2] = null;
                    updateTree(t);
                    TreeNode newNode = new TreeNode();
                    newNode.children[0] = l3;
                    newNode.children[1] = leaf;
                    updateTree(newNode);
                    array[0] = t;
                    array[1] = newNode;
                }
            }
            successfulInsertion = true;
        } else if (n == null) {
            successfulInsertion = true;
            array[0] = new LeafNode(key);
            array[1] = null;
            return array;
        }
        return array;
    }

    private void updateTree(TreeNode t) {
        if (t != null) {
            if (t.children[2] != null && t.children[1] != null && t.children[0] != null) {
                t.degree = 3;
                t.keys[0] = getValueForKey(t, Nodes.LEFT);
                t.keys[1] = getValueForKey(t, Nodes.RIGHT);
            } else if (t.children[1] != null && t.children[0] != null) {
                t.degree = 2;
                t.keys[0] = getValueForKey(t, Nodes.LEFT);
                t.keys[1] = 0;
            } else if (t.children[0] != null) {
                t.degree = 1;
                t.keys[1] = t.keys[0] = 0;
            }
        }
    }

    private int getValueForKey(Node n, Nodes whichVal) {

        int key = -1;

        TreeNode t = null;
        LeafNode l = null;
        if (n instanceof TreeNode) {
            t = (TreeNode) n;
        } else {
            l = (LeafNode) n;
        }

        if (l != null) {
            key = l.key;
        }

        if (t != null) {

            if (null != whichVal) {
                switch (whichVal) {

                    case LEFT:
                        key = getValueForKey(t.children[1], Nodes.DUMMY);
                        break;

                    case RIGHT:
                        key = getValueForKey(t.children[2], Nodes.DUMMY);
                        break;

                    case DUMMY:
                        key = getValueForKey(t.children[0], Nodes.DUMMY);
                        break;
                    default:
                        break;
                }
            }
        }
        return key;
    }

    public boolean insert(int key) {
        boolean insert = false;
        split = false;

        if (!search(key)) {
            insertKey(key);
        }

        if (successfulInsertion) {
            size++;
            insert = successfulInsertion;
            successfulInsertion = false;
        }
        return insert;
    }

    public boolean search(int key) {
        return search(key, root);
    }

    private boolean search(int key, Node n) {
        boolean found = false;

        TreeNode t = null;
        LeafNode l = null;
        if (n instanceof TreeNode) {
            t = (TreeNode) n;
        } else {
            l = (LeafNode) n;
        }

        if (t != null) {
            if (t.degree == 1) {
                found = search(key, t.children[0]);
            } else if (t.degree == 2 && key < t.keys[0]) {

                found = search(key, t.children[0]);
            } else if (t.degree == 2 && key >= t.keys[0]) {

                found = search(key, t.children[1]);
            } else if (t.degree == 3 && key < t.keys[0]) {

                found = search(key, t.children[0]);
            } else if (t.degree == 3 && key >= t.keys[0] && key < t.keys[1]) {

                found = search(key, t.children[1]);
            } else if (t.degree == 3 && key >= t.keys[1]) {

                found = search(key, t.children[2]);
            }
        } else if (l != null && key == l.key) {
            return true;
        }

        return found;
    }

    public static void main(String[] args) {
        int[] array = {3, 7, 9, 23, 45, 1, 5, 14, 55, 24, 13, 11, 8, 19, 4, 31, 35, 56};
        TwoThreeTree tree = new TwoThreeTree();

        for (int i : array) {
            boolean success = tree.insert(i);
            if (success) {
                System.out.printf("Key %s inserted\n", i);
            } else {
                System.out.printf("Key %s already exists\n", i);
            }
        }
       boolean x=tree.search(100);
       if(x==true){
       System.out.printf("Found");
       }
       else {
           System.out.printf("Not found");
       }
    }
}
