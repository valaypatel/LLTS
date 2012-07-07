package com.LLTS;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.LLTS.Database.PlistHelper;

public class Information extends Activity {
	
	SharedPreferences settings;
	SharedPreferences.Editor editor;

	RadioGroup shiftDayClose;
	RadioButton shift;
	RadioButton day;
	LinearLayout shiftValue;
	EditText numberOfShiftText;
	
	Button save;
	Button cancle;
	
	String shiftOrDay;
	int numberOfShift;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.information);
		
		settings = getSharedPreferences(PlistHelper.PREFS_NAME, 0);
		editor = settings.edit();
		shiftValue =(LinearLayout) findViewById(R.id.ShiftValueView);
		shiftDayClose = (RadioGroup) findViewById(R.id.ShiftOrDayGroup);
		shift = (RadioButton) findViewById(R.id.ShiftClose);
		day=(RadioButton) findViewById(R.id.DayClose);
		numberOfShiftText = (EditText) findViewById(R.id.NumberofShift);
		
		
		shiftOrDay = settings.getString("ShifOrDay", "Day");
		if(shiftOrDay.equals("Shift")) {
			shiftValue.setVisibility(View.VISIBLE);
			shift.setChecked(true);
			numberOfShift = settings.getInt("NumberOfShift", 3);
			numberOfShiftText.setText(numberOfShift+"");
		}
		else {
			shiftValue.setVisibility(View.INVISIBLE);
			day.setChecked(true);
		}
		
		
		shiftDayClose.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId==day.getId()) {
					shiftValue.setVisibility(View.INVISIBLE);
				}
				else{
					shiftValue.setVisibility(View.VISIBLE);
				}
				
			}
		});
		
		save = (Button) findViewById(R.id.Save);
		cancle = (Button) findViewById(R.id.Cancle);
		save.setOnClickListener(new MyOnClickListener());
		cancle.setOnClickListener(new MyOnClickListener());
				
	}
	
	public class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			if(v.equals(save)) {
				editor.putString("ShifOrDay", day.isChecked()?"Day":"Shift");
				editor.putInt("NumberOfShift", numberOfShiftText.length()==0?0:Integer.parseInt(numberOfShiftText.getText().toString()));
				editor.commit();
				Toast.makeText(getApplicationContext(), "Shift Information Changed", Toast.LENGTH_SHORT).show();
				finish();
			}
			else if(v.equals(cancle)) {
				finish();
			}
			
		}
		
	}

}
