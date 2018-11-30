package cn.muchen.framework.util.encrypt.rsa;

import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import cn.muchen.framework.util.encrypt.base64.BASE64Tools;

/**
 * 
 * @ClassName:：RsaSignatureByte 
 * @Description： RSA算法--字符数组
 * @author ：柯雷
 * @date ：2018年11月16日 下午2:56:30 
 *
 */
public class RsaSignatureByte {
	/**
	 * RSA算法定义
	 */
	private static final String KEY_ALGORITHM = "RSA";

	/**
	 * 密钥大小
	 */
	private static final int KEY_SIZE = 1024;
	/**
	 * MD5withRSA算法定义
	 */
	private static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	/**
	 * 
	 * 对信息进行签名
	 * 
	 * @param data
	 *            待签名的信息
	 * @param privateKey
	 *            由BASE64编码的私钥串
	 * @param charset
	 * 
	 * @return 由BASE64编码的签名串
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {
		byte[] privateKeyBytes = BASE64Tools.decrypt(privateKey);

		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(
				privateKeyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(priKey);
		signature.update(data);

		return BASE64Tools.encrypt(signature.sign());
	}

	/**
	 * 验证信息签名正确性
	 * 
	 * @param data
	 *            待验证的信息
	 * @param publicKey
	 *            由BASE64编码的公钥串
	 * @param sign
	 *            由BASE64编码的签名串
	 * @return 签名正确性
	 * @throws Exception
	 */
	public static boolean verify(byte[] data,
			String publicKey, String sign) throws Exception {
		byte[] keyBytes = BASE64Tools.decrypt(publicKey);

		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey pubKey = keyFactory.generatePublic(keySpec);

		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(pubKey);
		signature.update(data);

		return signature.verify(BASE64Tools.decrypt(sign));
	}

