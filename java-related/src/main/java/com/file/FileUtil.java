/**
 * @(#)FileUtils.java, 18/7/16.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.file;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.List;

/**
 * @author 田躲躲(tian_dd)
 */
public class FileUtil {

    public static void main(String[] args) throws Exception{

        String filePath = "/Users/tian_dd/workspace/java-practice/java-related/src/main/java/com/file/data.txt";
        File file = ResourceUtils.getFile(filePath);
        //System.out.println(FileUtils.readLines(file, "UTF-8").stream().collect(Collectors.joining(",")));

        String content = "{\"msgImage\":\"https://haitao.nos.netease.com/7b3629e4-de4a-42d9-9258-82442561599c.jpg?klsize=960x345\",\"msgJumpUrl\":\"https://m.kaola.com/activity/h5/39929.shtml\",\"msgText\":\"亲爱的，欢迎来到种草社区这个大家庭！快跟着种草熊一起去看看，如何才能玩转种草社区吧！\"}";

        System.out.println(content);
        content = content.replaceAll("</?[^>]+>", "");
        content = content.replaceAll("[^\\d.A-Za-z\u3007\u3400-\u4DB5\u4E00-\u9FCB\uE815-\uE864]", " ");
        System.out.println(content);

        List<String> lines = FileUtils.readLines(file);
        for(String line : lines){
            System.out.println(line+"\"" + "),");
        }

    }

}