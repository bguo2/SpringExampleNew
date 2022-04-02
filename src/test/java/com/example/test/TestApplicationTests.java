package com.example.test;

import com.example.test.model.User;
import com.example.test.repository.UserRepoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//@PrepareForTest() class to mock
@Slf4j
class TestApplicationTests {

    @Mock
    private UserRepoService userRepoService;

    @BeforeEach
    public void setup() {
        log.info("setup");
        //PowerMockito.spy() //class instance to initialize
    }

    @Test
    void test1() {
        List<User> users = new ArrayList<>() {{
            add(new User());
        }};
        when(userRepoService.getAllUsers(anyLong())).thenReturn(users);
        List<User> result = userRepoService.getAllUsers(3L);
        assertTrue("users should not empty", !result.isEmpty());
    }

}
