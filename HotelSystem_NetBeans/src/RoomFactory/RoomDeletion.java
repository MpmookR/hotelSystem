
package RoomFactory;

import Room.Rooms;
import java.util.List;
import java.util.Scanner;

public class RoomDeletion{

    private Scanner scanner;
    
    public RoomDeletion(){
        scanner = new Scanner(System.in);
    }
    
    public void removeRoom(List<Rooms> roomList) {
        
        try{
            System.out.println("Remove a Room");
            System.out.println("Enter the Room Number to remove");
            int roomNumber = Integer.parseInt(scanner.nextLine());
            
            //Find the room to remove
            Rooms roomToRemove = null; //type use null, int uses 0
            for (int i=0; i < roomList.size(); i++ ){
                Rooms room = roomList.get(i);
                if (room.getRoomNumber() == roomNumber){
                    roomToRemove = room;
                }          
            }
            
            if (roomToRemove != null){
                roomList.remove(roomToRemove);
                System.out.println("Room: " + roomNumber + " is successfully removed");
                System.out.println(roomToRemove);
            } else {
                System.out.println("No room found with the room number: " + roomNumber); 
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid room number.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
    
}
