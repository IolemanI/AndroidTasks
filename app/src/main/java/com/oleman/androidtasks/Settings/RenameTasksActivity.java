package com.oleman.androidtasks.Settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.oleman.androidtasks.FileAdapter;
import com.oleman.androidtasks.R;

import java.util.ArrayList;

import static com.oleman.androidtasks.Settings.SettingAdapter.SETTING_FILE_NAME;

public class RenameTasksActivity extends AppCompatActivity implements View.OnClickListener {

    public final static String BTN_NAMES = "button_names";

    private FileAdapter fileAdapter;
    private SharedPreferences sPreferences;
    private SettingAdapter settingAdapter;

    private EditText task1Txt;
    private EditText task2Txt;
    private EditText task3Txt;
    private EditText task4Txt;
    private EditText task5Txt;
    private EditText task6Txt;


    public RenameTasksActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rename_tasks);

        task1Txt = (EditText) findViewById(R.id.nameTask1);
        task2Txt = (EditText) findViewById(R.id.nameTask2);
        task3Txt = (EditText) findViewById(R.id.nameTask3);
        task4Txt = (EditText) findViewById(R.id.nameTask4);
        task5Txt = (EditText) findViewById(R.id.nameTask5);
        task6Txt = (EditText) findViewById(R.id.nameTask6);

        Button saveBtn = (Button) findViewById(R.id.btnSave_rename);
        Button readBtn = (Button) findViewById(R.id.readFile_rename);

        saveBtn.setOnClickListener(this);
        readBtn.setOnClickListener(this);


        settingAdapter = new SettingAdapter(BTN_NAMES, getSharedPreferences(SETTING_FILE_NAME, MODE_PRIVATE));

        getNames();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.btnSave_rename:
//                fileAdapter.writeFile(task1Txt.getText().toString()+ ", "
//                        +task2Txt.getText().toString()+ ", "
//                        +task3Txt.getText().toString()+ ", "
//                        +task4Txt.getText().toString()+ ", "
//                        +task5Txt.getText().toString()+ ", "
//                        +task6Txt.getText().toString()
//                );
                settingAdapter.saveText(task1Txt.getText().toString()+ ", "
                        +task2Txt.getText().toString()+ ", "
                        +task3Txt.getText().toString()+ ", "
                        +task4Txt.getText().toString()+ ", "
                        +task5Txt.getText().toString()+ ", "
                        +task6Txt.getText().toString());

                break;
//            case R.id.readFile_rename:
//                if (fileAdapter.isAvailable()) {
//                    Snackbar.make(view, "File is available!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
//                } else Snackbar.make(view, "File is not available!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
//
//                break
        }
        setResult(RESULT_OK, intent);
        finish();


    }

    private void getNames(){
        ArrayList<String> nameList = settingAdapter.getArrayTexts();

        if (nameList.size() > 0){
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
                    case 5:
                        task6Txt.setText(nameList.get(i));
                        break;
                }
            }
        }else {
            task1Txt.setText(R.string.task_1);
            task2Txt.setText(R.string.task_2);
            task3Txt.setText(R.string.task_3);
            task4Txt.setText(R.string.task_4);
            task5Txt.setText(R.string.task_5);
            task6Txt.setText(R.string.task_6);
        }
    }



}
