package com.example.blog_app;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {
    public final JdbcClient jdbcClient;

    public BlogRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }


    public List<Blog> findAllBlogs() {
        return jdbcClient.sql("SELECT * FROM articles ORDER BY postTime DESC") // ⭕ ここに追記！
                .query(Blog.class)
                .list();
    }

    public List<Blog> searchByTitle(String keyword) {
        return jdbcClient.sql("SELECT * FROM articles WHERE title LIKE :keyword ORDER BY postTime DESC") // ⭕ ここに追記！
                .param("keyword", "%" + keyword + "%")
                .query(Blog.class)
                .list();
    }

    public Optional<Blog> findById(Long id) {
        return jdbcClient.sql("SELECT id, title, article, postTime FROM articles WHERE id = :id")
                .param("id", id)
                .query(Blog.class)
                .optional();
    }

    public void save(Blog blog) {
        jdbcClient.sql("INSERT INTO articles (title, article, postTime) VALUES (:title, :article, :postTime)")
                .param("title", blog.getTitle())
                .param("article", blog.getArticle())
                .param("postTime", blog.getPostTime())
                .update();
    }

    public void save(String title, String article) {
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        jdbcClient.sql("INSERT INTO articles (title, article, postTime) VALUES (:title, :article, :postTime)")
                .param("title", title)
                .param("article", article)
                .param("postTime", now)
                .update();
    }

}
