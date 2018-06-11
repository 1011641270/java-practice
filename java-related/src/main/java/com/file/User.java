/**
 * This File is created by hztianduoduo at 2016年3月10日,any questions please have a message on me!
 */
package com.file;

/**
 * @author hztiandd@tian-dd.top
 * 
 * 2016年3月10日
 */
public class User {
    
    
    private String userName;
    
    private String passWord;
    
    private int flag;
    
    /**
     * @param userName
     * @param passWord
     * @param flag
     */
    public User(String userName, String passWord, int flag) {
        super();
        this.userName = userName;
        this.passWord = passWord;
        this.flag = flag;
    }
    
    public User(String userName, String passWord) {
        super();
        this.userName = userName;
        this.passWord = passWord;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the passWord
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * @param passWord the passWord to set
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * @return the flag
     */
    public int getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }
    
    
    
    

}
