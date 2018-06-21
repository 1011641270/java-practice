/**
 * @(#)FilterVerifyHandler.java, 18/6/20.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 支持自定义扩展FilterVerifyHandler
 * filterKey和filterProperty、vertifyMode的关联关系
 * @author 田躲躲(tian_dd@yeah.net)
 */
public class FilterVerifyHandler {

    //过滤器唯一key
    protected String filterKey;

    //验证方式
    protected VertifyMode vertifyMode;

    //过滤器基本配置项，从DB拉取
    protected FilterProperty filterProperty;

    public boolean handlerFilterRule(FilterReqParam filterReqParam){

        //DB Filter处理逻辑
        HashMap<OptEnum, Object> ruleMap = filterProperty.getRuleMap();
        for(Map.Entry entry : ruleMap.entrySet()){
            OptEnum optEnum = (OptEnum) entry.getKey();
            Object value = entry.getValue();
            //规则处理器，会单独拆分出来做
            if(optEnum.equals(OptEnum.IN)){
                return ((ArrayList)value).contains(filterReqParam.getReqData());
            }
        }

        //TODO：其他filter类型【第三方系统等】处理逻
        // 辑

        return false;

    }

    public enum VertifyMode{
        ADD,
        OR;
    }

}