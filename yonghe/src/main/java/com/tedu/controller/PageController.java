package com.tedu.controller;

import com.tedu.dao.DoorMapper;
import com.tedu.pojo.Door;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PageController {
    @Autowired
    DoorMapper doorMapper;

    /*
    1.测试springmvc运行环境
    http:localhost:8080/yonghe/testmvc
     */
    @RequestMapping("/testmvc")
    public String testmvc() {
        System.out.println("testmvc方法执行了...");
        return "test";
    }
    //获取DoorMapper接口子类实例
    //@Autowired注解的作用:到spring容器中,根据类型(DoorMapper)
    //去寻找该接口的子类实例,由于在spring配置文件配置过扫描
    //com.tedu.dao包下的所有接口,spring会为DoorMapper接口创建
    //子类实例,通过自动装配,将DoorMapper接口的子类实例赋值给doorMapper


    /* 实现通用的页面跳转方法:通过jsp文件的名字作为访问路径访问指定名称的jsp
     *   localhost:8080/yonghe/index
     *  当浏览器访问controller方法上的路径为"index",name/{}中
     *  pageName的值则为"index",再将/{}中pageName的值传递给toPage方法上的形参pageName
     * 那么方法上的形参pageName的值也为"index"
     * 最后将形参pageName直接返回.
     * 因此,若访问路径为/index,最后则跳转到index.jsp
     * 若访问路径为/_top,最后则跳转到_top.jsp
     */
    @RequestMapping("/{pageName}")
    public String toPage(@PathVariable String pageName) {
        return pageName;
    }

    /* 2.测试ssm的运行环境 :查询门店表中的所有门店信息
     */
    @RequestMapping("/testssm")
    public String testssm() {
        List<Door> list = doorMapper.findAll();
        for (Door door : list) {
            System.out.println(door);
        }
        //跳转到test.jsp页面
        return "test";
    }
}
