
package Booking;

import Room.Rooms;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.Random;

public class Booking implements Overlappable {
    private String bookingID;
    private String customerName;
    private String customerSurname;
    private LocalDate dateOfBirth;
    private String contactNumber;
    private String email;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Rooms room;
    private double totalPrice;
    
    public Booking (String customerName,String customerSurname, LocalDate dateOfBirth, String contactNumber, String email, LocalDate checkInDate, LocalDate checkOutDate, Rooms room){
    
        bookingID = getRandomBookingID();
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.dateOfBirth = dateOfBirth;
        this.contactNumber = contactNumber;
        this.email = email;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;
        this.totalPrice = calculateTotalPrice();
        
    }

    public static String getRandomBookingID() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public String getBookingID() {
        return bookingID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public Rooms getRoom() {
        return room;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    

    public LocalDate getCheckInDate() {
        return checkInDate;
    }


    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    private double calculateTotalPrice() {
        long totalStay = DAYS.between(checkInDate, checkOutDate); //stackoverflow
        return room.getPricePerNight() * totalStay;

    }

    @Override
    public boolean overlaps(Booking other) {
        return this.checkInDate.isBefore(other.getCheckOutDate()) 
                && this.checkOutDate.isAfter(other.getCheckInDate());
    }
    
    @Override
    public String toString() {
        return "Thank you for Booking with us" + '\n'
                + "Booking Confirmation:" + '\n'
                + "BookingID: " + bookingID + '\n'
                + "Name: " + customerName + '\n'
                + "Surname: " + customerSurname + '\n'
                + "Date of Birth: " + dateOfBirth + '\n'
                + "Contact Number: " + contactNumber + '\n'
                + "Email: " + email + '\n'
                + "Check In Date: " + checkInDate + '\n'
                + "Check Out Date: " + checkOutDate + '\n'
                + "Booking Details:" + room + '\n'
                + "Total Price: " + totalPrice + '\n';
    }

}
