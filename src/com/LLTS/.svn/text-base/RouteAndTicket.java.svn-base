package com.LLTS;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RouteAndTicket extends Activity {

	ListView routeAndTicketList;
	String lv_arr[]={"Open new Pack","Open Discontinued Pack or Close Unsold pack","Close Packs"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rout_and_ticket);
		
		routeAndTicketList = (ListView) findViewById(R.id.RouteAndTicketManagementList);		
		routeAndTicketList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , lv_arr));
		routeAndTicketList.setOnItemClickListener(new MyListOnClickListener());

	}
	
	public class MyListOnClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Intent i = new Intent();
			switch (arg2) {
			case 0 :
				i.setClass(getApplicationContext(), OpenNewPack.class);
				break;
			case 1 :
				i.setClass(getApplicationContext(), OpenDiscontinuedPackOrCloseUnsoldpack.class);
				break;
			case 2 :
				i.setClass(getApplicationContext(), ClosePacks.class);
				break;
			default:
				return;
			}
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(i);
		}
		
	}

	
}
