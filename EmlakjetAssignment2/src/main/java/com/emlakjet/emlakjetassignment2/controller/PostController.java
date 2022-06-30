package com.emlakjet.emlakjetassignment2.controller;

import com.emlakjet.emlakjetassignment2.dto.response.CreatePostResponse;
import com.emlakjet.emlakjetassignment2.dto.response.UpdatePostResponse;
import com.emlakjet.emlakjetassignment2.entity.Post;


import com.emlakjet.emlakjetassignment2.enumerations.Category;
import com.emlakjet.emlakjetassignment2.service.PostService;
import com.emlakjet.emlakjetassignment2.dto.request.CreatePostRequest;
import com.emlakjet.emlakjetassignment2.dto.request.UpdatePostRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;


// Our Controller class that we define for endpoints

@RestController
@RequestMapping("/api/posts")
public class PostController {

    // creating service object from service class.
    private final PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }


    //Find all posts endpoint.We use Jpa repository for this endpoint
    @GetMapping
    public List<Post> findAll() {return postService.findAll();}


    //Find by id endpoint.
    @GetMapping("/{postId}")
    public ResponseEntity getPosts(@PathVariable long postId) {
        Post post = postService.findById(postId);
        if (post == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(post);
    }

    //Create post endpoint.
    @PostMapping
    public long createPost(@RequestBody CreatePostRequest request) {
        return postService.create(request);
    }

    //Delete post endpoint
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable("id") long id) {
        postService.deleteById(id);
    }

    //Update post endpoint
    @PutMapping(value = "{id}")
    public UpdatePostResponse updatePost(@PathVariable Long id, @RequestBody UpdatePostRequest postRequest) {
        return postService.updatePost(postRequest, id);
    }

    //Get posts created by some one endpoint
    @GetMapping("/createdBy")
    public Collection<CreatePostResponse> findPostByCreatedBy(@RequestParam(value = "createdBy") String createdBy) {
        return postService.findPostByCreatedBy(createdBy);
    }

    // Get posts from selected endpoint
    @GetMapping("/category")
    public Collection<CreatePostResponse> findPostByCategory(@RequestParam(name = "category") Category category) {
        return postService.findPostByCategory(category);
    }


    // Get last 3 posts endpoint
    @GetMapping("/getLastThreePost")
    public Collection<CreatePostResponse> getLastThreePost() {
        return postService.getLastThreePost();

    }
}