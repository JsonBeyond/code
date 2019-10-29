package com.alex.codevie;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Codevie2
 * @Description 第二届编程大赛
 * <p>
 * 已知：
 * 1.三个转子的字母顺序已知：如
 * 转子a:
 * 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26
 * 1,19,10,14,26,20,8,16,7,22,4,11,5,17,9,12,23,18,2,25,6,24,13,21,3,15
 * 转子b:
 * 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26
 * 1,6,4,15,3,14,12,23,5,16,2,22,19,11,18,25,24,13,7,10,8,21,9,26,17,20
 * 转子c:
 * 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26
 * 8,18,26,17,20,22,10,3,13,11,4,23,5,24,9,12,25,16,19,6,15,21,2,7,1,14
 * 2.三个转子的初始旋转位置不确定,如：图一中，最左的转子24对应字母A,初始状态也可以是其他位置对应字母A,中转子和快转子同理。
 * <p>
 * Input
 * RAXLZSDKQAGECXWGVK
 * <p>
 * Output
 * PAGODA************
 * @Author Alex
 * @CreateDate 10/10/2019 1:20 PM
 * @Version 1.0
 */
class Codevie2 {

    /**
     * 每个转子转一圈的步长
     */
    private static int TURN_FACTOR = 26;
    /**
     * 多少个转轮
     */
    private static int TURN_NUMBER = 3;
    private static Map<Integer, Integer> MAP_A = Maps.newHashMapWithExpectedSize(TURN_FACTOR);
    private static Map<Integer, Integer> MAP_B = Maps.newHashMapWithExpectedSize(TURN_FACTOR);
    private static Map<Integer, Integer> MAP_C = Maps.newHashMapWithExpectedSize(TURN_FACTOR);

    private static List<Map<Integer, Integer>> MAPS = Lists.newArrayListWithExpectedSize(TURN_NUMBER);

    private static Map<Integer, Integer> MAP_REAL_A = Maps.newHashMapWithExpectedSize(TURN_FACTOR);
    private static Map<Integer, Integer> MAP_REAL_B = Maps.newHashMapWithExpectedSize(TURN_FACTOR);
    private static Map<Integer, Integer> MAP_REAL_C = Maps.newHashMapWithExpectedSize(TURN_FACTOR);

    // 初始化三个转子的数字顺序
    static {
        // 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26
        // 1,19,10,14,26,20,8,16,7,22,4,11,5,17,9,12,23,18,2,25,6,24,13,21,3,15
        MAP_A.put(1, 1);
        MAP_A.put(2, 19);
        MAP_A.put(3, 10);
        MAP_A.put(4, 14);
        MAP_A.put(5, 26);
        MAP_A.put(6, 20);
        MAP_A.put(7, 8);
        MAP_A.put(8, 16);
        MAP_A.put(9, 7);
        MAP_A.put(10, 22);
        MAP_A.put(11, 4);
        MAP_A.put(12, 11);
        MAP_A.put(13, 5);
        MAP_A.put(14, 17);
        MAP_A.put(15, 9);
        MAP_A.put(16, 12);
        MAP_A.put(17, 23);
        MAP_A.put(18, 18);
        MAP_A.put(19, 2);
        MAP_A.put(20, 25);
        MAP_A.put(21, 6);
        MAP_A.put(22, 24);
        MAP_A.put(23, 13);
        MAP_A.put(24, 21);
        MAP_A.put(25, 3);
        MAP_A.put(26, 15);


        // 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26
        // 1,6,4,15,3,14,12,23,5,16,2,22,19,11,18,25,24,13,7,10,8,21,9,26,17,20
        MAP_B.put(1, 1);
        MAP_B.put(2, 6);
        MAP_B.put(3, 4);
        MAP_B.put(4, 15);
        MAP_B.put(5, 3);
        MAP_B.put(6, 14);
        MAP_B.put(7, 12);
        MAP_B.put(8, 23);
        MAP_B.put(9, 5);
        MAP_B.put(10, 16);
        MAP_B.put(11, 2);
        MAP_B.put(12, 22);
        MAP_B.put(13, 19);
        MAP_B.put(14, 11);
        MAP_B.put(15, 18);
        MAP_B.put(16, 25);
        MAP_B.put(17, 24);
        MAP_B.put(18, 13);
        MAP_B.put(19, 7);
        MAP_B.put(20, 10);
        MAP_B.put(21, 8);
        MAP_B.put(22, 21);
        MAP_B.put(23, 9);
        MAP_B.put(24, 26);
        MAP_B.put(25, 17);
        MAP_B.put(26, 20);

        // 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26
        // 8,18,26,17,20,22,10,3,13,11,4,23,5,24,9,12,25,16,19,6,15,21,2,7,1,14
        MAP_C.put(1, 8);
        MAP_C.put(2, 18);
        MAP_C.put(3, 26);
        MAP_C.put(4, 17);
        MAP_C.put(5, 20);
        MAP_C.put(6, 22);
        MAP_C.put(7, 10);
        MAP_C.put(8, 3);
        MAP_C.put(9, 13);
        MAP_C.put(10, 11);
        MAP_C.put(11, 4);
        MAP_C.put(12, 23);
        MAP_C.put(13, 5);
        MAP_C.put(14, 24);
        MAP_C.put(15, 9);
        MAP_C.put(16, 12);
        MAP_C.put(17, 25);
        MAP_C.put(18, 16);
        MAP_C.put(19, 19);
        MAP_C.put(20, 6);
        MAP_C.put(21, 15);
        MAP_C.put(22, 21);
        MAP_C.put(23, 2);
        MAP_C.put(24, 7);
        MAP_C.put(25, 1);
        MAP_C.put(26, 14);

        //初始化需要排列组合的数组(abc)
        MAPS.add(MAP_A);
        MAPS.add(MAP_B);
        MAPS.add(MAP_C);
    }

