import java.io.IOException;
import java.util.Scanner;

public class ParkingLot {
    static AssignmentSystem assignmentSystem = new AssignmentSystem();
    static ExitSystem exitSystem = new ExitSystem();
    static Admin admin = new Admin();

    public static void main(String[] args) throws IOException {
        admin.initializeDefaultFloor(assignmentSystem, 5);
        Scanner scanner = new Scanner(System.in);
        assignmentSystem.entranceBoard.idleMessage();
        assignmentSystem.entranceBoard.displayMessage("To show prices, please tap yes");
        int selection = scanner.nextInt(); // button corresponding to yes and no
        if (selection == 1) {
            assignmentSystem.showPrices();
        }
        assignmentSystem.entranceBoard.displayMessage("Do you want to proceed to park?"); // presents two options yes or
        // no
        selection = scanner.nextInt();
        if (selection == 1) {
            System.out.print("Enter your vehicle type: ");
            String vehicleType = scanner.next();
            assignmentSystem.assignSpot(vehicleType);
        } else {
            System.out.println("Have a great day!");
            assignmentSystem.entranceBoard.idleMessage();
        }
        //at the exit point
        exitSystem.exitDisplayBoard.displayMessage("Please insert your parking ticket");
        long id = scanner.nextLong();
        exitSystem.makePayment(assignmentSystem.getSpot(id));
    }
}
