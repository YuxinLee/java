package jindong.test;

/*
*1.问题：
* 小B想去购物，总是习惯性的把要买的东西列在一个购买清单上，每个物品单独列一行（即便要买多个某种物品）
*这天小B早早的来到了商店，由于她太激动，以至于她到达商店的时候，服务员还没有把各个商品的价签排好，
* 所有的价签还都在柜台上。因此还需要一段时间，等服务器把价签放到对应的商品处，小B才能弄清她的购买清单所需的费用.
* 小B都有些迫不及待了，她希望你能够根据购买清单，帮她算算最好和最坏的情况下所需的费用，你能帮她吗？
*
* 2.输入：
* 输入中有多组测试数据。每组测试数据的第一行为两个整数n和m（1=＜n, m=＜1000），分别表示价签的数量以及
* 小B的购买清单中所列的物品数。第二行为空格分隔的n个正整数，表示货架上各类物品的价格，每个数的大小不超过100000。
* 随后的m行为购买清单中物品的名称，所有物品名称为非空的不超过32个拉丁字母构成的字符串，
* 保证清单中不同的物品种类数不超过n，且商店有小B想要购买的所有物品。
*
* 3.输出：
* 对每组测试数据，在单独的行中输出两个数a和b，表示购买清单上所有的物品可能需要的最小和最大费用。
*
* 4.测试用例：
* 样例输入
*5 3
*4 2 1 10 5
*apple
*orange
*mango
*6 5
*3 5 1 6 8 1
*peach
*grapefruit
*banana
*orange
*orange
*
*样例输出
7 19
11 30
* */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int n = in.nextInt();
            int m = in.nextInt();
            ArrayList<Integer> arrayList = new ArrayList();
            HashMap<String, Integer> hashMap = new HashMap<>();
            for(int i=0;i<n;i++){
                arrayList.add(in.nextInt());
            }

            for(int i=0;i<m;i++){
                String good = in.next();
                if(hashMap.containsKey(good)){
                    int count = hashMap.get(good);
                    hashMap.put(good,++count);
                }else{
                    hashMap.put(good,1);
                }
            }
            solution(arrayList,hashMap);
        }
    }

    public static void solution(ArrayList<Integer> arrayList, HashMap<String, Integer> hashMap){
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(hashMap.values());
        Collections.sort(list);
        Collections.sort(arrayList);
        int min = 0;
        int max = 0;
        for(int i=0;i<list.size();i++){
            min += list.get(list.size()-i-1) * arrayList.get(i);
            max += list.get(list.size()-i-1) * arrayList.get(arrayList.size()-1-i);
        }

        System.out.println(min+" "+max);
    }
}
