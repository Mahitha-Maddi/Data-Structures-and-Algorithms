/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mahit
 */
public class Krusks {
    static class EDGE implements Comparable<EDGE>{
        int from, to;
        int weight;
        EDGE(int f, int t, int w){
            from = f;
            to = t;
            weight = w; 
        }  

        @Override
        public int compareTo(EDGE o) {
            return this.weight-o.weight;
        } 
        
        @Override
        public String toString(){
            return "["+from+", "+to+"]";
        }
    }
    
    private static Map<Integer, Integer> PARENT;
    private static Map<Integer, Integer> RANKS; //to store the depths
    
    public static void initialize(int[] universe){ 
        PARENT = new HashMap<>();
        RANKS = new HashMap<>();
        for(int x:universe){
            PARENT.put(x, x);
            RANKS.put(x, 1);
        } 
    }
    
    public static int FindSet(int item){
        int parent = PARENT.get(item); 
        if(parent==item)return item;
        else return FindSet(parent);
    }
    
    public static void Union(int setA, int setB){
        int pA, pB;
        while((pA = PARENT.get(setA))!=setA){setA = pA;}
        while((pB = PARENT.get(setB))!=setB){setB = pB;}
        
        int rankFirst = RANKS.get(setA), rankSecond = RANKS.get(setB);
        if(rankFirst>rankSecond){
            PARENT.put(setB, setA);  
            updateRanksUpward(setB);
        }else if(rankSecond>rankFirst){
            PARENT.put(setA, setB);  
            updateRanksUpward(setA);
        }else{
            PARENT.put(setB, setA); 
            updateRanksUpward(setB);
        }
    }
    
    public static void updateRanksUpward(int current){


        int currentDepth = RANKS.get(current);
        int currentsParent = PARENT.get(current);
        int parentsDepth = RANKS.get(currentsParent);
        if(!(currentDepth<parentsDepth || currentsParent == current)){ 
            RANKS.put(currentsParent, currentDepth+1);
            updateRanksUpward(currentsParent);
        }
    } 
        //CLSR p631 Algorithm
    public static ArrayList<EDGE> Kruskal(int[] vertices, EDGE[] edges){  
            //Initialize A = empty set
            ArrayList<EDGE> mst = new ArrayList<>();
            
            //for each vertex v belongs to G.V MAKE-SET(v)
            initialize(vertices);
             
            //sort the edges of G.E into non decreasing order by weight w
            Arrays.sort(edges);
            
            //For each edge (u,v) belongs to G.E taken in non decreasing order by weight
            for(EDGE edge:edges){
                //If (find-set(u)!=find-set(v)
                if(FindSet(edge.from)!=FindSet(edge.to)){
                    //A = A union (u, v)
                    mst.add(edge);
                    //UNION(u, v)
                    Union(edge.from, edge.to);
                }
            }             
            //Display contents
            System.out.println("MST contains the edges: "+mst);
            return mst;
    }
    
    
    public static void main(String[] args){
        //Test data
        //CLRS Example p632
        int[] vertices = new int[]{1,2,3,4,5,6,7}; 
        EDGE[] edges = new EDGE[9];
        edges[0] = new EDGE(1,4,3);
        edges[1] = new EDGE(4,7,2);
        edges[2] = new EDGE(7,3,4);
        edges[3] = new EDGE(2,4,4);
        edges[4] = new EDGE(5,7,2);
        edges[5] = new EDGE(2,3,5);
        edges[6] = new EDGE(4,6,5);
        edges[7] = new EDGE(6,7,1);
        edges[8] = new EDGE(5,6,2);
        //edges[9] = new EDGE(2,6,70);
      
        
        //Call Kruskal Algorithm
        Kruskal(vertices, edges);
    }
    

}
