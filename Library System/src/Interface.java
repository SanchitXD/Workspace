import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 							LIBRARY SYSTEM PROGRAM                               *
 * 								--USER CLASS--				               	     *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @author Omar Abdeldayem                             						     *
 * @author Sanchit Luthra                                                        *
 * @instructor Mr. Sayed                               						     *
 * @date March 18th, 2014                             						     *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 
 * 
 * Purpose:
 * 			The purpose of this assignment was to create a 
 * 		simple library systen using the Object-Oriented 
 * 		Programming paradigm.
 * 			
 * 			The User, Book, and Library classes were created
 * 		with the functionality to search, remove and add Users/Books.
 * 		Also, for users to check out, check in books, accumulate and 
 * 		pay fines.
 * 
 * Logic:
 * 			When started, the user is presented with the option of setting 
 * 		how large the book and user databases are. This can also be skipped
 * 		and the program will default to a value of 5 for both.
 * 
 *   		The User is then given the option to search, login a user, add an item, and remove 
 *   	items.
 *   
 *   		The user can search for both users and books separately and the complete 
 *   	databases can be printed out for both. When search for a student a student number must be used.
 *   	When searching for a book a choice can be made between title, author and category.
 *   
 *   		The login will allow  student to check in, check out, or pay a fine. A check in cannot be made if 
 *   	there are no books out. A check out cannot be made if the user is not eligible.
 *   
 *   		Books and Users can be added. All of the User's and book's personal info is required.
 *   
 *   		Books and users can be remove. Some of the user's and book's personal info are required.
 *   
 *   Bugs:
 *   		Some of the painted text may go off screen if it is too long.
 *   	
 */
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@SuppressWarnings("serial")
public class Interface extends Applet implements ActionListener
{
	//Size of the Window
	private	final int APPLET_WIDTH = 500;
	private	final int APPLET_HEIGHT = 400;

	//The library object 
	//and it's parameters
	private	int SHELF_SPACE;
	private	int SCHOOL_SIZE;
	private	Library library;
	
	//Buttons that appear on the mainMenuScreen
	private	Button mainMenuSearch;
	private	Button loginUser;
	private	Button addItem;
	private	Button removeItem;
	
	//Button that appears on multiple screens
	//mainly used for small tasks
	private	Button next;
	
	//Buttons for the search screens
	private	Button searchConfirm;
	private	Button listUsers;
	private	Button listBooks;
	
	//Button used to return to the mainMenu
	private	Button home;
	
	//Button for comparing searched books
	private	Button compare;
	
	//Buttons for adding/removing objects
	private	Button addConfirm;
	private	Button removeConfirm;
	
	//Buttons used on the option screen
	private	Button checkIn;
	private	Button checkOut;
	private	Button lost;
	private	Button payFine;
	private	Button confirm;
	
	//Check box group used for checking in books
	private	CheckboxGroup booksCheckedOut;
	private	Checkbox item1;
	private	Checkbox item2;
	private	Checkbox item3;
	
	//Used for the different ways 
	//of searching books
	private	CheckboxGroup searchTypes;
	private	Checkbox author;
	private	Checkbox title;
	private	Checkbox category;
	
	//Used to select user or book
	//for the search, add and remove
	private	CheckboxGroup selection;
	private	Checkbox user;
	private	Checkbox book;
	
	//A text field used when only one is needed
	//on screen
	private	TextField searchBar;
	
	//used for adding students
	private	TextField fname;
	private	TextField lname;
	private	TextField stuNum;
	
	//used for adding/removing books
	private	TextField titleText;
	private	TextField authorText;
	private	TextField categoryText;
	private	TextField costText;
	private	TextField isbnText;
	private	TextField ratingText;
	
	//the screen seen at start
	private	boolean mainScreen;

	//the screens that are used for adding books/users
	private	boolean addScreen;
	private	boolean addUserScreen;
	private	boolean addBookScreen;
	private	boolean addResultsScreen;
	
	//the screens used for search
	private	boolean searchScreen;
	private	boolean searchUserScreen;
	private	boolean searchBookScreen;
	private	boolean searchBookResultsScreen;
	private	boolean searchUserResultsScreen;
	
	//the screens used for removing books/users
	private	boolean removeScreen;
	private	boolean removeUserScreen;
	private	boolean removeBookScreen;
	private	boolean removeResultsScreen;
	
	//the screens used for the login process
	private	boolean loginScreen;
	private	boolean optionScreen;
	private	boolean checkOutScreen;
	private	boolean checkInScreen;
	private	boolean fineScreen;
	private	boolean resultsScreen;
	
