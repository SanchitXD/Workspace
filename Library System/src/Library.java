import java.io.*;
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

public class Library 
{	
	// Current number of User and Book object variables
	private int numUsers;
	private int numBooks;
	
	// Maximum values in the Library for User, Book objects
	private static int MAX_USERS;
	private static int SHELF_SPACE;
	
	// Maximum number of days a book can be checked out before fine is applied
	private static int MAX_DAYS_OUT;
	
	// ArrayLists that will hold the Book and User objects in the library
	private ArrayList<Book> bookCatalogue;
	private ArrayList<User> userList;
	
	public Library(int maxUsers, int shelfSpace)
	{
		// Assign variables initial values
		MAX_USERS = maxUsers;
		SHELF_SPACE = shelfSpace;
		MAX_DAYS_OUT = 14;
		userList = new ArrayList<User>(MAX_USERS);
		bookCatalogue = new ArrayList<Book>(SHELF_SPACE);
		// No current
		numUsers = 0;
		numBooks = 0;
		
		// Initialize the library with default users and books from a text file
		initializeDefaultUsers();
		initializeDefaultBooks();
	}
	
	/**
	 * Get the maximum number of days a Book can be checked out for
	 * @return MAX_DAYS_OUT
	 */
	public int getMaxDaysOut()
	{
		return MAX_DAYS_OUT;
	}
	
	/**
	 * Get the Library's catalogue of Books
	 * @return bookCatalogue
	 */
	public ArrayList<Book> getBookCatalogue()
	{
		return bookCatalogue;
	}
	
	/**
	 * Get the Lbirary's list of Users
	 * @return userList
	 */
	public ArrayList<User> getUserList() 
	{
		return userList;
	}
	
	/**
	 * Get the number of Books held in the Library
	 * @return numBooks
	 */
	public int getNumBooks()
	{
		return numBooks;
	}
	
	/**
	 * Get the number of Users registered in the Library
	 * @return numUsers
	 */
	public int getNumUsers() 
	{
		return numUsers;
	}
	
	/**
	 * Register a User in the Library system
	 * @param u - the User being added
	 * @return true if the user is added successfully, false otherwise
	 */
	public boolean addUser(User u)
	{
		boolean unique = true;
		
		// Checks for an already existing student number
		for(int i = 0; i < numUsers; i++) 
		{
			if(u.getStudentNumber() == userList.get(i).getStudentNumber())
			{
				unique = false;
				break;
			}
		}
		
		// Checks for space in the Library and a unique student number before adding
		if (numUsers < MAX_USERS && unique)
		{
			userList.add(u);
			numUsers++;
			return true;
		}
		// User being added already exists in the system
		else
		{
			return false;
		}
	}
	
	/**
	 * Remove a User from the Library system
	 * @param u - the User to be removed
	 * @return true if the User is removed successfully, false otherwise
	 */
	public boolean removeUser(User u)
	{
		// Indices in the list are positive so the default value is negative to avoid misinterpretation
		int userIndex = -1;
		
		// Look through the userList checking to see if a User matched the one to be removed
		for (int i = 0; i < numUsers; i++)
		{
			if (userList.get(i) == u)
			{
				userIndex = i;
				userList.remove(userIndex);
				break;
			}
		}
		
		// If a user is found, return all the Books they had checked out and
		// decrement the number of Users in the system
		if (userIndex != -1)
		{
			if (u.getNumBooksOut() > 0)
			{
				for (int i = 0; i < u.getBooksCheckedOut().size(); i++)
				{
					checkInBook(u.getBooksCheckedOut().get(i), 0);
				}
			}
			numUsers--;
			return true;
		}
		// User does not exist in the system; cannot be removed
		else
		{
			return false;
		}
	}
	
