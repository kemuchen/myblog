package cn.muchen.framework.util.encrypt.rsa;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import cn.muchen.framework.util.encrypt.base64.BASE64Tools;

/**
 * 
 * @ClassName:：RsaUtil 
 * @Description： RSA算法工具--用于生成秘钥
 * @author ：柯雷
 * @date ：2018年11月16日 下午2:58:21 
 *
 */
public class RsaUtil {

	/**
	 * RSA算法定义
	 */
	public static final String KEY_ALGORITHM = "RSA";

	/**
	 * 生成密钥对
	 * 
	 * @return 长度为2的String数组，分别为BASE64编码的公钥和密钥
	 * @throws Exception
	 */
	public static String[] generateKeyPair(int KEY_SIZE) throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator
				.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(KEY_SIZE);

		KeyPair keyPair = keyPairGen.generateKeyPair();
		String publicKey = BASE64Tools
				.encrypt(keyPair.getPublic().getEncoded());
		String privateKey = BASE64Tools.encrypt(keyPair.getPrivate()
				.getEncoded());

		return new String[] { publicKey, privateKey };
	}

	public static void main(String[] args) throws Exception {
		String[] keyPair = generateKeyPair(1024);
		System.out.println("公钥=" + keyPair[0]);
		System.out.println("私钥=" + keyPair[1]);
	}

}
