package com.example.attendance_calculator;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class DjangoPasswordHasher {

    /**
     * Create a PBKDF2 SHA256 hash for a password in Django-compatible format.
     *
     * @param password   The password to hash
     * @param iterations Number of iterations for PBKDF2 (default is 720000 to match Django)
     * @return The password hash in Django's format
     * @throws NoSuchAlgorithmException If the algorithm is not available
     * @throws InvalidKeySpecException  If the key spec is invalid
     */
    public static String hashPassword(String password, int iterations)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        // Hash the password using PBKDF2
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hashBytes = factory.generateSecret(spec).getEncoded();

        // Encode the salt and hash to base64
        String saltB64 = Base64.getEncoder().encodeToString(salt);
        String hashB64 = Base64.getEncoder().encodeToString(hashBytes);

        // Format in Django's style
        return String.format("pbkdf2_sha256$%d$%s$%s", iterations, saltB64, hashB64);
    }

    /**
     * Create a PBKDF2 SHA256 hash for a password using default iterations (720000).
     *
     * @param password The password to hash
     * @return The password hash in Django's format
     * @throws NoSuchAlgorithmException If the algorithm is not available
     * @throws InvalidKeySpecException  If the key spec is invalid
     */
    public static String hashPassword(String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        return hashPassword(password, 720000);
    }

    /**
     * Verify a password against a stored Django-style PBKDF2 SHA256 hash.
     *
     * @param password   The password to verify
     * @param storedHash The stored hash to check against
     * @return True if the password matches, False otherwise
     * @throws NoSuchAlgorithmException If the algorithm is not available
     * @throws InvalidKeySpecException  If the key spec is invalid
     */
    public static boolean verifyPassword(String password, String storedHash)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Parse the hash components using regex
        Pattern pattern = Pattern.compile("pbkdf2_sha256\\$(\\d+)\\$(.*?)\\$(.*)");
        Matcher matcher = pattern.matcher(storedHash);

        if (!matcher.matches()) {
            return false;
        }

        int iterations = Integer.parseInt(matcher.group(1));
        String saltB64 = matcher.group(2);
        String storedHashB64 = matcher.group(3);

        // Decode the salt from base64
        byte[] salt = Base64.getDecoder().decode(saltB64);

        // Hash the input password with the same salt and iterations
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hashBytes = factory.generateSecret(spec).getEncoded();

        // Encode to base64 for comparison
        String hashB64 = Base64.getEncoder().encodeToString(hashBytes);

        // Compare the computed hash with the stored hash
        return hashB64.equals(storedHashB64);
    }

    /**
     * Find a user's password hash from the user data.
     *
     * @param username The username to look up
     * @param userData Map of usernames to password hashes
     * @return The password hash if found, null otherwise
     */
    public static String getUserHash(String username, Map<String, String> userData) {
        return userData.get(username);
    }
}