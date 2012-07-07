package com.LLTS.Database;

public class UserManagementDatabaseHolder {
	
	public static String userID = "UserID";
	public static String userName = "UserName";
	public static String password = "Password";
	public static String group = "UserGroup";

	
	int userIDValue;
	String userNameValue;
	String passwordValue;
	String groupValue;
	public int getUserIDValue() {
		return userIDValue;
	}
	public void setUserIDValue(int userIDValue) {
		this.userIDValue = userIDValue;
	}
	public String getUserNameValue() {
		return userNameValue;
	}
	public void setUserNameValue(String userNameValue) {
		this.userNameValue = userNameValue;
	}
	public String getPasswordValue() {
		return passwordValue;
	}
	public void setPasswordValue(String passwordValue) {
		this.passwordValue = passwordValue;
	}
	public String getGroupValue() {
		return groupValue;
	}
	public void setGroupValue(String groupValue) {
		this.groupValue = groupValue;
	}


}
