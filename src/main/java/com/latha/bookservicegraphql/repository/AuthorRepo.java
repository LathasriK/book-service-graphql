package com.latha.bookservicegraphql.repository;

import com.latha.bookservicegraphql.model.Author;
import com.latha.bookservicegraphql.model.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.jar.Attributes;

public interface AuthorRepo extends CrudRepository<Author, String> {


    Optional<Author> findById(String authorId);
}
