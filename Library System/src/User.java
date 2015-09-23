import java.util.ArrayList;

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

public class User 
{
	// Maximum number of books a user can have checked out
	private static final int MAX_BOOKS = 3;
	// Maximum fine that can accumulate before user is prevented from checking out books
	private static final double MAX_FINE = 5.00;
	
	private int studentNumber;
	private int numBooksOut;
	private double currentFine;
	private String firstName;
	private String lastName;
	// List of all the books checked out by user
	private ArrayList<Book> booksOut;
	
	public User(String fName, String lName, int stuNum)
	{
		// Assign all the member variables their appropriate values
		firstName = fName;
		lastName = lName;
		studentNumber = stuNum;
		currentFine = 0.00;
		numBooksOut = 0;
		booksOut = new ArrayList<Book>(MAX_BOOKS);
	}
	
	/**
	 * Student number get method
	 * @return user's student number
	 */
	public int getStudentNumber()
	{
		return studentNumber;
	}
	
	/**
	 * First name get method
	 * @return user's first name
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * Last name get method
	 * @return user's last name
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * Fine get method
	 * @return user's current fine
	 */
	public double getCurrentFine()
	{
		return currentFine;
	}
	
	/**
	 * Add's to the user's current fine for overdue books
	 * @param fine - fine to be added
	 */
	public void addToFine(double fine)
	{
		currentFine += fine;
	}
	
	/**
	 * User method that pays overdue fines to the library
	 * @param paid - amount of money the student is paying
	 * @return true if payment was processed, false otherwise
	 */
	public boolean payFine(double paid)
	{
		// Check if the user owes money
		if (currentFine != 0)
		{
			// Prevents the current fine from dropping to a negative value if the amount paid is greater than the fine
			if (paid > currentFine)
			{
				currentFine = 0.0;
			}
			else
			{
				currentFine -= paid;
			}
			return true;
		}
		else
		{
			// User didn't owe money
			return false;
		}
	}
	
	
	/**
	 * Get all the books currently checked out by the user
	 * @return ArrayList of user's books
	 */
	public ArrayList<Book> getBooksCheckedOut()
	{
		return booksOut;
	}
	
	/**
	 * Get the number of books
	 * @return
	 */
	public int getNumBooksOut()
	{
		return numBooksOut;
	}
	

	/**
	 * Adds a Book to the end of the User's array of borrowed books
	 * IFF the user has less than three books checked out and current fine doesn't exceed the max
	 * @param b - the Book to be checked out/borrowed
	 */
	public boolean addToBooksCheckedOut(Book b)
	{
		// Check that the user hasn't exceed the maximum fine / maximum number of books checked out
		if (numBooksOut < MAX_BOOKS && currentFine <= MAX_FINE) 
		{
			// Add the book and increase the number of books checked out counter
			booksOut.add(b);
			numBooksOut++;
			return true;
		}
		// Check out wasn't processed 
		else
		{
			return false;
		}
	}
	
	/**
	 * Removes the book from the User's books checked out array
	 * @param b - the book to be removed from the array
	 * @return true if the book was removed successfully and false otherwise
	 */
	public boolean returnBook(Book b)
	{
		// Book index is by default -1 (doesn't exist in user's checked out list)
		int bookIndex = -1;
		
		// Loop through the user's checked out books array
		for (int i = 0; i < MAX_BOOKS; i++)
		{
			// If book being returned matches a reference in the User's checked out books, remove it
			if (booksOut.get(i) == b)
			{
				bookIndex = i;
				booksOut.remove(bookIndex);
				// break out of the loop since result has already been found
				break;
			}
		}
		
		// Decrement the number of books the user has checked out
		if (bookIndex != -1)
		{
			numBooksOut--;
			return true;
		}
		// Book being returned isn't with the User, nothing has changed
		else
		{
			return false; 
		}
		
	}
	
	/**
	 * toString method -- formats all User information into a String
	 * @return String containing specific User object's information
	 */
	public String toString()
	{
		String value = "";

		value += lastName + ", " + firstName + "    " + studentNumber + "    $" + currentFine + "    ";

		if(numBooksOut == 0)
		{
			value += "No books out";
		}
		else
		{
			for(int i = 0; i < numBooksOut; i++)
			{
				value += booksOut.get(i).getTitle() + ", ";
			}
		}

		return value;
	}
	
}
