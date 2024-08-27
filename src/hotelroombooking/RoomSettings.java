/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelroombooking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RoomSettings {

        protected static String[] roomNum = {"101", "102", "103", "104", "105","106","107","108","109","110","201","202","203","204","205","206","207","208","209","210"};
        protected static String checkInDate;
        protected static String checkOutDate;
        protected static int numOfDays;
        protected static String room;
        
        
        
        public RoomSettings(int numOfDays,String checkInDate, String checkOutDate)
             {
                 this.numOfDays = numOfDays;
                 this.checkInDate= checkInDate;
                 this.checkOutDate= checkOutDate;
             }
        public RoomSettings(String checkInDate, String checkOutDate, int numOfDays, String room){
            this.numOfDays = numOfDays;
            this.checkInDate= checkInDate;
            this.checkOutDate= checkOutDate;
            this.room = room;//constructor overloading 其中一个
        }
        public RoomSettings(){
            
        }
        
        public static Date getNewCheckOut() {
            try{
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date = dateFormat.parse(checkOutDate);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.DATE, -1);
                Date newDate = cal.getTime();
                return newDate;
            }catch(ParseException e){
                return null;
            }
               
        }

         public static String[][] getRooms() {
            String[][] availability = new String[numOfDays][roomNum.length];

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            
            Date newCheckOutDate =getNewCheckOut();
            
            addRoom(checkInDate,checkOutDate);
            
            try {
                BufferedReader reader = new BufferedReader(new FileReader("roomAvailability.txt"));
                int index = 0;
                String line;
                
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    String dateString = parts[0];
                    Date date = dateFormat.parse(dateString);
                    
                    if (!date.before(dateFormat.parse(checkInDate)) && !date.after((newCheckOutDate)))  {
                        
                        String[] roomAvailability = parts[1].split(",");
                        for (int i = 0; i < roomNum.length; i++) {
                            availability[index][i] = roomAvailability[i];
                            
                        }
                        index++;
                    }
                }
                reader.close();
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            } catch (ParseException e) {
                System.out.println("Error parsing date: " + e.getMessage());
            }
            return availability;
        }
        public static String[] getAvailableRooms() {
                String availability[][]=getRooms();
                for (int i = 0; i < availability.length; i++) {
                    for (int j = 0; j < availability[i].length; j++) {
                        if ("null".equals(availability[i][j])) {
                            roomNum[j] = null;
                        }
                    }
                
             }
                return roomNum;
        }
        
        public void updateRoomAvailability() {
            String[] roomNum = {"101", "102", "103", "104", "105","106","107","108","109","110","201","202","203","204","205","206","207","208","209","210"};
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date newCheckOutDate =getNewCheckOut();

            try {
                BufferedReader reader = new BufferedReader(new FileReader("roomAvailability.txt"));
                BufferedWriter writer = new BufferedWriter(new FileWriter("roomAvailability.txt.new"));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    String dateString = parts[0];
                    Date date = dateFormat.parse(dateString);
                    if (!date.before(dateFormat.parse(checkInDate)) && !date.after(newCheckOutDate))  {
                        String[] roomAvailability = parts[1].split(",");
                        for (int i = 0; i < roomNum.length; i++) {
                            if(roomNum[i].equals(room)) {
                                // Update the availability for the selected room on the selected date range
                                for (int j = 0; j < numOfDays; j++) {
                                    roomAvailability[i] = "null";
                                }
                                // Write the updated availability to the new file
                                writer.write(dateString + ":" + String.join(",", roomAvailability) + "\n");
                            }
                        }
                    } else {
                        // Write the original line to the new file if the date range doesn't match
                        writer.write(line + "\n");
                    }
                }
                reader.close();
                writer.close();
                // Replace the original file with the new file
                Files.move(Paths.get("roomAvailability.txt.new"), Paths.get("roomAvailability.txt"), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.out.println("Error reading/writing file: " + e.getMessage());
            } catch (ParseException e) {
                System.out.println("Error parsing date: " + e.getMessage());
            }
        }
        

        
        
        public static void addRoom(String checkInDate, String checkOutDate) {
            // Read existing contents of roomAvailability.txt file into a LinkedHashMap
            String fileName = "roomAvailability.txt";
            LinkedHashMap<String, List<String>> roomMap = new LinkedHashMap<>();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    String dateString = parts[0];
                    String[] roomNumbers = parts[1].split(",");
                    roomMap.put(dateString, Arrays.asList(roomNumbers));
                }
                reader.close();
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }

            // Add new date and room numbers to the LinkedHashMap
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date checkIn= null;
            Date checkOut =null;
          
            try {
                checkIn = dateFormat.parse(checkInDate);
                checkOut = dateFormat.parse(checkOutDate);
            } catch (ParseException e) {
                System.out.println("Error parsing date: " + e.getMessage());
            }

            while (!checkIn.after(checkOut)) {
                String dateString = dateFormat.format(checkIn);
                if (!roomMap.containsKey(dateString)) {
                    List<String> roomNumbers = Arrays.asList("101", "102", "103", "104", "105","106","107","108","109","120","121","122","123","124","125","126","127","128","129","130");
                    roomMap.put(dateString, roomNumbers);
                }
                checkIn = addDays(checkIn, 1);
            }

            // Write contents of LinkedHashMap back to roomAvailability.txt file in the correct order
            try {
                FileWriter writer = new FileWriter(fileName);
                for (Map.Entry<String, List<String>> entry : roomMap.entrySet()) {
                    String dateString = entry.getKey();
                    List<String> roomNumbers = entry.getValue();
                    String roomString = String.join(",", roomNumbers);
                    writer.write(dateString + ":" + roomString + "\n");
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("Error writing file: " + e.getMessage());
            }
        }

        private static Date addDays(Date date, int days) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, days);
            return cal.getTime();
        }
}

