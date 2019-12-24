package com.topscit.springboot1.util;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class uploadFile {

	public static String upload(MultipartFile fm,HttpServletRequest req){
		
		String path = null;
		//文件上传
		if(fm != null)
		{
			
			String realPath = req.getServletContext().getRealPath("/upload");
			File dir = new File(realPath);
			if(!dir.exists())
			{
				dir.mkdirs();
			}
			
			String fileName = System.currentTimeMillis() + fm.getOriginalFilename();
			
			File dest = new File(realPath + "/" +fileName);
			try {
				fm.transferTo(dest);
				
				path = "upload/"+fileName;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("文件存储失败");
			} 
			
		}
		return path;
	}
}
