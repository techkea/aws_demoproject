package dk.clbo.controller;

import dk.clbo.model.Department;
import dk.clbo.repository.DepartmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class DepartmentController {
    private DepartmentRepository departmentRepo = new DepartmentRepository();

    @GetMapping("/departments")
    public String allDepartments(Model objectThatTransportsData) {
        ArrayList<Department> allDepartments = departmentRepo.getAllDepartments();
        objectThatTransportsData.addAttribute("departments", allDepartments);
        return "departments";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("dept", new Department());
        return "create";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute Department dept){

        departmentRepo.create(dept);
        return "redirect:/departments";
    }
}