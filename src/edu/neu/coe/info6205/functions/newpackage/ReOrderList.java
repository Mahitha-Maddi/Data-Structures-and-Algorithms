/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.functions.newpackage;

import java.util.Scanner;

/**
 *
 * @author mahit
 */

public class ReOrderList {
    public class ListNode {
    int val;
ListNode next;
ListNode() {}
 ListNode(int val) { this.val = val; }
ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
     ListNode newHead;
    public void reorderList(ListNode head) {
        
        if(head==null){
            return;
        }
        ListNode temp=head;
        int size=0;
        while(temp!=null){
            temp=temp.next;
            size++;
        }
        int n=size;
        if(n%2==0){
             n=n/2;
        }
        else{
            n=(int)(n/2)+1;
        }
        ListNode temp1=head;
        while(n>0){
            temp1=temp1.next;
            n--;
        }
        ListNode list2=temp1.next;temp1.next=null;newHead=list2;
        reverse(list2);ListNode temp3=head; ListNode t;ListNode list3;
        list2=newHead;
        while(temp3!=null){
            t=temp3.next;
            temp3.next=list2;
            temp3=t; 
            if(list2!=null){list3=list2.next;
                           list2.next=t;
                           list2=list3;}
         }
        
    }
    
    ListNode reverse(ListNode list2){
        if(list2.next==null){
            newHead=list2;return list2;
        }
        ListNode temp=reverse(list2.next);
        temp.next=list2;
        return list2;
    }
     public static void main (String[] args) {
		//code
		
     }
}
