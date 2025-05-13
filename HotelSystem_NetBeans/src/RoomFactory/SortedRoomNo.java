package RoomFactory;

import Room.Rooms;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// sort by room number (ascending)
public class SortedRoomNo implements Comparator<Rooms> {

    @Override
    public int compare(Rooms r1, Rooms r2) {
        return Integer.compare(r1.getRoomNumber(), r2.getRoomNumber());
        //Integer.compare is the simplified version of if-else blocks

//        if (r1.getRoomNumber() < r2.getRoomNumber()) {
//            return -1;
//        } else if (r1.getRoomNumber() > r2.getRoomNumber()) {
//            return 1;
//        } else {
//            return 0;
//        }
    }

    public void listByRoomNumber(List<Rooms> roomList) {
        System.out.println("Sorted Room Number: Ascending");

        Collections.sort(roomList, new SortedRoomNo());

        for (int i = 0; i < roomList.size(); i++) {
            Rooms room = roomList.get(i);
            System.out.println(room);
        }
    }
}
