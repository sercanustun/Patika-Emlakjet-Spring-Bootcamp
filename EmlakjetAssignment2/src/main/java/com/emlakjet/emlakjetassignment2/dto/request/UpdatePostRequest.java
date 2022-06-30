package com.emlakjet.emlakjetassignment2.dto.request;

import com.emlakjet.emlakjetassignment2.enumerations.Category;
import lombok.Data;

import java.util.Date;

// We need those datas when we update a  post.
@Data
public class UpdatePostRequest {
    private long id;
    private String updatedBy;
    private String title;
    private String text;
    private Category category;

}
