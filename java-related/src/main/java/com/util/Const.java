package com.util;

/**
 * Created by hztianduoduo on 2018/6/11.
 */
public enum Const {

    PARSEERROR(400400, "parseError");

    public final int code;

    public final String msg;

    private Const(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Const valueOf(int code) {
        for (Const rc: Const.values()) {
            if (rc.code == code)
                return rc;
        }
        return null;
    }

}
