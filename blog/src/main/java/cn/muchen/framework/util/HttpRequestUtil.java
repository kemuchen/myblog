package cn.muchen.framework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: HttpRequestUtil
 * @Description: http请求工具类
 * @author: 柯雷
 * @date：2018年11月29日 下午8:21:05
 */
public class HttpRequestUtil {

	private static String charset = "utf-8";
	private static Integer connectTimeout = 360000;
	private static Integer socketTimeout = 360000;
	private static String proxyHost = null;
	private static Integer proxyPort = null;
	
	/** 日志打印对象 */
	private static Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);

	/**
	 * @throws Exception 
	 * 
	 * @Title：doGet 
	 * @param ：@param url
	 * @param ：@param params
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	public static String doGet(String url, Map<String, Object> params) throws Exception {
		logger.info("【HttpRequestUtil.doGet】http请求：url=" + url + "?" + params);
		Set<String> keySet = params.keySet();
		
		// 拼接参数
		StringBuilder urlBuilder = new StringBuilder(url).append("?");
		for (String key : keySet) {
			urlBuilder.append(key).append("=").append(params.get(key)).append("&");
		}
		
		return doGet(urlBuilder.toString().substring(0, urlBuilder.length() - 1));
	}
	
	/**
	 * Do GET request
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	public static String doGet(String url) throws Exception {
		logger.info("【HttpRequestUtil.doGet】http请求：url=" + url);
		URL localURL = new URL(url);

		URLConnection connection = openConnection(localURL);
		HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

		httpURLConnection.setRequestProperty("Accept-Charset", charset);
		httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		StringBuffer resultBuffer = new StringBuffer();
		String tempLine = null;

		if (httpURLConnection.getResponseCode() >= 300) {
			throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
		}

		try {
			inputStream = httpURLConnection.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream);
			reader = new BufferedReader(inputStreamReader);

			while ((tempLine = reader.readLine()) != null) {
				resultBuffer.append(tempLine);
			}

		} finally {

			if (reader != null) {
				reader.close();
			}

			if (inputStreamReader != null) {
				inputStreamReader.close();
			}

			if (inputStream != null) {
				inputStream.close();
			}

		}

		return resultBuffer.toString();
	}

	/**
	 * Do POST request
	 * 
	 * @param url
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url, Map<String, Object> parameterMap) throws Exception {
		logger.info("【HttpRequestUtil.doPost】http请求：url=" + url + "?" + parameterMap);
		/* Translate parameter map to parameter date string */
		StringBuffer parameterBuffer = new StringBuffer();
		if (parameterMap != null) {
			Iterator<String> iterator = parameterMap.keySet().iterator();
			String key = null;
			String value = null;
			while (iterator.hasNext()) {
				key = iterator.next();
				if (parameterMap.get(key) != null) {
					value = (String) parameterMap.get(key);
				} else {
					value = "";
				}

				parameterBuffer.append(key).append("=").append(value);
				if (iterator.hasNext()) {
					parameterBuffer.append("&");
				}
			}
		}

		System.out.println("POST parameter : " + parameterBuffer.toString());

		URL localURL = new URL(url);

		URLConnection connection = openConnection(localURL);
		HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

		httpURLConnection.setDoOutput(true);
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setRequestProperty("Accept-Charset", charset);
		httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		httpURLConnection.setRequestProperty("Content-Length", String.valueOf(parameterBuffer.length()));

		OutputStream outputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		StringBuffer resultBuffer = new StringBuffer();
		String tempLine = null;

		try {
			outputStream = httpURLConnection.getOutputStream();
			outputStreamWriter = new OutputStreamWriter(outputStream);

			outputStreamWriter.write(parameterBuffer.toString());
			outputStreamWriter.flush();

			if (httpURLConnection.getResponseCode() >= 300) {
				throw new Exception(
						"HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
			}

			inputStream = httpURLConnection.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream);
			reader = new BufferedReader(inputStreamReader);

			while ((tempLine = reader.readLine()) != null) {
				resultBuffer.append(tempLine);
			}

		} finally {

			if (outputStreamWriter != null) {
				outputStreamWriter.close();
			}

			if (outputStream != null) {
				outputStream.close();
			}

			if (reader != null) {
				reader.close();
			}

			if (inputStreamReader != null) {
				inputStreamReader.close();
			}

			if (inputStream != null) {
				inputStream.close();
			}

		}

		return resultBuffer.toString();
	}

	private static URLConnection openConnection(URL localURL) throws IOException {
		URLConnection connection;
		if (proxyHost != null && proxyPort != null) {
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
			connection = localURL.openConnection(proxy);
		} else {
			connection = localURL.openConnection();
		}
		return connection;
	}

	/**
	 * Render request according setting
	 * 
	 * @param request
	 */
	@SuppressWarnings("unused")
	private void renderRequest(URLConnection connection) {

		if (connectTimeout != null) {
			connection.setConnectTimeout(connectTimeout);
		}

		if (socketTimeout != null) {
			connection.setReadTimeout(socketTimeout);
		}
	}
	
	public static void main(String[] args) throws Exception {
		Map<String, Object> params = new HashMap<>();
		params.put("code", "f44c3606f2948be23d4fc70f5397040a");
		params.put("grant_type", "authorization_code");
		params.put("client_secret", "e8066160a6a5ab6afa7775147993465e");
		params.put("redirect_uri", "http://22815l1b14.iask.in/thirdLogin/weiboLogin");
		params.put("client_id", "2191145838");
		System.out.println(HttpRequestUtil.doPost("https://api.weibo.com/oauth2/access_token", params));
	}
}
