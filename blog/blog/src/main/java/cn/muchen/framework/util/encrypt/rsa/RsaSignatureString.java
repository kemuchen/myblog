package cn.muchen.framework.util.encrypt.rsa;

import javax.crypto.Cipher;

import cn.muchen.framework.util.encrypt.base64.BASE64Tools;

/**
 * 
 * @ClassName:：RsaSignatureString 
 * @Description： RSA算--字符串
 * @author ：柯雷
 * @date ：2018年11月16日 下午2:57:46 
 *
 */
public class RsaSignatureString {
	/**
	 * 
	 * 对信息进行签名
	 * 
	 * @param data
	 *            待签名的信息
	 * @param charset
	 *            转换类型
	 * @param privateKey
	 *            由BASE64编码的私钥串
	 * @param charset
	 * 
	 * @return 由BASE64编码的签名串
	 * @throws Exception
	 */
	public static String sign(String content, String privateKey) throws Exception {
		return RsaSignatureByte.sign(BASE64Tools.decrypt(content), privateKey);
	}

	/**
	 * 验证信息签名正确性
	 * 
	 * @param data
	 *            待验证的信息
	 * @param charset
	 *            转换类型
	 * @param publicKey
	 *            由BASE64编码的公钥串
	 * @param sign
	 *            由BASE64编码的签名串
	 * @return 签名正确性
	 * @throws Exception
	 */
	public static boolean verify(String content, String publicKey, String sign) throws Exception {
		return RsaSignatureByte.verify(BASE64Tools.decrypt(content), publicKey, sign);
	}

	/**
	 * 使用私钥解密信息
	 * 
	 * @param data
	 *            待解密的信息
	 * @param charset
	 *            转换类型
	 * @param privateKey
	 *            由BASE64编码的私钥串
	 * @return 解密后的信息
	 * @throws Exception
	 */
	public static String decryptByPrivateKey(String content, String privateKey) {
		//rsaSignatureByte = new RsaSignatureByte();
		String a = "";
		try{
			Cipher cipher = RsaSignatureByte.getDecryptCipherByPrivateKey(privateKey);
			a = new String(RsaSignatureByte.decrypt(BASE64Tools.decrypt(content), cipher), "UTF-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		return a;
	}

	/**
	 * 使用公钥解密信息
	 * 
	 * @param data
	 *            待解密的信息
	 * @param charset
	 *            转换类型
	 * @param publicKey
	 *            由BASE64编码的公钥串
	 * @return 解密后的信息
	 * @throws Exception
	 */
	public static String decryptByPublicKey(String content, String publicKey) throws Exception {
		Cipher cipher = RsaSignatureByte.getDecryptCipherByPublicKey(publicKey);

		return new String(RsaSignatureByte.decrypt(BASE64Tools.decrypt(content), cipher), "UTF-8");
	}

	/**
	 * 使用私钥加密信息
	 * 
	 * @param data
	 *            待加密的信息
	 * @param charset
	 *            转换类型
	 * @param privateKey
	 *            由BASE64编码的私钥串
	 * @return 加密后的信息
	 * @throws Exception
	 */
	public static String encryptByPrivateKey(String content, String privateKey) throws Exception {
		Cipher cipher = RsaSignatureByte.getEncryptCipherByPrivateKey(privateKey);

		return BASE64Tools.encrypt(RsaSignatureByte.encrypt(content.getBytes("UTF-8"), cipher));
	}

	/**
	 * 使用公钥加密信息
	 * 
	 * @param data
	 *            待加密的信息
	 * @param charset
	 *            转换类型
	 * @param publicKey
	 *            由BASE64编码的公钥串
	 * @return 加密后的信息
	 * @throws Exception
	 */
	public static String encryptByPublicKey(String content, String publicKey) throws Exception {
		Cipher cipher = RsaSignatureByte.getEncryptCipherByPublicKey(publicKey);

		return BASE64Tools.encrypt(RsaSignatureByte.encrypt(content.getBytes("UTF-8"), cipher));
	}
}
