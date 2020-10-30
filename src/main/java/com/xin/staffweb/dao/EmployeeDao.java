package com.xin.staffweb.dao;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.xin.staffweb.pojo.Department;
import com.xin.staffweb.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {

    // 模拟数据库中的数据
    private static Map<Integer,Employee> employees = null;

    // 员工所属的部门
    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<Integer, Employee>(); // 创建一个员工表

        employees.put(1001, new Employee(1001,"AA","132654@163.com",1,new Department(101,"教学部")));
        employees.put(1002, new Employee(1002,"BB","14564@163.com",0,new Department(101,"运营部")));
        employees.put(1003, new Employee(1003,"CC","187964@163.com",1,new Department(101,"后勤处")));
        employees.put(1004, new Employee(1004,"DD","11526344@163.com",0,new Department(101,"教研部")));
        employees.put(1005, new Employee(1005,"EE","1784554@163.com",1,new Department(101,"政教处")));

    }

    // 主键 自增
    public static Integer initId = 1006;

    // 增加员工

    public void save(Employee employee){

        if (employee.getId() == null){

            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));

        employees.put(employee.getId(),employee);
    }

    // 查询员工全部信息
    public Collection<Employee> getAll(){
        return employees.values();
    }

    // 通过id查询员工
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }


    // 通过删除员工
    public void delete(Integer id){
        employees.remove(id);
    }
}
