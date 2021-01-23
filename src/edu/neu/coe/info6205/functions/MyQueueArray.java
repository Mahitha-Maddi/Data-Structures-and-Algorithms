/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mahit
 */
public class MyQueueArray {
    
    static final int maxSize=20;
    
    private static int rear;
       	private int front;
	private String[] queue;
        
        
    ArrayList<String> list;
	
    
    MyQueueArray(){
		 //maxSize = s;
	     queue = new String[maxSize];
	     rear = -1;
             front = 0;
             
        list = new ArrayList<String>();
	}
	public void enqueue(String str)
	{
		if (maxSize-1 == rear) { 
            System.out.println("\nQueue is full\n"); 
            return; 
        } 
		else { 
                    
            System.out.println("Pushed element:" + str);
                    rear++;
            queue[rear] = str; 
            
        } 
        return; 
	}
	
	public void dequeue() 
	{
		// If queue is empty, return. 
        if (rear==-1) {
            
           System.out.print("Queue is empty");
            return;
        }
            
        
           System.out.print("\nPOPPING ELEMENT\n" + queue[front]);    
        if (rear==0){
             queue[rear] = ""; 
        rear--; 
        return;
        }
        for (int i = 0; i < rear ; i++) { 
            queue[i] = queue[i + 1]; 
        } 
        // store 0 at rear indicating there's no element 
        if (rear < maxSize) 
            queue[rear] = ""; 
        rear--; 
        
	}
        
        
	public boolean isFull()
	{
		return rear == maxSize-1;
	}
	public boolean isEmpty()
	{
            
		return rear==-1;
	}
	public String peek() {
        if (isEmpty())  System.out.println("\nQueue is Empty\n"); 
        return queue[front];
    }
	public void printOriginalOrder() 
    { 
        if (rear==-1) { 
            System.out.println("\nQueue is Empty\n"); 
            return; 
        } 
         System.out.println("Printing queue in Original Order:");
        for (int i = front; i <= rear; i++) { 
            System.out.println(queue[i]); 
            System.out.println("\n");
        } 
        return; 
    } 

        void printReverseOrder() {
       if (rear==-1) { 
            System.out.println("\nQueue is Empty\n"); 
            return; 
        }  else {
            System.out.println("Printing queue in Reverse Order:");
            for (int i = rear; i >= 0; i--) {
                System.out.println(queue[i]); 
            System.out.println("\n");
            }

        }
    }

    public void readFile() {
        Scanner s;
        try {
            s = new Scanner(new File("C:\\Users\\mahit\\Downloads\\input.txt"));

            while (s.hasNextLine()) {
                list.add(s.next());
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyQueueArray myQueue = new MyQueueArray();
        myQueue.readFile();
        System.out.println("Queue isEmpty: " + myQueue.isEmpty());
        System.out.println();
        System.out.println("Enquing 3 elements into queue");
       
        for (int i = 0; i < 3; i++) {
            myQueue.enqueue(myQueue.list.get(i));
        }
        System.out.println();
        System.out.println("Queue isEmpty: " + myQueue.isEmpty());
        System.out.println();

        System.out.println("\nDequeuing 2 elements from queue");
        myQueue.dequeue();
        myQueue.dequeue();
        
        System.out.println();
        System.out.println("Queue isEmpty: " + myQueue.isEmpty());
        System.out.println();
/*
        System.out.println("\nSince stack is not empty. Popping all elements from stack to make it empty so that we can push elements in the next step.");
        myQueue.pop();
*/
        System.out.println();
        System.out.println("Queue isEmpty: " + myQueue.isEmpty());
        System.out.println();

        System.out.println("\nEnquing all elements into Queue");
        for (int i = 0; i < myQueue.list.size(); i++) {
            myQueue.enqueue(myQueue.list.get(i));
        }
        System.out.println();

        System.out.println("\nDequeuing all elements into Queue");
        while (rear > -1) {
            myQueue.dequeue();
        }

        System.out.println();

        System.out.println("\nEnquing all elements into Queue");

        for (int i = 0; i < myQueue.list.size(); i++) {
            myQueue.enqueue(myQueue.list.get(i));
        }

        System.out.println();
        myQueue.printReverseOrder();
        System.out.println();
        myQueue.printOriginalOrder();

    }
}
