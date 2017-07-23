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

import java.util.ArrayList;

import static com.oleman.androidtasks.MainActivity.LOG_TAG;

public class RenameTasksActivity extends AppCompatActivity implements View.OnClickListener {

    FileAdapter fileAdapter;

    private EditText task1Txt;
    private EditText task2Txt;
    private EditText task3Txt;
    private EditText task4Txt;
    private EditText task5Txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rename_tasks);

        task1Txt = (EditText) findViewById(R.id.nameTask1);
        task2Txt = (EditText) findViewById(R.id.nameTask2);
        task3Txt = (EditText) findViewById(R.id.nameTask3);
        task4Txt = (EditText) findViewById(R.id.nameTask4);
        task5Txt = (EditText) findViewById(R.id.nameTask5);

        Button saveBtn = (Button) findViewById(R.id.btnSave_rename);
        Button readBtn = (Button) findViewById(R.id.readFile_rename);

        saveBtn.setOnClickListener(this);
        readBtn.setOnClickListener(this);

        fileAdapter = new FileAdapter(getApplicationContext());

        getNames();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave_rename:
                fileAdapter.writeFile(task1Txt.getText().toString()+ ", " +task2Txt.getText().toString()+ ", "
                        +task3Txt.getText().toString()+ ", " +task4Txt.getText().toString()+ ", "
                        +task5Txt.getText().toString());
                finish();

                break;
            case R.id.readFile_rename:
                if (fileAdapter.isAvailable()) {
                    Toast.makeText(this, "File is available!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, getIntent().getStringExtra("1"), Toast.LENGTH_SHORT).show();
                } else Toast.makeText(this, "File is not available!", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    private void getNames(){
        FileAdapter fileAdapter = new FileAdapter(this);
        ArrayList<String> nameList = fileAdapter.getNameList();

        if (fileAdapter.isAvailable()){
            for (int i=0; i<nameList.size(); i++){
                switch (i){
                    case 0:
                        task1Txt.setText(nameList.get(i));
                        break;
                    case 1:
                        task2Txt.setText(nameList.get(i));
                        break;
                    case 2:
                        task3Txt.setText(nameList.get(i));
                        break;
                    case 3:
                        task4Txt.setText(nameList.get(i));
                        break;
                    case 4:
                        task5Txt.setText(nameList.get(i));
                        break;
                }
            }
        }else {
            task1Txt.setText("Task 1");
            task2Txt.setText("Task 2");
            task3Txt.setText("Task 3");
            task4Txt.setText("Task 4");
            task5Txt.setText("Task 5");
        }
    }



}
