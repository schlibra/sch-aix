package com.sch.sudoku;

import java.util.Random;

@SuppressWarnings("all")
public class SudokuCreater {
    private int[][] Arr;
    private int[][] Sudoku;
    private int[][] Answer;
    private int[][] Game;
    public SudokuCreater(){
        this.Arr = new int[9][9];
        this.Sudoku = new int[9][9];
        this.Answer = new int[9][9];
        rand();
        DFS(Arr,0,false);
        diger();
    }
    private void rand(){
        int t = 0;
        int x,y,num;
        while (t<15){
            x = new Random().nextInt(9);
            y = new Random().nextInt(9);
            num = new Random().nextInt(9);
            if(Arr[y][x] == 0){
                if(isTrue(Arr,x,y,num)){
                    Arr[y][x] = num;
                    t++;
                }
            }
        }
    }
    public static boolean isTrue(int[][] arr,int x,int y,int num){
        for(int i = (y/3)*3;i<(y/3+1)*3;i++){
            for(int j = (x/3)*3;j<(x/3+1)*3;j++){
                if(arr[i][j]==num){
                    return false;
                }
            }
        }
        for(int i = 0;i<9;i++){
            if((arr[i][x]==num||arr[y][i]==num)){
                return false;
            }
        }
        return true;
    }
    private boolean flag = false;
    int total = 0;
    private void DFS(int[][] arr,int n,boolean all){
        if(n<81){
            if(flag==true&&all==false){
                return;
            }
            if(arr[n/9][n%9]==0){
                for(int i=1;i<10;i++){
                    if(isTrue(arr,n%9,n/9,i)){
                        arr[n/9][n%9]=i;
                        DFS(arr,n+1,all);
                        arr[n/9][n%9]=0;
                    }
                }
            }else{
                DFS(arr,n+1,all);
            }
        }else{
            if(!all){
                flag=true;
                for(int i =0;i<9;i++){
                    for(int j =0;j<9;j++){
                        Sudoku[i][j]=arr[i][j];
                        Answer[i][j]=arr[i][j];
                    }
                }
            }else{
                for(int i=0;i<9;i++){
                    for(int j=0;j<9;j++){
                        if(arr[i][j]!=Answer[i][j]){
                            Game[i][j]=Answer[i][j];
                            i=10;
                            j=10;
                            break;
                        }
                    }
                }
            }
        }
    }
    private void diger(){
        int t = 55;
        Game=new int[9][9];
        while (t>0){
            int x = new Random().nextInt(9);
            int y = new Random().nextInt(9);
            if(Sudoku[y][x]!=0){
                Sudoku[y][x]=0;
                t--;
            }
        }
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                Game[i][j]=Sudoku[i][j];
            }
        }
        DFS(Sudoku,0,true);
    }
    public int[][] getArr(){
        return this.Game;
    }
    public int[][] getAnswer(){
        return this.Answer;
    }
}
