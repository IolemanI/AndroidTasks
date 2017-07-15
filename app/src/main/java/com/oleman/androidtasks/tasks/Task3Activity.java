package com.oleman.androidtasks.tasks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.oleman.androidtasks.R;

public class Task3Activity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{


    private Button green;
    private Button red;
    private LinearLayout.LayoutParams lParamsGreen;
    private LinearLayout.LayoutParams lParamsRed;
    SeekBar seekBar;

    private int greenNum;
    private int redNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task3);

        green = (Button) findViewById(R.id.buttonGreen);
        red = (Button) findViewById(R.id.buttonRed);
        seekBar = (SeekBar) findViewById(R.id.seekBarTask3);
        seekBar.setOnSeekBarChangeListener(this);   //говорим, что обработчик SeekBar-а находится ниже (строка 41)

        lParamsGreen = (LinearLayout.LayoutParams) green.getLayoutParams();
        lParamsRed = (LinearLayout.LayoutParams) red.getLayoutParams();



    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        redNum = i;       //вес красной кнопки - параметр i (progress)
        greenNum = seekBar.getMax() - i;   //вес зеленой кнопки - все оставшееся

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
