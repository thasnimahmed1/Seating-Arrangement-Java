import java.util.ArrayList;
import java.util.List;

// Enum representing different seating styles
enum SeatingStyle {
    OPEN, // Open seating (first come first served)
    ASSIGNED, // Assigned seating
    GROUP // Group seating
}

// Class representing a seat 
class Seat {
    private int seatNumber;
    private boolean reserved;
    
    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.reserved = false;
    }
    
    public int getSeatNumber() {
        return seatNumber;
    }
    
    public boolean isReserved() {
        return reserved;
    }
    
    public void reserve() {
        reserved = true;
    
    }
}

// Class represendting a seating arrangement
class SeatingArrangement {
    private SeatingStyle seatingStyle;
    private List<Seat> seats;
    
    public SeatingArrangement(int numSeats, SeatingStyle seatingStyle) {
        this.seatingStyle = seatingStyle;
        seats = new ArrayList<>();
        for (int i = 1; i <= numSeats; ++i) {
            seats.add(new Seat(i));
        }
    }
    
    public List<Seat> getAvailableSeats() {
        List<Seat> availableSeats = new ArrayList<>();
        for (Seat seat : seats) {
            if (!seat.isReserved()) {
                availableSeats.add(seat);
            }
        }
        
        return availableSeats;
    }
    
    public List<Seat> reserveSeats(int numSeats) {
        List<Seat> reservedSeats = new ArrayList<>();
        int count = 0;
        for (Seat seat : seats) {
            if (!seat.isReserved()) {
                seat.reserve();
                reservedSeats.add(seat);
                count++;
                if (count == numSeats) {
                    break;
                }
            }
        }
        
        return reservedSeats;
    }
}

// Class representing an event
class Event {
    private SeatingArrangement seatingArrangement;
    
    public Event(int numSeats, SeatingStyle seatingStyle) {
        this.seatingArrangement = new SeatingArrangement(numSeats, seatingStyle);
    }
    
    public List<Seat> getAvailableSeats() {
        return seatingArrangement.getAvailableSeats();
    }
    
    public List<Seat> bookSeats(int numSeats) {
        List<Seat> reservedSeats = seatingArrangement.reserveSeats(numSeats);
        System.out.println("Successfully booked "+ reservedSeats.size() + " seats.");
        return reservedSeats;
    }
}

public class Main {
    public static void main(String[] args) {
        // Example
        Event event = new Event(100, SeatingStyle.ASSIGNED);
        List<Seat> availableSeats = event.getAvailableSeats();
        System.out.println("Available seats: " + availableSeats.size());
        
        // Book seats
        int numSeatsToBook = 5;
        List<Seat> bookedSeats = event.bookSeats(numSeatsToBook);
        
        // Check available seats after booking
        availableSeats = event.getAvailableSeats();
        System.out.println("Available seats: " + availableSeats.size());
    }
}