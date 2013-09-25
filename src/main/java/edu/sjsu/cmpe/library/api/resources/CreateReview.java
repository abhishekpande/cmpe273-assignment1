package edu.sjsu.cmpe.library.api.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.domain.Review;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CreateReview {

    public CreateReview() {
	// do nothing
    }

    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public ReviewDto getReviewByIsbn(@PathParam("isbn") LongParam isbn) {
	// FIXME - Dummy code
	Review review = new Review();
	review.setIsbn(isbn.get());
	review.setComment("Very goood");

	ReviewDto bookResponse = new ReviewDto(review);
	bookResponse.addLink(new LinkDto("view-book", "/books/" + review.getIsbn(),
		"GET"));
	bookResponse.addLink(new LinkDto("update-book",
		"/books/" + review.getIsbn(), "POST"));
	// add more links

	return bookResponse;
    }


}
