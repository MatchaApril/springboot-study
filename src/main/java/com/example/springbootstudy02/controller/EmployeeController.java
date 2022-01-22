package com.example.springbootstudy02.controller;

import com.example.springbootstudy02.dao.DepartmentDao;
import com.example.springbootstudy02.dao.EmployeeDao;
import com.example.springbootstudy02.pojo.Department;
import com.example.springbootstudy02.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddpage(Model model){
        //需要查出所有部门的信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee){
        //添加的操作
        employeeDao.add(employee);    //调用底层业务方法保存员工信息(这方法是在EmployeeDao类里自己写的)
        return "redirect:/emps";   //重定向（重定向是：A->B改为A->C，转发是A->B->C）
    }
}
