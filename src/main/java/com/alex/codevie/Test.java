package com.alex.codevie;


/**
 * @author qxs on 2019/7/12.
 */
public class Test {
    private static int[] key = {16, 2, 57, 56, 60, 48, 57, 18, 13, 7, 1, 30, 13, 55, 58, 22, 12};

    public static void main(String[] args) {

        int count = 0;
        for (int i = 65; i <= 90; i++) {
            StringBuilder s = new StringBuilder();
            for (int aKey : key) {
                s.append((char) (i ^ aKey));
                count++;
            }
            System.out.println(s);
        }
        System.out.println(count);
    }

}
