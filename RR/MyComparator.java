/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Comparator;

/**
 *
 * @author Rayhan
 */
public class MyComparator implements Comparator<Node>{

    @Override
    public int compare(Node a,Node b) {
        
        if(a.f_cost>b.f_cost)
        {
            return 1;
        }
        else if(a.f_cost<b.f_cost)
        {
            return -1;
        }
        return 0;
    }
    
}
