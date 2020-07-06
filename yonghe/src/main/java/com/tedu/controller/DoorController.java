package com.tedu.controller;

import com.tedu.dao.DoorMapper;
import com.tedu.pojo.Door;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/* 门店模块的controller */
@Controller
public class DoorController {

    /*
    获取DoorMapper接口的子类实例
     */
    @Autowired
    DoorMapper doorMapper;

    /** 1.查询所有的门店信息 */
    @RequestMapping("/doorList")
    public String doorlist(Model model){
    //查询所有门店信息,返回一个门店集合
        List<Door> list= doorMapper.findAll();
        //将门店集合存入Model中,带到door_list.jsp进行显示
        model.addAttribute("list",list);
        //跳转到door_list.jsp
        return "door_list";
    }
    /** 2.根据id删除门店信息 */
    @RequestMapping("/doorDelete")
    public String doorDelete(Integer id){
        //根据门店id删除门店
        doorMapper.deleteById(id);
        //转发到查询所有门店的方法,最终跳转到门店列表,显示最新门店
       return "forward:/doorList";
    }
    /** 3.新增门店信息 name=xxx&tel=xxx&addr=xxx */
    @RequestMapping("/doorAdd")
    public String doorAdd(Door door){
        //调用doorMapper的add方法新增门店信息
        doorMapper.add(door);
        //转发到查询所有门店的方法,最终跳转到门店列表,显示最新门店
        return "forward:/doorList";
    }
    /** 根据id查询门店信息 */
    @RequestMapping("/doorInfo")
    public String doorInfo(Integer id, Model model){
        //调用doorMapper的findById方法,根据id查询门店信息
        Door door=doorMapper.findById(id);
        //将门店对象存入Model的findById方法,根据id查询门店信息
        model.addAttribute("Door",door);
        //跳转到门店修改页面
        return "door_update";
    }

    /** 4.根据id修改门店信息 */
    @RequestMapping("/doorUpdate")
    public String doorUpdate( Door door){
        //调用doorMapper的updateById方法,根据id修改门店信息
        doorMapper.updateById( door );
        //转发到查询所有门店的方法,最终跳转到门店列表,显示最新门店
        return "forward:/doorList";
    }
}
