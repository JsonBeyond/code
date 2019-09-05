package com.alex.practise.threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName ABA
 * @Description CAS操作
 * @Author Alex
 * @CreateDate 15/08/2019 10:09
 * @Version 1.0
 */
public class ABA {
    private static AtomicInteger atomicInt = new AtomicInteger(100);
    private static AtomicStampedReference<Integer> atomicStampedRef =
            new AtomicStampedReference<>(100, 0);

    public static void main(String[] args) throws InterruptedException {
        Thread intT1 = new Thread(() -> {
            atomicInt.compareAndSet(100, 101);
            atomicInt.compareAndSet(101, 100);
        });

        Thread intT2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean c3 = atomicInt.compareAndSet(100, 101);
            System.out.println(c3);        //true
        });

        intT1.start();
        intT2.start();
        intT1.join();
        intT2.join();

        Thread refT1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedRef.compareAndSet(100, 101,
                    atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
            atomicStampedRef.compareAndSet(101, 100,
                    atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
            System.out.println("refT1执行完毕");
        });

        Thread refT2 = new Thread(() -> {
            int stamp = atomicStampedRef.getStamp();
            System.out.println("before sleep : stamp = " + stamp);    // stamp = 0
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("after sleep : stamp = " + atomicStampedRef.getStamp());//stamp = 1
            boolean c3 = atomicStampedRef.compareAndSet(100, 101, stamp, stamp + 1);
            System.out.println(c3);        //false
        });

        refT1.start();
        refT2.start();
    }
}
