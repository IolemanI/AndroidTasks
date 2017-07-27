package com.oleman.androidtasks.tasks;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.oleman.androidtasks.R;
import com.oleman.androidtasks.adapters.CatListAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.oleman.androidtasks.tasks.Task7AddCat.ageList;
import static com.oleman.androidtasks.tasks.Task7AddCat.careerList;
import static com.oleman.androidtasks.tasks.Task7AddCat.colorList;
import static com.oleman.androidtasks.tasks.Task7AddCat.nameList;

public class Task7Activity extends AppCompatActivity implements View.OnClickListener{

    List<String> names;
    List<String> ages;
    List<String> colors;
    List<String> careers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task7);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_t7);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//                Intent intent = new Intent("androidtasks.intent.action.task7_add_cat");
//                startActivity(intent);
//
//            }
//        });
        getCardContent();

        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler_t7);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(new CatListAdapter(names, ages, colors, careers));

    }

    private void getCardContent() {
        names = nameList;
        ages = ageList;
        colors = colorList;
        careers = careerList;
    }

    @Override
    public void onClick(View view) {

    }

}
