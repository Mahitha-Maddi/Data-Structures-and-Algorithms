/*
 * Shiva Mahitha Maddi
 * 001061161
 */
package edu.neu.coe.info6205.functions;

/**
 *
 * @author mahit
 */
class Record {

    static public class Node {

        private int key;
        private int height;
        private Node leftNode;
        private Node rightNode;

        public Node(int key, Node leftNode, Node rightNode) {
            this.key = key;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        public Node(int key) {
            this.key = key;
            this.height = 1;
        }

        public int getKey() {
            return key;
        }

        public Node getLeftNode() {
            return leftNode;
        }

        public Node getRightNode() {
            return rightNode;
        }

        public int getHeight() {
            return height;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }

        public void setHeight(int height) {
            this.height = height;
        }

    }

    public static class AVLTree {

        Node root;

        // A utility function to get the height of the tree 
        int height(Node N) {
            if (N == null) {
                return -1;
            }

            return N.getHeight();
        }

        // A utility function to get maximum of two integers 
        int max(int a, int b) {
            return (a > b) ? a : b;
        }

        // A utility function to right rotate subtree rooted with y 
        // See the diagram given above. 
        Node rightRotate(Node y) {
            Node x = y.getLeftNode();
            Node T2 = x.getRightNode();

            // Perform rotation 
            x.setRightNode(y);
            y.setLeftNode(T2);

            // Update heights 
            y.setHeight(max(height(y.getLeftNode()), height(y.getRightNode())) + 1);
            x.setHeight(max(height(x.getLeftNode()), height(x.getRightNode())) + 1);

            // Return new root 
            return x;
        }

        // A utility function to left rotate subtree rooted with x 
        // See the diagram given above. 
        Node leftRotate(Node x) {
            Node y = x.getRightNode();
            Node T2 = y.getLeftNode();

            // Perform rotation 
            y.setLeftNode(x);
            x.setRightNode(T2);

            //  Update heights 
            x.setHeight(max(height(x.getLeftNode()), height(x.getRightNode())) + 1);
            y.setHeight(max(height(y.getLeftNode()), height(y.getRightNode())) + 1);

            // Return new root 
            return y;
        }

        // Get Balance factor of node N 
        int getBalance(Node N) {
            if (N == null) {
                return 0;
            }

            return height(N.getLeftNode()) - height(N.getRightNode());
        }

        Node insert(Node node, int key) {

            /* 1.  Perform the normal BST insertion */
            if (node == null) {
                return (new Node(key));
            }

            if (key < node.key) {
                node.setLeftNode(insert(node.getLeftNode(), key));
            } else if (key > node.key) {
                node.setRightNode(insert(node.getRightNode(), key));
            } else // Duplicate keys not allowed 
            {
                return node;
            }

            /* 2. Update height of this ancestor node */
            node.height = 1 + max(height(node.getLeftNode()),
                    height(node.getRightNode()));

            /* 3. Get the balance factor of this ancestor 
              node to check whether this node became 
              unbalanced */
            int balance = getBalance(node);

            // If this node becomes unbalanced, then there 
            // are 4 cases Left Left Case 
            if (balance > 1 && key < node.getLeftNode().key) {
                return rightRotate(node);
            }

            // Right Right Case 
            if (balance < -1 && key > node.getRightNode().key) {
                return leftRotate(node);
            }

            // Left Right Case 
            if (balance > 1 && key > node.getLeftNode().key) {
                node.setLeftNode(leftRotate(node.getLeftNode()));
                return rightRotate(node);
            }

            // Right Left Case 
            if (balance < -1 && key < node.getRightNode().key) {
                node.setRightNode(rightRotate(node.getRightNode()));
                return leftRotate(node);
            }

            /* return the (unchanged) node pointer */
            return node;
        }

        // A utility function to print preorder traversal 
        // of the tree. 
        // The function also prints height of every node 
        void preOrder(Node node) {
            if (node != null) {
                System.out.print(node.key + " ");
                preOrder(node.getLeftNode());
                preOrder(node.getRightNode());
            }
        }

    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
//{9,23,45,1,5,14,55,24,13,11,8,19,4,31,35,56}
        /* Constructing tree given in the above figure */
        tree.root = tree.insert(tree.root, 9);
        tree.root = tree.insert(tree.root, 23);
        tree.root = tree.insert(tree.root, 45);
        tree.root = tree.insert(tree.root, 1);
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 14);
        tree.root = tree.insert(tree.root, 55);
        tree.root = tree.insert(tree.root, 24);
        tree.root = tree.insert(tree.root, 13);
        tree.root = tree.insert(tree.root, 11);
        tree.root = tree.insert(tree.root, 8);
        tree.root = tree.insert(tree.root, 19);
        tree.root = tree.insert(tree.root, 4);
        tree.root = tree.insert(tree.root, 31);
        tree.root = tree.insert(tree.root, 35);
        tree.root = tree.insert(tree.root, 56);

        /* The constructed AVL Tree would be 
             30 
            /  \ 
          20   40 
         /  \     \ 
        10  25    50 
         */
        System.out.println("Preorder traversal"
                + " of constructed tree is : ");
        tree.preOrder(tree.root);
    }
}

