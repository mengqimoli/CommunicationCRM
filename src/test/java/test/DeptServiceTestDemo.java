package test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wyl.crm.domain.Department;
import com.wyl.crm.domain.Employee;
import com.wyl.crm.service.IDepartmentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class DeptServiceTestDemo {

	@Autowired
	private IDepartmentService departmentService;;

	@Test
	public void testName() throws Exception {
		System.out.println(departmentService);
	}

	@Test
	public void testSave() throws Exception {
		Department dept = new Department();
		dept.setSn("boss");
		dept.setName("总经办");
		Employee manager = new Employee();
		manager.setId(1L);
		dept.setManager(manager);

		departmentService.save(dept);
	}

	@Test
	public void testDelete() throws Exception {
		departmentService.delete(5L);
	}

	@Test
	public void testUpdate() throws Exception {
		Department dept = departmentService.getOne(1L);
		dept.setDirPath("adfadfasdf");
		dept.setSn("dev");
		Employee manager = new Employee();
		manager.setId(2L);
		dept.setManager(manager);
		departmentService.update(dept);
	}

	@Test
	public void getOne() throws Exception {
		Department dept = departmentService.getOne(1L);
		System.err.println(dept);
	}
	
	@Test
	public void getAll() throws Exception {
		List<Department> all = departmentService.getAll();
		System.err.println(all);
	}
}
