/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.functions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author mahit
 */
public class BFS {
    private Queue<Node> queue;
	static ArrayList<Node> nodes=new ArrayList<Node>();
	static class Node
	{
		int data;
		boolean visited;
		List<Node> neighbours;
 
		Node(int data)
		{
			this.data=data;
			this.neighbours=new ArrayList<>();
 
		}
		public void addneighbours(Node neighbourNode)
		{
			this.neighbours.add(neighbourNode);
		}
		public List<Node> getNeighbours() {
			return neighbours;
		}
		public void setNeighbours(List<Node> neighbours) {
			this.neighbours = neighbours;
		}
	}
 
	public BFS()
	{
		queue = new LinkedList<Node>();
	}
 
	public void bfs(Node node)
	{
		queue.add(node);
		node.visited=true;
		while (!queue.isEmpty())
		{
 
			Node element=queue.remove();
			System.out.print(element.data + " ");
			List<Node> neighbours=element.getNeighbours();
			for (int i = 0; i < neighbours.size(); i++) {
				Node n=neighbours.get(i);	
				if(n!=null && !n.visited)
				{
					queue.add(n);
					n.visited=true;
 
				}
			}
 
		}
	}
        
       
        
      /*  public void recursiveBFS(Node node)
	{
            queue.add(node);
		node.visited=true;
		if (queue.isEmpty())
			return;

		// pop front node from queue and print it
		Node element = queue.remove();
		System.out.print(element.data + " ");

		// do for every edge (v -> u)
                
                List<Node> neighbours=element.getNeighbours();
			for (int i = 0; i < neighbours.size(); i++) {
                            Node n=neighbours.get(i);
			if (n!=null && !n.visited)
			{
				// mark it discovered and push it into queue
                        n.visited=true;
			queue.add(n);
			       
			}
                        
		recursiveBFS(n);
		}

	}*/
 
	public static void main(String arg[])
	{
 
		Node node10 = new Node(1);
		Node node20 = new Node(2);
		Node node30 = new Node(3);
		Node node40 = new Node(4);
		Node node50 = new Node(5);
		Node node60 = new Node(6);
		Node node70 = new Node(7);
                Node node80 = new Node(8);
                Node node90 = new Node(9);

		node10.addneighbours(node40);
		node20.addneighbours(node30);
		node20.addneighbours(node40);
		node30.addneighbours(node20);
		node30.addneighbours(node70);
		node40.addneighbours(node10);
		node40.addneighbours(node20);
		node40.addneighbours(node80);
		node40.addneighbours(node70);
		node40.addneighbours(node60);
		node50.addneighbours(node60);
		node50.addneighbours(node70);
		node50.addneighbours(node90);
		node60.addneighbours(node40);
		node60.addneighbours(node50);
		node60.addneighbours(node70);
		node70.addneighbours(node60);
		node70.addneighbours(node50);
                node70.addneighbours(node40);
		node70.addneighbours(node30);
                node80.addneighbours(node40);
                node90.addneighbours(node50);
		
		System.out.println("The BFS traversal of the graph is ");
		BFS bfsExample = new BFS();
		bfsExample.bfs(node10);
 
	}
}
