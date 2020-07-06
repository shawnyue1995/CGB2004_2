package com.tedu.dao;

import com.tedu.pojo.Door;

import java.util.List;

/*
DoorMapper接口
声明增删改查方法,对门店信息进行操作
 */
public interface DoorMapper {
    /*
    1.查询所有门店信息
     */
    public List<Door> findAll();
    /** 2.根据门店id删除门店信息 */
    public void deleteById(Integer id);
    /** 3.新增门店信息 */
    public void add(Door door);

    /** 4.根据id查询门店信息(为了回显门店信息) */
    public Door findById(Integer id);
    /** 5.根据id修改门店信息 */
    public void updateById(Door door);
}
