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
public class RecordBtree <T extends Comparable<T>>{
    private int degree;
	
	private Node<T> root;
	
	public RecordBtree(int t) {
		this.root = null;
		this.degree = t;
	}
	
	public String inorderTraverse() {
		return inorder(root);
	}
	
	private String inorder(Node<T> current) {
		if (current == null) {
			return "";
		}
		else {
			String result = "";
			result += inorder(current.records[0].leftNode) + " ";
			for(int i = 0; i < current.n; ++i) {
				result += current.records[i].key.toString() + " " + 
						inorder(current.records[i].rightNode) + " ";
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
		}
		else {
			if (root.n == 2*degree - 1) {
				Node<T> newRoot = new Node<>();
				newRoot.leaf = false;
				newRoot.records[0] = new Record<>(root.records[degree-1].key);
				Node<T> child1 = root;
				Node<T> child2 = new Node<>();
				for(int i = 0; i < degree - 1; i ++) {
					child2.records[i] = root.records[i+degree];
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
	
	
	private class Node<T extends Comparable<T>>{
		Record<T>[] records;
		boolean leaf;
		int n;
		
		public Node() {
			this.records = new Record[2*degree-1];
			this.leaf = true;
			this.n = 0;
		}
		
		boolean isLeaf() {
			return leaf;
		}
		
		void insert(T data) {
			//System.out.println(data.toString());
			assert(n < 2*degree - 1);
			Record<T> newRecord = new Record<T>(data);
			//System.out.println(newRecord.key);
			int i = n - 1;
			if (leaf) {
				//System.out.println("leaf");
				//System.out.println(records[i].key);
				while(i >= 0 && records[i].key.compareTo(data)>0) {
					records[i+1] = records[i];
					i--;
					//System.out.println(i);
				}
				//System.out.println(i);
				records[i+1] = newRecord;
				n++;
				//System.out.println("insert " + data + " at " + i+1);
			}
			else {
				//System.out.println("nonleaf " + data);
				while(i >= 0 && records[i].key.compareTo(data)>0) {
					//System.out.println(data+" "+records[i].key);
					i--;
				}
				//System.out.println("current " + i);
				boolean leftmost = false;
				if (i == -1) {
					leftmost = true;
					if (records[0].leftNode.n == 2*degree-1) {
						splitChild(-1, records[0].leftNode);
						
						if (records[i+1].key.compareTo(data)<0) {
							i++;
						}
					}
					if (i != -1) {
						records[i].rightNode.insert(data);
					} else {
						records[0].leftNode.insert(data);
					}
				}
				else if (records[i].rightNode.n == 2*degree-1) {
					splitChild(i, records[i].rightNode);
					
					if (records[i+1].key.compareTo(data)<0) {
						i++;
					}
				}
				if (!leftmost) records[i].rightNode.insert(data);
			}
		}
		
		void splitChild(int from, Node<T> node) {
			Node<T> child2 = new Node<>();
			child2.leaf = node.leaf;
			child2.n = degree - 1;
			
			for(int i = 0; i < degree-1; ++i) {
				child2.records[i] = node.records[i+degree];
			}
			
			node.n = degree - 1;
			for(int j = n-1; j >= from + 1; j--) {
				//System.out.println(n + " " + j);
				records[j+1] = records[j];
			}
			records[from + 1] = new Record<T>(node.records[degree-1].key);
			records[from + 1].leftNode = node;
			records[from + 1].rightNode = child2;
			if (from + 2 < n) {
				records[from + 2].leftNode = child2;
			}
			n++;
		}
		
		void remove(T t) {
			
		}
		
		void removeFromLeaf(T t) {
			
		}
		
		void removeFromNonLeaf(T t) {
			
		}
		
		T getPred(){
			if (leaf) {
				return records[n-1].key; 
			} else {
				return records[n-1].rightNode.getPred();
			}
		}
		
		T getSucc(){
			if (leaf) {
				return records[0].key;
			} else {
				return records[0].leftNode.getSucc();
			}
		}
		
		void borrowFromPrev(int idx) {
			assert(idx <= n);
			if (idx == n) {
				for(int i = records[n-1].rightNode.n; i >= 0; i --) {
					records[n-1].rightNode.records[i] = records[n-1].rightNode.records[i-1];
				}
			} else {
				
			}
		}
		
		void borrowFromNext(int idx) {
			
		}
		
		void merge(int idx) {
			
		}
	}
	
	private class Record<T extends Comparable<T>>{
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
		RecordBtree<Integer> bTree = new RecordBtree<>(2);
		int[] inserts = {3, 7, 9, 23, 45, 1, 5, 14, 5, 24, 13, 11, 8, 19, 4, 31, 35, 56, 17, 29, 6, 22};
		for(int i : inserts) {
			bTree.insert(i);
		}
		System.out.println(bTree.inorderTraverse());
	}
}
