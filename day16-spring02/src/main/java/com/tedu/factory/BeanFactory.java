package com.tedu.factory;

import org.junit.Test;

import java.io.InputStream;
import java.util.Properties;

public class BeanFactory {
    //用于加载config.properties文件中所有内容
    private static Properties prop=null;
    static{
        //为prop进行初始化
        prop=new Properties();
        try {
            //通过类加载器config.properties文件,获取指向该文件的流对象
            //通过当前类的解码对象获取加载当前类加载器
            ClassLoader loader=BeanFactory.class.getClassLoader();
            //通过加载当前类的类加载器,去加载config文件
            InputStream in=loader.getResourceAsStream("config.properties");
            //通过指向config文件的流对象,将config文件中所有内容加载进prop对象中
            prop.load(in);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /*
    根据接口名(EmpService,EmpDao)获取该接口对应的子类实例
     */
    public static Object getBean(String key){
        //通过key(接口名)获取该接口对应的子类的全限定类名
        String ClassName=prop.getProperty(key);
        try {
            Object obj=Class.forName(ClassName).newInstance();
            return obj;
        }catch (Exception e ){
            e.printStackTrace();
        }
        return null;
    }

    /*测试prop中是否加载到了config文件中所有内容,
     * 如果能通过prop获取到config文件中的内容,
     * 则说明已经成功的将config文件中的内容加载进了prop对象中
     */
    @Test
    public void testProp(){
        //prop.getProperty 通过config文件中的key获取其对应的value
        String value1=prop.getProperty("EmpService");
        System.out.println("EmpService"+value1);
        String value2=prop.getProperty("EmpDao");
        System.out.println("EmpDao"+value2);
    }
}
