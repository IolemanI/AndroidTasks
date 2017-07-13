package com.oleman.androidtasks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int LAYOUT = R.layout.activity_main;
    public static final String TAG = "myLogs";

    private Menu menu;  //выведено меню для управления чек-боксом в меню.
    private Button task1Btn;
    private Button task2Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        Log.d(TAG, "Find the view-elements."); //запись логов
        task1Btn = (Button) findViewById(R.id.task1Btn);
        task2Btn = (Button) findViewById(R.id.task2Btn);

        task1Btn.setOnClickListener(this);
        task2Btn.setOnClickListener(this);

        registerForContextMenu(task1Btn); //регистрация контекстного меню для кнопок
        registerForContextMenu(task2Btn);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        switch (v.getId()){
            case R.id.task1Btn:
                menu.add(0, 1, 0, "What the task?"); //добавление элементом в контекстное меню для определенной кнопки
                break;
            case R.id.task2Btn:
                menu.add(0, 1, 0, "What the task?");
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);

        menu.add(1,4,3,"Display About").setCheckable(true).setChecked(true);  //программно добавляэм пункт меню.
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){     //обработка нажатия на пункты меню
            case R.id.action_about:
                Toast.makeText(MainActivity.this, "In developing", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_setting:
                Toast.makeText(MainActivity.this, "In developing", Toast.LENGTH_LONG).show();
                break;
            case 4:
                item.setChecked(!item.isChecked());         //обработка чек-бокса в меню
                if (item.isChecked()) menu.findItem(R.id.action_about).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
                else if (!item.isChecked())menu.findItem(R.id.action_about).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){   //обработка нажатия на кнопки TASK 1, 2, и т.д.
            case R.id.task1Btn:
                Intent intent = new Intent(this, Task1Activity.class);
                startActivity(intent);
                Log.d(TAG, "Open Task #1.");
                break;
            case R.id.task2Btn:
                task2Btn.setText("Clicked");
                Log.d(TAG, "Button TASK 2 clicked.");

                //вывод на дисплей короткого сообщения "Тост" с изоюражением.
                Toast toast = Toast.makeText(MainActivity.this, "TASK 2 in developing!", Toast.LENGTH_SHORT);
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
