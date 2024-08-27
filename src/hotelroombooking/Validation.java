package hotelroombooking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Validation {
    private String username;
    private String password;
    private String name;
    private String icNumber;
    private String contactNumber;
    private String email;
    private String checkInDate;
    private String checkOutDate;
    private String roomId;
    private String amount;

    public Validation(String username, String password){
        this.username = username;
        this.password = password;
    }
    public Validation( String name, String icNumber, String contactNumber, String email, String checkInDate, String checkOutDate,String amount)
    {
     
        this.name = name;
        this.icNumber = icNumber;
        this.contactNumber = contactNumber;
        this.email=email;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.amount = amount;
        
    }

    Validation(String username) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public String getUsername() {
        return username;
    }

    public boolean login() {
        try {
            File file = new File("staffAccount.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String dataLine;
            String[] dataArr;

            while ((dataLine = br.readLine()) != null && dataLine.length() != 0) {
                // Split data line into array
                dataArr = dataLine.split(":");

                String un = dataArr[0];
                String pw = dataArr[1];

                // Validate if it is matched
                if (un.equals(username) && pw.equals(password)) {
                    this.username= un;
                    return true;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.err.println("staffAccount.txt file not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error while reading staffAccount.txt file.");
            e.printStackTrace();
        }

        return false;
    }
    
   public boolean formValidation() throws ParseException {
        boolean nameVal = true;
        boolean icVal = true;
        boolean numVal = true;
        boolean emailVal = true;
        boolean dateVal = true;
        boolean amountVal = true;

        if (name.equals("") || !Pattern.matches("[a-zA-Z ]+", name)) {
            JOptionPane.showMessageDialog(null, "Username is mandatory and can only be text!");
            nameVal = false;
        } 
        if (icNumber.equals("") || !Pattern.matches("[0-9]+", icNumber) || icNumber.length() != 12) {
            JOptionPane.showMessageDialog(null, "IC is mandatory and can only be a 12-digit number!");
            icVal = false;
        }
        if (contactNumber.equals("") || !Pattern.matches("[0-9]+", contactNumber) || contactNumber.length() < 10 || contactNumber.length() > 11) {
            JOptionPane.showMessageDialog(null, "Contact is mandatory and can only be a 10-digit number!");
            numVal = false;
        } 
        if (email.equals("") || !Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", email)) {
            JOptionPane.showMessageDialog(null, "Email format is wrong!");
            emailVal = false;
        } 

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date dateBefore = sdf.parse(checkInDate);
        Date dateAfter = sdf.parse(checkOutDate);
        if (dateAfter.before(dateBefore) || dateAfter.equals(dateBefore)) {
            JOptionPane.showMessageDialog(null, "Invalid check-in date");
            dateVal = false;
        }//check in should > current date
        //check in date should after current date -1
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -1); // get yesterday's date
        Date yesterday = cal.getTime();

        if (dateBefore.before(yesterday)) {
            JOptionPane.showMessageDialog(null, "Check-in date cannot be before yesterday");
            dateVal = false;
        }

        if (amount.equals("") || !Pattern.matches("[0-9]+", amount)) {
            JOptionPane.showMessageDialog(null, "Amount is mandatory and can only be digits");
            amountVal = false;
        }

        if (!nameVal || !icVal || !numVal || !emailVal || !dateVal || !amountVal) {
            return false; // at least one validation check failed, return false
        } else {
            return true; // all validation checks passed, return true
        }
    }

}
