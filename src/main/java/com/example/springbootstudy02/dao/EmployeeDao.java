package com.example.springbootstudy02.dao;

import com.example.springbootstudy02.pojo.Department;
import com.example.springbootstudy02.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapAutoConfiguration;
import org.springframework.core.metrics.StartupStep;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//员工Dao
@Repository
public class EmployeeDao {

    //模拟数据库中的数据
    private static Map<Integer, Employee> employees=null;
    //员工有所属的部门
    @Autowired
    private  DepartmentDao departmentDao;  //在此处，需要在两个dao类前加上@Repository
    static {
        employees=new HashMap<Integer, Employee>();   //创建一个部门表

        employees.put(1001,new Employee(1001,"AA","A23456@qq.com",0,new Department(101, "教学部")));
        employees.put(1002,new Employee(1002,"BB","B23456@qq.com",1,new Department(101, "市场部")) );
        employees.put(1003,new Employee(1003,"CC","C23456@qq.com",0,new Department(101, "教研部")));
        employees.put(1004,new Employee(1004,"DD","D23456@qq.com",1,new Department(101, "运营部")));
        employees.put(1005,new Employee(1005,"EE","E23456@qq.com",0,new Department(101, "后勤部")) );
    }

    //主键自增
    private static Integer initId = 1006;
    //增加一个员工
    public void add(Employee employee){
        if(employee.getId()==null)
            employee.setId(initId++);
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));  //根据员工部门ID来增添一个部门
        employees.put(employee.getId(), employee);
    }
    //查询全部员工信息
    public Collection<Employee> getAll(){
        return employees.values();
    }
    //通过id查询员工
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }
    //通过id删除员工
    public void delete(Integer id){
        employees.remove(id);
    }


}
