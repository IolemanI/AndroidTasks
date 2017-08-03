package com.oleman.androidtasks.tasks;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.oleman.androidtasks.R;
import com.oleman.androidtasks.adapters.CatListAdapter;
import com.oleman.androidtasks.adapters.DBHelper;

import java.util.ArrayList;
import java.util.List;

import static com.oleman.androidtasks.MainActivity.LOG_TAG;
import static com.oleman.androidtasks.tasks.Task7AddCat.ageList;
import static com.oleman.androidtasks.tasks.Task7AddCat.careerList;
import static com.oleman.androidtasks.tasks.Task7AddCat.colorList;
import static com.oleman.androidtasks.tasks.Task7AddCat.nameList;

public class Task7Activity extends AppCompatActivity implements View.OnClickListener{

    private DBHelper dbHelper;
    private static ArrayList<String> nameList, ageList, colorList, careerList;
    private static ArrayList<Integer> idList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task7);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addCat_t7);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent("androidtasks.intent.action.task7_add_cat");
                startActivity(intent);
            }
        });

        dbHelper = new DBHelper(this);

    }

    @Override
    protected void onResume() {
        readData();
        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler_t7);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(new CatListAdapter(this, idList, nameList, ageList, colorList, careerList));
        super.onResume();
    }

    @Override
    public void onClick(View view) {

    }

    public void readData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_CATS, null, null, null, null, null, null);

        idList = new ArrayList<>();
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

                idList.add(cursor.getInt(idIndex));
                nameList.add("Name: " +cursor.getString(nameIndex));
                ageList.add("Age: " +cursor.getString(ageIndex));
                colorList.add("Color: " +cursor.getString(colorIndex));
                careerList.add("Career: " +cursor.getString(careerIndex));

            }while (cursor.moveToNext());

        }else
            Log.d(LOG_TAG, "0 rows");

        cursor.close();
    }
}
