package com.oleman.androidtasks.tasks;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.oleman.androidtasks.R;

public class Task3Activity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{


    private Button green;
    private Button red;
    private LinearLayout.LayoutParams lParamsGreen;
    private LinearLayout.LayoutParams lParamsRed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task3);

        green = (Button) findViewById(R.id.buttonGreen_t3);
        red = (Button) findViewById(R.id.buttonRed_t3);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBarTask3);
        seekBar.setOnSeekBarChangeListener(this);   //говорим, что обработчик SeekBar-а находится ниже (строка 41)

        lParamsGreen = (LinearLayout.LayoutParams) green.getLayoutParams();
        lParamsRed = (LinearLayout.LayoutParams) red.getLayoutParams();

        Intent intent = getIntent();
        String action = intent.getAction();

        if (action != null) {
            if (action.equals("androidtasks.intent.action.colorpicker")) {
                registerForContextMenu(green);
                registerForContextMenu(red);

                Toast.makeText(this, "You can call the context menu!", Toast.LENGTH_LONG).show();
            }
        }

        String theme = intent.getStringExtra("theme");

        if(theme != null){
            if (theme.equals("blue")){
                red.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
                green.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            }else if (theme.equals("grey")){
                red.setBackgroundColor(getResources().getColor(R.color.ligth_color_task5));
                green.setBackgroundColor(getResources().getColor(R.color.dark_color_task5));

                red.setTextColor(getResources().getColor(R.color.white));
                green.setTextColor(getResources().getColor(R.color.white));
            }else if (theme.equals("yellow")){
                red.setBackgroundColor(getResources().getColor(R.color.yellow_task6));
                green.setBackgroundColor(getResources().getColor(R.color.dark_yellow_task6));
            }
        }



    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()){
            case R.id.buttonGreen_t3:
                menu.add(0, 4, 0, "Red");
                menu.add(0, 5, 0, "Green");
                menu.add(0, 6, 0, "Blue");
                break;
            case R.id.buttonRed_t3:
                menu.add(0, 1, 0, "Red");
                menu.add(0, 2, 0, "Green");
                menu.add(0, 3, 0, "Blue");
                break;

        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){   //показывает, что за TASK при нажатии на "Info"
            case 1:
                red.setBackgroundColor(getResources().getColor(R.color.colorRed));
                break;
            case 2:
                red.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                break;
            case 3:
                red.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            case 4:
                green.setBackgroundColor(getResources().getColor(R.color.colorRed));
                break;
            case 5:
                green.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                break;
            case 6:
                green.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        int redNum = i;
        int greenNum = seekBar.getMax() - i;

        lParamsRed.weight = redNum;     //устанавливаем вес
        lParamsGreen.weight = greenNum;

        red.setText(String.valueOf(redNum));   //пишем значение в название кнопки
        green.setText(String.valueOf(greenNum));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
