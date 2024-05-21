package org.pj.library_management.web;


import jakarta.transaction.Transactional;
import org.pj.library_management.dao.entities.Admin;
import org.pj.library_management.dao.entities.Student;
import org.pj.library_management.service.AdminManager;
import org.pj.library_management.service.StudentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Transactional
public class StudentController {
    @Autowired
    private StudentManager studentManager;
    @Autowired
    private AdminManager adminManager;

    @GetMapping("/admin/allStudents")
    String allStudents(Model model,
                       @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "pageSize", defaultValue = "6") int pageSize,
                       @RequestParam(name = "search", defaultValue = "") String keyword){
        Page<Student>students;
        students = studentManager.searchStudent(keyword, page, pageSize);
        model.addAttribute("studentsList",students.getContent());
        int[]studentPages=new int[students.getTotalPages()];
        for (int i = 0; i <studentPages.length ; i++) {
            studentPages[i]=i;
        }
        model.addAttribute("pages", studentPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "AllStudents";
    }
    @GetMapping("/admin/viewStudent")
    public String viewStudent(Model model, @RequestParam(name = "id")Integer id){
        Student student=studentManager.getStudentById(id);
        model.addAttribute("viewStudent",student);
        return "ViewStudent";
    }

    @GetMapping("/admin/editStudent")
    public String editStudent(Model model,@RequestParam(name = "id")Integer id){
        Student student =studentManager.getStudentById(id);
        if(student!=null){
            model.addAttribute("studentUpdate",student);
            return "EditStudentAdmin";
        }else {
           return "error";
        }

    }
    @GetMapping("/user/editProfile")
    public String editProfile(Model model,@RequestParam(name = "id")Integer id){
        Student student =studentManager.getStudentById(id);
        if(student!=null){
            model.addAttribute("profileUpdate",student);
            return "EditStudent";
        }else {
            return "error";
        }

    }
    @GetMapping("/user/profileStudent")
    public String StudentProfile(Model model,@RequestParam(name = "username")String username){
        Student student =studentManager.getStudentByUsername(username);
        if(student!=null){
            model.addAttribute("profileStudent",student);
            return "ProfileStudent";
        }else {
            return "error";
        }

    }
    @GetMapping("/admin/addStudent")
    public String addStudent(Model model){
        model.addAttribute("addStudent",new Student());
        return "AddStudent";
    }
    @PostMapping("/admin/addStudent")
    public String addStudentAction(Model model, @Valid Student student, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "redirect:/admin/addStudent";
        }
        studentManager.addStudent(student);
        return "redirect:/admin/allStudents";
    }
    @PostMapping("/admin/updateStudent")
    public String updateStudent(Model model,@RequestParam(name = "id")Integer id,
                                   @RequestParam(name = "fullName") String fullName,
                                   @RequestParam(name = "email") String email,
                                   @RequestParam(name = "address") String address,
                                   @RequestParam(name = "school") String school,
                                   @RequestParam(name = "yearLevel") String yearLevel,
                                   @RequestParam(name = "phoneNumber") String phoneNumber,
                                   @RequestParam(name = "age") Integer age) {
        Student student = studentManager.getStudentById(id);
        student.setFullName(fullName);
        student.setEmail(email);
        student.setAddress(address);
        student.setSchool(school);
        student.setYearLevel(yearLevel);
        student.setPhoneNumber(phoneNumber);
        student.setAge(age);
        studentManager.updateStudent(student);
        return "redirect:/admin/allStudents";
    }
    @PostMapping("/user/updateProfile")
    public String updateProfile(Model model,@RequestParam(name = "id")Integer id,
                                @RequestParam(name = "fullName") String fullName,
                                @RequestParam(name = "email") String email,
                                @RequestParam(name = "address") String address,
                                @RequestParam(name = "school") String school,
                                @RequestParam(name = "yearLevel") String yearLevel,
                                @RequestParam(name = "phoneNumber") String phoneNumber,
                                @RequestParam(name = "age") Integer age) {
        Student student;
        student = studentManager.getStudentById(id);
        student.setFullName(fullName);
        student.setEmail(email);
        student.setAddress(address);
        student.setSchool(school);
        student.setYearLevel(yearLevel);
        student.setPhoneNumber(phoneNumber);
        student.setAge(age);
        studentManager.updateStudent(student);
        return "redirect:/user/home";
    }
    @GetMapping("/admin/deleteStudent")
    public String deleteStudent(Model model,@RequestParam(name="id")Integer id ){
        if(studentManager.deleteStudentById(id)){
            return "redirect:/admin/allStudents";
        }else {
            return "error";
        }
    }



}
