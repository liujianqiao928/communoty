package com.custchina.shequdemo.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileTool {
	
	/**
	 * ������ ����1
	 * @param path �ļ�·��
	 * @param name �ļ�����
	 * @return
	 */
	public static String FILELIST(File path,String name) {
		
		String pathName = filePath(path, name);
		
		return pathName;
	}
	
	/**
	 * ������ ����2
	 * @param url �ļ�·�� 
	 * @param name �ļ�����
	 * @return
	 */
	public static String FILELIST(String url,String name) {
		
		File path = new File(url);
		
		String pathName = filePath(path, name);
		
		return pathName;
	}
	
	/**
	 * ��ѯ�ļ����������ļ���
	 * @param path �ļ�·��
	 * @return
	 */
	public static List<String> FILELIST(File path) {
		
		List<String> listName = new ArrayList<>();
		
		File[] fileList  = path.listFiles();
		
		for (File file : fileList) {
			String nameNew = StringUtils.substringBefore(file.getName(), ".");
			listName.add(nameNew);
		}
		return listName;
	}
	
	/**
	 * ������ 
	 * @param path
	 * @param name
	 * @return
	 */
	public static String filePath(File path,String name) {
		
		// ��ȡ�ļ����б�
		List<String> listname = FILELIST(path);
		
		int count = 0;
		
		// ��ȡ . ǰ���ļ���
		String nameNew = StringUtils.substringBefore(name, ".");
		
		Pattern pattern = Pattern.compile(nameNew); 
		Matcher matcher;
		
		for (String f : listname) {
			
			matcher = pattern.matcher(f);
			
			if(matcher.lookingAt()) {
				count++;
			}
			
		}
		String a = "";
		if(count == 0){
			a="";
		}else {
			a="("+count+")";
		}
		// ƴ������������ļ�����·��
		String pathName = path.getPath() + "\\" + nameNew + a + name.replace(nameNew, "");
		
		return pathName;
	}
	
	
	/**
	 * �ļ��ϴ�
	 * @param f1 ��Ҫ�ϴ����ļ�
	 * @param f2 ��ŵ�λ�ü�����
	 * @return
	 */
	public static Boolean FILEUPLOAD(MultipartFile f1, String f2) {
		boolean result = false;
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(f1.getInputStream(), 1024);
			
			out = new BufferedOutputStream(new FileOutputStream(f2), 1024);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			// ����ˢ��
			out.flush();
			in.close();
			out.close();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

}
