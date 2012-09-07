package com.example.soundsignatureintegration;

import java.io.File;
import java.io.IOException;

import android.media.MediaPlayer;
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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class ShowImageActivity extends Activity {

	MediaPlayer mp = new MediaPlayer();

	@Override
	protected void onPause() {

		mp.stop();
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {

		mp.stop();
		// TODO Auto-generated method stub
		super.onStop();
	}

	int x = 0, y = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showimage);

		String datas = getIntent().getStringExtra("DATAS");

		final String[] data = datas.split("%%%");

		for (int i = 0; i < data.length; i++)
			Log.e("data", data[i]);

		final int toPass = Integer.parseInt(data[0]);
		;

		String[] ini = data[1].split("###");

		// set up MediaPlayer

		try {
			mp.setDataSource(ini[0]);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mp.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mp.start();

		final String[] values = data[Integer.parseInt(data[0])].split("###");

		/* Image resource of the imageView */

		File f = new File(values[0]);
		ImageView image = (ImageView) findViewById(R.id.imageView1);
		image.setImageURI(Uri.fromFile(f));

		image.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {

				Toast.makeText(
						ShowImageActivity.this,
						"Clicked : " + (int) event.getX() + ","
								+ (int) event.getY(), Toast.LENGTH_SHORT)
						.show();

				x = (int) event.getX();
				y = (int) event.getY();

				int center_x = Integer.parseInt(values[1]);
				int center_y = Integer.parseInt(values[2]);

				Log.e("Clicked&Saved", "_Clicked_X :" + x + " _Saved_X :"
						+ center_x + "_Clicked_Y : " + y + " _Saved_Y"
						+ center_y);

				if (x < center_x + 25 && x > center_x - 25) {
					if (y < center_y + 25 && y > center_y - 25) {

						data[toPass] = "Y";
						Log.e("Login", "Success!");
					}
				} else {

					data[toPass] = "N";
					Log.e("Login", "Fail!");

				}

				return false;
			}
		});

		Toast.makeText(getApplicationContext(),
				"This file will be played : " + ini[0], Toast.LENGTH_LONG)
				.show();

		Toast.makeText(getApplicationContext(), "Tolerance level : " + ini[1],
				Toast.LENGTH_LONG).show();

		Toast.makeText(getApplicationContext(),
				data[Integer.parseInt(data[0])], Toast.LENGTH_LONG).show();

		Button buttonName = (Button) findViewById(R.id.button1);
		OnClickListener clickListener = new OnClickListener() {
			public void onClick(View v) {

				Log.e("DEBUG", "Inside onClick()");
				int val = toPass + 1;
				if ((val < 5)) {
					Log.e("DEBUG", "Condition satisfied!");

					Log.e("DEBUG", "Val :" + val);
					Intent nameOfIntent = new Intent(ShowImageActivity.this,
							ShowImageActivity.class);
					nameOfIntent.putExtra("DATAS", val + "%%%" + data[1]
							+ "%%%" + data[2] + "%%%" + data[3] + "%%%"
							+ data[4]);
					startActivity(nameOfIntent);
				} else {

					if (data[2].equalsIgnoreCase("Y")
							&& data[3].equalsIgnoreCase("Y")
							&& data[toPass].equalsIgnoreCase("Y")) {

						AlertDialog.Builder builder = new AlertDialog.Builder(
								ShowImageActivity.this);
						builder.setMessage("Login succesfull!")
								.setCancelable(false)
								.setPositiveButton("Okay!",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int id) {
												ShowImageActivity.this.finish();
											}
										});
						AlertDialog alert = builder.create();
						alert.show();

					} else {

						AlertDialog.Builder builder = new AlertDialog.Builder(
								ShowImageActivity.this);
						builder.setMessage("Login fail!")
								.setCancelable(false)
								.setPositiveButton("Okay!",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int id) {
												ShowImageActivity.this.finish();
											}
										});
						AlertDialog alert = builder.create();
						alert.show();

					}

				}

			}
		};

		buttonName.setOnClickListener(clickListener);

	}

}
