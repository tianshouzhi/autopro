package ${package}.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.io.*;
import ${package}.service.*;
import ${package}.domain.*;

/**
*这里写上你对${dbTable.tableName?cap_first}Service的测试代码
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml") 
public class Test${dbTable.tableName?cap_first}Service {
	
	@Autowired
	private I${dbTable.tableName?cap_first}Service ${dbTable.tableName}Service; 

	@Test
	public void testSave() {
		/*${dbTable.tableName?cap_first} ${dbTable.tableName}=null;
		${dbTable.tableName}Service.save(${dbTable.tableName});*/ 
	}
	
	@Test
	public void testUpdate() {
	//${dbTable.tableName?cap_first} ${dbTable.tableName}=null;
	//${dbTable.tableName}Service.save(${dbTable.tableName}); 
	}
	
	@Test
	public void testDelete() {
	//Serializable id=null;
	//${dbTable.tableName}Service.delete(id); 
	}

	@Test
	public void testfindById() {
	//Serializable id=null;
	//${dbTable.tableName?cap_first} ${dbTable.tableName}=${dbTable.tableName}Service.findById(id);
	//System.out.println(${dbTable.tableName}); 
	}
	
}
