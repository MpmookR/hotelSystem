package RoomFactory;

import Room.Rooms;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//sort by floor number (descending)
public class SortedFloorNo implements Comparator<Rooms> {
    //Comparator<typeOfobject>

    @Override
    public int compare(Rooms r1, Rooms r2) {
        //return Integer.compare(r2.getFloorNumber(), r1.getFloorNumber());

        if (r1.getFloorNumber() > r2.getFloorNumber()) {
            return -1;
        } else if (r1.getFloorNumber() < r2.getFloorNumber()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void listByFloorNumber(List<Rooms> roomList) {
        System.out.println("Sorted Floor Number: Descending");

        Collections.sort(roomList, new SortedFloorNo());

        for (int i = 0; i < roomList.size(); i++) {
            Rooms room = roomList.get(i);
            System.out.println(room);
        }
    }
}
