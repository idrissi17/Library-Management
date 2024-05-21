package org.pj.library_management.web;


import org.pj.library_management.dao.entities.Person;
import org.pj.library_management.service.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private PersonManager personManager;

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }
    @PostMapping("/loginPost")
    public String loginPost(Model model, @RequestParam(name = "username")String username
            ,@RequestParam(name = "password")String password){
        Person person = personManager.loginPerson(username,password);
        if(person !=null){
            model.addAttribute("Customer", person);
            System.out.println(person.getUsername());
            return "Home";
        }else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }


}
