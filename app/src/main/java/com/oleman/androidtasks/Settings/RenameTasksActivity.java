package com.oleman.androidtasks.Settings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    private Button saveBtn;
    private Button readBtn;

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

        readBtn = (Button) findViewById(R.id.readFile_rename);
        readBtn.setOnClickListener(this);

        fileAdapter = new FileAdapter(getApplicationContext());

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
                } else Toast.makeText(this, "File is not available!", Toast.LENGTH_SHORT).show();

                break;
        }
    }


}
