package com.example.msumner.travelhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.msumner.travelhelper.nonactivities.ExpenseBaseAdapter;


public class AddExpenseToClaim extends Activity {
	private ExpenseBaseAdapter adapter;
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_expense_to_claim);     
        final ListView listViewExpense = (ListView) findViewById(R.id.listViewAddExpense);
        
        adapter = new  ExpenseBaseAdapter(this);
        listViewExpense.setAdapter(adapter);
        	
        listViewExpense.setOnItemClickListener(new OnItemClickListener(){
        	public void onItemClick(AdapterView<?> a, View v, int positionExpense, long id){
            	Intent intent = new Intent(AddExpenseToClaim.this, EditClaim.class);
            	intent.putExtra("positionExpense",positionExpense);
            	startActivity(intent);
            	
        	}
        });
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_expense_to_claim, menu);
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
	

}
