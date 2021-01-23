/*
 * Shiva Mahitha Maddi
 * 001061161
 */
package edu.neu.coe.info6205.functions;

/**
 *
 * @author mahit
 */
public class TestTwoThreeTree {

    private static Node1 root = null;

    public int find(double key) {
        Node1 curNode = root;
        int childNumber;
        while (true) {
            if ((childNumber = curNode.findItem(key)) != -1) {
                return childNumber;
            } else if (curNode.isLeaf()) {
                return -1;
            } else {
                curNode = getNextChild(curNode, key);
            }
        }
    }

    public Node1 getNextChild(Node1 theNode, double theValue) {

        int j;

        int numItems = theNode.getNumItems();
        for (j = 0; j < 2; j++) {
            if (theNode.getItem(j)!=null && theValue < theNode.getItem(j).dData) {
                return theNode.getChild(j);
            }
        } // end for
        return theNode.getChild(j);
    }

    public void displayTree() {
        recDisplayTree(this.root, 0, 0);
    }

    private void recDisplayTree(Node1 thisNode, int level, int childNumber) {
        System.out.print("level=" + level + " child=" + childNumber + " ");
        thisNode.displayNode();

        //int numItems = thisNode.getNumItems();
        for (int j = 0; j < 3; j++) {
            Node1 nextNode = thisNode.getChild(j);
            if (nextNode != null) {
                recDisplayTree(nextNode, level + 1, j);
            } 
            /*se {
                return;
            }*/
        }
    }

    public void insert1(int item) {
        if (root == null) {
            root = new Node1();

            DataItem5 tempItem = new DataItem5(item);
            root.setItemArray(0, tempItem);
        } else {
            root.insert(item);
        }
    }

    public static void main(String[] args) {
        TestTwoThreeTree tree = new TestTwoThreeTree();
        System.out.print("2-3 Tree: ");

        int[] array = {9, 23, 45, 1, 5, 14, 55, 24, 13, 11, 8, 19, 4, 31, 35, 56};

        for (int i : array) {
            
System.out.print("\nInserted element: "+i);
            tree.insert1(i);
        }
System.out.print("\n-----------------------------------------------------\n");

System.out.print("Printing elements: \n");
        tree.displayTree();
        
System.out.print("\n-----------------------------------------------------\n");

System.out.print("Searching element 5: \n");
if( tree.find(5)>-1){
    System.out.print("Found element \n");
}
else{
    
    System.out.print("Not found element \n");
}
    
    System.out.print("Searching element 108: \n");
if( tree.find(108)>-1){
    System.out.print("Found element \n");
}
else{
    
    System.out.print("Not found element \n");
}
    }

}

class Node1 {

    private static final int ORDER = 3;
    private int numItems;
    private Node1 parent;
    private Node1 childArray[] = new Node1[ORDER + 1];
    private DataItem5 itemArray[] = new DataItem5[ORDER];

    Node1(DataItem5 toAdd, Node1 prev) {
        parent = prev;
        this.setItemArray(0, toAdd);
    }

    Node1() {

    }

    void insert(int dValue) {

        DataItem5 tempItem = new DataItem5(dValue);

        int diff = this.getItem(0).dData - dValue;
        if (this.isLeaf()) {
            if (this.getItem(1) == null) {   //Leaf has only one data item
                if (diff <= 0) {    //New item is larger than dataOne
                    this.setItemArray(1, tempItem);
                } else {            //New item is smaller than dataOne
                    this.setItemArray(1, this.getItem(0));
                    this.setItemArray(0, tempItem);
                    // this.setNumItems(numItems++);
                }
            } else {                //Leaf has two items and must be split
                this.splitLeaf(dValue);

                if (this.getParent() != null) {
                    this.getParent().pushUp(this);
                }
            }

            return;
        }

        //Not a leaf, continue traversal
        if (diff > 0) {       //New item is smaller than dataOne and the smallest
            this.getLeft().insert(dValue);
        } //New item is larger or equal to dataOne and this node
        else if (this.getItem(1) == null) {      //is a 2-node
            this.getRight().insert(dValue);
        } else {              //This node is a 3-node

            diff = this.getItem(1).dData - dValue;

            if (diff > 0) {   //New item is smaller than dataTwo
                this.getMid().insert(dValue);
            } else {          //New item is larger than dataTwo and the largest
                this.getRight().insert(dValue);
            }
        }
    }

    private void splitLeaf(int dValue) {
        DataItem5 tempItem = new DataItem5(dValue);

        int diff = this.getItem(0).dData - dValue;
        if (diff > 0) {        //New item is smaller than dataOne and smallest

            this.connectChild(0, new Node1(tempItem, this));
            this.connectChild(2, new Node1(this.getItem(1), this));
            this.setItemArray(1, null);
        } else {       //New item is larger than dataOne
            diff = this.getItem(1).dData - dValue;

            if (diff > 0) {    //New item is smaller than dataTwo
                this.connectChild(0, new Node1(this.getItem(0), this));
                this.connectChild(2, new Node1(this.getItem(1), this));
                this.setItemArray(1, null);
                this.setItemArray(0, tempItem);
            } else {              //New item is larger than dataTwo and largest
                this.connectChild(0, new Node1(this.getItem(0), this));
                this.connectChild(2, new Node1(tempItem, this));
                this.setItemArray(0, this.getItem(1));
                this.setItemArray(1, null);
            }
        }
    }

