package com.util;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author 王志远
 * 2013-1-28
 */
public class StringUtil extends StringUtils {
	public static String debugByte(String text) {
		if (text == null || text.length() == 0) {
			return "";
		}
		StringBuffer out = new StringBuffer();
		try {
			out.append("ori=" + text + "\r\n");
			out.append("new String=" + new String(text.getBytes()) + "\r\n");
			out.append("ori-utf-8=" + new String(text.getBytes(), "utf-8") + "\r\n");
			out.append("ori-gbk=" + new String(text.getBytes(), "gbk") + "\r\n");
			out.append("ori-8859-1=" + new String(text.getBytes(), "iso-8859-1") + "\r\n");
			out.append("gbk=" + new String(text.getBytes("gbk")) + "\r\n");
			out.append("gbk---utf-8=" + new String(text.getBytes("gbk"), "utf-8") + "\r\n");
			out.append("gbk---8859-1=" + new String(text.getBytes("gbk"), "iso-8859-1") + "\r\n");
			out.append("utf-8=" + new String(text.getBytes("utf-8")) + "\r\n");
			out.append("utf-8---gbk=" + new String(text.getBytes("utf-8"), "gbk") + "\r\n");
			out.append("utf-8---8859-1=" + new String(text.getBytes("utf-8"), "iso-8859-1") + "\r\n");
			out.append("iso-8859-1=" + new String(text.getBytes("iso-8859-1")) + "\r\n");
			out.append("iso-8859-1---gbk=" + new String(text.getBytes("iso-8859-1"), "gbk") + "\r\n");
			out.append("iso-8859-1---utf-8=" + new String(text.getBytes("iso-8859-1"), "utf-8") + "\r\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out.toString();
	}
	/**
	 * 计算两个字符串的相似度
	 * @param a
	 * @param b
	 * @return 返回百分比
	 */
	public static int calculateSemblance(String a, String b) {
		int arrays[][]; // 矩阵
		int asize = a.length();
		int bsize = b.length();		
		
		if (asize == 0) {
			return bsize;
		}
		if (bsize == 0) {
			return asize;
		}
		arrays = new int[asize + 1][bsize + 1];
		
		for (int i = 0; i <= asize; i++) { 
			arrays[i][0] = i;
		}
		for (int j = 0; j <= bsize; j++) { 
			arrays[0][j] = j;
		}
		for (int i = 1; i <= asize; i++) { 
			char ch1 = a.charAt(i - 1);
			for (int j = 1; j <= bsize; j++) {
				char ch2 = b.charAt(j - 1);
				int temp;
				if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2) {
					temp = 0;
				} else {
					temp = 1;
				}
				arrays[i][j] = MathUtil.min(arrays[i - 1][j] + 1, arrays[i][j - 1] + 1, arrays[i - 1][j - 1] + temp);
			}
		}
		return (int) ((1-(float)arrays[asize][bsize]/Math.max(a.length(), b.length()))*100);
	}
	
	/**
	 * 验证是否只由字母和数字组成 防注入
	 * @param str
	 * @return
	 */
	public static boolean isNumberOrChar(String str){
		Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 是否包含空值
	 * @param strs
	 * @return
	 */
	public static boolean isHasNull(String ...strs){
		for (String str : strs) {
			if(isBlank(str)){
				return true;
			}
		}
		return false;
	}


	/**
	 * 格式化数字为千分位显示；
	 * 
	 * @param 要格式化的数字
	 *            ；
	 * @return
	 */
	public static String fmtMicrometer(Number num) {
		return NumberFormat.getInstance().format(num);
	}
	

	
	

	public static void main(String agrs[]){
		String debugByte = StringUtil.debugByte("ֽʽքؾϱǷҭŚɝӦكˇ֢ҹք");
		System.out.println(debugByte);
	}
	
}