    private Map<Integer, Integer[]> enigma() {
        System.out.println("调用一次enigm");
        // 总循环次数
        int count = 0;
        MAP_REAL_A = MAPS.get(0);
        MAP_REAL_B = MAPS.get(1);
        MAP_REAL_C = MAPS.get(2);
        Map<Integer, Integer[]> map = Maps.newLinkedHashMapWithExpectedSize(26);
        for (int i = 1; i <= TURN_FACTOR; i++) {
            for (int j = 1; j <= TURN_FACTOR; j++) {
                for (int k = 1; k <= TURN_FACTOR; k++) {
                    map.clear();
                    for (int l = 1; l <= TURN_FACTOR; l++) {
                        map.put(l, new Integer[]{
                                getOppsiteLocation(-i, l + 1),
                                MAP_REAL_A.get(getOppsiteLocation(-i, l + 1)),
                                getOppsiteLocation(-j, l + 1),
                                MAP_REAL_B.get(getOppsiteLocation(-j, l + 1)),
                                getOppsiteLocation(-k, l + 1),
                                MAP_REAL_C.get(getOppsiteLocation(-k, l + 1)),

//                                getLocation(i, l - 1),
//                                MAP_REAL_A.get(getLocation(i, l - 1)),
//                                getLocation(j, l - 1),
//                                MAP_REAL_B.get(getLocation(j, l - 1)),
//                                getLocation(k, l - 1),
//                                MAP_REAL_C.get(getLocation(k, l - 1)),
                                l
                        });
                        count++;
                    }
                    if (getOutput(map, 18, 6) == 16) {
                        map = getNextMap(map);
                        if (getOutput(map, 1, 6) == 1) {
                            map = getNextMap(map);
                            if (getOutput(map, 24, 6) == 7) {
                                map = getNextMap(map);
                                if (getOutput(map, 12, 6) == 15) {
                                    map = getNextMap(map);
                                    if (getOutput(map, 26, 6) == 4) {
                                        map = getNextMap(map);
                                        if (getOutput(map, 19, 6) == 1) {
                                            map = getNextMap(map);
                                            System.out.println("找到初始位置,历经" + count + "次循环");
                                            return map;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private int getOutput(Map<Integer, Integer[]> map, int num, int length) {
        for (Integer[] value : map.values()) {
            System.out.println(Arrays.toString(value));
        }
        for (Integer[] value : map.values()) {
            if (value[length].equals(num)) {
                if (length - 2 < 0) {
                    for (Map.Entry<Integer, Integer[]> integerEntry : map.entrySet()) {
                        if (integerEntry.getValue()[0].equals(value[length])) {
                            return integerEntry.getKey();
                        }
                    }
                } else {
                    getOutput(map, value[length - 1], length - 2);
                }
            }
        }

//        for (Integer[] value : map.values()) {
//            if (value[6].equals(num)) {
//                for (Integer[] value2 : map.values()) {
//                    if (value2[4].equals(value[5])) {
//                        for (Integer[] value3 : map.values()) {
//                            if (value3[2].equals(value2[3])) {
//                                for (Map.Entry<Integer, Integer[]> integerEntry : map.entrySet()) {
//                                    if (integerEntry.getValue()[0].equals(value3[1])) {
////                                        System.out.println("根据输入找输出次数:"+count);
//                                        return integerEntry.getKey();
//                                    }
//                                    count++;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
        return -1;
    }

    private int getLocation(int x, int l) {
        return x + l > TURN_FACTOR ? (x + l - TURN_FACTOR) : x + l;
    }

    private int getOppsiteLocation(int x, int l) {
        return x + l < 1 ? (x + l + TURN_FACTOR) : x + l;
    }

    /**
     * 根据当前所有转子的位置集合,获取下一次击键的所有转子位置集合
     *
     * @param map 当前所有转子的位置
     * @return java.util.Map<java.lang.Integer, java.lang.Integer [ ]>
     * @author Alex
     * @date 10/12/2019 11:26 AM
     */
    private Map<Integer, Integer[]> getNextMap(Map<Integer, Integer[]> map) {
//        System.out.println("调用getNextMap");
        Map<Integer, Integer[]> newMap = Maps.newHashMapWithExpectedSize(TURN_FACTOR);
        for (int i = 1; i <= TURN_FACTOR; i++) {
            newMap.put(i, new Integer[]{
                    map.get(i)[0],
                    map.get(i)[1],
                    map.get(i)[2],
                    map.get(i)[3],
//                    map.get(getLocation(i, 1))[4],
//                    map.get(getLocation(i, 1))[5],
//                    getOppsiteLocation(map.get(i)[6], -1),

                    map.get(getOppsiteLocation(i, -1))[4],
                    map.get(getOppsiteLocation(i, -1))[5],
                    getLocation(map.get(i)[6], 1),
            });
        }
        map = newMap;
        return map;
    }


    /**
     * map集合通过value获取第一个key或者唯一key
     *
     * @param map   map集合
     * @param value value关键字
     * @return java.lang.Integer
     * @author Alex
     * @date 10/12/2019 11:11 AM
     */
    private Integer getOneKey(Map<Integer, Integer> map, int value) {
        return (Integer) ((List) getKey(map, value)).get(0);
    }

    /**
     * map集合通过value查询key集合
     *
     * @param map   map集合
     * @param value value关键字
     * @return java.lang.Object
     * @author Alex
     * @date 10/12/2019 11:10 AM
     */
    private static Object getKey(Map<?, ?> map, Object value) {
        List<Object> keyList = new ArrayList<>();
        for (Object key : map.keySet()) {
            if (map.get(key).equals(value)) {
                keyList.add(key);
            }
        }
        return keyList;
    }


    /**
     * 数组排列组合,计算A(n,k)
     *
     * @param data   源数组
     * @param target 排列后数组
     * @param k      排列因子
     * @return void
     * @author Alex
     * @date 10/12/2019 11:07 AM
     */
    private <E> void arrangeSelect(List<E> data, List<E> target, int k) {
        List<E> copyData;
        List<E> copyTarget;
        if (target.size() == k) {
            MAPS = (List<Map<Integer, Integer>>) target;
            Map<Integer, Integer[]> map = enigma();
            if (null != map) {
                System.out.println("找到答案了,此时转子顺序为慢转子->中转子->快转子:");
                return;
            }
        }

        for (int i = 0; i < data.size(); i++) {
            copyData = new ArrayList<>(data);
            copyTarget = new ArrayList<>(target);
            copyTarget.add(copyData.get(i));
            copyData.remove(i);
            arrangeSelect(copyData, copyTarget, k);
        }
    }

    int[] hahah() {
        arrangeSelect(MAPS, Lists.newArrayListWithExpectedSize(TURN_NUMBER), MAPS.size());
        return null;
    }
}
