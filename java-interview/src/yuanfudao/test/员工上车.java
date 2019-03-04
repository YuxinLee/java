package yuanfudao.test;

/*
* 假设大巴的容量为m，从队首开始，每m个人分为一个小组，每个小组坐一辆车，
* 例如：员工数8，车容量3，员工到达的顺序为1 2 3 4 5 6 7 8
* 3个人一个小组，分别为：小组一：1 2 3，小组二：4 5 6， 小组三： 7 8
* 小组上车的顺序为小组三，二，一。所有员工的上车顺序为7 8 4 5 6 1 2 3
*
* 输入：
* 第一行：员工数和大巴的容量
* 第二行：所有员工工号(按到达顺序)
*
* 输出：
* 员工工号
*
* 示例：
* 输入：
* 5 3
* 1 3 5 2 4
*
* 输出：
* 2 4 1 3 5
*
* */
import java.util.Scanner;
import java.util.ArrayList;

public class 员工上车 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            ArrayList<Integer> arrayList = new ArrayList<>();
            int n = in.nextInt();
            int m = in.nextInt();
            for(int i=0;i<n;i++){
                arrayList.add(in.nextInt());
            }

            int count = n / m;

            for(int i=count*m;i<n;i++){
                System.out.print(arrayList.get(i)+" ");
            }

            for(int i=count-1;i>=0;i--){
                for(int j=i*m;j<i*m+m;j++){
                    if(j==m-1){
                        System.out.print(arrayList.get(j));
                        break;
                    }
                    System.out.print(arrayList.get(j)+" ");
                }
            }
        }
    }
}
