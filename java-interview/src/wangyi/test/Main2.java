package wangyi.test;

/*
* 问题描述：
* 小易在玩俄罗斯方块，屏幕上有n列，每次都有一个1*1的方块落下，在同一列上，后落的方块会落在之前
* 的方块之上，当一整行方块被沾满时，这一行就消失，并且会得到1分，
* 有一天，小易又开始了一局游戏，当玩到第m个方块的时候他就把游戏关掉了，小易希望你告诉他他获得了多少分
*
* 输入：第一行为n和m，n表示列数，m表示方块个数
* 第二行；为m个数，为每个方块所在的列数
*
* 输出：总得分
*
* 示例：
* 输入：
* 3 9
* 1 1 2 2 2 3 1 2 3
*
* 输出：
* 2
* */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int n = in.nextInt();
            int m = in.nextInt();

            ArrayList<Integer> arrayList = new ArrayList<>();
            HashMap<Integer,Integer> hashMap = new HashMap<>();
            for(int i=0;i<m;i++){
                int num = in.nextInt();
                if(hashMap.containsKey(num)){
                    int count = hashMap.get(num);
                    hashMap.put(num,++count);
                }else{
                    hashMap.put(num,1);
                }

            }

            for(int key : hashMap.keySet()){
                int value = hashMap.get(key);
                arrayList.add(value);
            }
            Collections.sort(arrayList);

            if(arrayList.size()!=n){
                System.out.println(0);
            }else{
                System.out.println(arrayList.get(0));
            }




        }
    }
}
