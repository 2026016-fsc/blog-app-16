package com.example.blog_app;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/")
    public String blogs(Model model) {
        model.addAttribute("blogs", blogService.findAllBlogs());
        return "blogsHome";
    }

    @GetMapping("/blogs/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (blogOptional.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("blog", blogOptional.get());
        return "blogs/detail";
    }

    @GetMapping("/blogsHome")
    public String search(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("blogs", blogService.search(keyword));
        return "/blogsHome";
    }

    @GetMapping("/blogs/new")
    public String newBlog(HttpSession session, Model model) {
        String savedName = (String) session.getAttribute("authorName");

        if (savedName != null) {
            model.addAttribute("defaultName", savedName);
        } else {
            model.addAttribute("defaultName", "名無しさん");
        }

        return "blogs/new";
    }

    @PostMapping("/blogs/create")
    public String createBlog(
            @RequestParam String name,
            @RequestParam String title,
            @RequestParam String article,
            HttpSession session) {

        session.setAttribute("authorName", name);

        String titleWithString = title + " ＠" + name;

        blogService.save(titleWithString, article);

        return "redirect:/";
    }
    
    @GetMapping("/Nullpo")
    public String nullpo() {
        return "Nullpo";
    }

}