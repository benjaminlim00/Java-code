package com.delta.persistence;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class PersistenceActivity extends Activity {
    TextView readingView;   //the default view
    TextView appRestartsView;   //the view you see when you restart
    private int howManyTimesBeenRun = 0;
    private static final String NUMBER_OF_TIMES_RUN_KEY = "NUMBER_OF_TIMES_RUN_KEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persistence);
        readingView = (TextView) findViewById(R.id.displayView);
        appRestartsView = (TextView) findViewById(R.id.applicationRestarts);
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE); //create shared preferences
        //read
        int defaultValue = 0;
        howManyTimesBeenRun = sharedPreferences.getInt(NUMBER_OF_TIMES_RUN_KEY,defaultValue);   //number of times run always starts at default 0
        //first time message
        if(howManyTimesBeenRun == 0){
            Toast.makeText(this,"Welcome to your new magic notepad!", Toast.LENGTH_LONG).show();
        }
        howManyTimesBeenRun++;  //always increment count by 1
        //write
        SharedPreferences.Editor editor = sharedPreferences.edit(); //open a editor
        editor.putInt(NUMBER_OF_TIMES_RUN_KEY,howManyTimesBeenRun); //save the number of times ran
        editor.commit();    //commit the changes
        appRestartsView.setText(String.valueOf(howManyTimesBeenRun));   //display number of times ran.
    }
    @Override
    protected void onPause() {
        super.onPause();
        saveTextFile(readingView.getText().toString()); //save the changes
    }
    @Override
    protected void onResume() {
        super.onResume();
        readingView.setText(getTextFile()); //get last saved data
    }
    private static final String DATA_FILE = "my_file";
    //Reading and Writing
    public String getTextFile() {   //how do we retrieve data
        FileInputStream fileInputStream = null;
        String fileData = null;
        try{
            fileInputStream = openFileInput(DATA_FILE); //open the saved file
            int size = fileInputStream.available(); //check size of we can make buffer with that size
            byte[] buffer = new byte[size]; //make the buffer with correct size
            fileInputStream.read(buffer);   //feed the inputstream into buffer
            fileInputStream.close();    //close the input stream
            fileData = new String(buffer,"UTF-8");  //translate buffer data into String, save in fileData
        }catch(FileNotFoundException e){
            Log.e("FILE","Couldnt find that file");
            e.printStackTrace();
        }catch(IOException e){
            Log.e("FILE","Error");
            e.printStackTrace();
        }finally{
            try{
                if(fileInputStream != null){    //close the fileinputstream when done
                    fileInputStream.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return fileData;    //return the retrieved data
    }
    public void saveTextFile(String content) {  // how do we save the data?
        FileOutputStream fileOutputStream = null;
        try{
            fileOutputStream = openFileOutput(DATA_FILE,Context.MODE_PRIVATE);  //make file output stream
            fileOutputStream.write(content.getBytes()); //write content into it, but first translate into bytes
        }catch(FileNotFoundException e){
            Log.e("FILE","Couldnt find that file");
            e.printStackTrace();
        }catch(IOException e){
            Log.e("FILE","IO Error");
            e.printStackTrace();
        }finally{
            try{
                if(fileOutputStream != null){   //close the file output stream
                    fileOutputStream.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.persistence, menu);
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
}