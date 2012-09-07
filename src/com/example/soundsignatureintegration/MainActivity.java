package com.example.soundsignatureintegration;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button registerButton = (Button) findViewById(R.id.button1);
		OnClickListener clickListener = new OnClickListener() {
			public void onClick(View v) {

				Intent newActivityIntent = new Intent(getApplicationContext(),
						RegisterActivity.class);
				startActivity(newActivityIntent);

			}
		};

		registerButton.setOnClickListener(clickListener);
		
		Button loginButton = (Button) findViewById(R.id.button2);
		OnClickListener clickListener2 = new OnClickListener() {
			public void onClick(View v) {

				Intent newActivityIntent = new Intent(getApplicationContext(),
						ReadFileActivity.class);
				startActivity(newActivityIntent);

			}
		};

	loginButton.setOnClickListener(clickListener2);


	}

}
