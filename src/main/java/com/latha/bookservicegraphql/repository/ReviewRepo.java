package com.latha.bookservicegraphql.repository;

import com.latha.bookservicegraphql.model.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepo extends CrudRepository<Review,String> {
    public List<Review> findAllByBookId(String bookId);

}
