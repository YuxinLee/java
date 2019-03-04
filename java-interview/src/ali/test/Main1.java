package ali.test;

/*
*某互联网支付公司为推广收钱码，推出微客找大哥返佣模式。规则如下：一个用户注册为蚂蚁微客后，
* 可以从该公司获得不同的任务，完成任务后可以获得佣金奖励，每个任务的难度和奖励都不同。
*微客作为大哥可以召集小弟，然后分配任务给他完成。但是每个小弟的能力不同，任务难度高于小弟的能力的话，
* 小弟就无法完成任务。
*现有任务数组level[i]表示第i个任务的难度，commission[i]表示第i个任务的佣金。
*worker[j]表示第j个小弟的能力。 小弟的能力worker[j]只能完成小于或者等于woker[j]的任务。
*每个小弟最多安排一个任务，但是一个任务可以被几个小弟多次完成。
*作为大哥，能获得最大的佣金是多少?

*输入描述
*输入数据包含三行，
*第一行，数组level（level[i]表示任务i的难度）
*第二行，数组commission（commission[i]表示任务i的佣金）
*第三行，数组worker（woker[j]表示小弟j的工作能力）

*输出描述
*输出一个整数，即最大的收益。如果状态不对，或者收益不对，可以返回0

*输入样例
*level = [1,2,3,4,7]
*commission = [2,1,15,20,25]
*worker=[2,3,4,6]

*输出样例
*57
*
* */

import java.util.Map;
import java.util.TreeMap;

public class Main1{
    public static void main(String[] args){
        int[] level = {1,2,3,4,7};
        int[] commission = {2,1,15,20,25};
        int[] worker = {2,3,4,6};
        System.out.println(maxCommission(level,commission,worker));
    }

    public static Integer maxCommission(int[] level, int[] commission, int[] worker) {

        /*校验参数*/
        if (level.length != commission.length || level.length == 0  ) {
            return 0 ;
        }
        int m=0,n=0,p=0;
        if (level[m] > 0 && level[m] <Integer.MAX_VALUE && m < level.length ) {
            m++;
        } else {
            return 0;
        }

        if (commission[n] > 0 && level[n] <Integer.MAX_VALUE && n < commission.length ) {
            n++;
        } else {
            return 0;
        }

        if (worker[p] > 0 && level[p] <Integer.MAX_VALUE && p < worker.length ) {
            p++;
        } else {
            return 0;
        }

        /*level对应commission 放进一个 treemap（目的就是按照commission排序），然后从前往后遍历woker，每一个woker要优先考虑他能解决level的中对应钱最多   */
        TreeMap <Integer, Integer> treemap = new TreeMap<>();
        Integer maxcom = new Integer(0);

        for (int j = 0; j < level.length; j++) {
            treemap.put(commission[j],level[j]);
        }
        Map<Integer,Integer> map=treemap.descendingMap();
        for (int i = 0 ; i< worker.length;i++) {
            for(int key : map.keySet())
            {
                //System.out.println(key+"->"+map.get(key)+"worker"+worker[i]);
                if (worker[i] >= map.get(key)&& map.get(key) != 0) {
                    maxcom +=key;
                    //System.out.println("maxcom"+maxcom);
                    break;
                }

            }
        }
        return maxcom;
    }
}



