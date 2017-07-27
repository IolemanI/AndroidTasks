package com.oleman.androidtasks.tasks;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.oleman.androidtasks.DBHelper;
import com.oleman.androidtasks.R;

import static com.oleman.androidtasks.MainActivity.LOG_TAG;

public class Task7Activity extends AppCompatActivity implements View.OnClickListener{

    private final String[] COLORS = {"red", "black", "white", "gray"};

    private String colorTxt;

    private EditText nameText;
    private EditText ageText;
    private EditText careerText;
    private Spinner colorSpin;
    private Button saveBtn;
    private Button readBtn;
    private Button clearBtn;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task7);

        nameText = (EditText) findViewById(R.id.name_t7);
        ageText = (EditText) findViewById(R.id.age_t7);
        careerText = (EditText) findViewById(R.id.career_t7);
        colorSpin = (Spinner) findViewById(R.id.color_t7);

        saveBtn = (Button) findViewById(R.id.save_t7);
        readBtn = (Button) findViewById(R.id.read_t7);
        clearBtn = (Button) findViewById(R.id.clear_t7);

        saveBtn.setOnClickListener(this);
        readBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);

        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, COLORS);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        colorSpin.setAdapter(spinAdapter);
        colorSpin.setPrompt("Color");

        // устанавливаем обработчик нажатия
        colorSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch (position){
                    case 0:
                        colorTxt = "red";
                        break;
                    case 1:
                        colorTxt = "black";
                        break;
                    case 2:
                        colorTxt = "white";
                        break;
                    case 3:
                        colorTxt = "gray";
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View view) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        switch (view.getId()){
            case R.id.save_t7:
                contentValues.put(DBHelper.KEY_NAME, nameText.getText().toString());
                contentValues.put(DBHelper.KEY_AGE, ageText.getText().toString());
                contentValues.put(DBHelper.KEY_COLOR, colorTxt);
                contentValues.put(DBHelper.KEY_CAREER, careerText.getText().toString());

                db.insert(DBHelper.TABLE_CATS, null, contentValues);
                break;
            case R.id.read_t7:
                Cursor cursor = db.query(DBHelper.TABLE_CATS, null, null, null, null, null, null);

                if (cursor.moveToFirst()){
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                    int ageIndex = cursor.getColumnIndex(DBHelper.KEY_AGE);
                    int colorIndex = cursor.getColumnIndex(DBHelper.KEY_COLOR);
                    int careerIndex = cursor.getColumnIndex(DBHelper.KEY_CAREER);
                        do{
                            Log.d(LOG_TAG, "ID = "+cursor.getInt(idIndex)
                                    +", NAME = "+cursor.getString(nameIndex)
                                    +", AGE = "+cursor.getString(ageIndex)
                                    +", COLOR = "+cursor.getString(colorIndex)
                                    +", CAREER = "+cursor.getString(careerIndex)
                            );
                        }while (cursor.moveToNext());

                }else
                    Log.d(LOG_TAG, "0 rows");

                cursor.close();
                break;
            case R.id.clear_t7:
                db.delete(DBHelper.TABLE_CATS, null, null);
                break;
        }
        dbHelper.close();
    }

}
