
package Room;

import Booking.Booking;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Rooms {
    private int roomNumber;
    private int floorNumber;
    private String occupancy;
    private double pricePerNight;
    private List<Booking> bookings; 
    //access and interact with the attributes and methods of the Booking class.

    
    public Rooms (int roomNumber, int floorNumber, String occupancy, double pricePerNight){
        this.roomNumber = roomNumber;
        this.floorNumber = floorNumber;
        this.occupancy = occupancy;
        this.pricePerNight = pricePerNight;
        validateRoomNumber(roomNumber);
        validateFloorNumber(floorNumber);
        validateOccupancy(occupancy);
        this.bookings = new ArrayList<>();

        
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        validateRoomNumber(roomNumber);
        this.roomNumber = roomNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        validateFloorNumber(floorNumber);
        this.floorNumber = floorNumber;
    }

    public String getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(String occupancy) {
        validateOccupancy(occupancy);
        this.occupancy = occupancy;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }
    
    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
    
    public List<Booking> getBookings() {
        return bookings;
    }
    
    //check room availability depends on the bookings specific to that room
    public boolean isAvailableDuring(LocalDate startDate, LocalDate endDate) {
        for (Booking booking : bookings) {
            if (!(booking.getCheckOutDate().isBefore(startDate) || booking.getCheckInDate().isAfter(endDate))) {
                return false; 
            }
        }
        return true; // No overlaps
    }
    
    //adds a new booking to the room's list of bookings
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }
    
    @Override
    public String toString() {
        return '\n' + "Room Type: " + getRoomType() + '\n'
           + "Room Number: " + roomNumber + '\n'
           +"Floor: " + floorNumber + '\n'
           +"Occupancy: " + occupancy + '\n'
           +"Price Per Night: GBP" + pricePerNight + '\n';
    }
    
 // Abstract method for room type
    public abstract String getRoomType();
    
 // Private validation methods | w11 shape
    public static void validateRoomNumber(int roomNumber) {
        if (roomNumber <= 0) {
            throw new IllegalArgumentException("Room number must be greater than 0.");
        }
    }
    
    public static void validateFloorNumber(int floorNumber) {
        if (floorNumber < 0) {
            throw new IllegalArgumentException("Floor number cannot be negative.");
        }
    }
    
    public static void validateOccupancy(String occupancy) {
        if (occupancy == null || occupancy.isEmpty()) {
            throw new IllegalArgumentException("Occupancy cannot be empty. It must be Single, Double or Triple");
        }

    }
}
