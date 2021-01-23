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
public class BTreeInsertDelete <T extends Comparable<T>> {
    
      


    private Node root;

    public BTreeInsertDelete() {
        root = null;
    }

    public BTreeInsertDelete(Node root) {
        this.root = root;
    }

    public void insert(T val) {
        insert(val, root, null, false);
    }

    public boolean isMember(T val) {
        return isMember(val, root);
    }

    public void delete(T val) {
        delete(val, root);
    }

    public String toString() {
        return inOrder();
    }

    public String inOrder() {
        return inOrder(root);
    }

    private void insert(T val, Node node, Node parent, boolean right) {

        if (node == null) {
            if (parent == null) {
                root = node = new Node(val, parent);
            } else if (right) {
                parent.right = node = new Node(val, parent);
            } else {
                parent.left = node = new Node(val, parent);
            }
            restructInsert(node, false);
        } else if (val.compareTo(node.value) == 0) {
            node.value = val;
        } else if (val.compareTo(node.value) > 0) {
            insert(val, node.right, node, true);
        } else {
            insert(val, node.left, node, false);
        }
    }

    private boolean isMember(T val, Node node) {

        boolean member = false;

        if (node == null) {
            member = false;
        } else if (val.compareTo(node.value) == 0) {
            member = true;
        } else if (val.compareTo(node.value) > 0) {
            member = isMember(val, node.right);
        } else {
            member = isMember(val, node.left);
        }

        return member;
    }

    private void delete(T val, Node node) {

        if (val.compareTo(node.value) == 0) {
            deleteNode(node);
        } else if (val.compareTo(node.value) > 0) {
            delete(val, node.right);
        } else {
            delete(val, node.left);
        }
    }

    private void deleteNode(Node node) {

        Node eNode, minMaxNode, delNode = null;
        boolean rightNode = false;

        if (node.isLeaf()) {
            if (node.parent == null) {
                root = null;
            } else if (node.isRightNode()) {
                node.parent.right = null;
                rightNode = true;
            } else if (node.isLeftNode()) {
                node.parent.left = null;
            }
            delNode = node;
        } else if (node.hasLeftNode()) {
            minMaxNode = node.left;
            for (eNode = node.left; eNode != null; eNode = eNode.right) {
                minMaxNode = eNode;
            }
            delNode = minMaxNode;
            node.value = minMaxNode.value;

            if (node.left.right != null) {
                minMaxNode.parent.right = minMaxNode.left;
                rightNode = true;
            } else {
                minMaxNode.parent.left = minMaxNode.left;
            }

            if (minMaxNode.left != null) {
                minMaxNode.left.parent = minMaxNode.parent;
            }
        } else if (node.hasRightNode()) {
            minMaxNode = node.right;
            delNode = minMaxNode;
            rightNode = true;

            node.value = minMaxNode.value;

            node.right = minMaxNode.right;
            if (node.right != null) {
                node.right.parent = node;
            }
            node.left = minMaxNode.left;
            if (node.left != null) {
                node.left.parent = node;
            }
        }
        restructDelete(delNode.parent, rightNode);
    }

    private String inOrder(Node node) {

        String result = "";
        if (node != null) {
            result = result + inOrder(node.left) + " ";
            result = result + node.value.toString();
            result = result + inOrder(node.right);
        }
        return result;
    }

    private void restructInsert(Node node, boolean wasRight) {

        if (node != root) {
            if (node.parent.balance == '_') {
                if (node.isLeftNode()) {
                    node.parent.balance = '/';
                    restructInsert(node.parent, false);
                } else {
                    node.parent.balance = '\\';
                    restructInsert(node.parent, true);
                }
            } else if (node.parent.balance == '/') {
                if (node.isRightNode()) {
                    node.parent.balance = '_';
                } else {
                    if (!wasRight) {
                        rotateRight(node.parent);
                    } else {
                        doubleRotateRight(node.parent);
                    }
                }
            } else if (node.parent.balance == '\\') {
                if (node.isLeftNode()) {
                    node.parent.balance = '_';
                } else {
                    if (wasRight) {
                        rotateLeft(node.parent);
                    } else {
                        doubleRotateLeft(node.parent);
                    }
                }
            }
        }
    }

    private void restructDelete(Node z, boolean wasRight) {

        Node parent;
        boolean isRight = false;
        boolean climb = false;
        boolean canClimb;

        if (z == null) {
            return;
        }

        parent = z.parent;
        canClimb = (parent != null);

        if (canClimb) {
            isRight = z.isRightNode();
        }

        if (z.balance == '_') {
            if (wasRight) {
                z.balance = '/';
            } else {
                z.balance = '\\';
            }
        } else if (z.balance == '/') {
            if (wasRight) {
                if (z.left.balance == '\\') {
                    doubleRotateRight(z);
                    climb = true;
                } else {
                    rotateRight(z);
                    if (z.balance == '_') {
                        climb = true;
                    }
                }
            } else {
                z.balance = '_';
                climb = true;
            }
        } else {
            if (wasRight) {
                z.balance = '_';
                climb = true;
            } else {
                if (z.right.balance == '/') {
                    doubleRotateLeft(z);
                    climb = true;
                } else {
                    rotateLeft(z);
                    if (z.balance == '_') {
                        climb = true;
                    }
                }
            }
        }

        if (canClimb && climb) {
            restructDelete(parent, isRight);
        }
    }

