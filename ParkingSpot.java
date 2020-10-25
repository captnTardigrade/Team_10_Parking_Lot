
public class ParkingSpot {
    private String adminKey = "spotSecret";
    boolean isOccupied = false;
    long entryTime;
    int space;
    long id; // customer id
    double[] priceFactor;

    ParkingSpot(int space, double[] priceFactor) {
        this.space = space;
        this.priceFactor = priceFactor;
        this.entryTime = System.currentTimeMillis();
    }

    public void assignSpot(long id) {
        this.isOccupied = true;
        this.id = id;
    }

    public void blockSpot(String adminKey) {
        if (adminKey == this.adminKey) {
            this.isOccupied = true;
            System.out.println("Blocked spot successfully");
        }
    }

    public void unblockSpot(String adminKey){
        if(adminKey == this.adminKey){
            this.isOccupied = false;
            System.out.println("Spot unblocked successfully");
        }
    }
}

class EVSpot extends ParkingSpot{

    boolean isCharging = false;

    EVSpot(int space, double[] priceFactor) {
        super(space, priceFactor);
    }
    double[] chargingPrices = {10, 10, 10};
}
