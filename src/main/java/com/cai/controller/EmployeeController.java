package com.cai.controller;

import com.cai.dao.DepartmentDao;
import com.cai.dao.EmployeeDao;
import com.cai.pojo.Department;
import com.cai.pojo.Employee;
import com.cai.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    private EmployeeService employeeService;

    //查询全部用户然后去员工页面
    @RequestMapping("/emps")
    public String list(Model model){
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    //去添加页面
    @GetMapping("/add")
    public String add(){
        return "emp/add";
    }

    //先进行添加操作然后再跳回员工页面
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        //添加的操作
        employeeService.save(employee);
        return "redirect:emps";
    }

    //去修改页面
    @GetMapping("/update")
    public String update(Integer id,Model model){
        try{
           Employee employee = this.employeeService.findById(id);
           model.addAttribute("emp",employee);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "emp/update";
    }
    //修改员工信息
    @PostMapping("/updateEmp")
    public String update(Employee employee){
        try{
            employeeService.updateEmp(employee);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:emps";
    }

    //删除员工
    @GetMapping("/del")
    public String del(Integer id){
        try{
            employeeService.delEmp(id);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:emps";
    }

  /*  //去员工页面
    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    //去添加页面
    @GetMapping("/emp")
    public String add(Model model){
        //查出所有部门的信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    //跳回员工页面
    @PostMapping("/emp")
    public String addEmp(Employee employee){
       //添加的操作
        employeeDao.addEmployee(employee);
        return "redirect:emps";
    }

    //去员工修改页面
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id")Integer id,Model model){
        //查看原来的数据
         Employee employee =  employeeDao.getEmployeeById(id);
         model.addAttribute("emp",employee);
        return "emp/update";
    }

    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee){
        employeeDao.addEmployee(employee);
        return "redierct:emps";
    }

    //删除员工
    @GetMapping("/delEmp/{id}")
    public String deleteEmp(@PathVariable("id") int id){
        employeeDao.delete(id);
        return "redirect:emps";
    }*/
}
