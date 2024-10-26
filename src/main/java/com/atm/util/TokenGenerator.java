package com.atm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

@Component
public class TokenGenerator {
    private static final Logger logger = LoggerFactory.getLogger(TokenGenerator.class);
    public static String generateDigestToken(String userId) {
        try {
            String tokenBase = userId + UUID.randomUUID().toString();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(tokenBase.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
        } catch (NoSuchAlgorithmException t) {
            logger.info(t.getMessage());
        }
        return null;
    }
}
