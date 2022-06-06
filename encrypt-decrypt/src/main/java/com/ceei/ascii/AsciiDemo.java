package com.ceei.ascii;

public class AsciiDemo {
    public static void main(String[] args) {
        char a ='A';
        int b=a;
        //打印b在ascii当中十进制的数字
        System.out.println(b);
        String c="aaa";
        //字符串拆开成字节数组
        char[] chars = c.toCharArray();
        for (char aChar : chars) {
            int acsiicode=aChar;
            System.out.println(acsiicode);
        }
    }
}
