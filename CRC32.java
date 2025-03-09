package BaiFox;

import java.util.zip.CRC32;

public class CRC32 {
    public static String GetCRC32(String text) {
        CRC32 crc32 = new CRC32();
        crc32.update(text.getBytes());
        long checksum = crc32.getValue();
        return Long.toHexString(checksum);
    }
}
