package com.xifeng.common.tool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import javax.servlet.http.HttpServletRequest;

import com.xifeng.common.constant.CommonConst;
import com.xifeng.common.constant.ConfigConst;
import com.xifeng.common.log.CommonLogger;
import com.xifeng.common.log.Log;
import com.xifeng.common.log.LogTemplate;
import com.xifeng.common.util.StringUtil;

public class NetHelper {
	private static final Log log = CommonLogger.getInstance();
    private static int BUFFER_SIZE = 1024; // 缓冲区最大字节数
    private static String cmd = "common:NetHelper";
    
    
    /**
     * getIpAddr:
     * 获取Ip地址，先取X-Forwarded-For地址，如果为空， <br>
     * 取Proxy-Client-IP，如果为空，再取<br>
     * WL-Proxy-Client-IP，如果为空，再取request.getRemoteAddr() <br>
     * 注意：如果以上任何一种方式获取到的ip中有, 逗号分隔出的多个IP地址，则取第一个合法的IP地址（非unknown的地址)
     *
     * @author xiezbmf
     * Date:2016年9月12日下午4:38:46 <br>
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (isUnknownIp(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (isUnknownIp(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isUnknownIp(ip)) {
            ip = request.getRemoteAddr();
        }
        if (StringUtil.isNotEmpty(ip)) {
            String[] ips = ip.split(",");
            for (int i = 0; i < ips.length; i++) {
                if (ips[i] != null && !"".equals(ips[i])
                        && !"unknown".equalsIgnoreCase(ips[i])) {
                    ip = ips[i];
                    break;
                }

            }
        }
        return ip;
    }
    
    private static boolean isUnknownIp(String ip){
    	return StringUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip);
    }
    
   
    /**
     * getByteByStream:(普通输入流转化为字节数组). <br>
     *
     * @author xiezbmf
     * Date:2016年9月12日下午4:44:54 <br>
     * @param is
     * @return
     */
    public static byte[] getByteByStream(InputStream is) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            byte[] buf = new byte[BUFFER_SIZE];
            int num;
            while ((num = is.read(buf, 0, buf.length)) != -1) {
                out.write(buf, 0, num);
            }
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
        	log.error(String.format(LogTemplate.COMMON_SYS_FAIL, cmd,"getByteByStream is failed"), e);
            return null;
        } finally {
            try {
                if (out != null) {
                    out.close();
                    out = null;
                }
            } catch (IOException e) {
                log.error(String.format(LogTemplate.COMMON_SYS_FAIL, cmd,"getByteByStream close outstream is failed"), e);
            }
        }
    }

   
    /**
     * getStringByStream:(将流转成字符串，使用默认编码). <br>
     * @see ConfigConst.DEFAULT_ENCODE
     *
     * @author xiezbmf
     * Date:2016年9月12日下午4:46:01 <br>
     * @param is
     * @return
     */
    public static String getStringByStream(InputStream is) {
        return getStringByStream(is, ConfigConst.DEFAULT_ENCODE);
    }

   
    /**
     * getStringByStream:(将流转成字符串,可以指定字符串编码). <br>
     *
     * @author xiezbmf
     * Date:2016年9月12日下午4:47:04 <br>
     * @param is
     * @param encode
     * @return
     */
    public static String getStringByStream(InputStream is, String encode) {
        byte[] bytes = getByteByStream(is);
        return getStringByBytes(bytes, encode);
    }
    
    
    /**
     * getStringByBytes:(将字节数组转成字符串，使用默认编码). <br>
     * @see ConfigConst.DEFAULT_ENCODE
     * 
     * @author xiezbmf
     * Date:2016年9月12日下午4:47:47 <br>
     * @param bytes
     * @return
     */
    public static String getStringByBytes(byte[] bytes){
        return getStringByBytes(bytes,ConfigConst.DEFAULT_ENCODE);
    }
    
   
    /**
     * getStringByBytes:(将字节数组转成字符串，可以指定编码). <br>
     *
     * @author xiezbmf
     * Date:2016年9月12日下午4:48:55 <br>
     * @param bytes
     * @param encode
     * @return
     */
    public static String getStringByBytes(byte[] bytes, String encode) {
        if (bytes != null) {
            try {
                return new String(bytes, encode);
            } catch (UnsupportedEncodingException e) {
                log.error(String.format(LogTemplate.COMMON_SYS_FAIL, cmd,"getStringByBytes is failed"), e);
            }
        }
        return "";
    }
    
    

    /**
     * 
     * httpPostData:(http post 请求，提交数据采用系统配置默认编码). <br>
     * @see ConfigConst.DEFAULT_ENCODE
     * @author xiezbmf
     * Date:2016年9月12日下午4:49:44 <br>
     * @param requestUrl 请求url
     * @param cookie 请求cookie
     * @param data 提交内容
     * @return
     */
    public static String httpPostData(String requestUrl, String cookie,String data) {
        TimeCount tc = new TimeCount();
        tc.start();
        HttpURLConnection httpConn = null;
        OutputStream outPs = null;
        InputStream inPs = null;
        int responseCode = -1;
        try {
            URL url = new URL(requestUrl);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setConnectTimeout(CommonConst.HTTP_CONN_TIMEOUT);
            httpConn.setReadTimeout(CommonConst.HTTP_READ_TIMEOUT);
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml");
            httpConn.setRequestProperty("Accept-Encoding", "gzip,deflate");
//            httpConn.setRequestProperty("Content-type", "text/javascript;charset=UTF-8");
            httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            
            // 如果有Cookie，Cookie带过去
            if (!StringUtil.isEmpty(cookie)) {
            	httpConn.setRequestProperty("Cookie", cookie);
            }
            httpConn.connect();
			if(data!=null){
				OutputStream os = httpConn.getOutputStream();
				os.write(data.getBytes(ConfigConst.DEFAULT_ENCODE));
			}
            inPs = (InputStream) httpConn.getInputStream();
            responseCode = httpConn.getResponseCode();
            String encoding = httpConn.getHeaderField("Content-Encoding");
            byte[] bytes = null;
            // 读取post之后的返回值,Stream转化为字符串
            // 如果是Content-Encoding=gzip需要用解压方式
            if (!StringUtil.isEmpty(encoding) && encoding.equalsIgnoreCase("gzip")) {
                bytes = unzip(inPs);
            } else {
                // 直接读取输入流转化为字符串
                bytes = getByteByStream(inPs);
            }
            if (bytes != null) {
                String result = new String(bytes, ConfigConst.DEFAULT_ENCODE);
                bytes = null;
                return result;
            }
            return null;
        } catch (Exception e) {
        	log.error(String.format(LogTemplate.COMMON_SYS_FAIL, cmd,"httpPostData is failed，responseCode is "+responseCode), e);
        } finally {
            try {
                if (inPs != null) {
                    inPs.close();
                    inPs = null;
                }
            } catch (IOException e) {
            	log.error(String.format(LogTemplate.COMMON_SYS_FAIL, cmd,"httpPostData close input stream is failed"), e);
            }

            try {
                if (outPs != null) {
                    outPs.close();
                    outPs = null;
                }
            } catch (IOException e) {
            	log.error(String.format(LogTemplate.COMMON_SYS_FAIL, cmd,"httpPostData close output stream is failed"), e);
            }

            httpConn.disconnect();
            httpConn = null;
        }
        return null;
    }
    
    

   
    /**
     * unzip:(GZIPInputStream 解压). <br>
     *
     * @author xiezbmf
     * Date:2016年9月12日下午4:51:21 <br>
     * @param in
     * @return
     */
    private static byte[] unzip(InputStream in) {
        GZIPInputStream gin = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            gin = new GZIPInputStream(in);
            byte[] buf = new byte[BUFFER_SIZE];
            int num;
            while ((num = gin.read(buf, 0, buf.length)) != -1) {
                out.write(buf, 0, num);
                out.flush();
            }
            return out.toByteArray();
        } catch (IOException e) {
        	log.error(String.format(LogTemplate.COMMON_SYS_FAIL, cmd,"unzip is failed"), e);
            return out.toByteArray();
        } finally {
            try {
                if (out != null) {
                    out.close();
                    out = null;
                }
            } catch (IOException e) {
            	log.error(String.format(LogTemplate.COMMON_SYS_FAIL, cmd,"unzip close out is failed"), e);
            }
            try {
                if (gin != null) {
                    gin.close();
                    gin = null;
                }
            } catch (IOException e) {
            	log.error(String.format(LogTemplate.COMMON_SYS_FAIL, cmd,"unzip close out gin is failed"), e);
            }

        }
    }
}
