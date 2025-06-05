import java.util.ArrayList;
import java.util.Scanner;

public class FlightReservationSystem
{
	public static void main(String[] args)
	{
		// Create a FlightManager object
		FlightManager manager = new FlightManager();

		// List of reservations that have been made
		ArrayList<Reservation> myReservations = new ArrayList<Reservation>();	// my flight reservations

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		while (scanner.hasNextLine())
		{
			String inputLine = scanner.nextLine();
			if (inputLine == null || inputLine.equals("")) continue;

			// The command line is a scanner that scans the inputLine string
			// For example: list AC201
			Scanner commandLine = new Scanner(inputLine);
			
			// The action string is the command to be performed (e.g. list, cancel etc)
			String action = commandLine.next();

			if (action == null || action.equals("")) continue;

			if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			
			// List all flights
			else if (action.equalsIgnoreCase("LIST"))
			{
				manager.printAllFlights(); 
			}
			// List all seats (example input: seats AC220)
			else if (action.equalsIgnoreCase("SEATS"))
			{
				String flightNum = getFlightNumFromCommandLine(commandLine);
				if (flightNum == null) {
					System.out.println("Usage: seats flightnum");
					continue;
				}
				
				Flight flight = manager.getFlightWithNumber(flightNum);
				if (flight == null) {
					System.out.println("Flight " + flightNum + " not found." );
					continue;
				}
				
				flight.printSeats();												
			}
			else if (action.equalsIgnoreCase("PASMAN"))
			{
				String flightNum = getFlightNumFromCommandLine(commandLine);
				if (flightNum == null) {
					System.out.println("Usage: passman flightnum");
					continue;
				}
				
				Flight flight = manager.getFlightWithNumber(flightNum);
				if (flight == null) {
					System.out.println("Flight " + flightNum + " not found." );
					continue;
				}
				
				flight.printPassengerManifest();												
			}
			// Reserve a flight based on Flight number string (example input: res AC220)
			else if (action.equalsIgnoreCase("RES"))
			{
				ArrayList<String> cmdlineArgs = getArgsFromCommandLine(commandLine, 4);
				if (cmdlineArgs == null) {
					System.out.println("Usage: res flight name passport seat");
					continue;					
				}

				String flightNum = cmdlineArgs.get(0);				
				String name = cmdlineArgs.get(1);
				String passport = cmdlineArgs.get(2);
				String seat = cmdlineArgs.get(3);
				
				Passenger p = new Passenger(name, passport, seat, LongHaulFlight.economy);	
				try {
					Reservation res = manager.reserveSeatOnFlight(flightNum, p);
					myReservations.add(res);
					res.print();

				} catch (SeatOccupiedException | DuplicatePassengerException e) {
					System.out.println(e.getLocalizedMessage());
					continue;
				}
				
								
			}
		  // Reserve a first class seat on a flight based on Flight number string (example input: res AC220)
			else if (action.equalsIgnoreCase("RESFCL"))
			{
/*
				String flightNum = getFlightNumFromCommandLine(commandLine);
				if (flightNum == null) {
					continue;
				}
				
				Reservation res = manager.reserveSeatOnFlight(flightNum, LongHaulFlight.firstClass);
				if (res == null)  {
					System.out.println(manager.getErrorMessage());
					continue;
				}
				
				myReservations.add(res);
				res.print();
*/				
				// Same as above except call 
				// manager.reserveSeatOnFlight() and pass in the flight number string as well as the string constant:
				// LongHaulFlight.firstClass (see class LongHaulFlight)
				// Do the LongHaulFlight class and this command after all the basic functionality is working for regular flights
			}
			// Query the flight manager to see if seats are still available for a specific flight (example input: seats AC220)
		  // This one is done for you as a guide for other commands
/*
			else if (action.equalsIgnoreCase("SEATS"))
			{
				String flightNum = null;

				if (commandLine.hasNext())
				{
					flightNum = commandLine.next();

					if (manager.seatsAvailable(flightNum))
					{
						System.out.println("Seats are available");
					}
					else
					{
						System.out.println(manager.getErrorMessage());
					}
				}
			}
			*/
			// Cancel an existing reservation (example input: cancel AC220 Tim DD2345) 
			else if (action.equalsIgnoreCase("CANCEL"))
			{
				ArrayList<String> cmdlineArgs = getArgsFromCommandLine(commandLine, 3);
				if (cmdlineArgs == null) {
					System.out.println("Usage: cancel flight name passport");
					continue;					
				}

				String flightNum = cmdlineArgs.get(0);				
				String name = cmdlineArgs.get(1);
				String passport = cmdlineArgs.get(2);
				
				Passenger p = new Passenger(name, passport, "", LongHaulFlight.economy);								
				Reservation res = new Reservation(flightNum, "", p);
				Boolean found = false;
				for (int i = 0; i < myReservations.size(); i++) {
					// if (myReservations.get(i).flightNum.equalsIgnoreCase(flightNum)) {
					if (res.equals(myReservations.get(i))) {
						res = myReservations.get(i);
						myReservations.remove(i);
						found = true;
						break;
					}
				}
				if (!found) {
					System.out.println("not found");
					continue;
				}
				
				try {
					manager.cancelReservation(res);								
				} catch (DuplicatePassengerException e) {
					System.out.println(e.getLocalizedMessage());
				}

			}
			// Print all the reservations in array list myReservations
			else if (action.equalsIgnoreCase("MYRES"))
			{
				for (int i = 0; i < myReservations.size(); ++i) {
					myReservations.get(i).print();
				}
				
			}
			// Print the list of aircraft (see class Manager)
			else if (action.equalsIgnoreCase("CRAFT"))
		    {
				manager.printAllAircraft();
			}
			// These commands can be left until we study Java interfaces
			// Feel free to implement the code in class Manager if you already understand interface Comparable
			// and interface Comparator
			else if (action.equalsIgnoreCase("SORTCRAFT"))
		    {
				manager.sortAircraft();
		    }
			else if (action.equalsIgnoreCase("SORTBYDEP"))
			{
				manager.sortByDeparture();			  
			}
			else if (action.equalsIgnoreCase("SORTBYDUR"))
			{
				manager.sortByDuration();
			}
	  
			System.out.print("\n>");
		}
	}
	
	private static String getFlightNumFromCommandLine(Scanner commandLine) {

		if (commandLine.hasNext()) {
			return commandLine.next();
		}
		return null;		
	}
	
	private static ArrayList<String> getArgsFromCommandLine(Scanner commandLine, int numArgs) {

		ArrayList<String> args = new ArrayList<String>();
		while (args.size() < numArgs) {
			if (commandLine.hasNext()) {
				args.add(commandLine.next());
			} else {
				return null;		
			}
		}
		return args;		
	}

	
}

