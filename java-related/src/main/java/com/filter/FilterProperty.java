/**
 * @(#)FilterProperty.java, 18/6/20.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.filter;

import java.util.HashMap;

/**
 * @author 田躲躲(tian_dd@yeah.net)
 */
public class FilterProperty {

    //过滤器对应操作规则
    private HashMap<OptEnum, Object> ruleMap;

    public FilterProperty(HashMap<OptEnum, Object> ruleMap) {
        this.ruleMap = ruleMap;
    }

    public HashMap<OptEnum, Object> getRuleMap() {
        return ruleMap;
    }

    public void setRuleMap(HashMap<OptEnum, Object> ruleMap) {
        this.ruleMap = ruleMap;
    }

}