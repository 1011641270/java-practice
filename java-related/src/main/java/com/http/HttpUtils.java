/**
 * @(#)HttpUtils.java, 18/6/20.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.http;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * @author 田躲躲(tian_dd@yeah.net)
 */
public class HttpUtils {

    public static void main(String[] args) throws Exception{

        for(int i=15086;i<=65309;i++){
            String url = "http://10.206.58.38:2900/kaola_community_ms_kol_search/kol_type/"+i;
            System.out.println(new HttpUtils().doDelete(url, null, null));
            System.out.println("i = " + i);
            Thread.sleep(10);
        }

    }

    private String doDelete(String url,String reqbody,Map<String,Object> map){
        FormEncodingBuilder builder=addParamToBuilder(reqbody, map);
        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .delete(body)
                .build();
        return execute(request);
    }

    public String execute(Request request){
        try {
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
        }
        return "{\"error\":\"fail\"}";
    }

    public FormEncodingBuilder addParamToBuilder(String reqbody,Map<String,Object> map){
        FormEncodingBuilder builder=new FormEncodingBuilder();
        if(!StringUtils.isEmpty(reqbody)){
            if(reqbody.startsWith("?")){
                reqbody=reqbody.substring(1);
            }
            String[] params=reqbody.split("&");
            for(int i=0;i<params.length;i++){
                if(params[i].equals("")){
                    continue;
                }
                String [] kv=params[i].split("=");
                builder.add(kv[0], kv[1]);
            }
        }
        if(map!=null){
            Iterator<Map.Entry<String,Object>> ite= map.entrySet().iterator();
            for(;ite.hasNext();){
                Map.Entry<String,Object> kv=ite.next();
                builder.add(kv.getKey(), kv.getValue().toString());
            }
        }
        return builder;
    }

}