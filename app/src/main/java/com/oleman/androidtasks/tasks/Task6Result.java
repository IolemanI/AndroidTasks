package com.oleman.androidtasks.tasks;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.oleman.androidtasks.R;

public class Task6Result extends AppCompatActivity implements View.OnClickListener{

    private Button blue;
    private Button grey;
    private Button yellow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task6_result);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        blue = (Button) findViewById(R.id.blue_t6);
        grey = (Button) findViewById(R.id.grey_t6);
        yellow = (Button) findViewById(R.id.yellow_t6);

        blue.setOnClickListener(this);
        grey.setOnClickListener(this);
        yellow.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.blue_t6:
                intent.putExtra("theme", "blue");
                break;
            case R.id.grey_t6:
                intent.putExtra("theme", "grey");
                break;
            case R.id.yellow_t6:
                intent.putExtra("theme", "yellow");
                break;
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
