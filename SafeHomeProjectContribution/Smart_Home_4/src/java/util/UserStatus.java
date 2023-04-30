/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Dregs
 */
public class UserStatus {
     private static String userAccessLevel = "-1";    
     private static String currentUserId = "-1";   
     private static String ModificationUserId = "-1"; 
     private static String ModificationObjectId = "-1"; 
     private static String temporaryVariable = "";
     private static String filterData = "";
     private static String userName = "";

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        UserStatus.userName = userName;
    }
     
    public static void setFilterData(String filterData) {
        UserStatus.filterData = filterData;
    }

    public static String getFilterData() {
        return filterData;
    }
     
     public static void setTemporaryVariable(String passedVariable){
        temporaryVariable = passedVariable;
     }
     
     public static String getTemporaryVariable(){
        return temporaryVariable;
     }
     
     public static void setUserAccessLevel(String accessLevel){
        userAccessLevel = accessLevel;
     }
     
     public static String getUserAccessLevel(){
        return userAccessLevel;
     }
     
     public static void setCurrentUserId(String UserId){
        currentUserId = UserId;
     }
     
     public static String getCurrentUserId(){
        return currentUserId;
     }
     
     public static void setModificationUserId(String UserId){
        ModificationUserId = UserId;
     }
     
     public static String getModificationUserId(){
        return ModificationUserId;
     }
     
     public static void setModificationObjectId(String objectId){
        ModificationObjectId = objectId;
     }
     
     public static String getModificationObjectId(){
        return ModificationObjectId;
     }
}
