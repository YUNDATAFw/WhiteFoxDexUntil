package BaiFox.file;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String GETmd5(String filePath) {
        // file 文件完全路径（安卓）
        // 判断文件是否存在
        java.io.File file = new java.io.File(filePath);
        if (!file.exists()) {
            return null;
        }

        // 获取文件的md5值
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int numRead;
            while ((numRead = fis.read(buffer)) != -1) {
                md.update(buffer, 0, numRead);
            }
            byte[] mdBytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : mdBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
