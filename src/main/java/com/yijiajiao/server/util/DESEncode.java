package com.yijiajiao.server.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-12-30-19:46
 */
public class DESEncode {
    /**
     * 密钥算法
     * */
    public static final String KEY_ALGORITHM = "DESede";

    /**
     * 加密/解密算法/工作模式/填充方式
     * */
    public static final String CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";


    /**
     * 转换密钥
     *
     * @param key
     *            二进制密钥
     * @return Key 密钥
     * */
    public static Key toKey(byte[] key) throws Exception {
        // 实例化Des密钥
        DESedeKeySpec dks = new DESedeKeySpec(key);
        // 实例化密钥工厂
        SecretKeyFactory keyFactory = SecretKeyFactory
                .getInstance(KEY_ALGORITHM);
        // 生成密钥
        return keyFactory.generateSecret(dks);
    }

    /**
     * 加密数据
     *
     * @param data
     *            待加密数据
     * @param key
     *            密钥
     * @return byte[] 加密后的数据
     * */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 还原密钥
        Key k = toKey(key);
        // 实例化
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        // 初始化，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, k);
        // 执行操作
        return cipher.doFinal(data);
    }

    /**
     * 解密数据
     *
     * @param data
     *            待解密数据
     * @param key
     *            密钥
     * @return byte[] 解密后的数据
     * */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 欢迎密钥
        Key k = toKey(key);
        // 实例化
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        // 初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, k);
        // 执行操作
        return cipher.doFinal(data);
    }

    /**
     * 加密字符串
     */
    public static String encode(String str,String screatKey){
        String result = "";
        try {
            byte[] data = DESEncode.encrypt(str.getBytes(), screatKey.getBytes());
            result =  Base64.encode(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解密字符串
     * @param str
     * @return
     */
    public static String decode(String str,String screatKey){
        String result = "";
        try {
            byte[] data = Base64.decode(str);
            data = DESEncode.decrypt(data, screatKey.getBytes());
            result = new String(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        String phone = encode("13711111111", "6D8F4E85D844E2DD0AE1C133485F5517");
        String userCode = encode("8d05cbe5d8cbaaf2c561fc4985204d88", "6D8F4E85D844E2DD0AE1C133485F5517");
        System.out.println("phone = " + phone);
        System.out.println("userCode = " + userCode);

        System.out.println(decode(phone, "6D8F4E85D844E2DD0AE1C133485F5517"));
        System.out.println(decode(userCode, "6D8F4E85D844E2DD0AE1C133485F5517"));
    }
}
