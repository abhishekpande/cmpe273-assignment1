package edu.sjsu.cmpe.library.api.resources;


import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;  
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;

import com.yammer.dropwizard.jersey.params.IntParam;
import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

	  static final Map<Integer,Book> bookDb =new HashMap<Integer,Book>();
	  static final  AtomicInteger idCounter = new AtomicInteger(1);
	
	  public BookResource() {
	// do nothing
    }

    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public BookDto getBookByIsbn(@PathParam("isbn") IntParam isbn) {
	// FIXME - Dummy code
	Book book = new Book();
	 
    book.setIsbn(new Long(123));
	book.setTitle("Programming Java");
    BookDto bookResponse = new BookDto(book);
	bookResponse.addLink(new LinkDto( "view","/books/" +book.getIsbn(),"POST"));
	bookResponse.addLink(new LinkDto("update-book",
		"/books/" + book.getIsbn(), "POST"));
	// add more links

	return bookResponse;
    }

   @Path("/books")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public LinksDto createBook()
    {
   
	     Book book=new Book();
    //	bookDb.setIsbn(isbn.get());
    //	bookDb.put(book.getIsbn(),book);		
    	System.out.println("Created customer " + book.getIsbn());
    	LinksDto linkResponse=new LinksDto();
        linkResponse.addLink(new LinkDto("view-book","/books/1"+ book.getIsbn(),"GET"));   
        linkResponse.addLink(new LinkDto("update","/books/1"+ book.getIsbn(),"PUT"));
        linkResponse.addLink(new LinkDto("delete-book","/books/1" +book.getIsbn(),"DELETE"));
        linkResponse.addLink(new LinkDto("create-book","/books/1" +book.getIsbn(),"POST"));
       return linkResponse;
    }

@Path("/books/{isbn}")
@DELETE
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public LinksDto deleteBook(@PathParam("isbn") IntParam isbn)
{
	Book book=new Book();
		LinksDto linkResponse=new BookDto(book);
	
	linkResponse.addLink(new LinkDto("create-book","/books" +book.getIsbn(),"POST"));
    return linkResponse;
}

@Path("/books/{isbn}?status={new-status")
@PUT
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public LinksDto updateBook(@QueryParam("isbn")IntParam isbn)
{
	Book book=new Book();
 	
	LinksDto linkResponse=new LinksDto();
    linkResponse.addLink(new LinkDto("view-book","/books/1"+ book.getIsbn(),"GET"));   
    linkResponse.addLink(new LinkDto("update","/books/1"+ book.getIsbn(),"PUT"));
    linkResponse.addLink(new LinkDto("delete-book","/books/1" +book.getIsbn(),"DELETE"));
    linkResponse.addLink(new LinkDto("create-book","/books/1" +book.getIsbn(),"POST"));
   return linkResponse;

}


}
