package com.example.blog_app;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> search(String keyword){
        if(keyword == null || keyword.isBlank()){
            return blogRepository.findAllBlogs();
        }
        return blogRepository.searchByTitle(keyword);
    }

    public List<Blog> findAllBlogs(){
        return blogRepository.findAllBlogs();
    }

    public Optional<Blog> findById(Long id){
        return blogRepository.findById(id);
    }

    public void save(String title, String article) {
        blogRepository.save(title, article);
    }
}
