package RoomFactory;

import Room.*;
import java.util.Scanner;

//UML: completed

public class RoomFactory implements RoomCreation {

    private Scanner scanner;

    public RoomFactory() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Rooms createRoom() {

        System.out.println("-----Add a New Room-----");
        int choice = -1;

        while (true) {
            try {
                System.out.println("Select Room Type:");
                System.out.println("1. Standard");
                System.out.println("2. Deluxe");
                System.out.println("3. Suite (with kitchenette)");
                System.out.println("4. Suite (without kitchenette)");
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());

                if (choice < 1 || choice > 4) {
                    throw new IllegalArgumentException("Invalid choice. Please select 1, 2, 3, or 4.");
                }
                break; // Exit loop if input is valid
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number (1-4).");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        int roomNumber = getValidatedRoomNumber();
        int floorNumber = getValidatedFloorNumber();
        String occupancy = getValidatedOccupancy();
        double pricePerNight = getValidatedPricePerNight();

        switch (choice) {
            case 1: // Standard Room
                int windowNum = getValidatedWindowNum();
                return new StandardRoom(windowNum, roomNumber, floorNumber, occupancy, pricePerNight);

            case 2: // Deluxe Room
                windowNum = getValidatedWindowNum();
                double balconySize = getValidatedBalconySize();
                String view = getValidatedView();
                return new DeluxRoom(balconySize, view, windowNum, roomNumber, floorNumber, occupancy, pricePerNight);

            case 3: // Suite Room
                windowNum = getValidatedWindowNum();
                balconySize = getValidatedBalconySize();
                view = getValidatedView();
                double livingRoomSize = getValidatedLivingRoomSize();
                int bathrooms = getValidatedBathrooms();
                
//                boolean kitchenette = true;

                return new SuiteRoom(livingRoomSize, bathrooms, true, balconySize, view, windowNum, roomNumber, floorNumber, occupancy, pricePerNight);
            
            case 4: //suite without kitchennette.
                windowNum = getValidatedWindowNum();
                balconySize = getValidatedBalconySize();
                view = getValidatedView();
                livingRoomSize = getValidatedLivingRoomSize();
                bathrooms = getValidatedBathrooms();
                
                return new SuiteRoom(livingRoomSize, bathrooms, balconySize, view, windowNum, roomNumber, floorNumber, occupancy, pricePerNight);

            default:
                System.out.println("Invalid choice. Please select a valid room type.");
                return null;
        }
    }
    
    //w11 shape Encapsulate: Only visible within RoomFactory class 
    private int getValidatedRoomNumber() {
        int roomNumber = -1;
        while (roomNumber <= 0) {
            try {
                System.out.print("Enter Room Number: ");
                roomNumber = Integer.parseInt(scanner.nextLine());
                Rooms.validateRoomNumber(roomNumber);
            } catch (NumberFormatException e) {
                System.out.println("Room number must be a positive number.");
            }
        }
            return roomNumber;
        }


    private int getValidatedFloorNumber() {
        int floorNumber = -1;
        while (floorNumber < 0) {
            try {
                System.out.print("Enter Floor Number: ");
                floorNumber = Integer.parseInt(scanner.nextLine());
                Rooms.validateFloorNumber(floorNumber);
            } catch (NumberFormatException e) {
                System.out.println("Floor number must be a non-negative number.");
            } 
        }
        return floorNumber;
    }

    private String getValidatedOccupancy() {
        String occupancy = null;
        while (occupancy == null || occupancy.isEmpty()) {
            try {
                System.out.print("Enter Occupancy (Single/Double/Triple): ");
                occupancy = scanner.nextLine();
                Rooms.validateOccupancy(occupancy);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                occupancy = null;
            }
        }
        return occupancy;
    }

    private double getValidatedPricePerNight() {
        double pricePerNight = -1.0;
        while (pricePerNight <= 0) {
            try {
                System.out.print("Enter Price Per Night: ");
                pricePerNight = Double.parseDouble(scanner.nextLine());
                if (pricePerNight <= 0) {
                    System.out.println("Price per night must be a positive value.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number for the price per night.");
            }
        }
        return pricePerNight;
    }

    private int getValidatedWindowNum() {
        int windowNum = -1;
        while (windowNum < 1) {
            try {
                System.out.print("Enter Number of Windows: ");
                windowNum = Integer.parseInt(scanner.nextLine());
                StandardRoom.validatewindowNum(windowNum);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number for the windows.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return windowNum;
    }

    private double getValidatedBalconySize() {
        double balconySize = 0.0;
        while (balconySize <= 0) {
            try {
                System.out.print("Enter Balcony Size (in sq. meters): ");
                balconySize = Double.parseDouble(scanner.nextLine());
                if (balconySize <= 0) {
                    System.out.println("Balcony size must be a positive value.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number for the balcony size(in sq. meters)");
            }
        }
        return balconySize;
    }

    private String getValidatedView() {
        String view = null;
        while (view == null || view.isEmpty()) {
            try {
                System.out.print("Enter View (Sea View, Mountain View, or Landmark View): ");
                view = scanner.nextLine();
                DeluxRoom.validateView(view);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                view = null;
            }
        }
        return view;
    }

    private double getValidatedLivingRoomSize() {
        double livingRoomSize = -1.0;
        while (livingRoomSize <= 0) {
            try {
                System.out.print("Enter Living Room Size (in sq. meters): ");
                livingRoomSize = Double.parseDouble(scanner.nextLine());
                if (livingRoomSize <= 0) {
                    System.out.println("Living room size must be a positive value.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number for the living room size(sq. meters).");
            }
        }
        return livingRoomSize;
    }

    private int getValidatedBathrooms() {
        int bathrooms = -1;
        while (bathrooms < 1) {
            try {
                System.out.print("Enter Number of Bathrooms: ");
                bathrooms = Integer.parseInt(scanner.nextLine());
                if (bathrooms < 1) {
                    System.out.println("A suite should have at least 1 bathroom.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number for the bathrooms.");
            }
        }
        return bathrooms;
    }
}
