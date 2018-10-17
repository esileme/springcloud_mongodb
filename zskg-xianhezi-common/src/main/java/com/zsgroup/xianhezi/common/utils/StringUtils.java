package com.zsgroup.xianhezi.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by pg on 15/11/17.
 */
public class StringUtils {

    public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERCHAR = "0123456789";

    //判断字符串是否为空
    public static boolean isNull(String string){
        return ("").equals(string) || null == string;
    }

    //判断响应是否正常
    public static boolean isSuccess(Map map){
        return ("success").equals(map.get("ret"));
    }


    //获取当前时间：YYYYMMDDHHMMSS


    public static String getFullTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        return df.format(new Date());
    }

    /**
     *
     * @param length timestamp后带的随机码的长度
     * @return 生成一串订单号
     */
    public synchronized static String getOrderNo(int length){
        //生成统一订单号
        return getTimeStamp()+(int)(Math.random()*Math.pow(10, length));
    }

    /**
     *
     *
     * @return 生成一串订单号 没有参数，带4位随机码
     */
    public static String getOrderNo(){
        //生成统一订单号
        return getTimeStamp()+(int)(Math.random()*10000);
    }

    //生成addtime的timestamp格式
    public static String getTimeStamp(){
        return Long.toString(DateUtils.getTimeStampSeconds());
    }



    //使字符串其中符号改成*
    public static String getString(String s){
        //手机号码
        if(s==null){
            return "*";
        }
        if(s.length()==11){
            s=s.substring(0,3)+"****"+s.substring(7);
            return s;
        //15身份证
        }else if(s.length()==15){
            s=s.substring(0,4)+"*******"+s.substring(11);
            return s;
        //18位身份证
        }else if(s.length()==18){
            s=s.substring(0,4)+"**********"+s.substring(14);
            return s;
        //2位或者3位姓名
        }else if(s.length()==2||s.length()==3){
            s="*"+s.substring(1,s.length());
            return s;
        //4位姓名
        }else if(s.length()==4){
            s="**"+s.substring(2,s.length());
            return s;
        }else if(s.length()<4){
            return "*"+s;
        }else{
            int l=s.length();
            int quar=l/4,half=l/2;
            int end=l-quar;
            StringBuilder halfStr= new StringBuilder();
            for(int i=0;i<half;i++){
                halfStr.append("*");
            }
            String ns=s.substring(0,quar)+halfStr+s.substring(end);

            return ns;

        }
    }

    public static String camelName(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母小写
            return name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String camels[] = name.split("_");
        for (String camel :  camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 其他的驼峰片段，首字母大写
            result.append(camel.substring(0, 1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
        return result.toString();
    }

    /**
     * @param urlparam 带分隔的url参数
     * @return
     */
    public static Map<String,String> paramToMap(String urlparam){
        Map<String,String> map = new HashMap<String,String>();
        String[] param =  urlparam.split("&");
        for(String keyvalue:param){
            String[] pair = keyvalue.split("=");
            if(pair.length==2){
                map.put(pair[0], pair[1]);
            }
        }
        return map;
    }

    /**
     * 返回一个定长的随机纯小写字母字符串(只包含小写字母)
     *
     * @param length
     *            随机字符串长度
     * @return 随机字符串
     */
    public static String generateLowerString(int length) {
        return generateUpperLowerString(length).toLowerCase();
    }

    /**
     * 返回一个定长的随机纯大写字母字符串(只包含大写字母)
     *
     * @param length
     *            随机字符串长度
     * @return 随机字符串
     */
    public static String generateUpperString(int length) {
        return generateUpperLowerString(length).toUpperCase();
    }

    /**
     *
     * @param length timestamp后带的随机码的长度
     * @return 生成一串订单号
     */
    public static String generateNum(int length){
        //生成统一订单号
        return (int)(Math.random()*Math.pow(10, length))+"";
    }

    /**
     * 返回一个定长的随机纯字母字符串(只包含大小写字母)
     *
     * @param length
     *            随机字符串长度
     * @return 随机字符串
     */
    public static String generateUpperLowerString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(LETTERCHAR.charAt(random.nextInt(LETTERCHAR.length())));
        }
        return sb.toString();
    }

    public static String createIcloud(){
        return generateUpperString(1)+generateLowerString(1)+generateNum(6);
    }

    /**
     * 根据身份证号码获取年龄
     */
    public static int idNoToAge(String idNo){
        int leh = idNo.length();
        String dates="";
        if (leh == 18) {
            dates = idNo.substring(6, 10);
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String year=df.format(new Date());
            return Integer.parseInt(year)-Integer.parseInt(dates);
        }else{
            dates = idNo.substring(6, 8);
            return Integer.parseInt(dates);
        }

    }

    public static void main(String[] args) {

        System.out.println(createIcloud());

    }

}
