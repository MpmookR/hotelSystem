
package Room;

public class StandardRoom extends Rooms  {
    private int windowNum;
    
    public StandardRoom(int windowNum,int roomNumber, int floorNumber, String occupancy, double pricePerNight){
        super(roomNumber, floorNumber, occupancy, pricePerNight);
        this.windowNum = windowNum;
        validatewindowNum(windowNum);

    }
    
    public int getwindowNum() {
        return windowNum;
    }

    public void setwindowNum(int windowNum) {
        validatewindowNum(windowNum);
        this.windowNum = windowNum;
    }
            
    @Override
    public String getRoomType() {
        return "Standard";
    }
    
    @Override
    public String toString(){
        return super.toString() + "Number of Windows: " + windowNum + '\n';
    }
    
    public static void validatewindowNum(int windowNum) {
        if (windowNum < 1) {
            throw new IllegalArgumentException("Every room should have at least 1 window");
        }
    }

}
