package cn.ezid.cert.core.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ResourceEncoder {
	private static final Logger log = LoggerFactory.getLogger(ResourceEncoder.class);
	
	private static final char[] pass = "this|is|the|pass".toCharArray();
	private static final byte[] salt = "res".getBytes();

	public static String encode(String path) {
		try {
			String prefix = path.substring(0, path.lastIndexOf("."));
			String suffix = path.substring(path.lastIndexOf("."));
			
			byte[] bytes = encrypt(pass, salt, prefix.getBytes("UTF-8"));
			return Base64.encodeBase64URLSafeString(bytes) + suffix;
		} catch (Exception e) {
			log.warn(e.getMessage());
			return null;
		}
	}

	public static String decode(String path) {
		try {
			String prefix = path.substring(0, path.lastIndexOf("."));
			String suffix = path.substring(path.lastIndexOf("."));
			
			byte[] bytes= Base64.decodeBase64(prefix);
			byte[] rawBytes = decrypt(pass, salt, bytes);
			
			return new String(rawBytes, "UTF-8") + suffix;
		} catch (Exception e) {
			log.warn(e.getMessage());
			return null;
		}
	}
	
	private static SecretKey getSecretKey(char[] password, byte[] salt) throws NoSuchAlgorithmException,
			InvalidKeySpecException {
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		// NOTE: last argument is the key length, and it is 256
		KeySpec spec = new PBEKeySpec(password, salt, 1024, 256);
		SecretKey tmpKey = factory.generateSecret(spec);
		SecretKey secretKey = new SecretKeySpec(tmpKey.getEncoded(), "AES");
		return secretKey;
	}

	private static byte[] encrypt(char[] password, byte[] salt, byte[] data) throws NoSuchAlgorithmException,
			InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidParameterSpecException,
			IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		SecretKey secret = getSecretKey(password, salt);

		Cipher cipher = Cipher.getInstance("AES");
		// NOTE: This is where the Exception is being thrown
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		byte[] ciphertext = cipher.doFinal(data);
		return (ciphertext);
	}

	private static byte[] decrypt(char[] password, byte[] salt, byte[] data) throws NoSuchAlgorithmException,
			InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidParameterSpecException,
			IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		SecretKey secret = getSecretKey(password, salt);
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secret);
		byte[] ciphertext = cipher.doFinal(data);
		return ciphertext;
	}
}