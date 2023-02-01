package cn.seheum.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源接口定义
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
