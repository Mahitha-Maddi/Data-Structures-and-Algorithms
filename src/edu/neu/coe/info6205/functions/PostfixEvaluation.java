/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.functions;

import java.util.Stack;

/**
 *
 * @author mahit
 */
public class PostfixEvaluation {
    int evaluatePostfix(String expression)
{
	// Declaring a Stack from Standard template library in C++. 
	Stack<Integer> S =new Stack<>();

	for(int i = 0;i< expression.length();i++) {
            
            char c = expression.charAt(i);

		// Scanning each character from left. 
		// If character is a delimitter, move on. 
		if(c == ' ' || c == ',') continue; 

		// If character is operator, pop two elements from stack, perform operation and push the result back. 
		else if(isOperator(c)) {
			// Pop two operands. 
			int operand2 = S.peek(); S.pop();
			int operand1 = S.peek(); S.pop();
			// Perform operation
			int result = performOperation(c, operand1, operand2);
			//Push back result of operation on stack. 
			S.push(result);
		}
		else if(isNumericDigit(c)){
			// Extract the numeric operand from the string
			// Keep incrementing i as long as you are getting a numeric digit. 
			/*int operand = 0; 
			while(i<expression.length() && isNumericDigit(expression.charAt(i))) {
				// For a number with more than one digits, as we are scanning from left to right. 
				// Everytime , we get a digit towards right, we can multiply current total in operand by 10 
				// and add the new digit. 
				operand = (operand*10) + (expression.charAt(i) - '0'); 
				i++;
			}
			// Finally, you will come out of while loop with i set to a non-numeric character or end of string
			// decrement i because it will be incremented in increment section of loop once again. 
			// We do not want to skip the non-numeric character by incrementing i twice. 
			i--;
                        

			// Push operand on stack. 
			S.push(operand);
                        */
                        
                        S.push(expression.charAt(i)-'0');
		}
	}
	// If expression is in correct format, Stack will finally have one element. This will be the output. 
	return S.peek();
}

// Function to verify whether a character is numeric digit. 
boolean isNumericDigit(char C) 
{
	if(C >= '0' && C <= '9') return true;
	return false;
}

// Function to verify whether a character is operator symbol or not. 
boolean isOperator(char C)
{
	if(C == '+' || C == '-' || C == '*' || C == '/')
		return true;

	return false;
}

// Function to perform an operation and return output. 
int performOperation(char operation, int operand1, int operand2)
{
	if(operation == '+') return operand1 + operand2;
	else if(operation == '-') return operand1 - operand2;
	else if(operation == '*') return operand1 * operand2;
	else if(operation == '/')
        { if (operand2 == 0){
                  throw new UnsupportedOperationException("Cannot divide by zero");
                  
        }
            return operand1 / operand2;
        }
	else System.out.println("Unexpected Error \n");
	return -1; 
}

  public static void main(String[] args) {
        //String infixExpression = "2 * (5 *(3+6))/15-2";
        PostfixEvaluation p = new PostfixEvaluation();
        //System.out.println(i.evaluate(infixExpression));
        
        
        System.out.println("Postfix evaluation for the expression 48+65-*32-22+*/ is " + p.evaluatePostfix("48+65-*32-22+*/"));
    }

}
