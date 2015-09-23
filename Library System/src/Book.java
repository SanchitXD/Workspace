/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 * 				LIBRARY SYSTEM PROGRAM                 *
 * 					--USER CLASS--					   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @author Omar Abdeldayem                             *
 * @author Sanchit Luthra                              *
 * @instructor Mr. Sayed                               *
 * @date March 18th, 2014                              *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 */
public class Book 
{
	private final String TITLE;
	private final String AUTHOR;
	private final String CATEGORY;
	private final int COST;
	private final long ISBN;
	
	private User borrower;
	private double starRating;
	private boolean isCheckedOut;
	
	public Book(String title, String author, String category, int cost, long isbn, double rating)
	{
		// Assign all final variables values
		TITLE = title;
		AUTHOR = author;
		CATEGORY = category;
		COST = cost;
		ISBN = isbn;
		
		starRating = rating;
		borrower = null;
		isCheckedOut = false;
	}
	
	/**
	 * Get the Book's title
	 * @return Book title
	 */
	public String getTitle() 
	{
		return TITLE;
	}
	
	/**
	 * Get the Book's author
	 * @return Book author
	 */
	public String getAuthor() 
	{
		return AUTHOR;
	}
	
	/**
	 * Get the Book's ISBN number
	 * @return ISBN
	 */
	public long getISBN() 
	{
		return ISBN;
	}
	
	/**
	 * Get the Book's category
	 * @return Book category
	 */
	public String getCategory() 
	{
		return CATEGORY;
	}
	
	/**
	 * Get the Book's cost
	 * @return Book cost
	 */
	public int getCost() 
	{
		return COST;
	}
	
	/**
	 * Get the Book's star rating for comparisons
	 * @return Book star rating
	 */
	public double getStarRating() 
	{
		return starRating;
	}
	
	/**
	 * Get the state of the Book in the Library
	 * @return true if the Book is checked out, false if it is available in the Library
	 */
	public boolean isCheckedOut() 
	{
		return isCheckedOut;
	}
	
	/**
	 * Assign the Book a checked out state
	 * @param isCheckedOut - the state of the book, true if being checked out; false if being returned
	 */
	public void setCheckedOut(boolean isCheckedOut) 
	{
		this.isCheckedOut = isCheckedOut;
	}
	
	/**
	 * Associate/Dissociate the Book object with the User object checking out/checking in the Book
	 * @param borrower - appropriate User object if being checked out; null if being checked in
	 */
	public void setBorrower(User borrower)
	{
		this.borrower = borrower;
	}
	
	/**
	 * Get the reference to the User object with the Book
	 * @return User reference associated with the Book; null if the Book is not checked out
	 */
	public User getBorrower()
	{
		return borrower;
	}	
	
	/**
	 * toString method -- formats all Book information into a String
	 * @return String containing specific Book object's information
	 */
	public String toString()
    {
		String value = "";
        value = TITLE + "    by: " + AUTHOR + "    " + CATEGORY + "    $" + COST + "    " + ISBN + "    " + starRating + "    ";
        if(isCheckedOut == true)
        {
                value += "Checked out by " + borrower.getFirstName() + " " + borrower.getStudentNumber();
        }
        else
        {
                value += "In library";
        }
        
        return value;
    }
}
