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
public class NewClass3 {
        public static int solution(String S) {
        // write your code in Java SE 8
    Stack<Integer> stk = new Stack<Integer>();       
    String[] split = S.split(" ");
    int max = ((int)Math.pow(2,20))-1;
    
    for(String str : split)
    { 
        if(str.equals("DUP"))
        {
            if(stk.size() ==0) return -1;
            stk.push(stk.peek());
        }
        else if(str.equals("POP"))
        {
            if(stk.size() ==0) return -1;
            stk.pop();
        }
        else if(str.equals("+")|| str.equals("-"))
        {
            if(stk.size() < 2)return -1;
            int top1 = stk.pop();
            int top2= stk.pop();
            if(str=="+")
            {
                int sum = top1+top2;
                if(max < sum) return -1;//overflows
                stk.push(sum);
            }
            else
            {
                int sub = top1-top2;
                if(sub < 0)return -1;//underflows
                stk.push(sub);
            }
        }
        else
        {
          
                stk.push(Integer.valueOf(str));
        }
         
    }
    
    return stk.size() == 0?-1:stk.peek();
    }
        
        
        
         public static void main(String[] args) {
        // TODO code application logic here
//System.out.println(solution("4 5 6 - 7 +"));
double max = (Math.pow(2,20));
System.out.println(max);

StringBuilder sb=new StringBuilder();
StringBuffer sbf=new StringBuffer();

    }
}
