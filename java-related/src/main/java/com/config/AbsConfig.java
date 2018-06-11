/**
 * This File is created by hztianduoduo at 2015年12月18日,any questions please have a message on the http://tian-dd.top.
 */
package com.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author hztianduoduo
 *
 */
public abstract class AbsConfig {

    private String fileName;

    private boolean reload;

    public AbsConfig(String fileName, boolean reload) {
        this.fileName = fileName;
        this.reload = reload;
    }

    public String getValue(String key) {
        FileConfigWatch fcw = FileConfigWatch.getFileConfigWatch(fileName,
            reload);
        return fcw.getValue(key);
    }

    public String getValue(String key, String defau) {
        String value = getValue(key);
        if (StringUtils.isEmpty(value)) {
            return defau;
        }
        return value;
    }

    public int getInt(String key, int defau) {
        String value = getValue(key);
        if (StringUtils.isEmpty(value)) {
            return defau;
        }
        return Integer.valueOf(value);
    }

    public long getLong(String key, long defau) {
        String value = getValue(key);
        if (StringUtils.isEmpty(value)) {
            return defau;
        }
        return Long.valueOf(value);
    }

    public boolean getBoolean(String key, boolean defau) {
        String value = getValue(key);
        if (StringUtils.isEmpty(value)) {
            return defau;
        }
        return Boolean.valueOf(value);
    }

    public Date getDate(String key, Date defau) {
        String value = getValue(key);
        if (StringUtils.isEmpty(value)) {
            return defau;
        }

        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
        } catch (ParseException e) {
            return defau;
        }
    }

    
}
