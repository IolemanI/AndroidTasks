package com.oleman.androidtasks.Settings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oleman.androidtasks.MainActivity;
import com.oleman.androidtasks.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class RenameTasksActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String FILENAME = "AndroidTasks";
    public static final String TAG = "myLogs";

    private EditText task1Txt;
    private EditText task2Txt;
    private EditText task3Txt;
    private EditText task4Txt;
    private EditText task5Txt;

    private Button saveBtn;

    private Button task1Btn;
    private Button task2Btn;
    private Button task3Btn;
    private Button task4Btn;
    private Button task5Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rename_tasks);

        task1Txt = (EditText) findViewById(R.id.nameTask1);
        task2Txt = (EditText) findViewById(R.id.nameTask2);
        task3Txt = (EditText) findViewById(R.id.nameTask3);
        task4Txt = (EditText) findViewById(R.id.nameTask4);
        task5Txt = (EditText) findViewById(R.id.nameTask5);

        saveBtn = (Button) findViewById(R.id.btnSave_rename);
        saveBtn.setOnClickListener(this);


        readFile();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave_rename:
                writeFile(task1Txt.getText().toString());
                break;
        }
    }

    private void readFile() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(FILENAME)));
            String s = "";

            while ((s = br.readLine()) != null){
                Toast.makeText(this, s, Toast.LENGTH_LONG).show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile(String s) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(FILENAME, MODE_PRIVATE)));

            bw.write(s);
            bw.close();
            Log.d(TAG, "File has been written!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
