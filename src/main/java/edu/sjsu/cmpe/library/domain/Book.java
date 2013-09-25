package edu.sjsu.cmpe.library.domain;


import java.util.List;
import java.util.ArrayList;




import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


//@JsonPropertyOrder({ "isbn", "title", "publication-date", "language", "num-pages", "status", "reviews", "authors"})
public class Book {


	
	private long isbn;


	
    private String title;
    
	@JsonProperty("publication-date")
	
    private String publicationDate;
    
	@JsonProperty
    private String language;	
    
	@JsonProperty("num-pages")
    private int noOfPages; 				
    
	@JsonProperty
    private String status="available"; 	
    
	@JsonProperty
    private List<Review> reviews = new ArrayList<Review>();
    
	
	@JsonProperty
    private Author[] authors;
    
    
    public long getIsbn() {
	return this.isbn;
    }


    public void setIsbn(long isbn) {
	this.isbn = isbn;
    }


   
    public String getTitle() {
	return this.title;
    }


   
     
    public void setTitle(String title) {
	this.title = title;
    }
    
    
    public String getPublicationDate() {
	return this.publicationDate;
    }
    
     
    
    public void setPublicationDate(String publicationDate) {
	this.publicationDate = publicationDate;
    }
    
    
    public String getLanguage() {
	return this.language;
    }
    
    
    public void setLanguage(String language) {
	this.language = language;
    }
   
    
     
    public int getNoOfPages() {
	return this.noOfPages;
    }
    
    
    public void setNoOfPages(int noOfPages) {
	this.noOfPages = noOfPages;
    }
   
    
     
    public String getStatus() {
	return this.status;
    }
    
    
    public void setStatus(String status) {
	this.status = status;
    }


    
    public Author[] getAuthors() {
		return this.authors;
	}


 
     
    public void setAuthors(Author[] authors) {
		this.authors = authors;
	}


    
    public List<Review> getReviews() {
		return this.reviews;
	}
    
    
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}


	
	public Author getoneAuthors(int id) {
		return this.authors[id];	
	}


	
	public Review getoneReview(int id) {
		return this.reviews.get(id);
	}
}