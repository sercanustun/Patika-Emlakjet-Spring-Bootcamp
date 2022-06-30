package com.emlakjet.emlakjetassignment2.entity;

import com.emlakjet.emlakjetassignment2.enumerations.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;


//Our entity class. Posts have those datas.
@Data

// we are saying this is my entity class to spring
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
// we can define table name with this annotation but not needed.
@Table(name = "posts")
public class Post {

    // we are saying this is my id and it should generate automaticly  by spring
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @CreatedDate
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;
    private String text;
    private Category category;
}
