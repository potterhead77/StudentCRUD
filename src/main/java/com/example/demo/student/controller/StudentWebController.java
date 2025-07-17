package com.example.demo.student.controller;

import com.example.demo.student.model.Student;
import com.example.demo.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class StudentWebController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public String getStudentsPage(Model model) {
        List<Student> students = studentService.getStudents();
        model.addAttribute("students", students);
        return "students";
    }
    @PostMapping("/students/add")
    public String addStudent(@RequestParam String name, @RequestParam String email) {
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        studentService.addNewStudent(student);
        return "redirect:/students";
    }

    @PostMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "form";
    }


    @PostMapping("/students/update")
    public String updateStudent(@RequestParam Long id,
                                @RequestParam String name,
                                @RequestParam String email) {
        studentService.updateStudent(id, name, email);
        return "redirect:/students";
    }
}
