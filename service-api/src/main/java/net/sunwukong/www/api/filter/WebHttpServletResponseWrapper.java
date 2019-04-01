package net.sunwukong.www.api.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 说明:
 *
 * @author Mick
 * @CreateDate 2018/6/23 18:03
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public class WebHttpServletResponseWrapper extends HttpServletResponseWrapper {
    private ServletOutputStream outputStream;
    private PrintWriter writer;
    private CustomServletOutputStream cout;

    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @throws IllegalArgumentException
     *          if the response is null
     */
    public WebHttpServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (writer != null) {
            throw new IllegalStateException("getWriter() has already been called on this response.");
        }

        if (outputStream == null) {
            outputStream = getResponse().getOutputStream();
            cout = new CustomServletOutputStream(outputStream);
        }
        return cout;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (outputStream != null) {
            throw new IllegalStateException("getOutputStream() has already been called on this response.");
        }

        if (writer == null) {
            cout = new CustomServletOutputStream(getResponse().getOutputStream());
            writer = new PrintWriter(new OutputStreamWriter(cout, getResponse().getCharacterEncoding()), true);
        }
        return writer;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (writer != null) {
            writer.flush();
        } else if (outputStream != null) {
            cout.flush();
        }
    }

    public byte[] getCopy() {
        if (cout != null) {
            return cout.getCopy();
        } else {
            return new byte[0];
        }
    }
}
