/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.luv2code.springboot.cruddemo.contoller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;


/**
 *
 * @author ragha
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {
    
    private EmployeeService employeeService;
    
    public EmployeeController(EmployeeService theEmployeeService){
        employeeService=theEmployeeService;
    }
     
     @GetMapping("/list")
     public String listEmployees(Model themodel){
         

         List<Employee>  theEmployees = employeeService.findAll();

         themodel.addAttribute("employees",theEmployees);
         return "list-employees";
     }
     @GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Employee theEmployee = new Employee();

		theModel.addAttribute("employee", theEmployee);

		return "employee-form";
	}

    @GetMapping("/showFormForUpdate")
    public String showFormFORUpdate(@RequestParam("employeeId") int theId,Model theModel){
        
       Employee theEmployee=employeeService.findById(theId);

       theModel.addAttribute("employee",theEmployee);

       

        return "employee-form";
    }

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

		// save the employee
		employeeService.save(theEmployee);

		// use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}

    @GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") int theId) {

		// delete the employee
		employeeService.deleteById(theId);

		// redirect to /employees/list
		return "redirect:/employees/list";

	}
}

