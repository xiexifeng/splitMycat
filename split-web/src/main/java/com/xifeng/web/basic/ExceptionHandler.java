package com.xifeng.web.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.xifeng.common.constant.ConfigConst;
import com.xifeng.common.log.CommonLogger;
import com.xifeng.common.log.Log;
import com.xifeng.common.tool.NetHelper;
import com.xifeng.common.util.StringUtil;

public class ExceptionHandler implements HandlerExceptionResolver {
    private static String DEFAULT_ERROR_VIEW = "error";
    private static String DEFAULT_EXCEPTION_ATTR = "ex";
    private static Log log = CommonLogger.getInstance();
    
    
    private String defaultErrorView;
    private String exceptionAttribute;
    
    /**
     * @return the defaultErrorView
     */
    public String getDefaultErrorView() {
        if(StringUtil.isNotEmpty(defaultErrorView)){
            return defaultErrorView;
        }else{
            return DEFAULT_ERROR_VIEW;
        }
    }

    /**
     * @param defaultErrorView the defaultErrorView to set
     */
    public void setDefaultErrorView(String defaultErrorView) {
        this.defaultErrorView = defaultErrorView;
    }

    /**
     * @return the exceptionAttribute
     */
    public String getExceptionAttribute() {
        if(StringUtil.isNotEmpty(exceptionAttribute)){
            return exceptionAttribute;
        }else{
            return DEFAULT_EXCEPTION_ATTR;
        }
    }

    /**
     * @param exceptionAttribute the exceptionAttribute to set
     */
    public void setExceptionAttribute(String exceptionAttribute) {
        this.exceptionAttribute = exceptionAttribute;
    }
    

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put(getExceptionAttribute(), ex);
        String data = "";
        try {
            data = NetHelper.getStringByStream(request.getInputStream(), ConfigConst.DEFAULT_ENCODE);
        } catch (IOException e) {
            log.error(String.format("cmd=error:handler | url=%s | querystring=%s | ex=%s",request.getRequestURL(),request.getQueryString(),getStackMsg(ex)));
            return new ModelAndView(getDefaultErrorView(),model);
        }
        log.error(String.format("cmd=error:handler | url=%s | querystring=%s | data=%s | ex=%s",request.getRequestURL(),request.getQueryString(),data,getStackMsg(ex)));
        // 根据不同错误转向不同页面
        return new ModelAndView(getDefaultErrorView(),model);
    }
    
    public static String getStackMsg(Exception e) {  
         // StringWriter将包含堆栈信息
         StringWriter stringWriter = new StringWriter();
         //必须将StringWriter封装成PrintWriter对象，
         //以满足printStackTrace的要求
         PrintWriter printWriter = new PrintWriter(stringWriter);
         //获取堆栈信息
         e.printStackTrace(printWriter);
         //转换成String，并返回该String
         StringBuffer error = stringWriter.getBuffer();
         return error.toString();
     }  
      
    public  static String getStackMsg(Throwable e) {  
        // StringWriter将包含堆栈信息
        StringWriter stringWriter = new StringWriter();
        //必须将StringWriter封装成PrintWriter对象，
        //以满足printStackTrace的要求
        PrintWriter printWriter = new PrintWriter(stringWriter);
        //获取堆栈信息
        e.printStackTrace(printWriter);
        //转换成String，并返回该String
        StringBuffer error = stringWriter.getBuffer();
        return error.toString();
    }  

}
