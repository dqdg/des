package com.ceei.desaes;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

//对称加密
public class DesAesDemo {
    public static void main(String[] args) throws Exception {
        //原文
        String input="硅谷";
        //定义key
        String key="12345678";//DES加密密钥必须8个字节
        //算法
//        String transformation="DES";
        //默认情况下如果没有写填充模式和加密模式，默认使用“DES/ECB/PKCS5Padding”
        String transformation="DES/CBC/PKCS5Padding";
        //加密类型
        String algorithm ="DES";
        //Cipher类提供了加密和解密的加密密码的功能。它构成了Java加密扩展（JCE）框架的核心
        Cipher cipher = Cipher.getInstance(transformation);
        //创建加密规则
        //第一个参数表示key的字节;第二个参数表示加密的类型
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(),algorithm);
        //创建iv向量，iv向量是使用到cbc加密模式
        IvParameterSpec ivParameterSpec = new IvParameterSpec(key.getBytes());
        //进行加密初始化。第一个参数表示模式，分为加密模式和解密模式；第二个参数表示加密的规则
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec,ivParameterSpec);
        //调用加密算法
        //参数表示原文的字节数组
        byte[] bytes = cipher.doFinal(input.getBytes());
        //创建base64
        String code = Base64.encodeBase64String(bytes);
        System.out.println(code);
        //输出：qANksk5lvqM=
        //输出：8Ze/OtPlSaU=
        //打印密文
        //如果直接打印密文就是乱码，因为在编码表上面找不到对应字符
//        System.out.println(new String(bytes));
        String s=decryptDES(code,key,transformation,algorithm);
        System.out.println("解密："+s);
    }

    /**
     *解密
     * @param code 密文
     * @param key   密钥
     * @param transformation 加密算法
     * @param algorithm      加密类型
     * @return
     */
    private static String decryptDES(String code, String key, String transformation, String algorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(),algorithm);
        //创建IV向量
        IvParameterSpec ivParameterSpec = new IvParameterSpec(key.getBytes());
        //Cipher.DECRYPT_MODE表示解密
        //解密规则
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec,ivParameterSpec);
        //解密，传入密文
        byte[] bytes = cipher.doFinal(Base64.decodeBase64(code));
        return new String(bytes);
    }
}