class UpdateRoomToAvailable extends RoomSettings {
    
    private String id;
    public UpdateRoomToAvailable(String checkInDate, String checkOutDate, String room, int numOfDays,String id) {
        super(checkInDate, checkOutDate, numOfDays, room);
        this.id = id;
    }
    public UpdateRoomToAvailable(String id){
        this.id=id;
    }

    
    
    public void updateRoomAvailability() {
        ArrayList<String> tempArray = new ArrayList<>();
        

        //temporary array to hold the data that is read from file
        try {
            FileReader fr = new FileReader("bookingDetails.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line;
            String[] lineArr;

            while((line = reader.readLine()) != null){
                //split each line to check if the search id is present
                lineArr = line.split(",");
                if (lineArr[0].equals(id)){
                    checkInDate = lineArr[5];
                    checkOutDate = lineArr[6];
                    room = lineArr[7];
                    //if the search id is present, add the old data
                    //plus the new data in to the temp array

                }else{
                    tempArray.add(line);
                }
            }
            fr.close();
        } catch(Exception e){  
            e.printStackTrace();
        }

        //temporary array to hold the data that is read from file
        String[] roomNum = {"101", "102", "103", "104", "105","106","107","108","109","110","201","202","203","204","205","206","207","208","209","210"};
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            BufferedReader reader = new BufferedReader(new FileReader("roomAvailability.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("roomAvailability.txt.new"));
            String line;
            RoomSettings r = new RoomSettings();
            Date newCheckOutDate =r.getNewCheckOut();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String dateString = parts[0];
                Date date = dateFormat.parse(dateString);
                if (!date.before(dateFormat.parse(checkInDate)) && !date.after(newCheckOutDate))  {
                    String[] roomAvailability = parts[1].split(",");
                    for (int i = 0; i < roomNum.length; i++) {
                        if(roomNum[i].equals(room)) {
                            // Update the availability for the selected room on the selected date range
                            for (int j = 0; j < 3; j++) {
                                roomAvailability[i] = room;
                            }
                            // Write the updated availability to the new file
                            writer.write(dateString + ":" + String.join(",", roomAvailability) + "\n");
                        }
                    }
                } else {
                    // Write the original line to the new file if the date range doesn't match
                    writer.write(line + "\n");
                }
            }
            reader.close();
            writer.close();
            // Replace the original file with the new file
            Files.move(Paths.get("roomAvailability.txt.new"), Paths.get("roomAvailability.txt"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Error reading/writing file: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }
    }
}



