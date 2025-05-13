package RoomFactory;

import Room.Rooms;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SortedTypeOccupancy {

    public List<Rooms> listRoomsByTypeAndOccupancy(List<Rooms> roomList, String roomType,
            String occupancy, LocalDate checkInDate, LocalDate checkOutDate) {

        List<Rooms> sortRooms = new ArrayList<>();

        // Filter rooms that match roomType, occupancy, and availability
        for (int i = 0; i < roomList.size(); i++) {
            Rooms room = roomList.get(i);
            if (room.getRoomType().equalsIgnoreCase(roomType)
                    && room.getOccupancy().equalsIgnoreCase(occupancy)
                    && room.isAvailableDuring(checkInDate, checkOutDate)) {
                sortRooms.add(room);
            }
        }
        return sortRooms;
    }

}
