package Booking;

import Room.Rooms;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

//UML:done
public class MakeBooking {

    private Scanner scanner = new Scanner(System.in);

    public Booking createBooking(List<Rooms> roomList) {
        System.out.print("Enter Room Number to Book: ");
        int roomNumber = Integer.parseInt(scanner.nextLine());

        Rooms selectedRoom = null;
        for (int i = 0; i < roomList.size(); i++) {
            Rooms room = roomList.get(i);
            if (room.getRoomNumber() == roomNumber) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Room number: " + roomNumber + " not found.");
            return null;
        }

        LocalDate checkInDate;
        LocalDate checkOutDate;

        while (true) {
            try {
                System.out.print("Enter Check-In Date (yyyy-mm-dd): ");
                checkInDate = LocalDate.parse(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-mm-dd format");
            }
        }

        while (true) {
            try {
                System.out.print("Enter Check-Out Date (yyyy-mm-dd): ");
                checkOutDate = LocalDate.parse(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-mm-dd format");

            }
        }

        // Check for overlapping bookings using overlaps method that defined in booking class
        List<Booking> bookings = selectedRoom.getBookings();
        boolean isOverlapping = false; 

        for (int i = 0; i < bookings.size(); i++) {
            Booking activeBooking = bookings.get(i);
            if (activeBooking.overlaps(new Booking(null, null, null, null, null, checkInDate,
                    checkOutDate, selectedRoom))) {
                isOverlapping = true;
                break;
            }
        }

        if (isOverlapping) {
            System.out.println("Room is already booked during the selected timeframe");
            return null;
        }

        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();
        System.out.println("Enter Customer Surname: ");
        String customerSurname = scanner.nextLine();
        System.out.println("Enter Date of Birth (yyyy-mm-dd): ");
        LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter Contact Number: ");
        String contactNumber = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        Booking booking = new Booking(customerName, customerSurname, dateOfBirth, 
                contactNumber, email, checkInDate, checkOutDate, selectedRoom);
        selectedRoom.addBooking(booking);

        System.out.println("\n" + booking);
        return booking;
    }
}
