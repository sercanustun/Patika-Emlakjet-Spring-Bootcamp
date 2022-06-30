package com.emlakjet.emlakjetassignment2.dto.request;

import com.emlakjet.emlakjetassignment2.enumerations.Category;
import lombok.Data;


// We need those datas when we create a new post.
@Data
public class CreatePostRequest {
    private long id;
    private String title;
    private String createdBy;
    private String text;
    private Category category;
}
