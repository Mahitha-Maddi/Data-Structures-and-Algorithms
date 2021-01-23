/*
 * Shiva Mahitha Maddi
 * 001061161
 */
package edu.neu.coe.info6205.functions;

import java.util.Arrays;

/**
 *
 * @author mahit
 */
public class TowersOfHanoi {

    static Stack src, dest, aux;
    static int count = 0;

    public TowersOfHanoi(int n) {

        this.src = createStack(n);
        for (int i = n; i >= 1; i--) {
            push(src, i);
        }
        this.dest = createStack(n);
        this.aux = createStack(n);
    }

    class Stack {

        int capacity;
        int top;
        int array[];
    }

    Stack createStack(int capacity) {
        Stack stack = new Stack();
        stack.capacity = capacity;
        stack.top = -1;
        stack.array = new int[capacity];
        return stack;
    }

    static boolean isFull(Stack stack) {
        return (stack.top == stack.capacity - 1);
    }

    static boolean isEmpty(Stack stack) {
        return (stack.top == -1);
    }

    static void push(Stack stack, int item) {
        if (isFull(stack)) {
            return;
        }
        stack.array[++stack.top] = item;
    }

    static int pop(Stack stack) {
        if (isEmpty(stack)) {
            return Integer.MIN_VALUE;
        }

        int top = stack.array[stack.top];
        stack.array[stack.top] = 0;
        stack.top--;
        return top;
    }

    void printStack() {

        if (src.top == -1) {
            System.out.println("Src stack: [0, 0, 0, 0, 0, 0, 0]");
        } else {
            System.out.println("Src stack:" + Arrays.toString(src.array));

        }

        if (aux.top == -1) {
            System.out.println("Aux stack: [0, 0, 0, 0, 0, 0, 0]");
        } else {
            System.out.println("Aux stack:" + Arrays.toString(aux.array));

        }

        if (dest.top == -1) {
            System.out.println("Dest stack: [0, 0, 0, 0, 0, 0, 0]");
        } else {
            System.out.println("Dest stack:" + Arrays.toString(dest.array));

        }

    }

    public void moveDisksBtwTwoPoles(char s, char d) {
        Stack src1 = new Stack();
        Stack dest1 = new Stack();

        if (s == 'S') {
            src1 = src;
        } else if (s == 'D') {
            src1 = dest;
        } else if (s == 'A') {
            src1 = aux;
        }
        //-------------------------------------------
        if (d == 'S') {
            dest1 = src;
        } else if (d == 'D') {
            dest1 = dest;
        } else if (d == 'A') {
            dest1 = aux;
        }

        //-------------------------------------------
        int pole1TopDisk = pop(src1);
        //int pole2TopDisk = pop(dest); 
        push(dest1, pole1TopDisk);

        moveDisk(s, d, pole1TopDisk);

    }

    public static void moveDisk(char fromPeg, char toPeg, int disk) {
        System.out.println("Move the disk " + disk
                + " from " + fromPeg + " to " + toPeg);
    }

    public void tohRecursive(int num_of_disks, char s, char d, char a) {
        int n = num_of_disks;
        try {
            if (n > 0) {
                if (n == 1) {
                    //System.out.println("Move disk 1 from rod " + s + " to rod " + d); 
                    moveDisksBtwTwoPoles(s, d);
                    count++;
                    printStack();

                    System.out.println("----------------------------------------");

                    return;
                }
                tohRecursive(n - 1, s, a, d);
                //System.out.println("Move disk " + n + " from rod " + s + " to rod " + d); 
                moveDisksBtwTwoPoles(s, d);
                count++;
                printStack();

                System.out.println("----------------------------------------");
                tohRecursive(n - 1, a, d, s);
            }
        } catch (Exception e) {

        }

    }

    public static void main(String[] args) {

        int noOfDisks = 7;

        TowersOfHanoi ob = new TowersOfHanoi(noOfDisks);

        System.out.println("Src stack:[7, 6, 5, 4, 3, 2, 1]");
        System.out.println("Aux stack: [0, 0, 0, 0, 0, 0, 0]");
        System.out.println("Dest stack:[0, 0, 0, 0, 0, 0, 0]");

        System.out.println("----------------------------------------");

        char s = 'S', d = 'D', a = 'A';

        ob.tohRecursive(noOfDisks, s, d, a);

        System.out.println("----------------------------------------");

        System.out.println("number of moves : " + ob.count);

    }

}
