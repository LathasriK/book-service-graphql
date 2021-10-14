package com.latha.bookservicegraphql.datafetcher;

import com.latha.bookservicegraphql.model.Book;
import com.latha.bookservicegraphql.model.BookResponse;
import com.latha.bookservicegraphql.repository.BookRepo;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateBookDataFetcher implements DataFetcher<BookResponse> {
    @Autowired
    BookRepo bookService;

    @Override
    public BookResponse get(DataFetchingEnvironment dataFetchingEnvironment) {
        BookResponse response = new BookResponse();
        Book newBok = new Book();
        newBok.setBookId(dataFetchingEnvironment.getArgument("bookId"));
        newBok.setTitle(dataFetchingEnvironment.getArgument("title"));
        newBok.setAuthorId(dataFetchingEnvironment.getArgument("authorId"));
        newBok.setYear(dataFetchingEnvironment.getArgument("year"));
        newBok.setEdition(dataFetchingEnvironment.getArgument("edition"));
        newBok.setLanguage(dataFetchingEnvironment.getArgument("language"));
        newBok.setExtension(dataFetchingEnvironment.getArgument("extension"));
       newBok.setPages(dataFetchingEnvironment.getArgument("pages"));
        newBok.setDescription(dataFetchingEnvironment.getArgument("description"));
        newBok.setCover(dataFetchingEnvironment.getArgument("cover"));

        bookService.save(newBok);
        response.setBook(newBok);

        response.setMessage("book " + newBok.getTitle() + " saved successfully");
        return response;
    }

}
