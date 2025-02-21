package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Employee;
import com.example.demo.repos.EmpDao;

@Service
public class EmployeeService {

    @Autowired
    private EmpDao dao;

    public Iterable<Employee> getEmployees() {
        return dao.findAll();
    }

    public Optional<Employee> getEmployeeById(int id) {
        return dao.findById(id);
    }

    public String insertEmployee(Employee e) {
        if (dao.existsById(e.getEid())) {
            return "Sorry, the employee already exists. Choose another ID.";
        }
        dao.save(e);
        return "Added new employee successfully!";
    }

    public String updateEmployee(Employee e, int id) {
        if (!dao.existsById(id)) {
            return "Sorry, the employee does not exist. Choose another ID to update.";
        }
        dao.save(e);
        return "Updated record successfully.";
    }

    public String deleteEmployee(int id) {
        if (!dao.existsById(id)) {
            return "Sorry, the employee does not exist. Choose another ID to delete.";
        }
        dao.deleteById(id);
        return "Successfully deleted employee record!";
    }

    public List<Employee> getCustomQuery(String desig) {
        return dao.myCustomQuery(desig);
    }

    public List<Employee> getEmployeeBelow(int age) {
        return dao.findByAgeLessThan(age);
    }

    public List<Employee> getEmployeeAbove(int age) {
        return dao.findByAgeGreaterThan(age);
    }

    public List<Employee> getEmployeeByDesig(String desig) {
        return dao.getEmployeeByDesignation(desig);
    }
}
