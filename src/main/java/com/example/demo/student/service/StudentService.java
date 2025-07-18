package com.example.demo.student.service;

import com.example.demo.student.repository.StudentRepository;
import com.example.demo.student.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }

        studentRepository.save(student);
    }
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Student ID not found: " + id));
    }


    public void deleteStudent(Long Studentid) {
        boolean exists = studentRepository.existsById(Studentid);
        if(!exists){
            throw new IllegalStateException(
                    "Student with id" + Studentid + "does not exist"
            );
        }
        studentRepository.deleteById(Studentid);
    }
    @Transactional
    public void updateStudent(Long Studentid,
                              String name,
                              String email){
        Student student = studentRepository.findById(Studentid)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id" + Studentid + "does not exist"
                ));
        if(name != null && name.length()>0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }
        if(email != null && email.length()>0 && !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}

