/**
 * @(#)FilterChainImpl.java, 18/6/20.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.filter;

import java.util.ArrayList;

/**
 * @author 田躲躲(tian_dd@yeah.net)
 */
public class FilterChainImpl implements FilterChain{

    ArrayList<FilterVerifyHandler> list = new ArrayList<>();

    @Override
    public boolean doFilter(FilterReqParam filterReqParam) {

        //可以对list进行自定义规则排序，优先级处理
        for(FilterVerifyHandler filterVerifyHandler : list){
            //自定义验证规则
            if(!filterVerifyHandler.handlerFilterRule(filterReqParam)){
                return false;
            }
        }

        return true;
    }

    @Override
    public void add(FilterVerifyHandler filterVerifyHandler) {
        list.add(filterVerifyHandler);
    }
}