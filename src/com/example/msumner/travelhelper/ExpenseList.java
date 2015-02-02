package com.example.msumner.travelhelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.msumner.travelhelper.nonactivities.ExpenseBaseAdapter;
import com.example.msumner.travelhelper.nonactivities.ExpenseClaimNeeded;
import com.example.msumner.travelhelper.nonactivities.ExpenseController;




public class ExpenseList extends Activity implements View.OnClickListener {
	Button buttonBack;
	Button buttonAddExpense;
	private ExpenseBaseAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense_list);
        buttonAddExpense = (Button) findViewById(R.id.buttonAddExpense);
        buttonAddExpense.setOnClickListener(this);
        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(this);
        
        ListView listViewExpense = (ListView) findViewById(R.id.listViewExpense);
        
        adapter = new  ExpenseBaseAdapter(this);
        listViewExpense.setAdapter(adapter);
       
        //Intent code: http://developer.android.com/reference/android/content/Intent.html
        listViewExpense.setOnItemClickListener(new OnItemClickListener(){
        	public void onItemClick(AdapterView<?> a, View v, int position, long id){
        		Intent intent = new Intent(ExpenseList.this, EditExpense.class);
        		intent.putExtra("position", position);
        		startActivity(intent);
        	}
        });
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_list, menu);
		return true;
	}
	
    private void buttonBackClick()
    {
    	 Intent intent = new Intent(this, ExpenseClaim.class);
         startActivity(intent);
    }
    
    private void buttonAddExpenseclick()
    {
    	Intent intent = new Intent(this, AddExpense.class);
    	startActivity(intent);
    }
    
    //executes page change function
	public void onClick(View v) {
		switch(v.getId())
		{
			case R.id.buttonBack:
				buttonBackClick();
				break;
			case R.id.buttonAddExpense:
				buttonAddExpenseclick();
				break;
		}
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
}
