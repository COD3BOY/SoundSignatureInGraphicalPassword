package com.example.soundsignatureintegration;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class SelectImageActivity extends Activity {

	int pass=0;
	String FILENAME;
	int x=0,y=0;
	
	String imageLoc="";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectimage);
        
        pass=getIntent().getExtras().getInt("pass");
        FILENAME=getIntent().getExtras().getString("FILENAME");
        
       if(pass==2)
       {
    	   Button nextButton = (Button) findViewById(R.id.button2);
    	   nextButton.setText("Finish");
       }
        
        
        ImageView img = (ImageView) findViewById(R.id.imageView1);
        img.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				
				Toast.makeText(SelectImageActivity.this,
						"Clicked : "+(int)event.getX()+","+(int)event.getY(),
						Toast.LENGTH_SHORT).show();
				
				x=(int)event.getX();
				y=(int)event.getY();
				
				return false;
			}
		});        
        Button nextButton = (Button) findViewById(R.id.button2);
		OnClickListener clickListener = new OnClickListener() {
			public void onClick(View v) {
				
				
				if(!imageLoc.equalsIgnoreCase(""))
				{
					if(!(x==0))
					{
						try{
							FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);
							
							
							
							String toWrite=imageLoc+"###"+x+"###"+y+"%%%";
							
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
							

						
						ImageView image = (ImageView) findViewById(R.id.imageView1);
						image.setImageResource(0);
						Intent newActivityIntent = new Intent(getApplicationContext(),
								SelectImageActivity.class);
						 if(pass==2)
					        {

								Toast.makeText(SelectImageActivity.this,
										"Thank you for signing up!",
										Toast.LENGTH_SHORT).show();
					        	finish();
					        }
					        else
					        {
					        	Log.e("PASS",""+pass);
						pass++;
						
						Bundle b= new Bundle();
						b.putInt("pass", pass);
						b.putString("FILENAME", FILENAME);
						
						newActivityIntent.putExtras(b);
						
						finish();
						startActivity(newActivityIntent);
					        }
						 
					}
					else
					{
						Toast.makeText(SelectImageActivity.this,
								"Please click a point! ",
								Toast.LENGTH_SHORT).show();
					}
				}
				else
				{
					Toast.makeText(SelectImageActivity.this,
							"Please select an image!",
							Toast.LENGTH_SHORT).show();
				}

				

			}
		};

		nextButton.setOnClickListener(clickListener);
		
		
		 Button selectButton = (Button) findViewById(R.id.button1);
			OnClickListener clickListener2 = new OnClickListener() {
				public void onClick(View v) {

					Intent intent = new Intent(SelectImageActivity.this, FilePickerActivity.class);
					
					ArrayList<String> extensions = new ArrayList<String>();
					extensions.add(".png");
					extensions.add(".jpg");
					intent.putExtra(FilePickerActivity.EXTRA_ACCEPTED_FILE_EXTENSIONS, extensions);
					
					// Start the activity
					startActivityForResult(intent, 0);
					
					
				}
			};

			selectButton.setOnClickListener(clickListener2);

       
    }

    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK) {
			switch(requestCode) {
			case 0:
				if(data.hasExtra(FilePickerActivity.EXTRA_FILE_PATH)) {
					// Get the file path
				File f = new File(data.getStringExtra(FilePickerActivity.EXTRA_FILE_PATH));
				
				imageLoc=f.getAbsolutePath();
				
				ImageView image = (ImageView) findViewById(R.id.imageView1);
		
				image.setImageURI(Uri.fromFile(f));
				
				}
			}
		}
	}
    

}
