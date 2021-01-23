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
public class MyStackArray {
    static final int MAX=20;
    //int size;
    String arr[];
    private static int top;
    ArrayList<String> list;

    MyStackArray() {
        this.arr = new String[MAX];
        list = new ArrayList<String>();
        MyStackArray.top = -1;
    }

    public void push(String pushedElement) {
        if (!isFull()) {
            top++;
            arr[top] = pushedElement;
            System.out.println("Pushed element:" + pushedElement);
        } else {
            System.out.println("Stack is full !");
        }
    }

    public void pop() {
          if (top <= -1) {
            System.out.print("\nStack is empty");
            return;
        }
        
           System.out.print("\nPOPPING ELEMENT\n" + arr[top]);
            top--;

        
    }

    public String peek() {
        if (!this.isEmpty()) {
            return arr[top];
        } else {
            System.out.println("Stack is Empty");
            return null;
        }
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (MAX - 1 == top);
    }

    void printOriginalOrder() {
        if (top == -1) {
            System.out.println("Stack is Empty");
        } else {
            System.out.println("Printing stack in Original Order:");
            for (int i = 0; i <= top; i++) {
                System.out.println("Item: " + arr[i]);
                System.out.println("\n");
            }
        }
    }

    void printReverseOrder() {
        if (top == -1) {
            System.out.println("Stack is Empty");
        } else {
            System.out.println("Printing stack in Reverse Order:");
            for (int i = top; i >= 0; i--) {
                System.out.println("Item: " + arr[i]);
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
        MyStackArray myStack = new MyStackArray();
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

        System.out.println("\nPushing all elements into stack");
        for (int i = 0; i < myStack.list.size(); i++) {
            myStack.push(myStack.list.get(i));
        }
        System.out.println();

        System.out.println("\nPoping all elements into stack");
        while (top > -1) {
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
