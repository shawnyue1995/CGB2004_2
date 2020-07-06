package com.tedu.service;
import com.tedu.dao.EmpDao;
import com.tedu.dao.EmpDaoImpl;
/**
 * 员工模块的service(业务层)接口实现类
 * service层 ---> dao层
 */
public class EmpServiceImpl implements EmpService {
	/* 获取Dao接口的子类实例
	 * ——这里使用new对象的方式造成了程序之间的耦合性提升 */
	private EmpDao dao = new EmpDaoImpl();
	
	@Override
	public void addEmp() {
		System.out.println("调用dao层的方法添加员工信息...");
		dao.addEmp();
	}
}
