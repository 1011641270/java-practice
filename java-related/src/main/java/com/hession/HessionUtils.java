/**
 * @(#)HessionUtils.java, 18/9/21.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hession;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 *  1. hessian序列化的时候会取出对象的所有自定义属性，相同类型的属性是子类在前父类在后的顺序。
 *  2. hessian在反序列化的时候，是将对象所有属性取出来，存放在一个map中   key = 属性名  value是反序列类，相同名字的会以子类为准进行反序列化。
 *  3. 相同名字的属性 在反序列化的是时候，由于子类在父类前面，子类的属性总是会被父类的覆盖，由于java多态属性，在上述例子中父类 User.username = null
 * @author 田躲躲(hztianduoduo)
 */
public class HessionUtils implements Serializable{

    public static void main(String[] args) throws Exception{
        new HessionUtils().testSerialize();
    }

    public void testSerialize() throws Exception{
        UserInfo user = new UserInfo();
        user.setUsername("hello world");
        user.setPassword("buzhidao");
        user.setAge(21);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //Hessian的序列化输出
        HessianOutput ho = new HessianOutput(os);
        ho.writeObject(user);

        byte[] userByte = os.toByteArray();
        ByteArrayInputStream is = new ByteArrayInputStream(userByte);


        //Hessian的反序列化读取对象
        HessianInput hi = new HessianInput(is);
        UserInfo u = (UserInfo) hi.readObject();

        System.out.println("姓名：" + u.getUsername());
        System.out.println("密码：" + u.getPassword());
        System.out.println("年龄：" + u.getAge());
    }

    public class User implements Serializable {

        private String username;
        private String password;
        private Integer age;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

    public class UserInfo extends User {

        private String username;

        public UserInfo(){}

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

}

