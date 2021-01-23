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
class TwoThree {

    public Node23 root = null;

    public void insertOne(int item) {
        if (root == null) {
            Node23 root = new Node23();
            root.insert(item);
        } else {
            root.insert(item);
        }
    }
    /*
public static class TwoThreeKey {
        int dataOne;
        int dataTwo;
        TwoThreeKey() {
            this.dataOne = 0;
            this.dataTwo = 0;
        }
    }*/
    public class Node23 {

        //Node23 root = null;
        Integer dataOne, dataTwo;
        
        TwoThreeTree.TwoThreeKey keyList;
        Node23 left, mid, right;
        Node23 parent;

        Node23(Integer toAdd, Node23 prev) {
            parent = prev;
            dataOne = toAdd;
        }

        Node23() {
        this.dataOne = null;
        this.dataTwo=null;
            this.left = null;
            this.right = null;
            this.mid = null;
        }

        private void insert(Integer item) {
            
          int diff = this.dataOne.compareTo(item);

            //Node is a leaf
            if ((left == null) && (mid == null) && (right == null)) {
                if (this.dataTwo == null) {   //Leaf has only one data item
                    if (diff <= 0) {    //New item is larger than dataOne
                        this.dataTwo = item;
                    } else {            //New item is smaller than dataOne
                        this.dataTwo = dataOne;
                        this.dataOne = item;
                    }
                } else {                //Leaf has two items and must be split
                    splitLeaf(item);

                    if (parent != null) {
                        this.parent.pushUp(this);
                    }
                }

                return;
            }

            //Not a leaf, continue traversal
            if (diff > 0) {       //New item is smaller than dataOne and the smallest
                left.insert(item);
            } //New item is larger or equal to dataOne and this node
            else if (dataTwo == null) {      //is a 2-node
                right.insert(item);
            } else {              //This node is a 3-node
                diff = dataTwo - item;

                if (diff > 0) {   //New item is smaller than dataTwo
                    mid.insert(item);
                } else {          //New item is larger than dataTwo and the largest
                    right.insert(item);
                }
            }
        }

        private void splitLeaf(Integer item) {
            int diff = dataOne - item;

            if (diff > 0) {        //New item is smaller than dataOne and smallest
                left = new Node23(item, this);
                right = new Node23(dataTwo, this);
                dataTwo = null;
            } else {       //New item is larger than dataOne
                diff = dataTwo - item;

                if (diff > 0) {    //New item is smaller than dataTwo
                    left = new Node23(dataOne, this);
                    right = new Node23(dataTwo, this);
                    dataTwo = null;
                    dataOne = item;
                } else {              //New item is larger than dataTwo and largest
                    left = new Node23(dataOne, this);
                    right = new Node23(item, this);
                    dataOne = dataTwo;
                    dataTwo = null;
                }
            }
        }

        protected void pushUp(Node23 item) {
            if (this.dataTwo == null) {   //This node is a 2-node
                int diff = dataOne - item.dataOne;

                if (diff > 0) { //Node item.dataOne to add is smaller than this.dataOne
                    this.dataTwo = dataOne;
                    this.dataOne = item.dataOne;
                    left = item.left;
                    left.parent = this;
                    mid = item.right;
                    mid.parent = this;
                } else { //Node item.dataOne to add is larger or equal than this.dataOne
                    this.dataTwo = item.dataOne;
                    mid = item.left;
                    mid.parent = this;
                    right = item.right;
                    right.parent = this;
                }
            } else {              //This node is a 3-node and must be split
                splitThreeNode(item);

                if (parent != null) {
                    parent.pushUp(this);
                }
            }
        }

        private void splitThreeNode(Node23 item) {
            int diff = dataOne - item.dataOne;
            Node23 temp = null;

            if (diff > 0) {     //Item toAdd is smaller than dataOne
                left = item;
                left.parent = this;
                temp = new Node23(dataTwo, this);
                dataTwo = null;
                temp.left = mid;
                temp.left.parent = temp;
                temp.right = right;
                temp.right.parent = temp;
                right = temp;
                mid = null;

            } else {
                diff = dataTwo - item.dataOne;

                if (diff > 0) {     //Item to add is between dataOne and dataTwo
                    temp = new Node23(dataOne, this);
                    temp.left = left;
                    temp.left.parent = temp;
                    temp.right = item.left;
                    temp.right.parent = temp;
                    left = temp;
                    temp = new Node23(dataTwo, this);
                    temp.left = item.right;
                    temp.left.parent = temp;
                    temp.right = right;
                    temp.right.parent = temp;
                    right = temp;
                    dataOne = item.dataOne;
                    dataTwo = null;
                    mid = null;
                } else {          //Item to add is larger than dataTwo
                    right = item;
                    right.parent = this;
                    temp = new Node23(dataOne, this);
                    temp.left = left;
                    temp.left.parent = temp;
                    temp.right = mid;
                    temp.right.parent = temp;
                    left = temp;
                    dataOne = dataTwo;
                    dataTwo = null;
                    mid = null;
                }
            }
        }

        /*class DataItem {

            public double dData;

            public DataItem(double dd) {
                dData = dd;
            }

            public void displayItem() {
                System.out.print("/" + dData);
            }
        }*/

       
    }
    
	public static void main(String[] args) {
		TwoThree tree = new TwoThree();

		int[] array = { 9,23,45,1,5,14,55,24,13,11,8,19,4,31,35,56 };

		for (int i : array) {
			tree.insertOne(i);
		}
                
                //tree.displayTree();
	}

    /* public void displayTree() {
            recDisplayTree(root, 0, 0);
        }
public void displayNode() {
		for (int j = 0; j < numItems; j++)
			itemArray[j].displayItem();
		System.out.println("/");
	}
        private void recDisplayTree(Node23 thisNode, int level, int childNumber) {
            System.out.print("level=" + level + " child=" + childNumber + " ");
            thisNode.displayNode();

            int numItems = thisNode.getNumItems();
            for (int j = 0; j < numItems + 1; j++) {
                Node nextNode = thisNode.getChild(j);
                if (nextNode != null) {
                    recDisplayTree(nextNode, level + 1, j);
                } else {
                    return;
                }
            }
        }*/
}
