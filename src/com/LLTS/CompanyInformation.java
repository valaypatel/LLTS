package com.LLTS;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.LLTS.Database.CompanyInformationDatabaseHolder;
import com.LLTS.Database.DatabaseManager;

public class CompanyInformation extends Activity {

	
	Button save;
	Button cancle;
	EditText companyName;
	EditText companyAddress;
	EditText companyCity;
	Spinner companyState;
	EditText companyZip;
	EditText companyPhone;
	EditText companyLocation;
	
	DatabaseManager dbManager;
	boolean isCompanyInformationStored;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.company_information);
		dbManager = new DatabaseManager(getApplicationContext());
		save = (Button) findViewById(R.id.Save);
		cancle = (Button) findViewById(R.id.Cancle);
		save.setOnClickListener(new MyOnClickListener());
		cancle.setOnClickListener(new MyOnClickListener());
		
		dbManager.open();
		//Toast.makeText(getApplicationContext(), dbManager.isCompanyInformationStored()+"", Toast.LENGTH_LONG).show();
		isCompanyInformationStored = dbManager.isCompanyInformationStored();
		
		dbManager.close();
		
		companyName = (EditText) findViewById(R.id.Name);
		companyAddress = (EditText) findViewById(R.id.Address);
		companyCity = (EditText) findViewById(R.id.City);
		companyState = (Spinner) findViewById(R.id.state);
		companyZip = (EditText) findViewById(R.id.Zip);
		companyPhone = (EditText) findViewById(R.id.Phone);
		companyLocation = (EditText) findViewById(R.id.Location);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
		            this, R.array.state_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		companyState.setAdapter(adapter);

		
		if(isCompanyInformationStored) {
			
			
			dbManager.open();
			CompanyInformationDatabaseHolder companyInformationDatabaseHolder = dbManager.getCompanyInformation();
			dbManager.close();
			companyName.setText(companyInformationDatabaseHolder.getCompanyNameValue());
			companyAddress.setText(companyInformationDatabaseHolder.getCompanyAddressValue());
			companyZip.setText(companyInformationDatabaseHolder.getCompanyZipValue());
			companyPhone.setText(companyInformationDatabaseHolder.getCompanyPhoneValue());
			companyLocation.setText(companyInformationDatabaseHolder.getCompanyLocationValue());
			
		}
		
		
	}
	
	
	public class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId()==R.id.Save) {
				saveInfo();
				finish();
			}
			else if(v.getId()==R.id.Cancle)
				finish();
			else
				return;
		}
		
		
	}
	
	public void saveInfo() {
	
      
        dbManager.open();        
        CompanyInformationDatabaseHolder companyInformationDatabaseHolder = new CompanyInformationDatabaseHolder();
        companyInformationDatabaseHolder.setCompanyNameValue(companyName.getText().toString());
        companyInformationDatabaseHolder.setCompanyAddressValue(companyAddress.getText().toString());
        companyInformationDatabaseHolder.setCompanyCityValue(companyCity.getText().toString());
        companyInformationDatabaseHolder.setCompanyStateValue(companyState.getSelectedItem().toString());
        companyInformationDatabaseHolder.setCompanyZipValue(companyZip.getText().toString());
        companyInformationDatabaseHolder.setCompanyPhoneValue(companyPhone.getText().toString());
        companyInformationDatabaseHolder.setCompanyLocationValue(companyLocation.getText().toString());
        if(isCompanyInformationStored)
        	dbManager.deleteCompanyInformation();
        dbManager.insertCompanyInfornation(companyInformationDatabaseHolder);
        dbManager.close();
        Toast.makeText(getApplicationContext(), "Company Information saved!!!", Toast.LENGTH_LONG).show();
        
        
		
	}

}

