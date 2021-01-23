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
public class InsertionAndDeletion<T extends Comparable<T>> {

    private int degree;

    private Node<T> root;

    public InsertionAndDeletion(int t) {
        this.root = null;
        this.degree = t;
    }

    public String inorderTraverse() {
        return inorder(root);
    }

    private String inorder(Node<T> current) {
        if (current == null) {
            return "";
        } else {
            String result = "";
            result += inorder(current.records[0].leftNode) + " ";
            for (int i = 0; i < current.n; ++i) {
                result += current.records[i].key.toString() + " "
                        + inorder(current.records[i].rightNode) + " ";
            }
            return result;
        }
    }

    public void insert(T t) {
        if (root == null) {
            Node<T> s = new Node<>();
            s.records[0] = new Record<T>(t);
            s.n = 1;
            root = s;
        } else {
            if (root.n == 2 * degree - 1) {
                Node<T> newRoot = new Node<>();
                newRoot.leaf = false;
                newRoot.records[0] = new Record<>(root.records[degree - 1].key);
                Node<T> child1 = root;
                Node<T> child2 = new Node<>();
                for (int i = 0; i < degree - 1; i++) {
                    child2.records[i] = root.records[i + degree];
                }
                child2.leaf = root.leaf;
                child2.n = degree - 1;
                child1.n = degree - 1;
                newRoot.records[0].leftNode = child1;
                newRoot.records[0].rightNode = child2;
                newRoot.n = 1;
                root = newRoot;
            }
            root.insert(t);
        }
    }

    public void remove(T t) {
        this.root.remove(t);
        if (this.root.n == 0) {
            this.root = this.root.records[0].leftNode;
        }
    }

    private class Node<T extends Comparable<T>> {

        Record<T>[] records;
        boolean leaf;
        int n;

        public Node() {
            this.records = new Record[2 * degree - 1];
            this.leaf = true;
            this.n = 0;
        }

        boolean isLeaf() {
            return leaf;
        }

        void insert(T data) {
            //System.out.println(data.toString());
            assert (n < 2 * degree - 1);
            Record<T> newRecord = new Record<T>(data);
            //System.out.println(newRecord.key);
            int i = n - 1;
            if (leaf) {
                //System.out.println("leaf");
                //System.out.println(records[i].key);
                while (i >= 0 && records[i].key.compareTo(data) > 0) {
                    records[i + 1] = records[i];
                    i--;
                    //System.out.println(i);
                }
                //System.out.println(i);
                records[i + 1] = newRecord;
                n++;
                //System.out.println("insert " + data + " at " + i+1);
            } else {
                //System.out.println("nonleaf " + data);
                while (i >= 0 && records[i].key.compareTo(data) > 0) {
                    //System.out.println(data+" "+records[i].key);
                    i--;
                }
                //System.out.println("current " + i);
                boolean leftmost = false;
                if (i == -1) {
                    leftmost = true;
                    if (records[0].leftNode.n == 2 * degree - 1) {
                        splitChild(-1, records[0].leftNode);

                        if (records[i + 1].key.compareTo(data) < 0) {
                            i++;
                        }
                    }
                    if (i != -1) {
                        records[i].rightNode.insert(data);
                    } else {
                        records[0].leftNode.insert(data);
                    }
                } else if (records[i].rightNode.n == 2 * degree - 1) {
                    splitChild(i, records[i].rightNode);

                    if (records[i + 1].key.compareTo(data) < 0) {
                        i++;
                    }
                }
                if (!leftmost) {
                    records[i].rightNode.insert(data);
                }
            }
        }

        void splitChild(int from, Node<T> node) {
            Node<T> child2 = new Node<>();
            child2.leaf = node.leaf;
            child2.n = degree - 1;

            for (int i = 0; i < degree - 1; ++i) {
                child2.records[i] = node.records[i + degree];
            }

            node.n = degree - 1;
            for (int j = n - 1; j >= from + 1; j--) {
                //System.out.println(n + " " + j);
                records[j + 1] = records[j];
            }
            records[from + 1] = new Record<T>(node.records[degree - 1].key);
            records[from + 1].leftNode = node;
            records[from + 1].rightNode = child2;
            if (from + 2 < n) {
                records[from + 2].leftNode = child2;
            }
            n++;
        }

