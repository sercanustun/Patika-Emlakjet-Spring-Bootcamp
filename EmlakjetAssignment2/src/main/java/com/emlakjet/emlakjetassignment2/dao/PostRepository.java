package com.emlakjet.emlakjetassignment2.dao;

import com.emlakjet.emlakjetassignment2.entity.Post;
import com.emlakjet.emlakjetassignment2.enumerations.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


// Our repository we extend JpaRepository because we can use ready to use objects
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    // we created a query because JpaRepository doesnt have theese.
    List<Post> findPostByCreatedBy(String createdBy);

    List<Post> findPostByCategory(Category category);


}
