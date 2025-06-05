/*
 * A simple class to model an electronic airline flight reservation
 * 
 */
public class Reservation
{
	String flightNum;
	String flightInfo;
	boolean firstClass;
	String passengerName;
	String passengerPassport;
	String seat;
	
	
	public Reservation(String flightNum, String info, Passenger p)
	{
		this.flightNum = flightNum;
		this.flightInfo = info;
		this.firstClass = false;
		this.passengerName = p.getName();
		this.passengerPassport = p.getPassport();
		this.seat = p.getSeat();
	}
	
	public boolean isFirstClass()
	{
		return firstClass;
	}

	public void setFirstClass()
	{
		this.firstClass = true;
	}

	public String getFlightNum()
	{
		return flightNum;
	}
	
	public String getPassengerName()
	{
		return this.passengerName;
	}
	public String getPassengerPassport()
	{
		return this.passengerPassport;
	}
	public String getSeat()
	{
		return this.seat;
	}

	public String getFlightInfo()
	{
		return flightInfo;
	}

	public void setFlightInfo(String flightInfo)
	{
		this.flightInfo = flightInfo;
	}
	
	@Override
	public boolean equals(Object obj) {
		Reservation res = (Reservation) obj;
		return  this.flightNum.equals(res.flightNum) &&
				this.passengerName.equals(res.passengerName) &&
				this.passengerPassport.equals(res.passengerPassport); 
	}

	public void print()
	{
		System.out.println(flightInfo);
		if (isFirstClass()) {
			System.out.println("\tFCL");
		}
	}
}
