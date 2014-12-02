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

public class LoginActivity extends Activity {
	Button loginButton;
	TextView registerLink;

	SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		session = new SessionManager(getApplicationContext());

		loginButton = (Button) findViewById(R.id.btnLogin);
		
		final LoginActivity a = this;

		loginButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				EditText userNameText = (EditText)findViewById(R.id.login_username);
				EditText passwordText = (EditText)findViewById(R.id.login_password);
				
				a.logIn(userNameText.getText().toString(), passwordText.getText().toString());

			}

		});

		registerLink = (TextView) findViewById(R.id.link_to_register);

		final Context c = this;

		registerLink.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(c, RegisterActivity.class);
				startActivity(intent);
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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

	private void logIn(String userName, String password) {
		if (userName != "" && password != "") {
			session.createLoginSession(userName, password);
			Intent i = new Intent(getApplicationContext(), MainActivity.class);
			startActivity(i);
			finish();
		}
	}
}
