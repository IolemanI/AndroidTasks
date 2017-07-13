package com.oleman.androidtasks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int LAYOUT = R.layout.activity_main;
    public static final String TAG = "myLogs";

    Button task1Btn;
    Button task2Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        Log.d(TAG, "Find the view-elements.");
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
                Log.d(TAG, "Open Task #1.");
                break;
            case R.id.task2Btn:
                task2Btn.setText("Clicked");
                Log.d(TAG, "Button TASK 2 clicked.");
                Toast toast = Toast.makeText(MainActivity.this, "Button TASK 2 was pressed!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                LinearLayout toastImage = (LinearLayout) toast.getView();
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(R.drawable.toast_image);
                toastImage.addView(imageView, 0);
                toast.show();
                break;
        }
    }
}
