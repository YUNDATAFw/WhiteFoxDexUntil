package BaiFox;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class UserToken {

    public static String UserToken(String User, String Key, Long StartTime, Long EndTime, String ActivityID) {
        // 将传入参数拼接成字符串
        String rawToken = User + ":" + Key + ":" + StartTime + ":" + EndTime + ":" + ActivityID;
        // 使用Base64进行编码
        String encodedToken = Base64.getEncoder().encodeToString(rawToken.getBytes());
        // 返回token
        return encodedToken;
    }

    public static boolean IfToken(String token, String user) {
        try {
            // 对token进行解码
            String decodedToken = new String(Base64.getDecoder().decode(token));
            // 分割解码后的字符串
            String[] parts = decodedToken.split(":");
            // 判断token的用户是否和请求用户一致
            if (!parts[0].equals(user)) {
                return false;
            }
            // 判断token是否有效（这里简单判断是否过期）
            Long endTime = Long.parseLong(parts[3]);
            if (System.currentTimeMillis() > endTime) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String GetTokenData(String token, String user) {
        if (!IfToken(token, user)) {
            // 如果token的用户与请求用户不一致，或者token无效，则不解析
            return null;
        }
        try {
            // 对token进行解码
            String decodedToken = new String(Base64.getDecoder().decode(token));
            // 分割解码后的字符串
            String[] parts = decodedToken.split(":");
            // 创建一个map来存储解析结果
            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("User", parts[0]);
            dataMap.put("Key", parts[1]);
            dataMap.put("StartTime", parts[2]);
            dataMap.put("EndTime", parts[3]);
            dataMap.put("ActivityID", parts[4]);
            // 将map转换为json字符串
            String dataJson = dataMap.toString().replaceAll("=", ":").replaceAll(", ", ",\n");
            return dataJson;
        } catch (Exception e) {
            return null;
        }
    }
}