	/**
	 * 根据私钥获得解密用Cipher
	 * 
	 * @param privateKey
	 *            由BASE64编码的私钥串
	 * @return Cipher
	 * @throws Exception
	 */
	public static Cipher getDecryptCipherByPrivateKey(String privateKey)
			throws Exception {
		byte[] keyBytes = BASE64Tools.decrypt(privateKey);

		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key priKey = keyFactory.generatePrivate(pkcs8KeySpec);

		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, priKey);
		return cipher;
	}

	/**
	 * 根据公钥获得解密用Cipher
	 * 
	 * @param publicKey
	 *            由BASE64编码的公钥串
	 * @return Cipher
	 * @throws Exception
	 */
	public static Cipher getDecryptCipherByPublicKey(String publicKey)
			throws Exception {
		byte[] keyBytes = BASE64Tools.decrypt(publicKey);

		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key pubKey = keyFactory.generatePublic(x509KeySpec);

		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, pubKey);
		return cipher;
	}

	/**
	 * 根据私钥获得加密用Cipher
	 * 
	 * @param privateKey
	 *            由BASE64编码的私钥串
	 * @return Cipher
	 * @throws Exception
	 */
	public static Cipher getEncryptCipherByPrivateKey(String privateKey)
			throws Exception {
		byte[] keyBytes = BASE64Tools.decrypt(privateKey);

		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key priKey = keyFactory.generatePrivate(pkcs8KeySpec);

		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, priKey);
		return cipher;
	}

	/**
	 * 根据公钥获得加密用Cipher
	 * 
	 * @param publicKey
	 *            由BASE64编码的公钥串
	 * @return Cipher
	 * @throws Exception
	 */
	public static Cipher getEncryptCipherByPublicKey(String publicKey)
			throws Exception {
		byte[] keyBytes = BASE64Tools.decrypt(publicKey);

		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key pubKey = keyFactory.generatePublic(x509KeySpec);

		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		return cipher;
	}

	/**
	 * 使用Cipher解密信息
	 * 
	 * @param data
	 *            待解密的信息
	 * @param cipher
	 * @return 解密后的信息
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, Cipher cipher)
			throws Exception {
		int dataLimit = getMaxDataBytesLimit();
		int encryptLimit = getMaxEncryptBytesLimit();
		int groupNum = (int) Math.ceil(data.length / (double) encryptLimit);
		byte[] decryptT = new byte[groupNum * dataLimit];

		int decryptOffset = 0;
		for (int group = 0; group < groupNum; group++) {
			byte[] dataTemp = new byte[encryptLimit];
			System.arraycopy(data, group * encryptLimit, dataTemp, 0,
					encryptLimit);
			byte[] decryptTemp = cipher.doFinal(dataTemp);
			System.arraycopy(decryptTemp, 0, decryptT, decryptOffset,
					decryptTemp.length);
			decryptOffset += decryptTemp.length;
		}

		byte[] decrypt = new byte[decryptOffset];
		System.arraycopy(decryptT, 0, decrypt, 0, decrypt.length);

		return decrypt;
	}

	/**
	 * 使用Cipher加密信息
	 * 
	 * @param data
	 *            待加密的信息
	 * @param cipher
	 * @return 加密后的信息
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, Cipher cipher)
			throws Exception {
		int dataLimit = getMaxDataBytesLimit();
		int encryptLimit = getMaxEncryptBytesLimit();
		int groupNum = (int) Math.ceil(data.length / (double) dataLimit);
		byte[] encrypt = new byte[groupNum * encryptLimit];

		int encryptOffset = 0;
		for (int group = 0; group < groupNum; group++) {
			int dataLimitT = dataLimit;
			if ((group + 1) * dataLimit > data.length) {
				dataLimitT = data.length - group * dataLimit;
			}
			byte[] dataTemp = new byte[dataLimitT];
			System.arraycopy(data, group * dataLimit, dataTemp, 0, dataLimitT);
			byte[] encryptTemp = cipher.doFinal(dataTemp);
			System.arraycopy(encryptTemp, 0, encrypt, encryptOffset,
					encryptTemp.length);
			encryptOffset += encryptTemp.length;
		}

		return encrypt;
	}

	/**
	 * 使用私钥解密信息
	 * 
	 * @param data
	 *            待解密的信息
	 * @param privateKey
	 *            由BASE64编码的私钥串
	 * @return 解密后的信息
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data,
			String privateKey) throws Exception {
		Cipher cipher = getDecryptCipherByPrivateKey(privateKey);

		return decrypt(data, cipher);
	}

	/**
	 * 使用公钥解密信息
	 * 
	 * @param data
	 *            待解密的信息
	 * @param publicKey
	 *            由BASE64编码的公钥串
	 * @return 解密后的信息
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data,
			String publicKey) throws Exception {
		Cipher cipher = getDecryptCipherByPublicKey(publicKey);

		return decrypt(data, cipher);
	}

	/**
	 * 使用私钥加密信息
	 * 
	 * @param data
	 *            待加密的信息
	 * @param privateKey
	 *            由BASE64编码的私钥串
	 * @return 加密后的信息
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data,
			String privateKey) throws Exception {
		Cipher cipher = getEncryptCipherByPrivateKey(privateKey);

		return encrypt(data, cipher);
	}

	/**
	 * 使用公钥加密信息
	 * 
	 * @param data
	 *            待加密的信息
	 * @param publicKey
	 *            由BASE64编码的公钥串
	 * @return 加密后的信息
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data,
			String publicKey) throws Exception {
		Cipher cipher = getEncryptCipherByPublicKey(publicKey);

		return encrypt(data, cipher);
	}

	/**
	 * 获取最大待加密字节限制
	 * 
	 * @return 最大待加密字节限制
	 */
	private static int getMaxDataBytesLimit() {
		return getMaxEncryptBytesLimit() - 11;
	}

	/**
	 * 获取最大密文字节限制
	 * 
	 * @return 最大密文字节限制
	 */
	private static int getMaxEncryptBytesLimit() {
		return (int) Math.ceil(KEY_SIZE / 8.0);
	}



	public static void main(String[] args) throws Exception {
		// 对数据进行加签
		// sign("1234567890","","MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAp58OqRrGkod/zw9AuKf9NEF3f9DkdiPXMCW227p26joxe4vy/6J+cWlbercUp6gsDvrkZwPxOIoNERw0YABsCwIDAQABAkAYxLQR4Nb+x+7m1cYmgwwzMIlL5b4chudBQhFnHoJ70af8UtcvFDc5jPDwHNHkHPHjWbSFDWIaUtEiBvvC3sghAiEA5WfBXrTWBU/eFBeaGHB+7kKgi4aD1aGUiPvAwlQlo70CIQC7DbB5YhPkuqc8C4NKOl02mZuX03GkvntmIuOX2kznZwIgOjYn1VZh8JshqUuL6KOzMdZqUr1herYzsMbhVO5xVqkCIHqfn8gDPs1ce5OGbJw1pLPNgU3HxKFeaiDr0E3VQ0MTAiEAjj01ORPJss517ipDu1O27eqVD5mUT8uGl99p/DJ71yM=");
		// 验证签名的正确性
		// verify()
		// 使用私钥加密

		String[] keyPair = RsaUtil.generateKeyPair(128);
		String aa =  BASE64Tools.encrypt(encryptByPrivateKey(
				BASE64Tools.decrypt("11"),
				keyPair[1]));
		System.out.println("aa:"+aa);
		// 使用公钥解密
		System.out
				.println(BASE64Tools.encrypt(decryptByPublicKey(
						BASE64Tools.decrypt(aa),
						keyPair[0])));// 使用公钥加密
		
	}
}
