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
public class testing {

    private Node3 root = new Node3();

	public int find(double key) {
		Node3 curNode = root;
		int childNumber;
		while (true) {
			if ((childNumber = curNode.findItem(key)) != -1)
				return childNumber;
			else if (curNode.isLeaf())
				return -1;
			else
				curNode = getNextChild(curNode, key);
		}
	}
	public Node3 getNextChild(Node3 theNode, double theValue) {

		int j;

		int numItems = theNode.getNumItems();
		for (j = 0; j < numItems; j++) {
			if (theValue < theNode.getItem(j).dData)
				return theNode.getChild(j);
		} // end for
		return theNode.getChild(j);
	}

	public void insert(int dValue)
        {
            Node3 curNode = root;
	    DataItem3 tempItem = new DataItem3(dValue);
                while (true) {
			if (curNode.isFull()) {
				split(curNode);
				curNode = curNode.getParent();
                                curNode = getNextChild(curNode, dValue);
			}
                        else if (curNode.isLeaf())
				break;
                            else
				curNode = getNextChild(curNode, dValue);
		}
                curNode.insertItem(tempItem);
		System.out.println("Inserted  " + dValue);
	}

	public void split(Node3 thisNode) {
		DataItem3 itemB, itemC;
		Node3 parent, child2, child3;
		int itemIndex;

		itemC = thisNode.removeItem();
		itemB = thisNode.removeItem();
		child2 = thisNode.disconnectChild(2);
		child3 = thisNode.disconnectChild(3);

		Node3 newRight = new Node3();

		if (thisNode == root) {
			root = new Node3();
			parent = root;
			root.connectChild(0, thisNode);
		} else
			parent = thisNode.getParent();

		itemIndex = parent.insertItem(itemB);
		int n = parent.getNumItems();

		for (int j = n - 1; j > itemIndex; j--) {
			Node3 temp = parent.disconnectChild(j);
			parent.connectChild(j + 1, temp);
		}

		parent.connectChild(itemIndex + 1, newRight);

		newRight.insertItem(itemC);
		newRight.connectChild(0, child2);
		newRight.connectChild(1, child3);
	}


	public void displayTree() {
		recDisplayTree(root, 0, 0);
	}

	
	private void recDisplayTree(Node3 thisNode, int level, int childNumber) {
		System.out.print("level=" + level + " child=" + childNumber + " ");
		thisNode.displayNode();

		int numItems = thisNode.getNumItems();
		for (int j = 0; j < numItems + 1; j++) {
			Node3 nextNode = thisNode.getChild(j);
			if (nextNode != null)
				recDisplayTree(nextNode, level + 1, j);
			else
				return;
		}
	}

	public static void main(String[] args) {
		testing tree = new testing();

		int[] array = { 9,23,45,1,5,14,55,24,13,11,8,19,4,31,35,56 };

		for (int i : array) {
			tree.insert(i);
		}
                
                tree.displayTree();
	}

}

class Node3 {
	private static final int ORDER = 3;
	private int numItems;
	private Node3 parent;
	private Node3 childArray[] = new Node3[ORDER];
	private DataItem3 itemArray[] = new DataItem3[ORDER - 1];
        
         Node3(DataItem3 toAdd, Node3 prev) {
            parent = prev;
            itemArray[0] = toAdd;
        }
         Node3(){
             
         }

	public void connectChild(int childNum, Node3 child) {
		childArray[childNum] = child;
		if (child != null)
			
                    child.parent = this;
	}

	public Node3 disconnectChild(int childNum) {
		Node3 tempNode = childArray[childNum];
		childArray[childNum] = null;
		return tempNode;
	}

	public Node3 getChild(int childNum) {
		return childArray[childNum];
	}

	public Node3 getParent() {
		return parent;
	}

	public boolean isLeaf() {
		return (childArray[0] == null) ? true : false;
	}

	public int getNumItems() {
		return numItems;
	}

	public DataItem3 getItem(int index) // get DataItem at index
	{
		return itemArray[index];
	}

	public boolean isFull() {
		return (numItems == ORDER - 1) ? true : false;
	}

	public int findItem(double key)

	{
		for (int j = 0; j < ORDER - 1; j++) {
			if (itemArray[j] == null)
				break;
			else if (itemArray[j].dData == key)
				return j;
		}
		return -1;
	}

	public int insertItem(DataItem3 newItem) {
                numItems++;
		double newKey = newItem.dData;
                for (int j = ORDER - 2; j >= 0; j--) {
			if (itemArray[j] == null)
				continue;
			else {
				double itsKey = itemArray[j].dData;
				if (newKey < itsKey)
					itemArray[j + 1] = itemArray[j];
				else {
					itemArray[j + 1] = newItem;
					return j + 1;
				}
			}
		}
		itemArray[0] = newItem;
		return 0;
	}

	public DataItem3 removeItem() {
		DataItem3 temp = itemArray[numItems - 1];
		itemArray[numItems - 1] = null;
		numItems--;
		return temp;
	}

	public void displayNode() {
		for (int j = 0; j < numItems; j++)
			itemArray[j].displayItem();
		System.out.println("/");
	}

}

class DataItem3 {
	public double dData;
        public DataItem3(double dd) {
		dData = dd;
	}
        public void displayItem() {
         System.out.print("/" + dData);
	}
}


