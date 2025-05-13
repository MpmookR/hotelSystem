package RoomFactory;

import Room.Rooms;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class SortBy {

    private SortedRoomNo roomComparator;
    private SortedFloorNo floorComparator;
    private SortedPrice priceComparator;
    private SortedTypeOccupancy roomMatch;
    private Scanner scanner = new Scanner(System.in);

    public SortBy() {
        this.roomComparator = new SortedRoomNo();
        this.floorComparator = new SortedFloorNo();
        this.priceComparator = new SortedPrice();
        this.roomMatch = new SortedTypeOccupancy();

    }

    public void AdminSortOption(List<Rooms> roomList) {

        while (true) {
            System.out.println("List Registered Rooms");
            System.out.println("1. By Room Number (Ascending)");
            System.out.println("2. By Floor Number (Descending)");
            System.out.println("3. Exit...");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    roomComparator.listByRoomNumber(roomList);
                    break;
                case 2:
                    floorComparator.listByFloorNumber(roomList);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Please select a valid option.");
            }
        }
    }

    private LocalDate getCheckInDate() {
        while (true) {
            try {
                System.out.print("Enter Check-In Date (yyyy-mm-dd): ");
                return LocalDate.parse(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }
    }

    private LocalDate getCheckOutDate(LocalDate checkInDate) {
        while (true) {
            try {
                System.out.println("Enter Check-Out Date (yyyy-mm-dd):");
                LocalDate checkOutDate = LocalDate.parse(scanner.nextLine());
                if (checkOutDate.isBefore(checkInDate)) {
                    System.out.println("Check-Out Date cannot be before Check-In Date");
                } else {
                    return checkOutDate;
                }
            } catch (Exception e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }
    }

    public void CustomerSortOption(List<Rooms> roomList) {

        while (true) {
            System.out.println("List Available Room");
            System.out.println("1. By Price (Ascending)");
            System.out.println("2. By Room Type and Occupancy ");
            System.out.println("3. Exit...");
            System.out.println("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    // Get timeframe for filtering
                    LocalDate checkInDate = getCheckInDate();
                    LocalDate checkOutDate = getCheckOutDate(checkInDate);

                    // Filter and sort rooms by price and availability using SortedPrice class
                    List<Rooms> availableRooms = priceComparator.filterAndSortByPrice(roomList, checkInDate, checkOutDate);

                    // Display sorted rooms
                    if (availableRooms.isEmpty()) {
                        System.out.println("No rooms available during the specified timeframe.");
                    } else {
                        System.out.println("Available Rooms Sorted by Price (Low to High price):");

                        for (int i = 0; i < availableRooms.size(); i++) {
                            System.out.println(availableRooms.get(i));
                        }
                    }
                    break;

                case 2:
                    checkInDate = getCheckInDate();
                    checkOutDate = getCheckOutDate(checkInDate);

                    System.out.print("Enter Room Type (Standard/Deluxe/Suite): ");
                    String roomType = scanner.nextLine();
                    System.out.print("Enter Occupancy (Single/Double/Triple): ");
                    String occupancy = scanner.nextLine();

                    List<Rooms> sortRooms = roomMatch.listRoomsByTypeAndOccupancy(
                            roomList, roomType, occupancy, checkInDate, checkOutDate
                    );

                    if (sortRooms.isEmpty()) {
                        System.out.println("No rooms available matching the specified type, occupancy, and timeframe.");
                    } else {
                        System.out.println("Available Rooms Matching Criteria:");
                        for (int i = 0; i < sortRooms.size(); i++) {
                            Rooms room = sortRooms.get(i);
                            System.out.println(room);
                        }
                    }
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Please select a valid choice.");
            }
        }

    }

}
