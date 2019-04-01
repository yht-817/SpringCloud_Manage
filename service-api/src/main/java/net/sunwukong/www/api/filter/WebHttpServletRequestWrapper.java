package net.sunwukong.www.api.filter;


import com.alibaba.fastjson.JSONObject;
import com.sdkinfo.www.core.io.IoUtil;
import com.sdkinfo.www.core.support.WafKit;
import com.sdkinfo.www.core.util.CharsetUtil;
import com.sdkinfo.www.core.util.StrUtil;
import com.sdkinfo.www.extra.servlet.ServletUtil;
import com.sdkinfo.www.log.StaticLog;
import com.vdurmont.emoji.EmojiParser;
import net.sunwukong.www.api.crypto.symmetric.AES128UtilBase64;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.util.JSONUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 类说明:WEB过滤处理类
 *
 * @author ideacoding
 * create 2018-05-09 14:13
 * Email ：ideacoding@163.com
 **/
public class WebHttpServletRequestWrapper extends HttpServletRequestWrapper{

    /**
     * 是否启用XSS
     */
    private boolean enableXss = false;

    /**
     * 是否开启SQL
     */
    private boolean enableSQL = false;

    /**
     * 封装，不需要过滤URL路径列表(XSS)
     */
    protected List<String> urlExclusion = null;

    /**
     * 是否开启多种参数传输方式（true：同时接收APP和WEB端参数    false：只接收APP端参数）
     */
    private boolean enableOnParam = false;

    /**
     * 是否开启参数解密
     */
    private boolean enableDecrypt = false;

    /**
     * 封装,需要解密的字段
     */
    protected List<String> decryptField = null;

    /**
     * 是否启用参数打印
     */
    private boolean enableParamPrint = false;

    /**
     * 请求路径
     */
    private String path = null;

    /**
     * 构造函数
     * @param request
     * @param enableXss         是否开启XSS
     * @param enableSQL         是否开启SQL
     * @param enableOnParam     是否开启多参数传输
     * @param enableDecrypt     是否开启参数解密
     * @param decryptField      解密字段
     * @param enableParamPrint  是否开启参数打印
     */
    public WebHttpServletRequestWrapper(HttpServletRequest request,boolean enableXss,boolean enableSQL,boolean enableOnParam,boolean enableDecrypt,List<String> decryptField,boolean enableParamPrint,String path) {
        super(request);
        this.decryptField = decryptField;
        this.enableXss = enableXss;
        this.enableOnParam = enableOnParam;
        this.enableDecrypt = enableDecrypt;
        this.decryptField = decryptField;
        this.enableParamPrint = enableParamPrint;
        this.path = path;
    }

    /**
     * 数组参数过滤
     * @param parameter 过滤参数
     * @return
     */
    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if ( values == null ) {
            return null;
        }

        int count = values.length;
        String[] encodedValues = new String[count];
        for ( int i = 0 ; i < count ; i++ ) {
            encodedValues[i] = filterParamString(values[i]);
        }

