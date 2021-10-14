package com.latha.bookservicegraphql.datafetcher1;

import com.latha.bookservicegraphql.model.Author;
import com.latha.bookservicegraphql.model.Book;
import com.latha.bookservicegraphql.repository.AuthorRepo;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorDataFetcher implements DataFetcher<Author> {
    @Autowired
    AuthorRepo authorRepo;


    @Override
    public Author get(DataFetchingEnvironment dataFetchingEnvironment) {

        String authorId = dataFetchingEnvironment.getArgument("authorId");
        Optional<Author> author;
        if (authorId == null ) {
            Book book = dataFetchingEnvironment.getSource();
            if (book != null) {
                author = authorRepo.findById(book.getAuthorId());
            } else {
                return null;
            }
        } else {
            author = authorRepo.findById(authorId);
        }
        return author.get();
    }
}