	//used to decide whether something has been done correctly
	//and used for paint
	private	boolean error;
	private	boolean comparison;
	private	boolean found;
	private	boolean added;
	private	boolean removed;
	private	boolean canCheckOut;
	private	boolean canCheckIn;
	private	boolean completed;
	
	//the lists of books sorted for use throughout the program
	private	ArrayList<User> userList;
	private	ArrayList<Book> bookList;
	
	//Used for the compare
	private	Book highestRated;
	
	//the String used for printing out error
	//the the results of check in, check out
	//and the pay fine
	private	String errorText;
	private	String resultText;
	
	//the color used for the background and title header
	private Color backGround;
	private Color header;
    private Font defaultFont;

	public void init()//TODO init
	{		
		//set the size of the window
		setSize(APPLET_WIDTH, APPLET_HEIGHT);

		// Assign the Colors values
		backGround = new Color(255, 68, 68);
		header = new Color(204, 0, 0);
		
		// Assign error and result text empty values
		errorText = "";
		resultText = "";
		
		//no layout 
		setLayout(null);
		
		try
		{
			//parse the sizes of the databases
			SHELF_SPACE = Integer.parseInt(JOptionPane.showInputDialog("How big is the library?"));
			SCHOOL_SIZE = Integer.parseInt(JOptionPane.showInputDialog("How many students are in the school?"));
		}
		catch(Exception e)
		{
			//set default size if parse not completed
			SHELF_SPACE = 5;
			SCHOOL_SIZE = 5;
		}
		
		//set up new library
		library = new Library(SCHOOL_SIZE, SHELF_SPACE);
		library.addUser(new User("Sanchit", "Luthra", 458895));
		library.addUser(new User("Hello", "Bro", 458885));
		
		library.addBook(new Book("Road", "King", "Fiction", 5, 12345678, 4));
		library.addBook(new Book("Hunger", "King", "Horror", 15, 1234567, 4.5));

		
		//initialize the ArrayLists
		bookList = new ArrayList<Book>(SHELF_SPACE);
		userList = new ArrayList<User>(SCHOOL_SIZE);

		//Initialize search bar
		searchBar = new TextField("", 100);
		searchBar.setBounds(150, 100, 200, 25);
		
		//initialize text fields
		fname = new TextField("", 100);
		lname = new TextField("", 100);
		stuNum = new TextField("", 100);
		titleText = new TextField("", 100);
		authorText = new TextField("", 100);
		categoryText = new TextField("", 100);
		costText = new TextField("", 100);
		isbnText = new TextField("", 100);
		ratingText = new TextField("", 100);

		//initialize check boxes
		selection = new CheckboxGroup();
		user = new Checkbox("User", selection, true);
		book = new Checkbox("Book", selection, false);
		
		//initialize check boxes
		searchTypes = new CheckboxGroup();
		author = new Checkbox("Author", searchTypes, false);
		title = new Checkbox("Title", searchTypes, true);
		category = new Checkbox("Category", searchTypes, false);
		
		//initialize check boxes
		booksCheckedOut = new CheckboxGroup();
		item1 = new Checkbox("", booksCheckedOut, true);
		item2 = new Checkbox("", booksCheckedOut, false);
		item3 = new Checkbox("", booksCheckedOut, false);

		//initialize buttons
		mainMenuSearch = new Button("Search Book/User");
		loginUser = new Button("Login User");
		addItem = new Button("Add User/Book");
		removeItem = new Button("Remove User/Book");
		next = new Button("Next");
		searchConfirm = new Button("Search");
		listUsers = new Button("User List");
		listBooks = new Button("List Books");
		home = new Button("Home");
		compare = new Button("Comapare");
		addConfirm = new Button("Add");
		removeConfirm = new Button("Remove");
		checkIn = new Button("Check in");
		checkOut = new Button("Check out");
		lost = new Button("Lost");
		payFine = new Button("Pay Fine");
		confirm = new Button("Confirm");
		
		//initialize screens and set GUI
		addScreen = false;
		searchScreen = false;
		removeScreen = false;
		loginScreen = false;
		user.setBounds(150, 100, 100, 25);
		book.setBounds(150, 175, 100, 25);
		next.setBounds(200, 245, 100, 25);
		
		optionScreen = false;
		checkInScreen = false;
		checkOutScreen = false;
		fineScreen = false;
		resultsScreen = false;
		checkIn.setBounds(175, 100, 150, 25);
		lost.setBounds(210, 200, 80, 40);
		checkOut.setBounds(200, 175, 100, 25);
		payFine.setBounds(175, 250, 150, 25);
		confirm.setBounds(210, 140, 80, 40);
		item1.setBounds(150, 70, 55, 25);
		item2.setBounds(125, 70, 50, 25);
		item3.setBounds(300, 70, 80, 25);
		
		removeUserScreen = false;
		removeBookScreen = false;
		removeConfirm.setBounds(210, 140, 80, 40);
		
		addUserScreen = false;
		fname.setBounds(150, 100, 200, 20);
		lname.setBounds(150, 160, 200, 20);
		stuNum.setBounds(150, 220, 200, 20);
		addConfirm.setBounds(210, 260, 80, 40);
		
		addBookScreen = false;
		titleText.setBounds(50, 100, 180, 20);
		authorText.setBounds(270, 100, 180, 20);
		categoryText.setBounds(50,  160, 180, 20);
		costText.setBounds(270, 160, 180, 20);
		isbnText.setBounds(50, 220, 180, 20);
		ratingText.setBounds(270, 220, 180, 20);

		searchUserScreen = false;
		listUsers.setBounds(210, 220, 80, 40);
		searchConfirm.setBounds(210, 140, 80, 40);

		addResultsScreen = false;		
		searchUserResultsScreen = false;
		searchBookResultsScreen = false;
		addResultsScreen = false;
		resultsScreen = false;
		home.setBounds(20, 50, 100, 25);
		compare.setBounds(140, 50, 100, 25);
		
		searchBookScreen = false;
		title.setBounds(225, 70, 50, 25);
		author.setBounds(150, 70, 55, 25);
		category.setBounds(300, 70, 80, 25);
		listBooks.setBounds(210, 220, 80, 40);
		
		mainScreen = true;
		mainMenuSearch.setBounds(175, 100, 150, 25);
		loginUser.setBounds(200, 175, 100, 25);
		addItem.setBounds(175, 250, 150, 25);
		removeItem.setBounds(175, 325, 150, 25);
		
		//add Action Listeners
		mainMenuSearch.addActionListener(this);		
		loginUser.addActionListener(this);
		addItem.addActionListener(this);
		removeItem.addActionListener(this);
		next.addActionListener(this);
		searchConfirm.addActionListener(this);
		listUsers.addActionListener(this);
		listBooks.addActionListener(this);
		home.addActionListener(this);
		compare.addActionListener(this);
		addConfirm.addActionListener(this);
		removeConfirm.addActionListener(this);
		checkIn.addActionListener(this);
		checkOut.addActionListener(this);
		lost.addActionListener(this);
		payFine.addActionListener(this);
		confirm.addActionListener(this);

		//add the initial GUI
		add(mainMenuSearch);
		add(loginUser);
		add(addItem);
		add(removeItem);		
	}

