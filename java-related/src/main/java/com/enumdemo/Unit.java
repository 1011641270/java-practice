/**
 * This File is created by hztianduoduo at 2015年12月31日,any questions please have
 * a message on the http://tian-dd.top.
 */
package com.enumdemo;

import com.exception.ParseException;

/**
 * @author hztianduoduo
 */
public enum Unit {

    
    TEST1 {
        public String getName() {
            return "TEST1";
        }
    },
    TEST2 {
        public String getName() {
            return "TEST2";
        }
    };

    public String getName() throws ParseException {
        throw new ParseException();
    }
    
    public static void main(String[] args) throws ParseException {
        System.out.println(Unit.TEST1.getName());
    }
    
}