        void remove(T t) {
            //System.out.println(t);
            System.out.println(inorderTraverse());
            boolean hit = false;
            int hitInd = -1;
            for (int i = 0; i < n; ++i) {
                if (records[i].key.compareTo(t) == 0) {
                    hit = true;
                    hitInd = i;
                }
            }
            System.out.println("hit: " + hit);
            if (hit) {
                if (leaf) {
                    for (int i = hitInd; i < n - 1; i++) {
                        records[i] = records[i + 1];
                    }
                    n--;
                    return;
                } else {
                    if (records[hitInd].leftNode.n > degree - 1) {
                        T pred = records[hitInd].leftNode.getPred();
                        records[hitInd].key = pred;
                        records[hitInd].leftNode.remove(pred);
                    } else if (records[hitInd].rightNode.n > degree - 1) {
                        T succ = records[hitInd].rightNode.getSucc();
                        records[hitInd].key = succ;
                        records[hitInd].rightNode.remove(succ);
                    } else {
                        Node<T> left = records[hitInd].leftNode;
                        Node<T> right = records[hitInd].rightNode;
                        left.records[left.n++] = new Record<T>(records[hitInd].key);
                        left.records[left.n - 1].leftNode = left.records[left.n - 2].rightNode;
                        left.records[left.n - 1].rightNode = right.records[0].leftNode;
                        for (int i = 0; i < right.n; i++) {
                            left.records[i + left.n] = right.records[i];
                        }
                        left.n += right.n;
                        for (int i = hitInd; i < n - 1; ++i) {
                            records[i] = records[i + 1];
                        }
                        n--;
                        records[hitInd].leftNode = left;
                    }
                    return;
                }
            } else {
                if (leaf) {
                    return;
                }
                for (int i = 0; i < n; ++i) {
                    if (records[i].key.compareTo(t) > 0) {
                        hitInd = i;
                        break;
                    }
                }
                System.out.println("Not hit, go to index: " + hitInd);
                if (hitInd == -1) {
                    /*
					 * case right most
                     */
                    if (records[n - 1].rightNode.n > degree - 1) {
                        //System.out.println("direct remove");
                        records[n - 1].rightNode.remove(t);
                        return;
                    }
                    if (records[n - 1].leftNode.n > degree - 1) {
                        //System.out.println("borrow from left");
                        Node<T> left = records[n - 1].leftNode;
                        Node<T> right = records[n - 1].rightNode;
                        for (int i = right.n; i > 0; --i) {
                            right.records[i] = right.records[i - 1];
                        }
                        right.records[0] = new Record<T>(records[n - 1].key);
                        records[n - 1].key = left.records[left.n - 1].key;
                        right.records[0].leftNode = left.records[left.n - 1].rightNode;
                        right.records[0].rightNode = right.records[1].leftNode;
                        left.n--;
                        right.n++;
                        records[n - 1].rightNode.remove(t);
                        return;
                    } else {
                        //System.out.println("merge together");
                        Node<T> left = records[n - 1].leftNode;
                        Node<T> right = records[n - 1].rightNode;
                        left.records[left.n++] = new Record<T>(records[n - 1].key);
                        left.records[left.n - 1].leftNode = left.records[left.n - 2].rightNode;
                        left.records[left.n - 1].rightNode = right.records[0].leftNode;
                        for (int i = 0; i < right.n; i++) {
                            //System.out.println(right.records[i].key);
                            left.records[i + left.n] = right.records[i];
                        }
                        left.n += right.n;
                        n--;
                        records[n - 1].rightNode.remove(t);
                    }
                } else if (hitInd == 0) {
                    /*
					 * case left most
                     */
                    if (records[0].leftNode.n > degree - 1) {
                        records[0].leftNode.remove(t);
                        return;
                    }
                    if (records[0].rightNode.n > degree - 1) {
                        Node<T> left = records[0].leftNode;
                        Node<T> right = records[0].rightNode;
                        left.records[left.n] = new Record<T>(records[0].key);
                        left.records[left.n].leftNode = left.records[left.n - 1].rightNode;
                        left.records[left.n].rightNode = right.records[0].leftNode;
                        records[0].key = right.records[0].key;
                        for (int i = 0; i < right.n - 1; ++i) {
                            right.records[i] = right.records[i + 1];
                        }
                        left.n++;
                        right.n--;
                        records[0].leftNode.remove(t);
                    } else {
                        Node<T> left = records[0].leftNode;
                        Node<T> right = records[0].rightNode;
                        left.records[left.n++] = new Record<T>(records[0].key);
                        left.records[left.n - 1].leftNode = left.records[left.n - 2].rightNode;
                        left.records[left.n - 1].rightNode = right.records[0].leftNode;
                        for (int i = 0; i < right.n; i++) {
                            left.records[i + left.n] = right.records[i];
                        }
                        left.n += right.n;
                        for (int i = 0; i < n - 1; ++i) {
                            records[i] = records[i + 1];
                        }
                        n--;
                        records[0].leftNode = left;
                        records[0].leftNode.remove(t);
                    }

                } else {
                    if (records[hitInd].leftNode.n > degree - 1) {
                        records[hitInd].leftNode.remove(t);
                        return;
                    }
                    if (records[hitInd - 1].leftNode.n > degree - 1) {
                        Node<T> left = records[hitInd - 1].leftNode;
                        Node<T> right = records[hitInd].leftNode;
                        for (int i = right.n; i > 0; --i) {
                            right.records[i] = right.records[i - 1];
                        }
                        right.records[0] = new Record<T>(records[hitInd - 1].key);
                        records[hitInd - 1].key = left.records[left.n - 1].key;
                        right.records[0].leftNode = left.records[left.n - 1].rightNode;
                        right.records[0].rightNode = right.records[1].leftNode;
                        left.n--;
                        right.n++;
                        records[hitInd].leftNode.remove(t);
                    }
                    if (records[hitInd].rightNode.n > degree - 1) {
                        Node<T> left = records[hitInd].leftNode;
                        Node<T> right = records[hitInd].rightNode;
                        left.records[left.n++] = new Record<T>(records[hitInd].key);
                        left.records[left.n - 1].leftNode = left.records[left.n - 2].rightNode;
                        left.records[left.n - 1].rightNode = right.records[0].leftNode;
                        left.records[left.n] = new Record<T>(records[hitInd].key);
                        left.records[left.n].leftNode = left.records[left.n - 1].rightNode;
                        left.records[left.n].rightNode = right.records[0].leftNode;
                        records[hitInd].key = right.records[0].key;
                        for (int i = 0; i < right.n - 1; ++i) {
                            right.records[i] = right.records[i + 1];
                        }
                        left.n++;
                        right.n--;
                        records[hitInd].leftNode.remove(t);
                    } else {
                        Node<T> left = records[hitInd].leftNode;
                        Node<T> right = records[hitInd].rightNode;
                        for (int i = 0; i < right.n; i++) {
                            left.records[i + left.n] = right.records[i];
                        }
                        left.n += right.n;
                        for (int i = hitInd; i < n - 1; ++i) {
                            records[i] = records[i + 1];
                        }
                        n--;
                        records[hitInd].leftNode = left;
                        records[hitInd].leftNode.remove(t);
                    }
                }
            }
        }

