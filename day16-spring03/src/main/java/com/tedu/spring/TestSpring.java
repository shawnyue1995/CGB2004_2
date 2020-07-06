package com.tedu.spring;

import com.tedu.pojo.User;
import com.tedu.service.EmpService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    //1.获取spring容器对象(传入spring配置文件的路径)
    ClassPathXmlApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
    /*
     * 1.测试spring的IOC(控制反转)
     * 目标:通过spring容器创建EmpService接口的实例,EmpDao接口的实例
     *
     */
    @Test
    public void testIOC(){
        //2.获取通过spring容器获取EmpService接口子类实例
        EmpService service= (EmpService) ac.getBean("empService");
        System.out.println(service);
    }
    /*
     * 2.测试Bean对象的单实例和多实例
     * 单实例:通过spring容器获取某一个类的实例,从头到尾获取的都是同一个实例,
     * 		即spring只会为该类创建一个对象,每次返回的都是这个对象
     * 优点:节省内存空间
     * 缺点:可能会出现线程安全问题,因为所有线程访问都是同一个实例
     * 多实例:通过spring获取某一个类的实例,每次获取的都是不同的实例
     * 		即spring容器都会为该类创建新的实例,每次返回的都是不同的实例
     * 优点:不会出现线程安全问题,因为每个线程持有的都是不同的实例
     * 缺点:太耗费内存空间,可能会导致程序效率低下
     */
    @Test
    public void testBean(){
        //1.通过spring容器获取User类的实例
        User u1= (User) ac.getBean("user");
        User u2= (User) ac.getBean("user");
        //思考:u1和u2指向的是同一个对象(实例)吗
        //2.比较u1==u2
        if (u1==u2){
            System.out.println("当前user类的实例是单实例!");
        }else{
            System.out.println("当前user类的实例是多实例!");
        }
    }
    @Test
    public void testDI(){
        //通过spring容器获取User对象
        User user= (User) ac.getBean("user");
        System.out.println(user);
    }
}
