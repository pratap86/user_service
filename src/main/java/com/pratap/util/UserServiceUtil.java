package com.pratap.util;

import java.security.SecureRandom;
import java.util.Random;

public interface UserServiceUtil {

    final Random RANDOM = new SecureRandom();
    final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    static String generateUserId(int length) {
        return generateRandomString(length);
    }

    static String generateAddressId(int length) {
        return generateRandomString(length);
    }
    private static String generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }
}
