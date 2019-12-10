package com.topscit.springboot1.util;

import java.util.Random;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5Util {
	public static String md5(String text,String salt)
	{
		Md5Hash md5Hash = new Md5Hash(text,salt);//盐�??
		String string = md5Hash.toString();
		return string;
	}
	public static String getRandomStr(int n)
	{
       char v[] = {
			'1','2','3','4','5','a','b','c','A','b','c','!'	
		};
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for(int i = 0; i < n; i++)
		{
			int nextInt = random.nextInt(12);
			sb.append(v[nextInt]);
		}
		return sb.toString();
		
	}
	
	
	public static void main(String[] args) {
		String randomStr = Md5Util.getRandomStr(6);
		System.out.println(randomStr);
		System.out.println(Md5Util.md5("123456",randomStr));
	}

	
}
