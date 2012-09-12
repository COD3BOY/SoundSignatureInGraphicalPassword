package com.example.soundsignatureintegration;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		Button registerButton = (Button) findViewById(R.id.button1);

		OnClickListener clickListener = new OnClickListener() {
			public void onClick(View v) {

				final AlertDialog.Builder alert = new AlertDialog.Builder(
						MainActivity.this);
				final EditText input = new EditText(MainActivity.this);
				alert.setTitle("Admin Password");
				alert.setView(input);
				alert.setPositiveButton("Login",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								String value = input.getText().toString()
										.trim();

								if (value.equalsIgnoreCase("admin")) {

									Intent newActivityIntent = new Intent(
											getApplicationContext(),
											RegisterActivity.class);
									startActivity(newActivityIntent);

								} else {
									Toast.makeText(MainActivity.this,
											"Sorry wrong Admin password!",
											Toast.LENGTH_SHORT).show();
								}
							}
						});

				alert.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								dialog.cancel();
							}
						});
				alert.show();

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
