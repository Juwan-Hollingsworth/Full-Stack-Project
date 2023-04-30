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
public class SHUsers implements Serializable {
    private String userId; //membershioid
    private String accessLevel; //fname
    private String userPassword;
    private String userName; //email

    //define DB connection
    private Connection conn = null;

    public String getUserPassword() {
        return userPassword;
    }

    public void setuserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public boolean ModifyUser(String newUserName, String password, String accessLevel, String userId) {
        
        boolean isSuccessful = false;
        
        try{
            conn = OracleConnection.getConnection();
            
            String sql = "UPDATE Users " +
                        "SET username =?, password =?, accessLevel =?" +
                        "WHERE userid =?";
            
            //create statement
            PreparedStatement stmt
                    = conn.prepareStatement(sql);
            stmt.setString(1, newUserName);
            stmt.setString(2, password);
            stmt.setString(3, accessLevel);
            stmt.setString(4, userId);
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
    
    public boolean DeleteUser(String userId){
        
        boolean isSuccessful = false;
        
        try{
            conn = OracleConnection.getConnection();
            
            String sql = "DELETE FROM users WHERE UserId=?";
            
            //create statement
            PreparedStatement stmt
                    = conn.prepareStatement(sql);
            stmt.setString(1, userId);
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
    
    public String generateId() {
        
         String nextAvailableUserId ="";
        
        try{
            conn = OracleConnection.getConnection();
            String sql = "select max(UserId) from users";
            
            //create statement
            PreparedStatement stmt
                    = conn.prepareStatement(sql);
            //run SQL
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                //combination of userName/pwd is correct
                int incrementedId = Integer.parseInt((rs.getString(1))) + 1;
                nextAvailableUserId = String.valueOf(incrementedId);
            }
        }catch(Exception exp){
            exp.printStackTrace();
        }finally{
            conn = null;
            OracleConnection.closeConnection();
        }   
     
        return nextAvailableUserId;
    }

    public boolean CreateUser(String userId, String userName, String password, String accessLevel) {
        
        boolean isSuccessful = false;
        
        try{
            conn = OracleConnection.getConnection();
            
            String sql = "insert into users values (?,?,?,?)";
            
            //create statement
            PreparedStatement stmt
                    = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            stmt.setString(2, userName);
            stmt.setString(3, password);
            stmt.setString(4, accessLevel);
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
    
    
    public boolean ChangePassword(String password) {
        
        boolean isSuccessful = false;
        
        try{
            conn = OracleConnection.getConnection();
            
            String sql = "UPDATE Users " +
                        "SET Password =? " +
                        "WHERE UserId = ?";
            
            //String sql = "select * from users where "
                    //+ " UserName =? and Password = ?";
            
            //create statement
            PreparedStatement stmt
                    = conn.prepareStatement(sql);
            stmt.setString(1, password);
            stmt.setString(2, UserStatus.getCurrentUserId());
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
    
    public boolean login(String userName, String pwd){
        boolean validated = false;
        try{
            conn = OracleConnection.getConnection();
            String sql = "select * from users where "
                    + " UserName =? and Password = ?";
            //create statement
            PreparedStatement stmt
                    = conn.prepareStatement(sql);
            stmt.setString(1, userName);
            stmt.setString(2, pwd);
            //run SQL
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                //combination of userName/pwd is correct
                validated = true;
                UserStatus.setCurrentUserId ((rs.getString(1)));
                UserStatus.setUserName ((rs.getString(2)));
                UserStatus.setUserAccessLevel ((rs.getString(4)));
            }
        }catch(Exception exp){
            exp.printStackTrace();
        }finally{
            conn = null;
            OracleConnection.closeConnection();
        }
        
        return validated;
    } //login method ends
    
    public List<SHUsers> getUsers(String relationalOperator, String userIdToPass){
        List<SHUsers> mems = new ArrayList<SHUsers>();
        try{
            conn = OracleConnection.getConnection();
            String sql = "select UserID, UserName, AccessLevel, password from Users where UserID " + relationalOperator  + " " + userIdToPass;
            Statement stmt = conn.createStatement();
            //Run SQL
            ResultSet rs = stmt.executeQuery(sql);
            //Process resultset
            while(rs.next()){
                //pull data in resultset //get email
                SHUsers member = new SHUsers();
                member.setUserId(rs.getString(1)); //UserID
                member.setUserName(rs.getString(2)); //UserName
                member.setAccessLevel(rs.getString(3));  //AccessLevel
                member.setuserPassword(rs.getString(4));  //password
                mems.add(member);
            }
        }catch(Exception exp){
           exp.printStackTrace();
        }finally{
           conn = null;
           OracleConnection.closeConnection();
        }
        return mems;
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
