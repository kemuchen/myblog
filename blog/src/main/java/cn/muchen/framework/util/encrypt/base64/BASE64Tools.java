package cn.muchen.framework.util.encrypt.base64;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @ClassName:：BASE64Tools 
 * @Description： BASE64加密解密工具
 * @author ：柯雷
 * @date ：2018年11月16日 下午2:56:06 
 *
 */
public class BASE64Tools {
	/**
	 * 使用Cipher解密信息
	 * @param data 待解密的信息
	 * @return 解密后的信息
	 * @throws Exception
	 */
	public static byte[] decrypt(String data) throws Exception {
		return Base64.decodeBase64(data);
		
	}
	
	/**
	 * 使用BASE64加密信息
	 * @param data 待加密的信息
	 * @return 加密后的信息
	 * @throws Exception
	 */
	public static String encrypt(byte[] data) throws Exception {
		return Base64.encodeBase64URLSafeString(data); 
	}

}
