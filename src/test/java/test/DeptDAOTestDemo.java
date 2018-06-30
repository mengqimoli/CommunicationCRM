package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wyl.crm.domain.Department;
import com.wyl.crm.domain.Employee;
import com.wyl.crm.mapper.DepartmentMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class DeptDAOTestDemo {

	@Autowired
	private DepartmentMapper departmentMapper;

	@Test
	public void test() throws Exception {
		System.out.println(departmentMapper);
	}

	@Test
	public void testSave() throws Exception {

		Department dept = new Department();

		dept.setSn("market");
		dept.setName("市场部");
		Employee manager = new Employee();
		manager.setId(2L);
		dept.setManager(manager);
		Department parent = new Department();
		parent.setId(1L);
		dept.setParent(parent);

		departmentMapper.save(dept);
	}

	@Test
	public void testGet() throws Exception {
		Department department = departmentMapper.getOne(1L);
		System.err.println(department);
	}

	@Test
	public void getAll() throws Exception {
		System.out.println(departmentMapper.getAll());
	}

	@Test
	public void testUpdate() throws Exception {

		Department dept = departmentMapper.getOne(3L);

		dept.setName("开发部");

		departmentMapper.update(dept);
	}

	@Test
	public void testDelete() throws Exception {
		departmentMapper.delete(4L);
	}
}
