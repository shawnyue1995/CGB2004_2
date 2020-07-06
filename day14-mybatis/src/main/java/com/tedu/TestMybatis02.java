package com.tedu;

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
public class TestMybatis02 {
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
        练习2:新增员工信息:赵云保安6000
         */
    @Test
    public void testInsert() throws IOException {
        //通过namespace+id找到并执行sql语句,返回并执行结果
        int rows=session.insert("EmpMapper.insert02");
        System.out.println("影响行数"+rows);
    }
/*练习3：修改员工信息：赵云 保镖 20000*/
    @Test
    public void testUpdate(){
        int rows=session.update("EmpMapper.update03");
        System.out.println("影响行数"+rows);
    }
    /*练习4: 删除name为'赵云'的记录 */
    @Test
    public void testDelete(){
        int rows=session.delete("EmpMapper.delete04");
        System.out.println("影响行数"+rows);
    }
    //-------------占位符---------------
/*练习5: 查询emp表中指定id的员工信息*/
    @Test
    public void testFindById(){
        Integer id=1;
        //根据namespace+id找到并执行sql语句,返回结果
        Emp emp=session.selectOne("EmpMapper.findById05",id);
        System.out.println(emp);
    }
    /*
     * 练习6: 新增员工信息: 张飞 Java开发工程师 15000
     */
    @Test
    public void testInsert02(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name","张飞");
        map.put("job","java开发工程师");
        map.put("salary",15000);
        int rows=session.insert("EmpMapper.insert06",map);
        System.out.println("影响行数"+rows);
    }
    /*
     * 练习7: 修改员工信息: 张飞 架构师 25000
     */
    @Test
    public void testUpdate02(){
        Emp emp=new Emp(null,"张飞","架构师",25000.0);
        int rows=session.update("EmpMapper.update07",emp);
        System.out.println("影响行数"+rows);
    }
    /*
     * 练习8：删除emp表中指定id的员工信息
     */
    @Test
    public void testDelete02(){
        Integer id=1;
        int rows=session.delete("EmpMapper.delete08",id);
        System.out.println("影响行数"+rows);
    }
    /* 练习9: 动态指定要查询的列
     * []id []name []job []salary
     * salary id,name from emp
     * salary id,name,job from emp
     * salary id,name,salary from emp
     */
    @Test
    public void testFindAll02(){
        Map map=new HashMap();
        map.put("colName","id,name");
        String colName="id,name";
        List<Emp> list=session.selectList("EmpMapper.findAll02",map);
        for (Emp emp:list) {
            System.out.println(emp);
        }
    }
    /* 练习10: 根据name模糊查询emp表 :使用#{}占位符*/
    @Test
    public void testFindByName(){
        List<Emp> list=session.selectList("EmpMapper.findByName10","%刘%");
        for (Emp emp:list) {
            System.out.println(emp);
        }
    }
    /* 练习11: 根据name模糊查询emp表:使用${}占位符 */
    @Test
    public void testFindByName02(){
        Map map=new HashMap();
        map.put("name","刘");
        List<Emp> list=session.selectList("EmpMapper.findByName11",map);
        for (Emp emp:list) {
            System.out.println(emp);
        }
    }
    /*
     * 练习12: 根据薪资查询员工信息 如果没有参数, 则不执行where子句, 默认查询所有员工： select * from emp
     * 如果参数中只有minSal(即minSal不为null), 则： ... where salary > minSal
     * 如果参数中只有maxSal(即maxSal不为null), 则： ... where salary < maxSal
     * 如果参数有 minSal、maxSal(即minSal、maxSal不为null), 则： ... where salary > minSal and salary< maxSal
     */
    @Test
    public void testFindBySal12(){
        Map map=new HashMap();
        map.put("minSal",3000);
        map.put("maxSal",4000);
        List<Emp> list=session.selectList("EmpMapper.findBySal12",map);
        for (Emp emp:list) {
            System.out.println(emp);
        }
    }
    /*
     * 练习13: 根据薪资查询员工信息
     * 如果没有参数, 则不执行where子句, 默认查询所有员工：select * from emp
     * 如果参数中只有minSal(即minSal不为null), 则：... where salary > minSal
     * 如果参数中只有maxSal(即maxSal不为null), 则：... where salary < maxSal
     * 如果参数有 minSal、maxSal(即minSal、maxSal不为null), 则：... where salary > minSal and salary < maxSal
     */
    @Test
    public void testFindBySal13(){
        Map map=new HashMap();
        map.put("minSal",3000);
        map.put("maxSal",4000);
        List<Emp> list=session.selectList("EmpMapper.findBySal12",map);
        for (Emp emp:list) {
            System.out.println(emp);
        }
    }
    	/*
	 * 练习14: 根据员工的id批量删除员工信息
	delete from emp where id in (1,3,5,7)
	collection: 如果传的参数仅仅是一个数组或者List集合, collection可以指定为
		array或list; 如果传的是多个参数，用map封装，collection则指定为map中的key
	open: 指定生成的SQL片段以什么符号开始
	close: 指定生成的SQL片段以什么符号结束
	item: 指定变量接收数组或集合中的元素
	separator: 指定一个间隔符, 在将数组或集合中的每个元素拼接到SQL片段之后,
		在后面拼接一个间隔符
	 */
    @Test
    public void testDeletByIds(){
        Integer[] ids={1,3,5,7};
        int rows=session.delete("EmpMapper.deleteByIds",ids);
        System.out.println("影响行数"+rows);
    }
    /*
     * 练习15: 根据员工的id批量更新员工信息 将id为 2、4、6、8的员工的薪资在原有基础上增加1000 update emp set
     * salary=salary + 1000 where id in(2,4,6,8);
     */
    @Test
    public void testUpdateByIds(){
        Integer[] ids={2,4,6,8};
        int rows=session.update("EmpMapper.updateByIds",ids);
        System.out.println("影响行数"+rows);
    }
    /* 练习16：根据员工的id批量查询指定id的员工信息
     * 查询id为 2、4、6、8的员工的信息
     */
    @Test
    public void testFindByIds(){
        Integer[] ids={2,4,6,8};
        List<Emp> list=session.selectList("EmpMapper.findByIds",ids);
        System.out.println(list);
    }
}
