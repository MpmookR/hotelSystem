package Room;

public class SuiteRoom extends DeluxRoom {

    private double livingRoomSize;
    private int numberOfBathrooms;
    private boolean kitchenette;

    // Constructor with all attributes, including kitchenette
        public SuiteRoom(double livingRoomSize, int numberOfBathrooms, boolean kitchenette, double balconySize,
            String view, int windowNum, int roomNumber, int floorNumber, String occupancy,
            double pricePerNight) {
        super(balconySize, view, windowNum, roomNumber, floorNumber, occupancy, pricePerNight);
        this.livingRoomSize = livingRoomSize;
        this.numberOfBathrooms = numberOfBathrooms;
        this.kitchenette = kitchenette;
    }

    //overloading SuiteRoom without kitchenette
    public SuiteRoom(double livingRoomSize, int numberOfBathrooms, double balconySize,
            String view, int windowNum, int roomNumber, int floorNumber, String occupancy,
            double pricePerNight) {
        super(balconySize, view, windowNum, roomNumber, floorNumber, occupancy, pricePerNight);
        this.livingRoomSize = livingRoomSize;
        this.numberOfBathrooms = numberOfBathrooms;
        this.kitchenette = false;
    }
    
    public double getLivingRoomSize() {
        return livingRoomSize;
    }

    public void setLivingRoomSize(double livingRoomSize) {
        this.livingRoomSize = livingRoomSize;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public boolean hasKitchenette() {
        return kitchenette;
    }

    public void setKitchenette(boolean kitchenette) {
        this.kitchenette = kitchenette;
    }

    @Override
    public String getRoomType() {
        return "Suite";
    }

    @Override
    public String toString() {
        return super.toString() +
                "Living Room Size: " + livingRoomSize + " sq.meters\n" +
                "Number of Bathrooms: " + numberOfBathrooms + "\n" +
                "Kitchenette: " + (kitchenette? "Yes" : "No") + "\n";
    }

}
