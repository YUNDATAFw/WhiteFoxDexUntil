package BaiFox;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class md5 {
    public static String Getmd5(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(text.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                String hex = Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3);
                sb.append(hex);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "MD5处理失败";
        }
    }
}
