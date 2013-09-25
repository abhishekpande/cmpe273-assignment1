package edu.sjsu.cmpe.library.api.resources;



import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



import com.yammer.metrics.annotation.Timed;
import com.sun.jersey.api.NotFoundException;


import edu.sjsu.cmpe.library.domain.Book;

import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;
import edu.sjsu.cmpe.library.dto.AuthorDto;
import edu.sjsu.cmpe.library.dto.AuthorsDto;
import edu.sjsu.cmpe.library.domain.Author;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Resources {

	private static HashMap<Long, Book> bookstore = new HashMap<Long,Book>();
   private static long bookisbn=1;
	private static long authorisbn=1;
	private static long reviewisbn=1;
	
     @POST
	@Timed(name = "create-book")
	public Response createBook( Book book) {
		book.setIsbn(bookisbn); 
		bookstore.put(bookisbn, book);
		bookisbn++;
     for (int author=0;author<book.getAuthors().length;author++)
		{
			book.getAuthors()[author].isbn=authorisbn;
			authorisbn++;
       }
        BookDto bookResponse = new BookDto();
		bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(), "GET"));
		bookResponse.addLink(new LinkDto("update-book","/books/" + book.getIsbn(), "PUT"));
		bookResponse.addLink(new LinkDto("delete-book","/books/" + book.getIsbn(), "DELETE"));
		bookResponse.addLink(new LinkDto("create-review","/books/" + book.getIsbn() + "/reviews", "POST"));
         return Response.status(201).entity(bookResponse.getLinks()).build();
	}
    
    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public BookDto viewBook(@PathParam("isbn") long isbn) {

     Book getbook=bookstore.get(isbn);
     BookDto bookResponse = new BookDto(getbook);
		bookResponse.addLink(new LinkDto("view-book", "/books/" + getbook.getIsbn(), "GET"));
		bookResponse.addLink(new LinkDto("update-book","/books/" + getbook.getIsbn(), "PUT"));
		bookResponse.addLink(new LinkDto("delete-book","/books/" + getbook.getIsbn(), "DELETE"));
		bookResponse.addLink(new LinkDto("create-review","/books/" + getbook.getIsbn() + "/reviews", "POST"));
        return bookResponse;
    }

@DELETE
    @Path("/{isbn}")
    @Timed(name = "delete-book")
    public Response deleteBook(@PathParam("isbn") long isbn) {
          bookstore.remove(isbn);
          LinksDto links = new LinksDto();
		  links.addLink(new LinkDto("create-book", "/books", "POST"));
          return Response.ok(links).build();
    }
    @PUT
    @Path("/{isbn}")
    @Timed(name = "update-book")
    public Response updateBook(@PathParam("isbn") long isbn, @QueryParam("status") String status) throws Exception {

       try{
			if(!status.equalsIgnoreCase("avialable") &&
				!status.equalsIgnoreCase("lost") &&
				!status.equalsIgnoreCase("checked-out") &&
				!status.equalsIgnoreCase("in-queue")) {
			throw new NotFoundException("In-valid value entered for status. Valid values are [avialable,lost,checked-out,in-queue]");


			}
		}	catch (Exception e) {
			throw e;
		}

       Book book= bookstore.get(isbn);
		book.setStatus(status);
       BookDto bookResponse = new BookDto();
		bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(), "GET"));
		bookResponse.addLink(new LinkDto("update-book","/books/" + book.getIsbn(), "PUT"));
		bookResponse.addLink(new LinkDto("delete-book","/books/" + book.getIsbn(), "DELETE"));
		bookResponse.addLink(new LinkDto("create-review","/books/" + book.getIsbn() + "/reviews", "POST"));
		if (book.getReviews().size() !=0 ){
			bookResponse.addLink(new LinkDto("view-all-reviews","/books/" + book.getIsbn() + "/reviews", "GET"));
			}


	return Response.ok().entity(bookResponse.getLinks()).build();
    }
@POST
    @Path("/{isbn}/reviews")
    @Timed(name = "create-review")
    public Response createReview( Review reviews, @PathParam("isbn") long isbn) {


		Book book = bookstore.get(isbn);
        reviews.setID(reviewisbn);
		book.getReviews().add(reviews);
		reviewisbn++;
        ReviewDto reviewResponse = new ReviewDto();
		reviewResponse.addLink(new LinkDto("view-review", "/books/" + book.getIsbn() + "/reviews/" + reviews.getID(), "GET"));
       return Response.status(201).entity(reviewResponse.getLinks()).build();
    }
 @GET
    @Path("/{isbn}/reviews/{id}")
    @Timed(name = "view-review")
    public ReviewDto viewReview(@PathParam("isbn") long isbn, @PathParam("id") long id) {
		int i=0;
		Book book = bookstore.get(isbn);


		while (book.getoneReview(i).getID()!=id)
		{
			i++;
		}


		ReviewDto reviewResponse = new ReviewDto(book.getoneReview(i));
        return reviewResponse;

	}

 @GET
 @Path("/{isbn}/reviews")
 @Timed(name = "view-all-reviews")
 public ReviewsDto viewAllReviews(@PathParam("isbn") long isbn) {


		Book book = bookstore.get(isbn);
		ReviewsDto reviewResponse = new ReviewsDto(book.getReviews());


	return reviewResponse;
 }

 @GET
    @Path("/{isbn}/authors")
    @Timed(name = "view-all-authors")
    public AuthorsDto viewAllAuthors(@PathParam("isbn") long isbn) {


		Book book = bookstore.get(isbn);
		AuthorsDto authorResponse = new AuthorsDto(book.getAuthors());


	return authorResponse;
    }
}

