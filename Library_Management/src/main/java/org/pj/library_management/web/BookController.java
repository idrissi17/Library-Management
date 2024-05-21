package org.pj.library_management.web;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.pj.library_management.dao.entities.Author;
import org.pj.library_management.dao.entities.Book;
import org.pj.library_management.dao.entities.Genre;
import org.pj.library_management.dao.repository.AuthorRepository;
import org.pj.library_management.service.AuthorManager;
import org.pj.library_management.service.BookManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@Transactional
public class BookController {
    @Autowired
    private BookManager bookManager;
    @Autowired
    private AuthorManager authorManager;

    @GetMapping("/home")
    public String homePage(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "taille", defaultValue = "8") int size,
                           @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Book> books=bookManager.searchBooks(keyword,page,size);
        model.addAttribute("listBook",books.getContent());
        int[]pages;
        pages = new int[books.getTotalPages()];
        for (int i = 0; i < pages.length; i++) {
            pages[i] = i;
        }
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        return "Home";
    }
    @GetMapping("/user/home")
    public String homePageUser(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "taille", defaultValue = "8") int size,
                           @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Book> books;
        books = bookManager.searchBooks(keyword,page,size);
        Model listBook = model.addAttribute("listBook", books.getContent());
        int[]pages=new int[books.getTotalPages()];
        for (int i = 0; i < pages.length; i++) {
            pages[i] = i;
        }
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        return "Home";
    }

    @GetMapping("/user/detailsBook")
    public String detailsBook(Model model, @RequestParam(name = "id")Integer id){
        Book book=bookManager.getBookById(id);
        model.addAttribute("viewBook",book);
        return "ViewBook";
    }

    @GetMapping("/admin/addBook")
    public String addBookAction(Model model){
        List<Author>authors=authorManager.getAllAuthors();
        model.addAttribute("authors",authors);
        model.addAttribute("addBook",new Book());
        model.addAttribute("genres", Genre.values());
        return "AddBook";
    }

    @PostMapping("/admin/addBookPost")
    public String addBookPostAction(@RequestParam(name="title") String title,
                                    @RequestParam(name="genre") Genre genre,
                                    @RequestParam(name="available") Boolean available,
                                    @RequestParam(name="publicationYear") LocalDate publicationYear,
                                    @RequestParam(name="description") String description,
                                    @RequestParam(name="author") String authorName,
                                    @RequestParam(name="imageFileName") MultipartFile imageFileName) {
        // Retrieve author by full name
        Author author = authorManager.getAuthorByFullName(authorName);

        // Check if author is null
        if (author == null) {
            // Handle case where author is not found
            // You can return an error message or redirect to a page to add the author
            return "redirect:/admin/addAuthorGet";
        }

        // Create a new book
        Book book = new Book();
        book.setTitle(title);
        book.setGenre(genre);
        book.setAvailable(available);
        book.setPublicationYear(publicationYear);
        book.setDescription(description);
        book.setAuthor(author);

        // Handle image file upload
        if (imageFileName != null && !imageFileName.isEmpty()) {
            try {
                // Save the image file
                String fileName = "Upload_" + imageFileName.getOriginalFilename();
                String uploadPath = "static/img/";

                // Ensure the directory exists
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                // Save the image file
                File uploadedFile = new File(uploadPath + fileName);
                imageFileName.transferTo(uploadedFile);

                // Assuming 'book' is an instance of a class that has a method to set the image path
                book.setImageFileName(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle file upload error
            }
        }

        // Add the book to the book manager
        bookManager.addBook(book);

        // Add the book to the author's list of books
        author.getBooks().add(book);
        authorManager.updateAuthor(author);

        // Redirect to home page
        return "redirect:/user/home";
    }
    @GetMapping("/admin/deleteBook")
    public String deleteBook(Model model,@RequestParam(name="id")Integer id ){
        Book book =bookManager.getBookById(id);
        if(bookManager.deleteBook(book)){
            return "redirect:/admin/listBooks";
        }else {
            return "error";
        }
    }
    


}
