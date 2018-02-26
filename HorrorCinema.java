import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HorrorCinema {
	//global variables
	String[] listOfFilms = null;
	String[] listOfOrders = null;
	Double ticketPrice = 9.60;
	
	public static void main (String[] args){
		
		new HorrorCinema();
		
	}
	
	public HorrorCinema(){//default constructor
		
		//Greeting message shown to the user along with the board with prices and discounts
		welcomeMessage();
		//Method listing the films available
		listFilms();
		//Method to call the menu for the user
		mainMenu();	
		
	}
	
	public void mainMenu(){ //This method will display the main menu to the user
		
		String option = "";
		
		while(!option.equals("y")){
			
			System.out.println("--------------------------- MENU ---------------------------");
			System.out.println("1 - Creat a new order");
			System.out.println("2 - List of films");
			System.out.println("3 - View previous orders");
			System.out.println("0 - Exit");
			System.out.println("------------------------------------------------------------");
			System.out.println("");
			System.out.print("Please choose an option:");
			
			option = getInput();
		

			if (option.equals("1")){
				createNewOrder();
			}else if (option.equals("2")){
				listFilms();
			}else if (option.equals("3")){
				viewPreviousOrders();
			}else if (option.equals("0")){
				System.out.println("Are you sure you want to quit? Type 'y' for yes or 'n' for no");
				option = getInput();
				}
		}

	}
		
	
	public void welcomeMessage(){
		
		System.out.println("Greetings human, Welcome to the Horror Cinema! Are you brave enough to enter?!");
		System.out.println("Spooky prices... Spooky fun!");
		System.out.println("");
		System.out.println("-------------------------- PRICES --------------------------");
		System.out.println("Under 12 years old -> 40% discount");
		System.out.println("13 years old -> 13 is the spooky number, you go in for FREE!");
		System.out.println("14 to 64 years old -> Regular price: €9.60");
		System.out.println("Over 65 years old -> 20% discount");
		System.out.println("------------------------------------------------------------");
		System.out.println("");
		
	}
	
	public void createNewOrder(){ //This method displays a list of the movies and allows the user to create a new order
		
		listFilms();//Calling method to list all the movies
		int Option = 0;
		int ticketsQuantity = 0;
		int age = 0;
		boolean correct = false;	

		while(!correct){
			
			System.out.println("What film would you like to watch?");
			
			try{
			
				Option = Integer.parseInt(getInput()) - 1;
				
				if (Option > 12){
					
					System.out.println("Please choose a valid option!");
					
				}else {correct = true;} //breaks the loop when the user type the correct input
		
			} catch (NumberFormatException nfe) {
			
				System.out.println("");
				System.out.println("Please choose an option. Only numbers accepted!");
				System.out.println("");
			
			}
		}
		
		correct = false;
		
		while(!correct){
		
			System.out.println("How many tickets do you want?");
			
			try{
		
				ticketsQuantity = Integer.parseInt(getInput());
				correct = true; //breaks the loop when the user type the correct input
		
			} catch (NumberFormatException nfe) {
			
				System.out.println("");
				System.out.println("Error: Only numbers accepted!");
				System.out.println("");
				
			}
		}
		
		correct = false;
		
		while(!correct){
		
			System.out.println("How old are you?");
			
			try{
		
				age = Integer.parseInt(getInput());
				correct = true; //breaks the loop when the user type the correct input
		
			} catch (NumberFormatException nfe) {
			
				System.out.println("");
				System.out.println("Only numbers accepted!");
				System.out.println("");
				
			}
		}
		
		System.out.println("");
		
		if (ticketsQuantity == 1){//This if will make sure that when only one ticket is bought, it will print out "ticket" in the singular form

			System.out.println("You are buying " + ticketsQuantity +" ticket for the film number " + listOfFilms[Option]);
			
		}else {
		
			System.out.println("You are buying " + ticketsQuantity +" tickets for the film number " + listOfFilms[Option]);
		
		}
		
		System.out.println("The total price is: €" + calcPriceOfTickets(ticketPrice, ticketsQuantity, age));
		System.out.println("");
		writeToFile(listOfFilms[Option], ticketsQuantity, age, calcPriceOfTickets(ticketPrice, ticketsQuantity, age)); //Here the last order will be saved into a file
	}
	
	public void listFilms(){//When called this method display the list of films available so the user doesn't need to create an order to see the list of films
		
		System.out.println("");
		listOfFilms = readFile("C:\\Users\\ThieresLuiz\\workspace\\HorrorCinema\\films.txt");
		
	}
	
	public void viewPreviousOrders(){//This method will display previous orders made
		
		System.out.println("");
		System.out.println("----------------- VIEWING PREVIOUS ORDERS ------------------");
		listOfOrders = readListOfOrders("C:\\Users\\ThieresLuiz\\workspace\\HorrorCinema\\orders.txt");
		System.out.println("------------------------------------------------------------");
		System.out.println("");
		
	}
	
	public String getInput(){//Method used to get the user's input
		
		String input = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try{
		
			input = br.readLine();
			
		}catch(IOException ioe){}
		
	return input;

	}
	
	public String[] readListOfOrders(String path){
		
		String[] list = new String[50];
		
			try{
			
				int i = 0;
				BufferedReader br = new BufferedReader(new FileReader(path));
				String line = br.readLine();
			
				while(line != null){
				
					System.out.println(line);
					list[i++] = line;
					line = br.readLine();
					System.out.println(line);
					list[i++] = line;
					line = br.readLine();
					System.out.println(line);
					list[i++] = line;
					line = br.readLine();
					System.out.println(line);
					list[i++] = line;
					line = br.readLine();
					System.out.println(line);
					System.out.println("------------------------------------------------------------");
					line = br.readLine();
				}
			
				br.close();
				System.out.println("");
			
			}catch(FileNotFoundException fnfe){
			
				System.out.println("");
				System.out.println("File not found. Please make sure you provided the correct path or that the file exists.");
				System.out.println("");
			
			}catch(IOException ioe) {
			
				System.out.println("");
				System.out.println("File not found. Please make sure you provided the correct path or that the file exists.");
				System.out.println("");
			
			}
	
			return list;
		
	}
	
	public String[] readFile (String path){//Method used to read a ".txt" file
		
		String[] list = new String[50];
		
		try{
			
			int i = 0;
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = br.readLine();
			
			while(line != null){
				
				System.out.println(line);
				list[i] = line;
				line = br.readLine();
				i++;
		
			}
			
		br.close();
		System.out.println("");
			
		}catch(FileNotFoundException fnfe){
			
			System.out.println("");
			System.out.println("File not found. Please make sure you provided the correct path or that the file exists.");
			System.out.println("");
			
		}catch(IOException ioe) {
			
			System.out.println("");
			System.out.println("File not found. Please make sure you provided the correct path or that the file exists.");
			System.out.println("");
			
		}
	
	return list;
		
	}
	
	public void writeToFile (String film, int quantTickets, int age, double totalPrice){//Method used to save the orders into a ".txt" file
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); //I googled this up because I wanted to print the date and time of the purchase of tickets
		Calendar cal = Calendar.getInstance();
		   
		try{
			
			PrintWriter writer = new PrintWriter(new FileWriter("orders.txt", true));
			writer.println("Date: " + dateFormat.format(cal.getTime()));
		    writer.println("Film: " + film);
			writer.println("No. of Tickets: " + quantTickets);
			writer.println("Age: " + age);
			writer.println("Total Price: " + totalPrice);
			writer.close();
				
		}catch(IOException ioe) {
			
			System.out.println("");
			System.out.println("The application wasn't able to save to file.");
			System.out.println("");
			
		}
		
	}
	
	public double calcPriceOfTickets(double price, int quantity, int age){//Method to calculate the discounts on the price of the ticket
		
		if (age <= 12){//If the user is 12 years old or younger they will get 40% discount over the total price of the tickets
			
			price = (price - (price*0.4)) * quantity;

		}else if (age == 13){//As a special promotion of the Horror Cinema users that are 13 years old will get in for FREE
			
			price = (price - price) * quantity;
			System.out.println("13 is the spooky number. FREE TICKETS FOR YOU!");
			
		}else if (age >=14 && age <=64){//If the user is between 14 and 64 years old they get no discounts
			
			price = roundDouble((price * quantity), 2);
			
		}else if (age >= 65){//If the user is 65 years old or older they will get 20% discount
			
			price = (price - (price*0.2)) * quantity;
			
		}
		
		return price;
		
	}
	
	public double roundDouble(double value, int numberOfDecimalPlaces) {//Method that will round double values to 2 decimal places
		
	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(numberOfDecimalPlaces, RoundingMode.HALF_UP);
	    
	return bd.doubleValue();
	    
	}

}
