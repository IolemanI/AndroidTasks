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

    private Menu menu;  //параметр выведен для управления чек-боксом в меню.
    private Button task1Btn;
    private Button task2Btn;
    private Button task3Btn;
    private Button task4Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        Log.d(TAG, "Find the view-elements."); //запись логов
        task1Btn = (Button) findViewById(R.id.task1Btn);
        task2Btn = (Button) findViewById(R.id.task2Btn);
        task3Btn = (Button) findViewById(R.id.task3Btn);
        task4Btn = (Button) findViewById(R.id.task4Btn);

        task1Btn.setOnClickListener(this);
        task2Btn.setOnClickListener(this);
        task3Btn.setOnClickListener(this);
        task4Btn.setOnClickListener(this);

        registerForContextMenu(task1Btn); //регистрация контекстного меню для кнопок
        registerForContextMenu(task2Btn);
        registerForContextMenu(task3Btn);
        registerForContextMenu(task4Btn);
    }

    @Override        // создание контекстного меню
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        switch (v.getId()){
            case R.id.task1Btn:
                menu.add(0, 1, 0, "Info"); //добавление элементом в контекстное меню для определенной кнопки
                break;
            case R.id.task2Btn:
                menu.add(0, 2, 0, "Info");
                break;
            case R.id.task3Btn:
                menu.add(0, 3, 0, "Info");
                break;
            case R.id.task4Btn:
                menu.add(0, 4, 0, "Info");
                break;
        }
    }

    @Override        // обработка нажатия на контекстное меню
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){   //показывает, что за TASK при нажатии на "Info"
            case 1:
                Toast.makeText(MainActivity.this, "Color picker.", Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(MainActivity.this, "Adding the buttons in real time.", Toast.LENGTH_LONG).show();
                break;
            case 3:
                Toast.makeText(MainActivity.this, "Changing the parameters of view-elements in real time.", Toast.LENGTH_LONG).show();
                break;
            case 4:
                Toast.makeText(MainActivity.this, "Calculator.", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override        // создание меню в navigation bar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);

        menu.add(1,4,3,"Display About").setCheckable(true).setChecked(true);  //программно добавляэм пункт меню.
        this.menu = menu;
        return true;
    }

    @Override        // выбор пункта меню из navigation bar
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

    @Override        // обработка нажятия на кнопку
    public void onClick(View view) {

        switch (view.getId()){   //обработка нажатия на кнопки TASK 1, 2, и т.д.
            case R.id.task1Btn:
                Intent intent1 = new Intent(this, Task1Activity.class);
                startActivity(intent1);
                Log.d(TAG, "Open Task #1.");
                break;
            case R.id.task2Btn:
                Intent intent2 = new Intent(this, Task2Activity.class);
                startActivity(intent2);
                Log.d(TAG, "Open Task #2.");

                break;
            case R.id.task3Btn:
                Intent intent3 = new Intent(this, Task3Activity.class);
                startActivity(intent3);
                Log.d(TAG, "Open Task #3.");

                break;
            case R.id.task4Btn:
                Log.d(TAG, "Task #4 clicked.");

                Toast toast = Toast.makeText(MainActivity.this, "In developing.", Toast.LENGTH_SHORT);
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
