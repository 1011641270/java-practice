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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.file.User;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

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


        String json = "[{\"channelId\":3,\"createTime\":1495802265467,\"discussionId\":9060,\"effectiveTime\":1501235405104,\"status\":1,\"updateTime\":1501235405104},{\"channelId\":3,\"createTime\":1500023287038,\"discussionId\":9495,\"effectiveTime\":1500024521060,\"status\":1,\"updateTime\":1500024521060},{\"channelId\":3,\"createTime\":1501075025384,\"discussionId\":9713,\"effectiveTime\":1507616235049,\"status\":1,\"updateTime\":1507616235049},{\"channelId\":3,\"createTime\":1501076291808,\"discussionId\":9720,\"effectiveTime\":1501081530035,\"status\":1,\"updateTime\":1501081530035},{\"channelId\":3,\"createTime\":1501145496711,\"discussionId\":9756,\"effectiveTime\":1501235398490,\"status\":1,\"updateTime\":1501235398490},{\"channelId\":3,\"createTime\":1501157073469,\"discussionId\":9773,\"effectiveTime\":1501235395332,\"status\":1,\"updateTime\":1501235395332},{\"channelId\":3,\"createTime\":1501158344879,\"discussionId\":9777,\"effectiveTime\":1501235391405,\"status\":1,\"updateTime\":1501235391405},{\"channelId\":3,\"createTime\":1501229530077,\"discussionId\":9819,\"effectiveTime\":1501235387780,\"status\":1,\"updateTime\":1501235387780},{\"channelId\":3,\"createTime\":1501234209076,\"discussionId\":9821,\"effectiveTime\":1501235385315,\"status\":1,\"updateTime\":1501235385315},{\"channelId\":3,\"createTime\":1501307760801,\"discussionId\":9846,\"effectiveTime\":1525963995925,\"status\":1,\"updateTime\":1525963995925},{\"channelId\":3,\"createTime\":1501035965391,\"discussionId\":10293,\"effectiveTime\":1501035996396,\"status\":1,\"updateTime\":1501035996396},{\"channelId\":3,\"createTime\":1501054716102,\"discussionId\":10301,\"effectiveTime\":1509787166211,\"status\":1,\"updateTime\":1509787166211},{\"channelId\":3,\"createTime\":1501074261610,\"discussionId\":10314,\"effectiveTime\":1509450253693,\"status\":1,\"updateTime\":1509450253693},{\"channelId\":3,\"createTime\":1501140438330,\"discussionId\":10318,\"effectiveTime\":1501140493630,\"status\":1,\"updateTime\":1501140493630},{\"channelId\":3,\"createTime\":1501160706977,\"discussionId\":10322,\"effectiveTime\":1501235392320,\"status\":1,\"updateTime\":1501235392320},{\"channelId\":3,\"createTime\":1501170814713,\"discussionId\":10333,\"effectiveTime\":1501235389408,\"status\":1,\"updateTime\":1501235389408},{\"channelId\":3,\"createTime\":1501170926971,\"discussionId\":10334,\"effectiveTime\":1501235390315,\"status\":1,\"updateTime\":1501235390315},{\"channelId\":3,\"createTime\":1501227972649,\"discussionId\":10346,\"effectiveTime\":1501235386584,\"status\":1,\"updateTime\":1501235386584},{\"channelId\":3,\"createTime\":1501307874286,\"discussionId\":10368,\"effectiveTime\":1526006446176,\"status\":1,\"updateTime\":1526006446176},{\"channelId\":3,\"createTime\":1501552835330,\"discussionId\":10418,\"effectiveTime\":1501570076523,\"status\":1,\"updateTime\":1501570076523},{\"channelId\":3,\"createTime\":1501749877287,\"discussionId\":10511,\"effectiveTime\":1501750290632,\"status\":1,\"updateTime\":1501750290632},{\"channelId\":3,\"createTime\":1501816351792,\"discussionId\":10560,\"effectiveTime\":1501817287657,\"status\":1,\"updateTime\":1501817287657},{\"channelId\":3,\"createTime\":1501816377580,\"discussionId\":10561,\"effectiveTime\":1501817295793,\"status\":1,\"updateTime\":1501817295793},{\"channelId\":3,\"createTime\":1501817206772,\"discussionId\":10563,\"effectiveTime\":1501817264943,\"status\":1,\"updateTime\":1501817264943},{\"channelId\":3,\"createTime\":1501760971846,\"discussionId\":11052,\"effectiveTime\":1501817296078,\"status\":1,\"updateTime\":1501817296078},{\"channelId\":3,\"createTime\":1501814535274,\"discussionId\":11071,\"effectiveTime\":1501817294109,\"status\":1,\"updateTime\":1501817294109},{\"channelId\":3,\"createTime\":1502086820658,\"discussionId\":11103,\"effectiveTime\":1502088223044,\"status\":1,\"updateTime\":1502088223044},{\"channelId\":3,\"createTime\":1502071752630,\"discussionId\":12014,\"effectiveTime\":1502091601244,\"status\":1,\"updateTime\":1502091601244},{\"channelId\":3,\"createTime\":1502088493185,\"discussionId\":12019,\"effectiveTime\":1502091671739,\"status\":1,\"updateTime\":1502091671739},{\"channelId\":3,\"createTime\":1503453935642,\"discussionId\":12277,\"effectiveTime\":1503453945525,\"status\":1,\"updateTime\":1503453945525},{\"channelId\":3,\"createTime\":1503475355622,\"discussionId\":12297,\"effectiveTime\":1504092041239,\"status\":1,\"updateTime\":1504092041239},{\"channelId\":3,\"createTime\":1504493584322,\"discussionId\":12426,\"effectiveTime\":1526006516202,\"status\":1,\"updateTime\":1526006516202},{\"channelId\":3,\"createTime\":1504594371266,\"discussionId\":12494,\"effectiveTime\":1509450884471,\"status\":1,\"updateTime\":1509450884471},{\"channelId\":3,\"createTime\":1504681355965,\"discussionId\":12523,\"effectiveTime\":1504684584757,\"status\":1,\"updateTime\":1504684584757},{\"channelId\":3,\"createTime\":1504701643676,\"discussionId\":12526,\"effectiveTime\":1504701728026,\"status\":1,\"updateTime\":1504701728026},{\"channelId\":3,\"createTime\":1504704349103,\"discussionId\":12527,\"effectiveTime\":1504771439039,\"status\":1,\"updateTime\":1504771439039},{\"channelId\":3,\"createTime\":1504792155226,\"discussionId\":12549,\"effectiveTime\":1526006558016,\"status\":1,\"updateTime\":1526006558016},{\"channelId\":3,\"createTime\":1504859136134,\"discussionId\":12560,\"effectiveTime\":1507540748905,\"status\":1,\"updateTime\":1507540748905},{\"channelId\":3,\"createTime\":1505698736243,\"discussionId\":12656,\"effectiveTime\":1526006502102,\"status\":1,\"updateTime\":1526006502102},{\"channelId\":3,\"createTime\":1506413699137,\"discussionId\":12786,\"effectiveTime\":1527849606543,\"status\":1,\"updateTime\":1527849606543},{\"channelId\":3,\"createTime\":1506693918711,\"discussionId\":12895,\"effectiveTime\":1506695067869,\"status\":1,\"updateTime\":1506695067869},{\"channelId\":3,\"createTime\":1503054187076,\"discussionId\":13838,\"effectiveTime\":1523504301607,\"status\":1,\"updateTime\":1523504301607},{\"channelId\":3,\"createTime\":1503054187178,\"discussionId\":13839,\"effectiveTime\":1523504312035,\"status\":1,\"updateTime\":1523504312035},{\"channelId\":3,\"createTime\":1503054187278,\"discussionId\":13840,\"effectiveTime\":1523504321692,\"status\":1,\"updateTime\":1523504321692},{\"channelId\":3,\"createTime\":1503054187376,\"discussionId\":13841,\"effectiveTime\":1523515012482,\"status\":1,\"updateTime\":1523515012482},{\"channelId\":3,\"createTime\":1503054187478,\"discussionId\":13842,\"effectiveTime\":1525749872872,\"status\":1,\"updateTime\":1525749872872},{\"channelId\":3,\"createTime\":1503054188536,\"discussionId\":13855,\"effectiveTime\":1525768221223,\"status\":1,\"updateTime\":1525768221223},{\"channelId\":3,\"createTime\":1503054188688,\"discussionId\":13857,\"effectiveTime\":1526006484750,\"status\":1,\"updateTime\":1526006484750},{\"channelId\":3,\"createTime\":1503054189644,\"discussionId\":13866,\"effectiveTime\":1526006452466,\"status\":1,\"updateTime\":1526006452466},{\"channelId\":3,\"createTime\":1503397655934,\"discussionId\":14036,\"effectiveTime\":1503397675382,\"status\":1,\"updateTime\":1503397675382},{\"channelId\":3,\"createTime\":1504077978811,\"discussionId\":14140,\"effectiveTime\":1504092037664,\"status\":1,\"updateTime\":1504092037664},{\"channelId\":3,\"createTime\":1504080130249,\"discussionId\":14141,\"effectiveTime\":1504080379857,\"status\":1,\"updateTime\":1504080379857},{\"channelId\":3,\"createTime\":1504081175548,\"discussionId\":14144,\"effectiveTime\":1504092036625,\"status\":1,\"updateTime\":1504092036625},{\"channelId\":3,\"createTime\":1504081182317,\"discussionId\":14145,\"effectiveTime\":1504092031239,\"status\":1,\"updateTime\":1504092031239},{\"channelId\":3,\"createTime\":1504256346838,\"discussionId\":14165,\"effectiveTime\":1509450845843,\"status\":1,\"updateTime\":1509450845843},{\"channelId\":3,\"createTime\":1504506703666,\"discussionId\":14190,\"effectiveTime\":1504506722375,\"status\":1,\"updateTime\":1504506722375},{\"channelId\":3,\"createTime\":1504523798848,\"discussionId\":14228,\"effectiveTime\":1507547072131,\"status\":1,\"updateTime\":1507547072131},{\"channelId\":3,\"createTime\":1504595357023,\"discussionId\":14263,\"effectiveTime\":1504616310309,\"status\":1,\"updateTime\":1504616310309},{\"channelId\":3,\"createTime\":1504599608144,\"discussionId\":14265,\"effectiveTime\":1504615559035,\"status\":1,\"updateTime\":1504615559035},{\"channelId\":3,\"createTime\":1504704702157,\"discussionId\":14280,\"effectiveTime\":1504771406379,\"status\":1,\"updateTime\":1504771406379},{\"channelId\":3,\"createTime\":1505818533073,\"discussionId\":14577,\"effectiveTime\":1506320429879,\"status\":1,\"updateTime\":1506320429879},{\"channelId\":3,\"createTime\":1506750831959,\"discussionId\":14853,\"effectiveTime\":1506750866617,\"status\":1,\"updateTime\":1506750866617},{\"channelId\":3,\"createTime\":1507634481038,\"discussionId\":15103,\"effectiveTime\":1527849552082,\"status\":1,\"updateTime\":1527849552082},{\"channelId\":3,\"createTime\":1507637889657,\"discussionId\":15116,\"effectiveTime\":1526006562324,\"status\":1,\"updateTime\":1526006562324},{\"channelId\":3,\"createTime\":1507808198063,\"discussionId\":15192,\"effectiveTime\":1509449963103,\"status\":1,\"updateTime\":1509449963103},{\"channelId\":3,\"createTime\":1508824448181,\"discussionId\":15450,\"effectiveTime\":1509450158678,\"status\":1,\"updateTime\":1509450158678},{\"channelId\":3,\"createTime\":1508934352983,\"discussionId\":15457,\"effectiveTime\":1509449937497,\"status\":1,\"updateTime\":1509449937497},{\"channelId\":3,\"createTime\":1510907841630,\"discussionId\":15925,\"effectiveTime\":1510912607683,\"status\":1,\"updateTime\":1510912607683},{\"channelId\":3,\"createTime\":1507634048673,\"discussionId\":16059,\"effectiveTime\":1507634081978,\"status\":1,\"updateTime\":1507634081978},{\"channelId\":3,\"createTime\":1507634951024,\"discussionId\":16060,\"effectiveTime\":1507635231510,\"status\":1,\"updateTime\":1507635231510},{\"channelId\":3,\"createTime\":1507635736688,\"discussionId\":16068,\"effectiveTime\":1526006568150,\"status\":1,\"updateTime\":1526006568150},{\"channelId\":3,\"createTime\":1507639573959,\"discussionId\":16074,\"effectiveTime\":1530769479453,\"status\":1,\"updateTime\":1530769479453},{\"channelId\":3,\"createTime\":1510642893001,\"discussionId\":17340,\"effectiveTime\":1510800504515,\"status\":1,\"updateTime\":1510800504515},{\"channelId\":3,\"createTime\":1511589646324,\"discussionId\":17984,\"effectiveTime\":1512376998260,\"status\":1,\"updateTime\":1512376998260},{\"channelId\":3,\"createTime\":1511434258674,\"discussionId\":18109,\"effectiveTime\":1512376984896,\"status\":1,\"updateTime\":1512376984896},{\"channelId\":3,\"createTime\":1514182569371,\"discussionId\":21952,\"effectiveTime\":1528354645507,\"status\":1,\"updateTime\":1528354645507},{\"channelId\":3,\"createTime\":1514950471722,\"discussionId\":23664,\"effectiveTime\":1515489292777,\"status\":1,\"updateTime\":1515489292777},{\"channelId\":3,\"createTime\":1516101441764,\"discussionId\":25386,\"effectiveTime\":1519999673084,\"status\":1,\"updateTime\":1519999673084},{\"channelId\":3,\"createTime\":1520598223500,\"discussionId\":41706,\"effectiveTime\":1523341558221,\"status\":1,\"updateTime\":1523341558221},{\"channelId\":3,\"createTime\":1521787722514,\"discussionId\":45718,\"effectiveTime\":1525750013081,\"status\":1,\"updateTime\":1525750013081},{\"channelId\":3,\"createTime\":1530187934989,\"discussionId\":72833,\"effectiveTime\":1530601923255,\"status\":1,\"updateTime\":1530601923255}]";

        JSONArray jsonArray = JSONArray.parseArray(json);
        List<Long> list = Lists.newArrayList();

        for(Object object : jsonArray){
            Long articleId = ((JSONObject)object).getLong("discussionId");
            list.add(articleId);
        }

        System.out.println(list);
        System.out.println(StringUtils.join(list,","));

    }
    

}
