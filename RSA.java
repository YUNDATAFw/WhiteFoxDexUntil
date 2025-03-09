import java.security.*;
import java.util.Base64;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Cipher;

public class RSA {

    // 生成RSA密钥对
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048, new SecureRandom());
        return keyPairGenerator.generateKeyPair();
    }

    // 将公钥转换为字符串
    public static String getPublicKeyString(PublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    // 将私钥转换为字符串
    public static String getPrivateKeyString(PrivateKey privateKey) {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    // 将字符串形式的公钥转换为PublicKey对象
    public static PublicKey getPublicKeyFromString(String publicKeyString) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKeyString);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePublic(keySpec);
    }

    // 将字符串形式的私钥转换为PrivateKey对象
    public static PrivateKey getPrivateKeyFromString(String privateKeyString) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyString);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePrivate(keySpec);
    }

    // 使用公钥加密字符串
    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] cipherText = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }

    // 使用私钥解密字符串
    public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(cipherText);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(bytes));
    }

    // 拆分密钥为256份，最后16份为签名数据
    public static String[] splitKey(String keyString, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(keyString);
        int totalParts = 256;
        int signatureParts = 16;
        int dataParts = totalParts - signatureParts;

        // 计算每份数据的大小
        int partSize = (keyBytes.length + dataParts - 1) / dataParts; // 向上取整

        // 创建签名
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(keyBytes);
        byte[] signatureBytes = signature.sign();

        // 拆分密钥
        String[] parts = new String[totalParts];
        int offset = 0;
        for (int i = 0; i < dataParts; i++) {
            int end = Math.min(offset + partSize, keyBytes.length);
            byte[] partBytes = new byte[end - offset];
            System.arraycopy(keyBytes, offset, partBytes, 0, partBytes.length);
            parts[i] = Base64.getEncoder().encodeToString(partBytes);
            offset = end;
        }

        // 添加签名数据
        int signaturePartSize = (signatureBytes.length + signatureParts - 1) / signatureParts; // 向上取整
        offset = 0;
        for (int i = dataParts; i < totalParts; i++) {
            int end = Math.min(offset + signaturePartSize, signatureBytes.length);
            byte[] partBytes = new byte[end - offset];
            System.arraycopy(signatureBytes, offset, partBytes, 0, partBytes.length);
            parts[i] = Base64.getEncoder().encodeToString(partBytes);
            offset = end;
        }

        return parts;
    }

    // 合成密钥并校验签名
    public static String combineKey(String[] parts, PublicKey publicKey) throws Exception {
        int totalParts = 256;
        int signatureParts = 16;
        int dataParts = totalParts - signatureParts;

        // 合成密钥数据
        byte[] keyBytes = new byte[0];
        for (int i = 0; i < dataParts; i++) {
            if (parts[i] != null && !parts[i].isEmpty()) {
                byte[] partBytes = Base64.getDecoder().decode(parts[i]);
                keyBytes = concatenateByteArrays(keyBytes, partBytes);
            }
        }

        // 合成签名数据
        byte[] signatureBytes = new byte[0];
        for (int i = dataParts; i < totalParts; i++) {
            if (parts[i] != null && !parts[i].isEmpty()) {
                byte[] partBytes = Base64.getDecoder().decode(parts[i]);
                signatureBytes = concatenateByteArrays(signatureBytes, partBytes);
            }
        }

        // 校验签名
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(keyBytes);
        if (!signature.verify(signatureBytes)) {
            throw new SecurityException("Signature verification failed. The key may have been tampered with.");
        }

        return Base64.getEncoder().encodeToString(keyBytes);
    }

    // 辅助方法：拼接两个字节数组
    private static byte[] concatenateByteArrays(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    // 主方法：测试代码
    public static void main(String[] args) {
        try {
            // 生成密钥对
            KeyPair keyPair = generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // 获取公钥和私钥的字符串表示
            String publicKeyString = getPublicKeyString(publicKey);
            String privateKeyString = getPrivateKeyString(privateKey);

            System.out.println("Public Key String: " + publicKeyString);
            System.out.println("Private Key String: " + privateKeyString);

            // 拆分私钥
            String[] privateKeyParts = splitKey(privateKeyString, privateKey);
            System.out.println("Private Key Parts: " + java.util.Arrays.toString(privateKeyParts));

            // 合成私钥并校验签名
            String combinedPrivateKeyString = combineKey(privateKeyParts, publicKey);
            System.out.println("Combined Private Key String: " + combinedPrivateKeyString);

            // 测试加密和解密
            String plainText = "Hello, RSA!";
            String encryptedText = encrypt(plainText, publicKey);
            System.out.println("Encrypted Text: " + encryptedText);

            String decryptedText = decrypt(encryptedText, getPrivateKeyFromString(combinedPrivateKeyString));
            System.out.println("Decrypted Text: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
