package com._3u.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


/**
 * 
 * @author xiaof
 * 
 */
public class HTMLUtils {

	/**
	 * 过滤css和js标签
	 * 
	 * @param inputString
	 * @return
	 */
	public static String cssAndJs2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		Pattern pScript;
		Matcher mScript;
		Pattern pStyle;
		Matcher mStyle;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			// }
			pScript = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			mScript = pScript.matcher(htmlStr);
			htmlStr = mScript.replaceAll(""); // 过滤script标签

			pStyle = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			mStyle = pStyle.matcher(htmlStr);
			htmlStr = mStyle.replaceAll(""); // 过滤style标签

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// 返回文本字符串
	}

	/**
	 * 过滤html标签
	 * 
	 * @param content
	 * @return
	 */
	public static String html2Text(String content) {
		String regex = "(<.*?>)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			content = content.replace(matcher.group(), "");
		}
		regex = "(</.*?>)";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(content);
		while (matcher.find()) {
			content = content.replace(matcher.group(), "");
		}
		return content;

	}

	/**
	 * 过滤a标签
	 * 
	 * @param content
	 * @return
	 */
	public static String filterHrefA(String content) {
		String regex = "(<a.*?>)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			// String text = matcher.group(2);
			content = content.replace(matcher.group(), "");
		}
		regex = "(</a>)";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(content);
		while (matcher.find()) {
			// String text = matcher.group(2);
			content = content.replace(matcher.group(), "");
		}
		return content;
	}

	/**
	 * 获取内容页面
	 * 
	 * @param url
	 * @return
	 */
	public static Document getDocument(String url) {
		try {
			Connection c = Jsoup.connect(url);
			c.timeout(30000);
			Document doc = c.get();// 获取内容页面
			return doc;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 下载文件
	 * 
	 * @param oriUrl
	 * @param newName
	 */
	public static boolean getWebContentToFile(String oriUrl, String newName) {
		HttpURLConnection httpUrl = null;
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			URL url = new URL(oriUrl);
			// 建立链接
			httpUrl = (HttpURLConnection) url.openConnection();
			// 连接指定的资源
			httpUrl.connect();
			// 获取网络输入流
			bis = new BufferedInputStream(httpUrl.getInputStream());
			int bytes_read = 0;
			byte[] buf = new byte[1024 * 1024];
			os = new FileOutputStream(newName);
			while ((bytes_read = bis.read(buf)) > 0) {
				os.write(buf, 0, bytes_read);
			}
			return true;
		} catch (Exception e) {
			//System.err.println(e);
			//System.err.println("Usage: java HttpClient <URL> [<filename>]");
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (httpUrl != null) {
				httpUrl.disconnect();
			}
		}
		return false;
	}

	/**
	 * 得到下载文件的长度
	 * 
	 * @param oriUrl
	 * @return
	 */
	public static int downFile(String oriUrl) {
		int num = 0;
		try {
			URL url = null;
			HttpURLConnection httpUrl = null;
			url = new URL(oriUrl);
			// 建立链接
			httpUrl = (HttpURLConnection) url.openConnection();
			num = httpUrl.getContentLength();
			httpUrl.connect();
			httpUrl.disconnect();
		} catch (Exception e) {
			System.err.println(e);
			System.err.println("Usage: java HttpClient <URL> [<filename>]");
		}
		return num;
	}
	
	public static String postHttp(String oriUrl, NameValuePair[] datas) {
		HttpClient httpclient = new HttpClient();
		// 创建post方法实例
		PostMethod postMethod = new PostMethod(oriUrl);
		try {
			if (oriUrl.startsWith("https")) {// 如果是https请求
				verifierHostname();
			}
			postMethod.setRequestBody(datas);
			// 执行post方法
			int statusCode = httpclient.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				InputStream responseBody = postMethod.getResponseBodyAsStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						responseBody, "utf-8"));
				String line = "";
				StringBuilder sTotalString = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					sTotalString.append(line);
				}
				return sTotalString.toString();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return "-1";
	}

	public static String postURLConnection(String oriUrl, String params) {
		if (oriUrl.startsWith("https")) {// 如果是https请求
			return postHttps(oriUrl, params);
		}
		InputStream urlStream = null;
		BufferedReader reader = null;
		try {
			URL url = new URL(oriUrl);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setConnectTimeout(10000);
			connection.setReadTimeout(10000);
			OutputStreamWriter out = new OutputStreamWriter(connection
					.getOutputStream(), "utf-8");
			out.write(params); // post的关键所在！
			// remember to clean up
			out.flush();
			out.close();
			// 一旦发送成功，用以下方法就可以得到服务器的回应：
			StringBuilder sTotalString = new StringBuilder();
			String sCurrentLine = "";
			urlStream = connection.getInputStream();
			// 传说中的三层包装阿！
			reader = new BufferedReader(new InputStreamReader(urlStream, "utf-8"));
			while ((sCurrentLine = reader.readLine()) != null) {
				sTotalString.append(sCurrentLine);
			}
			return sTotalString.toString();
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (urlStream != null) {
				try {
					urlStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "-1";
	}

	private static void verifierHostname() throws NoSuchAlgorithmException,
			KeyManagementException {
		SSLContext sslContext = null;
		sslContext = SSLContext.getInstance("TLS");
		X509TrustManager xtm = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) {
			}

			public void checkServerTrusted(X509Certificate[] chain,
					String authType) {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		X509TrustManager[] xtmArray = new X509TrustManager[] { xtm };
		sslContext.init(null, xtmArray, new java.security.SecureRandom());
		if (sslContext != null) {
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext
					.getSocketFactory());
		}
		HostnameVerifier hnv = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		HttpsURLConnection.setDefaultHostnameVerifier(hnv);
	}
	
	/**
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String postHttps(String url, String params) {
		InputStream urlStream = null;
		BufferedReader reader = null;
		try {
			verifierHostname();
			URLConnection urlCon = (new URL(url)).openConnection();
			urlCon.setConnectTimeout(10000);
			urlCon.setReadTimeout(10000);
			urlCon.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(urlCon
					.getOutputStream(), "utf-8");
			out.write(params); // post的关键所在！
			// remember to clean up
			out.flush();
			out.close();
			// 一旦发送成功，用以下方法就可以得到服务器的回应：
			StringBuilder sTotalString = new StringBuilder();
			String sCurrentLine = "";
			urlStream = urlCon.getInputStream();
			// 传说中的三层包装阿！
			reader = new BufferedReader(new InputStreamReader(urlStream));
			while ((sCurrentLine = reader.readLine()) != null) {
				sTotalString.append(sCurrentLine);
			}
			return sTotalString.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (urlStream != null) {
				try {
					urlStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "-1";
	}
	
	/**
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String postHttps(String url, String params, Map<String, String> headers) {
		InputStream urlStream = null;
		BufferedReader reader = null;
		try {
			verifierHostname();
			URLConnection urlCon = (new URL(url)).openConnection();
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				urlCon.setRequestProperty(entry.getKey(), entry.getValue());
	        }
			urlCon.setConnectTimeout(10000);
			urlCon.setReadTimeout(10000);
			urlCon.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(urlCon
					.getOutputStream(), "utf-8");
			out.write(params); // post的关键所在！
			// remember to clean up
			out.flush();
			out.close();
			// 一旦发送成功，用以下方法就可以得到服务器的回应：
			StringBuilder sTotalString = new StringBuilder();
			String sCurrentLine = "";
			urlStream = urlCon.getInputStream();
			// 传说中的三层包装阿！
			reader = new BufferedReader(new InputStreamReader(urlStream));
			while ((sCurrentLine = reader.readLine()) != null) {
				sTotalString.append(sCurrentLine);
			}
			return sTotalString.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (urlStream != null) {
				try {
					urlStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "-1";
	}

	public static void main(String[] args) {
		postHttps("https://pay.i4.cn", "");
	}

}
