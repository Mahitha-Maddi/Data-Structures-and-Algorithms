/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.functions.newpackage;

/**
 *
 * @author mahit
 */
public class Wordsearch {
     public static boolean exist(char[][] board, String word) {
        int m=board.length;
        int n=board[0].length;
        //flag=false;
        Boolean[][] visited=new Boolean[m][n];
         for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                visited[i][j]=false;
            }
         }
        boolean flag1=false;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(word.charAt(0)==board[i][j]){
                    visited[i][j]=true;
                    flag1=dfs(board,i,j,word,""+word.charAt(0),visited);
                    if(flag1==true){
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
   static int[] p={0,0,-1,1};
    static int[] q={-1,1,0,0};
    static boolean  dfs(char[][] board,int x,int y,String word,String str,Boolean[][] visited){
        if(word.length()==str.length()){
            if(word.equals(str)){
                return true;
            }
            return false;
        }
           
        for(int i=0;i<2;i++){
            int k=x+p[i],l=y+q[i];
            if(k>=0 && k<board.length && l>=0 && l<board[0].length && visited[k][l]==false){
                visited[k][l]=true;
                String btr=str+board[k][l];
                boolean flag= dfs(board,k,l,word,btr,visited);
                if(flag==true){
                    return true;
                }
                
            }
        }
                visited[x][y]=false;
       
        return false;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        char[][] board={{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        
     String word="ABCCED";
System.out.println(exist(board,  word));
    }
}
