package com.LLTS;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuView extends Activity {

//	ImageView administration;
//	ImageView routeAndTicket;
//	ImageView shiftOrDayClosing;
//	ImageView report;
	String userType;
	
	ListView menuList;
	ArrayAdapter<String> arrayAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_view);
		
		if(getIntent().hasExtra("UserType")) {	
			userType = getIntent().getExtras().getString("UserType");
		}
//		administration = (ImageView) findViewById(R.id.MenuItem1);
//		if(userType.equals("Admin"))
//			administration.setOnClickListener(new MyOnClickListener());
//		else
//			administration.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					Toast.makeText(getApplicationContext(), "You dont have administrative Previlages", Toast.LENGTH_LONG).show();
//				}
//			});
//		routeAndTicket = (ImageView) findViewById(R.id.MenuItem2);
//		routeAndTicket.setOnClickListener(new MyOnClickListener());
//		
//		shiftOrDayClosing = (ImageView) findViewById(R.id.MenuItem3);
//		shiftOrDayClosing.setOnClickListener(new MyOnClickListener());
//		
//		report = (ImageView) findViewById(R.id.MenuItem4);
//		report.setOnClickListener(new MyOnClickListener());
		
		if(userType.equals("Admin")) {
			
			String menuItems[] = {"1. "+getResources().getString(R.string.menu1),"2. "+getResources().getString(R.string.menu2),"3. "+getResources().getString(R.string.menu3)
					,"4. "+getResources().getString(R.string.menu4),"5. "+getResources().getString(R.string.menu5),"6. "+getResources().getString(R.string.menu6)};
			arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , menuItems);
			menuList = (ListView) findViewById(R.id.MenuList);
			menuList.setAdapter(arrayAdapter);
			menuList.setOnItemClickListener(new AdminListOnClickListener());
		
		}
		else if(userType.equals("Sales Clerk")) {
			
			String menuItems[] = {"1. "+getResources().getString(R.string.menu2),"2. "+getResources().getString(R.string.menu3)
					,"3. "+getResources().getString(R.string.menu4),"4. "+getResources().getString(R.string.menu5)};
			arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , menuItems);
			menuList = (ListView) findViewById(R.id.MenuList);
			menuList.setAdapter(arrayAdapter);
			menuList.setOnItemClickListener(new SalesListOnClickListener());
		
		}
		else if(userType.equals("Manager")) {
			
			String menuItems[] = {getResources().getString(R.string.menu2),getResources().getString(R.string.menu3)
					,getResources().getString(R.string.menu4),getResources().getString(R.string.menu5)};
			arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , menuItems);
			menuList = (ListView) findViewById(R.id.MenuList);
			menuList.setAdapter(arrayAdapter);
			menuList.setOnItemClickListener(new SalesListOnClickListener());
		
		}
		
	}
	
	
	public class AdminListOnClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Intent i = new Intent();
			switch (arg2) {
			case 0:
				i.setClass(getApplicationContext(), Administration.class);
				break;
			case 1:
				i.setClass(getApplicationContext(), RouteAndTicket.class);
				break;
			case 2:
				i.setClass(getApplicationContext(), ShiftClosing.class);
				break;
			case 3:
				i.setClass(getApplicationContext(), ReportView.class);
				break;
			default:
				return;
			}
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(i);
		}
    }
	public class SalesListOnClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Intent i = new Intent();
			switch (arg2) {
			case 0:
				i.setClass(getApplicationContext(), RouteAndTicket.class);
				break;
			case 1:
				i.setClass(getApplicationContext(), ShiftClosing.class);
				break;
			case 2:
				i.setClass(getApplicationContext(), ReportView.class);
				break;
			default:
				return;
			}
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(i);
		}
    }
}


