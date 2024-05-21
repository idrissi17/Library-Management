package org.pj.library_management.web;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.pj.library_management.dao.entities.Author;
import org.pj.library_management.dao.entities.Book;
import org.pj.library_management.service.AuthorManager;
import org.pj.library_management.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Transactional
public class AuthorController {

    @Autowired
    private AuthorManager authorManager;
    @GetMapping("/user/allAuthors")
    public String authorsList(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "pageSize", defaultValue = "6") int pageSize,
                              @RequestParam(name = "search", defaultValue = "") String keyword){
        Page<Author>authors=authorManager.searchAuthors(keyword,page,pageSize);
        model.addAttribute("listAuthors",authors.getContent());
        int[] authorPages = new int[authors.getTotalPages()];
        for (int i = 0; i < authorPages.length; i++) {
            authorPages[i]=i;
        }
        model.addAttribute("pages", authorPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "AllAuthors";
    }

    @GetMapping("/user/viewAuthor")
    public String viewAuthor(Model model, @RequestParam(name = "id")Integer id){
        Author author=authorManager.getAuthorById(id);
        model.addAttribute("viewAuthor",author);
        return "ViewAuthor";
    }


    @GetMapping("/admin/editAuthor")
    public String editAuthor(Model model,@RequestParam(name = "id")Integer id){
        Author author=authorManager.getAuthorById(id);
        if(author!=null){
            model.addAttribute("authorUpdate",author);
            return "EditAuthor";
        }else {
            return "error";
        }
    }
    @PostMapping("/admin/updateAuthor")
    public String updateAuthor(Model model,@RequestParam(name = "id")Integer id,
                               @RequestParam(name = "fullName") String fullName,
                               @RequestParam(name = "email") String email,
                               @RequestParam(name = "address") String address,
                               @RequestParam(name = "phoneNumber") String phoneNumber,
                               @RequestParam(name = "age") Integer age){
        Author author = authorManager.getAuthorById(id);
        author.setFullName(fullName);
        author.setEmail(email);
        author.setAddress(address);
        author.setPhoneNumber(phoneNumber);
        author.setAge(age);
        authorManager.updateAuthor(author);
        return"redirect:/user/allAuthors";

    }

    @GetMapping("/admin/addAuthor")
    public String addAuthor(Model model){
        model.addAttribute("addAuthor",new Author());
        return "AddAuthor";
    }
    @PostMapping("/admin/addAuthor")
    public String addAuthorAction(Model model, @Valid Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "AddAuthor";
        }
        authorManager.addAuthor(author);
        return "redirect:/user/allAuthors";
    }

    @GetMapping("/admin/deleteAuthor")
    public String deleteAuthor(Model model,@RequestParam(name="id")Integer id ){
        if(authorManager.deleteAuthorById(id)){
            return "redirect:/user/allAuthors";
        }else {
            return "error";
        }
    }
}
