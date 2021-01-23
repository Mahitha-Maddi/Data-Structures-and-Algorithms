/*
 * Shiva Mahitha Maddi
 * 001061161
 */
package edu.neu.coe.info6205.functions;

import java.util.Stack;

/**
 *
 * @author mahit
 */
public class InfixEvaluation1 {

    public int evaluate(String expression){
        //Stack for Numbers
        Stack<Integer> numbers = new Stack<>();

        //Stack for operators
        Stack<Character> operations = new Stack<>();
        
        
        for(int i=0; i<expression.length();i++) {
            char c = expression.charAt(i);
            //check if it is number
            if(Character.isDigit(c)){
                //Entry is Digit, it could be greater than one digit number
                int num = 0;
                while (Character.isDigit(c)) {
                    num = num*10 + (c-'0');
                    i++;
                    if(i < expression.length())
                        c = expression.charAt(i);
                    else
                        break;
                }
                i--;
                //push it into stack
                numbers.push(num);
            }else if(c=='('){
                //push it to operators stack
                operations.push(c);
            }
            //Closed brace, evaluate the entire brace
            else if(c==')') {
                while(operations.peek()!='('){
                    int output = performOperation(numbers, operations);
                    //push it back to stack
                    numbers.push(output);
                }
                operations.pop();
            }
            // current character is operator
            else if(isOperator(c)){
                //1. If current operator has higher precedence than operator on top of the stack,
                //the current operator can be placed in stack
                // 2. else keep popping operator from stack and perform the operation in  numbers stack till
                //either stack is not empty or current operator has higher precedence than operator on top of the stack
                while(!operations.isEmpty() && precedence(c)<=precedence(operations.peek())){
                    int output = performOperation(numbers, operations);
                    //push it back to stack
                    numbers.push(output);
                }
                //now push the current operator to stack
                operations.push(c);
            }
        }
        //If here means entire expression has been processed,
        //Perform the remaining operations in stack to the numbers stack

        while(!operations.isEmpty()){
            int output = performOperation(numbers, operations);
            //push it back to stack
            numbers.push(output);
        }
        return numbers.pop();
    }

    static int precedence(char c){
        switch (c){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    public int performOperation(Stack<Integer> numbers, Stack<Character> operations) {
        int a = numbers.pop();
        int b = numbers.pop();
        char operation = operations.pop();
        switch (operation) {
            case '+':
                return a + b;
            case '-':
                return b - a;
            case '*':
                return a * b;
            case '/':
                if (a == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                return b / a;
        }
        return 0;
    }

    public boolean isOperator(char c){
        return (c=='+'||c=='-'||c=='/'||c=='*'||c=='^');
    }

    public static void main(String[] args) {
        //String infixExpression = "2 * (5 *(3+6))/15-2";
        InfixEvaluation1 i = new InfixEvaluation1();
        //System.out.println(i.evaluate(infixExpression));
        
        
        System.out.println(i.evaluate("1*2/3+(4+5-(6*(7/8)))")); 
        System.out.println(i.evaluate("4 + 8 * 6 - 5 / 3 - 2 * 2 + 2")); 
        
        System.out.println(i.evaluate("(1 + 3 + ( ( 4 / 2 ) * ( 8 * 4 ) )) ")); 
        
        System.out.println(i.evaluate("(4+8)*( 6 - 5 ) / ( ( 3 - 2 ) * ( 2 + 2 ) )")); 
    }
}

