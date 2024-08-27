/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelroombooking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zheng
 */
public class FileHandling {
    private int newNum;
    private String name;
    private String email;
    private String phone;
    private String ic;
    private String checkIn;
    private String checkOut;
    private String roomNo;
    private String amount;
    private String removeId;
    
    private DefaultTableModel model;
    private String fileName;
    
    public FileHandling( int newNum, String name, String phone, String ic, String email, String checkIn, String checkOut, String roomNo, String amount)
    {
        this.newNum = newNum;
        this.name = name;
        this.ic = ic;
        this.phone = phone;
        this.email=email;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.amount = amount;
        this.roomNo=roomNo;
    }
    
    public FileHandling(String removeId){
        this.removeId = removeId;
    }
    public FileHandling(DefaultTableModel model, String filename){
        this.model = model;
        this.fileName=filename;
    }
    public FileHandling(){
        
    }

    
    public String writeRecord()
    {
    try{
            FileWriter fw = new FileWriter("bookingDetails.txt",true);
            PrintWriter outputFile = new PrintWriter(fw);
            outputFile.print(""+newNum+",");
            outputFile.print(name+",");
            outputFile.print(ic+",");
            outputFile.print(phone+",");
            outputFile.print(email+",");
            outputFile.print(checkIn+",");
            outputFile.print(checkOut+",");
            outputFile.print(roomNo+",");
            outputFile.print(amount+"\n");
            
            outputFile.close();
            return "Success";
            
        }
        catch (Exception ex)
        {
            return ex.toString();
            
        }
    
    
}
    
    public String[][] getBookingDetails() {
        File file = new File("bookingDetails.txt");
        List<String[]> rows = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] dataRow = line.split(",");
                rows.add(dataRow);
            }
            br.close();
        } catch(FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(RoomBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch(IOException ex) {
            java.util.logging.Logger.getLogger(RoomBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // convert the list to a 2D array
        String[][] bookingDetails = new String[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            bookingDetails[i] = rows.get(i);
        }

        return bookingDetails;
    }
    public void removeRecord() throws IOException {
        int position =0;
        String currentDir = System.getProperty("user.dir");
        String tempFile = "temp.txt";
        File oldFile = new File(currentDir+"/bookingDetails.txt");
        File newFile = new File( tempFile);

        String currentLine;
        String data[];
        System.out.println(removeId);
        try (FileWriter fw = new FileWriter(tempFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw);
             FileReader fr = new FileReader("bookingDetails.txt");
             BufferedReader br = new BufferedReader(fr)) {
            while ((currentLine = br.readLine()) != null) {
                
                data = currentLine.split(",");
                
                if (!(data[position].equalsIgnoreCase(removeId))) {
                    pw.println(currentLine);
                    
                }
            }
            
            pw.flush();
        }

        oldFile.delete();
        File dump = new File("bookingDetails.txt");
        newFile.renameTo(dump);
    }
    public static void deleteRecord(String bookingId) throws FileNotFoundException, IOException {
        try{    
        File inputFile = new File( "bookingDetails.txt");
            File tempFile = new File( "Temp.txt");
            BufferedReader reader = new BufferedReader(new FileReader( inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String line;
            while((line = reader.readLine()) != null){
                String[] data = line.split( ",");
                if (data[0].equals(bookingId)) {
                    continue; // Skip the line with the matching room number
                }
                writer.write( line);
                writer.newLine();
            }
            
            reader.close();
            writer.close();
                    
            inputFile.delete();
            tempFile.renameTo(inputFile);
    
        }catch (IOException e){

        }
    
    }
}

