package BaiFox.file;

import java.io.File;

public class Delete {
    public static boolean DeleteFolder(String dir, int type) {
        // 删除文件夹
        // dir文件夹目录  type类型1保留父文件夹 0不保留父文件夹
        // 判断文件夹路径是否存在
        File folder = new File(dir);
        if (!folder.exists() || !folder.isDirectory()) {
            return false;
        }

        // 删除文件夹下的文件
        deleteFiles(folder);

        // 根据type决定是否删除父文件夹
        if (type == 0) {
            if (folder.delete()) {
                return true;
            } else {
                return false;
            }
        } else if (type == 1) {
            return true;
        } else {
            return false;
        }
    }

    private static void deleteFiles(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFiles(file);
                }
                if (!file.delete()) {
                    //System.out.println("删除文件 " + file.getAbsolutePath() + " 时发生错误");
                }
            }
        }
    }

    public static boolean DeleteFile(String filePath) {
        // 判断文件是否存在
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        }

        // 删除文件
        if (file.delete()) {
            return true;
        } else {
            return false;
        }
    }
}
