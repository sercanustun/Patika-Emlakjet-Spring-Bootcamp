package com.emlakjet.emlakjetassignment2.dto.response;

import com.emlakjet.emlakjetassignment2.enumerations.Category;
import lombok.Data;


import java.util.Date;

// we responsing those datas when we update a post.
@Data
public class UpdatePostResponse {
    private long id;
    private String updatedBy;
    private String title;
    private String text;
    private Category category;
    private Date updatedAt;
}
