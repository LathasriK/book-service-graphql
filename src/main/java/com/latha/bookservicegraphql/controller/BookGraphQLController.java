package com.latha.bookservicegraphql.controller;


import com.latha.bookservicegraphql.model.Book;
import com.latha.bookservicegraphql.service.GraphQLService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.Query;

@RestController
@RequestMapping("/graphql/bookstore")
public class BookGraphQLController {

@Autowired
GraphQLService graphQLService;

@PostMapping
public ExecutionResult getAllBooks(@RequestBody String query){
   ExecutionResult result = graphQLService.getGraphQL().execute(query);
   return result;
}





















}
