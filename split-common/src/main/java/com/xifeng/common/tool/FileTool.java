package com.xifeng.common.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileTool {
	public static boolean dirExists(String dirPath) {
		File f = new File(dirPath);
		return f.isDirectory();
	}

	public static String readFullText(String filePath) {
		StringBuilder sb = new StringBuilder(1000);
		File f = new File(filePath);
		if (f.exists()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(f);
				byte[] b = new byte[1024];
				int i = 0;
				while ((i = fis.read(b)) != -1) {
					sb.append(new String(b, 0, i, "utf-8"));
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return sb.toString();
	}
	
	public static void createProjectDir(String projectName,String rootDir,String org){
		String packageName = projectName.substring(projectName.indexOf("-")+1,projectName.length());
		String dao = org+"."+packageName+".dao";
		dao = dao.replace(".", "/");
		String component = org+"."+packageName+".component";
		component = component.replace(".", "/");
		String service = org+"."+packageName+".service";
		service = service.replace(".", "/");
		String model = org+"."+packageName+".model";
		model = model.replace(".", "/");
		
		String rootSrc = rootDir+projectName+"/src/main/java/";
		File f = new File(rootSrc+dao);
		f.mkdirs();
		f = new File(rootSrc+component);
		f.mkdirs();
		f = new File(rootSrc+service);
		f.mkdirs();
		f = new File(rootSrc+model);
		f.mkdirs();
		
		String rootRes = rootDir+projectName+"/src/main/resources/";
		String sql = "xml/sql";
		String mapping = "xml/dao/mapping";
		String lang = "xml/lang";
		String serviceRes = "xml/service";
		f = new File(rootRes+sql);
		f.mkdirs();
		f = new File(rootRes+mapping);
		f.mkdirs();
		f = new File(rootRes+lang);
		f.mkdirs();
		f = new File(rootRes+serviceRes);
		f.mkdirs();
	}
	public static void main(String[] args) {
		String projectName = "split-whale";
		String rootDir = "E:/xiezhb/workspace_discover/splitMycat/";
		String org = "com.xifeng";
		FileTool.createProjectDir(projectName, rootDir, org);
	}
}
