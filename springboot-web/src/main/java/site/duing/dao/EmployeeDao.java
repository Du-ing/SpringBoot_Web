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
    public void add(Employee employee){
        if (employee.getId() == null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));

        employees.put(employee.getId(),employee);
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
    public void removeEmploeeById(Integer id){
        employees.remove(id);
    }
}
