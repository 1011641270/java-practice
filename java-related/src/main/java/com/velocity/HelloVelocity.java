/**
 * This File is created by hztianduoduo at 2015年12月23日,any questions please have
 * a message on the http://tian-dd.top.
 */
package com.velocity;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Test;

/**
 * @author hztianduoduo
 */
public class HelloVelocity {

    
    //不加载VM文件
    @Test
    public void TestVelocity_1() throws IOException {

        Velocity.init();

        String template = "#set( $iAmVariable = \"good!\" )" + "\n" 
                + "Welcome $name to velocity.com"  + "\n" + "today is $date." + "\n" 
                + "#foreach ($i in $list)" + "\n"  + "$i" + "\n"  + "#end" + "\n"  + "$iAmVariable" + "\n" ;

        VelocityContext context = new VelocityContext();

        context.put("name", "velocity");
        context.put("date", (new Date()).toString());

        List<String> temp = new ArrayList<String>();
        temp.add("tdd");
        context.put("list", temp);

        StringReader reader = new StringReader(template);
        StringWriter writer = new StringWriter();
        
        Velocity.evaluate(context, writer, "mystring", reader);

        writer.close();
        reader.close();

        System.out.println(writer.toString());

    }
    
    //加载VM文件
    @Test
    public void TestVelocity_2(){

        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class",
                ClasspathResourceLoader.class.getName());

        ve.init();

        Template t = ve.getTemplate("hellovelocity.vm");

        VelocityContext ctx = new VelocityContext();

        ctx.put("name", "velocity");
        ctx.put("date", (new Date()).toString());

        List<String> temp = new ArrayList<String>();
        temp.add("tdd");
        ctx.put("list", temp);

        StringWriter sw = new StringWriter();
        t.merge(ctx, sw);
        System.out.println(sw.toString());
        
    }

}
