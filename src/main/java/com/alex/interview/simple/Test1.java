package com.alex.interview.simple;

/**
 * @ClassName Test1
 * @Description TODO
 * @Author Alex
 * @CreateDate 26/07/2019 10:04
 * @Version 1.0
 */
public class Test1 {
    static boolean foo(char c) {
        System.out.println(c);
        return true;
    }

    public static void main(String[] args) {

        int i = 0;
        for(foo('A');foo('B')&&i<2;foo('C')){
            i++;
            foo('D');
        }
    }
}
