package Booking;

import Room.Rooms;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//UML:done
public class BookingReport {

    public List<String> reportDetails(List<Rooms> roomList, LocalDate startDate, LocalDate endDate) {
        List<String> reportLines = new ArrayList<>();

        // Iterate through rooms and bookings
        for (int i = 0; i < roomList.size(); i++) {
            Rooms room = roomList.get(i);
            List<Booking> bookings = room.getBookings();
            boolean roomHasBookings = false;

            // Check if room has bookings
            for (int j = 0; j < bookings.size(); j++) {
                Booking booking = bookings.get(j);

                // Add room details once if it has bookings
                if (!roomHasBookings) {
//                    reportLines.add("Room Details: " + room);
                    reportLines.add("Bookings Details:");
                    roomHasBookings = true;
                }

                // Add booking details
                reportLines.add(" Booking ID: " + booking.getBookingID());
                reportLines.add(" Customer: " + booking.getCustomerName());
                reportLines.add(" Contact Number: " + booking.getContactNumber());
                reportLines.add(" Email: " + booking.getEmail());
                reportLines.add(" Check-In Date: " + booking.getCheckInDate());
                reportLines.add(" Check-out Date: " + booking.getCheckOutDate());
                reportLines.add(" Total price: " + booking.getTotalPrice());
                reportLines.add(" Room Details: " + '\n' + room);
            }

            if (roomHasBookings) {
                reportLines.add(""); // Add a blank line for separation
            }
        }

        // If no bookings found, add a message
        if (reportLines.isEmpty()) {
            reportLines.add("No bookings found within the specified timeframe.");
        }

        return reportLines;
    }

    public void generateFileReport(List<Rooms> roomList) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Input the timeframe
            System.out.print("Enter Start Date (yyyy-mm-dd): ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter End Date (yyyy-mm-dd): ");
            LocalDate endDate = LocalDate.parse(scanner.nextLine());

            // Generate the report for the specified timeframe
            List<String> reportLines = reportDetails(roomList, startDate, endDate);

            // Get file name from admin
            System.out.print("Enter the file name to save the report (e.g., bookings.txt): ");
            String fileName = scanner.nextLine();

            // Use FileReport class to save the input filename
            // Write the report to a file
            try (PrintWriter writer = new PrintWriter(fileName)) {
                for (int i = 0; i < reportLines.size(); i++) {
                    writer.println(reportLines.get(i));
                }
                System.out.println('\n' + "Report has been successfully saved to " + fileName);
            } catch (IOException e) {
                System.out.println("Error while writing the report to the file: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("An error occurred while generating the report: " + e.getMessage());
        }
    }
}
