package com.web.back.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class SecretKey {
    private static final int KEY_LENGTH = 104; // 바이트

    // 랜덤 키 생성
    private static String generateKey() {
        SecureRandom secureRandom;
        try {
            secureRandom = SecureRandom.getInstanceStrong();
        }
        catch(NoSuchAlgorithmException e) {
            throw new RuntimeException("예외 : ", e);
        }

        byte[] keyBytes = new byte[KEY_LENGTH];
        secureRandom.nextBytes(keyBytes);

        // Base64 인코딩
        String base64EncodedKey = Base64.getEncoder().encodeToString(keyBytes);

        return base64EncodedKey;
    }

    public static void main(String[] args) {
        System.out.println("랜덤 서명 키 : " + generateKey());
    }
}
