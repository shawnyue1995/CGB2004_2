package com.tedu.controller;

import com.tedu.pojo.User;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/*@Controller作用:1识当前类属于controller层
 * 2.配合包扫描使用,在springmvc的配置文件中会配置扫描"com.tedu.controller"包,
 * 如果在这个包下的类上,有@Controller注解,那么这么类的对象的创建就会交给spring容器负责
 * 当前这个Hellocontroller是一个普通类,不是Servlet,不是web资源,不能右键run as ,run on server
 * localhost:8080/day16-springmvc/hello
 */
@Controller
public class HelloController {
    /*
    @RequestMapping注解:为当前方法绑定一个请求路径
	如果通过浏览器请求这个注解中的路径,就会执行下面的方法
     */
    @RequestMapping("/hello")
    public String testHello(){
        System.out.println("testhello方法执行了...Hello Springmvc...");
        return "home";
    }
    /*
     * 1.测试springmvc的参数绑定---简单类型参数绑定
     * /testParam01?name=张飞&age=28&addr=河北
     * 如何呼气获取请求中name,age,addr的值
     * 可以再方法上声明形参,要求,形参的名字必须和请求参数的名字保持一致
     */
    @RequestMapping("/testParam01")
    public String testParam01(String name,Integer age,String addr){
        System.out.println("name="+name+",age="+age+",addr="+addr);
        return "test";
    }
    /*
     * 2.简单类型的参数绑定练习:++++++++++++++++++++++++++
     *
     * /testParam02?username=刘备&psw=1234&addr=河北&age=30
     *		 如何获取请求中的username,psw,addr,age的参数值
     * http:localhost:8080/day16-springmvc/testParam02?username=刘备&psw=1234&addr=河北&age=30
     */
    @RequestMapping("/testParam02")
    public String testParam02(String username,String psw,String addr,Integer age){
        System.out.println("username="+username );
        System.out.println("psw="+psw );
        System.out.println("addr="+addr );
        System.out.println("age="+age );
        return "test";
    }
    /*
     * 3.包装类型的参数绑定
     *  /testParam03?name=刘德华&age=18&addr=中国香港
     *  如果请求参数较多,或者希望将请求参数的值封装到一个对象,可以直接在方法上
     *  声明一个包装类型的对象,使用包装对象接收请求中的参数值
     *	要求包装对象中有请求参数对应的setter方法
     */
    @RequestMapping("/testParam03")
    public String testParam03(User user){
        System.out.println("user.name="+user.getName());
        System.out.println("user.age="+user.getAge());
        System.out.println("user.addr="+user.getAddr());
        return "test";
    }
    /*
     * 4.日期类型的餐数绑定
     * /testParam04?date=2020/06/23 10:52:39
     * /testParam04?date=2020-06-23 10:52:39
     * 由于springmvc底层在接收日期类型的参数时,默认是以/(斜杠)作为分隔符
     * 如果提交以横杠(-)分隔的日期餐数,springmvc识别不了,机会报400错误!
     *
     * 解决方法1:如果是springmvc接收参数,以后再向服务器提交日期参数时,日期使用斜杠分隔	`*
     * 解决方法2:也可以将springmvc默认接收日期参数的格式改为以横杠(-)分隔!
     * 		这种方法改完后,springmvc只能识别横杠分隔的日期,不能识别斜杠分隔的日期!
     */
    @RequestMapping("/testParam04")
    public String testParam04(Date date){
        System.out.println(date);
        return "test";
    }
    /*
     * 5.测试springmvc实现请求转发
     * 1.在springmvc中,从controller跳转到jsp,默认就是请求转发!
     * 理由:得通过转发才能访问WEB-INF目录下的资源
     * 		从controller跳转到jsp,地址栏没变化,说明是转发
     * 2.如果是从controller中的方法转发到另外一个方法,如何实现转发呢?
     *
     */
    @RequestMapping("/testForward")
    public String testForward(){
        System.out.println("testForward方法执行了...");
        return "forward:/toPage";
    }
    @RequestMapping("/toPage")
    public String toPage(){
        System.out.println("toPage方法执行了...");
        return "test";
    }
    /*
     * 6.测试springmvc实现请求重定向
     * 从controller的一个方法重定向到另一个方法,可以在最后:
     * return "redirect:/访问路径"
     * 从controller的一个方法重定向到百度
     */
    @RequestMapping("/testRedirect")
    public String testRedirect(){
        System.out.println("testRedirect方法执行了...");
        //从当前方法重定向到百度
        return "redirect:http://www.baidu.com";
    }
    /*
     * 7.POST提交中文参数时的乱码处理
     * 接收请求中的user和like参数的值(可能会有多个值)
     */
    @RequestMapping("/testParam05")
    public String testParam05(String user,String[] like){
        System.out.println("user="+user);
        System.out.println("like数组="+ Arrays.toString(like));
        return "test";
    }
    @RequestMapping("/testModel01")
    public String testModel01(Model model){
        User u1=new User("王五",20,"北京海淀区");
        //将User对象存入Model中
        model.addAttribute("user",u1);
        //转发带到home.jsp
        return "home";
    }
    @RequestMapping("/testModel02")
    public String testModel02(Model model){
        List<User> list=new ArrayList<>();
        User u1=new User("王五",20,"北京海淀区");
        User u2=new User("赵六",24,"北京昌平");
        User u3=new User("周七",28,"北京丰台");
        //将List集合存入Model中
        list.add(u1);
        list.add(u2);
        list.add(u3);
        //转发带到home.jsp,在jsp中显示
        return "home";
    }
    /* 自定义日期格式转换器
     * 将springmvc默认以斜杠(/)分隔日期改为以横杠分隔(-)
     */
    @InitBinder
    public void InitBinder(ServletRequestDataBinder binder){
        binder.registerCustomEditor(java.util.Date.class,
                new CustomDateEditor(
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),true)
        );
    }
}
