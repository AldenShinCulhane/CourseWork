/* 
 *  Class to model an airline flight. In this simple system, all flights originate from Toronto
 *  
 *  This class models a simple flight that has only economy seats
 */

import java.util.ArrayList;
import java.util.TreeMap;


class DuplicatePassengerException extends Exception {
	public DuplicatePassengerException(String message) {
		super(message);
	}
}

class SeatOccupiedException extends Exception {
	public SeatOccupiedException(String message) {
		super(message);
	}
}

class FlightNotFoundException extends Exception {
	public FlightNotFoundException(String message) {
		super(message);
	}
}


public class Flight
{
	public static enum Status {DELAYED, ONTIME, ARRIVED, INFLIGHT};
	public static enum FlightType {SHORTHAUL, MEDIUMHAUL, LONGHAUL};

	String flightNum;
	String airline;
	String origin, dest;
	String departureTime;
	Status status;
	int flightDuration;
	Aircraft aircraft;
	protected int passengers;
	protected ArrayList<Passenger> manifest;
	protected TreeMap<String, Passenger> seatMap;
  
	public Flight()
	{
		this.flightNum = "AC999";
		this.airline = "Air Canada";
		this.dest = "New York";
		this.origin = "Toronto";
		this.departureTime = "2300";
		this.flightDuration = 1;
		this.aircraft = new Aircraft(180, "Airbus 320");
		this.passengers = 0;
		this.status = Status.ONTIME;
	}
	
	public Flight(String flightNum, String airline, String dest, String departure, int flightDuration, Aircraft aircraft)
	{
		this.flightNum = flightNum;
		this.airline = airline;
		this.dest = dest;
		this.origin = "Toronto";
		this.departureTime = departure;
		this.flightDuration = flightDuration;
		this.aircraft = new Aircraft(aircraft);
		this.passengers = 0;
		this.status = Status.ONTIME;
		this.manifest = new ArrayList<Passenger>();
		this.seatMap = new TreeMap<String, Passenger>();
	}
	
	public FlightType getFlightType()
	{
		return FlightType.MEDIUMHAUL;
	}
	public void reserveSeat(Passenger p, String seat) throws SeatOccupiedException, DuplicatePassengerException {
		
		if (this.seatMap.containsKey(seat)) {
			throw new SeatOccupiedException("Seat " + seat.toUpperCase() + " already occupied");
		}
		
		if (this.manifest.contains(p)) {
			throw new DuplicatePassengerException("Duplicate Passenger " + p.getName() + " " + p.getPassport());	
		}
		
		this.manifest.add(p);
		this.seatMap.put(seat, p);		
	}
	public void cancelSeat(Passenger p) throws DuplicatePassengerException {
		if (!this.manifest.contains(p)) {
			throw new DuplicatePassengerException("passenger " + p.getName() + " not found");	
		}
		
		this.seatMap.remove(p.getSeat());
		this.manifest.remove(p);
	}
	public void printPassengerManifest() {
		for (int i = 0; i < this.manifest.size(); ++i) {
			Passenger p = this.manifest.get(i);
			System.out.println(p.getName() + " " + p.getPassport() + " " + p.getSeat());			
		}

	}
	public void printSeats() {
		int rows = this.aircraft.seatLayout.length;
		int cols = this.aircraft.seatLayout[0].length;
		System.out.println();
		for (int c = 0; c < cols; ++c) {
			for (int r = 0; r < rows; r++) {
				String seatName = this.aircraft.seatLayout[r][c];
				if (this.seatMap.containsKey(seatName)) {
					seatName = "XX";
				}
				System.out.print(seatName + " ");
			}
			System.out.println();
			if (c == 1) {
				System.out.println();
			}
		}	
		System.out.println("\nXX = Occupied    + = First Class");
	}
	public String getFlightNum()
	{
		return flightNum;
	}
	public void setFlightNum(String flightNum)
	{
		this.flightNum = flightNum;
	}
	public String getAirline()
	{
		return airline;
	}
	public void setAirline(String airline)
	{
		this.airline = airline;
	}
	public String getOrigin()
	{
		return origin;
	}
	public void setOrigin(String origin)
	{
		this.origin = origin;
	}
	public String getDest()
	{
		return dest;
	}
	public void setDest(String dest)
	{
		this.dest = dest;
	}
	public String getDepartureTime()
	{
		return departureTime;
	}
	public void setDepartureTime(String departureTime)
	{
		this.departureTime = departureTime;
	}
	
	public Status getStatus()
	{
		return status;
	}
	public void setStatus(Status status)
	{
		this.status = status;
	}
	public int getFlightDuration()
	{
		return flightDuration;
	}
	public void setFlightDuration(int dur)
	{
		this.flightDuration = dur;
	}
	
	public int getPassengers()
	{
		return passengers;
	}
	public void setPassengers(int passengers)
	{
		this.passengers = passengers;
	}
	
	// Check to see if there is room on this flight - compare current passenger count
	// with aircraft max capacity of economy seats
	public boolean seatsAvailable()
	{
		return passengers < aircraft.numEconomySeats;
	}
	
	/*
	 * Cancel a seat - essentially reduce the passenger count by 1. Make sure the count does not
	 * fall below 0 (see instance variable passenger)
	 */
//	public void cancelSeat()
//	{
//		if (passengers > 0) {
//			--passengers;
//		}
//	}
	
	/*
	 * reserve a seat on this flight - essentially increases the passenger count by 1 only if there is room for more
	 * economy passengers on the aircraft used for this flight (see instance variables above)
	 */
	public boolean reserveSeat()
	{
		int capacity = aircraft.numEconomySeats; 
		if (passengers < capacity) {
			++passengers;
			return true;
		}
		return false;
	}
	
	public String toString()
	{
		return airline + "\t Flight:  " + flightNum + "\t Dest: " + dest + "\t Departing: " + departureTime + "\t Duration: " + flightDuration + "\t Status: " + status;
	}
  
}