	public void paint(Graphics g) 
	{
		// TODO paint
		// Store the default font to revert to it later
		if (defaultFont == null)
		{
			defaultFont = g.getFont();
		}
		
		//draw background
		g.setColor(backGround);
		g.fillRect(0, 0, APPLET_WIDTH, APPLET_HEIGHT);
		
		//draw header
		g.setColor(header);
		g.fillRect(0, 0, APPLET_WIDTH, 45);
		
		//set text color and header font
		g.setColor(Color.WHITE);
		g.setFont(new Font("Segoe UI", 1, 26));
		
		//appears at the top of the window
		g.drawString("SLSS Library System", 130, 30);
		
		// revert to default font for remaining text
		g.setFont(defaultFont);
		
		if(error)
		{
			//if the program runs into an error it will
			//draw the message
			g.drawString(errorText, 200, 400);
		}
		
		if(searchUserScreen || removeUserScreen || loginScreen)
		{
			//used for any screen that uses the search bar
			g.drawString("Enter Student Number", 150, 100);
		}
		else if(searchUserResultsScreen)//the search results for the user
		{
			if(found)//if something is found
			{
				for(int i = 0; i < userList.size(); i++)//print all results from the list
				{
					g.drawString(userList.get(i).toString(), 20, 100 + (i*20));
				}
			}
			else
			{
				g.drawString("Student not found.", 20, 100);//if nothing is not found
			}
		}
		else if(searchBookResultsScreen)//the search results for books
		{
			if(found)//if book(s) are found
			{
				for(int i = 0; i < bookList.size(); i++)//print all the results
				{
					g.drawString(bookList.get(i).toString(), 20, 100 + (i*20));
				}
			}
			else
			{
				g.drawString("Book not found.", 20, 100);//if no book is found
			}
			
			if(comparison)//if the user wants to compare
			{
				//print the result of the comparison
				g.drawString(highestRated.getTitle() + " by: " + highestRated.getAuthor() + " is better.", 140, 70);
			}
		}
		else if(addUserScreen)//add user screen
		{
			//add the appropriate text to direct the user
			g.drawString("First Name", 150, 100);
			g.drawString("Last Name", 150, 160);
			g.drawString("Student Number", 150, 220);
		}
		else if(addBookScreen)//add book screen
		{
			//add the appropriate text to direct the user
			g.drawString("Title", 50, 100);
			g.drawString("Category", 50, 160);
			g.drawString("ISBN", 50, 220);
			g.drawString("Author", 270, 100);
			g.drawString("Cost", 270, 160);
			g.drawString("Rating", 270, 220);
		}
		else if(addResultsScreen)//the addResultsScreen
		{
			if(added)//if the the user was added
			{
				g.drawString("Added!", 20, 100);
			}
			else//if the user was not added
			{
				g.drawString("Not added.", 20, 100);
			}
		}
		else if(removeBookScreen || checkOutScreen)
		{
			//draw the appropriate directional text
			g.drawString("Title", 50, 100);
			g.drawString("Author", 270, 100);
		}
		else if(removeResultsScreen)//the removeResultsScreen
		{
			if(removed)//if it was removed
			{
				g.drawString("Removed!", 20, 100);
			}
			else//if it was not removed
			{
				g.drawString("Not removed.", 20, 100);
			}
		}
		else if(fineScreen)// the fineScreen
		{
			//directional text
			g.drawString("Balance: $" + userList.get(0).getCurrentFine(), 150, 80);
			g.drawString("Amout paid", 150, 100);
		}
		else if(checkInScreen)//the checkInScreen
		{
			//directional text
			g.drawString("Days Out", 80, 120);
		}
		else if(resultsScreen)//the resultsScreen
		{
			//the result of the logged in student's action
			g.drawString(resultText, 20, 100);
		}
		else if(optionScreen)
		{
			if(!canCheckIn)
			{
				g.drawString("You cannot check in anything!", 175, 130);
			}
			if(!canCheckOut)
			{
				g.drawString("You cannot check anything out!", 175, 195);
			}
		}
	}


