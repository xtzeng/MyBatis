
import java.util.Date;

import org.junit.Test;

import com.immutable.alias.dao.impl.StudentDaoImpl;
import com.immutable.alias.pojos.Student;


public class StudentTest {

	
	@Test
	public void testAdd() {
		StudentDaoImpl studentDaoImpl = new StudentDaoImpl();
		System.out.println("测试插入");
		Student addStudent = new Student();
		addStudent.setName("张三");
		addStudent.setBirth(new Date());
		addStudent.setScore(88);
		System.out.println(studentDaoImpl.addStudent(addStudent));
		System.out.println("测试根据id查询");
		System.out.println(studentDaoImpl.selectStudentById(4));
		System.out.println("测试模糊查询");
	}	
	
	@Test
	public void testUpdate() {
		
		StudentDaoImpl studentDaoImpl = new StudentDaoImpl();
		System.out.println("测试更新学生信息");
		Student updateStudent = new Student();
		updateStudent.setId(2);
		updateStudent.setName("李四1");
		updateStudent.setBirth(new Date());
		updateStudent.setScore(21);
		System.out.println(studentDaoImpl.updateStudent(updateStudent));
	
	}
	
}
