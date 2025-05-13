package RoomFactory;

import Room.Rooms;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// sort by price (ascending) and availability
public class SortedPrice {

    //check the date
    public List<Rooms> filterAndSortByPrice(List<Rooms> roomList,
            LocalDate checkInDate, LocalDate checkOutDate) {

        List<Rooms> availableRooms = new ArrayList<>(); 
        //assign a list to contain sorted availableRooms

        for (int i = 0; i < roomList.size(); i++) {
            Rooms room = roomList.get(i);
            if (room.isAvailableDuring(checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }

        }

        // Sort the available rooms and price (ascending)
        Collections.sort(availableRooms, new Comparator<Rooms>() {
            //Collections.sort(theListToSort, new Comparator<T>() {

            @Override
            public int compare(Rooms r1, Rooms r2) {
                if (r1.getPricePerNight() < r2.getPricePerNight()) {
                    return -1;
                } else if (r1.getPricePerNight() > r2.getPricePerNight()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        return availableRooms;
    }

}