    protected void pushUp(Node1 item) {
        if (this.getItem(1) == null) {   //This node is a 2-node
            int diff = this.getItem(0).dData - item.getItem(0).dData;

            if (diff > 0) { //Node item.dataOne to add is smaller than this.dataOne
                this.setItemArray(1, this.getItem(0));
                this.setItemArray(0, item.getItem(0));
                this.connectChild(0, item.getChild(0));
                this.getLeft().setParent(this);
                this.connectChild(1, item.getChild(2));
                this.getMid().setParent(this);
            } else { //Node item.dataOne to add is larger or equal than this.dataOne
                this.setItemArray(1, item.getItem(0));
                this.connectChild(1, item.getChild(0));
                this.getMid().setParent(this);
                this.connectChild(2, item.getChild(2));
                this.getRight().setParent(this);
            }
        } else {              //This node is a 3-node and must be split
            splitThreeNode(item);

            if (this.parent != null) {
                this.parent.pushUp(this);
            }
        }
    }

    private void splitThreeNode(Node1 item) {
        int diff = this.getItem(0).dData - item.getItem(0).dData;
        Node1 temp = null;

        if (diff > 0) {     //Item toAdd is smaller than dataOne
            this.connectChild(0, item);
            this.getLeft().setParent(this);
            temp = new Node1(this.getItem(1), this);
            this.setItemArray(1, null);
            temp.connectChild(0, this.getMid());
            temp.getLeft().setParent(temp);
            temp.connectChild(2, this.getRight());
            temp.getRight().setParent(temp);
            temp.connectChild(2, temp);
            temp.connectChild(1, null);
        } else {
            diff = this.getItem(1).dData - item.getItem(0).dData;
            if (diff > 0) {     //Item to add is between dataOne and dataTwo
                temp = new Node1(this.getItem(0), this);
                temp.connectChild(0, this.getLeft());
                temp.getLeft().setParent(temp);
                temp.connectChild(2, item.getLeft());
                temp.getRight().setParent(temp);
                this.connectChild(0, temp);
                temp = new Node1(this.getItem(1), this);
                temp.connectChild(0, item.getRight());
                temp.getLeft().setParent(temp);
                temp.connectChild(2, this.getRight());
                temp.getRight().setParent(temp);
                this.connectChild(2, temp);
                this.setItemArray(0, item.getItem(0));
                this.setItemArray(1, null);
                this.connectChild(1, null);
            } else {          //Item to add is larger than dataTwo
                this.connectChild(2, item);
                this.getRight().setParent(this);
                temp = new Node1(this.getItem(0), this);
                temp.connectChild(0, this.getLeft());
                temp.getLeft().setParent(temp);
                temp.connectChild(2, this.getMid());
                temp.getRight().setParent(temp);
                this.connectChild(0, temp);
                this.setItemArray(0, this.getItem(1));
                this.setItemArray(1, null);
                this.connectChild(1, null);
            }
        }
    }

    public void connectChild(int childNum, Node1 child) {
        childArray[childNum] = child;
        if (child != null) {
            child.parent = this;
        }
    }

    public Node1 disconnectChild(int childNum) {
        Node1 tempNode = childArray[childNum];
        childArray[childNum] = null;
        return tempNode;
    }

    public Node1 getChild(int childNum) {
        return childArray[childNum];
    }

    public Node1 getParent() {
        return parent;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    public void setParent(Node1 parent) {
        this.parent = parent;
    }

    public boolean isLeaf() {
        return (childArray[0] == null) ? true : false;
    }

    public int getNumItems() {
        return numItems;
    }

    public DataItem5 getItem(int index) // get DataItem at index
    {
        return itemArray[index];
    }

    public boolean isFull() {
        return (numItems == ORDER - 1) ? true : false;
    }

    public Node1[] getChildArray() {
        return childArray;
    }

    public Node1 getLeft() {
        return childArray[0];
    }

    public Node1 getRight() {
        return childArray[2];
    }

    public Node1 getMid() {
        return childArray[1];
    }

    public void setChildArray(Node1[] childArray) {
        this.childArray = childArray;
    }

    public DataItem5[] getItemArray() {
        return itemArray;
    }

    public void setItemArray(int index, DataItem5 item) {
        this.itemArray[index] = item;
    }

    public int findItem(double key) {
        for (int j = 0; j < ORDER - 1; j++) {
            if (itemArray[j] == null) {
                break;
            } else if (itemArray[j].dData == key) {
                return j;
            }
        }
        return -1;
    }

    public void displayNode() {
        // for (int j = 0; j < 2; j++) {
        if (this.itemArray[0] != null) {
            this.itemArray[0].displayItem();
        }
        if (this.itemArray[1] != null) {
            this.itemArray[1].displayItem();
        }

        // }
        System.out.println("/");
    }

}

class DataItem5 {

    public int dData;

    public DataItem5(int dd) {
        dData = dd;
    }

    public void displayItem() {
        System.out.print("/" + dData);
    }

}
