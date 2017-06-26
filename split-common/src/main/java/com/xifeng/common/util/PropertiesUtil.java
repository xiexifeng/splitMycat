package com.xifeng.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

import com.xifeng.common.config.LocalConfig;
import com.xifeng.common.constant.ConfigConst;
import com.xifeng.common.log.CommonLogger;
import com.xifeng.common.log.Log;
import com.xifeng.common.log.LogTemplate;
import com.xifeng.common.tool.FileTool;

public class PropertiesUtil extends PropertyPlaceholderConfigurer{

    private static final Log logger = CommonLogger.getInstance();
	
	private String cmd = "util:configProperties";
	
	public static final String XML_FILE_EXTENSION = ".xml";

	private Resource[] locations; // 重新定义父类中的这个同名属性
    private boolean ignoreResourceNotFound = false; // 重新定义父类中的这个同名属性
    private PropertiesPersister propertiesPersister = new DefaultPropertiesPersister(); // 重新定义父类中的这个同名属性
    private String fileEncoding; // 配置文件编码
    private String configPath; // 配置文件目录
    private String[] configList; // 配置文件列表
    private static Map<String, String> propertiesMap = new HashMap<String, String>();
    private static int pwdType;


    public void init() {
        this.configPath = LocalConfig.CONFIG_PATH;
        this.initLocations(this.configPath);
    }

    /**
     * Set a location of a properties file to be loaded.
     * <p>
     * Can point to a classic properties file or to an XML file that follows JDK
     * 1.5's properties XML format.
     */
    public void setLocation(Resource location) {
        this.locations = new Resource[] { location };
    }

    /**
     * Set locations of properties files to be loaded.
     * <p>
     * Can point to classic properties files or to XML files that follow JDK
     * 1.5's properties XML format.
     * <p>
     * Note: Properties defined in later files will override properties defined
     * earlier files, in case of overlapping keys. Hence, make sure that the
     * most specific files are the last ones in the given list of locations.
     */
    public void setLocations(Resource[] locations) {
        this.locations = locations;
    }

    public void setIgnoreResourceNotFound(boolean ignoreResourceNotFound) {
        this.ignoreResourceNotFound = ignoreResourceNotFound;
    }

    /**
     * @return the configList
     */
    public String[] getConfigList() {
        return configList;
    }

    /**
     * @param configList the configList to set
     */
    public void setConfigList(String[] configList) {
        this.configList = configList;
    }

    
    /**
     * @return the fileEncoding
     */
    public String getFileEncoding() {
        return fileEncoding;
    }

    /**
     * @param fileEncoding the fileEncoding to set
     */
    public void setFileEncoding(String fileEncoding) {
        this.fileEncoding = fileEncoding;
    }
    
    
   

    protected void initLocations(String configPath) {
        if (FileTool.dirExists(configPath)) {
            this.locations = new FileSystemResource[configList.length];
            String[] loadfiles = new String[configList.length];
            
            for (int i = 0; i < configList.length; i++) {
                String file = configList[i];
                String fp = this.configPath + file;
                
                File f = new File(fp);
                if (null == f || !f.exists())  continue;
				
                loadfiles[i] = file;
                Resource location = new FileSystemResource(fp).createRelative(file);
                this.locations[i] = location;
            }
            logger.info(String.format(LogTemplate.COMMON_SYS_OK, cmd,"load resource from path "+ configPath + " success,"+Arrays.asList(loadfiles)));
        } else {
        	logger.error(String.format(LogTemplate.COMMON_SYS_FAIL, cmd,"Config Path "+configPath+"is not exists."));
        }

    }
    
