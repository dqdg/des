package com.ceei.digest;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * 消息摘要算法，防止篡改信息
 */
public class digestdemo1 {
    public static void main(String[] args) throws Exception{
        //原文
        String input ="aa";
        //算法
        String algorithm="MD5";
        //创建消息摘要对象
        MessageDigest instance = MessageDigest.getInstance(algorithm);
        //执行消息摘要算法
        byte[] digest = instance.digest(input.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        //对密文进行迭代
        for (byte b : digest) {
            //把密文转换为16进制
            //Integer.toHexString的参数是int，如果不进行&0xff，那么当一个byte会转换成int时，由于int是32位，而byte只有8位这时会进行补位，
            //例如补码11111111的十进制数为-1转换为int时变为11111111111111111111111111111111
            String s =Integer.toHexString((b&0xff));
            //如果密文长度是1，需要在高位进行补0
            if (s.length()==1){
                s="0"+s;
            }
            stringBuilder.append(s);
        }
        System.out.println(stringBuilder.toString());//输出：4124bc0a9335c27f086f24ba207a4912
        //base64转码
        System.out.println(Base64.encodeBase64String(digest));//输出：QSS8CpM1wn8IbyS6IHpJEg==

    }
}
