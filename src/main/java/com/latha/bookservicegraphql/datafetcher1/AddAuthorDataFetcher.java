package com.latha.bookservicegraphql.datafetcher1;

import com.latha.bookservicegraphql.model.Author;
import com.latha.bookservicegraphql.repository.AuthorRepo;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class AddAuthorDataFetcher implements DataFetcher<String> {

    @Autowired
    AuthorRepo authorRepo;

    @Override
    public String get(DataFetchingEnvironment dataFetchingEnvironment) {
        Author newAuthor = new Author();
        newAuthor.setName(dataFetchingEnvironment.getArgument("name"));
        newAuthor.setAuthorId(dataFetchingEnvironment.getArgument("authorId"));
        newAuthor.setAge(dataFetchingEnvironment.getArgument("age"));
        newAuthor.setPincode(dataFetchingEnvironment.getArgument("pincode"));
        newAuthor.setStreetName(dataFetchingEnvironment.getArgument("streetName"));
        newAuthor.setStreetNumber(dataFetchingEnvironment.getArgument("streetNumber"));
       authorRepo.save(newAuthor);
       return "Author details saved";

    }
}
