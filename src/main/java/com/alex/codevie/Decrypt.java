package com.alex.codevie;

/**
 * 编程大赛报名地址解密
 * Created by tangz on 2019/7/11.
 */
public class Decrypt {

    public static void main(String[] args) {
        //密文
        int ciphertext[] = new int[]{16, 2, 57, 56, 60, 48, 57, 18, 13, 7, 1, 30, 13, 55, 58, 22, 12};
        //原文
        StringBuffer originaltext = new StringBuffer();
        int count = 0;
        for (int i = 'A'; i <= 'Z'; i++) {
            originaltext = new StringBuffer();
            //爆破所有可能的k
            //System.out.println(i);
            for (int a : ciphertext) {
                //遍历所有字母[0-9]的数字，看看哪个和对应ciphertext的元素的和等于i
                for (int j = '0'; j <= 'z'; j++) {
                    if (a == (i ^ j)) {
                        originaltext.append((char) j);
                        break;
                    }
                    if (j == '9') {
                        j = 'A' - 1;
                    }
                    if (j == 'Z') {
                        j = 'a' - 1;
                    }
                    count++;
                }
            }
            //System.out.println(originaltext.toString());
            if (ciphertext.length == originaltext.length()) {
                System.out.println("原文:" + originaltext.toString());
            }
        }
        System.out.println("循环次数:"+count);

    }
}