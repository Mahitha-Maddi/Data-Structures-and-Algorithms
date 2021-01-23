
/*
 * Shiva Mahitha Maddi
 * 001061161
 */
package edu.neu.coe.info6205.functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author mahit
 */
public class MyQueueLinkedList {
     private static Node rear;
    private Node front ; //rear is last...front is first
    ArrayList<String> list;

    private class Node {

        String data;
        Node next;

        Node(String d) {
            data = d;
            next = null;
        } // Constructor 

    }

    MyQueueLinkedList() {
       
        this.front = null;
        this.rear = null;
        list = new ArrayList<String>();
    }

   public void enqueue(String str) {

        // If queue is empty, then new node is front and rear both 
        if (this.rear == null) {
            Node node = new Node(str);
            this.front = this.rear = node;
            System.out.println("Enqueued: " + node.data);
            return;
        }

        Node node = new Node(str);
        System.out.println("Enqueued: " + node.data);

        // Add the new node at the end of queue and change rear 
        this.rear.next = node;
        this.rear = node;
    }

    public void dequeue() {
        // If queue is empty, return NULL. 
        if (this.front == null) {
            return;
        }
        System.out.println("\nDequeued: " + this.front.data);
        this.front = this.front.next;

        // If front becomes NULL, then change rear also as NULL 
        if (this.front == null) {
            this.rear = null;
        }
    }

    public boolean isEmpty() {
        return front == null;
    }

    public String peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        return front.data;
    }

    public void display() {
        if (front == null) {
            System.out.println("\nQueue Underflow");
        } else {
            Node temp = front;
            System.out.println("\nElements in the queue:");
            while (temp != null) {

                System.out.println(temp.data + "->");
                temp = temp.next;
            }
        }
    }
    void printReverseOrder() {
        if (front == null) {
            System.out.println("Queue is Empty");
        } else {
            System.out.println("Printing queue in Reverse Order:");
            Node temp = front;
            while (temp != null) {
                System.out.println("Item: " + temp.data);
                temp = temp.next;
            }
        }
    }

    void printOriginalOrder(Node temp) {
        if (temp == null) {
            return;
        }
        printOriginalOrder(temp.next);
        System.out.println("Item: " + temp.data);

    }

    void printOriginalOrder() {
        Node temp = front;
        if (front == null) {
            System.out.println("Queue is Empty");
        } else {
            System.out.println("Printing queue in Original Order:");
            printOriginalOrder(temp);
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
        MyQueueLinkedList myQueue = new MyQueueLinkedList();
        myQueue.readFile();
        System.out.println("Queue is Empty: " + myQueue.isEmpty());
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
        System.out.println("Queue is Empty: " + myQueue.isEmpty());
        System.out.println();

      /*  System.out.println("\nSince queue is not empty. Popping all elements from stack to make it empty so that we can push elements in the next step.");
        myQueue.dequeue();
        

        System.out.println();
        System.out.println("Queue is Empty: " + myQueue.isEmpty());
        System.out.println();

     */
        
        System.out.println("\nEnquing all elements into Queue");
        for (int i = 0; i < myQueue.list.size(); i++) {
            myQueue.enqueue(myQueue.list.get(i));
        }
        System.out.println();

        System.out.println("\nDequeuing all elements into Queue");
        while (rear!= null) {
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
