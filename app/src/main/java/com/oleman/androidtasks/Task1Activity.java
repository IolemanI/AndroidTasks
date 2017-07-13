package com.oleman.androidtasks;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Task1Activity extends AppCompatActivity implements View.OnClickListener {

    private Button redBtn;
    private Button greenBtn;
    private Button blueBtn;

    private TextView text;

    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);

        text = (TextView) findViewById(R.id.textTask1);

        layout = (LinearLayout) findViewById(R.id.task1_layout);

        redBtn = (Button) findViewById(R.id.redBtn);
        greenBtn = (Button) findViewById(R.id.greenBtn);
        blueBtn = (Button) findViewById(R.id.blueBtn);

        redBtn.setOnClickListener(this);
        greenBtn.setOnClickListener(this);
        blueBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.redBtn:
                text.setText("Color: RED");
                layout.setBackgroundColor(getResources().getColor(R.color.colorRed));
                break;
            case R.id.greenBtn:
                text.setText("Color: GREEN");
                layout.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                break;
            case R.id.blueBtn:
                text.setText("Color: BLUE");
                layout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
        }
    }
}
