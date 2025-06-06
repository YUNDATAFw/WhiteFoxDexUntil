import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import android.content.ContentUris;

public class FilePickerUtil {

    private static final String TAG = "FilePickerUtil"; // 日志标签
    private static final int REQUEST_CODE_PICK_FILE = 1001; // 请求码，用于标识文件选择器的回调

    /**
     * 启动系统文件选择器的方法
     *
     * @param activity 当前的Activity实例
     */
    public static void pickFile(Activity activity) {
        // 创建一个Intent，用于启动文件选择器
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT); // 使用ACTION_GET_CONTENT来选择文件
        intent.addCategory(Intent.CATEGORY_OPENABLE); // 添加类别，允许选择可打开的文件
        intent.setType("*/*"); // 设置文件类型为所有类型，允许选择任何文件
        // 调用startActivityForResult启动文件选择器，并传入请求码
        activity.startActivityForResult(intent, REQUEST_CODE_PICK_FILE);
    }

    /**
     * 处理文件选择器的回调结果，获取文件路径
     *
     * @param requestCode 请求码
     * @param resultCode  结果码
     * @param data        回调返回的Intent
     * @param activity    当前的Activity实例
     * @return 文件的绝对路径，如果失败则返回错误信息
     */
    public static String handleActivityResult(int requestCode, int resultCode, Intent data, Activity activity) {
        // 检查请求码和结果码是否符合预期
        if (requestCode == REQUEST_CODE_PICK_FILE && resultCode == Activity.RESULT_OK) {
            // 如果回调数据不为空
            if (data != null) {
                // 从Intent中获取文件的Uri
                Uri uri = data.getData();
                if (uri != null) {
                    // 调用方法获取文件的绝对路径
                    String filePath = getPathFromUri(uri, activity);
                    if (filePath != null) {
                        return filePath;
                    } else {
                        Log.e(TAG, "Failed to get file path from Uri: " + uri);
                        return "Failed to get file path from Uri: " + uri;
                    }
                } else {
                    Log.e(TAG, "Uri is null");
                    return "Uri is null";
                }
            } else {
                Log.e(TAG, "Callback data is null");
                return "Callback data is null";
            }
        } else {
            Log.e(TAG, "Request code or result code does not match");
            return "Request code or result code does not match";
        }
    }
   
    
    

    /**
     * 根据Uri获取文件的绝对路径
     *
     * @param uri       文件的Uri
     * @param activity  当前的Activity实例
     * @return 文件的绝对路径，如果失败则返回错误信息
     */
    private static String getPathFromUri(Uri uri, Activity activity) {
        // 检查Uri的格式并处理
        if (DocumentsContract.isDocumentUri(activity, uri)) {
            // 如果Uri是Document类型的Uri
            return getPathFromDocumentUri(uri, activity);
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果Uri是Content类型的Uri
            return getDataColumn(activity, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果Uri是File类型的Uri
            return uri.getPath();
        } else {
            Log.e(TAG, "Unsupported Uri scheme: " + uri.getScheme());
            return "Unsupported Uri scheme: " + uri.getScheme();
        }
    }

    /**
     * 从Document类型的Uri获取文件路径
     *
     * @param uri       文件的Uri
     * @param activity  当前的Activity实例
     * @return 文件的绝对路径，如果失败则返回错误信息
     */
    private static String getPathFromDocumentUri(Uri uri, Activity activity) {
        String path = null;
        // 获取Uri的文档ID
        String docId = DocumentsContract.getDocumentId(uri);
        // 根据Uri的authority来处理不同的情况
        if ("com.android.externalstorage.documents".equals(uri.getAuthority())) {
            // 外部存储的Uri
            String[] parts = docId.split(":");
            if ("primary".equals(parts[0])) {
                path = Environment.getExternalStorageDirectory() + "/" + parts[1];
            } else {
                Log.e(TAG, "Unsupported storage type: " + parts[0]);
                return "Unsupported storage type: " + parts[0];
            }
        } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
            // 下载文件的Uri
            String id = DocumentsContract.getDocumentId(uri);
            Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            path = getDataColumn(activity, contentUri, null, null);
        } else if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
            // 多媒体文件的Uri
            String id = docId.split(":")[1];
            String type = docId.split(":")[0];
            Uri contentUri = null;
            if ("image".equals(type)) {
                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(type)) {
                contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(type)) {
                contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            } else {
                Log.e(TAG, "Unsupported media type: " + type);
                return "Unsupported media type: " + type;
            }
            String selection = "_id=?";
            String[] selectionArgs = new String[]{id};
            path = getDataColumn(activity, contentUri, selection, selectionArgs);
        } else {
            Log.e(TAG, "Unsupported authority: " + uri.getAuthority());
            return "Unsupported authority: " + uri.getAuthority();
        }
        if (path == null) {
            Log.e(TAG, "Failed to get path from DocumentUri: " + uri);
            return "Failed to get path from DocumentUri: " + uri;
        }
        return path;
    }

    /**
     * 从ContentResolver中获取文件路径
     *
     * @param context       当前的Context实例
     * @param uri           文件的Uri
     * @param selection     查询条件
     * @param selectionArgs 查询条件参数
     * @return 文件的绝对路径，如果失败则返回错误信息
     */
    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(columnIndex);
            } else {
                Log.e(TAG, "Failed to get data column from Uri: " + uri);
                return "Failed to get data column from Uri: " + uri;
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception while querying Uri: " + uri, e);
            return "Exception while querying Uri: " + uri + ", Error: " + e.getMessage();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}

