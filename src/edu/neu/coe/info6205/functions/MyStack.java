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
public class MyStack {

    static final int maxSize = 50;
    private static String[] stackArray;
    private int top;

    public MyStack() {
        stackArray = new String[maxSize];
        top = -1;
    }

    public void push(String j) {
        if (!isFull()) {
            top++;
            stackArray[top] = j;
            System.out.println("Pushed element:" + j);
        } else {
            System.out.println("Stack is full !");
        }
    }

    public void pop() {
        if (top < 0) {
            System.out.println("Stack is empty");
            return;
        }
        String str = stackArray[0];
        for (int i = 0; i < stackArray.length - 1; i++) {
            stackArray[i] = stackArray[i + 1];
        }
        System.out.println("Item popped: " + str);
        top--;
        return ;
    }

    public String peek() {
        return stackArray[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }

    public void printStack() {
        if (isEmpty()) {
            return;
        }
        for (String s : MyStack.stackArray) {
            if (s != null) {
                System.out.println(s);
            }
        }
    }

    public static void main(String[] args) {
        String sentence = "It was the best of time";
        String[] array = sentence.split(" ");
        
        MyStack theStack = new MyStack();
        
        
        System.out.println();
        System.out.println("Stack isEmpty: " + theStack.isEmpty());
        System.out.println();
        System.out.println("\nPushing all elements into stack\n");
        for (String element : array) {
                        theStack.push(element);
		}
      /*  
        System.out.println("\nPushing all elements into stack\n");
        for (int i = 0; i >= array.length - 1; i++) {
            theStack.push(array[i]);
        }
        */
        System.out.println();
        System.out.println("Stack isEmpty: " + theStack.isEmpty());
        System.out.println();
        System.out.println("\n Reading back elements from stack\n");
        while (!theStack.isEmpty()) {

           theStack.pop();
        }
        System.out.println();
        System.out.println("Stack isEmpty: " + theStack.isEmpty());
        System.out.println();
        theStack.pop();
    }

}
