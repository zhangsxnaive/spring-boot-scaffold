package com.chmpay.idauth.common.util;


import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BeanUtils {
    private static BeanUtils beanUtils = new BeanUtils();

    public static BeanUtils getInstance() {
        return beanUtils;
    }

    private BeanUtils() {
    }


    public static <T> T toBean(Class <T> tls, HttpServletRequest request)
            throws IllegalAccessException, InstantiationException {

        T t = tls.newInstance();

        Field[] fields = tls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            String value = request.getParameter(fieldName);
            if (!StringUtils.isEmpty(value)) {
                Object val = getObject(value, field.getType());
                if(val!=null&&field.getType().equals(String.class)) {
                    val = val.toString().replaceAll("\t", ",");
                    val = val.toString().replaceAll("\r", ",");
                    val = val.toString().replaceAll("\n", ",");
                }
                field.set(t, val);
            }
        }


        return t;
    }
    
    public static <T> T toBean(Class <T> tls, Map map)
            throws IllegalAccessException, InstantiationException {

        T t = tls.newInstance();

        Field[] fields = tls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value =  map.get(fieldName);
            if (value!=null) {
                Object val = getObject(value.toString(), field.getType());
                field.set(t, val);
            }
        }


        return t;
    }
    
    public static Map<String,Object> toBeanAndCountCol(Object  object , Map map)
    		throws IllegalAccessException, InstantiationException {
    	Map<String,Object> returnMap=new HashMap<String,Object>();
    	Class<?> class1 = object.getClass();// 传递的是哪个子类的对象，class1就是该子类的类类型
    	Object obj=	class1.newInstance();
    	int count=0;
        Field[] fields = class1.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value =  map.get(fieldName);
            if (value!=null) {
            	
                Object val = getObject(value.toString(), field.getType());
                field.set(obj, val);
                count++;
            }
        }
        returnMap.put("class", obj);
        returnMap.put("count", count);
        
        return returnMap;
    }

    public static <T> T toBean(Class <T> tls, T t, HttpServletRequest request, String[] excludes)
            throws IllegalAccessException, InstantiationException {
        if (t == null) {
            t = tls.newInstance();
        }

        Field[] fields = tls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            boolean ignore = false;
            if (excludes != null && excludes.length > 0) {
                for (String exclude : excludes) {
                    if (fieldName.equals(exclude)) {
                        ignore = true;
                        break;
                    }
                }
            }
            if (ignore) {
                continue;
            }
            String value = request.getParameter(fieldName);
            if (!StringUtils.isEmpty(value)) {
                Object val = getObject(value, field.getType());
                field.set(t, val);
            }
        }


        return t;
    }

    public static Object getObject(String val, Class <?> tls) {

        if(StringUtils.isEmpty(val)){
            return null;
        }

//        System.out.println(val);
//        System.out.println(tls.getName());
        if (tls.getName().equals(Boolean.class.getName())) {
            return Boolean.valueOf(val);
        }
        if (tls.getName().equals(Integer.class.getName())) {
            return Integer.parseInt(val);
        }
        if (tls.getName().equals(Double.class.getName())) {
            return Double.parseDouble(val);
        }

        if (tls.getName().equals(Long.class.getName())) {
            return Long.parseLong(val);
        }
        if (tls.getName().equals(String.class.getName())) {
            return val;
        }
        if (tls.getName().equals(BigDecimal.class.getName())) {
            return new BigDecimal(val);
        }
        if (tls.getName().equals(Character.class.getName())) {
            return val.toCharArray()[0];
        }
        if (tls.getName().equals(Date.class.getName())) {
            return DateUtil.parseStrDate(val, formatPattern(val.length()));
        }
        throw new RuntimeException("NO SUPPORT JAVA TYPE！");
    }

    public static String formatPattern(int val) {

        if (val == 12) {
            return DateUtil.Y2MDHMSS;
        }
        if (val == 8) {
            return DateUtil.YMD;
        }
        if (val == 10) {
            return DateUtil.YMD_;
        }
        if (val == 19) {
            return DateUtil.YMDHMS_;
        }
        throw new RuntimeException("NO SUPPORT JAVA DATE TYPE！");
    }


    public static  <T> T  toBean(Class<T> tls, T t, HttpServletRequest request, String[] excludes, String[] canEmpty) throws  Exception {
        if (t == null) {
            t = tls.newInstance();
        }

        Field[] fields = tls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            boolean ignore = false;
            if (excludes != null && excludes.length > 0) {
                for (String exclude : excludes) {
                    if (fieldName.equals(exclude)) {
                        ignore = true;
                        break;
                    }
                }
            }
            if (ignore) {
                continue;
            }
            String value = request.getParameter(fieldName);
            if (!StringUtils.isEmpty(value)|| ParamsUtils.contains(fieldName,canEmpty)) {
                Object val = getObject(value, field.getType());
                if(val!=null&&field.getType().equals(String.class)) {
                    val = val.toString().replaceAll("\t", ",");
                    val = val.toString().replaceAll("\r", ",");
                    val = val.toString().replaceAll("\n", ",");
                }
                field.set(t, val);
            }
        }


        return t;
    }
}
