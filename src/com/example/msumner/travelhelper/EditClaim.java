package com.example.msumner.travelhelper;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msumner.travelhelper.nonactivities.Claim;
import com.example.msumner.travelhelper.nonactivities.ClaimController;
import com.example.msumner.travelhelper.nonactivities.ExpenseClaimNeeded;
import com.example.msumner.travelhelper.nonactivities.ExpenseController;

//code implemented from: https://ydandroid.wordpress.com/2011/05/03/using-listview-to-display-list-of-strings/ 2015
//http://stackoverflow.com/questions/19468025/add-items-to-listview-android 2015
//http://stackoverflow.com/questions/5070830/populating-a-listview-using-arraylist

public class EditClaim extends Activity implements View.OnClickListener{
	private ListView listViewExpensetoClaim;
	private ArrayAdapter<String> listAdapter; 
	private Integer TotalCurrency = 0;
	static ArrayList<String> test = new ArrayList<String>();
	static int i = 0;
	TextView textViewCurrency;
	//output total http://www.techillumination.in/2010/02/simple-android-application-for-adding.html
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_claim);

        //trying to add expenses to claims here. IT NOW WORKS IM SO HAPPY!
        listViewExpensetoClaim = (ListView) findViewById(R.id.listViewExpenseClaim);
        int positionExpense = getIntent().getExtras().getInt("positionExpense");
        if (i == 0){
        	String expense = "";
        }
        
        else{
        String name = ExpenseController.getExpense(positionExpense).getName();
        //supposed to be amount not description, I am just scared to break things!
        String description = ExpenseController.getExpense(positionExpense).getDescription();
        String unitOfCurrency = ExpenseController.getExpense(positionExpense).getCurrency(); 
       	Integer amount = Integer.valueOf(ExpenseController.getExpense(positionExpense).getDescription());
		String total = sum(amount);
		textViewCurrency = (TextView)findViewById(R.id.textViewTotalCurrency);
		textViewCurrency.setText(total);
        test.add("Expense: " + name + "  " + "Amount " + description + " " + unitOfCurrency);
        }
    	listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,test);
        listViewExpensetoClaim.setAdapter( listAdapter );  
        i ++;

        //delete expense
        listViewExpensetoClaim.setOnItemLongClickListener(new OnItemLongClickListener(){
        	public boolean onItemLongClick(AdapterView<?> a, View v, final int position, long id) 
			{					
				// delete the expense
				test.remove(position);
				i = 0;

				// restart the activity
				finish();
				startActivity(getIntent());
				
				return true;
			}
		});
	}
	
	private String sum(int y){
		TotalCurrency += y;
		return Integer.toString(TotalCurrency);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_claim, menu);
		return true;
	}
	
	public void onBackPressed() {
    	Intent intent = new Intent(this, ExpenseClaim.class);
    	startActivity(intent);
		i = 0;
        }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
    private void buttonAddExpense()
    {

    	Intent intent = new Intent(this, AddExpenseToClaim.class);
    	startActivity(intent);
    }
    
    private void buttonSend()
    {

    	Intent intent = new Intent(this, SendEmail.class);
    	startActivity(intent);
    }
    
	public void onClick(View v) {
		switch(v.getId())
		{
			case R.id.buttonAddExpense:
				buttonAddExpense();
				break;
				
			case R.id.buttonEmailClaim:
				buttonSend();
				break;
		}
		
		
	}
	
	public void editClaim(View view){
        int positionClaim = getIntent().getExtras().getInt("position");
		ClaimController claimController = ExpenseClaimNeeded.getClaimController();
		Claim entry = claimController.getClaim(positionClaim);
		Claim.ClaimStatus status = entry.getStatus();
    	Context context = this.getApplicationContext();

		//edit Claim
		if (status == Claim.ClaimStatus.Returned || (status == Claim.ClaimStatus.In_Progress)){
			Claim prevClaim = claimController.getClaim(positionClaim);
			
	    	String name = new String();
	    	String startDate = new String();
	    	String endDate = new String();
	    	
	    	EditText editTextName = (EditText) findViewById(R.id.editTextName);
	    	EditText editTextStartDate = (EditText) findViewById(R.id.editTextStartDate);
	    	EditText editTextEndDate = (EditText) findViewById(R.id.editTextEndDate);
	    	RadioButton radioProgress = (RadioButton)findViewById(R.id.radioInProgress);
	    	RadioButton radioSubmitted = (RadioButton)findViewById(R.id.radioSubmitted);
	    	RadioButton radioApproved = (RadioButton)findViewById(R.id.radioButtonApproved);
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

	    	if (name.equals("")){
	    		name = prevClaim.getName();
	    	}
	    	if (startDate.equals("")){
	    		startDate = prevClaim.getStartDate();
	    	}
	    	if (endDate.equals("")){
	    		endDate = prevClaim.getEndDate();
	    	}
	    	Claim claim = new Claim(name, startDate, endDate,status );
	    	claimController.addClaim(context,claim);
			claimController.deleteClaim(context, positionClaim);
	    	toastText = "Claim Updated";
	    	toast = Toast.makeText(context,toastText, Toast.LENGTH_LONG);
	    	toast.show();	
		}
		
		//TOAST http://developer.android.com/guide/topics/ui/notifiers/toasts.html
		//Can't edit Claim
		else {
			CharSequence text = "No edits allowed";
			int duration = Toast.LENGTH_LONG;
			
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			
		}
   	 	Intent intent = new Intent(this, ExpenseClaim.class);
   	 	startActivity(intent);
		i = 0;
		
	}
}
