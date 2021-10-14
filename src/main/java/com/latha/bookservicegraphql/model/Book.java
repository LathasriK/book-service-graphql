package com.latha.bookservicegraphql.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {
@Id
    private String bookId;
    private String title;
//    private String authorName;
    private Integer year;
    private String edition;
    private String language;
    private String extension;
    private Integer pages;
    private String description;
    private String cover;
    private String authorId;
//    private Review review;

}