        T getPred() {
            if (leaf) {
                return records[n - 1].key;
            } else {
                return records[n - 1].rightNode.getPred();
            }
        }

        T getSucc() {
            if (leaf) {
                return records[0].key;
            } else {
                return records[0].leftNode.getSucc();
            }
        }

        void borrowFromPrev(int idx) {
            assert (idx <= n);
            if (idx == n) {
                for (int i = records[n - 1].rightNode.n; i >= 0; i--) {
                    records[n - 1].rightNode.records[i] = records[n - 1].rightNode.records[i - 1];
                }
            } else {

            }
        }

        void borrowFromNext(int idx) {

        }

        void merge(int idx) {

        }
    }

    private class Record<T extends Comparable<T>> {

        private T key;
        private Node<T> leftNode;
        private Node<T> rightNode;

        public Record(T key, Node<T> leftNode, Node<T> rightNode) {
            this.key = key;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        public Record(T key) {
            this.key = key;
            this.leftNode = null;
            this.rightNode = null;
        }

        public T getKey() {
            return key;
        }

        public Node<T> getLeftNode() {
            return leftNode;
        }

        public Node<T> getRightNode() {
            return rightNode;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public void setLeftNode(Node<T> leftNode) {
            this.leftNode = leftNode;
        }

        public void setRightNode(Node<T> rightNode) {
            this.rightNode = rightNode;
        }
    }

    public static void main(String[] args) {
        InsertionAndDeletion<Character> bTree = new InsertionAndDeletion<>(3);

        System.out.println("Insertion in Btree: ");
        char[] inserts = {'A', 'G', 'F', 'B', 'K', 'D', 'H', 'M', 'J', 'E', 'S', 'I', 'R', 'X', 'C', 'L', 'N', 'T', 'U', 'P'};
        for (char i : inserts) {

            System.out.println("Inserting: " + i);
            bTree.insert(i);
        }
        
        System.out.println(bTree.inorderTraverse());

        System.out.println("\n-------------------------------------------------------------------------");

        //Case 1:

        System.out.println("Case1:\n");
        
        System.out.println("Removing J: ");
        bTree.remove('J');

        System.out.println(bTree.inorderTraverse());
        //Case 2:

        System.out.println("\n-------------------------------------------------------------------------");

        System.out.println("Case2:\n");
        
        System.out.println("Removing M: ");
        bTree.remove('M');
        
        System.out.println("\n***************************************************");
        
        System.out.println("Removing N: ");
        bTree.remove('N');
        
        
        
        System.out.println("\n***************************************************");
        System.out.println("Removing R: ");
        bTree.remove('R');

        System.out.println(bTree.inorderTraverse());
        //Case 3:

        System.out.println("\n-------------------------------------------------------------------------");

        System.out.println("Case3:\n");
        
        System.out.println("Removing U: ");
        bTree.remove('U');
        
        
        System.out.println("\n***************************************************");
        System.out.println("Removing X: ");
        bTree.remove('X');
        
        
        System.out.println("\n***************************************************");
        System.out.println("Removing L: ");
        bTree.remove('L');
        System.out.println(bTree.inorderTraverse());
    }
}
