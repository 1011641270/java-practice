/**
 * @(#)OptEnum.java, 18/6/20.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.filter;

/**
 * @author 田躲躲(tian_dd@yeah.net)
 */
public enum OptEnum {

    EQUALTO("==", "等于"),
    NOT_EQUALTO("!=", "不等于"),
    GREATERTHAN(">", "大于"),
    GREATERTHANOREQUALTO(">=", "大于等于"),
    LESSTHAN("<", "小于"),
    LESSTHANOREQUALTO("<=", "小于等于"),
    IN("in", "在该数据列表内"),
    NOTIN("not in", "不在数据列表内"),
    NOT_NULL("not null", "不为空");

    OptEnum(String symbol, String desc){
        this.symbol = symbol;
        this.desc = desc;
    }

    private String symbol;
    private String desc;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}