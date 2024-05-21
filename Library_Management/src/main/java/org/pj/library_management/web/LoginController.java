package org.pj.library_management.web;


import org.pj.library_management.dao.entities.Customer;
import org.pj.library_management.service.CustomUserDetailsService;
import org.pj.library_management.service.CustomerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private CustomerManager customerManager;

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }
    @PostMapping("/loginPost")
    public String loginPost(Model model, @RequestParam(name = "username")String username
            ,@RequestParam(name = "password")String password){
        Customer customer = customerManager.loginCustomer(username,password);
        if(customer!=null){
            model.addAttribute("Customer",customer);
            System.out.println(customer.getUsername());
            return "Home";
        }else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }


}
