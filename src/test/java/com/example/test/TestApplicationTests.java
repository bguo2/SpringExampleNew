package com.example.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@ExtendWith(MockitoExtension.class)
//@PrepareForTest() class to mock
@Slf4j
class TestApplicationTests {

    @BeforeEach
    public void setup() {
        log.info("setup");
        //PowerMockito.spy() //class instance to initialize
    }

    @Test
    void contextLoads() {
    }

}
