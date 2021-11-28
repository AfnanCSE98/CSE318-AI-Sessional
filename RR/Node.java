/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Rayhan
 */
public class Node {
    State nodeState;
    int g_cost;
    int h_cost;
    int f_cost;
    Node parent;

    public Node(State nodeState, int g_cost,int h_cost) {
        this.nodeState = nodeState;
        this.g_cost = g_cost;
        this.h_cost=h_cost;
        this.f_cost=this.g_cost+this.h_cost;
    }
}
