package com.example.msumner.travelhelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.msumner.travelhelper.nonactivities.ClaimBaseAdapter;
import com.example.msumner.travelhelper.nonactivities.ClaimController;
import com.example.msumner.travelhelper.nonactivities.ExpenseClaimNeeded;

/*
 * references: Switching Pages: https://www.youtube.com/watch?v=fFOPzmeknNo 1/23/2015
 * listview code http://androidexample.com/Create_A_Simple_Listview_-_Android_Example/index.php?view=article_discription&aid=65&aaid=90 1/25/2015
 * save/view from https://github.com/nklose/CMPUT301/tree/master/NoNameProject
 */

public class ExpenseClaim extends Activity implements View.OnClickListener {
	Button buttonAddClaim;
	Button buttonExpense;

	
	private ClaimBaseAdapter adapter;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_claim);

        // Assign adapter to ListView
		final ListView listViewClaim = (ListView) findViewById(R.id.listViewClaim);
        
		adapter = new ClaimBaseAdapter(this);
        listViewClaim.setAdapter(adapter); 

        buttonAddClaim = (Button) findViewById(R.id.buttonAddClaim);
        buttonAddClaim.setOnClickListener(this);
        buttonExpense = (Button) findViewById(R.id.buttonExpense);
        buttonExpense.setOnClickListener(this);

        //Intent code: http://developer.android.com/reference/android/content/Intent.html
        //ListView claim Click Listener
        listViewClaim.setOnItemClickListener(new OnItemClickListener() {
              public void onItemClick(AdapterView<?> a, View v, int position, long id) {
            	  Intent intent = new Intent(ExpenseClaim.this, EditClaim.class);
            	  intent.putExtra("position", position);
            	  startActivity(intent);
              }
        });
        
        final Context context = this.getApplicationContext();
        
        listViewClaim.setOnItemLongClickListener(new OnItemLongClickListener(){
        	//deletes the claim
			public boolean onItemLongClick(AdapterView<?> a, View view, final int position, long id) {
				ClaimController Controller = ExpenseClaimNeeded.getClaimController();
				
				Controller.deleteClaim(context, position);
				
				finish();
				startActivity(getIntent());
				return true;
			}

         }); 
    }
   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.expense_claim, menu);
        return true;
    }
    private void buttonAddClaimClick()
    {
    	 Intent intent = new Intent(this, AddClaim.class);
         startActivity(intent);
    }

    private void buttonExpense()
    {

    	 Intent intent = new Intent(this, ExpenseList.class);
         startActivity(intent);
    }
    

    
    @Override
	public void onClick(View v) {
		switch(v.getId())
		{
			case R.id.buttonAddClaim:
				buttonAddClaimClick();
				break;
			case R.id.buttonExpense:
				buttonExpense();
				break;
		}
	}
}
