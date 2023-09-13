package ru.dz.restclientdemo.client;

import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;
import ru.dz.restclientdemo.domain.Post;

import java.util.List;

public interface JsonPlaceholderService {

    @GetExchange("/posts")
    List<Post> getAllPosts();

    @GetExchange("/posts/{id}")
    Post getPostById(Integer id);

    @PostExchange("/posts")
    Post createPost(Post post);

    @PutExchange("/posts/{id}")
    Post updatePost(Integer id, Post post);

    @DeleteExchange("/posts/{id}")
    void deletePost(Integer id);
}
