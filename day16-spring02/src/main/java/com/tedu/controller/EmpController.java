package com.tedu.controller;
import com.tedu.factory.BeanFactory;
import org.junit.Test;
import com.tedu.service.EmpService;
import com.tedu.service.EmpServiceImpl;
/**
 * 模拟表现层 
 * controller --> service --> dao
 */
public class EmpController {
	/* 获取Service接口的子类实例
	 * ——这里使用new对象的方式造成了程序之间的耦合性提升
	 * 因为如果没有EmpServiceImpl这个类,当前程序就会报错(编译不通过)
	 * 所以我们说 当前类 依赖于EmpServiceImpl这个对象
	 * 或者EmpServiceImpl这个类一旦被替换,整个项目中,所有
	 * new EmpServiceImpl的地方都要修改源码,这样整个项目都需要重新编译,打包,部署
	 * 会造成不必要的麻烦
	 *  */
//	private EmpService service = new EmpServiceImpl();
	private EmpService service= (EmpService) BeanFactory.getBean("EmpService");
	@Test
	public void testAddEmp() {
		System.out.println("调用service层的方法添加员工信息...");
		service.addEmp();
	}
}


