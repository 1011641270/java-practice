/**
 * This File is created by hztianduoduo at 2015年12月17日,any questions please have a message on the http://tian-dd.top.
 */
package com.exception;

import com.util.Const;

/**
 * @author hztianduoduo
 *
 */
public class ParseException extends CommonException{
    
    private static final long serialVersionUID = 1L;

    public ParseException() {
        super(Const.PARSEERROR.code);
    }

    public ParseException(String message) {
        super(Const.PARSEERROR.code, message);
    }

    public ParseException(String message, Throwable cause) {
        super(Const.PARSEERROR.code, message, cause);
    }

    public ParseException(Throwable cause) {
        super(Const.PARSEERROR.code, cause);
    }

}
