package com.LLTS;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.LLTS.Database.DatabaseManager;
import com.LLTS.Database.TicketPackInformationDatabaseHolder;

public class TicketPackInformation extends Activity {

	Button save;
	Button cancle;
	EditText valuePack;
	EditText group1;
	EditText group2;
	EditText group3;
	
	DatabaseManager dbManager;
	boolean isTicketInformationStored;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ticket_pack_information);
		dbManager = new DatabaseManager(getApplicationContext());
		save = (Button) findViewById(R.id.Save);
		cancle = (Button) findViewById(R.id.Cancle);
		save.setOnClickListener(new MyOnClickListener());
		cancle.setOnClickListener(new MyOnClickListener());
		
		dbManager.open();
		//Toast.makeText(getApplicationContext(), dbManager.isCompanyInformationStored()+"", Toast.LENGTH_LONG).show();
		isTicketInformationStored = dbManager.isTicketInformationStored();		
		dbManager.close();
		
		valuePack = (EditText) findViewById(R.id.ValuePack);
		group1 = (EditText) findViewById(R.id.Group1);
		group2 = (EditText) findViewById(R.id.Group2);
		group3 = (EditText) findViewById(R.id.Group3);
		
		if(isTicketInformationStored) {
			
			dbManager.open();
			TicketPackInformationDatabaseHolder ticketPackInformationDatabaseHolder= dbManager.getTicketInformation();		
			dbManager.close();
			
			valuePack.setText(ticketPackInformationDatabaseHolder.getTickeValueValue()+"");
			group1.setText(ticketPackInformationDatabaseHolder.getFirstGroupValue()+"");
			group2.setText(ticketPackInformationDatabaseHolder.getSecondGroupValue()+"");
			group3.setText(ticketPackInformationDatabaseHolder.getThirdGroupValue()+"");
			
		}
		
	}
	
	public class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId()==R.id.Save)
				saveInfo();
			else if(v.getId()==R.id.Cancle)
				finish();
			else
				return;
		}
		
		
	}
	
	
	public void saveInfo() {
		String toast="";
		boolean error = true;
		try {
			if(valuePack.length()==0 || group1.length()==0 || group2.length()==0 || group3.length()==0) {
				toast = "Enter All values";
	        	error =true;
			}
			else if(Float.parseFloat(valuePack.getText().toString())==0) {
	        	
	        	toast = "Enter proper value of Ticket Pack";
	        	error =true;
	        }
	        else if(Integer.parseInt(group1.getText().toString())==0) {
				toast = "Enter Proper value in Route";
	        	error =true;
			}
	        else if(Integer.parseInt(group2.getText().toString())==0) {
				toast = "Enter proper value in Serial Number";
	        	error =true;
			}
	        else if(Integer.parseInt(group3.getText().toString())==0) {
				toast = "Enter proper value in Ticket Number";
	        	error =true;
			}
	        else {
	        	TicketPackInformationDatabaseHolder ticketPackInformationDatabaseHolder = new TicketPackInformationDatabaseHolder();
	        
	        	ticketPackInformationDatabaseHolder.setTickeValueValue(Double.parseDouble(valuePack.getText().toString()));
	        	ticketPackInformationDatabaseHolder.setDigitInBarcodeValue(Integer.parseInt(group1.getText().toString())+Integer.parseInt(group2.getText().toString())+Integer.parseInt(group3.getText().toString()));
	        	ticketPackInformationDatabaseHolder.setFirstGroupValue(Integer.parseInt(group1.getText().toString()));
	        	ticketPackInformationDatabaseHolder.setSecondGroupValue(Integer.parseInt(group2.getText().toString()));
	        	ticketPackInformationDatabaseHolder.setThirdGroupValue(Integer.parseInt(group3.getText().toString()));
	        	dbManager.open();
	        	if(isTicketInformationStored)
	        		dbManager.deleteTicketInformation();
	        	dbManager.insertTicketInfornation(ticketPackInformationDatabaseHolder);
	        	dbManager.close();
	        	toast = "Ticket Pack information Stroed";
	        	error =false;
	        	
	        }
		}
		catch(NumberFormatException e) {
			toast = "Enter proper values";
        	error =true;
			
		}
        finally {
        	 Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
             if(!error)finish();
        }
        
       
		
	}

}
