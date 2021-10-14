package com.latha.bookservicegraphql.repository;

import com.latha.bookservicegraphql.model.Book;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepo extends CrudRepository<Book, String> {

    @NotNull
    List<Book> findAll();
}
