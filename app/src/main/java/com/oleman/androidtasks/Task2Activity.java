package com.oleman.androidtasks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.StringReader;

public class Task2Activity extends AppCompatActivity implements View.OnClickListener {

    Button createBtn;
    Button clearBtn;
    RadioGroup rgGravity;
    LinearLayout layoutForButtons;
    EditText buttonsNameText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);

        createBtn = (Button) findViewById(R.id.createBtn);
        clearBtn = (Button) findViewById(R.id.clearBtn);

        rgGravity = (RadioGroup) findViewById(R.id.rgGravity);
        buttonsNameText = (EditText) findViewById(R.id.buttonNameText);
        layoutForButtons = (LinearLayout) findViewById(R.id.layoutForButtons);

        createBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.createBtn:  //обработка нажатия кнопки "CREATE"
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                int btnGravity = Gravity.LEFT;  //создание переменной гравитации

                switch (rgGravity.getCheckedRadioButtonId()){  //проверка какая радиокнопка выбрана в группе rgGravity
                    case R.id.rbLeft:
                        btnGravity = Gravity.LEFT;
                        break;
                    case R.id.rbCenter:
                        btnGravity = Gravity.CENTER_HORIZONTAL;
                        break;
                    case R.id.rbRight:
                        btnGravity = Gravity.RIGHT;
                        break;
                }
                layoutParams.gravity = btnGravity;  //загрузка в layoutParams параметра гравитация

                Button newBtn = new Button(this);
                newBtn.setText(buttonsNameText.getText().toString()); //установка текста кнопки из текстового поля buttonsNameText
                layoutForButtons.addView(newBtn, layoutParams);

                break;
            case R.id.clearBtn:   //обработка нажатия кнопки "CLEAR"
                layoutForButtons.removeAllViews(); //удаление всех view из layoutForButtons

                //вывод на дисплей короткого сообщения "Тост" с изображением.
                Toast toast = Toast.makeText(Task2Activity.this, "Deleted!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0); // создание гравитации для тоста
                LinearLayout toastImage = (LinearLayout) toast.getView();
                ImageView imageView = new ImageView(Task2Activity.this);
                imageView.setImageResource(R.drawable.done);
                toastImage.addView(imageView, 0);
                toast.show();

                break;
        }
    }
}
