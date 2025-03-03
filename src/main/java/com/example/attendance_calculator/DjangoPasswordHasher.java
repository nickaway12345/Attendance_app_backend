package com.example.attendance_calculator;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class DjangoPasswordHasher {
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int ITERATIONS = 720000;  // Django uses this
    private static final int KEY_LENGTH = 256;  // Django uses 256-bit key

    public static String hashPassword(String password, String salt, int iterations)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] saltBytes = salt.getBytes(StandardCharsets.UTF_8);  // Use UTF-8 encoded salt (not Base64)
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, iterations, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(hash);  // Base64 encode to match Django
    }

    public static String encode(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String hash = hashPassword(password, salt, ITERATIONS);
        return String.format("%s$%d$%s$%s", ALGORITHM, ITERATIONS, salt, hash);
    }

    public static boolean verifyPassword(String password, String encoded) throws NoSuchAlgorithmException, InvalidKeySpecException {
        System.out.println(password);
        System.out.println(encoded);
        String[] parts = encoded.split("\\$");

        int iterations = Integer.parseInt(parts[1]);
        String salt = parts[2];
        String expectedHash = parts[3];

        String computedHash = hashPassword(password, salt, iterations);
        System.out.println(computedHash);
        System.out.println(encoded);
        return expectedHash.equals(computedHash);
    }
}
