/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.OracleConnection;
import util.UserStatus;
/**
 *
 * @author jqu
 */
public class SHDevices implements Serializable {
    private String objectID; //membershioid
    private String deviceName; //fname
    private String ipAddress;
    private String deviceLocation; //email
    private String isShared;
    
    //define DB connection
    private Connection conn = null;

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setDeviceLocation(String deviceLocation) {
        this.deviceLocation = deviceLocation;
    }

    public void setIsShared(String isShared) {
        this.isShared = isShared;
    }
    
    public String getObjectID() {
        return objectID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getDeviceLocation() {
        return deviceLocation;
    }

    public String getIsShared() {
        return isShared;
    }
    
public String returnUserID(String userName) {
        
        String returnedUserID = "";
        
        try{
            conn = OracleConnection.getConnection();
            
            String sql = "Select userid from users where username = ?";
            
            //create statement
            PreparedStatement stmt
                    = conn.prepareStatement(sql);
            stmt.setString(1, userName);
            //run SQL
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                //combination of userName/pwd is correct
                returnedUserID = rs.getString(1);
            }
        }catch(Exception exp){
            exp.printStackTrace();
        }finally{
            conn = null;
            OracleConnection.closeConnection();
        }
        
        return returnedUserID;
    }
    
    

    public boolean extendOwnership(String extendUserId, String objectId, String ownershipID) {
        
        boolean isSuccessful = false;
        
        try{
            conn = OracleConnection.getConnection();
            
            String sql = "INSERT INTO ownership VALUES (?,?,?)";
            
            //create statement
            PreparedStatement stmt
                    = conn.prepareStatement(sql);
            stmt.setString(1, ownershipID);            
            stmt.setString(2, objectId);            
            stmt.setString(3, extendUserId);
            //run SQL
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                //combination of userName/pwd is correct
                isSuccessful = true;
            }
        }catch(Exception exp){
            exp.printStackTrace();
        }finally{
            conn = null;
            OracleConnection.closeConnection();
        }
        
