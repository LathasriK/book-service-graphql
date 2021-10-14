package com.latha.bookservicegraphql.datafetcher2;

import com.latha.bookservicegraphql.model.Book;
import com.latha.bookservicegraphql.model.Review;
import com.latha.bookservicegraphql.repository.ReviewRepo;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class ReviewDataFetcher implements DataFetcher<List<Review>> {
    @Autowired
    ReviewRepo reviewRepo;


    @Override
    public List<Review> get(DataFetchingEnvironment dataFetchingEnvironment) {
        String bookId = dataFetchingEnvironment.getArgument("bookId");
        if (bookId == null ) {
            Book book = dataFetchingEnvironment.getSource();
            if (book != null) {
                return reviewRepo.findAllByBookId(book.getBookId());
            } else {
                return (List<Review>) reviewRepo.findAll();
            }
        } else {
            return reviewRepo.findAllByBookId(bookId);
        }
    }
}


