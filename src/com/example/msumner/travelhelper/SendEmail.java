package com.example.msumner.travelhelper;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SendEmail extends Activity {
	
	//I tried :(
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_email);
		final EditText emailAddress = (EditText) findViewById(R.id.editTextEmail);
		final String email = emailAddress.getText().toString();
		Button send = (Button) findViewById(R.id.buttonSend);
		//code from http://stackoverflow.com/questions/8701634/send-email-intent 2015
		send.setOnClickListener(new OnClickListener(){
			public void onClick(View view)
			{

        		Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email,"EXTRA_EMAIL);"));
        		intent.putExtra(Intent.EXTRA_EMAIL, "EXTRA_EMAIL");
        		startActivity(Intent.createChooser(intent, "Send email: "));
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.send_email, menu);
		return true;
	}

}
