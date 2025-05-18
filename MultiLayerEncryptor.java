import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.io.ByteArrayOutputStream;

public class MultiLayerEncryptor {

    private static final int SALT_LENGTH = 16;
    private static final int IV_LENGTH = 16;
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;

    public static String encrypt(String plaintext, String key) throws Exception {
        // Generate salt and IV
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        byte[] iv = new byte[IV_LENGTH];
        random.nextBytes(iv);

        // Generate AES key
        SecretKey secretKey = generateKey(key, salt);

        // AES encryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
        byte[] ciphertext = cipher.doFinal(plaintext.getBytes(java.nio.charset.StandardCharsets.UTF_8));

        // Combine salt + iv + ciphertext
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(salt);
        outputStream.write(iv);
        outputStream.write(ciphertext);
        byte[] combined = outputStream.toByteArray();

        // Base64 encoding
        String base64Data = Base64.getEncoder().encodeToString(combined);

        // Obfuscation: Reverse string
        String obfuscated = new StringBuilder(base64Data).reverse().toString();

        // Calculate hash
        String hash = calculateHash(plaintext);

        // Final combination
        return obfuscated + hash;
    }

    public static String decrypt(String encryptedStr, String key) throws Exception {
        // Split components
        if (encryptedStr.length() <= 64) {
            throw new IllegalArgumentException("Invalid encrypted string");
        }
        String obfuscatedPart = encryptedStr.substring(0, encryptedStr.length() - 64);
        String storedHash = encryptedStr.substring(encryptedStr.length() - 64);

        // Reverse obfuscation
        String base64Data = new StringBuilder(obfuscatedPart).reverse().toString();

        // Decode Base64
        byte[] combined = Base64.getDecoder().decode(base64Data);

        // Extract components
        if (combined.length < SALT_LENGTH + IV_LENGTH) {
            throw new IllegalArgumentException("Invalid data length");
        }
        byte[] salt = Arrays.copyOfRange(combined, 0, SALT_LENGTH);
        byte[] iv = Arrays.copyOfRange(combined, SALT_LENGTH, SALT_LENGTH + IV_LENGTH);
        byte[] ciphertext = Arrays.copyOfRange(combined, SALT_LENGTH + IV_LENGTH, combined.length);

        // Regenerate key
        SecretKey secretKey = generateKey(key, salt);

        // AES decryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
        byte[] plaintextBytes = cipher.doFinal(ciphertext);
        String plaintext = new String(plaintextBytes, java.nio.charset.StandardCharsets.UTF_8);

        // Verify hash
        String calculatedHash = calculateHash(plaintext);
        if (!calculatedHash.equals(storedHash)) {
            throw new SecurityException("Hash validation failed");
        }

        return plaintext;
    }

    private static SecretKey generateKey(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKey tmp = factory.generateSecret(spec);
        return new SecretKeySpec(tmp.getEncoded(), "AES");
    }

    private static String calculateHash(String data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(data.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        return bytesToHex(hashBytes);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        String key = "MySuperSecretKey";
        String plaintext = "Hello, World! 你好，世界！";

        // Encryption
        String encrypted = encrypt(plaintext, key);
        System.out.println("Encrypted: " + encrypted);

        // Decryption
        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted: " + decrypted);
    }
}
