import java.util.Scanner;

interface Administrator{
    void blockSpot(ParkingSpot parkingSpot);
    void unblockSpot(ParkingSpot parkingSpot);
    void blockFloor(ParkingFloor parkingFloor);
    void unblockFloor(ParkingFloor parkingFloor);
    void initializeFloors(AssignmentSystem assignmentSystem);
    void initializeDefaultFloor(AssignmentSystem assignmentSystem, int numFloors);
    void initializeGroundFloor(AssignmentSystem assignmentSystem);
}

public class Admin implements Administrator{
    private String spotKey = "spotSecret";
    private String floorKey = "floorSecret";
    private String masterKey = "topSecret";

    public void setKey(String key) {
        System.out.println("Enter the previous key: ");
        Scanner scanner = new Scanner(System.in);
        String prevKey = scanner.nextLine();
        if (prevKey == this.masterKey) {
            this.masterKey = key;
            System.out.println("Updated the key successfully.");
        } else {
            System.out.println("Wrong security key. Please try again.");
        }
    }

    public void blockSpot(ParkingSpot parkingSpot) {
        parkingSpot.blockSpot(this.spotKey);
    }

    public void unblockSpot(ParkingSpot parkingSpot) {
        parkingSpot.unblockSpot(spotKey);
    }

    public void blockFloor(ParkingFloor parkingFloor) {
        parkingFloor.blockFloor(floorKey);
    }

    public void unblockFloor(ParkingFloor parkingFloor) {
        parkingFloor.unblockFloor(floorKey);
    }
    
    public void initializeFloors(AssignmentSystem assignmentSystem){
        assignmentSystem.initalizeFloors(masterKey);
    }

    public void initializeDefaultFloor(AssignmentSystem assignmentSystem, int numFloors){
        assignmentSystem.defaultFloor(floorKey, numFloors);
    }

    public void initializeGroundFloor(AssignmentSystem assignmentSystem){
        assignmentSystem.groundFloor(floorKey);
    }
}
