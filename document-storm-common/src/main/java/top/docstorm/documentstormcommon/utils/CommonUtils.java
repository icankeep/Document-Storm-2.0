package top.docstorm.documentstormcommon.utils;

import java.io.Closeable;

/**
 * @Description:
 * @author: passer
 * @version：2019/9/19
 */
public class CommonUtils {

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
