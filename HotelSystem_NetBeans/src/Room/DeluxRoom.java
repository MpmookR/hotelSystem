
package Room;
public class DeluxRoom extends StandardRoom {
    private double balconySize;
    private String view;
    
    public DeluxRoom(double balconySize, String view, int windowNum,int roomNumber, int floorNumber, String occupancy, double pricePerNight){
        super(windowNum,roomNumber, floorNumber, occupancy, pricePerNight);
        this.balconySize = balconySize;
        this.view = view;
        validateView(view);
    }

    public double getBalconySize() {
        return balconySize;
    }

    public void setBalconySize(double balconySize) {
        this.balconySize = balconySize;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        validateView(view);
        this.view = view;
    }
    
    @Override
    public String getRoomType() {
        return "Deluxe";
    }
    
    @Override
    public String toString(){
        return super.toString() 
        + "Balcony size: " + balconySize + " sq.meters" + '\n'
        +"View: " + view + '\n';
    }

    
    public static void validateView(String view) {
        if (view == null || view.isEmpty()) {
            throw new IllegalArgumentException("View cannot be empty. Either sea view, landmark view, or mountain view");
        }
    }
}
