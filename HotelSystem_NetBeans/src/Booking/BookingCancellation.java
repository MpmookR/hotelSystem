package Booking;

import Room.Rooms;
import java.util.List;
import java.util.Scanner;

public class BookingCancellation {

    private Scanner scanner = new Scanner(System.in);

    public boolean cancleBooking(List<Rooms> roomList) {
        System.out.println("Enter the Booking ID to cancle: ");
        String bookingID = scanner.nextLine();

        for (int i = 0; i < roomList.size(); i++) {
            Rooms room = roomList.get(i); //loop through each room 
            
            List<Booking> bookings = room.getBookings(); //get the booking details from room(i)

            Booking bookingToCancel = null;

            for (int j = 0; j < bookings.size(); j++) { //loop thru the booking details of the room(i)
                Booking booking = bookings.get(j);
                if (booking.getBookingID().equals(bookingID)) {
                // If the booking ID matches the user's input
                    bookingToCancel = booking; //then booking will be removed
                    break;
                }
            }

            // If the booking is found, remove it
            if (bookingToCancel != null) {
                bookings.remove(bookingToCancel);
                System.out.println('\n' + "Booking with ID: " + bookingID + " has been canceled successfully.");
                return true;
            }
        }

        System.out.println('\n' + "Booking ID " + bookingID + " not found.");
        return false;
    }

}
