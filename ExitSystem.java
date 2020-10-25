import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class ExitSystem {
    protected DisplayBoard exitDisplayBoard = new DisplayBoard();

    double calculateBill(ParkingSpot parkingSpot) {
        long exit = System.currentTimeMillis();
        Date exitTime = new Date((long) exit);
        Date entryTime = new Date((long) parkingSpot.entryTime);
        long diff = exitTime.getTime() - entryTime.getTime();
        long diffHours = diff / (60 * 60 * 1000);
        if (diffHours == 1) {
            return parkingSpot.priceFactor[0];
        } else if (diffHours == 2) {
            return parkingSpot.priceFactor[0] + parkingSpot.priceFactor[1];
        }

        return parkingSpot.priceFactor[0] + parkingSpot.priceFactor[1] + (diffHours - 2) * parkingSpot.priceFactor[2];
    }

    double calculateBill(EVSpot evSpot) {
        long exit = System.currentTimeMillis();
        Date exitTime = new Date((long) exit);
        Date entryTime = new Date((long) evSpot.entryTime);
        long diff = exitTime.getTime() - entryTime.getTime();
        long diffHours = diff / (60 * 60 * 1000);
        return evSpot.priceFactor[0] + evSpot.priceFactor[1] + (diffHours - 2) * evSpot.priceFactor[2]
                + evSpot.chargingPrices[0] + evSpot.chargingPrices[1] + evSpot.chargingPrices[2] * diffHours;
    }

    void makePayment(ParkingSpot parkingSpot) throws IOException {
        exitDisplayBoard.displayMessage("Your bill is: " + calculateBill(parkingSpot));
        exitDisplayBoard.displayMessage("Choose the mode of payment: \n1: Cash\n2: Card");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        if (option == 1) {
            ParkingAttendant parkingAttendant = new ParkingAttendant();
            parkingAttendant.makePayment();
        }
        if (option == 2) {
            BankTen bankTen = new BankTen();
            long id = parkingSpot.id;
            exitDisplayBoard.displayMessage("Please swipe your card");
            String cardNumber = scanner.next();
            while (!bankTen.isValidCard(cardNumber)) {
                exitDisplayBoard.displayMessage("Please use a valid card");
                cardNumber = scanner.next();
            }
            exitDisplayBoard.displayMessage("Enter your PIN");
            scanner.next();
            while (!bankTen.isCorrectPIN()) {
                exitDisplayBoard.displayMessage("Incorrect PIN");
            }
            if (bankTen.hasEnoughBalance(id, calculateBill(parkingSpot))) {
                exitDisplayBoard.displayMessage("Payment successful!");
                exitDisplayBoard.displayMessage("Have a great day!");
                deleteDetails(parkingSpot.id);
            } else {
                exitDisplayBoard.displayMessage(
                        "Your account doesn't have enough balance. Please choose a different mode of payment");
            }
        }
    }

    void makePayment(EVSpot evSpot) throws IOException {
        exitDisplayBoard.displayMessage("Your bill is: " + calculateBill(evSpot));
        exitDisplayBoard.displayMessage("Choose the mode of payment: \n1: Cash\n2: Card");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        if (option == 1) {
            ParkingAttendant parkingAttendant = new ParkingAttendant();
            parkingAttendant.makePayment();
        }
        if (option == 2) {
            BankTen bankTen = new BankTen();
            long id = evSpot.id;
            exitDisplayBoard.displayMessage("Please swipe your card");
            String cardNumber = scanner.next();
            while (!bankTen.isValidCard(cardNumber)) {
                exitDisplayBoard.displayMessage("Please use a valid card");
                cardNumber = scanner.next();
            }
            exitDisplayBoard.displayMessage("Enter your PIN");
            scanner.next();
            while (!bankTen.isCorrectPIN()) {
                exitDisplayBoard.displayMessage("Incorrect PIN. Please re-enter.");
                scanner.next();
            }
            if (bankTen.hasEnoughBalance(id, calculateBill(evSpot))) {
                exitDisplayBoard.displayMessage("Payment successful!");
                exitDisplayBoard.displayMessage("Have a great day!");
                deleteDetails(evSpot.id);
            } else {
                exitDisplayBoard.displayMessage(
                        "Your account doesn't have enough balance. Please choose a different mode of payment");
            }
        }
    }

    void deleteDetails(long id) throws IOException {

        String tempFile = "temp.csv";
        File oldFile = new File("CustomerData.csv");
        File newFile = new File(tempFile);
        String currentLine;
        FileReader fr;

        fr = new FileReader("./Bank.csv");
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw;
        fw = new FileWriter(tempFile, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        while ((currentLine = br.readLine()) != null) {
            String[] data = currentLine.split(",");
            if (!data[0].equals(id + "")) {
                pw.println(data[0] + "," + data[1]);
            }
        }
        oldFile.delete();
        File dump = new File("CustomerData.csv");
        newFile.renameTo(dump);
    }
}