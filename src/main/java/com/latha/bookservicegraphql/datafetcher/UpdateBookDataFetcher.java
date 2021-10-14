package com.latha.bookservicegraphql.datafetcher;

import com.latha.bookservicegraphql.model.Book;
import com.latha.bookservicegraphql.model.BookResponse;
import com.latha.bookservicegraphql.repository.BookRepo;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UpdateBookDataFetcher implements DataFetcher<BookResponse> {

    @Autowired
    BookRepo bookService;

    @Override
    public BookResponse get(DataFetchingEnvironment dataFetchingEnvironment) {
        BookResponse response = new BookResponse();
        String bookId =dataFetchingEnvironment.getArgument("bookId");
        Optional<Book> existingRecord = bookService.findById(bookId);

        if(existingRecord.isPresent()){

          Book existingBook = existingRecord.get();

            String title =dataFetchingEnvironment.getArgument("title");
            String authorId=dataFetchingEnvironment.getArgument("authorId");
            Integer year =dataFetchingEnvironment.getArgument("year");
            String edition =dataFetchingEnvironment.getArgument("edition");
            String language =dataFetchingEnvironment.getArgument("language");
            String extension =dataFetchingEnvironment.getArgument("extension");
            Integer pages =dataFetchingEnvironment.getArgument("pages");
            String description =dataFetchingEnvironment.getArgument("description");
            String cover =dataFetchingEnvironment.getArgument("cover");





            if(title!=null){
                existingBook.setTitle(title);}
//            if(authorName!=null){existingBook.setAuthorName(authorName);}
            existingBook.setYear(year);
            existingBook.setEdition(edition);
            existingBook.setLanguage(language);
            existingBook.setExtension(extension);
            existingBook.setPages(pages);
            existingBook.setDescription(description);
            existingBook.setCover(cover);

            bookService.save(existingBook);
            response.setBook(existingBook);
            response.setMessage("Book updated successfully!!");

        }
            else {
                response.setMessage("Couldn't find book with book Id "+bookId);

        }
return response;
        }


}
