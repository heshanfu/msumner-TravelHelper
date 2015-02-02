package com.example.msumner.travelhelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.msumner.travelhelper.nonactivities.Expense;
import com.example.msumner.travelhelper.nonactivities.ExpenseClaimNeeded;
import com.example.msumner.travelhelper.nonactivities.ExpenseController;


public class AddExpense extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_expense);    	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_expense, menu);
		return true;
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
	
    public Expense Expenseinfo(View view){
    	//Store Inputs
    	String name = new String();
    	String description = new String();
    	String date = new String();
    	String category = new String();
    	String amountSpent = new String();
    	String unitofCurrency = new String();
    	

    	
    	//XML Inputs
    	EditText editTextName = (EditText) findViewById(R.id.editTextName);
    	EditText editTextDescription = (EditText) findViewById(R.id.editTextDescription);
    	EditText editTextDate = (EditText) findViewById(R.id.editTextDate);
    	EditText editTextCategory = (EditText) findViewById(R.id.editTextCategory);
    	EditText editTextAmountSpent = (EditText) findViewById(R.id.editTextAmountSpent);
    	EditText editTextUnitofCurrency	 = (EditText) findViewById(R.id.editTextUnitofCurrency);
    	
    	//Get XML inputs
    	name = editTextName.getText().toString();
    	description = editTextDescription.getText().toString();
    	date = editTextDate.getText().toString();
    	category = editTextCategory.getText().toString();
    	amountSpent = editTextAmountSpent.getText().toString();
    	unitofCurrency = editTextUnitofCurrency.getText().toString();
    	
    	
    	
    	Expense expense = new Expense(name,description,date,category, amountSpent,unitofCurrency);
    	return expense;
    }
    
    public void createExpense(View view){
    	//context and controllers
    	Context context = this.getApplicationContext();
    	ExpenseController Controller = ExpenseClaimNeeded.getExpenseController();
    	
    	Expense expense = Expenseinfo(view);
    	CharSequence toastText;
    	Toast toast = null;
    	Controller.addExpense(context,expense);
    	toastText = "Expense Saved.";
   	 	Intent intent = new Intent(this, ExpenseClaim.class);
   	 	startActivity(intent);
    	toast = Toast.makeText(context,toastText, Toast.LENGTH_LONG);
    	toast.show();	
    	
    }
}
