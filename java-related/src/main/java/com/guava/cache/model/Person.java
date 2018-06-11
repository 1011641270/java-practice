/**
 * This File is created by hztianduoduo at 2015年12月31日,any questions please have a message on the http://tian-dd.top.
 */
package com.guava.cache.model;

/**
 * @author hztianduoduo
 *
 */
public class Person {
    
    private int id;
    private String name;
    
    public Person(int id , String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
