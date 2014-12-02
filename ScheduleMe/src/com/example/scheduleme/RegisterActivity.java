package com.example.scheduleme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends Activity {
	Button registerButton;
	TextView loginLink;
	SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		session = new SessionManager(getApplicationContext());
		
		registerButton = (Button) findViewById(R.id.btnRegister);
		
		registerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText emailText = (EditText)findViewById(R.id.reg_email);
				EditText firstNameText = (EditText)findViewById(R.id.reg_first_name);
				EditText lastNameText = (EditText)findViewById(R.id.reg_last_name);
				EditText userNameText = (EditText)findViewById(R.id.reg_username);
				EditText passwordText = (EditText)findViewById(R.id.reg_password);
				EditText phoneNumberText = (EditText)findViewById(R.id.reg_phone);
				
				register(firstNameText.getText().toString(), lastNameText.getText().toString(), userNameText.getText().toString(), passwordText.getText().toString(), emailText.getText().toString(), phoneNumberText.getText().toString());
				
				
				
			}
			
		});
		
		
		loginLink = (TextView) findViewById(R.id.link_to_login);
		
		final Context c = this;
		
		loginLink.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(c, LoginActivity.class);
			    startActivity(intent);
				
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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
	
	private void register(String firstName, String lastName, String userName, String password, String email, String phoneNumber) {
		if (!firstName.equals("") && !lastName.equals("") && !userName.equals("") && !password.equals("") && !email.equals("") && !phoneNumber.equals("")) {
			session.createLoginSession(userName, password);
			Intent i = new Intent(getApplicationContext(), MainActivity.class);
			startActivity(i);
			finish();
		}
	}
}
