package com.dator.claudette;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText eLastName, eFirstName, eExam1, eExam2, tAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eFirstName = findViewById(R.id.etFirstName);
        eLastName = findViewById(R.id.etLastName);
        eExam1 = findViewById(R.id.etExam1);
        eExam2 = findViewById(R.id.etExam2);
        tAverage = findViewById(R.id.tvAverage);
    }

    public void displayAverage(View v) {
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(folder, "external.txt");
        FileInputStream fin = null;
        int c;
        StringBuffer buffer = new StringBuffer();
        String firstname = eFirstName.getText().toString();
        String lastname = eLastName.getText().toString();
        int exam1 = Integer.parseInt(eExam1.getText().toString().trim());
        int exam2 = Integer.parseInt(eExam2.getText().toString().trim());
        double average = ((exam1 + exam2) / 2);
        String result = String.valueOf(average);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(firstname.getBytes());
            fos.write(lastname.getBytes());
            fos.write(result.getBytes());
            fin = new FileInputStream(file);
            while((c=fin.read()) != -1){
                buffer.append((char) c);
            }
            tAverage.setText(buffer);
            Toast.makeText(this, "Data saved in SD card..", Toast.LENGTH_SHORT);
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Error writing in SD card", Toast.LENGTH_LONG);
        } catch (IOException e) {
            Toast.makeText(this, "IO Exception", Toast.LENGTH_LONG);
        }
        //Toast.makeText(this, "Data saved...", Toast.LENGTH_LONG).show();
        //tAverage.setText(result);
    }

    public void saveExternal(View v){
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(folder, "external.txt");
        String firstname = eFirstName.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(firstname.getBytes());
            Toast.makeText(this, "Data saved in SD card..", Toast.LENGTH_SHORT);
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Error writing in SD card", Toast.LENGTH_LONG);
        } catch (IOException e) {
            Toast.makeText(this, "IO Exception", Toast.LENGTH_LONG);

        }
    }

    public void greetFromExternal(View v){
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(folder, "external.txt");
        FileInputStream fin = null;
        int c;
        StringBuffer buffer = new StringBuffer();
        try {
            fin = new FileInputStream(file);
            while((c=fin.read()) != -1){
                buffer.append((char) c);
            }
            tAverage.setText(buffer);

        } catch (Exception e) {
            Toast.makeText(this, "Error reading in SD card", Toast.LENGTH_LONG);
        }
    }
}
