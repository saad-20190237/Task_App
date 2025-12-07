package com.devtiro.tasks;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGen {
    public static  void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded =  encoder.encode("1234");
        System.out.println(encoded);
    }
}
