package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> OptionalStudent= studentRepository.findStudentByEmail(student.getEmail());
        if (OptionalStudent.isPresent()){
            throw new IllegalStateException("email is already used");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)){
            throw new IllegalStateException("student with ID: "+studentId+"does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student=studentRepository.findById(studentId)
                .orElseThrow(()-> new IllegalStateException("student with id " + studentId + "does not exists"));
        if (name!=null && !name.isEmpty() && !Objects.equals(name, student.getName())){
            student.setName(name);
        }
        if (email!=null && !email.isEmpty() && !Objects.equals(email, student.getEmail())){
            Optional<Student> studentOptional=studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("email is already taken");
            }
            student.setEmail(email);
        }
    }
}
