/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hotelroombooking;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zheng
 */
public final class RoomBooking extends javax.swing.JFrame {
    private int bookingId;
    private String name2;
    private String phone;
    private String ic;
    private String email;
    private String inDate ;
    private String outDate ;
    private String roomId;
    private String amount;
    
    
    /**
     * Creates new form RoomBooking
     */
    public RoomBooking() {
        initComponents();
        setLocationRelativeTo(null);
        
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month= calendar.get(Calendar.MONTH);
        int year=calendar.get(Calendar.YEAR);
        cmbInMonth.setSelectedItem(String.valueOf(month+1));
        cmbInYear.setSelectedItem(String.valueOf(year));

        cmbOutMonth.setSelectedItem(String.valueOf(month+1));
        cmbOutYear.setSelectedItem(String.valueOf(year));
        
 
        lblId.setText(String.valueOf("No:"+(getBookingId()+1)));
 
        
    }
    public void setCusName2(){
        name2 = txtName.getText();
        JOptionPane.showMessageDialog(null, txtName.getText(),"Error",JOptionPane.ERROR_MESSAGE);
        
    }
    public String getCusName(){
        return name2;
    } 
    public String getInDate()
    {
        int inDay = Integer.parseInt(cmbInDay.getSelectedItem().toString());
        int inMonth = Integer.parseInt(cmbInMonth.getSelectedItem().toString());
        int inYear = Integer.parseInt(cmbInYear.getSelectedItem().toString());
        String inDate = inDay+"-" +inMonth+"-"+inYear;
        return inDate;
    }
    public String getOutDate()
    {
        int outDay = Integer.parseInt(cmbOutDay.getSelectedItem().toString());
        int outMonth = Integer.parseInt(cmbOutMonth.getSelectedItem().toString());
        int outYear = Integer.parseInt(cmbOutYear.getSelectedItem().toString());
        String outDate = outDay+"-" +outMonth+"-"+outYear;
        return outDate;
    }
    public long getNumDays(){
        String inDate = getInDate();
        String outDate = getOutDate();
        int total=0;
        long daysDiff=0;
        try {
        // Convert `String` to `Date`
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            Date dateBefore = sdf.parse(inDate);
            Date dateAfter = sdf.parse(outDate);
            
            if(dateAfter.before(dateBefore)|| dateAfter.equals(dateBefore)){
                JOptionPane.showMessageDialog(null, "Invalid check in date","Error",JOptionPane.ERROR_MESSAGE);
            }else{ 

            // Calculate the number of days between dates
                long timeDiff = Math.abs(dateAfter.getTime() - dateBefore.getTime());
                daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
                return daysDiff;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return daysDiff;
    }
    public int getTotal(){
        int total=0;
        long daysDiff = getNumDays();
        total = (int) (daysDiff*350*110/100+10*daysDiff);
        return total;
    }
  public int getBookingId() {
    int highestNumber = 0;
    String fileName = "bookingDetails.txt";
    File file = new File(fileName);

    try {
        BufferedReader br = new BufferedReader(new FileReader(file));
        // get line from txt file
        Object[] tableLines = br.lines().toArray();
        // extract data from lines
        for (Object tableLine : tableLines) {
            String line = tableLine.toString().trim();
            String[] dataRow = line.split(",");
            int number = Integer.parseInt(dataRow[0]);
            // search highest Number
            if (number > highestNumber) {
                highestNumber = number;
            }
        }
        br.close();
    } catch (FileNotFoundException ex) {
        Logger.getLogger(RoomBooking.class.getName()).log(Level.SEVERE, null, ex);
        return 0;
    } catch (IOException ex) {
        Logger.getLogger(RoomBooking.class.getName()).log(Level.SEVERE, null, ex);
        return 0;
    }catch (NumberFormatException e){
        
    }

    return highestNumber;
}


    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox4 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnPay = new javax.swing.JButton();
        lblId = new javax.swing.JLabel();
        txtIcNo = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPhoneNo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbOutDay = new javax.swing.JComboBox<>();
        cmbOutMonth = new javax.swing.JComboBox<>();
        cmbOutYear = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cmbRoom = new javax.swing.JComboBox<>();
        btnSearchRoom = new javax.swing.JButton();
        cmbInDay = new javax.swing.JComboBox<>();
        cmbInMonth = new javax.swing.JComboBox<>();
        cmbInYear = new javax.swing.JComboBox<>();
        txtAmount = new javax.swing.JTextField();
        btnCal = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(72, 105, 149));
        jPanel1.setForeground(new java.awt.Color(236, 242, 247));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(197, 230, 239));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Room Booking");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        btnReset.setBackground(new java.awt.Color(236, 242, 247));
        btnReset.setForeground(new java.awt.Color(72, 105, 149));
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel1.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 370, 102, -1));

        btnExit.setBackground(new java.awt.Color(236, 242, 247));
        btnExit.setForeground(new java.awt.Color(72, 105, 149));
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel1.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 370, 102, -1));

        btnPay.setBackground(new java.awt.Color(236, 242, 247));
        btnPay.setForeground(new java.awt.Color(72, 105, 149));
        btnPay.setText("Make Payment");
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });
        jPanel1.add(btnPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 370, -1, -1));

        lblId.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblId.setForeground(new java.awt.Color(236, 242, 247));
        lblId.setText("No");
        jPanel1.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 37, -1));

        txtIcNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIcNoActionPerformed(evt);
            }
        });
        jPanel1.add(txtIcNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 158, -1));
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 158, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(236, 242, 247));
        jLabel2.setText("Customer Name:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 100, -1));
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, 158, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(236, 242, 247));
        jLabel3.setText("IC/Passpord Number:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, -1, -1));
        jPanel1.add(txtPhoneNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 158, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(236, 242, 247));
        jLabel5.setText("Email:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 180, 40, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(236, 242, 247));
        jLabel4.setText("Conatact Number:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(236, 242, 247));
        jLabel7.setText("Check-in Date:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(236, 242, 247));
        jLabel8.setText("Check-out Date:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 100, -1));

        cmbOutDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cmbOutDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbOutDayActionPerformed(evt);
            }
        });
        jPanel1.add(cmbOutDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, 50, -1));

        cmbOutMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cmbOutMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbOutMonthActionPerformed(evt);
            }
        });
        jPanel1.add(cmbOutMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, 50, -1));

        cmbOutYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023", "2024", "2025", "2026" }));
        cmbOutYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbOutYearActionPerformed(evt);
            }
        });
        jPanel1.add(cmbOutYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, 60, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(236, 242, 247));
        jLabel6.setText("Amount:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, 60, -1));

        cmbRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRoomActionPerformed(evt);
            }
        });
        jPanel1.add(cmbRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, 93, -1));

        btnSearchRoom.setBackground(new java.awt.Color(236, 242, 247));
        btnSearchRoom.setForeground(new java.awt.Color(72, 105, 149));
        btnSearchRoom.setText("Search Room");
        btnSearchRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchRoomActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearchRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 280, -1, -1));

        cmbInDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cmbInDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbInDayActionPerformed(evt);
            }
        });
        jPanel1.add(cmbInDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 50, -1));

        cmbInMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cmbInMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbInMonthActionPerformed(evt);
            }
        });
        jPanel1.add(cmbInMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, 50, -1));

        cmbInYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023", "2024", "2025", "2026", "2027" }));
        cmbInYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbInYearActionPerformed(evt);
            }
        });
        jPanel1.add(cmbInYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 60, -1));
        jPanel1.add(txtAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 93, -1));

        btnCal.setBackground(new java.awt.Color(236, 242, 247));
        btnCal.setForeground(new java.awt.Color(72, 105, 149));
        btnCal.setText("Calculate");
        btnCal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalActionPerformed(evt);
            }
        });
        jPanel1.add(btnCal, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 310, 98, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(236, 242, 247));
        jLabel9.setText("Room Id:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 60, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 560, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRoomActionPerformed
       
    }//GEN-LAST:event_cmbRoomActionPerformed

    private void cmbInDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbInDayActionPerformed
     

    }//GEN-LAST:event_cmbInDayActionPerformed

    private void cmbInMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbInMonthActionPerformed
         Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int inMonth = Integer.parseInt(cmbInMonth.getSelectedItem().toString());
        int inYear = Integer.parseInt(cmbInYear.getSelectedItem().toString());
        int daysInMonth;


        try{
            switch(inMonth){
                case 2:
                    
                    if((inYear%4==0 )){
                        daysInMonth=29;
                        break;
                    }else{
                        daysInMonth=28;
                        break;
                    }
                case 4:
                case 6:
                case 9:
                case 11:
                    daysInMonth=30;
                    break;
                default:
                    daysInMonth=31;
                    break;
            }
            
            
            cmbInDay.removeAllItems();
                for(int i=1;i<=daysInMonth;i++){
                    cmbInDay.addItem(String.valueOf(i));
        }
            cmbInDay.setSelectedItem(String.valueOf(day));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_cmbInMonthActionPerformed

    private void cmbInYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbInYearActionPerformed

    }//GEN-LAST:event_cmbInYearActionPerformed

    private void cmbOutMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbOutMonthActionPerformed
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int outMonth = Integer.parseInt(cmbOutMonth.getSelectedItem().toString());
        int outYear = Integer.parseInt(cmbOutYear.getSelectedItem().toString());
        int daysInMonth;


        try{
            switch(outMonth){
                case 2:
                    
                    if((outYear%4==0 )){
                        daysInMonth=29;
                        break;
                    }else{
                        daysInMonth=28;
                        break;
                    }
                case 4:
                case 6:
                case 9:
                case 11:
                    daysInMonth=30;
                    break;
                default:
                    daysInMonth=31;
                    break;
            }
            
            
            cmbOutDay.removeAllItems();
                for(int i=1;i<=daysInMonth;i++){
                    cmbOutDay.addItem(String.valueOf(i));
        }
            cmbOutDay.setSelectedItem(String.valueOf(day+1));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_cmbOutMonthActionPerformed

    private void cmbOutYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbOutYearActionPerformed
 
    }//GEN-LAST:event_cmbOutYearActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtName.setText("");
        txtIcNo.setText("");
        txtPhoneNo.setText("");
        txtEmail.setText("");
        cmbInDay.setSelectedIndex(0);
        cmbInMonth.setSelectedIndex(0);
        cmbInYear.setSelectedIndex(0);
        cmbInDay.setSelectedIndex(0);
        cmbInMonth.setSelectedIndex(0);
        cmbInYear.setSelectedIndex(0);
        cmbRoom.setSelectedIndex(0);
        txtAmount.setText("");
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        Menu m = new Menu();
        m.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void cmbOutDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbOutDayActionPerformed
         
    }//GEN-LAST:event_cmbOutDayActionPerformed

    private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayActionPerformed
        String name,phone, ic,email, amount, inDate,outDate,roomId;
        int bookingId;
        int numDays= (int)getNumDays();
        
        
        bookingId=1+getBookingId();
        name=txtName.getText();
        phone=txtPhoneNo.getText();
        ic=txtIcNo.getText();
        email=txtEmail.getText();
        inDate = getInDate();
        outDate = getOutDate();
        roomId=String.valueOf(cmbRoom.getSelectedItem());
        amount=txtAmount.getText();
        
        Validation val = new Validation(name,ic,phone,email,inDate,outDate,amount);
        try{
            boolean isValid = val.formValidation();
            if(isValid){
                RoomSettings obj2 = new RoomSettings(getInDate(), getOutDate(), numDays, roomId);
                obj2.updateRoomAvailability();
                
                //add record into text file
                FileHandling obj1 = new FileHandling(bookingId,name,phone,ic,email,inDate,outDate,roomId,amount);
                String res=obj1.writeRecord();
                if("Success".equals(res))
                    JOptionPane.showMessageDialog(null,"Registration completed.","Success",JOptionPane.INFORMATION_MESSAGE);
                else            
                    JOptionPane.showMessageDialog(null, "Could not write into file due to "+res,"Error",JOptionPane.ERROR_MESSAGE);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date currentDate = new Date();
                String formattedDate = sdf.format(currentDate);
                
                
                //create receipt
                Receipt re = new Receipt();
                re.setArea(String.valueOf(bookingId), name, phone, email, ic, getInDate(), getOutDate(), String.valueOf(getNumDays()), roomId, String.valueOf(getTotal()), String.valueOf(350*getNumDays()*10/100), String.valueOf(10*getNumDays()));
                re.setVisible(true);
                this.dispose();

                
            }else{

            }
        }catch(ParseException e){
            
        }
        
        
        
        
    }//GEN-LAST:event_btnPayActionPerformed
    
    
    
    private void btnCalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalActionPerformed
        txtAmount.setText(String.valueOf(getTotal()));
 
    }//GEN-LAST:event_btnCalActionPerformed

    private void btnSearchRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchRoomActionPerformed
        int numDays = (int)getNumDays();
        
       
        //get available rrom
        cmbRoom.removeAllItems();
        RoomSettings obj1=new RoomSettings(numDays,getInDate(),getOutDate());
        String[] availableRooms = obj1.getAvailableRooms();
        for (int i = 0; i < availableRooms.length; i++) {
            if (availableRooms[i]!=null){
                cmbRoom.addItem(availableRooms[i]);
            }
        }
        
    }//GEN-LAST:event_btnSearchRoomActionPerformed

    private void txtIcNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIcNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIcNoActionPerformed

 
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RoomBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RoomBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RoomBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RoomBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RoomBooking().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCal;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnPay;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearchRoom;
    private javax.swing.JComboBox<String> cmbInDay;
    private javax.swing.JComboBox<String> cmbInMonth;
    private javax.swing.JComboBox<String> cmbInYear;
    private javax.swing.JComboBox<String> cmbOutDay;
    private javax.swing.JComboBox<String> cmbOutMonth;
    private javax.swing.JComboBox<String> cmbOutYear;
    private javax.swing.JComboBox<String> cmbRoom;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblId;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtIcNo;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhoneNo;
    // End of variables declaration//GEN-END:variables
}


