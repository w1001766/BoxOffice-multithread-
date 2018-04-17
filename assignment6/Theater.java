// insert header here
package assignment6;

import java.util.ArrayList;
import java.util.List;

public class Theater extends Thread{ 
	/*
	 * Represents a seat in the theater
	 * A1, A2, A3, ... B1, B2, B3 ...
	 */
	public void run() {
		
	}
	static class Seat {
		private int rowNum;
		private int seatNum;
		private boolean reserved;
		String finalSeat;
		public Seat(int rowNum, int seatNum) {
			this.rowNum = rowNum;
			this.seatNum = seatNum;
			this.reserved = false;
		}

		public int getSeatNum() {
			return seatNum;
		}

		public int getRowNum() {
			return rowNum;
		}
		public void reserve() {
			this.reserved = true;
		}
		public boolean reserveOrNot() {
			return reserved;
		}

		@Override
		public String toString() {
			int level = rowNum/26;
			int Remain = rowNum%26;
			int firstInt = (rowNum - Remain)/26;
			
			char RowFirst = (char) (firstInt + 'A');
			char RowSecond = (char)(Remain + 'A');
			String Row = "";
			if(firstInt != 0) {
				RowFirst--;
				Row += RowFirst;
			}
			Row += RowSecond;
			seatNum++;
			Row += seatNum;
			return Row;
			// TODO: Implement this method to return the full Seat location ex: A1
		}
	}

  /*
	 * Represents a ticket purchased by a client
	 */
	static class Ticket {
		private String show;
		private String boxOfficeId;
		private Seat seat;
		private int client;

		public Ticket(String show, String boxOfficeId, Seat seat, int client) {
			this.show = show;
			this.boxOfficeId = boxOfficeId;
			this.seat = seat;
			this.client = client;
		}

		public Seat getSeat() {
			return seat;
		}

		public String getShow() {
			return show;
		}

		public String getBoxOfficeId() {
			return boxOfficeId;
		}

		public int getClient() {
			return client;
		}

		@Override
		public String toString() {
			String ClientString = "";
			ClientString+=client;
			String ticket = "";
			for(int i=0;i<31;i++) {
				ticket +="-";
			}
			ticket += "\n";
			ticket += "| ";
			ticket += "Show: ";
			ticket += show;
			for(int j = 31-show.length()-6-1-1-1;j>0;j--) {
				ticket += " ";
			}
			ticket += "|";
			ticket += "\n";
			ticket += "| ";
			ticket += "Box Office ID: ";
			ticket += boxOfficeId;
			for(int i=0;i<13-boxOfficeId.length();i++) {
				ticket += " ";
			}
			
			ticket += "|";
			ticket += "\n";
			ticket += "| ";
			ticket += "Seat: ";
			ticket += seat;
			for(int i=0;i<23-seat.toString().length();i++) {
				ticket += " ";
			}
			ticket += "|";
			ticket += "\n";
			ticket += "| ";
			ticket += "Client: ";
			ticket += client;
			for(int i=0;i<20-ClientString.length();i++) {
				ticket += " ";
			}
			ticket += "|";
			ticket += "\n";
			for(int i=0;i<31;i++) {
				ticket += "-";
			}
			//System.out.println(ticket);
			return ticket;
			// TODO: Implement this method to return a string that resembles a ticket
			
			
		}
	}
	private int numRows;
	private int seatsPerRow;
	private String show;
	private ArrayList<Ticket> ticketList;
	private ArrayList<Seat> seatList;
	public Theater(int numRows, int seatsPerRow, String show) {
		// TODO: Implement this constructor
		this.ticketList = new ArrayList<Ticket>();
		this.seatList = new ArrayList<Seat>();
		this.numRows = numRows;
		this.seatsPerRow = seatsPerRow;
		this.show = show;
		for(int i =0 ;i<numRows;i ++) {
			for(int j = 0;j<seatsPerRow;j++) {
				this.seatList.add(new Theater.Seat(i,j));
			}
		}
		
	}
	
	/*
	 * Calculates the best seat not yet reserved
	 *
 	 * @return the best seat or null if theater is full
   */
	
	public Seat bestAvailableSeat() {
		for(Seat s: seatList) {
			if(!s.reserveOrNot()) {
				return s;
			}
		}
		return null;
		//TODO: Implement this method
	}

	/*
	 * Prints a ticket for the client after they reserve a seat
   * Also prints the ticket to the console
	 *
   * @param seat a particular seat in the theater
   * @return a ticket or null if a box office failed to reserve the seat
   */
	public synchronized Ticket printTicket(String boxOfficeId, Seat seat, int client) {
		if(seat.reserveOrNot()) {
			return null;
		}
		seat.reserve();
		Ticket ticket = new Ticket(show, boxOfficeId, seat, client);
		this.ticketList.add(ticket);
		System.out.println(ticket);
		return ticket;
		//TODO: Implement this method
	}

	/*
	 * Lists all tickets sold for this theater in order of purchase
	 *
   * @return list of tickets sold
   */
	public List<Ticket> getTransactionLog() {
		return ticketList;
		//TODO: Implement this method
	}
}
