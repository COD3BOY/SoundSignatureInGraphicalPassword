package com.example.soundsignatureintegration;


import java.io.File;

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
import android.content.Intent;

public class ShowImageActivity extends Activity {

	
	int x=0,y=0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showimage);
		
	
		String datas = getIntent().getStringExtra("DATAS");
		
		final String [] data = datas.split("%%%");
		
		String [] ini = data[1].split("###");
		
		final String [] values= data[Integer.parseInt(data[0])].split("###");
		
		File f = new File(values[0]);
		
		ImageView image = (ImageView) findViewById(R.id.imageView1);

		image.setImageURI(Uri.fromFile(f));

		  
        
        image.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				
				Toast.makeText(ShowImageActivity.this,
						"Clicked : "+(int)event.getX()+","+(int)event.getY(),
						Toast.LENGTH_SHORT).show();
				
				x=(int)event.getX();
				y=(int)event.getY();
				
				
				int center_x=Integer.parseInt(values[1]);
				int center_y=Integer.parseInt(values[2]);
				
				Log.e("Clicked&Saved","_Clicked_X :"+x+" _Saved_X :"+center_x+"_Clicked_Y : "+y+" _Saved_Y"+center_y);
				
				
				if( ((x-center_x)^2 + (y - center_y)^2)< (75^2))
				{
					Log.e("Success", "Success!");
				}
				
				
				return false;
			}
		});      

		Toast.makeText(getApplicationContext(), "This file will be played : "+ini[0], Toast.LENGTH_LONG).show();
		

		Toast.makeText(getApplicationContext(), "Tolerance level : "+ini[1], Toast.LENGTH_LONG).show();
		
		
		
		Toast.makeText(getApplicationContext(), data[Integer.parseInt(data[0])], Toast.LENGTH_LONG).show();
		

Button buttonName = (Button) findViewById(R.id.button1);
OnClickListener clickListener = new OnClickListener() {
	public void onClick(View v) {
	
		Log.e("DEBUG","Inside onClick()");
		int val = Integer.parseInt(data[0])+1;
		if((val<5))
				{
			Log.e("DEBUG","Condition satisfied!");
			
			Log.e("DEBUG","Val :"+val);
		Intent nameOfIntent = new Intent(ShowImageActivity.this, ShowImageActivity.class);
        nameOfIntent.putExtra("DATAS", val+"%%%"+data[1]+"%%%"+data[2]+"%%%"+data[3]+"%%%"+data[4]);
		startActivity(nameOfIntent);
				}
		else 
		{
		

			Toast.makeText(getApplicationContext(), "Success or fail!", Toast.LENGTH_LONG).show();


		}
		
		
	}
};

buttonName.setOnClickListener(clickListener);

		

	}

}

