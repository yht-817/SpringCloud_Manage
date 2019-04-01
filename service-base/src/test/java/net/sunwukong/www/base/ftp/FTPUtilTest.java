package net.sunwukong.www.base.ftp;


import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class FTPUtilTest {

    @Test
    public void uploadFile() throws Exception {
        File file = new File("D:\\spider\\2018-07-26\\index.html");
        InputStream fileInputStream = new FileInputStream(file);
        String s = FTPUtil.uploadStreamFile(DirectoryEnums.CODE_100010001, fileInputStream);
        System.out.println(s);
    }

}