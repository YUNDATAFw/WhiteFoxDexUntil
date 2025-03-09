package BaiFox;

import java.security.MessageDigest;
import java.util.Base64;

public class Protect {
    public static String EN(String text, String token) {
        // 使用RC4加密
        byte[] encryptedBytes = RC4.encrypt(text.getBytes(), token.getBytes());
        // 加密结果使用base64编码
        String ciphertext = Base64.getEncoder().encodeToString(encryptedBytes);
        return ciphertext;
    }

    public static String DE(String ciphertext, String token) {
        // 把密文base64解编码
        byte[] decodedBytes = Base64.getDecoder().decode(ciphertext);
        // 然后再RC4解密
        byte[] decryptedBytes = RC4.decrypt(decodedBytes, token.getBytes());
        return new String(decryptedBytes);
    }

    public static String Token(String key) {
        // 把key经过md5加密后输出token
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(key.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                String hex = Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3);
                sb.append(hex);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Token生成失败";
        }
    }

    // RC4加密算法实现
    public static class RC4 {
        public static byte[] encrypt(byte[] data, byte[] key) {
            int[] S = new int[256];
            for (int i = 0; i < 256; i++) {
                S[i] = i;
            }
            int j = 0;
            for (int i = 0; i < 256; i++) {
                j = (j + S[i] + key[i % key.length]) % 256;
                int temp = S[i];
                S[i] = S[j];
                S[j] = temp;
            }
            byte[] encrypted = new byte[data.length];
            int i = 0, k = 0;
            for (int n = 0; n < data.length; n++) {
                i = (i + 1) % 256;
                k = (k + S[i]) % 256;
                int temp = S[i];
                S[i] = S[k];
                S[k] = temp;
                int t = (S[i] + S[k]) % 256;
                encrypted[n] = (byte) (data[n] ^ S[t]);
            }
            return encrypted;
        }

        public static byte[] decrypt(byte[] data, byte[] key) {
            return encrypt(data, key); // RC4加密和解密使用相同的方法
        }
    }
}
