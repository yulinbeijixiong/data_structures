package com.oujian.algorithm.base;

import java.util.*;

/**
 * @author annyu
 * @description 贪心算法
 * @date 2020/4/29
 **/
public class GreedyDemo {
    public static void main(String[] args) {
        HashMap<String, HashSet<String>> broadcast = new HashMap<String, HashSet<String>>(16);
        HashSet<String> area1 = new HashSet<String>(Arrays.asList("北京", "上海", "天津"));
        broadcast.put("k1", area1);
        HashSet<String> area2 = new HashSet<String>(Arrays.asList("广州", "北京", "深圳"));
        broadcast.put("k2", area2);
        HashSet<String> area3 = new HashSet<String>(Arrays.asList("成都", "上海", "杭州"));
        broadcast.put("k3", area3);
        HashSet<String> area4 = new HashSet<String>(Arrays.asList("上海", "天津"));
        broadcast.put("k4", area4);
        HashSet<String> area5 = new HashSet<String>(Arrays.asList("杭州", "大连"));
        broadcast.put("k5", area5);
        System.out.println(Arrays.toString(algorithm(broadcast).toArray()));

    }

    public static List<String> algorithm(HashMap<String, HashSet<String>> broadcast) {
        List<String> list = new ArrayList<String>();
        HashSet<String> allArea = new HashSet<String>();
        //得到所有广播的集合
        for (String key : broadcast.keySet()) {
            allArea.addAll(broadcast.get(key));
        }
        //用于存储最大值
        String maxKey;
        HashMap<String, Integer> keyCount = new HashMap<String, Integer>(16);
        HashSet<String> set = new HashSet<String>();
        Set<String> keySet = broadcast.keySet();
        while (allArea.size() > 0) {
            maxKey = null;
            keyCount.clear();
            for (String key1 :keySet) {
                set.clear();
                //看当前的广播站能覆盖 多少剩下地区
                set.addAll(broadcast.get(key1)) ;
                set.retainAll(allArea);
                //计算每个广播
                keyCount.put(key1, set.size());
            }
            for (String key : keySet) {
                //当前广播在剩下的地区中能覆盖地区的个数大于以前的
                if (maxKey == null || keyCount.get(key) > keyCount.get(maxKey)) {
                    maxKey = key;
                }

            }
            if (maxKey != null) {
                list.add(maxKey);
                allArea.removeAll(broadcast.get(maxKey));
                keySet.remove(maxKey);
            }
        }
        return list;
    }
}
