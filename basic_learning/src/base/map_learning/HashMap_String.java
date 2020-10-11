package base.map_learning;

import java.util.HashMap;

/**
 * HashMap底层使用的是数组链表的形式
 * HashMap的主干是一个Entry数组，每个Entry包含一个key-value键值对，链表则是为了解决hash冲突而存在的
 * 添加数据时，首先根据hashCode计算出hash值，如果定位到的数组不含链表，那么查找和添加等操作的时间复杂度为O(1),
 * 如果存在链表的话时间复杂度为O(n)，需要遍历链表进行equlas核对，因此链表出现越少越好，但是此时会占据更多的内存空间。
 */
public class HashMap_String {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
//        String s1 = "诸葛亮";
//        String s2 = "刘备";
//        String s3 = "孙权";
//        String s4 = "诸葛亮";
//        hashMap.put(s1, "西蜀");
//        hashMap.put(s2, "西蜀");
//        hashMap.put(s3, "东吴");
//        hashMap.put(s4, "东蜀");
//        capacity();
//        constructs(hashMap);
//        put();
//        System.out.println(tableSizeFor(1000));
        get();
    }

    /**
     * HashMap容量源码学习
     * 默认的初始化容量为16：static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;，自定义的时候必须为2的幂
     * 容量最大值为2的30次方：static final int MAXIMUM_CAPACITY = 1 << 30;    // 设置大于此值的时候，会默认为2的30次方
     * 加载因子为0.75：static final float DEFAULT_LOAD_FACTOR = 0.75f;
     * 桶：hashmap的table数组
     * bin：挂在数组上的链表
     * 链表转化为红黑树的阈值大于8：static final int TREEIFY_THRESHOLD = 8;
     * 红黑树转化为链表的阈值小于6：static final int UNTREEIFY_THRESHOLD = 6;
     * 最小树形化容量阈值：即 当哈希表中的容量 > 该值时，才允许树形化链表 （即 将链表 转换成红黑树）：static final int MIN_TREEIFY_CAPACITY = 64;
     *
     * Node<K,V>继承自Map.Entry<K,V>，成员变量：key，value，hash值，next，即链表下的节点
     */
    private static void capacity() {
        HashMap<String, String> hashMap = new HashMap<>();
        System.out.println(hashMap.size());
    }

    /**
     * hash算法 (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)
     * ^ 是异或的意思
     * h =        10 0001 1101 1100 0011 1010 1011
     * h >>> 16 = 00 0000 0000 0000 0010 0001 1101
     * 异或之后 = 10 0001 1101 1100 0001 1011 0110
     */
    public static int hash(Object key) {
        if (key == null) {
            return 0;
        }
        int h = key.hashCode();
        String ss = Integer.toBinaryString(h);
        System.out.println(ss);
        System.out.println(Integer.parseInt("10000111011100000110110110", 2));
        return h ^ (h >>> 16);
    }

    /**
     * 成员变量：
     * transient Node<K,V>[] table;
     * transient Set<Map.Entry<K,V>> entrySet;
     * transient int size;
     * public int size() {
     *         return size;
     *     }
     *  public boolean isEmpty() {
     *         return size == 0;
     *     }
     * transient int modCount;
     * int threshold;
     * final float loadFactor;
     */

    /**
     * 构造函数
     * public HashMap(int initialCapacity, float loadFactor)：初始容量，加载因子
     * public HashMap(int initialCapacity) {
     *         this(initialCapacity, DEFAULT_LOAD_FACTOR);  // 加载因子默认为0.75
     *     }
     * public HashMap() {
     *         this.loadFactor = DEFAULT_LOAD_FACTOR;
     *      }
     * public HashMap(Map<? extends K, ? extends V> m) {
     *         this.loadFactor = DEFAULT_LOAD_FACTOR;
     *         putMapEntries(m, false);
     *     }
     */
    public static void constructs(HashMap<String, String> map) {
        HashMap<String, String> hashMap = new HashMap<String, String>(16, (float) 0.70);
        HashMap<String, String> hashMap2 = new HashMap<String, String>();
        HashMap<String, String> hashMap3 = new HashMap<String, String>(map);
        System.out.println(hashMap3);
    }

    /**
     * public V put(K key, V value) {
     *         return putVal(hash(key), key, value, false, true);
     *     }
     * put方法：通过hash计算出一个索引值，如果当前索引值为空，则添加进去（key，value，next = null）
     */
    public static void put() {
        HashMap<String, String> hashMap = new HashMap<>();
        String s1 = "1";
        String s2 = "2";
        String s3 = "3";
        String s4 = "4";
        String s5 = "5";
        String s6 = "6";
        String s7 = "7";
        String s8 = "8";
        String s9 = "9";
        String s10 = "10";
        String s11 = "11";
        String s12 = "12";
        String s13 = "13";
        String s14 = "14";
        String s15 = "15";
        String s16 = "16";
        String s17 = "17";
        hashMap.put(s1, s1);
        hashMap.put(s2, s2);
        hashMap.put(s3, s3);
        hashMap.put(s4, s4);
        hashMap.put(s5, s5);
        hashMap.put(s6, s6);
        hashMap.put(s7, s7);
        hashMap.put(s8, s8);
        hashMap.put(s9, s9);
        hashMap.put(s10, s10);
        hashMap.put(s11, s11);
        hashMap.put(s12, s12);
        hashMap.put(s13, s13);
//        hashMap.put(s14, s14);
//        hashMap.put(s15, s15);
//        hashMap.put(s16, s16);
//        hashMap.put(s17, s17);
        System.out.println(hashMap);
    }

    /**
     * tableSizeFor: 扩容的参数，比输入值大的2的幂次方
     * @param cap
     * @return
     */
    public static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }

    public static void get() {
        HashMap<String, String> hashMap = new HashMap<>();
        String s1 = "1";
        String s2 = "2";
        hashMap.put(s1, s1);
        hashMap.put(s2, s2);
        hashMap.get(s1);
    }
}
