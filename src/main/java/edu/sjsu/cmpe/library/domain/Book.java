package edu.sjsu.cmpe.library.domain;

public class Book {
    private Book book;
	public Long  isbn;
    public String title;
    
    

    /**
     * @return the isbn
     */
    public Long getIsbn() {
	return isbn;
    }

    /**
     * @param isbn
     *            the isbn to set
     */
    public void setIsbn(Long isbn) {
	this.isbn = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }
}
