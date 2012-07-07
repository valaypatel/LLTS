package com.LLTS;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.LLTS.Database.DatabaseManager;
import com.LLTS.Database.UserManagementDatabaseHolder;

public class LLTS extends Activity {
	/** Called when the activity is first created. */

	EditText username;
	EditText password;
	Button loginBtn;
	CheckBox rememberMe;
	TextView usernameTxt;
	TextView passwordTxt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		username = (EditText) findViewById(R.id.UserName);
		password = (EditText) findViewById(R.id.Password);
		usernameTxt = (TextView) findViewById(R.id.UsernameTxt);
		passwordTxt = (TextView) findViewById(R.id.PasswordTxt);
		rememberMe = (CheckBox) findViewById(R.id.RememberMeChk);
		loginBtn = (Button) findViewById(R.id.UserLogin);
		loginBtn.setOnClickListener(new MyOnClickListener());
		
		DatabaseManager dbManager = new DatabaseManager(getApplicationContext());
		
		dbManager.open();
		int userCount = dbManager.getNumberofUsers();
		if(userCount==0)  {
			UserManagementDatabaseHolder user = new UserManagementDatabaseHolder();
			user.setUserIDValue(userCount+1);
			user.setUserNameValue("admin");
			user.setGroupValue("Admin");
			user.setPasswordValue("admin");
			dbManager.insertUserInfornation(user);
			
		}
		dbManager.close();

	}

	public class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			if (username.length() == 0) {
				usernameTxt.setTextColor(Color.RED);
				return;
			} else
				usernameTxt.setTextColor(Color.BLACK);

			if (password.length() == 0) {
				password.setTextColor(Color.RED);
				return;
			} else
				password.setTextColor(Color.BLACK);

			if (rememberMe.isChecked())
				Log.i("Application", "Remember me!!!!!");

			DatabaseManager dbManager = new DatabaseManager(getApplicationContext());
			UserManagementDatabaseHolder user = new UserManagementDatabaseHolder();
			dbManager.open();
			user = dbManager.checkUser(username.getText().toString());
			dbManager.close();
			if(user.getUserIDValue()!=0) {
				if (user.getPasswordValue().equals(password.getText().toString())) {
					
					Intent i = new Intent();
					i.setClass(getApplicationContext(), MenuView.class);
					i.putExtra("UserType", user.getGroupValue());
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(i);
					finish();
				}
				else {
					username.setText("");
					password.setText("");
					usernameTxt.setTextColor(Color.RED);
					passwordTxt.setTextColor(Color.RED);
					return;
				}
			}
			else {
				username.setText("");
				password.setText("");
				usernameTxt.setTextColor(Color.RED);
				passwordTxt.setTextColor(Color.RED);
				return;
			}
		}
	}
}