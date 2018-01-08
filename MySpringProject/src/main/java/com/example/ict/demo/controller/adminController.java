package com.example.ict.demo.controller;
import com.example.ict.demo.dao.StudentRepository;
import com.example.ict.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;


@Controller
public class adminController {

    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        System.out.println("---This  is home page---");
        List<Student> studentlist = studentRepository.findAll();
        model.addAttribute("studentList", studentlist);
        return "index";
    }

  /* @RequestMapping(value = "/upsertStudent", method = RequestMethod.GET)
    public String studentView(Model model) {
        System.out.println("----------StudentView---------");
        Student student = new Student();
        model.addAttribute("student", student);
        return "student";
    }
    @RequestMapping(value = "/upsertStudent", method = RequestMethod.POST)
    public String studentPost(Model model, @ModelAttribute("student") Student student, HttpServletRequest request) {
        System.out.println("-----------studentPost-------------");
        System.out.println(student);
        try{
            studentRepository.save(student);
            request.setAttribute("message", "Student saved successfully");
        }catch(Exception e){
            request.setAttribute("message", "Faild to save Student");
        }
        return "redirect:./";
    }*/

    @RequestMapping(value = "/upsertStudent", method = RequestMethod.GET)
    public String studentEdit(Model model, @RequestParam(value ="studentId", required=false) Integer studentId) {
        System.out.println("----------StudentEdit---------");
        if(studentId== null){
            Student student = new Student();
            model.addAttribute("student", student);
        }
        else {
            Student student = studentRepository.getStudentById(studentId);
            model.addAttribute("student", student);
        }
        return "student";
    }

    @RequestMapping(value = "/upsertStudent", method = RequestMethod.POST)
    public String studentUpdatePost(Model model, @ModelAttribute("student") Student student, HttpServletRequest request) {
        System.out.println("-----------studentPost-------------");
        System.out.println(student);
        try{
            studentRepository.save(student);
            request.setAttribute("message", "Edited successfully");
        }catch(Exception e){
            request.setAttribute("message", "Faild to save Edit");
        }
        return "redirect:./";
    }

    @RequestMapping(value = "/deleteStudent", method = RequestMethod.GET)
    public String deleteStudent(Model model, @RequestParam(value="studentId", required=false) Integer studentId) {
        System.out.println("-----------delete-------------studentId:"+studentId);
        Student student = studentRepository.getStudentById(studentId);
        studentRepository.delete(student);
        return "redirect:./";
    }

    /*@RequestMapping(value = "/upsertStudent", method = RequestMethod.POST)
    public String studentPost(Model model, @ModelAttribute("student") Student student){
        System.out.println("-----------StudentPost-------------");
        System.out.println(student);
        studentRepository.save(student);
        return "redirect:./";
    }*/
}