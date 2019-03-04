package beike.test;

/*
* 玥玥带乔乔一起逃亡，现在有许多的东西要放到乔乔的包里面，但是包的大小有限，所以我们只能够在里面放入非常重要的物品。
* 现在给出该种物品的数量、体积、价值的数值，希望你能够算出怎样能使背包的价值最大的组合方式，并且输出这个数值，乔乔会非常感谢你。
*
* 输入
第1行有2个整数，物品种数n和背包装载体积v；
第2行到i+1行每行3个整数，为第i种物品的数量m、体积w、价值s。

输出
仅包含一个整数，即为能拿到的最大的物品价值总和。
样例说明：选第一种一个，第二种两个，结果为3x1+5x2=13。

输入示例：
2 10
3 4 3
2 2 5

输出示例：
13
* */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

public class Main2 {
    public static ArrayList<String> res = new ArrayList<>();

    public static void dfs(String s, int index, String cur_string, Set<String> dict){

        if(index<=0){
            if(cur_string.length()>0){
                res.add(cur_string.substring(0,cur_string.length()-1));
            }
        }

        for(int i=index; i>=0; i--){
            if(dict.contains(s.substring(i,index))){
                dfs(s,i,s.substring(i,index)+" "+cur_string,dict);
            }
        }
    }
    public static void main(String[] args){


    }

    public static ArrayList<String> wordBreak(String s, Set<String> dict){
        dfs(s,s.length(),"",dict);
        return res;
    }

}
