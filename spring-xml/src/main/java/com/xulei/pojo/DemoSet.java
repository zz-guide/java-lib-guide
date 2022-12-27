package com.xulei.pojo;

import java.util.*;

/**
 * 用来测试集合属性的注入
 * Set
 * Map
 * Array
 * List
 */
public class DemoSet {
    public DemoSet(){
        System.out.println("DemoSet 无参构造");
    }
    public DemoSet(String[] str, List<String> list, Map<String, String> map, Set<String> set, Properties properties) {
        System.out.println("字符串数组注入="+Arrays.toString(str));

        if (list != null ) {
            System.out.println("List注入="+Arrays.toString(list.toArray()));
        }

        if (map != null ) {
            System.out.println("Map注入="+map);
            for (String key: map.keySet()) {
                System.out.println("map元素,key="+key+",value="+map.get(key));
            }
        }

        if (set != null ) {
            System.out.println("Set注入="+set);
            for (String item: set) {
                System.out.println("set元素,value="+item);
            }
        }

        if (properties != null) {
            System.out.println(properties.toString());
        }
    }
}
