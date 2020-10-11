package base.map_learning;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo1 {
    public static void main(String[] args){
        Map<String, String> map = new HashMap<String, String>();
        // 创建元素并添加到集合
        map.put("天", "地");
        map.put("雨", "风");
        map.put("大陆", "长空");
        map.put("山花", "海树");

        //第一种遍历方法
        Set<String> set = map.keySet();
        for(String key : set){
            String value = map.get(key);
            System.out.println(key +"---"+ value);
        }

        //第二种遍历方法
        // 获取所有键值对对象的集合
        Set<Map.Entry<String, String>> set2 = map.entrySet();
        // 遍历键值对对象的集合，得到每一个键值对对象
        for (Map.Entry<String, String> me : set2) {
            // 根据键值对对象获取键和值
            String key = me.getKey();
            String value = me.getValue();
            System.out.println(key + "---" + value);
        }
    }
}
