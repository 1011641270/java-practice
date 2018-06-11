package com.util;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author hztianduoduo
 */
// insert into tb_mobilegames_prizecode values(1,'1',1,'1',1,1,'1');
public class FileUtil {


    public static final String FILEPATH = "";
    public static final String OUTPUTFILEPATH = "";

    public static void main(String[] args) throws Exception{

        //FileUtil.readFile();

        StringTokenizer stringTokenizer = new StringTokenizer("hello world");

        while(stringTokenizer.hasMoreTokens()){
            System.out.println(stringTokenizer.nextToken());
        }

        getFileNames();


    }

    public static void readFile() {

        try {
            // read file content from file
            StringBuffer sb = new StringBuffer("");

            FileReader reader = new FileReader(FILEPATH);
            BufferedReader br = new BufferedReader(reader);

            String sql = "insert into tb_mobilegames_prizecode values";
            String str = null;

            int count = 1399900;
            while ((str = br.readLine()) != null) {

                if(StringUtils.isNotBlank(str) || str.getBytes().length != 0){

                    count++;

                    if(count>100000+1399900){
                        break;
                    }
                    sb.append(sql + "(" + count + "," + "'" + str + "'" + "," + 15 + "," + "'" + 0 + "'" + "," + System.currentTimeMillis() + "," + System.currentTimeMillis() + "," + "'" + 0 + "'" + ")" + ";" + "\n");
                }

            }

            System.out.println(sb);

            createNewFile(OUTPUTFILEPATH,sb.toString());

            br.close();
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createNewFile(String fileDirectoryAndName, String fileContent) {
        try {

            String fileName = fileDirectoryAndName;
            File myFile = new File(fileName);
            if (!myFile.exists())
                myFile.createNewFile();
            else // 如果不存在则扔出异常
                throw new Exception("The new file already exists!");
            FileWriter resultFile = new FileWriter(myFile);
            PrintWriter myNewFile = new PrintWriter(resultFile);
            myNewFile.println(fileContent);
            resultFile.close();
        } catch (Exception ex) {
            System.out.println("无法创建新文件！");
            ex.printStackTrace();
        }
    }

    public static void getFileNames(){

        File file = new File("E:\\workspace");
        String[] files = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.startsWith("N")) {
                    return true;
                }
                return false;
            }
        });

        for (String fileName: files) {
            System.out.println(fileName);
        }

    }
    
}