	public void actionPerformed(ActionEvent e)
	{
		// TODO Action Performed
		if(e.getSource() == mainMenuSearch)//mainMenuSearch
		{	
			//add and remove GUI
			mainScreen = false;
			remove(mainMenuSearch);
			remove(loginUser);
			remove(addItem);
			remove(removeItem);

			searchScreen = true;
			add(user);
			add(book);
			add(next);
		}
		else if(e.getSource() == loginUser)//loginUser
		{
			//add and remove GUI
			mainScreen = false;
			remove(mainMenuSearch);
			remove(loginUser);
			remove(addItem);
			remove(removeItem);
			
			loginScreen = true;
			add(searchBar);
			add(next);
			
			repaint();
		}
		else if(e.getSource() == addItem)//addItem
		{
			//add and remove GUI
			mainScreen = false;
			remove(mainMenuSearch);
			remove(loginUser);
			remove(addItem);
			remove(removeItem);

			addScreen = true;
			add(user);
			add(book);
			add(next);
		}
		else if(e.getSource() == removeItem)//removeItem
		{
			//add and remove GUI
			mainScreen = false;
			remove(mainMenuSearch);
			remove(loginUser);
			remove(addItem);
			remove(removeItem);

			removeScreen = true;
			add(user);
			add(book);
			add(next);
		}
		else if(e.getSource() == next)//next button
		{
			if(addScreen == true)//next on the addScreen
			{
				if(user.getState() == true)//if the user wants to add a student
				{
					//add and remove GUI
					addScreen = false;
					remove(user);
					remove(book);
					remove(next);
					
					addUserScreen = true;
					add(stuNum);
					add(fname);
					add(lname);
					add(addConfirm);
					
					repaint();
				}
				else if(book.getState())//if the user wants to add a book
				{
					//add and remove GUI
					addScreen = false;
					remove(user);
					remove(book);
					remove(next);
					
					addBookScreen = true;
					add(titleText);
					add(authorText);
					add(categoryText);
					add(costText);
					add(isbnText);
					add(ratingText);
					add(addConfirm);
					
					repaint();
				}
			}
			else if(searchScreen == true)
			{
				if(user.getState()) // Search for users
				{
					//add and remove GUI
					searchScreen = false;
					remove(user);
					remove(book);
					remove(next);

					searchUserScreen = true;
					add(listUsers);
					add(searchConfirm);
					searchBar.setText("");
					add(searchBar);

					repaint();
				}
				else if(book.getState()) // Search for books
				{
					//add and remove GUI
					searchScreen = false;
					remove(user);
					remove(book);
					remove(next);

					searchBookScreen = true;
					add(searchBar);
					add(searchConfirm);
					add(author);
					add(title);
					add(category);
					add(listBooks);
				}
			}
			else if(removeScreen)
			{
				if(user.getState())//remove a user
				{
					//add and remove GUI
					removeScreen = false;
					remove(user);
					remove(book);
					remove(next);
					
					removeUserScreen = true;
					add(searchBar);
					add(removeConfirm);
					
					repaint();
				}
				else if(book.getState())//remove a book
				{
					//add and remove GUI
					removeScreen = false;
					remove(user);
					remove(book);
					remove(next);
					
					removeBookScreen = true;
					add(titleText);
					add(authorText);
					add(removeConfirm);
					
					repaint();
				}
			}
			else if(loginScreen)//login a user
			{
				try
				{
					//get the information from the text field if parse completes there is no error
					int studentNumber = Integer.parseInt(searchBar.getText());
					error = false;
					errorText = "";
					
					//if user doesn't exist an error exists
					if(library.searchUser(studentNumber) == null)
					{
						error = true;
						errorText = "Student not found.";
						
						repaint();
					}
					else
					{
						//add the user to the userList and the books 
						//the user has checked out to the bookList
						userList.add(library.searchUser(studentNumber));
						bookList = userList.get(0).getBooksCheckedOut();
						
						//add and Remove GUI
						loginScreen = false;
						remove(searchBar);
						remove(next);
						
						optionScreen = true;
						add(checkIn);
						add(checkOut);
						add(payFine);
						add(home);
						
						//if they have no books out they cannot check in anything
						if(bookList.size() == 0)
						{
							checkIn.setVisible(false);
							canCheckIn = false;
						}
						else
						{
							checkIn.setVisible(true);
							canCheckIn = true;
						}
						
						//if they are not eligible they cannot check out a book
						if(bookList.size() == 3 || userList.get(0).getCurrentFine() > 5)
						{
							checkOut.setVisible(false);
							canCheckOut = false;
						}
						else
						{
							checkOut.setVisible(true);
							canCheckOut = true;
						}
						
						repaint();
					}
				}
				catch(Exception ex)
				{
					//if the information from the text field cannot be parsed
					error = true;
					errorText = "Enter student number as a integer.";
					
					repaint();
				}
			}
		}
		else if(e.getSource() == checkOut)//check out a book
		{
			//add and remove GUI
			optionScreen = false;
			remove(payFine);
			remove(checkIn);
			remove(checkOut);
			remove(home);
			
			checkOutScreen = true;
			add(titleText);
			add(authorText);
			add(confirm);
			
			repaint();
		}
		else if(e.getSource() == checkIn)//check in a book
		{
			//add and remove GUI
			optionScreen = false;
			remove(home);
			remove(checkIn);
			remove(checkOut);
			remove(payFine);
			
			checkInScreen = true;
			searchBar.setText("");
			add(searchBar);
			add(lost);
			add(confirm);
			
			if(bookList.size() == 1)//if the user only has one book out
			{
				//set book titles to check boxes 
				item1.setLabel(bookList.get(0).getTitle());
			}
			else if(bookList.size() == 2)//if the user has two books out
			{
				//set book titles to check boxes
				item1.setLabel(bookList.get(0).getTitle());
				item2.setLabel(bookList.get(1).getTitle());
			}
			else if(bookList.size() == 3)//if user has three books out
			{
				//set book titles to check boxes
				item1.setLabel(bookList.get(0).getTitle());
				item2.setLabel(bookList.get(1).getTitle());
				item3.setLabel(bookList.get(2).getTitle());
			}
			
			//add the check boxes only if they have a title set to them
			if(!item1.getLabel().equals(""))
			{
				add(item1);
			}
			if(!item2.getLabel().equals(""))
			{
				add(item2);
			}
			if(!item3.getLabel().equals(""))
			{
				add(item3);
			}
			
			repaint();
		}
		else if(e.getSource() == lost)
		{
			Book lostBook = null;
			//get selected book check in, add the cost to users fine, and then remove the book
			if(item1.getState()) 
			{
				lostBook = bookList.get(0);
				userList.get(0).addToFine(bookList.get(0).getCost());
				library.checkInBook(bookList.get(0), 0);
			}
			else if(item2.getState())
			{
				lostBook = bookList.get(1);
				userList.get(0).addToFine(bookList.get(1).getCost());
				library.checkInBook(bookList.get(1), 0);
			}
			else if(item3.getState())
			{
				lostBook = bookList.get(2);
				userList.get(0).addToFine(bookList.get(2).getCost());
				library.checkInBook(bookList.get(2), 0);
			}
			
			library.removeBook(lostBook);
			
			resultText = "Account charged for book.";
			
			checkInScreen = false;
			remove(item1);
			remove(item2);
			remove(item3);
			remove(searchBar);
			remove(confirm);
			remove(lost);
			
			resultsScreen = true;
			add(home);
			repaint();
		}
		else if(e.getSource() == payFine)// pay a fine
		{
			//add and remove GUI
			optionScreen = false;
			remove(payFine);
			remove(checkIn);
			remove(checkOut);
			remove(home);
			
			fineScreen = true;
			searchBar.setText("");
			add(searchBar);
			add(confirm);
			
			repaint();
		}
		else if(e.getSource() == confirm)//The confirm button
		{
			if(fineScreen)//confirm the amount being paid for the fine
			{
				try
				{
					Double amountPaid = Double.parseDouble(searchBar.getText());
					error = false;
					errorText = "";
					//if the information parses there is no error
					
					userList.get(0).payFine(amountPaid);
					//pay the fine for the user signed in
					
					resultText = "Fine paid!";
					//set the result text
					
					//add and remove GUI
					fineScreen = false;
					remove(searchBar);
					remove(confirm);
					
					resultsScreen = true;
					add(home);
					
					repaint();					
				}
				catch(Exception ex)
				{
					//if the information is not parsed there is an error
					error = true;
					errorText = "Enter a decimal number.";
					
					repaint();
				}
			}
			else if(checkInScreen)//confirm a book being checked in
			{
				try
				{
					int daysOut = Integer.parseInt(searchBar.getText());
					error = false;
					errorText = "";
					//if information is parsed there is an error
					
					//check in the selected book with the days it has been checked out
					if(item1.getState())
					{
						library.checkInBook(bookList.get(0), daysOut);
					}
					else if(item2.getState())
					{
						library.checkInBook(bookList.get(1), daysOut);
					}
					else if(item3.getState())
					{
						library.checkInBook(bookList.get(2), daysOut);
					}
					
					// set the result text
					resultText = "Book checked in!";
					if (daysOut > library.getMaxDaysOut())
					{
						resultText += " Book was overdue, fine added to account.";
					}
					
					//add and remove GUI
					checkInScreen = false;
					remove(item1);
					remove(item2);
					remove(item3);
					remove(searchBar);
					remove(confirm);
					remove(lost);
					
					resultsScreen = true;
					add(home);
					
					repaint();
				}
				catch(Exception ex)
				{
					//if information not parsed there will be an error
					error = true;
					errorText = "Days out is an integer.";
					repaint();
				}
			}
			else if(checkOutScreen)//confirm a check out
			{
				//get the information from the text fields
				String bookTitle = titleText.getText();
				String bookAuthor = authorText.getText();
				
				//put the entire catalog into the array
				ArrayList<Book> catalogue = library.getBookCatalogue();
				
				//create a new book object that will store the book if it is found
				Book book1 = null;
				
				//Search the catalog for a match in title and author and the book has to be in library
				for(int i = 0; i < catalogue.size(); i++)
				{
					if(catalogue.get(i).getAuthor().equalsIgnoreCase(bookAuthor) && 
							catalogue.get(i).getTitle().equalsIgnoreCase(bookTitle) && catalogue.get(i).isCheckedOut() == false)
					{
						//if a match is found set that book equal to book1
						book1 = catalogue.get(i);
						break;
					}
				}
				
				if(book1 == null)//if no match was found
				{
					resultText = "Book not available.";
				}
				else
				{
					//if a match is found
					library.checkOutBook(userList.get(0), book1);
					resultText = "Book checked out.";
				}
				
				//add and remove GUI
				checkOutScreen = false;
				remove(titleText);
				remove(authorText);
				remove(confirm);
				
				resultsScreen = true;
				add(home);
				
				repaint();
			}
		}
		else if(e.getSource() == removeConfirm)//confirm a removal of an object
		{
			if(removeUserScreen)//if removing a user
			{
				try
				{
					int studentNumber = Integer.parseInt(searchBar.getText());
					error = false;
					errorText = "";
					//if the information is parsed then there is no error
					
					//add and remove GUI
					removeUserScreen = false;
					remove(searchBar);
					remove(removeConfirm);
					
					removeResultsScreen = true;
					add(home);					
					
					//if no match is found a removal cannot be made
					if(library.searchUser(studentNumber) == null)
					{
						removed = false;
						
					}
					else
					{
						//if a match is found a removal can be made
						library.removeUser(library.searchUser(studentNumber));						
						removed = true;

					}
					
					repaint();
				}
				catch(Exception ex)
				{
					//if the information is not parsed there is an error
					error = true;
					errorText = "Enter student number as an Integer.";
					repaint();
				}
			}
			else if(removeBookScreen)//book removal
			{
				//get information from text fields
				String bookTitle = titleText.getText();
				String bookAuthor = authorText.getText();
				
				//get the catalogue to search and the new book if a match is found
				Book book1 = null;
				bookList = library.getBookCatalogue();
				
				//search the catalogue for a match by title and author and the book must be in the library
				for(int i = 0; i < bookList.size(); i++)
				{
					if(bookList.get(i).getAuthor().equalsIgnoreCase(bookAuthor) && 
							bookList.get(i).getTitle().equalsIgnoreCase(bookTitle) && bookList.get(i).isCheckedOut() == false)
					{
						//if a match is found set it equal to book1
						book1 = bookList.get(i);
						break;
					}
				}
				
				if(book1 == null)//if no match is found then no removal is made
				{
					removed = false;
				}
				else
					//if a match is found then a removal is made
				{
					library.removeBook(book1);
					removed = true;
				}
				
				//add and remove GUI
				remove(titleText);
				remove(authorText);
				remove(removeConfirm);
				removeBookScreen = false;
				
				removeResultsScreen = true;
				add(home);
				
				repaint();
			}
		}
		else if (e.getSource() == addConfirm) //confirm the addition of an object
		{
			if(addUserScreen)//adding a user
			{
				//get the information from the text fields
				String firstName = fname.getText();
				String lastName = lname.getText();
				
				try
				{
					int studentNumber = Integer.parseInt(stuNum.getText());
					
					if(firstName.equals("") || lastName.equals(""))
					{
						error = true;
						errorText = "Enter all info.";
						repaint();
						//there is an error if some info is missing
					}
					else
					{
						error = false;
						errorText = "";
						//the info is filled out and parsed there is no error
						
						if(library.addUser(new User(firstName, lastName, studentNumber)))//if the user is added 
						{
							added = true;
						}
						else
						{
							//if the user wasn't added
							added = false;
						}
						
						//add and remove GUI
						addUserScreen = false;
						addResultsScreen = true;
						
						remove(fname);
						remove(lname);
						remove(stuNum);
						remove(addConfirm);
						
						add(home);
						
						repaint();
					}
				}
				catch(Exception ex)
				{
					error = true;
					errorText = "Enter a number as the Student Number.";
					repaint();
					//if the information is not parsed
				}
			}
			else if(addBookScreen)
			{
				//get information from text fields
				String bookTitle = titleText.getText();
				String bookAuthor = authorText.getText();
				String bookCategory = categoryText.getText();
				
				try 
				{
					int bookCost = Integer.parseInt(costText.getText());
					long bookIsbn = Long.parseLong(isbnText.getText());
					double bookRating = Double.parseDouble(ratingText.getText());
					
					if(bookTitle.equals("") || bookAuthor.equals("") || bookCategory.equals(""))
					{
						error = true;
						errorText = "Enter all info.";
						repaint();
						// if some info is missing there is an error
					}
					else
					{
						error = false;
						errorText = "";
						//if information is filled out correctly there is no error
						
						if(library.addBook(new Book(bookTitle, bookAuthor, bookCategory, bookCost, bookIsbn, bookRating)))
						{
							//if book is added
							added = true;
						}
						else
						{
							//if book is not added
							added = false;
						}
						
						//add and remove GUI
						addBookScreen = false;
						remove(titleText);
						remove(authorText);
						remove(categoryText);
						remove(costText);
						remove(isbnText);
						remove(ratingText);
						remove(addConfirm);
						
						addResultsScreen = true;
						add(home);
						repaint();
					}
				}
				catch(Exception ex)
				{
					error = true;
					errorText = "Enter info in the appropriate format.";
					repaint();
					//if info is not parsed there will be an error
				}
			}
		}
		else if(e.getSource() == listUsers)// list the current users
		{
			error = false;
			errorText = "";
			//no error
			
			//add and remove GUI
			remove(searchBar);
			remove(searchConfirm);
			remove(listUsers);
			searchUserScreen = false;
		
			searchUserResultsScreen = true;
			add(home);
			found = true;
			
			//get the current users and print them
			userList = library.getUserList();

			repaint();
			
		}
		else if(e.getSource() == listBooks)// list the current books
		{
			error = false;
			errorText = "";
			//no error
			
			//add and remove GUI
			remove(searchBar);
			remove(searchConfirm);
			remove(listBooks);
			remove(author);
			remove(title);
			remove(category);
			searchBookScreen = false;
			
			searchBookResultsScreen = true;
			add(home);
			found = true;
			
			//get the books and print them
			bookList = library.getBookCatalogue();
			
			//add the compare option 
			if(bookList.size() > 1) add(compare);
			
			repaint();
		}
		else if(e.getSource() == searchConfirm) //confirm a search
		{
			if(searchUserScreen)//search a user
			{
				String stuNumString = searchBar.getText();
				
				try
				{
					int studentNumber = Integer.parseInt(stuNumString);
					error = false;
					errorText = "";
					//if the information is parsed then there is no error
					
					//add and remove GUI
					remove(searchBar);
					remove(searchConfirm);
					remove(listUsers);
					searchUserScreen = false;
				
					searchUserResultsScreen = true;
					add(home);
					
					if(library.searchUser(studentNumber) == null)//if no match is found 
					{
						found = false;
						repaint();
					}
					else
					{
						//if a match is found add to the list and print
						userList.add(library.searchUser(studentNumber));
						found = true;
						repaint();
					}
				}
				catch(Exception ex)
				{
					error = true;
					errorText = "Enter a number as the Student Number.";
					repaint();
					//if information is not parsed then there is no error
				}
			}
			else if(searchBookScreen)//search for a book
			{
				String searchText = searchBar.getText();
				
				if(searchText.equals(""))
				{
					//if info is missing then there is an error
					error = true;
					errorText = "Enter the info.";
					repaint();
				}
				else
				{
					error = false;
					errorText = "";
					//all info is there there is no error
					
					//add and remove GUI
					remove(searchBar);
					remove(searchConfirm);
					remove(listBooks);
					remove(author);
					remove(title);
					remove(category);
					searchBookScreen = false;
					
					searchBookResultsScreen = true;
					add(home);
					
					if(author.getState())//search by author
					{
						if(library.searchBookByAuthor(searchText) == null)
						{
							//no match found
							found = false;
							repaint();
						}
						else
						{
							//match found
							bookList = library.searchBookByAuthor(searchText);
							found = true;
						
							//if more than one match add the compare option
							if(bookList.size() > 1) add(compare);
						
							repaint();
						}
					}
					else if(title.getState())//search by title
					{
						if(library.searchBookByTitle(searchText) == null)
						{
							//no match found
							found = false;
							repaint();
						}
						else
						{
							//match found
							bookList = library.searchBookByTitle(searchText);
							found = true;

							//if more than one match add the compare option
							if(bookList.size() > 1) add(compare);
						
							repaint();
						}
					}
					else if(category.getState())//search by category
					{
						if(library.searchBookByCategory(searchText) == null)
						{
							//no match found
							found = false;
							repaint();
						}
						else
						{
							//match found
							bookList = library.searchBookByCategory(searchText);
							found = true;

							//if more than one match add the compare option
							if(bookList.size() > 1) add(compare);
						
							repaint();
						}
					}
				}
			}
		}
		else if(e.getSource() == home)//the home button
		{
			//this button resets all of the GUI objects and lists used program 
			//and send the user back the mainMenuScreen
			if(searchUserResultsScreen)
			{
				searchUserResultsScreen = false;
				
				searchBar.setText("");
				
			}
			else if(searchBookResultsScreen)
			{
				searchBookResultsScreen = false;
				
				searchBar.setText("");
				
				remove(compare);
				comparison = false;
			}
			else if(addResultsScreen)
			{
				addResultsScreen = false;
				
				fname.setText("");
				lname.setText("");
				stuNum.setText("");
				
				titleText.setText("");
				authorText.setText("");
				categoryText.setText("");
				costText.setText("");
				isbnText.setText("");
				ratingText.setText("");
			}
			else if(removeResultsScreen)
			{
				searchBar.setText("");
				removeResultsScreen = false;
				userList = new ArrayList<User>(library.getNumUsers());
				bookList = new ArrayList<Book>(library.getNumBooks());

				titleText.setText("");
				authorText.setText("");
			}
			else if(optionScreen)
			{
				optionScreen = false;
				searchBar.setText("");
				
				remove(checkIn);
				remove(checkOut);
				remove(payFine);
			}
			else if(resultsScreen)
			{
				resultsScreen = false;
				searchBar.setText("");
				item1.setLabel("");
				item2.setLabel("");
				item3.setLabel("");
				titleText.setText("");
				authorText.setText("");
				resultText = "";
			}
			
			userList = new ArrayList<User>(library.getNumUsers());
			bookList = new ArrayList<Book>(library.getNumBooks());
			
			
			mainScreen = true;
			remove(home);
			error = false;
			errorText = "";
						
			add(mainMenuSearch);
			add(loginUser);
			add(addItem);
			add(removeItem);
			
			repaint();
		}
		else if(e.getSource() == compare)//compare multiple books
		{
			//find the highest rated book and then draw the results on the screen
			highestRated = bookList.get(0);
			
			for(int i = 1; i < bookList.size(); i++)
			{
				if(highestRated.getStarRating() < bookList.get(i).getStarRating()) 
				{
					highestRated = bookList.get(i);
				}
			}
			
			comparison = true;
			remove(compare);
			repaint();
		}
	}
}
