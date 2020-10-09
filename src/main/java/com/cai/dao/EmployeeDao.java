package com.cai.dao;

import com.cai.pojo.Department;
import com.cai.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
**
持久层
*/
@Repository
public class EmployeeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    //添加员工
    public void addEmployee(Employee employee){
        String sql = "insert into employee(lastname,email,gender,department,phone) values (?,?,?,?,?)";
        this.jdbcTemplate.update(sql,employee.getLastname(),employee.getEmail(),employee.getGender(),employee.getDepartment(),employee.getPhone());
    }

    //查询全部用户
    public List<Employee> selectAll(){
        String sql = "select * from employee";

        return this.jdbcTemplate.query(sql, new RowMapper<Employee>() {

            @Override
            public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setLastname(resultSet.getString("lastname"));
                employee.setEmail(resultSet.getString("email"));
                employee.setGender(resultSet.getInt("gender"));
                employee.setDepartment(resultSet.getString("department"));
                employee.setPhone(resultSet.getString("phone"));
                return employee;
            }
        });
    }



    //通过用户名查询
    public Employee selectEmpByName(String lastname){
        Employee employee = new Employee();
        try{
            String sql = "select * from employee where lastname = ?";
            Object [] arr = new Object[]{lastname};
            this.jdbcTemplate.query(sql, arr,new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet resultSet) throws SQLException {
                    employee.setId(resultSet.getInt("id"));
                    employee.setLastname(resultSet.getString("lastname"));
                    employee.setEmail(resultSet.getString("email"));
                    employee.setGender(resultSet.getInt("gender"));
                    employee.setDepartment(resultSet.getString("department"));
                    employee.setPhone(resultSet.getString("phone"));
                }
            });
        }catch (Exception e){
           // e.printStackTrace();
        }

        return employee;
    }

    //预修改查询
    public Employee selectEmpById(Integer id){
        Employee employee = new Employee();
        String sql = "select * from employee where id = ?";
        Object [] arr = new Object[]{id};
        this.jdbcTemplate.query(sql, arr,new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                employee.setId(resultSet.getInt("id"));
                employee.setLastname(resultSet.getString("lastname"));
                employee.setEmail(resultSet.getString("email"));
                employee.setGender(resultSet.getInt("gender"));
                employee.setDepartment(resultSet.getString("department"));
                employee.setPhone(resultSet.getString("phone"));
            }
        });
        return employee;
    }
    //修改员工信息
    public void update(Employee employee){
        String sql = "update employee set lastname=?,email=?,gender=?,department=?,phone=? where id = ?";
        this.jdbcTemplate.update(sql,employee.getLastname(),employee.getEmail(),
                employee.getGender(),employee.getDepartment(),employee.getPhone(),employee.getId());
    }

    //删除员工
    public void delete(Integer id){
        String sql = "delete from employee where id = ?";
        this.jdbcTemplate.update(sql,id);
    }
/*

    //模拟数据库中的数据
    private static Map<Integer,Employee> employees = null;
    //员工有所属的部门
    @Autowired
    private DepartmentDao departmentDao;
    static {
       employees = new HashMap<Integer,Employee>();//创建一个员工表

        employees.put(1001, new Employee(1001,"张三","2410132991@qq.com",1,new Department(101,"教学部"),"13622649830"));
        employees.put(1002, new Employee(1002,"李四","2410132992@qq.com",0,new Department(102,"市场部"),"13622649831"));
        employees.put(1003, new Employee(1003,"王五","2410132993@qq.com",1,new Department(103,"教研部"),"13622649832"));
        employees.put(1004, new Employee(1004,"赵六","2410132994@qq.com",0,new Department(104,"运营部"),"13622649834"));
        employees.put(1005, new Employee(1005,"田七","2410132995@qq.com",1,new Department(105,"后勤部"),"13622649835"));

    }


    //主键自增
    private static Integer initId = 1006;
    //增加员工
    public void save(Employee employee){
        if(employee.getId() == null){
            employee.setId(initId++);
        }




        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }

    //查询全部员工信息
    public Collection<Employee> getAll(){
        return employees.values();
    }

    //通过id查询员工
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }

    //删除员工
    public void delete(Integer id){
        employees.remove(id);
    }
*/

}
