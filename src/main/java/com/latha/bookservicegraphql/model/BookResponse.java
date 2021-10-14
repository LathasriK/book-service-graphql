package com.latha.bookservicegraphql.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {


    public String message;
    public Book book;


}
