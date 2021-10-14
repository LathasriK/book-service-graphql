package com.latha.bookservicegraphql.datafetcher;

import com.latha.bookservicegraphql.model.Book;
import com.latha.bookservicegraphql.repository.BookRepo;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AllBookDataFetcher implements DataFetcher<List<Book>> {


    @Autowired
    public BookRepo bookService;

    @Override
    public List<Book> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return (List<Book>) bookService.findAll();
    }
}
