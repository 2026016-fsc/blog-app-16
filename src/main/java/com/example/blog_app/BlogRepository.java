package com.example.blog_app;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {
    public final JdbcClient jdbcClient;

    public BlogRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Blog> findAllBlogs(){
        return jdbcClient.sql("SELECT title, article, postTime FROM articles")
                .query(Blog.class)
                .list();
    }

    public List<Blog> searchByTitle(String keyword) {
        return jdbcClient.sql("SELECT title, article, postTime FROM articles WHERE title LIKE :keyword")
                .param("keyword", "%" + keyword + "%")
                .query(Blog.class)
                .list();
    }
    


}
