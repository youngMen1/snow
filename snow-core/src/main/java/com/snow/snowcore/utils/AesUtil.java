package com.snow.snowcore.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/26 09:59
 * @description
 **/
public class AesUtil {

    /**
     * AES encryption and decryption password(length of 16 bit string, you can
     * find a password generator on the Internet to generate a 16 bit string).
     */
    private static final String AES_PWD = "123456";
    private static final Integer BINARY_BIT = 2;

    public static String encrypt(String content) throws Exception {
        return encrypt(content, AES_PWD);
    }

    /**
     * AES Encrypt
     *
     * @param content  Need encrypted string
     * @param password The Password.
     * @return Encrypted 16 hex string
     */
    public static String encrypt(String content, String password) throws Exception {
        //1.构造密钥生成器，指定为AES算法,不区分大小写
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        //2.根据password规则初始化密钥生成器
        //生成一个128位的随机源,根据传入的字节数组
        keygen.init(128, new SecureRandom(password.getBytes()));
        //3.产生原始对称密钥
        SecretKey originalKey = keygen.generateKey();
        //4.获得原始对称密钥的字节数组
        byte[] raw = originalKey.getEncoded();
        //5.根据字节数组生成AES密钥
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        //6.根据指定算法AES自成密码器
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        //8.獲取內容
        byte[] byteContent = content.getBytes("utf-8");
        //9.根据密码器的初始化方式--加密：将数据加密
        byte[] bytes = cipher.doFinal(byteContent);
        return bytesToHexString(bytes);
    }

    public static String decrypt(String content) throws Exception {
        return decrypt(content, AES_PWD);
    }

    /**
     * AES Decrypt
     *
     * @param hexString Encrypted 16 hex string
     * @param password  The Password.
     * @return Decrypted string
     */
    public static String decrypt(String hexString, String password) throws Exception {
        //1.构造密钥生成器，指定为AES算法,不区分大小写
        KeyGenerator keygen=KeyGenerator.getInstance("AES");
        //2.根据ecnodeRules规则初始化密钥生成器
        //生成一个128位的随机源,根据传入的字节数组
        keygen.init(128, new SecureRandom(password.getBytes()));
        //3.产生原始对称密钥
        SecretKey originalKey = keygen.generateKey();
        //4.获得原始对称密钥的字节数组
        byte[] raw = originalKey.getEncoded();

        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] content = hexStringToBytes(hexString);
        byte[] bytes = cipher.doFinal(content);
        return new String(bytes);
    }

    /**
     * Byte array to convert 16 hex string
     *
     * @param src array
     * @return 16 hex string
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv.toUpperCase());
        }
        return stringBuilder.toString();
    }

    /**
     * 16 hex string converted to byte array
     *
     * @param hexString 16 hex string
     * @return byte array
     */
    private static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || "".equals(hexString)) {
            return null;
        }
        int length = hexString.length() / 2;
        if (hexString.length() % BINARY_BIT != 0) {
            return null;
        }
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexString.charAt(pos)) << 4 |
                    charToByte(hexString.charAt(pos + 1)));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
