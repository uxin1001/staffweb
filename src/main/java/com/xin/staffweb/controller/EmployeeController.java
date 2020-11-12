package com.xin.staffweb.controller;

import com.xin.staffweb.dao.DepartmentDao;
import com.xin.staffweb.dao.EmployeeDao;
import com.xin.staffweb.pojo.Department;
import com.xin.staffweb.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        // 查出所有部门信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println("save=>"+employee);
        employeeDao.save(employee);//调用底层业务方法保存员工信息

        return "redirect:/emps";
    }

    // 跳转到修改员工界面
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id")Integer id, Model model){
        //查出原来数据
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("emp",employee);


        // 查出所有部门信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/update";
    }

    //保存修改信息
    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee){

        employeeDao.save(employee);
        return "redirect:/emps";
    }
    //删除员工信息
    @GetMapping("/delemp/{id}")
    public String delelteEmp(@PathVariable("id")Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
