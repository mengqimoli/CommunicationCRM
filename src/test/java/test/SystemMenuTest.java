package test;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wyl.crm.domain.Department;
import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.SystemMenu;
import com.wyl.crm.service.ISystemMenuService;
import com.wyl.crm.utils.UserContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class SystemMenuTest {

	@Autowired
	private ISystemMenuService systemMenuService;;
	@Test
	public void test() {
		List<SystemMenu> systemMenuByEmpId = systemMenuService.getAll();
		System.out.println(systemMenuByEmpId.size());
	}
	
	@Test
	public void testSave() throws Exception {
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setUsername("wangyilin");
		employee.setPassword("000000");
		UserContext.setUser(employee);
		SystemMenu menu = new SystemMenu();
		menu.setName("很帅的菜单");
		menu.setIntro("很帅的菜单");
		systemMenuService.save(menu);
	}
	@Test
	public void testgetOne() throws Exception {
		SystemMenu one = systemMenuService.getOne(1L);
		System.out.println(one);
	}

}
