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
public class MyStackLinkedList {

    private static Node top;
    ArrayList<String> list;

    private class Node {

        String data;
        Node next;

        Node(String d) {
            data = d;
            next = null;
        } // Constructor 

    }

    MyStackLinkedList() {
        MyStackLinkedList.top = null;
        list = new ArrayList<String>();
    }

    public boolean isEmpty() {
        return top == null;
    }

    public String peek() {
        if (!isEmpty()) {
            return top.data;
        } else {
            System.out.println("Stack is empty");
            return null;
        }
    }

    public void push(String str) {
        Node newNode = new Node(str);
        newNode.next = top;
        top = newNode;
        System.out.println("Item pushed: " + top.data);
    }

    public void pop() {
        if (top == null) {
            System.out.print("\nStack is empty");
            return;
        }

        System.out.print("\nPOPPING ELEMENT\n" + top.data);
        top = (top).next;
    }

    void printReverseOrder() {
        if (top == null) {
            System.out.println("Stack is Empty");
        } else {
            System.out.println("Printing stack in Reverse Order:");
            Node temp = top;
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
        Node temp = top;
        if (top == null) {
            System.out.println("Stack is Empty");
        } else {
            System.out.println("Printing stack in Original Order:");
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
        MyStackLinkedList myStack = new MyStackLinkedList();
        myStack.readFile();
        System.out.println("Stack isEmpty: " + myStack.isEmpty());
        System.out.println();
        System.out.println("Pushing 5 elements into stack");
        //pushing 5 elements into stack
        for (int i = 0; i < 5; i++) {
            myStack.push(myStack.list.get(i));
        }
        System.out.println();
        System.out.println("Stack isEmpty: " + myStack.isEmpty());
        System.out.println();

        System.out.println("\nPoping 4 elements from stack");
        myStack.pop();
        myStack.pop();
        myStack.pop();
        myStack.pop();

        System.out.println();
        System.out.println("Stack isEmpty: " + myStack.isEmpty());
        System.out.println();

        System.out.println("\nSince stack is not empty. Popping all elements from stack to make it empty so that we can push elements in the next step.");
        myStack.pop();

        System.out.println();
        System.out.println("Stack isEmpty: " + myStack.isEmpty());
        System.out.println();

        System.out.println();
        System.out.println("Stack isEmpty: " + myStack.isEmpty());
        System.out.println();

        System.out.println("\nPushing all elements into stack");
        for (int i = 0; i < myStack.list.size(); i++) {
            myStack.push(myStack.list.get(i));
        }
        System.out.println();

        System.out.println("\nPoping all elements into stack");
        while (top != null) {
            myStack.pop();
        }

        System.out.println();

        System.out.println("\nPushing all elements into stack");

        for (int i = 0; i < myStack.list.size(); i++) {
            myStack.push(myStack.list.get(i));
        }

        System.out.println();
        myStack.printReverseOrder();
        System.out.println();
        myStack.printOriginalOrder();

    }
}
