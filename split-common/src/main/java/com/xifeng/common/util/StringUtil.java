package com.xifeng.common.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;

import org.springframework.util.StringUtils;

import com.xifeng.common.constant.ConfigConst;

public class StringUtil extends StringUtils {

	public static boolean isNotEmpty(String s) {
		return !isEmpty(s);
	}

	public static int String2Int(String val, int dv) {
		try {
			if(StringUtil.isNotEmpty(val)){
				int v = Integer.valueOf(val);
				return v;
			}
		} catch (NumberFormatException e) {
		}
		return dv;
	}

	public static long String2Long(String val, long dv) {
		
		try {
			if(StringUtil.isNotEmpty(val)){
				long v = Long.valueOf(val);
				return v;
			}
		} catch (NumberFormatException e) {
		}
		return dv;
	}
		
	/**
	 * 进行html编码操作
	 * @param s 转码后的字符
	 * @return
	 */
	public static String encodeHTML(String s) {
		if (isNotEmpty(s)) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < s.length(); i++) {
				char ch = s.charAt(i);
				switch (ch) {
				case '&':
					sb.append("&amp;");
					break;
				case '<':
					sb.append("&lt;");
					break;
				case '>':
					sb.append("&gt;");
					break;
				case '"':
					sb.append("&quot;");
					break;
				default:
					sb.append(ch);
				}
			}
			return sb.toString();
		} else {
			return "";
		}
	}
	
	
	
	/**
	 * html解码 
	 * @param s 解码后的字符
	 * @return
	 */
	public static String decodeHTML(String s){
		s = s.replaceAll("&lt;","<");
		s = s.replaceAll("&gt;",">");
		s = s.replaceAll("&quot;","\"");
		s = s.replaceAll("&nbsp;"," ");
		s = s.replaceAll("&amp;", "&");
		s = s.replace("&#39;", "'");
        return s;
	}
	

	/**
	 * 
	 * getUUID32:(获取32位uuid). <br/>
	 *
	 * @author xiezbmf
	 * Date:2016年9月12日下午3:12:37 <br/>
	 * @return
	 */
	public static String getUUID32(){
	    UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        s = s.replace("-", "");
        return s;
	}
	
	/**
	 * 
	 * urlEncode:(采用utf-8，进行url编码). <br/>
	 *
	 * @author xiezbmf
	 * Date:2016年9月12日下午3:12:29 <br/>
	 * @param str
	 * @return
	 */
	public static String urlEncode(String str) {
		try {
			return URLEncoder.encode(str, ConfigConst.DEFAULT_ENCODE);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 
	 * urlDecode:(采用utf-8，进行url解码). <br/>
	 *
	 * @author xiezbmf
	 * Date:2016年9月12日下午3:13:40 <br/>
	 * @param str
	 * @return
	 */
	public static String urlDecode(String str) {
		try {
			return URLDecoder.decode(str, ConfigConst.DEFAULT_ENCODE);
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public static String getPrefixPadding(int num,int length,char prefix) throws Exception{
		return getPrefixPadding(String.valueOf(num),length,prefix);
	}
	
	public static String getPrefixPadding(String old,int length,char prefix) throws Exception{
		StringBuilder sb = new StringBuilder();
		int serLen = old.length();
		if (serLen <= length) {
			int len = length - serLen;
			for (int i = 0; i < len; i++) {
				sb.append(prefix);
			}
			sb.append(old);
			return sb.toString();
		}else {
			throw new Exception("serialNum length is bigger than set:"+length);
		}
	}
    public static void main(String[] args) {
		String s = "入账:还款-";
		System.out.println(s.length());
	}

}