    private void rotateLeft(Node a) {

        Node b = a.right;

        if (a.parent == null) {
            root = b;
        } else {
            if (a.isLeftNode()) {
                a.parent.left = b;
            } else {
                a.parent.right = b;
            }
        }

        a.right = b.left;
        if (a.right != null) {
            a.right.parent = a;
        }

        b.parent = a.parent;
        a.parent = b;
        b.left = a;

        if (b.balance == '_') {
            a.balance = '\\';
            b.balance = '/';
        } else {
            a.balance = '_';
            b.balance = '_';
        }
    }

    private void rotateRight(Node a) {

        Node b = a.left;

        if (a.parent == null) {
            root = b;
        } else {
            if (a.isLeftNode()) {
                a.parent.left = b;
            } else {
                a.parent.right = b;
            }
        }

        a.left = b.right;
        if (a.left != null) {
            a.left.parent = a;
        }

        b.parent = a.parent;
        a.parent = b;
        b.right = a;

        if (b.balance == '_') {
            a.balance = '/';
            b.balance = '\\';
        } else {
            a.balance = '_';
            b.balance = '_';
        }
    }

    private void doubleRotateLeft(Node a) {

        Node b = a.right;
        Node c = b.left;

        if (a.parent == null) {
            root = c;
        } else {
            if (a.isLeftNode()) {
                a.parent.left = c;
            } else {
                a.parent.right = c;
            }
        }

        c.parent = a.parent;

        a.right = c.left;
        if (a.right != null) {
            a.right.parent = a;
        }
        b.left = c.right;
        if (b.left != null) {
            b.left.parent = b;
        }

        c.left = a;
        c.right = b;

        a.parent = c;
        b.parent = c;

        if (c.balance == '/') {
            a.balance = '_';
            b.balance = '\\';
        } else if (c.balance == '\\') {
            a.balance = '/';
            b.balance = '_';
        } else {
            a.balance = '_';
            b.balance = '_';
        }

        c.balance = '_';
    }

    private void doubleRotateRight(Node a) {

        Node b = a.left;
        Node c = b.right;

        if (a.parent == null) {
            root = c;
        } else {
            if (a.isLeftNode()) {
                a.parent.left = c;
            } else {
                a.parent.right = c;
            }
        }

        c.parent = a.parent;

        a.left = c.right;
        if (a.left != null) {
            a.left.parent = a;
        }
        b.right = c.left;
        if (b.right != null) {
            b.right.parent = b;
        }

        c.right = a;
        c.left = b;

        a.parent = c;
        b.parent = c;

        if (c.balance == '/') {
            b.balance = '_';
            a.balance = '\\';
        } else if (c.balance == '\\') {
            b.balance = '/';
            a.balance = '_';
        } else {
            b.balance = '_';
            a.balance = '_';
        }
        c.balance = '_';
    }

    class Node {

        T value;

        Node parent;

        Node left;

        Node right;

        char balance;

        public Node(T value, Node parent) {
            this.value = value;
            this.parent = parent;
            this.left = null;
            this.right = null;
            this.balance = '_';
        }

        boolean isLeaf() {
            return ((left == null) && (right == null));
        }

        boolean isNode() {
            return !isLeaf();
        }

        boolean hasLeftNode() {
            return (null != left);
        }

        boolean hasRightNode() {
            return (right != null);
        }

        boolean isLeftNode() {
            return (parent.left == this);
        }

        boolean isRightNode() {
            return (parent.right == this);
        }
    }
    
        public static void main(String args[]) {

        BTreeInsertDelete<Integer> it = new BTreeInsertDelete<>();
        it.insert(3);
	it.insert(7);
	it.insert(9);
	it.insert(23);
	it.insert(45);
	it.insert(1);
	it.insert(5);
	it.insert(14);
	it.insert(5);
	it.insert(24);
	it.insert(13);
	it.insert(11);
	it.insert(8);
	it.insert(19);
	it.insert(4);
	it.insert(31);
	it.insert(35);
	it.insert(56);
	it.insert(17);
	it.insert(29);
	it.insert(6);
	it.insert(22);
	System.out.println(it);

	it.delete(45);
	it.delete(11);
    it.delete(31);
    it.delete(4);
	System.out.println(it);
	
        

    }

    
}
