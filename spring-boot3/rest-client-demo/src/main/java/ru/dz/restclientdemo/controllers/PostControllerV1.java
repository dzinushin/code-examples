package ru.dz.restclientdemo.controllers;

import org.springframework.web.bind.annotation.*;
import ru.dz.restclientdemo.domain.Post;
import ru.dz.restclientdemo.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostControllerV1 {

    private final PostService postService;

    public PostControllerV1(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable("id") Integer id) {
        return postService.getPostById(id);
    }

    @PostMapping("")
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable("id") Integer id, @RequestBody Post post) {
        return postService.updatePost(id, post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable("id") Integer id) {
        postService.deletePost(id);
    }
}
