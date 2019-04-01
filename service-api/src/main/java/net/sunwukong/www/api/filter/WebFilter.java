package net.sunwukong.www.api.filter;


import com.sdkinfo.www.core.util.CharsetUtil;
import com.sdkinfo.www.core.util.StrUtil;
import com.sdkinfo.www.log.StaticLog;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 类说明:WEB过滤器
 *
 * @author ideacoding
 * create 2018-05-09 14:09
 * Email ：ideacoding@163.com
 **/
public class WebFilter implements Filter {
    /**
     * 是否启用XSS
     */
    private boolean enableXss = false;

    /**
     * 是否开启SQL
     */
    private boolean enableSQL = false;

    /**
     * 封装，不需要过滤URL路径列表
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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String path = httpServletRequest.getServletPath();

        WebHttpServletResponseWrapper responseWrapper = new WebHttpServletResponseWrapper((HttpServletResponse) response);

        if (!path.contains("swagger") && !path.contains("api-doc")){

            if (urlExclusion!=null){
                boolean isok = false;
                for (int i = 0; i < urlExclusion.size(); i++) {
                    if (path.contains(urlExclusion.get(i))) {
                        isok = true;
                    }
                }
                if (!isok){
                    if (enableParamPrint){
                        StaticLog.error("1.======请求路径：{}",path);
                    }
                    chain.doFilter(new WebHttpServletRequestWrapper((HttpServletRequest) request, enableXss, enableSQL, enableOnParam, enableDecrypt, decryptField, enableParamPrint, path), responseWrapper);
                    if (enableParamPrint){
                        StaticLog.error("3.======响应数据：{}",StrUtil.str(responseWrapper.getCopy(),CharsetUtil.UTF_8));
                    }
                }else {
                    StaticLog.error("已过滤:{}",path);
                    chain.doFilter(request, response);
                }

            }else {
                if (enableParamPrint){
                    StaticLog.error("1.======请求路径：{}",path);
                }
                chain.doFilter(new WebHttpServletRequestWrapper((HttpServletRequest) request, enableXss, enableSQL, enableOnParam, enableDecrypt, decryptField, enableParamPrint, path), responseWrapper);
                if (enableParamPrint){
                    StaticLog.error("3.======响应数据：{}",StrUtil.str(responseWrapper.getCopy(),CharsetUtil.UTF_8));
                }
            }

        }else {
            chain.doFilter(request,response);
        }

    }

    @Override
    public void destroy() {

    }

    public boolean isEnableXss() {
        return enableXss;
    }

    public void setEnableXss(boolean enableXss) {
        this.enableXss = enableXss;
    }

    public List<String> getUrlExclusion() {
        return urlExclusion;
    }

    public void setUrlExclusion(List<String> urlExclusion) {
        this.urlExclusion = urlExclusion;
    }

    public boolean isEnableOnParam() {
        return enableOnParam;
    }

    public void setEnableOnParam(boolean enableOnParam) {
        this.enableOnParam = enableOnParam;
    }

    public boolean isEnableDecrypt() {
        return enableDecrypt;
    }

    public void setEnableDecrypt(boolean enableDecrypt) {
        this.enableDecrypt = enableDecrypt;
    }

    public List<String> getDecryptField() {
        return decryptField;
    }

    public void setDecryptField(List<String> decryptField) {
        this.decryptField = decryptField;
    }

    public boolean isEnableParamPrint() {
        return enableParamPrint;
    }

    public void setEnableParamPrint(boolean enableParamPrint) {
        this.enableParamPrint = enableParamPrint;
    }

    public boolean isEnableSQL() {
        return enableSQL;
    }

    public void setEnableSQL(boolean enableSQL) {
        this.enableSQL = enableSQL;
    }
}