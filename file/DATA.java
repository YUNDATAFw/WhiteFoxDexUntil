package BaiFox.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DATA {
    public static String GetData(String filePath) {
        // file 文件路径
        // 判断文件是否存在
        File file = new File(filePath);
        if (!file.exists()) {
            return "{\"error\":\"文件不存在\"}";
        }

        // 获取文件的详细信息
        String fileName = file.getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        String fileDirectory = file.getParent();
        long fileSize = file.length();

        // 获取文件创建时间和最后更新时间
        Path path = Paths.get(filePath);
        BasicFileAttributes attrs;
        try {
            attrs = Files.readAttributes(path, BasicFileAttributes.class);
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"error\":\"获取文件属性失败\"}";
        }
        Date creationTime = new Date(attrs.creationTime().toMillis());
        Date lastModifiedTime = new Date(attrs.lastModifiedTime().toMillis());

        // 获取文件MD5
        String fileMD5 = MD5.GETmd5(filePath);

        // 输出data,格式json
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data = String.format(
            "{\"fileName\":\"%s\",\"fileExtension\":\"%s\",\"fileDirectory\":\"%s\",\"fileSize\":%d,\"creationTime\":\"%s\",\"lastModifiedTime\":\"%s\",\"fileMD5\":\"%s\"}",
            fileName, fileExtension, fileDirectory, fileSize, sdf.format(creationTime), sdf.format(lastModifiedTime), fileMD5
        );
        return data;
    }
}
