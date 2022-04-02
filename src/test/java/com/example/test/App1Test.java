package com.example.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class App1Test {

    @Test
    public void test1() {
        log.info("test1");
    }
}
