package com.alex.snowflake;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SnowflakeIdWorkerTest {

    @Test
    @DisplayName("获取序列")
    void testNextId() {
        long start = System.currentTimeMillis();
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        for (int i = 0; i < 200000; i++) {
            long id = idWorker.nextId();
//            System.out.println(Long.toBinaryString(id));
//            System.out.println(id);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}