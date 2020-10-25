import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

interface Bank {
    boolean isCorrectPIN();

    boolean isValidCard(String cardNumber);

    boolean hasEnoughBalance(long id, double bill);
}

public class BankTen implements Bank {

    @Override
    public boolean isCorrectPIN() {
        double i = Math.random();
        if (i >= 0.98) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isValidCard(String cardNumber) {
        String validCardPattern = "^4[0-9]{12}(?:[0-9]{3})?$";
        Pattern pattern = Pattern.compile(validCardPattern);
        if (pattern.matcher(cardNumber).matches()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasEnoughBalance(long id, double bill) {
        try {
            String currentLine;
            FileReader fr = new FileReader("./Bank.csv");
            BufferedReader br = new BufferedReader(fr);

            try {
                while ((currentLine = br.readLine()) != null) {

                    String[] data = currentLine.split(",");
                    if (data[0].equals(id + "")) {
                        if (Float.parseFloat(data[1]) < bill) {
                            return false;
                        }
                    }
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}