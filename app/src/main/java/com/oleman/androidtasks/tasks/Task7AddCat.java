package com.oleman.androidtasks.tasks;

import android.content.ContentValues;
import android.content.Intent;
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

import com.oleman.androidtasks.adapters.DBHelper;
import com.oleman.androidtasks.R;

import java.util.ArrayList;

import static com.oleman.androidtasks.MainActivity.LOG_TAG;

/**
 * Важное замечание
 * Я выполняю все операции с базой данных в основном потоке.
 * Я делаю так, чтобы не усложнять урок.
 * Но в реале вам следует использовать для работы с БД отдельный поток,
 * чтобы ваше приложение не тормозило визуально. О том, как это сделать,
 * написано в уроках 80-91 и 135-136.
 */

public class Task7AddCat extends AppCompatActivity implements View.OnClickListener{

    private final String[] COLORS = {"red", "black", "white", "gray"};

    public int ID;
    public String NAME;
    public String AGE;
    public String COLOR;
    public String CAREER;

    public static ArrayList<String> nameList;
    public static ArrayList<String> ageList;
    public static ArrayList<String> colorList;
    public static ArrayList<String> careerList;

    private String colorTxt;

    private EditText nameText;
    private EditText ageText;
    private EditText careerText;

    private DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task7_add_cat);

        nameText = (EditText) findViewById(R.id.name_t7);
        ageText = (EditText) findViewById(R.id.age_t7);
        careerText = (EditText) findViewById(R.id.career_t7);
        Spinner colorSpin = (Spinner) findViewById(R.id.color_t7);

        Button saveBtn = (Button) findViewById(R.id.save_t7);
        Button readBtn = (Button) findViewById(R.id.read_t7);
        Button clearBtn = (Button) findViewById(R.id.clear_t7);

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
        //с помощью метода getWritableDatabase подключаемся к БД и получаем объект SQLiteDatabase.
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        //Класс ContentValues используется для указания полей
        // таблицы и значений, которые мы в эти поля будем вставлять.

        switch (view.getId()){
            case R.id.save_t7:
                contentValues.put(DBHelper.KEY_NAME, nameText.getText().toString());
                contentValues.put(DBHelper.KEY_AGE, ageText.getText().toString());
                contentValues.put(DBHelper.KEY_COLOR, colorTxt);
                contentValues.put(DBHelper.KEY_CAREER, careerText.getText().toString());

                long rowID = db.insert(DBHelper.TABLE_CATS, null, contentValues);
                //Вызываем метод insert – передаем ему имя таблицы и объект contentValues
                // с вставляемыми значениями.
                Log.d(LOG_TAG, "row inserted, ID = " + rowID);
                Toast.makeText(this, "Cat has been saved.", Toast.LENGTH_SHORT).show();

                nameText.setText("");
                ageText.setText("");
                careerText.setText("");
                break;
            case R.id.read_t7:
                Cursor cursor = db.query(DBHelper.TABLE_CATS, null, null, null, null, null, null);

                nameList = new ArrayList<>();
                ageList = new ArrayList<>();
                colorList = new ArrayList<>();
                careerList = new ArrayList<>();

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



                        nameList.add("Name: " +cursor.getString(nameIndex));
                        ageList.add("Age: " +cursor.getString(ageIndex));
                        colorList.add("Color: " +cursor.getString(colorIndex));
                        careerList.add("Career: " +cursor.getString(careerIndex));


                    }while (cursor.moveToNext());



                }else
                    Log.d(LOG_TAG, "0 rows");

                cursor.close();
                printArrays();
                Intent intent = new Intent(this, Task7Activity.class);
                startActivity(intent);
                break;
            case R.id.clear_t7:
                db.delete(DBHelper.TABLE_CATS, null, null);
                break;
        }
        dbHelper.close();
    }

    private void printArrays() {
        for (String s: nameList){
            Log.d(LOG_TAG, s);
        }
        for (String s: ageList){
            Log.d(LOG_TAG, s);
        }
        for (String s: colorList){
            Log.d(LOG_TAG, s);
        }
        for (String s: careerList){
            Log.d(LOG_TAG, s);
        }
    }


    public void putInArrays(String name, String age, String color, String career){




    }




}
