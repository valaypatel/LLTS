package com.LLTS;




import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.LLTS.Database.PlistHelper;



public class Administration extends Activity {

	 SharedPreferences settings;
	 SharedPreferences.Editor editor;
	
	ListView administrativeList;
	ArrayAdapter<String> arrayAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.administration);
		settings = getSharedPreferences(PlistHelper.PREFS_NAME, 0);
		editor = settings.edit();
		String s;
		//if(settings.getString("FirstDayOfTheWeek", "Monday").equals(null))
			s="First Day of Week";
		//else
			//s="First Day of Week - " + settings.getString("FirstDayOfTheWeek", "Monday");
		String lv_arr[]={"Company Information","Ticket Pack Information",s,"User Management","Shift Information"};
		
		arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , lv_arr);
		administrativeList = (ListView) findViewById(R.id.AdministrativeList);
		administrativeList.setAdapter(arrayAdapter);
		administrativeList.setOnItemClickListener(new MyListOnClickListener());

	}
	public class MyListOnClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Intent i = new Intent();
			switch (arg2) {
			case 0 :
				i.setClass(getApplicationContext(), CompanyInformation.class);
				break;
			case 1 :
				i.setClass(getApplicationContext(), TicketPackInformation.class);
				break;
			case 2 :
				showFirstDayOfWeek();
				return;
			case 3 :
				i.setClass(getApplicationContext(), UserManagement.class);
				break;
			case 4 :
				i.setClass(getApplicationContext(), Information.class);
				break;
			default:
				return;
			}
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(i);
		}
		
	}
	public void showFirstDayOfWeek() {
		
		final CharSequence[] items = {"Monday", "Tuesday", "Wednesday","Thursday","Friday","Saturday","Sunday"};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Select "+ getApplicationContext().getString(R.string.first_day_of_week));
		builder.setItems(items, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
				editor.putString("FirstDayOfTheWeek", (String) items[item]);
				editor.commit();
				Toast.makeText(getApplicationContext(), "First day of Week set to : "+items[item], Toast.LENGTH_SHORT).show();
		    }
		});
		AlertDialog alert = builder.create();
		alert.show();
	}
}
