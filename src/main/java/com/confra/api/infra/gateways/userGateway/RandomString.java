package com.confra.api.infra.gateways.userGateway;

import java.security.SecureRandom;

public class RandomString {
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateRandomString(int length) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(ALPHA_NUMERIC_STRING.length());
            stringBuilder.append(ALPHA_NUMERIC_STRING.charAt(randomIndex));
        }

        return stringBuilder.toString();
    }
}
