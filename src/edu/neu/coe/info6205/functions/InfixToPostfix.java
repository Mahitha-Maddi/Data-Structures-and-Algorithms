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
public class InfixToPostfix {
    
    String infixToPostfix(String expression)
{
	// Declaring a Stack from Standard template library in C++. 
	Stack<Character> S =new Stack<>();
	String postfix = ""; // Initialize postfix as empty string.
        
	for(int i = 0;i< expression.length();i++) {
            Character c= expression.charAt(i);
		// Scanning each character from left. 
		// If character is a delimitter, move on. 
		if(expression.charAt(i) == ' ' || expression.charAt(i) == ',') continue; 

		// If character is operator, pop two elements from stack, perform operation and push the result back. 
		else if(isOperator(expression.charAt(i))) 
		{
			while(!S.empty() &&  precedence(c)<=precedence(S.peek()))
			{
				postfix+= S.peek();
				S.pop();
			}
			S.push(expression.charAt(i));
		}
		// Else if character is an operand
		else if(isOperand(expression.charAt(i)))
		{
			postfix +=expression.charAt(i);
		}

		else if (expression.charAt(i) == '(') 
		{
			S.push(expression.charAt(i));
		}

		else if(expression.charAt(i) == ')') 
		{
			while(!S.empty() && S.peek() !=  '(') {
				postfix += S.peek();
				S.pop();
			}
			S.pop();
		}
	}

	while(!S.empty()) {
		postfix += S.peek();
		S.pop();
	}

	return postfix;
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

// Function to verify whether a character is english letter or numeric digit. 
// We are assuming in this solution that operand will be a single character
boolean isOperand(char C) 
{
	if(C >= '0' && C <= '9') return true;
	if(C >= 'a' && C <= 'z') return true;
	if(C >= 'A' && C <= 'Z') return true;
	return false;
}

// Function to verify whether a character is operator symbol or not. 
boolean isOperator(char C)
{
	if(C == '+' || C == '-' || C == '*' || C == '/' || C== '^')
		return true;

	return false;
}

// Function to verify whether an operator is right associative or not. 
  /*
    boolean isRightAssociative(char op)
{
	if(op == '$') return true;
	return false;
}

// Function to get weight of an operator. An operator with higher weight will have higher precedence. 
  
int getOperatorWeight(char op)
{
	int weight = -1; 
	switch(op)
	{
	case '+':
	case '-':
		weight = 1;
	case '*':
	case '/':
		weight = 2;
	case '$':
		weight = 3;
	}
	return weight;
}

// Function to perform an operation and return output. 

    boolean hasHigherPrecedence(char op1, char op2)
{
	int op1Weight = getOperatorWeight(op1);
	int op2Weight = getOperatorWeight(op2);

	// If operators have equal precedence, return true if they are left associative. 
	// return false, if right associative. 
	// if operator is left-associative, left one should be given priority. 
	/*if(op1Weight == op2Weight)
	{
		if(IsRightAssociative(op1)) return false;
		else return true;
	}
	return op1Weight > op2Weight ?  true: false;
}*/
    
    public static void main(String[] args) {
        InfixToPostfix i=new InfixToPostfix();
		String exp = "(4 + 8) * (6 - 5)/((3 - 2) * (2 + 2))"; 
        System.out.println("Postfix conversion of the infix expression (4 + 8) * (6 - 5)/((3 - 2) * (2 + 2)) is " + i.infixToPostfix(exp)); 

	}

}
