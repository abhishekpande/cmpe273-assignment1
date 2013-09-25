package edu.sjsu.cmpe.library.api.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
	

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;

	@Path("/v1/books")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public class DeleteResource {

	    public DeleteResource() 
	    {
	   
	    }
	    	
	    @Path("/{isbn}")
	    @DELETE
	    @Timed(name = "get-root")
	    public Response getRoot(@PathParam("isbn") LongParam isbn) {
		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("create-book", "/books", "POST"));
	    
		return Response.ok(links).build();
	    }
	}
		















