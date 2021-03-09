package com.example.book;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@SpringBootTest
class BookApplicationTests {

    @Test
    void contextLoads() {
        String password="admin";
        String newPassword=DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(newPassword);
    }

}
