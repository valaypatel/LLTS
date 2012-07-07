package com.LLTS;

import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.LLTS.Database.DatabaseManager;
import com.LLTS.Database.PackInformationDatabaseHolder;
import com.LLTS.Database.RouteInformationDatabaseHelper;
import com.LLTS.Database.TicketPackInformationDatabaseHolder;


public class OpenNewPack extends Activity {

	
	Button open;
	Button cancle;
	EditText slotNumber;
	EditText packBarcode;
	EditText routeName;
	EditText ticketValue;
	TextView routeNumber;
	TextView serialNumber;
	TextView ticketNumber;
	
	
	DatabaseManager dbManager;
	boolean isTicketInformationStored;
	
	TicketPackInformationDatabaseHolder ticketPackInformationDatabaseHolder;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.open_new_pack);
		
		dbManager = new DatabaseManager(getApplicationContext());
		open = (Button) findViewById(R.id.Open);
		cancle = (Button) findViewById(R.id.Cancle);
		open.setOnClickListener(new MyOnClickListener());
		cancle.setOnClickListener(new MyOnClickListener());
		
		packBarcode = (EditText) findViewById(R.id.Barcode);
		routeName = (EditText) findViewById(R.id.RouteName);
		slotNumber = (EditText) findViewById(R.id.SlotNo);
		ticketValue = (EditText) findViewById(R.id.TicketValue);
		routeNumber = (TextView) findViewById(R.id.RouteNumber);
		serialNumber = (TextView) findViewById(R.id.SerialNo);
		ticketNumber = (TextView) findViewById(R.id.TicketNo);
		
		
		packBarcode.addTextChangedListener(new MyOnTextChangeListener());
		packBarcode.setOnKeyListener(new MyOnKeyListener());
		dbManager.open();
		//Toast.makeText(getApplicationContext(), dbManager.isCompanyInformationStored()+"", Toast.LENGTH_LONG).show();
		isTicketInformationStored = dbManager.isTicketInformationStored();		
		dbManager.close();
		Log.i("Open Pack", isTicketInformationStored+"");
		if(!isTicketInformationStored) {
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Ticket Pack Information is not stored. Do you want to store it?").setTitle("Ticket Pack Information").setCancelable(false)
					.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									Intent i = new Intent();
									i.setClass(getApplicationContext(), TicketPackInformation.class);
									i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
									startActivity(i);
									finish();
								}
							}).setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									dialog.dismiss();
								}
							});
			builder.show();
			
		}
		else {
			dbManager.open();
			//Toast.makeText(getApplicationContext(), dbManager.isCompanyInformationStored()+"", Toast.LENGTH_LONG).show();
			
				ticketPackInformationDatabaseHolder = dbManager.getTicketInformation();
				int maxLength = ticketPackInformationDatabaseHolder.getDigitInBarcodeValue();
				InputFilter[] FilterArray = new InputFilter[1];
				FilterArray[0] = new InputFilter.LengthFilter(maxLength);
				packBarcode.setFilters(FilterArray);
			dbManager.close();
		}
		
	}
	public class MyOnTextChangeListener implements TextWatcher {

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			if(ticketPackInformationDatabaseHolder.getDigitInBarcodeValue()==s.toString().length()) {
				routeNumber.setText(s.subSequence(0, ticketPackInformationDatabaseHolder.getFirstGroupValue()));
				serialNumber.setText(s.subSequence(ticketPackInformationDatabaseHolder.getFirstGroupValue(), ticketPackInformationDatabaseHolder.getFirstGroupValue()+ticketPackInformationDatabaseHolder.getSecondGroupValue()));
				ticketNumber.setText(s.subSequence(ticketPackInformationDatabaseHolder.getFirstGroupValue()+ticketPackInformationDatabaseHolder.getSecondGroupValue(), s.length()));
				dbManager.open();
				//Toast.makeText(getApplicationContext(), dbManager.isCompanyInformationStored()+"", Toast.LENGTH_LONG).show();
					RouteInformationDatabaseHelper routeInformationDatabaseHelper = dbManager.getRouteName(routeNumber.getText().toString());
					if(!routeInformationDatabaseHelper.equals(null)) {
						routeName.setText(routeInformationDatabaseHelper.getRouteNameValue());
					}
				dbManager.close();
				slotNumber.requestFocus();
			}
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class MyOnKeyListener implements android.view.View.OnKeyListener {

		
		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
//			if(keyCode==KeyEvent.KEYCODE_ENTER)
//				return true;
			return false;
		}
		
	}
	
	
	public class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId()==R.id.Open)
				saveInfo();
			else if(v.getId()==R.id.Cancle)
				finish();
			else
				return;
		}
		
		
	}
	
	public void saveInfo() {		
	
		
		if(packBarcode.equals(null) || (slotNumber.equals(null)) || packBarcode.length()!=ticketPackInformationDatabaseHolder.getDigitInBarcodeValue()) {
			Toast.makeText(getApplicationContext(), "Enter All Values!!!", Toast.LENGTH_LONG).show();
			return;			
		}
		float ticketPrice =0;
		try {
			
			ticketPrice = Float.parseFloat(ticketValue.getText().toString());
		}
		catch (NumberFormatException e) {
			Toast.makeText(getApplicationContext(), "Enter Proper Ticket Value !!!", Toast.LENGTH_LONG).show();
			return;
		}
		dbManager.open();  
		if(dbManager.isPackAlreadyOpen(routeNumber.getText().toString(),serialNumber.getText().toString())) {
			dbManager.close();
			Toast.makeText(getApplicationContext(), "Pack is Already in System!!!", Toast.LENGTH_LONG).show();
			return;			
		}
		if(dbManager.isSlotFree(slotNumber.getText().toString())) {
			dbManager.close();
			Toast.makeText(getApplicationContext(), "Slot is not Free !!!", Toast.LENGTH_LONG).show();
			return;			
		}
			
		dbManager.close();
		if(routeName.getText().toString().length()>0) {
			dbManager.open();        
			RouteInformationDatabaseHelper routeInformationDatabaseHelper = dbManager.getRouteName(routeNumber.getText().toString());
			routeInformationDatabaseHelper.setRouteNameValue(routeName.getText().toString());
			routeInformationDatabaseHelper.setRouteNumberValue(routeNumber.getText().toString());
			dbManager.storeRoute(routeInformationDatabaseHelper);
	        
	        dbManager.close();
		}
		
		PackInformationDatabaseHolder packInformationDatabaseHolder = new PackInformationDatabaseHolder();
		packInformationDatabaseHolder.setRouteNumberValue(routeNumber.getText().toString());
		packInformationDatabaseHolder.setSerialNumberValue(serialNumber.getText().toString());
		packInformationDatabaseHolder.setFirstTicketNumberValue(Integer.parseInt(ticketNumber.getText().toString()));
		packInformationDatabaseHolder.setSlotNumberValue(slotNumber.getText().toString());
		packInformationDatabaseHolder.setTicketPriceValue(ticketPrice);
		packInformationDatabaseHolder.setPackOpenDateValue(new Date().getTime());
		
		packInformationDatabaseHolder.setStatusValue("Open");
		
		dbManager.open();        
        		
		dbManager.openPack(packInformationDatabaseHolder);
		
        
        
        int temp = (int)packInformationDatabaseHolder.getFirstTicketNumberValue() + (int)(dbManager.getTicketInformation().getTickeValueValue()/packInformationDatabaseHolder.getTicketPriceValue());
        Log.i("Application",temp +"");
        dbManager.close();
        Toast.makeText(getApplicationContext(), "Pack Opened!!!", Toast.LENGTH_LONG).show();
        finish();
        
		
	}

}
