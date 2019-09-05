package com.alex.dfa;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SensitiveWordServiceTest {

    @Test
    void sensitiveWordFiltering() {
        Set<String> set = new SensitiveWordService().sensitiveWordFiltering("我是个王八，王八哈哈，你会翻墙吗，王八蛋哦");
        System.out.println(set);
    }
}