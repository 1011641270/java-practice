package com.jsoup;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Jsoup {
    
    public static void main(String[] args) throws Exception{
        
        int page = 0;
        while(true){
            page++;
            boolean isLoop = printImgLink(getDivContent(page));
            if(isLoop){
                break;
            }
        }
        
    }
    
    
    public static Elements getDivContent(int page) throws IOException{
        
        String url = "http://i.wap.58.com/wapimg/?" + "page="+ page + "&infoid=25150533913132&cate=zufang&citylistname=bj";
        
        Document document = org.jsoup.Jsoup.connect(url).get();
        Elements elements = document.getElementsByClass("cnt");
        
        return elements;
        
    }
    
    
    public static boolean printImgLink(Elements elements){
        
        Elements elementsContent = elements.select("a");
        int count = 0;
        int eleSize = elementsContent.size();
        boolean isEndLoop = false;
        
        for (Element element: elementsContent) {
            System.out.println(element.select("img").attr("src"));
            count ++;
            if(count == 2 && element.select("a").text().equals("[上一张图]") && eleSize ==3){
                isEndLoop = true;
                return isEndLoop;
            }
        }
        
        return isEndLoop;
    }
    
}
