package com.cai.service.Impl;

import com.cai.dao.EmployeeDao;
import com.cai.pojo.Employee;
import com.cai.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/*用户管理业务层*/
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    //添加用户
    @Override
    @Transactional
    public void save(Employee employee) {
         this.employeeDao.addEmployee(employee);
    }

    //查询所有用户
    @Override
    public List<Employee> getAll() {
        return this.employeeDao.selectAll();
    }

    //删除用户
    @Override
    public void delEmp(Integer id){
        this.employeeDao.delete(id);
    }

    //通过id查询
    @Override
    public Employee findById(Integer id) {
        return this.employeeDao.selectEmpById(id);
    }

    @Override
    public void updateEmp(Employee employee) {
        this.employeeDao.update(employee);
    }

    //通过用户名查询
    @Override
    public Employee findByName(String lastname) {
        return this.employeeDao.selectEmpByName(lastname);
    }
    /*//通过id查询员工
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }*/
}
