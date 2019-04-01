package net.sunwukong.www.api.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 说明:
 *
 * @author Mick
 * @CreateDate 2018/6/24 16:00
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public class CustomServletOutputStream extends ServletOutputStream {

    private OutputStream outputStream;
    private ByteArrayOutputStream byteArrayOutputStream;

    public CustomServletOutputStream(OutputStream outputStream){
        this.outputStream=outputStream;
        byteArrayOutputStream=new ByteArrayOutputStream();
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }

    @Override
    public void write(int b) throws IOException {
        outputStream.write(b);
        byteArrayOutputStream.write(b);
    }

    public byte[] getCopy(){
        return byteArrayOutputStream.toByteArray();
    }
}
