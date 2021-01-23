/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mahit
 */
public class NewClass {
    public void fib(int n){
int i=0;
int j=1;
int sum;
System.out.println(i);
System.out.println(j);
while(j<n){
sum=i+j;
i=j;
j=sum;
System.out.println(j);
}

}
    
     public static void main(String[] args) {
        NewClass myQueue = new NewClass();
        myQueue.fib(5);
}
}
