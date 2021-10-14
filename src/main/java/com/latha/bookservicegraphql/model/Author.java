package com.latha.bookservicegraphql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Author {
@Id
    private String authorId;
    private String name;
    private Integer age;
    private String streetName;
    private String streetNumber;
    private Integer pincode;
//    private String bookId;

    }


