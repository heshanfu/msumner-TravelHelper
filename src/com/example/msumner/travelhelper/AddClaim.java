package com.example.msumner.travelhelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.msumner.travelhelper.nonactivities.Claim;
import com.example.msumner.travelhelper.nonactivities.Claim.ClaimStatus;
import com.example.msumner.travelhelper.nonactivities.ClaimController;
import com.example.msumner.travelhelper.nonactivities.ExpenseClaimNeeded;


public class AddClaim extends Activity implements View.OnClickListener  {
	private Button buttonBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_claim);
	    buttonBack = (Button) findViewById(R.id.buttonBack);
	    buttonBack.setOnClickListener(this);
	}
	   

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_claim, menu);
		return true;
	}
	
    private void buttonBackClick()
    {
    	 Intent intent = new Intent(this, ExpenseClaim.class);
         startActivity(intent);
    }
    
    
    public void createClaim(View view){
    	Claim.ClaimStatus status = Claim.ClaimStatus.In_Progress;
    	String name = new String();
    	String startDate = new String();
    	String endDate = new String();
    	
    	Context context = this.getApplicationContext();
    	ClaimController Controller = ExpenseClaimNeeded.getClaimController();
    	
    	//XML Inputs
    	//Radio Button code http://stackoverflow.com/questions/27504896/how-to-update-listview-when-loading-next-new-items-android
    	EditText editTextName = (EditText) findViewById(R.id.editTextName);
    	EditText editTextStartDate = (EditText) findViewById(R.id.editTextStartDate);
    	EditText editTextEndDate = (EditText) findViewById(R.id.editTextEndDate);
    	RadioButton radioProgress = (RadioButton)findViewById(R.id.radioProgress);
    	RadioButton radioSubmitted = (RadioButton)findViewById(R.id.radioSubmitted);
    	RadioButton radioApproved = (RadioButton)findViewById(R.id.radioApproved);
    	RadioButton radioReturned = (RadioButton)findViewById(R.id.radioReturned);
    	
		if (radioProgress.isChecked()){
			status = Claim.ClaimStatus.In_Progress;
		}
		if (radioSubmitted.isChecked()){
			status = Claim.ClaimStatus.Submitted;
		}
		if (radioApproved.isChecked()){
			status = Claim.ClaimStatus.Approved;
		}
		if (radioReturned.isChecked()){
			status = Claim.ClaimStatus.Returned;
		}
		
    	//Convert XML to String
    	name = editTextName.getText().toString();
    	startDate = editTextStartDate.getText().toString();
    	endDate = editTextEndDate.getText().toString();
    	CharSequence toastText;
    	Toast toast = null;
    	
    	Claim claim = new Claim(name, startDate, endDate,status );
    	Controller.addClaim(context,claim);
    	toastText = "Claim Saved.";
    	toast = Toast.makeText(context,toastText, Toast.LENGTH_LONG);
    	toast.show();	
   	 	Intent intent = new Intent(this, ExpenseClaim.class);
   	 	startActivity(intent);
    }

    @Override
	public void onClick(View v) {
		switch(v.getId())
		{
			case R.id.buttonBack:
				buttonBackClick();
				break;
		}		
	}    
}

