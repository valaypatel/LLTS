package com.LLTS;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.LLTS.Database.DatabaseManager;
import com.LLTS.Database.PackInformationDatabaseHolder;

public class ClosePacks extends Activity {
	
	
	TableLayout packList;
	
	DatabaseManager dbManager;	
	PackInformationDatabaseHolder openPacks[];
	int openPackCount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.close_packs);
		
		dbManager = new DatabaseManager(getApplicationContext());
		packList = (TableLayout) findViewById(R.id.PackTable);
		dbManager.open();
		openPackCount = dbManager.getNumberOfOpenPacks();		
		dbManager.close();
		
		if(openPackCount>0) {
			//users = new UserManagementDatabaseHolder[userCount];
			dbManager.open();
			openPacks = dbManager.getOpenPacks();		
			dbManager.close();
			setPackList(openPacks);
		}
	}
	
	public void setPackList(PackInformationDatabaseHolder openPacks[]) {
		

		TableRow tableRow;
    	TextView slotNumberText;
    	TextView routeNameText;
    	TextView serialNumber;
    	TextView routeNumber;
    	ImageView closeButton;
    	packList.removeAllViews();
    	dbManager.open();
		for(PackInformationDatabaseHolder pack:openPacks ) {
			int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float)5, getResources().getDisplayMetrics());
			tableRow = new TableRow(getApplicationContext());
			slotNumberText = new TextView(getApplicationContext());
			routeNameText = new TextView(getApplicationContext());
			serialNumber = new TextView(getApplicationContext());
			routeNumber = new TextView(getApplicationContext());
			closeButton = new ImageView(getApplicationContext());
			tableRow.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
			slotNumberText.setText(pack.getSlotNumberValue());
			slotNumberText.setTextSize(14);
			slotNumberText.setTextColor(Color.WHITE);
			slotNumberText.setGravity(Gravity.CENTER);
			slotNumberText.setPadding(0, 0, 0, 0);
			slotNumberText.setWidth((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float)50, getResources().getDisplayMetrics()));
			
			routeNameText.setText(dbManager.getRouteName(pack.getRouteNumberValue()).getRouteNameValue());
			
			routeNameText.setTextSize(14);
			routeNameText.setWidth((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float)90, getResources().getDisplayMetrics()));
			routeNameText.setTextColor(Color.WHITE);
			routeNameText.setGravity(Gravity.CENTER);
			routeNameText.setPadding(padding, 0, 0, 0);
			serialNumber.setText(pack.getSerialNumberValue());
			serialNumber.setTextSize(14);
			serialNumber.setTextColor(Color.WHITE);
			serialNumber.setGravity(Gravity.CENTER);
			serialNumber.setPadding(padding, 0, 0, 0);
			serialNumber.setWidth((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float)80, getResources().getDisplayMetrics()));
			routeNumber.setText(String.valueOf(pack.getRouteNumberValue()));
			routeNumber.setTextSize(14);
			routeNumber.setTextColor(Color.WHITE);
			routeNumber.setGravity(Gravity.CENTER);
			routeNumber.setPadding(padding, 0, 0, 0);
			routeNumber.setWidth((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float)60, getResources().getDisplayMetrics()));
			closeButton.setImageResource(R.drawable.close);
			closeButton.setTag(pack);
			closeButton.setOnClickListener(new MyPackCloseClickListerer());
			closeButton.setPadding(padding*padding, 0, 0, 0);
			tableRow.addView(slotNumberText, 0);
			tableRow.addView(routeNumber, 1);
			tableRow.addView(routeNameText, 2);
			tableRow.addView(serialNumber, 3);
			tableRow.addView(closeButton, 4);
			tableRow.setPadding(padding, padding, padding, padding);
			tableRow.setGravity(Gravity.CENTER_VERTICAL);
			tableRow.setTag(pack);
			packList.addView(tableRow);
		}
		dbManager.close();
	}
	
	public class MyPackCloseClickListerer implements OnClickListener {

		@Override
		public void onClick(View v) {
			PackInformationDatabaseHolder packInformationDatabaseHolder = (PackInformationDatabaseHolder) v.getTag();
			showShift(packInformationDatabaseHolder);
		}
	}
	
	public void showShift(final PackInformationDatabaseHolder packInformationDatabaseHolder) {
		
		final CharSequence[] items = {"First Shift", "Second Shift", "Third Shift","Day"};
		Log.i("Application",packInformationDatabaseHolder.getPackIDValue()+"");
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(false);
		builder.setTitle("Select Shift/Day");
		//builder.setMessage(settings.getString("FirstDayOfTheWeek", "Monday"));
		builder.setItems(items, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
		    	dbManager.open();
		    	int lastTicketValue = (int)packInformationDatabaseHolder.getFirstTicketNumberValue() + (int)(dbManager.getTicketInformation().getTickeValueValue()/packInformationDatabaseHolder.getTicketPriceValue()) -1;
		    	if(dbManager.dailySalesShiftEnds(packInformationDatabaseHolder.getPackIDValue(), lastTicketValue, (String) items[item])) {
					if(dbManager.closePack(packInformationDatabaseHolder.getPackIDValue())) {
						Toast.makeText(getApplicationContext(), "Pack Closed !!!!", Toast.LENGTH_LONG).show();
					}
				}
				dbManager.close();
				dbManager.open();
				openPacks = dbManager.getOpenPacks();		
				dbManager.close();
				setPackList(openPacks);
		    }
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

}
