package dk.clbo.controller;

import dk.clbo.model.Department;
import dk.clbo.repository.DepartmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}