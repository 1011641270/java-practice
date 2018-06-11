package com.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class FileConfigWatch extends Thread {
    
    private static Logger logger = LoggerFactory.getLogger(FileConfigWatch.class);

    public static final long DEFAULT_DELAY = 60000L;

    private static String CONFIG_DIR = null;

    public static void setConfigDir(String dir) {
        CONFIG_DIR = dir;
    }

    public static String getConfigDir() {
        return CONFIG_DIR;
    }

    private static Map<String, FileConfigWatch> watchMap = new ConcurrentHashMap<String, FileConfigWatch>();

    private String filename;

    private long delay = 60000L;

    private File file;

    private long lastModif = 0L;

    private boolean warnedAlready = false;

    private boolean interrupted = false;

    private Properties prop;

    public FileConfigWatch(String filename, boolean reload) {
        this.filename = filename;
        this.file = new File(filename);
        checkAndConfigure();
        
        /** 需要reload */
        if (reload) {
            setDaemon(true);
            start();
        }
       
    }

    public static FileConfigWatch getFileConfigWatch(String filename, boolean reload) {
        if (CONFIG_DIR != null) {
            filename = CONFIG_DIR + filename;
        }
        FileConfigWatch fcw = watchMap.get(filename);
        if (fcw == null) {
            synchronized (watchMap) {
                fcw = watchMap.get(filename);
                if (fcw == null) {
                    fcw = new FileConfigWatch(filename, reload);
                    watchMap.put(filename, fcw);
                }
            }
        }
        return fcw;
    }

    /**
     * 获得指定key的配置值
     * 
     * @param key
     * @return
     */
    public String getValue(String key) {
        if (prop == null) {
            logger.warn("config file not loaded, can't get its value {}", filename);
            return null;
        }
        return prop.getProperty(key);
    }

    /**
     * 获取指定key的配置值，如果key不存在，返回默认值
     * 
     * @param key
     * @param defau
     * @return
     */
    public String getValue(String key, String defau) {
        String value = getValue(key);
        if (StringUtils.isEmpty(value)) {
            return defau;
        }
        return value;
    }

    /**
     * 获取配置的整数值
     * 
     * @param key
     * @param defau
     * @return
     */
    public int getInt(String key, int defau) {
        String value = getValue(key);
        if (StringUtils.isEmpty(value)) {
            return defau;
        }
        return Integer.valueOf(value);
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    private void doOnChange() {
        Properties p = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            p.load(in);
            prop = p;
            logger.info("load config file success:{}", filename);
        } catch (IOException e) {
            logger.error("load config file fail:{}", filename, e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {}
            }
        }
    }

    private void checkAndConfigure() {
        boolean fileExists;
        try {
            fileExists = this.file.exists();
        } catch (SecurityException e) {
            logger.warn(
                "Was not allowed to read check file existance, file:[{}].",
                this.filename);
            this.interrupted = true;
            return;
        }
        //文件不存在，从classpath里面寻找
        if (!fileExists) {
            //解析出文件名再查找
            filename = filename;
            try {
                URL u = Thread.currentThread().getContextClassLoader()
                    .getResource(filename);
                if (u != null) {
                    file = new File(u.toURI());
                    fileExists = file.exists();
                }
            } catch (Exception e) {
                logger.error("check file exist fail through classloader:{}",
                    filename, e);
            }
        }
        //有可能是jar包里的文件，直接通过classloader加载
        if (!fileExists) {
            try {
                Properties p = new Properties();
                p.load(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(filename));
                prop = p;
                logger.info("load config file success through classloader:{}",
                    filename);
            } catch (Exception e) {
                logger.error("load config file fail through classloader:{}",
                    filename, e);
            }
        }

        if (fileExists) {
            long l = this.file.lastModified();
            if (l > this.lastModif) {
                this.lastModif = l;
                doOnChange();
                this.warnedAlready = false;
            }
        } else if (!this.warnedAlready) {
            logger.debug("[{}] does not exist.", this.filename);
            this.warnedAlready = true;
        }
    }

    public void run() {
        logger.info("start reload config file={}", this.filename);
        while (!this.interrupted) {
            try {
                logger.info("reload config file={}", this.filename);
                Thread.sleep(this.delay);
                checkAndConfigure();
            } catch (InterruptedException e) {
                logger.error("interrupted error:{}", filename, e);
            } catch (Exception e) {
                logger.error("checkAndConfigure error:{}", filename, e);
            }
        }
    }
}
