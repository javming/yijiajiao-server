package com.yijiajiao.server.filter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BufferedRequestWrapper extends HttpServletRequestWrapper {
    private ByteArrayInputStream       bais;
    private BufferedServletInputStream bsis;
    private byte[]  buffer;

    public BufferedRequestWrapper(HttpServletRequest req, int length) throws IOException {
        super(req);
        InputStream is = req.getInputStream();
        buffer = new byte[length];
        int pad = 0;
        while (pad < length) {
            pad += is.read(buffer, pad, length);
        }
    }

    public ServletInputStream getInputStream() {
        try {
            bais = new ByteArrayInputStream(buffer);
            bsis = new BufferedServletInputStream(bais);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bsis;
    }
}