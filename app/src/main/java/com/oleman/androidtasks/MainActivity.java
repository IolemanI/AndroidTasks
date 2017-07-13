package com.oleman.androidtasks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int LAYOUT = R.layout.activity_main;

    Button task1Btn;
    Button task2Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        task1Btn = (Button) findViewById(R.id.task1Btn);
        task2Btn = (Button) findViewById(R.id.task2Btn);

        task1Btn.setOnClickListener(this);
        task2Btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.task1Btn:
                Intent intent = new Intent(this, Task1Activity.class);
                startActivity(intent);
                break;
            case R.id.task2Btn:
                task2Btn.setText("Clicked");
                break;
        }
    }
}
