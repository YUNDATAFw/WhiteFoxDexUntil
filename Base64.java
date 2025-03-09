package BaiFox;

import java.util.Base64;

public class Base64 {
    public static String EnBase64(String text) {
        byte[] textByte = text.getBytes();
        String encodedfile = Base64.getEncoder().encodeToString(textByte);
        return encodedfile;
    }

    public static String DeBase64(String base64_text) {
        byte[] base64Decoded = Base64.getDecoder().decode(base64_text);
        return new String(base64Decoded);
    }
}
