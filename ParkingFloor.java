import java.util.Scanner;

public class ParkingFloor {
    private double[] carPrices = { 20, 10, 5 };
    private double[] bikePrices = { 10, 5, 5 };
    private double[] evPrices = { 50, 25, 15 };
    private double[] truckPrices = { 0, 0, 0 };
    private boolean isBlocked = false;
    private String floorKey = "floorSecret";
    int numSpots;
    ParkingSpot[] carSpots;
    ParkingSpot[] bikeSpots;
    ParkingSpot[] evSpots;
    ParkingSpot[] truckSpots;
    ParkingSpot[] handicappedSpots;
    int numCarSpots;
    int numBikeSpots;
    int numEVSpots;
    int numTruckSpots;
    int numHandicappedSpots;

    public void blockFloor(String floorkey) {
        if (this.floorKey == floorkey) {
            this.isBlocked = true;
        }
    }

    public void unblockFloor(String floorKey) {
        if (this.floorKey == floorKey) {
            this.isBlocked = false;
        }
    }

    void initializeCarSpots(int capacity) {
        this.numCarSpots = capacity;
        carSpots = new ParkingSpot[capacity];
        for (int i = 0; i < capacity; i++) {
            carSpots[i] = new ParkingSpot(2, carPrices);
        }
    }

    void initializeBikeSpots(int capacity) {
        this.numBikeSpots = capacity;
        bikeSpots = new ParkingSpot[capacity];
        for (int i = 0; i < capacity; i++) {
            bikeSpots[i] = new ParkingSpot(1, bikePrices);
        }
    }

    void initializeTruckSpots(int capacity) {
        this.numTruckSpots = capacity;
        truckSpots = new ParkingSpot[capacity];
        for (int i = 0; i < capacity; i++) {
            truckSpots[i] = new ParkingSpot(4, truckPrices);
        }
    }

    void initializeEVSpots(int capacity) {
        this.numEVSpots = capacity;
        evSpots = new ParkingSpot[capacity];
        for (int i = 0; i < capacity; i++) {
            evSpots[i] = new ParkingSpot(2, evPrices);
        }
    }

    void initializeHandicappedSpots(int capacity) {
        this.numHandicappedSpots = capacity;
        handicappedSpots = new ParkingSpot[capacity];
        for (int i = 0; i < capacity; i++) {
            handicappedSpots[i] = new ParkingSpot(2, carPrices);
        }
    }

    int totalNumSpots() {
        return this.numBikeSpots + this.numCarSpots + this.numEVSpots + this.numHandicappedSpots + this.numTruckSpots;
    }

    void convertSpot(ParkingSpot parkingSpot, int numSplits, String vehicleType, String adminKey) {
        if (parkingSpot.space % numSplits == 0) {
            if (parkingSpot.space == 4) {
                if (vehicleType.toLowerCase() == "car") {
                    initializeCarSpots(parkingSpot.space / numSplits);
                    parkingSpot.blockSpot(adminKey);
                }
                if (vehicleType.toLowerCase() == "bike") {
                    initializeBikeSpots(parkingSpot.space / numSplits);
                    parkingSpot.blockSpot(adminKey);
                }
            }
            if (parkingSpot.space == 2) {
                if (vehicleType.toLowerCase() == "bike") {
                    initializeBikeSpots(parkingSpot.space / numSplits);
                    parkingSpot.blockSpot(adminKey);
                }
            }
        } else {
            System.out.println("The requested action cannot be performed.");
        }
    }
}