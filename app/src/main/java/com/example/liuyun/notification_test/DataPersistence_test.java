package com.example.liuyun.notification_test;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DataPersistence_test extends AppCompatActivity {
/*
    private SharedPreferences preferenceSetting;
    private SharedPreferences.Editor preferenceEidtor;
    private static final int PREFERENCE_MODE_PRIVATE=0;
    //the data we want to save
    String name="Black Jack";
    boolean male=false;
    float floatNumber=10.0f;
    int intNumber=12;
    long longNumber=24L;
*/

      private EditText textBox;
      private Button save,load;
    private static final int BUFFER_SIZE=256;
    private static final String FILE_NAME="text_file.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_persistence_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
/*
        //use SharedPreference.Editor to put data into file
        //save stuff in the default preference file
        preferenceSetting=getPreferences(PREFERENCE_MODE_PRIVATE);
        preferenceEidtor=preferenceSetting.edit();
        //preferenceEditor.put<type>(key,value); is used to put data into file
        preferenceEidtor.putString("name",name);
        preferenceEidtor.putBoolean("male",male);
        preferenceEidtor.putFloat("floatNumber",floatNumber);
        preferenceEidtor.putInt("intNumber",intNumber);
        preferenceEidtor.putLong("longNumber",longNumber);
        preferenceEidtor.commit();

        */

        textBox=(EditText)findViewById(R.id.textBox);
        save=(Button)findViewById(R.id.btnSave);
        load=(Button)findViewById(R.id.btnLoad);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_file();
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_file();
            }
        });

    }



    private void save_file(){

        String textBoxString=textBox.getText().toString();
        try{
          FileOutputStream outputStream=openFileOutput(FILE_NAME,MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream);
            outputStreamWriter.write(textBoxString);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            textBox.setText("");
            Toast.makeText(getBaseContext(),"saveing successfully!",Toast.LENGTH_SHORT).show();

        }catch (IOException ioe){
            ioe.printStackTrace();
        }

    }
    private void load_file(){
        String savedString="";
        int readChar;
        char[] inputBuffer=new char[BUFFER_SIZE];
        try{
            FileInputStream fileInputStream=openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);

            while((readChar=inputStreamReader.read(inputBuffer))>0){
                String readString =String.copyValueOf(inputBuffer,0,readChar);
                savedString +=readString;
                inputBuffer =new char[BUFFER_SIZE];
            }

            textBox.setText(savedString);
            Toast.makeText(getBaseContext(),"File loaded susscessfuly!", Toast.LENGTH_SHORT).show();

        }catch(IOException ioe){
            ioe.printStackTrace();
        }


    }

}
