package com.emlakjet.emlakjetassignment2.service;

import com.emlakjet.emlakjetassignment2.dao.PostRepository;
import com.emlakjet.emlakjetassignment2.dto.request.CreatePostRequest;
import com.emlakjet.emlakjetassignment2.dto.request.UpdatePostRequest;
import com.emlakjet.emlakjetassignment2.dto.response.CreatePostResponse;
import com.emlakjet.emlakjetassignment2.dto.response.UpdatePostResponse;
import com.emlakjet.emlakjetassignment2.entity.Post;
import com.emlakjet.emlakjetassignment2.enumerations.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.*;

// We defined what our services do in this class.

@Service
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;
    private ModelMapper modelMapper;

    public PostServiceImp(PostRepository postRepository) {this.postRepository = postRepository;}


    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(long theId) {
        return postRepository.findById(theId).orElse(null);
    }

    @Override
    public long create(CreatePostRequest request) {
        var dbPost = postRepository.findById(request.getId());
        if (dbPost.isPresent()) {
            dbPost.get().setTitle(request.getTitle());
            dbPost.get().setCategory(request.getCategory());
            dbPost.get().setCreatedBy(request.getCreatedBy());
            dbPost.get().setText(request.getText());
            postRepository.save(dbPost.get());
            return dbPost.get().getId();
        }
        var newPost = Post.builder()
                .title(request.getTitle())
                .category(request.getCategory())
                .createdAt(new Date())
                .createdBy(request.getCreatedBy())
                .text(request.getText())
                .build();
        postRepository.save(newPost);
        return newPost.getId();
    }

    @Override
    public void deleteById(long theId) {
        Post post = postRepository.findById(theId).orElseThrow(IllegalArgumentException::new);
        postRepository.delete(post);
    }

    @Override
    public UpdatePostResponse updatePost(UpdatePostRequest request, Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            post.get().setTitle(request.getTitle());
            post.get().setUpdatedBy(request.getUpdatedBy());
            post.get().setText(request.getText());
            post.get().setCategory(request.getCategory());
            return modelMapper.map(postRepository.save(post.get()), UpdatePostResponse.class);
        }
        return null;
    }


    @Override
    public List<CreatePostResponse> findPostByCreatedBy(String createdBy) {
        return postRepository.findPostByCreatedBy(createdBy).stream()
                .map(post -> modelMapper.map(post, CreatePostResponse.class))
                .toList();
    }

    @Override
    public List<CreatePostResponse> findPostByCategory(Category category) {
        return postRepository.findPostByCategory(category).stream()
                .map(post -> modelMapper.map(post, CreatePostResponse.class))
                .toList();
    }

    @Override
    public Collection<CreatePostResponse> getLastThreePost() {
        return postRepository.findAll()
                .stream()
                .map(post -> modelMapper.map(post, CreatePostResponse.class))
                .sorted(Comparator.comparing(CreatePostResponse::getCreatedAt).reversed())
                .limit(3)
                .toList();

    }


}