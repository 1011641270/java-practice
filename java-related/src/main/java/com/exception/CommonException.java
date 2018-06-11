/**
 * This File is created by hztianduoduo at 2015年12月17日,any questions please have
 * a message on the http://tian-dd.top.
 */
package com.exception;

/**
 * @author hztianduoduo
 */
public class CommonException extends RuntimeException {

    private static final long serialVersionUID = -1908293116178119114L;

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CommonException(int code) {
        super("code is " + code);
        this.code = code;
    }

    public CommonException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CommonException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public CommonException(int code, Throwable cause) {
        super("code is " + code, cause);
        this.code = code;
    }

}
