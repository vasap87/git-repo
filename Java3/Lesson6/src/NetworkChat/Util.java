package NetworkChat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;

/**
 * Created by dmirty on 20/08/14.
 */
public class Util {
    static Logger log = LoggerFactory.getLogger(Util.class);
    static void closeResource(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                log.warn("Failed to close resource correctly");
            }
        }
    }
}
