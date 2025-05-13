package HotelSystem;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import Room.Rooms;
import RoomFactory.RoomFactory;
import RoomFactory.RoomDeletion;
import RoomFactory.SortBy;

import Booking.*;


public class HotelSystem implements HotelManager, HotelCustomer {
    private List<Rooms> roomList; // Collection to store room objects
    private int MAX_ROOMS = 10; //// Maximum number of rooms allowed
    private static int roomCount; 
    private RoomFactory roomFactory; // composition - roomcreation
    private RoomDeletion roomDeletion;
    private SortBy sortBy;           // Handles menu interaction for sorting
    private MakeBooking makeBooking;
    private BookingCancellation bookingCancellation;
    private BookingReport bookingReport;
    
    public HotelSystem() {
        roomList = new ArrayList<>();
        roomFactory = new RoomFactory();
        roomCount = 0;
        roomDeletion = new RoomDeletion();
        sortBy = new SortBy(); // Pass RoomListing to SortBy
        makeBooking = new MakeBooking();
        bookingCancellation = new BookingCancellation();
        bookingReport = new BookingReport();
    }
        
    public static void main(String[] args) {
        HotelSystem hotelSystem = new HotelSystem();
        hotelSystem.hotelMenu();
    }
    

    public void hotelMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-----Hotel Management System-----");
            System.out.println("1. Admin Menu");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    customerMenu();
                    break;
                case 3:
                    System.out.println("Exiting the system...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
     public void adminMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-----Admin Menu-----");
            System.out.println("1. List Rooms");
            System.out.println("2. Add Room");
            System.out.println("3. Remove Room");
            System.out.println("4. Generate Booking Report");
            System.out.println("5. Switch to Customer Menu");
            System.out.println("6. Exit...");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listRoom();
                    break;
                case 2:
                    addRoom();
                    break;
                case 3:
                    removeRoom();
                    break;
                case 4:
                    generateReport();
                    break;
                case 5:
                    customerMenu(); // Switch to Customer Menu
                    break;
                case 6:
                    return; // Back to Main Menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
     
     public void customerMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-----Customer Menu-----");
            System.out.println("1. Sort Rooms");
            System.out.println("2. Make Booking");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Switch to Admin Menu");
            System.out.println("5. Exit...");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    sortRoom();
                    break;
                case 2:
                    makeBooking();
                    break;
                case 3:
                    cancleBooking();
                    break;
                case 4:
                    adminMenu(); // Switch to Admin Menu
                    break;
                case 5:
                    return; // Back to Main Menu
                default:
                    System.out.println("Please try again.");
            }
        }
    }

    // HotelManager 

    @Override
    public void listRoom() {
        sortBy.AdminSortOption(roomList);
    }

    @Override
    public void addRoom() {
        
        //check if the maximum room limit is reached
        if (roomCount >= MAX_ROOMS){
            System.out.println("You reached the room creation limit: " + MAX_ROOMS + "rooms");
            return;
        }
        
        Rooms newRoom = roomFactory.createRoom(); 
        if (newRoom != null) {
            roomList.add(newRoom);
            roomCount++;
            System.out.println('\n' + "Room added successfully: " + newRoom);
        } else {
            System.out.println('\n' + "Room creation failed.");
        }
    }

    @Override
    public void removeRoom() {
        roomDeletion.removeRoom(roomList);
    }

    @Override
    public void generateReport() {
        bookingReport.generateFileReport(roomList);
    }
    
    // HotelCustomer
    @Override
    public void sortRoom() {
        sortBy.CustomerSortOption(roomList);
    }

    @Override
    public void makeBooking() {
        Booking newBooking = makeBooking.createBooking(roomList);
        if (newBooking != null){
            System.out.println("Booking successfully created");        
        } else {
            System.out.println("Please try again...");
        }
    }

    @Override
    public void cancleBooking() {        
        if(bookingCancellation.cancleBooking(roomList)){
            System.out.println("Booking cancellation completed.");
        } else {
            System.out.println("Failed to cancel the booking.");
        }
    }
    
}
