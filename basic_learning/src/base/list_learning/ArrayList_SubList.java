package base.list_learning;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * new SubList(this, 0, fromIndex, toIndex)
 * this.parent = parent: ArrayList表的对象
 * this.parentOffset = fromIndex：开始的索引
 * this.offset = offset + fromIndex;开始的索引
 * this.size = toIndex - fromIndex;SubList的长度
 * this.modCount = ArrayList.this.modCount; 修改的数量（ArrayList的长度）
 */
public class ArrayList_SubList {
    public static void main(String[] args){
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("11");
        arrayList.add("12");
        arrayList.add("13");
        arrayList.add("14");
        arrayList.add("15");
        arrayList.add("16");
        arrayList.add("17");
        arrayList.add("18");
        arrayList.add("19");

        List<String> subList = arrayList.subList(0,3);

        System.out.println(subList);


    }
}
