package com.tedu.mybatis;

import com.tedu.dao.EmpMapper;
import com.tedu.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 完成mybatis的快速入门:查询yonghedb.emp表中的所有员工信息
 */
public class TestMybatis03 {
        static SqlSession session =null;
        static{
         try {
             //1.读取mybatis的核心配置文件(mybatis-config.xml)
             //导入的是这个包下的Resources:import org.apache.ibatis.io.Resources;
             InputStream in=Resources.getResourceAsStream("Mybatis-config.xml");
             //2.通过配置信息获取一个SqlSessionFactory工厂对象
             SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(in);
             //3.通过工厂获取一个SqlSession对象
             session = fac.openSession(true);
         } catch (Exception e) {
             e.printStackTrace();
             }
        }
        /*
        /*Mapper接口开发要求:
	 * 1.提供一个接口,要求XxxMapper文件的namespace值和接口的全限定类名保持一致
	 * 2.执行的sql语句在接口中有对应的方法,并且SQL标签上的id值要和方法的名字保持一致
	 * 3.要求SQL标签上的resultType要和接口中对应方法的返回值类型保持一致(如果方法返回值是一个集合),
	 * resultType只需要指定集合中的泛型
	 * 4.要求SQL标签上的参数类型(可以省略不写)要和接口中对应方法的参数类型保持一致
	 */
    /*
     * 练习16:查询emp表中所有的员工信息
     */
     @Test
    public void testFindAll16(){
         //获取EmaMapper接口子类的实例(传入的是接口的字节码对象)
         EmpMapper mapper=session.getMapper(EmpMapper.class);
         //调用接口子类实例的findALL方法查询所有的员工信息
         List<Emp> list=mapper.findAll();
         /** findAll方法内部,会根据当前接口的全限定接口名 + 当前方法名 到Mapper文件中定位要执行的SQL语句
          * 找到并执行SQL语句,返回处理后的结果
          * 接口的全限定接口名  + 方法名 = namespace + id*/
         for (Emp emp:list) {
             System.out.println(emp);
         }
     }
    /*
     * 练习17:新增员工信息:张菲 java开发工程师 15000
     */
    @Test
    public void testInsert17(){
        EmpMapper mapper=session.getMapper(EmpMapper.class);
        Map map=new HashMap();
        map.put("name","王菲");
        map.put("job","Java开发工程师");
        map.put("salary",15000);
        int rows=mapper.insert06(map);
        System.out.println("影响行数"+rows);
    }
    /*
     * 练习18:修改员工信息:张菲 大数据开发工程师2000
     * */
    @Test
    public void testUpdate07(){
        EmpMapper mapper=session.getMapper(EmpMapper.class);
        Emp emp=new Emp(21,"张飞","大数据开发工程师",20000d);
        int rows=mapper.update07(emp);
        System.out.println("影响行数"+rows);
    }
    /*
     * 练习19:删除员工信息:id为22
     */
    @Test
    public void testDelete08(){
      EmpMapper mapper=session.getMapper(EmpMapper.class);
      int rows=mapper.delete08(24);
        System.out.println("影响行数"+rows);
    }
}
