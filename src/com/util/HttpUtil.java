package com.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.mina.util.Base64;


public class HttpUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HttpUtil.class);

	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String decodeBase64(String b64string) throws Exception {
		return new String(Base64.decodeBase64(b64string.getBytes()));
	}

	public static byte[] decodeByteBase64(byte[] base64) {
		return new Base64().decode(base64);
	}

	public static String encodeBase64(String stringsrc) {
		Base64 base64encode = new Base64();
		return new String(base64encode.encode(stringsrc.getBytes()));
	}

	public static byte[] encodeByteBase64(byte[] bytes) {
		Base64 base64encode = new Base64();
		return base64encode.encode(bytes);
	}

	public static void main(String[] args) throws Exception {
//		String t = "c2lkPTEmdWlkPTUwOTM0MiZpcD0xMTkuMTYxLjE1Ni4yOCZ0aW1lPTEyOTg4NzM0OTEmaW5kdWxnZT1u";
//		logger.info(decodeBase64(t));
//		try {
//			// post("http://www.baidu.com/s", createUrlParam("wd", "秦美人"));
//			// wget("http://www.baidu.com/s?wd=秦美人&a2=33");
//			String postr = wgetstr("http://192.168.1.99:8888/login/mg/reload.jsp?starid=1&callback=http://192.168.1.99:8888");
//			System.out.println(postr);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		long currentTimeMillis = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			String wget = wget(" http://192.168.1.99:8080/TestGame/QueryServlet?device="+RandomUtils.random(5000000));
			
		}
		System.out.println(System.currentTimeMillis()-currentTimeMillis);
		
		
		
	}

	public static String Md5(String s) {

		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param urladdress
	 *            http://www.baidu.com/s
	 * @param param
	 *            "aa=22&bb=33"
	 * @return
	 * @throws Exception
	 */
	public static boolean post(String urladdress, String param) throws Exception {
		HttpURLConnection uc = null;
		try {
			URL url = new URL(urladdress);

			uc = (HttpURLConnection) url.openConnection();
			uc.setDoInput(true);
			uc.setDoOutput(true);
			uc.setInstanceFollowRedirects(true); // 不允许重定向
			uc.setRequestMethod("POST");
			uc.setConnectTimeout(5000); // 五秒连接超时
			uc.setReadTimeout(5000); // 5秒返回超时
			uc.getOutputStream().write(param.getBytes());
			uc.connect();
			int t = uc.getResponseCode();
			logger.debug("发送到" + urladdress + "\r\n resultcode=" + t);
			// if(logger.isDebugEnabled()){
			try {
				String responseMessage = uc.getResponseMessage();
				BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
				String lines = "";
				while (reader.ready()) {
					lines += reader.readLine();
				}
				reader.close();
				logger.debug("返回值" + t + " " + responseMessage + " \n" + lines);
			} catch (Exception e) {
				logger.error(e, e);
			}
			// }
			return true;

		} catch (Exception e) {
			logger.error("异常" + urladdress, e);
			if (e instanceof ConnectException) {
				logger.error(e);
			} else {
				logger.error(e, e);
			}
		} finally {
			if (uc != null && uc.getInputStream() != null) {
				// uc.disconnect();//释放资源，并有可能影响到持久连接
				uc.getInputStream().close();// 只释放实例资源，不会影响持久连接
			}
		}
		return false;
	}

	
	public static boolean post(String urladdress,byte[] bytes) throws IOException{
		HttpURLConnection uc = null;
		try {
			URL url = new URL(urladdress);

			uc = (HttpURLConnection) url.openConnection();
			uc.setDoInput(true);
			uc.setDoOutput(true);
			uc.setInstanceFollowRedirects(true); // 不允许重定向
			uc.setRequestMethod("POST");
			uc.setRequestProperty("Cookie", "JSESSIONID=C0B910DC8EF9FDDC46156DFD0515200C");
//			uc.setConnectTimeout(5000); // 五秒连接超时
//			uc.setReadTimeout(5000); // 5秒返回超时
			uc.getOutputStream().write(bytes);			
			uc.connect();
			int t = uc.getResponseCode();
			logger.info("发送到" + urladdress + "\r\n resultcode=" + t);
			// if(logger.isDebugEnabled()){
			try {
				Set<Entry<String, List<String>>> entrySet = uc.getHeaderFields().entrySet();
				for (Entry<String, List<String>> entry : entrySet) {
				}
				
				
				
				String responseMessage = uc.getResponseMessage();
				 ByteArrayOutputStream output = new ByteArrayOutputStream();  
			       try {  
			           byte[] buffer = new byte[1024];  
			           int len = 0;  
			           while((len = uc.getInputStream().read(buffer)) != -1) {  
			              output.write(buffer, 0, len);  
			           }  
			       } catch (IOException e) {  
			       }  
			       byte[] byteArray = output.toByteArray();
			       for (byte b : byteArray) {
			    	   System.out.print(b+",");
			       }
					
//			       return output.toByteArray();  		
				logger.info("返回值" + t + " " + responseMessage + " \n" +" "+byteArray.length);
			} catch (Exception e) {
				logger.error(e, e);
			}
			// }
			return true;

		} catch (Exception e) {
			logger.error("异常" + urladdress, e);
			if (e instanceof ConnectException) {
				logger.error(e);
			} else {
				logger.error(e, e);
			}
		} finally {
			if (uc != null && uc.getInputStream() != null) {
				// uc.disconnect();//释放资源，并有可能影响到持久连接
				uc.getInputStream().close();// 只释放实例资源，不会影响持久连接
			}
		}
		return false;
	}
	
	/**
	 * @example http://www.baidu.com/s?wd=秦美人&a2=33
	 * @param urladdress
	 * @return
	 * @throws Exception
	 */
	public static String wget(String urladdress) throws Exception {
		HttpURLConnection uc = null;
		try {
			URL url = new URL(urladdress);
			uc = (HttpURLConnection) url.openConnection();
			uc.setInstanceFollowRedirects(false); // 不允许重定向
			uc.setRequestMethod("GET");
			uc.setConnectTimeout(5000); // 10秒超时
			uc.setReadTimeout(5000); // 10秒超时
			uc.connect();
			int t = uc.getResponseCode();
//			logger.debug("发送日志" + urladdress);
			String lines = "";
			if (logger.isDebugEnabled()) {
//				String responseMessage = uc.getResponseMessage();
				BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
				
				while (reader.ready()) {
					lines += reader.readLine();
				}
				reader.close();
//				System.out.println("返回信息" + t + " " + responseMessage + " \n" + lines);
			}
			return lines;
		} catch (Exception e) {
			logger.debug("发送日志出错" + urladdress);
			if (e instanceof ConnectException) {
				logger.error(e);
			} else {
				logger.error(e, e);
			}
		} finally {
			if (uc != null && uc.getInputStream() != null) {
				// uc.disconnect();//释放资源，并有可能影响到持久连接
				uc.getInputStream().close();// 只释放实例资源，不会影响持久连接
			}
		}
		return "";
	}

	public static String wgetstr(String urladdress) throws IOException {
		HttpURLConnection uc = null;
		try {
			URL url = new URL(urladdress);
			uc = (HttpURLConnection) url.openConnection();
			uc.setInstanceFollowRedirects(false); // 不允许重定向
			uc.setRequestMethod("GET");
			uc.setConnectTimeout(5000); // 10秒超时
			uc.setReadTimeout(5000); // 10秒超时
			uc.connect();
			int t = uc.getResponseCode();
			logger.debug("发送日志" + urladdress);
			String responseMessage = uc.getResponseMessage();
			BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String lines = "";
			while (reader.ready()) {
				lines += reader.readLine();
			}
			reader.close();
			logger.debug("返回信息" + t + " " + responseMessage + " \n" + lines);
			return lines;
		} catch (Exception e) {
			logger.debug("发送日志出错" + urladdress);
			if (e instanceof ConnectException) {
				logger.error(e);
			} else {
				logger.error(e, e);
			}
		} finally {
			if (uc != null && uc.getInputStream() != null) {
				// uc.disconnect();//释放资源，并有可能影响到持久连接
				uc.getInputStream().close();// 只释放实例资源，不会影响持久连接
			}
		}
		return "";
	}

	/**
	 * 
	 * @param urladdress
	 *            http://www.baidu.com/s
	 * @param param
	 *            "aa=22&bb=33"
	 * @return
	 * @throws Exception
	 */
	public static String postr(String urladdress, String param) throws Exception {
		HttpURLConnection uc = null;
		try {
			URL url = new URL(urladdress);

			uc = (HttpURLConnection) url.openConnection();
			uc.setDoInput(true);
			uc.setDoOutput(true);
			uc.setInstanceFollowRedirects(true); // 不允许重定向
			uc.setRequestMethod("POST");
			uc.setConnectTimeout(5000); // 五秒连接超时
			uc.setReadTimeout(5000); // 5秒返回超时
			uc.getOutputStream().write(param.getBytes());
			uc.connect();
			int t = uc.getResponseCode();
			logger.debug("发送到" + urladdress + "\r\n resultcode=" + t);
			// if(logger.isDebugEnabled()){
			String lines = "";
			try {
				String responseMessage = uc.getResponseMessage();
				BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));

				while (reader.ready()) {
					lines += reader.readLine();
				}
				reader.close();
				logger.debug("返回值" + t + " " + responseMessage + " \n" + lines);
			} catch (Exception e) {
				logger.error(e, e);
			}
			// }
			return lines;

		} catch (Exception e) {
			logger.error("异常" + urladdress, e);
			if (e instanceof ConnectException) {
				logger.error(e);
			} else {
				logger.error(e, e);
			}
		} finally {
			if (uc != null && uc.getInputStream() != null) {
				// uc.disconnect();//释放资源，并有可能影响到持久连接
				uc.getInputStream().close();// 只释放实例资源，不会影响持久连接
			}
		}
		return "";
	}

	public static String createUrlParam(Object... param) {
		StringBuilder sb = new StringBuilder();
		boolean isfirst = true;
		try {
			if (param != null && param.length > 1) {
				for (int i = 0; i < param.length; i += 2) {
					if (param[i + 1] != null) {
						if (!isfirst) {
							sb.append('&');
						}
						sb.append(param[i]);
						sb.append('=');
						String value = param[i + 1].toString();
						value = java.net.URLEncoder.encode(value, "utf-8");
						sb.append(value);
						isfirst = false;
					}
				}
			}
		} catch (Exception ex) {
		}
		return sb.toString();
	}

}
