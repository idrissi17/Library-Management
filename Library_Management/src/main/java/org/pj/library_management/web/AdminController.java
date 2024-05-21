package org.pj.library_management.web;


import org.pj.library_management.dao.entities.Author;
import org.pj.library_management.dao.entities.Book;
import org.pj.library_management.dao.entities.Student;
import org.pj.library_management.service.AuthorManager;
import org.pj.library_management.service.BookManager;
import org.pj.library_management.service.StudentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AuthorManager authorManager;
    @Autowired
    private StudentManager studentManager;
    @Autowired
    private BookManager bookManager;

    @GetMapping("/admin/home")
    public String getHome() {
        return "HomeAdmin";
    }
    @GetMapping("/admin/allAuthors")
    public String authorsList(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "pageSize", defaultValue = "6") int pageSize,
                              @RequestParam(name = "search", defaultValue = "") String keyword){
        Page<Author> authors=authorManager.searchAuthors(keyword,page,pageSize);
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
    @GetMapping("/admin/listBooks")
    public String listBooks(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "pageSize", defaultValue = "6") int pageSize,
                            @RequestParam(name = "search", defaultValue = "") String keyword){
        Page<Book>books =bookManager.searchBooks(keyword,page,pageSize);
        model.addAttribute("listBooks",books.getContent());
        int[] bookPages=new int[books.getTotalPages()];
        for (int i = 0; i < bookPages.length; i++) {
            bookPages[i]=i;
        }
        model.addAttribute("pages", bookPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);

        return "BooksList";
    }
    @GetMapping("/admin/viewBook")
    public String detailsBook(Model model, @RequestParam(name = "id")Integer id){
        Book book=bookManager.getBookById(id);
        model.addAttribute("viewBook",book);
        return "ViewBookAdmin";
    }


}
