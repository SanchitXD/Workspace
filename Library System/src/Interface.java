import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Interface extends JFrame implements ActionListener
{
	final int SHELF_SPACE;
	final int SCHOOL_SIZE;
	
	Button mainMenuSearch;
	Button loginUser;
	
	public Interface()
	{
		super("Library System");
		
		setLayout(null);
		
		setSize(400, 400);
		
		SHELF_SPACE = Integer.parseInt(JOptionPane.showInputDialog("How big is the library?"));
		SCHOOL_SIZE = Integer.parseInt(JOptionPane.showInputDialog("How many students are in the school?"));
		
		mainMenuSearch = new Button("Search Book/User");
		loginUser = new Button("Login User");
		
		mainMenuSearch.setBounds(145, 20, 110, 25);
		loginUser.setBounds(150, 50, 100, 25);
		
		mainMenuSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//move to the search screen
				//add and remove GUI
				//repaint
			}
		});
		
		loginUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		})
		
		add(mainMenuSearch);
		add(loginUser);
		
		show();
	}
	
	@Override
	public void paint(Graphics g) 
	{
		// TODO Auto-generated method stub
	
	}
	
	public static void main(String[] args)
	{
		new Interface();
	}
}
