package com.alex.codevie;

/**
 * @ClassName Codevie
 * @Description 编程大赛
 * @Author Alex
 * @CreateDate 12/07/2019 10:42
 * @Version 1.0
 */
//破译报名地址
//        Problem Description
//        有个叫“百果科技”的公司，举办了一次编程大赛。他们的报名地址后缀部分【https://docs.qq.com/sheet/[加密部分]】
//        采用一种简单的文法加密，后缀部分里面只有大小写字母加数字组成，没有其他任何字符；
//        现在还知道他们加密的方法是：只用一个大写字母和“加密部分的每一位”进行异或运算生成密文。请你帮忙解开完整的报名地址。
//
//
//        Input
//        [加密部分]长度为17，经过加密后生成的密文如下：
//        16,2,57,56,60,48,57,18,13,7,1,30,13,55,58,22,12
//
//        Output
//        输出17位仅由大小写字母加数字组成的原文。
//
//
//        TIPS
//        https://docs.qq.com/sheet/[加密部分] 输出的原文替换[加密部分]，然后用浏览器访问就可以开心的报名啦(#^.^#) ~

public class Codevie {
    public static void main(String[] args) {
        int[] result = {16, 2, 57, 56, 60, 48, 57, 18, 13, 7, 1, 30, 13, 55, 58, 22, 12};
        int count = 0;
        int letterA = 'A';
        int letterZ = 'Z';
        int lettera = 'a';
        int letterz = 'z';
        for (int j = letterA; j <= letterZ; j++) {
            StringBuilder origin = new StringBuilder();
            for (int i : result) {
                count++;
                int temp = i ^ j;
                boolean isLetter = (temp >= letterA && temp <= letterZ) || (temp >= lettera && temp <= letterz);
                if (isLetter) {
                    origin.append((char) temp);
                } else if (temp >= 48 && temp <= 57) {
                    origin.append(temp);
                } else {
                    break;
                }
            }
            if (origin.length() == result.length) {
                System.out.println("原地址："+origin + "，加密关键字：" + (char) j);
            }
        }
        System.out.println("循环次数（时间复杂度）："+count);
    }
}
