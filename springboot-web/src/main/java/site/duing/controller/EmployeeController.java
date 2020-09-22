package site.duing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import site.duing.dao.EmployeeDao;
import site.duing.pojo.Employee;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    @RequestMapping("/add")
    public String toAdd(){
        return "emp/add";
    }

    @RequestMapping("/deleteEmp")
    public String deleteEmp(Integer id){
        employeeDao.removeEmployeeById(id);
        return "emp/list";
    }

    @RequestMapping("/addEmp")
    public String addEmp(String lastName,String Email,String gender,String department){
        employeeDao.addEmp(lastName,Email,gender,department);
        return "redirect:/emps";
    }

    @RequestMapping("/updateEmp")
    @ResponseBody
    public String updateEmp(){
        return "update";
    }
}
