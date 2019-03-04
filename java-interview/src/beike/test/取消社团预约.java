package beike.test;

/*
取消社团预约：
在中国某大学内，教室资源十分紧张，有n个社团同时申请了在某一天使用同一个教室，假设第i个社团占用该
教室的时间为[li, ri]，根据学校规定，教务部门必须且最多取消一个社团的预约，来满足另外的n-1个社团
的需求，问学校有多少种取消的方案，(若两个社团占用的时间分别为[l1,r1]和[l2,r2]，此时若
r1=l2，视为时间不冲突)

输入：
第一行包含一个整数n，表示社团的数量
接下来的n行，每行包含两个整数，表示第i个社团占用该教室的时间[li,ri]

输出：
第一行包含一个整数m，表示可以删除社团的数量
第二行包含m个整数，分别为可删除社团的编号(从小到大排序)
(如果不删除一个社团，则不能作为一种方案)

样例输入：
3
3 10
20 30
1 3

样例输出：
3
1 2 3

样例输入：
4
3 10
20 30
1 3
1 39

输出：
1
4
**/


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class 取消社团预约 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            ArrayList<Bind> arrayList = new ArrayList<>();
            int n = in.nextInt();
            for(int i=0;i<n;i++){
                int timel = in.nextInt();
                int timer = in.nextInt();
                arrayList.add(new Bind(timel,timer));
            }
            Collections.sort(arrayList);
//            display(arrayList);
            ArrayList<Integer> arrayList1 = new ArrayList<>();
            int count = 0;
            for(int i=0;i<n;i++){
                arrayList.remove(i);
                int j;
                for(j=0;j<n-2;j++){
                    if(arrayList.get(j).timer>arrayList.get(j+1).timel){
                        break;
                    }
                }
                if(j==n-3){
                    count++;
                    arrayList1.add(i);
                }
            }
            System.out.println(count);
            for(int i=0;i<arrayList1.size();i++){
                System.out.print(arrayList1.get(i)+" ");
            }
        }
    }

    public static void display(ArrayList<Bind> arrayList){
        for(Bind bind : arrayList){
            System.out.println(bind.timel+"========="+bind.timer);
        }
    }
}

class Bind implements Comparable<Bind>{
    int timel;
    int timer;

    public Bind(int timel, int timer){
        this.timel = timel;
        this.timer = timer;
    }

    public int compareTo(Bind o){
        return this.timel-o.timel;
    }
}