        return isSuccessful;
    } 
    
    public boolean ModifyDevice(String DeviceName, String IpAddress, String DeviceLocation, String IsShared,String PassedObjectId) {
        
        boolean isSuccessful = false;
        
        try{
            conn = OracleConnection.getConnection();
            
            String sql = "UPDATE devices " +
                        "SET name =?, ipaddress =?, location =?, shared =?" +
                        "WHERE objectid =?";
            
            //create statement
            PreparedStatement stmt
                    = conn.prepareStatement(sql);
            stmt.setString(1, DeviceName);
            stmt.setString(2, IpAddress);
            stmt.setString(3, DeviceLocation);
            stmt.setString(4, IsShared);
            stmt.setString(5, PassedObjectId);
            //run SQL
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                //combination of userName/pwd is correct
                isSuccessful = true;
            }
        }catch(Exception exp){
            exp.printStackTrace();
        }finally{
            conn = null;
            OracleConnection.closeConnection();
        }
        
        return isSuccessful;
    } 
    
    public boolean DeleteDevice(String objectId){
        
        boolean isSuccessful = false;
        
        try{
            conn = OracleConnection.getConnection();
            
            String sql = "DELETE FROM devices WHERE objectId=?";
            String sql2 = "DELETE FROM ownership WHERE objectId=?";

            //create statement
            PreparedStatement stmt
                    = conn.prepareStatement(sql);
            PreparedStatement stmt2
                    = conn.prepareStatement(sql2);
            
            stmt.setString(1, objectId);
            stmt2.setString(1, objectId);
            //run SQL
            ResultSet rs = stmt.executeQuery();
            ResultSet rs2 = stmt2.executeQuery();
            
            if(rs.next() && rs2.next()){
                //combination of userName/pwd is correct
                isSuccessful = true;
            }
        }catch(Exception exp){
            exp.printStackTrace();
        }finally{
            conn = null;
            OracleConnection.closeConnection();
        }
        
        return isSuccessful;    
    }
    
    public String[] generateId() {
        
         String nextAvailableObjectId[] = new String[2];
        
        try{
            conn = OracleConnection.getConnection();
            String sql = "select max(objectId) from devices";
            String sql2 = "select max(ownershipID) from ownership";
              
            //create statement
            PreparedStatement stmt
                    = conn.prepareStatement(sql);

            PreparedStatement stmt2
                    = conn.prepareStatement(sql2);
            
            //run SQL
            ResultSet rs = stmt.executeQuery();
            ResultSet rs2 = stmt2.executeQuery();            
            if(rs.next() && rs2.next()){
                //combination of userName/pwd is correct
                //System.out.println("null cause error?");
                
                if (rs.getString(1) == null && rs2.getString(1) == null)
                {
                    nextAvailableObjectId[0]="1";
                    nextAvailableObjectId[1]="1";
                }
                else
                {
                    int incrementedObjectId = Integer.parseInt((rs.getString(1))) + 1;
                    int incrementedOwnershipId = Integer.parseInt((rs2.getString(1))) + 1;
                    nextAvailableObjectId[0] = String.valueOf(incrementedObjectId);
                    nextAvailableObjectId[1] = String.valueOf(incrementedOwnershipId);
                }
            }
        }catch(Exception exp){
            exp.printStackTrace();
        }finally{
            conn = null;
            OracleConnection.closeConnection();
        }   
     
        return nextAvailableObjectId;
    }

    public boolean CreateDevice(String deviceId, String deviceName, String ipAddress, String location, String shareStatus, String userId, String ownershipID) {
        
        boolean isSuccessful = false;
        
        try{
            conn = OracleConnection.getConnection();
            
            System.out.println("gfd " + deviceId + " " + deviceName + " "+ ipAddress + " "+ location + " "+ shareStatus + " "+ userId + " " + ownershipID);
            
            String sql = "insert into devices values (?,?,?,?,?)";
            String sql2 = "insert into ownership values (?,?,?)";
            
            //create statement
            PreparedStatement stmt
                    = conn.prepareStatement(sql);

            PreparedStatement stmt2
                    = conn.prepareStatement(sql2);
            
            
            stmt.setString(1, deviceId);
            stmt.setString(2, deviceName);
            stmt.setString(3, ipAddress);
            stmt.setString(4, location);
            stmt.setString(5, shareStatus);
            
            stmt2.setString(1, ownershipID);
            stmt2.setString(2, deviceId);
            stmt2.setString(3, userId);
            
            System.out.println("saasa "+location);
            System.out.println("dqwqwdq " + deviceName);
            
            //run SQL
            ResultSet rs = stmt.executeQuery();
            ResultSet rs2 = stmt2.executeQuery();
            
            if(rs.next() && rs2.next()){
                //combination of userName/pwd is correct
                isSuccessful = true;
            }
        }catch(Exception exp){
            exp.printStackTrace();
        }finally{
            conn = null;
            OracleConnection.closeConnection();
        }
        
        return isSuccessful;
    }    
    
    
    public List<SHDevices> getDevices(String ObjectID){
        List<SHDevices>  deviceList = new ArrayList<SHDevices>();
        try{
            conn = OracleConnection.getConnection();
            //String sql = "select UserID, UserName, AccessLevel, password from Users where UserID " + relationalOperator  + " " + userIdToPass;
            String sql="";
            
               System.out.println("aaaaaaaaa "+ObjectID);
            
            if (!ObjectID.equals("-1"))
            {
                System.out.println("hhhhhhhhhh"+UserStatus.getModificationObjectId());
                sql = "select objectid, name, ipaddress, location, shared from devices where objectid = " + ObjectID;
            }
            else if ((UserStatus.getUserAccessLevel().equals("1")) && (UserStatus.getFilterData().equals("")))
            {
                sql = "select distinct devices.objectid, name, ipaddress, location, shared from devices, ownership where (ownership.userid ="+ UserStatus.getCurrentUserId() +" and ownership.objectid = devices.objectid) or devices.shared = 1";
            }
            else if ((UserStatus.getUserAccessLevel().equals("0")) && (UserStatus.getFilterData().equals("")))
            {
               sql = "select distinct devices.objectid, name, ipaddress, location, shared from devices"; 
            }
            else if ((UserStatus.getUserAccessLevel().equals("1")) && (!UserStatus.getFilterData().equals("")))
            {
                sql = "select distinct devices.objectid, name, ipaddress, location, shared from devices, ownership where (ownership.userid ="+ UserStatus.getCurrentUserId() +" and ownership.objectid = devices.objectid) or devices.shared = 1 and location = " + UserStatus.getFilterData();
            }
            else if ((UserStatus.getUserAccessLevel().equals("0")) && (!UserStatus.getFilterData().equals("")))
            {
               sql = "select distinct devices.objectid, name, ipaddress, location, shared from devices where devices.location ='"+ UserStatus.getFilterData()+"'" ; 
               System.out.println("tsr");
            }
            
            UserStatus.setFilterData("");
            Statement stmt = conn.createStatement();
            //Run SQL
            ResultSet rs = stmt.executeQuery(sql);
            //Process resultset

            while(rs.next()){
                //pull data in resultset //get email
                SHDevices userDevice = new SHDevices();
                userDevice.setObjectID(rs.getString(1)); //UserID
                userDevice.setDeviceName(rs.getString(2)); //UserName
                userDevice.setIpAddress(rs.getString(3));  //AccessLevel
                userDevice.setDeviceLocation(rs.getString(4));  //password
                userDevice.setIsShared(rs.getString(5)); 
                deviceList.add(userDevice);
                 System.out.println(rs.getString(4));
            }
        }catch(Exception exp){
           exp.printStackTrace();
        }finally{
           conn = null;
           OracleConnection.closeConnection();
        }
        return deviceList;
    }//end getMembers()
    
    //test functions in Membership
    public static void main(String[] args){
        String email = "admin";
        String password = "admin";
        
        SHUsers mem = new SHUsers();
        if( mem.login(email, password) ){
            System.out.println(email + 
                    " Welcome to CSCI3320");
        }
        else {
            System.out.println(email + 
                    " does not has access!!!");
        }
        
    }
}
