/**
 * This File is created by hztianduoduo at 2016年3月10日,any questions please have
 * a message on me!
 */
package com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.file.PoiUtil.SpreadsheetWriter;

public class TestExcel {

    public static List<User> list1 = new ArrayList<User>();
    public static List<User> list2 = new ArrayList<User>();
    public static List<User> list3 = new ArrayList<User>();
    public static List<User> list4 = new ArrayList<User>();

    public static void main(String[] args) {

        //createFile();

        long times = 1457086135000L;
        
        Date date = new Date(times);  
        System.out.println(date); 
        
    }

    public static void download(String fileName,HttpServletResponse response) throws Exception{
        
        File file = new File("C:/" + fileName);
        if (!file.exists()) {
            return;
        }
        
        InputStream inputStream = new FileInputStream(file);
        OutputStream ops = response.getOutputStream();
        byte[] b = new byte[1024];
        int length;
        while ((length = inputStream.read(b)) > 0) {
            ops.write(b, 0, length);
        }
        inputStream.close();
        
    }
    
    
    public static void createFile() {
        try {
            
            String fileName = dateToStr();

            // 创建一个临时的 excel 文件，配置单元格属性，数值格式。
            FileOutputStream os;
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("数据统计");
            os = new FileOutputStream("C:/temp.xls");
            wb.write(os);
            os.close();

            Map<String, XSSFCellStyle> styles = PoiUtil.createStyles(wb);
            String sheetRef = sheet.getPackagePart().getPartName().getName();

            // 生成xml数据临时文件
            File tmp = File.createTempFile("sheet", ".xml");
            Writer fw = new OutputStreamWriter(new FileOutputStream(tmp),
                    "UTF-8");

            generate(fw, styles);
            fw.close();

            // 创建ZIP输出流，将xml数据临时文件数据写入到ZIP文件中。
            FileOutputStream out = new FileOutputStream("C:/" + fileName + ".xlsx");
            PoiUtil.substitute(new File("C:/temp.xls"), tmp,
                    sheetRef.substring(1), out);
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void generate(Writer out, Map<String, XSSFCellStyle> styles)
            throws Exception {

        Calendar calendar = Calendar.getInstance();

        SpreadsheetWriter sw = new SpreadsheetWriter(out);
        sw.beginSheet();

        sw.insertRow(0);
        int styleIndex = styles.get("header").getIndex();
        sw.createCell(0, "用户名", styleIndex);
        sw.createCell(1, "密码", styleIndex);
        sw.createCell(2, "标识", styleIndex);
        sw.createCell(3, "标识", styleIndex);
        sw.createCell(4, "标识", styleIndex);
        sw.endRow();

        int total = 7;
        int pageno = 0;

        int startId = 0;
        int endId = 2;

        while (total > 0) {

                total = total - 2; // 每次处理list的两条数据
                pageno++;

                List<User> list = getList(pageno);

                for (int rownum = startId; rownum < endId; rownum++) {
                    
                    sw.insertRow(rownum);
                    
                    User user = (User) list.get(rownum);
                    
                    sw.createCell(0, user.getUserName());
                    sw.createCell(1, user.getPassWord());
                    sw.createCell(2, user.getFlag());

                    sw.endRow();
                    calendar.roll(Calendar.DAY_OF_YEAR, 1);
                    
                }

                TimeUnit.MICROSECONDS.sleep(1000);
                startId += 2;
                endId += 2;

        }

        sw.endSheet();
    }

    public static ArrayList<User> getList(int count) {

        if (count == 1) {
            list1.add(new User("test1", "pass1", 13));
            list1.add(new User("test2", "pass2"));
            return (ArrayList<User>) list1;
        }
        if (count == 2) {
            list2.add(new User("test1", "pass1", 11));
            list2.add(new User("test2", "pass2"));
            list2.add(new User("test3", "pass3", 0));
            list2.add(new User("test4", "pass4", 0));
            return (ArrayList<User>) list2;
        }
        if (count == 3) {
            list3.add(new User("test1", "pass1", 11));
            list3.add(new User("test2", "pass2"));
            list3.add(new User("test3", "pass3", 0));
            list3.add(new User("test4", "pass4", 0));
            list3.add(new User("test5", "pass5", 0));
            list3.add(new User("test6", "pass6", 1));
            return (ArrayList<User>) list3;
        }
        if (count == 4) {
            list4.add(new User("test1", "pass1", 11));
            list4.add(new User("test2", "pass2"));
            list4.add(new User("test3", "pass3", 0));
            list4.add(new User("test4", "pass4", 0));
            list4.add(new User("test5", "pass5", 0));
            list4.add(new User("test6", "pass6", 1));
            list4.add(new User("test7", "pass7", 1));
            list4.add(new User("test8", "pass8", 1));
            return (ArrayList<User>) list4;
        }

        return null;

    }

    public static String dateToStr() {

        Date date = new Date();// 创建一个时间对象，获取到当前的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 设置时间显示格式
        String str = sdf.format(date);// 将当前时间格式化为需要的类型
        return str;

    }

}
