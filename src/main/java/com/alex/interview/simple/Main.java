package com.alex.interview.simple;

/**
 * @ClassName Main
 * @Description TODO
 * @Author Alex
 * @CreateDate 26/07/2019 10:24
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(Test2.x);
        Test2 t1 = new Test2();
        Test2 t2 = new Test2();
        t1.setValue(3);
        t2.setValue(4);

        System.out.println(t1.getValue());
        System.out.println(t2.getValue());
    }
}
