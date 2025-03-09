package BaiFox.file;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

public class File_To_16 {
    public static String File_To(String filePath) {
        // file文件路径
        // 把文件转为16进制
        // 用空格分开，头尾有中括号，如：[A6 2F 3C 55 3A]
        StringBuilder hexString = new StringBuilder();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            byte[] buffer = new byte[1024];
            int numRead;
            while ((numRead = fis.read(buffer)) != -1) {
                for (int i = 0; i < numRead; i++) {
                    hexString.append(String.format("%02X ", buffer[i]));
                }
            }
            return "[" + hexString.toString().trim() + "]";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
    
