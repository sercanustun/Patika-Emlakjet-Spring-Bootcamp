package com.emlakjet.emlakjetassignment2.service;

import com.emlakjet.emlakjetassignment2.dto.request.CreatePostRequest;
import com.emlakjet.emlakjetassignment2.dto.request.UpdatePostRequest;
import com.emlakjet.emlakjetassignment2.dto.response.CreatePostResponse;
import com.emlakjet.emlakjetassignment2.dto.response.UpdatePostResponse;
import com.emlakjet.emlakjetassignment2.entity.Post;
import com.emlakjet.emlakjetassignment2.enumerations.Category;

import java.util.Collection;
import java.util.List;


// Our service layer.
public interface PostService {
    List<Post> findAll();

    Post findById(long theId);

    long create(CreatePostRequest request);


    void deleteById(long theId);


    UpdatePostResponse updatePost(UpdatePostRequest request, Long id);

    Collection<CreatePostResponse> findPostByCreatedBy(String createdBy);


    List<CreatePostResponse> findPostByCategory(Category category);


    Collection<CreatePostResponse> getLastThreePost();
}
