package com.oleman.androidtasks.tasks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oleman.androidtasks.R;

public class Task1Activity extends AppCompatActivity implements View.OnClickListener {

    private Button redBtn;
    private Button greenBtn;
    private Button blueBtn;
    private TextView text;
    private LinearLayout layout;
    private String theme = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task1);

        text = (TextView) findViewById(R.id.textTask1);
        layout = (LinearLayout) findViewById(R.id.task1_layout);
        redBtn = (Button) findViewById(R.id.redBtn);
        greenBtn = (Button) findViewById(R.id.greenBtn);
        blueBtn = (Button) findViewById(R.id.blueBtn);

        redBtn.setOnClickListener(this);
        greenBtn.setOnClickListener(this);
        blueBtn.setOnClickListener(this);

        Intent intent = getIntent();
        theme = intent.getStringExtra("theme"); //set in the Task6

        if(theme != null){
            redBtn.setText(R.string.light_btn_t1);
            greenBtn.setText(R.string.middle_btn_t1);
            blueBtn.setText(R.string.dark_btn_t1);
            redBtn.setTextColor(getResources().getColor(R.color.dark_color_task5));
            greenBtn.setTextColor(getResources().getColor(R.color.dark_color_task5));
            blueBtn.setTextColor(getResources().getColor(R.color.dark_color_task5));
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.redBtn:
                if(theme != null){
                    text.setText("Light colored");
                    whatTheTheme(R.id.redBtn);
                    break;
                }
                text.setText("Color: RED");
                layout.setBackgroundColor(getResources().getColor(R.color.colorRed));
                break;
            case R.id.greenBtn:
                if(theme != null){
                    text.setText("Middle colored");
                    whatTheTheme(R.id.greenBtn);
                    break;
                }
                text.setText("Color: GREEN");
                layout.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                break;
            case R.id.blueBtn:
                if(theme != null){
                    text.setText("Dark colored");
                    whatTheTheme(R.id.blueBtn);
                    break;
                }
                text.setText("Color: BLUE");
                layout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
        }
    }

    /**
     * At first needs to set the theme in Task6 by calling the context menu.
     * Then here we getting string code (blue, gray or yellow) by getting intent Extras.
     * And by depending on what button we press, set the light, middle or dark colored layout.
     * @param id This is an id of button we have pressed.
     */
    private void whatTheTheme(int id){

        if (theme.equals("blue")){
            switch (id){
                case R.id.redBtn:
                    layout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLightest));
                    break;
                case R.id.greenBtn:
                    layout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    break;
                case R.id.blueBtn:
                    layout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    break;
            }

        }else if (theme.equals("grey")){
            text.setTextColor(getResources().getColor(R.color.white));
            switch (id){
                case R.id.redBtn:
                    layout.setBackgroundColor(getResources().getColor(R.color.ligth_color_task5));
                    break;
                case R.id.greenBtn:
                    layout.setBackgroundColor(getResources().getColor(R.color.main_color_task5));
                    break;
                case R.id.blueBtn:
                    layout.setBackgroundColor(getResources().getColor(R.color.dark_color_task5));
                    break;
            }

        }else if (theme.equals("yellow")){
            switch (id){
                case R.id.redBtn:
                    layout.setBackgroundColor(getResources().getColor(R.color.yellow_task6));
                    break;
                case R.id.greenBtn:
                    layout.setBackgroundColor(getResources().getColor(R.color.middle_yellow_task6));
                    break;
                case R.id.blueBtn:
                    layout.setBackgroundColor(getResources().getColor(R.color.dark_yellow_task6));
                    break;
            }

        }

    }
}
