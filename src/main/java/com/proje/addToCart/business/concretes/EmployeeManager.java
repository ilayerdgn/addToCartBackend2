package com.proje.addToCart.business.concretes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.proje.addToCart.business.abstracts.EmployeeService;
import com.proje.addToCart.dataAccess.abstracts.EmployeeDao;
import com.proje.addToCart.entities.concretes.Employee;
import com.proje.addToCart.entities.concretes.Order;

@Service
public class EmployeeManager implements EmployeeService{
	
	@Autowired
    private EntityManager entityManager;

    
	private EmployeeDao employeeDao;
	
	@Autowired
	public EmployeeManager(EmployeeDao employeeDao) {
		super();
		this.employeeDao = employeeDao;
	}

	@Override
	public List<Employee> getAll() {
		return employeeDao.findAll();
	}

	@Override
	public void add(Employee employee) {
		Employee _employee=new Employee();
		_employee.setName(employee.getName());
		_employee.setLastName(employee.getLastName());
		_employee.setTitle(employee.getTitle());
		_employee.setEmail(employee.getEmail());
		_employee.setPass(employee.getPass());
		_employee.setHireDate(employee.getHireDate());
		
		employeeDao.save(employee);
		
	}

	@Override
	public Employee update(Employee employee) {
		Integer rollNumber=employee.getEmployeeId();
		Employee _employee=employeeDao.findById(rollNumber).get();
		_employee.setName(employee.getName());
		_employee.setLastName(employee.getLastName());
		_employee.setTitle(employee.getTitle());
		_employee.setEmail(employee.getEmail());
		_employee.setPass(employee.getPass());
		_employee.setHireDate(employee.getHireDate());
		
		return employeeDao.save(employee);
		
	}

	@Override
	public void delete(int id) {
		employeeDao.deleteById(id);
		
	}

	@Override
	public Employee getById(int id) {
		return employeeDao.getOne(id);
	}

	@Override
	public List<Employee> login(String name, String password) {

	Query query = entityManager.createQuery("from Employee where name =:n and pass =:p");
	query.setParameter("n",name);
	query.setParameter("p", password);
	ArrayList<Employee> resultList=(ArrayList<Employee>) query.getResultList();
	List<Employee> employees=resultList;
	return employees;
	
	
	
	}

}
