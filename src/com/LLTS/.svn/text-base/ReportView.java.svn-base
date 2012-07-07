package com.LLTS;

import java.util.Date;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.LLTS.Database.DailySalesDatabaseHelper;
import com.LLTS.Database.DatabaseManager;
import com.LLTS.Database.PackInformationDatabaseHolder;

public class ReportView extends Activity {

	
	TableLayout dailySalesList;
	TextView reportDate;
	
	DatabaseManager dbManager;	
	DailySalesDatabaseHelper dailySales[];
	int dailySalesCount;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report_view);
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); 
		dbManager = new DatabaseManager(getApplicationContext());
		
		dailySalesList = (TableLayout) findViewById(R.id.ReportTable);
		reportDate = (TextView) findViewById(R.id.ReportDate);
		
		Date todayDate = new Date();
		//todayDate.setMonth(8);
		reportDate.setText(todayDate.getDate()+"/"+todayDate.getMonth()+"/"+(todayDate.getYear()+1900));
		dbManager.open();
		dailySales = dbManager.getDailySalesByDate(todayDate);	
		dbManager.close();
		
		setReport(dailySales);
		
	}
	
	public void setReport(DailySalesDatabaseHelper dailySales[]) {
		

		TableRow tableRow;
    	TextView slotNumberText;
    	TextView routeNameText;
    	TextView serialNumber;
    	TextView routeNumber;
    	TextView openTicketNumber;
    	TextView closeTicketNumber;
    	TextView ticketSoldNumber;
    	TextView totalSales;
    	dailySalesList.removeAllViews();
    	dbManager.open();
		for(DailySalesDatabaseHelper dailySale:dailySales ) {
			int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float)5, getResources().getDisplayMetrics());
			PackInformationDatabaseHolder pack = dbManager.getPackWithPackID(dailySale.getPackIDValue());
			tableRow = new TableRow(getApplicationContext());
			slotNumberText = new TextView(getApplicationContext());
			routeNameText = new TextView(getApplicationContext());
			serialNumber = new TextView(getApplicationContext());
			routeNumber = new TextView(getApplicationContext());
			openTicketNumber = new TextView(getApplicationContext());
			closeTicketNumber = new TextView(getApplicationContext());
			ticketSoldNumber = new TextView(getApplicationContext());
			totalSales = new TextView(getApplicationContext());
			
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
			
			openTicketNumber.setText(dailySale.getOpenTicketNumberValue()+"");
			openTicketNumber.setTextSize(14);
			openTicketNumber.setTextColor(Color.WHITE);
			openTicketNumber.setGravity(Gravity.CENTER);
			openTicketNumber.setPadding(padding, 0, 0, 0);
			openTicketNumber.setWidth((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float)80, getResources().getDisplayMetrics()));
			
			closeTicketNumber.setText(dailySale.getCloseTicketNumberValue()+"");
			closeTicketNumber.setTextSize(14);
			closeTicketNumber.setTextColor(Color.WHITE);
			closeTicketNumber.setGravity(Gravity.CENTER);
			closeTicketNumber.setPadding(padding, 0, 0, 0);
			closeTicketNumber.setWidth((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float)80, getResources().getDisplayMetrics()));
			
			ticketSoldNumber.setText(""+(dailySale.getCloseTicketNumberValue()-dailySale.getOpenTicketNumberValue()));
			ticketSoldNumber.setTextSize(14);
			ticketSoldNumber.setTextColor(Color.WHITE);
			ticketSoldNumber.setGravity(Gravity.CENTER);
			ticketSoldNumber.setPadding(padding, 0, 0, 0);
			ticketSoldNumber.setWidth((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float)80, getResources().getDisplayMetrics()));
			
	
			totalSales.setText(""+(int) ((dailySale.getCloseTicketNumberValue()-dailySale.getOpenTicketNumberValue())*pack.getTicketPriceValue()));
			totalSales.setTextSize(14);
			totalSales.setTextColor(Color.WHITE);
			totalSales.setGravity(Gravity.CENTER);
			totalSales.setPadding(padding, 0, 0, 0);
			totalSales.setWidth((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float)80, getResources().getDisplayMetrics()));
			
			
			
			tableRow.addView(slotNumberText, 0);
			tableRow.addView(routeNumber, 1);
			tableRow.addView(routeNameText, 2);
			tableRow.addView(serialNumber, 3);
			tableRow.addView(openTicketNumber, 4);
			tableRow.addView(closeTicketNumber, 5);
			tableRow.addView(ticketSoldNumber, 6);
			tableRow.addView(totalSales, 7);
			tableRow.setPadding(padding, padding, padding, padding);
			tableRow.setGravity(Gravity.CENTER);
			tableRow.setTag(pack);
			dailySalesList.addView(tableRow);
		}
		dbManager.close();
	}
	
	

}
