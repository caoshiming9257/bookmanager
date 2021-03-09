package com.example.book.utils;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 * @author Hanyixuan
 */
public class Md5Util {
    public static final int TIME = 5;

    public static final String SALT = "springsecurity";

    /**
     * 密码加密方法
     * @param password 密码
     * @return 加密后密码
     */
    public static String encode(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("MD5算法不可用");
        }
        for (int i = 0; i < TIME; i++) {
            byte[] bytes = digest.digest((password + SALT).getBytes(StandardCharsets.UTF_8));
            password = String.format("%032x", new BigInteger(1, bytes));
        }
        return password;
    }

    public static void main(String[] args) {
        System.out.println(Md5Util.encode("admin"));
    }
}