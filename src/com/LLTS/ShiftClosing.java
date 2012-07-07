package com.LLTS;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.LLTS.Database.DatabaseManager;
import com.LLTS.Database.PlistHelper;
import com.LLTS.Database.RouteInformationDatabaseHelper;
import com.LLTS.Database.TicketPackInformationDatabaseHolder;

public class ShiftClosing extends Activity {

	
	SharedPreferences settings;

	
	Button close;
	EditText lastTicketBarcode;
	EditText routeName;
	TextView routeNumber;
	TextView serialNumber;
	TextView ticketNumber;
	String shiftOrDay;
	LinearLayout shiftSelectionView;
	
	RadioGroup shiftNumberGroup;
	RadioButton shift1;
	RadioButton shift2;
	RadioButton shift3;
	String selectedShift;
	
	
	
	
	DatabaseManager dbManager;
	boolean isTicketInformationStored;
	
	TicketPackInformationDatabaseHolder ticketPackInformationDatabaseHolder;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shift_closing);
		
		dbManager = new DatabaseManager(getApplicationContext());
		close = (Button) findViewById(R.id.Close);
		close.setOnClickListener(new MyOnClickListener());
		settings = getSharedPreferences(PlistHelper.PREFS_NAME, 0);
		lastTicketBarcode = (EditText) findViewById(R.id.LastTicketBarcode);
		routeName = (EditText) findViewById(R.id.RouteName);
		routeNumber = (TextView) findViewById(R.id.RouteNumber);
		serialNumber = (TextView) findViewById(R.id.SerialNo);
		ticketNumber = (TextView) findViewById(R.id.TicketNo);
		shiftSelectionView = (LinearLayout) findViewById(R.id.ShiftValue);
		shiftNumberGroup = (RadioGroup) findViewById(R.id.ShiftRadioGroup);
		shift1 = (RadioButton) findViewById(R.id.Shift1);
		shift2 = (RadioButton) findViewById(R.id.Shift2);
		shift3 = (RadioButton) findViewById(R.id.Shift3);
		
		shiftOrDay = settings.getString("ShifOrDay", "Day");
		if(shiftOrDay.equals("Day")) {
			shiftSelectionView.setVisibility(View.INVISIBLE);
			selectedShift="Day";
			close.setText("Close Day");
		}
		else {
			shiftSelectionView.setVisibility(View.VISIBLE);
			selectedShift="First Shift";
			close.setText("Close Shift");
		}
		
		shiftNumberGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId==shift1.getId()) {
					selectedShift="First Shift";
				}
				else if (checkedId==shift2.getId()) {
					selectedShift="Second Shift";
				}
				else {
					selectedShift="Third Shift";
				}
				
			}
		});
		
		
		
		lastTicketBarcode.addTextChangedListener(new MyOnTextChangeListener());
		dbManager.open();
		//
		isTicketInformationStored = dbManager.isTicketInformationStored();	
		if(isTicketInformationStored) {
			ticketPackInformationDatabaseHolder = dbManager.getTicketInformation();
			int maxLength = ticketPackInformationDatabaseHolder.getDigitInBarcodeValue();
			InputFilter[] FilterArray = new InputFilter[1];
			FilterArray[0] = new InputFilter.LengthFilter(maxLength);
			lastTicketBarcode.setFilters(FilterArray);
			dbManager.close();
		}
		else {
			Toast.makeText(getApplicationContext(), "Ticket Information is not stored !!!!", Toast.LENGTH_LONG).show();
			finish();
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
	
	public class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId()==R.id.Close) {
				dbManager.open();
				if(!dbManager.isPackAlreadyOpen(routeNumber.getText().toString(), serialNumber.getText().toString())) {
					Toast.makeText(getApplicationContext(), "Pack in not open yet !!!", Toast.LENGTH_LONG).show();
					
				} 
				else { 
					int lastTicketNumber = Integer.parseInt(ticketNumber.getText().toString()) - 1;
					dbManager.dailySalesShiftEnds(dbManager.getPackId(routeNumber.getText().toString(), serialNumber.getText().toString()), lastTicketNumber, selectedShift);
					dbManager.dailySalesStarted(dbManager.getPackId(routeNumber.getText().toString(), serialNumber.getText().toString()), lastTicketNumber+1);
					dbManager.close(); 
					Toast.makeText(getApplicationContext(), selectedShift + " closed !!!", Toast.LENGTH_LONG).show();
					finish();
				}
				
			}
			else
				return;
		}
		
		
	}

}
