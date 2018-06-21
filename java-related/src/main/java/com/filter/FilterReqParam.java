/**
 * @(#)FilterReqParam.java, 18/6/20.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.filter;

/**
 * @author 田躲躲(tian_dd@yeah.net)
 */
public class FilterReqParam {

    private String reqData;

    public FilterReqParam(String reqData){
        this.reqData = reqData;
    }

    public String getReqData() {
        return reqData;
    }

    public void setReqData(String reqData) {
        this.reqData = reqData;
    }
}