package com.spring.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncrytedPasswordUtils {
	// Encryte Password with BCryptPasswordEncoder
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
 
    public static void main(String[] args) {
        String password = "123";
        String encrytedPassword = encrytePassword(password);
        System.out.println(new BCryptPasswordEncoder().matches("$2a$10$B0ZbSWHrgj.dEfJJiepz/.JBuaS2p4Xbb7Nhmn8qwiV6z49Rev/a.", "123"));
        System.out.println("Encryted Password: " + encrytedPassword);
    }
}
