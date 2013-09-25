package edu.sjsu.cmpe.library.dto;
import edu.sjsu.cmpe.library.domain.Review;
import java.util.List;


public class ReviewsDto extends LinksDto{
    private List<Review> review;


    public ReviewsDto(List<Review> review) {
    	super();
    	this.review = review;
        }
    

 public List<Review> getReview() {
	return review;
    }


   public void setReview(List<Review> review) {
	this.review = review;
    }
}

 
 


 
   

