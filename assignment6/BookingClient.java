// Insert header here
package assignment6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import assignment6.Theater.Seat;

import java.lang.Thread;

public class BookingClient {
	private Map<String,Integer>office;
	private Theater theater;
	ArrayList<Office> officeList;
	ArrayList<String> officeNames;
  /*
   * @param office maps box office id to number of customers in line
   * @param theater the theater where the show is playing
   */
  public BookingClient(Map<String, Integer> office, Theater theater) {
    // TODO: Implement this constructor
	  this.officeList = new ArrayList<Office>();
	  this.officeNames = new ArrayList<String>();
	  this.office = office;
	  this.theater = theater;
	  ArrayList<String> temp1 = new ArrayList<String>(office.keySet());
	  
	  this.officeNames = temp1;
	  for(String name: officeNames) {
		  this.officeList.add(new Office(name,office.get(name),theater));
	  }
	  
  }

  /*
   * Starts the box office simulation by creating (and starting) threads
   * for each box office to sell tickets for the given theater
   *
   * @return list of threads used in the simulation,
   *         should have as many threads as there are box offices
   */
	public List <Thread> simulate() {
		ArrayList<Thread> allThread = new ArrayList<Thread>();
		/*
		Thread t1 = officeList.get(0);
		t1.start();
		*/
		for(Thread eachThread: officeList) {
			allThread.add(eachThread);
			eachThread.start();
		}
		for(Thread thr: allThread) {
			try {
				thr.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return allThread;
		//TODO: Implement this method
		
	}
	public static void main(String[] args) {
		Theater movie = new Theater (3,5,"Avengers");
		Map<String,Integer> office = new HashMap<String, Integer>();
		office.put("Bx1", 3);
		office.put("Bx2", 3);
		office.put("Bx3", 2);
		office.put("Bx4", 1);
		office.put("Bx15", 4);

		BookingClient show = new BookingClient(office,movie);
		show.simulate();
		
		
	}
}
class Office extends Thread{
	private String name;
	private int client;
	private static int totalclient;
	private static Theater theater;
	private static boolean soldout = false;
	
	public Office(String name, int client, Theater theater) {
		this.name = name;
		this.client = client;
		this.theater = theater;
		totalclient += client;
	}
	public void run() {

			while(client>0) {
				synchronized(theater) {
					Seat nextSeat = theater.bestAvailableSeat();
					if(nextSeat != null) {
						theater.printTicket(name, nextSeat, totalclient);
						client--;
						totalclient--;
						
					}
					else {
						if(!soldout) {
							soldout=true;
							System.out.println("Sorry, we are sold out!");
						}
						
					}
				}
				try {
					Thread.sleep(0,1);
				} catch (InterruptedException e) {
				}
			}
			
		
	}
}