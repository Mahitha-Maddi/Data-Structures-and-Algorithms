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
public class CircularQueue {

    int MAX_SIZE;
    private int a[];
    int front, rear;

    // Constructor - set front and rear as -1. 
    // We are assuming that for an empty Queue, both front and rear will be -1.
    public CircularQueue(int size) {
        this.MAX_SIZE = size;
        a = new int[MAX_SIZE];
        front = -1;
        rear = -1;
    }

    // To check wheter Queue is empty or not
    boolean isEmpty() {
        return (front == -1 && rear == -1);
    }

    // To check whether Queue is full or not
    boolean isFull() {
        return (rear + 1) % MAX_SIZE == front ? true : false;
    }

    // Inserts an element in queue at rear end
    void enqueue(int x) {

        if (isFull()) {
            System.out.println("\nQueue is Full");
            return;
        }
        if (isEmpty()) {
            front = rear = 0;
        } else {
            rear = (rear + 1) % MAX_SIZE;
        }
        a[rear] = x;
        System.out.println("Enqueued " + x + " at position " + rear);
    }

    // Removes an element in Queue from front end. 
    void dequeue() {
        if (isEmpty()) {
            System.out.println("\nQueue is Empty");
            return;
        } else if (front == rear) {
            System.out.println("Dequeued " + a[front] + " from position " + front);
            System.out.println("Queue is reset");
            rear = front = -1;
        } else {
            front = (front + 1) % MAX_SIZE;

            System.out.println("Dequeued " + a[front] + " from position " + front);
        }
    }
    // Returns element at front of queue. 

    int front() {
        if (front == -1) {
            System.out.println("\nCannot return front from empty queue");
            return -1;
        }
        return a[front];
    }

    /* 
	   Printing the elements in queue from front to rear. 
	   This function is only to test the code. 
	   This is not a standard function for Queue implementation. 
     */
    void displayQueue() {
        if (isEmpty()) {
            System.out.println("\nQueue is Empty");
            return;
        }
        // Finding number of elements in queue  
        int count = (rear + MAX_SIZE - front) % MAX_SIZE + 1;

        System.out.println("\nHead position: " + front);
        System.out.print("\nQueue elements: \n");
        for (int i = 0; i < count; i++) {
            int index = (front + i) % MAX_SIZE; // Index of element while travesing circularly from front
            System.out.println(a[index] + " ");
        }
        System.out.println();

        System.out.println("Tail position: " + rear);
    }

    public static void main(String[] args) {
        CircularQueue cq = new CircularQueue(10); // creating an instance of Queue. 
        System.out.println("Queue size: 10");

        System.out.println();
        cq.enqueue(21);
        cq.enqueue(18);
        cq.enqueue(38);
        cq.enqueue(3);
        cq.enqueue(9);
        cq.enqueue(82);
        cq.enqueue(10);
        cq.enqueue(31);
        cq.enqueue(25);
        cq.enqueue(29);

        System.out.println();
        cq.displayQueue();

        System.out.println();
        cq.dequeue();
        cq.dequeue();
        cq.dequeue();

        System.out.println();
        cq.displayQueue();

        System.out.println();
        cq.enqueue(16);
        cq.enqueue(50);

        System.out.println();
        cq.displayQueue();

        System.out.println();
        cq.dequeue();
        cq.dequeue();
        cq.dequeue();
        cq.dequeue();
        cq.dequeue();
        cq.dequeue();
        cq.dequeue();
        cq.dequeue();
        cq.dequeue();

        System.out.println();
        cq.displayQueue();
    }

}
