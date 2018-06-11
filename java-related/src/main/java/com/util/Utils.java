/**
 * This File is created by hztianduoduo at 2016年4月11日,any questions please have a message on me!
 */
package com.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.file.User;
import com.google.common.collect.Maps;

/**
 * @author hztiandd@tian-dd.top
 * 
 * 2016年4月11日
 */
public class Utils {
    
    /**
     * 根据类名称获取实例
     * @param clazz
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public <T> T getObject(Class<T> clazz) throws InstantiationException, IllegalAccessException{
        return clazz.newInstance();
    }
    
    /**
     * 多个String转化成数组
     * @param param
     * @return
     */
    public <T> List<T> strs2List(T...param){
        return Arrays.asList(param);
    }
    
    /**
     * 获取hashmap实例
     * @return
     */
    public  <K, V> Map<K, V> hashMap() {  
        return new HashMap<K, V>();  
    }  
    
    /**
     * Map的key转化成List
     * @param map
     * @return
     */
    public <K,V> List<K> map2list(Map<K, V> map){
        return new ArrayList<K>(map.keySet());
    }

    /**
     * Object转化成Map{字段名称，object}    
     * @param object
     * @param attr
     * @return
     */
    public <T> Map<T, Object> object2MapByAttr(Object object, T attr){
       
        Field[] fields = object.getClass().getDeclaredFields();
        Map<T, Object> map = Maps.newConcurrentMap();
        
        for (Field field: fields) {
            field.setAccessible(true);
            if(field.getName().equals(attr)){
                map.put(attr,object);
                return map;
            }else {
                return null;
            }
        }
        
        return null;
        
    }
    
    /**
     * Object转化成Map
     * @param object
     * @return
     */
    public Map<String, Object> object2Map(Object object){
        
        Map<String, Object> parameter = new HashMap<String, Object>();  
        Field[]  fields   =   object.getClass().getDeclaredFields();
        
       for(int i = 0; i < fields.length; i++){  
           String fieldName =  fields[i].getName();  
           Object result = null;  
           String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);  
           
           try {  
               Method method = object.getClass().getMethod(getMethodName, new Class[] {});  
               result = method.invoke(object, new Object[] {});  
           } catch (Exception e) {  
               e.printStackTrace();  
           }    
           
           if(result != null){  
               parameter.put(fieldName, result);  
           }  
       }  
       return parameter;  
        
    }
    
    public static void main(String[] args) throws Exception{
        
        Utils utils = new Utils();
        
        /*Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("test1", 1);
        map.put("test2", 2);
        map.put("test3",3);

        Utils t = new Utils();
        List<String> result = t.map2list(map);
        for (String key : result) {
            System.out.println(key);
        }*/
        
        User user = new User("1", "1");
        //Map map = utils.object2Map(user);
       
        Map objectMap = utils.object2MapByAttr(user, "userName");
        
        User user2 = (User) objectMap.get("userName");
        System.out.println(user2.getPassWord());
        
        
    }
    

}
