package com.latha.bookservicegraphql.datafetcher2;

import com.latha.bookservicegraphql.model.Review;
import com.latha.bookservicegraphql.model.ReviewResponse;
import com.latha.bookservicegraphql.repository.ReviewRepo;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddReviewDataFetcher implements DataFetcher<ReviewResponse> {
    @Autowired
    ReviewRepo reviewRepo;

    @Override
    public ReviewResponse get(DataFetchingEnvironment dataFetchingEnvironment) {
        ReviewResponse response = new ReviewResponse();
        Review newReview = new Review();

        newReview.setBookId(dataFetchingEnvironment.getArgument("bookId"));
//        newReview.setReviewId(dataFetchingEnvironment.getArgument("reviewId"));
        newReview.setReviewerName(dataFetchingEnvironment.getArgument("reviewerName"));
        newReview.setComment(dataFetchingEnvironment.getArgument("comment"));
        reviewRepo.save(newReview);
//        response.set(newReview);
        response.setMessage("Review saved!");

        return response;
    }
}


