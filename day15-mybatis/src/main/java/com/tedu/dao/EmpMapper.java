package com.tedu.dao;

import com.tedu.pojo.Emp;

import java.util.List;
import java.util.Map;

//com.tedu.dao.EmpMapper
public interface EmpMapper {
    /*
    练习1:查询emp表中所有的员工信息
     */
    public List<Emp> findAll();
    /*
    练习6:新增员工信息
     */
    public int insert06(Map map);
    /*
    练习7:修改员工信息
     */
    public int update07(Emp map);
    /*
    练习8:删除员工信息
     */
    public int delete08(Integer id);
}