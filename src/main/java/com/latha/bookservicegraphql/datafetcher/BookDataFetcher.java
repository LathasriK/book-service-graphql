package com.latha.bookservicegraphql.datafetcher;

import com.latha.bookservicegraphql.model.Book;
import com.latha.bookservicegraphql.repository.BookRepo;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class BookDataFetcher implements DataFetcher<Optional<Book>> {

    @Autowired
    public BookRepo bookService;

    @Override
    public Optional<Book> get(DataFetchingEnvironment dataFetchingEnvironment) {

        String bookId = dataFetchingEnvironment.getArgument("bookId");
        return bookService.findById(bookId);
    }
}
