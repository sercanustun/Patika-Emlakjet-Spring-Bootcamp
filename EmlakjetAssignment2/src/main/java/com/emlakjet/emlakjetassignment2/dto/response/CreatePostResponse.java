package com.emlakjet.emlakjetassignment2.dto.response;

import com.emlakjet.emlakjetassignment2.enumerations.Category;
import lombok.Data;

import java.util.Date;


// we responsing those datas when we create a post.
@Data
public class CreatePostResponse {
    private long id;
    private String title;
    private String createdBy;
    private String text;
    private Category category;
    private Date createdAt;
}
