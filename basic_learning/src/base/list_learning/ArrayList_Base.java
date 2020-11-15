package base.list_learning;

import java.util.ArrayList;
import java.util.List;

/**
 * private static final int DEFAULT_CAPACITY = 10;  默认初始容量10
 * private static final Object[] EMPTY_ELEMENTDATA = {};  空的列表
 * private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
 * transient Object[] elementData;
 * private int size;
 */
public class ArrayList_Base {
    public static void main(String[] args){
//        constructs();
//        contains();
//        cloneTest();
        getTest();
    }

    /**
     * public ArrayList(int initialCapacity) {
     *         if (initialCapacity > 0) {
     *             this.elementData = new Object[initialCapacity];
     *         } else if (initialCapacity == 0) {
     *             this.elementData = EMPTY_ELEMENTDATA;
     *         } else {
     *             throw new IllegalArgumentException("Illegal Capacity: "+
     *                                                initialCapacity);
     *         }
     *     }
     */
    public static void constructs() {
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> arrayList1 = new ArrayList<>(1);
    }

    /**
     * public boolean contains(Object o) {
     *         return indexOf(o) >= 0;
     *     }
     */
    public static void contains() {
        ArrayList<String> list = new ArrayList<>();
        list.add("11");
        list.add("12");
        list.add("13");
        list.add("14");
        list.add("15");
        boolean flag = list.contains("15");
        System.out.println(flag);
        System.out.println(list.indexOf("15"));
    }

    /**
     * clone
     */
    public static void cloneTest() {
        ArrayList<String> list = new ArrayList<>();
        list.add("11");
        list.add("12");
        list.add("13");
        list.add("14");
        list.add("15");
        ArrayList<String> listClone = (ArrayList<String>)list.clone();
        System.out.println(list);
        System.out.println(listClone);
    }

    /**
     * get和set,add
     * add(int index, E element):大于index的数值向后移动一位
     */
    public static void getTest() {
        ArrayList<String> list = new ArrayList<>();
        list.add("11");
        list.add("18");
        list.add("13");
        list.add("14");
        list.add("15");
        list.add("16");
        list.add("17");
        list.add("18");
        list.add("19");
        list.add("20");
        list.add("21");
        list.add(5, "19");
        list.remove("18");
//        list.clear();
        System.out.println(list);
    }
}
