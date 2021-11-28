/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Arrays;

/**
 *
 * @author Rayhan
 */
public class State {

    int matrix[][];
    int size;

    public State(int n) {
        matrix = new int[n+2][n+2];
        this.size = n;
        int x=1;
        for(int i=1;i<=this.size;i++)
        {
            for(int j=1;j<=this.size;j++)
            {
                matrix[i][j]=x++;
            }
        }
        matrix[this.size][this.size]=0;
    }

    public State(State s, int x, int y, int i, int j) {
        matrix=new int[s.size+2][s.size+2];
        size=s.size;
        for(int k=1;k<=size;k++)
        {
            matrix[k]=Arrays.copyOf(s.matrix[k], s.matrix[k].length);
        }
        int temp=matrix[i][j];
        matrix[i][j]=matrix[x][y];
        matrix[x][y]=temp;
    }

    State(int[][] matrix, int n) {
        this.matrix=new int[n+2][n+2];
        for (int i = 1; i <= n; i++) {
            this.matrix[i]=Arrays.copyOf(matrix[i], matrix[i].length);
        }
        this.size=n;
    }
    
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        for(int i=1;i<=this.size;i++)
        {
            for(int j=1;j<=this.size;j++)
            {
                if((other.matrix[i][j] - this.matrix[i][j])!=0)
                {
                    return false;
                }
            }
        }
        return true;
    }
    public int CountInversionHelper()
    {
        int[] arr;
        arr = new int[this.size*this.size +5];
        int id = 0;
        for (int i = 1; i <=this.size; i++) {
            for (int j = 1; j <=this.size; j++) {
                arr[id++] = this.matrix[i][j];
            }
        }
//        for(int i=0;i<this.size*this.size;i++)
//        {
//            System.out.print(arr[i] +" ");
//        }
        int invc = getInvCount(arr, this.size*this.size);
        //System.out.println(invc);
        return invc;
    }

    int getInvCount(int arr[], int n) {
        int inv_count = 0;
        for (int i = 0; i <n-1; i++) {
            for (int j = i + 1; j < n; j++) {
                if(arr[i]==0 || arr[j]==0)continue;
                if (arr[i] > arr[j]) {
                    inv_count++;
                }
            }
        }

        return inv_count;
    }
    @Override
    public int hashCode()
    {
        int deep=Arrays.deepHashCode(this.matrix);
        return Math.abs(deep);
        //return deep;
    }
    
    void printstate()
    {
        for(int i=1;i<=this.size;i++)
        {
            for(int j=1;j<=this.size;j++)
            {
                if(this.matrix[i][j]==0)
                {
                    
                    System.out.print("   ");
                }
                else
                {
                    System.out.print(this.matrix[i][j]+"  ");
                }
                
            }
            System.out.println("");
        }
    }
}
