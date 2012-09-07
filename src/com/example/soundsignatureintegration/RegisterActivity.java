package com.example.soundsignatureintegration;

import java.io.File;
import java.io.FileOutputStream;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class RegisterActivity extends Activity {

	String filePath="default";

	String FILENAME = "";
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registerhome);

		final SeekBar seek=(SeekBar) findViewById(R.id.seekBar1);
		seek.setMax(10);
		Button nextButton = (Button) findViewById(R.id.button2);
		OnClickListener clickListener = new OnClickListener() {
			public void onClick(View v) {

			
				EditText uid = (EditText) findViewById(R.id.editText1);
				String uidStr="default";
				uidStr=	uid.getText().toString();
				Log.e("UID",uidStr);
				
				if(!uidStr.equalsIgnoreCase(""))
				{
					if(!filePath.equalsIgnoreCase("default"))
					{
						FILENAME=""+uid.getText()+".txt";
						try{
						FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
						
						
						String progress= ""+seek.getProgress();
						String toWrite=filePath+"###"+progress+"%%%";
						
						Log.e("WRITE#FILE_NAME",FILENAME);
						
						Log.e("WRITE#FILE_CONTENT",toWrite);
						
						fos.write(toWrite.getBytes());
						

						Log.e("WRITE#FILE_STATUS","Successfull");
						
						fos.close();
						}
						catch(Exception e)
						{
							Log.e("Exception#WritetoFile",e.toString());
						}
						
						finish();
						
						Intent newActivityIntent = new Intent(getApplicationContext(),
								SelectImageActivity.class);
						
						Bundle b= new Bundle();
						b.putInt("pass", 0);
						b.putString("FILENAME", FILENAME);
						newActivityIntent.putExtras(b);
						
						startActivity(newActivityIntent);
						
					}
					else
					{
						Toast.makeText(RegisterActivity.this,
								"Please select an audio file.. " ,
								Toast.LENGTH_LONG).show();
	
					}
					
				}
				else
				{
					Toast.makeText(RegisterActivity.this,
							"Please enter a unique ID.. " ,
							Toast.LENGTH_LONG).show();

				}

				
				
				

			}
		};

		nextButton.setOnClickListener(clickListener);

		Button selectButton = (Button) findViewById(R.id.button1);
		OnClickListener clickListener2 = new OnClickListener() {
			public void onClick(View v) {

				Intent intent = new Intent(RegisterActivity.this,
						FilePickerActivity.class);

				//ArrayList<String> extensions = new ArrayList<String>();
				//extensions.add(".amr");
				//extensions.add(".mp3");
				//intent.putExtra(
				//	FilePickerActivity.EXTRA_ACCEPTED_FILE_EXTENSIONS,
						//extensions);

				// Start the activity
				startActivityForResult(intent, 0);

			}
		};

		selectButton.setOnClickListener(clickListener2);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case 0:
				if (data.hasExtra(FilePickerActivity.EXTRA_FILE_PATH)) {
					// Get the file path
					File f = new File(
							data.getStringExtra(FilePickerActivity.EXTRA_FILE_PATH));
					
					Button selectButton = (Button) findViewById(R.id.button1);
					String[] paths=f.getAbsolutePath().split("/");
					
					selectButton.setText(paths[paths.length-1]);

					filePath=f.getAbsolutePath();
					
					Toast.makeText(RegisterActivity.this,
							"You selected " + f.getAbsolutePath(),
							Toast.LENGTH_LONG).show();

				}
			}
		}
	}
}