	/**
	 * Add a Book to the Library System
	 * @param b - the Book being added
	 * @return true if the Book is added successfully, false otherwise
	 */
	public boolean addBook(Book b)
	{
		// Check for space in the Library before adding
		if (numBooks < SHELF_SPACE)
		{
			bookCatalogue.add(b);
			numBooks++;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Remove a Book from the Library System
	 * @param b - the Book being removed
	 * @return true if the book is removed successfully, false otherwise
	 */
	public boolean removeBook(Book b)
	{
		int bookIndex = -1;
		
		// Look through the bookCatalogue to see if the Book exists within it
		for(int i = 0; i < numBooks; i++)
		{
			// Assing bookIndex the index of the location of the Book if a mathc is found
			if (bookCatalogue.get(i) == b)
			{
				bookIndex = i;
				break;
			}
		}
		
		// Check in the Book if it checked out, then remove it and decrement the number of Books
		if (bookIndex != -1)
		{
			if(b.isCheckedOut())
			{
				checkInBook(b, 0);
			}
			bookCatalogue.remove(bookIndex);
			numBooks--;
			return true;
		}
		// Book was not found in the system
		else
		{
			return false;
		}
	}
	
	/**
	 * Search for a User in the system based on their student number
	 * @param stdNum - the student number to search by
	 * @return reference to the User object with the matching student number
	 */
	public User searchUser(int stdNum)
	{
		// Loop through userList checking every User for a matching student number
		for (int i = 0; i < numUsers; i++)
		{
			if (userList.get(i).getStudentNumber() == stdNum)
			{
				return userList.get(i);
			}
		}
		// Student doesn't exist return null; error message handled by GUI
		return null;
	}
	
	/**
	 * Search for a Book in the system based on title
	 * @param title - the title searching by
	 * @return reference(s) to the Books with matching titles
	 */
	public ArrayList<Book>  searchBookByTitle(String title)
	{
		int numMatching = 0;
		
		// Check to see how many books have a matching title
		for (int k = 0; k < numBooks; k++)
		{
			if (bookCatalogue.get(k).getTitle().equalsIgnoreCase(title))
			{
				numMatching++;
			}
		}
		
		// Add all the matching Books in an array List and return it
		if (numMatching != 0)
		{
			ArrayList<Book>  matchingBooks = new ArrayList<Book> (numMatching);
			
			for (int i = 0; i < numBooks; i++)
			{
				if (bookCatalogue.get(i).getTitle().equalsIgnoreCase(title))
				{
					matchingBooks.add(bookCatalogue.get(i));
				}
			}
			return matchingBooks;
		}
		// No Books have that title
		else
		{
			return null;
		}
	}
	
	/**
	 * Performs a search on the bookCatalogue based on a specific author
	 * @param author - the author to find books by
	 * @return reference(s) to the Books with a matching author
	 */
	public ArrayList<Book>  searchBookByAuthor(String author)
	{
		ArrayList<Book>  matchingAuthors;
		int numMatching = 0;
		
		// Check to see how many books have a matching author
		for (int i = 0; i < numBooks; i++)
		{
			if (bookCatalogue.get(i).getAuthor().equalsIgnoreCase(author))
			{
				numMatching++;
			}
		}
		
		// Add all the matching Books in an array List and return it
		if (numMatching != 0)
		{
			matchingAuthors = new ArrayList<Book> (numMatching);
			for (int i = 0; i < numBooks; i++)
			{
				if (bookCatalogue.get(i).getAuthor().equalsIgnoreCase(author))
				{
					matchingAuthors.add(bookCatalogue.get(i));
				}
			}
			return matchingAuthors;
		}
		// No Books were written by that author
		else
		{
			return null;
		}
	}
	
	/**
	 * Performs a search on the book catalogue based on book categories
	 * @param category - the category to search by
	 * @return matchingCategories - the books found with matching categories, null if nothing is found
	 */
	public ArrayList<Book> searchBookByCategory(String category)
	{
		ArrayList<Book>  matchingCategories;
		int numMatching = 0;
		
		// Check to see how many books are in that category
		for (int i = 0; i < numBooks; i++)
		{
			if (bookCatalogue.get(i).getCategory().equalsIgnoreCase(category))
			{
				numMatching++;
			}
		}
		
		// Add all the matching Books in an array List and return it
		if (numMatching != 0)
		{
			matchingCategories = new ArrayList<Book> (numMatching);
			
			for (int i = 0; i < numBooks; i++)
			{
				if (bookCatalogue.get(i).getCategory().equalsIgnoreCase(category))
				{
					matchingCategories.add(bookCatalogue.get(i));
				}
			}
			return matchingCategories;
		}
		// No Books are in that category
		else
		{
			return null;
		}
		
	}
	
	/**
	 * Checks out a book to a specific user
	 * @param u - the user checking out the book
	 * @param b - the book being checked out
	 * @return true if the book is checked out successfully,
	 * 		   false if unsuccessful (book already checked out by another user)
	 */
	public boolean checkOutBook(User u, Book b)
	{
		// Check that the Book isn't already checked out
		// and that adding the Book to the User's list goes through (returns true)
		if (!b.isCheckedOut() && u.addToBooksCheckedOut(b))
		{
			// Check out the book and associate it with the specific user
			b.setCheckedOut(true);
			b.setBorrower(u);
			return true;
		}
		// Book already checked out/ User cannot check out anymore Books
		else
		{
			return false;
		}
	}
	
	/**
	 * Library method to check in a book back to the library
	 * @param - the book being returned
	 * @param daysOut - the number of days the book was checked out
	 * @return true if the book is checked in successfully, 
	 * 		   false if unsuccessful (book wasn't checked out or doesn't exist)
	 */
	public boolean checkInBook(Book b, int daysOut)
	{
		// Make sure Book is checked out
		if (b.isCheckedOut())
		{
			User bookBorrower = null;
			// Find the User object that has the Book checked out
			for (int i = 0; i < userList.size(); i++)
			{
				if (b.getBorrower() == userList.get(i))
				{
					bookBorrower = userList.get(i);
					break;
				}
			}
			
			// Maks the book available to check out and remove its association with its borrower
			b.setBorrower(null);
			b.setCheckedOut(false);
			
			if (bookBorrower != null)
			{
				// Removes the user's association with the book
				bookBorrower.returnBook(b);
			}
			
			// Apply a fine if the book was overdue
			if (daysOut > MAX_DAYS_OUT)
			{
				bookBorrower.addToFine(calculateFine(daysOut));
				return true;
			}
			

		}
		return false;
	}
	
	/** 
	 * Calculates student's fine based on number of days the book was out
	 * @param daysOut - number of days the book was checked out for
	 * @return the fine to be payed, if applicable
	 */
	private double calculateFine(int daysOut)
	{
		return 0.1 * (daysOut - MAX_DAYS_OUT);
	}
	
	/**
	 * Count the number of rows in a file 
	 * Accessory method to the initializeDefaultBooks and Users method
	 * @param fileName the File that will be read through to count the number of rows it has
	 * @return integer value of the number of rows in the file
	 */
	private int getNumRowsInFile(String fileName)
	{
		int numRows = 0;
		try 
		{
			// Create a BufferedReader to read through the file
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			// Read through the rows and increment the counter if they aren't empty/null
			while(reader.readLine() != null)
			{
				numRows++;
			}
			// Close the reader to prevent memory leaks
			reader.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return numRows;
	}
	
	/**
	 * Initializes the Library with a default set of Users read from a text file
	 */
	private void initializeDefaultUsers()
	{
		// User object parameters to be read from the file
		String fName = null;
		String lName = null;
		int studNum;
		
		// The current row in File
		int currentTotalRow = 1;
		// The current row of data that will be used to create a User object
		// Takes on values 1-3 inclusive
		int currentStudentRow = 1;
		// Get the number of rows in the file and trim off extra lines so the total is a multiple of 3
		// 3 lines of information are required to create a User
		int numOfRows = getNumRowsInFile("users.txt");
		if (numOfRows % 3 != 0)
		{
			numOfRows -= numOfRows % 3;
		}
		
		try 
		{	
			// Create the BufferedReader to read from the text file
			BufferedReader in = new BufferedReader(new FileReader("users.txt"));

			// Loop through the entire file as long as the Library has space to accommodate more Users
			while (currentTotalRow <= numOfRows && numUsers < MAX_USERS)
			{
				// Each value of currentStudentRow corresponds to a User Object parameter
				switch (currentStudentRow)
				{
				// Reads the first name
				case 1:
					fName = in.readLine();
					currentStudentRow++;
					break;
				// Reads the last Name
				case 2:
					lName = in.readLine();
					currentStudentRow++;
					break;
				// Reads the student number, creates and adds the User, then resets currentStudentRow to 1
				case 3:
					studNum = Integer.parseInt(in.readLine());
					addUser(new User(fName, lName, studNum));
					currentStudentRow = 1;
					break;
				}
				currentTotalRow++;
			}
			
			// Close reader to prevent memory leaks
			in.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Initializes the Library with a default set of Books from a text file
	 */
	private void initializeDefaultBooks()
	{
		// Book object parameters to be read from File
		String title = null;
		String author = null;
		String category = null;
		int cost = 0;
		long ISBN = 0;
		double rating = 0;
		
		// The current row in the File
		int currentTotalRow = 1;
		// The current row of data that will be used to create a Book object
		// Takes on values 1-6 inclusive
		int currentBookRow = 1;
		// Get the number of rows in the file and trim off extra lines so the total is a multiple of 6
		// 6 lines of information are required to create a User
		int numOfRows = getNumRowsInFile("books.txt");
		if (numOfRows % 6 != 0)
		{
			numOfRows -= numOfRows % 6;
		}
		
		try 
		{
			// Create the BufferedReader to read from the text file
			BufferedReader in = new BufferedReader(new FileReader("books.txt"));
			
			// Loop through the entire file as long as the Library has space to accommodate more Book
			while (currentTotalRow <= numOfRows && numBooks < SHELF_SPACE)
			{
				switch (currentBookRow)
				{
				// Reads the Book title
				case 1:
					title = in.readLine();
					currentBookRow++;
					break;
				// Reads the Book's author
				case 2:
					author = in.readLine();
					currentBookRow++;
					break;
				// Reads the Book category
				case 3:
					category = in.readLine();
					currentBookRow++;
					break;
				// Reads the Book's cost
				case 4:
					cost = Integer.parseInt(in.readLine());
					currentBookRow++;
					break;
				// Reads the Book's ISBN 
				case 5:
					ISBN = Long.parseLong(in.readLine());
					currentBookRow++;
					break;
				// Reads the Book's rating, creates and adds the Book, then resets currentBookRow to 1
				case 6: 
					rating = Double.parseDouble(in.readLine());
					addBook(new Book(title, author, category, cost, ISBN, rating));			
					currentBookRow = 1;
					break;
				}
				currentTotalRow++;
			}
			// Close reader to prevent memory leaks
			in.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
