package com.ceei.rabbitmq.utils;

/**
 * @ClassName: SleepUtils
 * @Author: dqdgo
 * @Description: 睡眠工具类
 * @Create: 2022-07-20 20:31
 **/
public class SleepUtils {
    public static void sleep(int second){
      try{
          Thread.sleep(1000*second);
      }  catch (InterruptedException _ignored){
          Thread.currentThread().interrupt();
      }
    }
}
