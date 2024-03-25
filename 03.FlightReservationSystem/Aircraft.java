/*
 * This class models an aircraft type with a model name, a maximum number of economy seats, and a max number of forst class seats 
 * 
 * Aircraft implements the Comparable interface by first comparing the number of economy seats. 
 * If the number is equal, then compare the number of first class seats 
 */
public class Aircraft  implements Comparable<Aircraft>
{
  String model;
  int numEconomySeats;
  int numFirstClassSeats;
  String[][] seatLayout;  
  
  public Aircraft(int seats, String model)
  {
  	this.numEconomySeats = seats;
  	this.numFirstClassSeats = 0;
  	this.model = model;
  }

  public Aircraft(int economy, int firstClass, String model)
  {
  	this.numEconomySeats = economy;
  	this.numFirstClassSeats = firstClass;
  	this.model = model;
  }
  // Copy constructor
  public Aircraft(Aircraft aircraft) {
	  this.model = aircraft.model;
	  this.numFirstClassSeats = aircraft.numFirstClassSeats;
	  this.numEconomySeats = aircraft.numEconomySeats;
	  this.seatLayout = aircraft.seatLayout;
  }
  
	public int getNumSeats()
	{
		return numEconomySeats;
	}
	
	public int getTotalSeats()
	{
		return numEconomySeats + numFirstClassSeats;
	}
	
	public int getNumFirstClassSeats()
	{
		return numFirstClassSeats;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}
	
	public void setSeatConfig(int rows, int cols)
	{
		this.seatLayout = new String[rows][cols];
		String[] columnNames = new String[] {"A","B","C","D"};
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < 4; c++) {
				this.seatLayout[r][c] = String.valueOf(r+1) + columnNames[c];
			}
		}
	}
	
	public void print()
	{
		System.out.println("Model: " + model + "\t Economy Seats: " + numEconomySeats + "\t First Class Seats: " + numFirstClassSeats);
	}

	/*
	 * Write a compareTo method that is part of the Comparable interface
	 */
    @Override
    public int compareTo(Aircraft otherAircraft) {    
    	return this.numEconomySeats - otherAircraft.numEconomySeats;
    }
	
}
  
