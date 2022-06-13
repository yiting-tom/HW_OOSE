package utils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * This class is used to generate/check a hash code for a string.
 */
public class Hash {
    private static byte[] salt = new byte[16];

    public Hash() throws NoSuchAlgorithmException {
    }

    /**
     * Generate a hash code for a string.
     * 
     * @param password The string to generate a hash code for.
     * @return The hash code of the string.
     * @throws InvalidKeySpecException If the password is invalid.
     */
    public static byte[] hash(String password) throws InvalidKeySpecException {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            byte[] encoded = factory.generateSecret(spec).getEncoded();

            return encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while hashing password", e);
        }
    }

    /**
     * Check if a hash code is valid for a string.
     * 
     * @param password The string to check the hash code for.
     * @param hash The hash code to check.
     * @return True if the hash code is valid for the string.
     * @throws InvalidKeySpecException If the password is invalid.
     */
    public static boolean check(String password, byte[] hash) throws InvalidKeySpecException {
        byte[] test = hash(password);
        if (test.length != hash.length) {
            return false;
        }
        for (int i = 0; i < test.length; i++) {
            if (test[i] != hash[i]) {
                return false;
            }
        }
        return true;
    }
}
