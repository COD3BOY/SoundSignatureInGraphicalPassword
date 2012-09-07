package com.example.soundsignatureintegration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ReadFileActivity extends Activity {
	
	String content = "";
 EditText edFileName, edContent;
 Button btnSave;
 ListView listSavedFiles;
 
 String[] SavedFiles;
 
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.login);
       
    listSavedFiles = (ListView)findViewById(R.id.list);
    
final EditText uid = (EditText) findViewById(R.id.editText1);

Button login = (Button) findViewById(R.id.button1);
OnClickListener clickListener = new OnClickListener() {
	public void onClick(View v) {
		
		 SavedFiles = getApplicationContext().fileList();
		int flag=0;
		for(int i=0;i<SavedFiles.length;i++)
		{
			if(SavedFiles[i].equalsIgnoreCase(uid.getText().toString().trim()+".txt"))
			{
				flag=1;
				OpenFileDialog(SavedFiles[i]);
				

				Toast.makeText(getApplicationContext(), "Successfull login! ", Toast.LENGTH_SHORT).show();

				Intent nameOfIntent = new Intent(getApplicationContext(), ShowImageActivity.class);
		        nameOfIntent.putExtra("DATAS", "2%%%"+content);
				startActivity(nameOfIntent);
		        
			}
			
			
			
		}
		
		if(flag==0)
		{
			Toast.makeText(getApplicationContext(), "Sorry, We dont see any match for your UID in our database! ", Toast.LENGTH_LONG).show();
			
		}
					
    
		
	}
};

login.setOnClickListener(clickListener);

    
    
   }
  
  
   void OpenFileDialog(String file){
    
    //Read file in Internal Storage
    FileInputStream fis;
    
    try {
     fis = openFileInput(file);
     byte[] input = new byte[fis.available()];
     while (fis.read(input) != -1) {}
     content += new String(input);
    } catch (FileNotFoundException e) {
     e.printStackTrace();
    } catch (IOException e) {
     e.printStackTrace(); 
    }
    
   }
}