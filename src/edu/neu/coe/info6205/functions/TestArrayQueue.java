
/*
 *Shiva Mahitha Maddi
 * NU ID : 001061161
 */
package edu.neu.coe.info6205.functions;

import java.util.ArrayList;

/**
 *
 * @author mahit
 */
public class TestArrayQueue {
    	private int maxSize,rear,front;
	private String[] queue;
        
    
    TestArrayQueue(int s){
		 maxSize = s;
	     queue = new String[maxSize];
	     rear = -1;
             front = 0;
             
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
            
        
           System.out.print("\nPOPPING ELEMENT\n" + queue[front] + "\n");    
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
	public void display() 
    { 
        if (rear==-1) { 
            System.out.println("\nQueue is Empty\n"); 
            return; 
        } 
        for (int i = front; i <= rear; i++) { 
            System.out.println(queue[i]); 
        } 
        return; 
    } 
	public static void main(String[] args) {
		TestArrayQueue q = new TestArrayQueue(10);
		q.enqueue("hi");
		q.enqueue("there");
		q.enqueue("sup?");
		q.dequeue();
                q.dequeue();
                //q.dequeue();
                
                 System.out.println( " Peek element is " + q.peek()); 
                
              
                //q.dequeue();
                
                 System.out.println( "Displaying all elements in the queue"); 
		q.display();
	}
}
