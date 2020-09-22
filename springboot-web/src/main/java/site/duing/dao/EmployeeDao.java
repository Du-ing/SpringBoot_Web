package site.duing.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import site.duing.pojo.Department;
import site.duing.pojo.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {

    //模拟数据库库中的数据
    private static Map<Integer, Employee> employees = new HashMap<Integer, Employee>();

    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees.put(1001,new Employee(1001,"AA","A123456@qq.com",1,new Department(101,"教学部")));
        employees.put(1002,new Employee(1002,"BB","B123456@qq.com",0,new Department(101,"市场部")));
        employees.put(1003,new Employee(1003,"CC","C123456@qq.com",1,new Department(101,"教研部")));
        employees.put(1004,new Employee(1004,"DD","D123456@qq.com",0,new Department(101,"运营部")));
        employees.put(1005,new Employee(1005,"EE","E123456@qq.com",1,new Department(101,"后勤部")));
    }

    //主键自增
    private static Integer initId = 1006;
    //增加一个员工
    public void addEmp(String lastName,String Email,String gender,String department){
        int gender1 = gender.equals("男") ? 1 : 0;
        Department d = null;
        for (Department department1 : departmentDao.getAllDepartment()) {
            if (department1.getDepartmentName().equals(department)){
                d = department1;
                break;
            }
        }
        Employee emp = new Employee(initId++,lastName,Email,gender1,d);
        employees.put(emp.getId(),emp);
    }

    //查询全部员工
    public Collection<Employee> getAll(){
        return employees.values();
    }

    //根据id查询员工
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }

    //删除员工
    public void removeEmployeeById(Integer id){
        employees.remove(id);
    }
}
