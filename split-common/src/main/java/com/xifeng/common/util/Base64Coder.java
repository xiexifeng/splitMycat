package com.xifeng.common.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

import com.xifeng.common.constant.ConfigConst;
import com.xifeng.common.log.CommonLogger;
import com.xifeng.common.log.Log;
import com.xifeng.common.log.LogTemplate;

public class Base64Coder {
	private static String cmd = "common:Base64Coder";

	private static final Log logger = CommonLogger.getInstance();

	public static String encodeString(String content) {
		try {
			return new String(Base64.encodeBase64(content
					.getBytes(ConfigConst.DEFAULT_ENCODE)),
					ConfigConst.DEFAULT_ENCODE);
		} catch (UnsupportedEncodingException e) {
			logger.error(String.format(LogTemplate.COMMON_SYS_FAIL, cmd,
					"encode String " + content + " is failed"), e);
		}
		return null;
	}

	public static String decodeString(String content) {
		try {
			return new String(Base64.decodeBase64(content),
					ConfigConst.DEFAULT_ENCODE);
		} catch (UnsupportedEncodingException e) {
			logger.error(String.format(LogTemplate.COMMON_SYS_FAIL, cmd,
					"decode String " + content + " is failed"), e);
		}
		return null;
	}
}