        return encodedValues;
    }




    @Override
    public Map<String, String[]> getParameterMap() {
        return super.getParameterMap();
    }

    @Override
    public BufferedReader getReader() throws IOException {
        BufferedReader reader = null;
        String jsonBodyStr = IoUtil.read(super.getReader());
        if (StrUtil.isNotBlank(jsonBodyStr)){
            JSONObject jsonBody = JSONObject.parseObject(jsonBodyStr);
            decryptField.forEach(field->{
                jsonBody.put(field, filterFieldString(String.valueOf(jsonBody.get(field))));
            });
            reader = IoUtil.getReader(IoUtil.toStream(jsonBody.toString(), CharsetUtil.CHARSET_UTF_8), CharsetUtil.CHARSET_UTF_8);
        }
        return reader;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        ServletInputStream servletInputStream = null;
        String jsonBodyStr = IoUtil.read(super.getInputStream(), "UTF-8");
        if (StrUtil.isNotBlank(jsonBodyStr)&&!"{}".equals(jsonBodyStr)){
            JSONObject jsonBody = JSONObject.parseObject(jsonBodyStr);
            if (enableDecrypt){
                decryptField.forEach(field->{
                    jsonBody.put(field, filterFieldString(String.valueOf(jsonBody.get(field))));
                });
            }
            if (enableParamPrint){
                StaticLog.error("2.======请求参数：{}",jsonBody);
            }
            byte[] body = jsonBody.toString().getBytes();
            servletInputStream = getServletInputStream(body);
        }else {
            if (enableOnParam){
                //将URL参数转换为body参数
                servletInputStream =  getServletInputStream(paramToBody());
            }else {
                servletInputStream = getServletInputStream(jsonBodyStr.getBytes());
            }
        }
        return servletInputStream;
    }

    /**
     * 过滤字符串内容（XSS SQL）
     * @param rawValue  待处理字符
     * @return
     */
    protected String filterParamString(String rawValue) {
        if (null == rawValue) {
            return null;
        }
        String tmpStr = rawValue;
        //是否开启XSS过滤
        if (this.enableXss) {
            if (urlExclusion == null || !urlExclusion.contains(path)) {
                tmpStr = WafKit.stripXSS(rawValue);
            }
        }
        //是否开启SQL过滤
        if (this.enableSQL) {
            tmpStr = WafKit.stripSqlInjection(tmpStr);
        }
        //转换表情符号
        tmpStr = EmojiParser.parseToAliases(tmpStr);
        return tmpStr;
    }

    /**
     * 解密value
     * @param rawValue  待解密字符
     * @return
     */
    protected JSONObject filterFieldString(String rawValue){
        String tmpStr = rawValue;
        //是否开启参数解密
        if (this.enableDecrypt) {
            tmpStr = AES128UtilBase64.decryptAES(rawValue);
        }
        tmpStr = filterParamString(tmpStr);
        return JSONObject.parseObject(tmpStr);
    }

    /**
     * 将修改后的值转换为ServletInputStream
     * @param body  修改后的body[]值
     * @return  返回ServletInputStream对象
     * @throws IOException  统一抛出异常
     */
    private ServletInputStream getServletInputStream(byte[] body) throws IOException {
        return new ServletInputStream() {
            private int lastIndexRetrieved = -1;
            private ReadListener readListener = null;
            @Override
            public boolean isFinished() {
                return (lastIndexRetrieved == body.length-1);
            }

            @Override
            public boolean isReady() {
                // This implementation will never block
                // We also never need to call the readListener from this method, as this method will never return false
                return isFinished();
            }

            @Override
            public void setReadListener(ReadListener listener) {
                this.readListener = listener;
                if (!isFinished()) {
                    try {
                        readListener.onDataAvailable();
                    } catch (IOException e) {
                        readListener.onError(e);
                    }
                } else {
                    try {
                        readListener.onAllDataRead();
                    } catch (IOException e) {
                        readListener.onError(e);
                    }
                }
            }

            @Override
            public int read() throws IOException {
                int i;
                if (!isFinished()) {
                    i = body[lastIndexRetrieved+1];
                    lastIndexRetrieved++;
                    if (isFinished() && (readListener != null)) {
                        try {
                            readListener.onAllDataRead();
                        } catch (IOException ex) {
                            readListener.onError(ex);
                            throw ex;
                        }
                    }
                    return i;
                } else {
                    return -1;
                }
                //return IoUtil.toStream(jsonBody.toString(), "utf-8").read();
            }
        };
    }

    /**
     * 获取URL参数，封装成入参JSON字符 转为byte
     * @return  返回封装后的byte[]
     */
    private byte[] paramToBody() {
        Map<String, String> paramMap = ServletUtil.getParamMap(getRequest());
        //过滤字符串（XSS SQL）
        paramMap.forEach((s, s2) -> {
            paramMap.put(s,filterParamString(s2));
        });
        RequestData<Map> requestData = new RequestData<>();
        requestData.setData(paramMap);
        if(enableParamPrint){
            StaticLog.error("2.======请求参数：{}",requestData);
        }
        return JSONUtil.bean2json(requestData).getBytes();
    }
}
