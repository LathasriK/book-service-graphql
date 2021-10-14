package com.latha.bookservicegraphql.service;

import com.latha.bookservicegraphql.datafetcher.*;
import com.latha.bookservicegraphql.datafetcher1.AddAuthorDataFetcher;
import com.latha.bookservicegraphql.datafetcher1.AuthorDataFetcher;
import com.latha.bookservicegraphql.datafetcher2.AddReviewDataFetcher;
import com.latha.bookservicegraphql.datafetcher2.ReviewDataFetcher;
import com.latha.bookservicegraphql.model.Author;
import com.latha.bookservicegraphql.model.Book;
import com.latha.bookservicegraphql.repository.AuthorRepo;
import com.latha.bookservicegraphql.repository.BookRepo;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;


@Service
public class GraphQLService {

    @Autowired
    BookRepo bookService;

    @Autowired
    AuthorRepo authorRepo;

    @Value("classpath:books.graphql")
    Resource resource;

    @Autowired
    AllBookDataFetcher allBookDataFetcher;

    @Autowired
    BookDataFetcher bookDataFetcher;

    @Autowired
    CreateBookDataFetcher createBookDataFetcher;
    @Autowired
    UpdateBookDataFetcher updateBookDataFetcher;

    @Autowired
    ReviewDataFetcher reviewDataFetcher;
    @Autowired
    AddReviewDataFetcher addReviewDataFetcher;

    @Autowired
    AuthorDataFetcher authorDataFetcher;
    @Autowired
    AddAuthorDataFetcher addAuthorDataFetcher;


    private GraphQL graphQL;


    @PostConstruct
    private void loadSchema() throws IOException {

        LoadData();

        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();

    }

    private void LoadData() {
        Stream.of(
                new Book("KL123", "Harry potter",1998,"new","telugu",".pdf",200,"The lives of a young wizard","http://graph.org/7123/abc.jpg","11"),
                new Book("KL456", "Half girl friend",2014,"1st edition","English",".pdf",335,"winning over the girl he loves","http://bagath.org/7234/abc.jpg","22")).forEach(book -> {
                    bookService.save(book); });

        Stream.of(
                new Author("11","J.K.Rowling",22,"Sada siva colony","3-6",600119),
                new Author("22","Chetan Bagath",22,"Chandra Layout","3-16",522509)).forEach(author -> {
            authorRepo.save(author);
        });

    }


    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("all", allBookDataFetcher)
                        .dataFetcher("book", bookDataFetcher)
                        .dataFetcher("review",reviewDataFetcher)
                        .dataFetcher("author",authorDataFetcher)
                        .dataFetcher("reviews",reviewDataFetcher))

                .type("Book", typeWiring -> typeWiring
                        .dataFetcher("review",reviewDataFetcher)
                        .dataFetcher("author",authorDataFetcher))

                .type("Mutation", typeWiring -> typeWiring
                        .dataFetcher("createBook", createBookDataFetcher)
                        .dataFetcher("updateBook", updateBookDataFetcher)
                        .dataFetcher("addReview",addReviewDataFetcher)
                        .dataFetcher("addAuthor",addAuthorDataFetcher))
                        .build();

    }

    public GraphQL getGraphQL() {

        return graphQL;
    }
}