    /**
     * 自定义加载配置文件方法
     */
    @Override
    protected void loadProperties(Properties props) throws IOException {
         if (this.locations != null) {
            for (Resource location : this.locations) {
            	if (null == location) {
					continue;
				}
                InputStream is = null;
                try {
                    is = location.getInputStream();
                    String filename = null;
                    try {
                        filename = location.getFilename();
                        logger.info(String.format(LogTemplate.COMMON_SYS_OK, cmd,"load file "+ filename + " is success"));
                    } catch (IllegalStateException ex) {
                        logger.error(String.format(LogTemplate.COMMON_SYS_FAIL, cmd,"load file "+ filename + " is fail"),ex);
                    }

                    if (filename != null && filename.endsWith(XML_FILE_EXTENSION)) {
                        this.propertiesPersister.loadFromXml(props, is);
                    } else {
                        if (this.fileEncoding != null) {
                            this.propertiesPersister.load(props, new InputStreamReader(is, this.fileEncoding));
                        } else {
                            this.propertiesPersister.load(props, is);
                        }
                    }
                } catch (IOException ex) {
                    if (this.ignoreResourceNotFound) {
                        logger.error(String.format(LogTemplate.COMMON_SYS_FAIL, cmd,"Could not load properties from " + location),ex);
                    } else {
                        throw ex;
                    }
                } finally {
                    if (is != null) {
                        is.close();
                    }
                }
            }
        }
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
        super.processProperties(beanFactory, props);
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = resolvePlaceholder(keyStr, props, SYSTEM_PROPERTIES_MODE_NEVER);
            propertiesMap.put(keyStr, value);
            //按公司安全规范，密码配置项不输出到日志文件  
            if(isPasswordField(keyStr)){
                logger.info(String.format(LogTemplate.COMMON_SYS_OK, cmd,"load propertie key/value in map key="+keyStr+" value=***"));
            }else{
                logger.info(String.format(LogTemplate.COMMON_SYS_OK, cmd,"load propertie key/value in map key="+keyStr+" value="+value));
            }
        }
    }

    
    
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        // 对密码进行解密
        try {
            if (pwdType == ConfigConst.PWD_TYPE_CIPHER) {
                Pattern regex = Pattern.compile("db\\..*\\.password");
                Matcher regexMatcher = regex.matcher(propertyName);
                if (regexMatcher.find()) {
                    String Key = ConfigConst.DB_PWD_ENCRTY_KEY;
                    return AES.decryptToStrFromBase64(propertyValue, Key);
                }
            }
        } catch (Exception e) {
            logger.error(String.format(LogTemplate.COMMON_SYS_FAIL, cmd,"decrypt password key="+ propertyName + " is fail"),e);
        }

        return super.convertProperty(propertyName, propertyValue);
    }

	@Override
	protected void convertProperties(Properties props) {
		pwdType = Integer.parseInt(props.getProperty("db.pwd.type", "0"));
		super.convertProperties(props);
	}

	/**
     * 以String型获取配置文件中指定key的值，获取失败默认将返回　""
     * 
     * @param name 配置文件中的key值
     * @return
     */
    public static String getProperty(String name) {
        if (StringUtil.isNotEmpty(name)) {
            String val = propertiesMap.get(name);
            return (StringUtil.isNotEmpty(val)) ? val.trim() : "";
        } else {
            return "";
        }
    }

    /**
     * 以String型获取配置文件中指定key的值
     * 
     * @param name 配置文件中的key值
     * @param dv 获取失败的默认值
     * @return
     */
    public static String getProperty(String name, String dv) {
        String val = getProperty(name);
        if (StringUtil.isNotEmpty(val)) {
            return val;
        } else {
            return dv;
        }
    }

    /**
     * 以int型获取配置文件中指定key的值，获取失败默认将返回0
     * 
     * @param name 配置文件中的key值
     * @return
     */
    public static int getPropertyInt(String name) {
        return getPropertyInt(name, 0);
    }

    /**
     * 以int型获取配置文件中指定key的值
     * 
     * @param name 配置文件中的key值
     * @param dv 获取失败的默认值
     * @return
     */
    public static int getPropertyInt(String name, int dv) {
        String val = getProperty(name);
        return StringUtil.String2Int(val, dv);
    }
    
   /**
    * 字符串转成数组
    * @param name
    * @param split
    * @return
    */
    public static String [] getPropertyArray(String name, String split) {
    	String val = getProperty(name);
    	split = StringUtil.isEmpty(split) ? "," : split;
    	if (StringUtil.isNotEmpty(val)) {
    		return val.split(split);
    	} else {
    		return new String[]{};
    	}
    }
    
    
   public static long getPropertyLong(String keystr){
	   return getPropertyLong(keystr,0);
   }
   
   public static long getPropertyLong(String name,long dv){
       String val = getProperty(name);
       return StringUtil.String2Long(val, dv);
   }
   
   private static boolean isPasswordField(String keystr){
       String key = keystr.toLowerCase();
       return (key.indexOf("superpwd")>0 || key.indexOf("password")>0 ||  key.indexOf("pwd")>0);
   }

}
