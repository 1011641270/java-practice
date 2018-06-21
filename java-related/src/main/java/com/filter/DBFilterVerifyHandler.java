/**
 * @(#)DBFilterVerifyHandler.java, 18/6/20.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.filter;

import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author 田躲躲(tian_dd@yeah.net)
 */
public class DBFilterVerifyHandler extends FilterVerifyHandler{

    public DBFilterVerifyHandler(String filterKey, VertifyMode vertifyMode, FilterProperty filterProperty){
        super.filterKey = filterKey;
        super.vertifyMode = vertifyMode;
        super.filterProperty = filterProperty;
    }

    @Override
    public boolean handlerFilterRule(FilterReqParam filterReqParam) {
        return super.handlerFilterRule(filterReqParam);
    }

    public static void main(String[] args) {

        //{"IN":["清单","文章"],"GREATERTHANOREQUALTO":"5"}
        HashMap<OptEnum, Object> ruleMap = Maps.newHashMap();
        ruleMap.put(OptEnum.GREATERTHANOREQUALTO, "5");
        ruleMap.put(OptEnum.IN, new ArrayList<String>(){{
            add("清单");
            add("文章");
        }});

        DBFilterVerifyHandler filterVerifyHandler = new DBFilterVerifyHandler("filter_key", VertifyMode.ADD, new FilterProperty(ruleMap));
        System.out.println(filterVerifyHandler.handlerFilterRule(new FilterReqParam("清单")));

    }


}