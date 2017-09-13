package com.oleman.androidtasks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.oleman.androidtasks.Settings.ActivitySettings;
import com.oleman.androidtasks.Settings.SettingAdapter;
import com.oleman.androidtasks.tasks.Task1Activity;
import com.oleman.androidtasks.tasks.Task2Activity;
import com.oleman.androidtasks.tasks.Task3Activity;
import com.oleman.androidtasks.tasks.Task4Activity;
import com.oleman.androidtasks.tasks.Task5Activity;
import com.oleman.androidtasks.tasks.Task7Activity;
import com.oleman.androidtasks.tasks.Task7AddCat;
import com.oleman.androidtasks.tasks.Task8;

import java.util.ArrayList;

import static com.oleman.androidtasks.Settings.ActivitySettings.ANIMATION_SETTINGS;
import static com.oleman.androidtasks.Settings.FontSettingsActivity.FONT_SIZE_SETTINGS;
import static com.oleman.androidtasks.Settings.RenameTasksActivity.BTN_NAMES;
import static com.oleman.androidtasks.Settings.SetThemeActivity.THEME_SETTINGS;
import static com.oleman.androidtasks.Settings.SettingAdapter.SETTING_FILE_NAME;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int LAYOUT = R.layout.main;
    public static final String LOG_TAG = "myLogs";

    private Menu menu;
    private SettingAdapter settingAdapter;
    private SettingAdapter settingAdapter1;
    private SettingAdapter settingAdapter2;
    private SettingAdapter adapterAnim;
    public Button task1Btn;
    public Button task2Btn;
    public Button task3Btn;
    public Button task4Btn;
    public Button task5Btn;
    public Button task6Btn;
    public Button task7Btn;
    public Button task8Btn;

    private Animation anim1 = null;
    private Animation anim2 = null;
    private Animation anim3 = null;
    private Animation anim4 = null;
    private Animation anim5 = null;
    private Animation anim6 = null;
    private Animation anim7 = null;
    private Animation anim8 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        task1Btn = (Button) findViewById(R.id.task1Btn);
        task2Btn = (Button) findViewById(R.id.task2Btn);
        task3Btn = (Button) findViewById(R.id.task3Btn);
        task4Btn = (Button) findViewById(R.id.task4Btn);
        task5Btn = (Button) findViewById(R.id.task5Btn);
        task6Btn = (Button) findViewById(R.id.task6Btn);
        task7Btn = (Button) findViewById(R.id.task7Btn);
        task8Btn = (Button) findViewById(R.id.task8Btn);

        task1Btn.setOnClickListener(this);
        task2Btn.setOnClickListener(this);
        task3Btn.setOnClickListener(this);
        task4Btn.setOnClickListener(this);
        task5Btn.setOnClickListener(this);
        task6Btn.setOnClickListener(this);
        task7Btn.setOnClickListener(this);
        task8Btn.setOnClickListener(this);

        registerForContextMenu(task1Btn); //регистрация контекстного меню для кнопок
        registerForContextMenu(task2Btn);
        registerForContextMenu(task3Btn);
        registerForContextMenu(task4Btn);
        registerForContextMenu(task5Btn);
        registerForContextMenu(task6Btn);
        registerForContextMenu(task7Btn);
        registerForContextMenu(task8Btn);

        settingAdapter = new SettingAdapter(BTN_NAMES, getSharedPreferences(SETTING_FILE_NAME, MODE_PRIVATE));
        settingAdapter1 = new SettingAdapter(FONT_SIZE_SETTINGS, getSharedPreferences(SETTING_FILE_NAME, MODE_PRIVATE));
        settingAdapter2 = new SettingAdapter(THEME_SETTINGS, getSharedPreferences(SETTING_FILE_NAME, MODE_PRIVATE));
        adapterAnim = new SettingAdapter(ANIMATION_SETTINGS, getSharedPreferences(SETTING_FILE_NAME, MODE_PRIVATE));

        renameButtons();

        if (adapterAnim.loadText().equals("ON")) startAnimation();

    }

    @Override
    protected void onResume() {
        String testStr = "F: "+settingAdapter1.loadText()+ "; "
                +"T: "+settingAdapter2.loadText()+ "; "
                +"A: "+adapterAnim.loadText();
        Toast.makeText(this, testStr, Toast.LENGTH_LONG).show();


        renameButtons();
        super.onResume();
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
            case R.id.task5Btn:
                menu.add(0, 5, 0, "Info");
                break;
            case R.id.task6Btn:
                menu.add(0, 6, 0, "Info");
                break;
            case R.id.task7Btn:
                menu.add(0, 7, 0, "Info");
                break;
            case R.id.task8Btn:
                menu.add(0, 7, 0, "Info");
                break;
        }
    }

    @Override        // обработка нажатия на контекстное меню
    public boolean onContextItemSelected(MenuItem item){

        switch (item.getItemId()){   //показывает, что за TASK при нажатии на "Info"
            case 1:
                Toast.makeText(MainActivity.this, "Color picker.", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(MainActivity.this, "Adding the buttons in real time.", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(MainActivity.this, "Changing the parameters of view-elements in real time.", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(MainActivity.this, "Firebase chat.", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(MainActivity.this, "Calculator.", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                Toast.makeText(MainActivity.this, "Here is some intent experiments.", Toast.LENGTH_SHORT).show();
                break;
            case 7:
                Toast.makeText(MainActivity.this, "Work with DataBases.", Toast.LENGTH_SHORT).show();
                break;
            case 8:
                Toast.makeText(MainActivity.this, "Here are all my CV messages.", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override        // создание меню в navigation bar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);

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
                Intent intent = new Intent(this, ActivitySettings.class);
                startActivity(intent);
                break;
//            case START_ANIMATION:
//                startAnimation();
//                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override        // обработка нажятия на кнопку
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()){   //обработка нажатия на кнопки TASK 1, 2, и т.д.
            case R.id.task1Btn:
                intent = new Intent(this, Task1Activity.class);
                startActivity(intent);
                break;
            case R.id.task2Btn:
                intent = new Intent(this, Task2Activity.class);
                startActivity(intent);

                break;
            case R.id.task3Btn:
                intent = new Intent(this, Task3Activity.class);
                startActivity(intent);

                break;
            case R.id.task4Btn:
                intent = new Intent(this, Task4Activity.class);
                startActivity(intent);

                break;
            case R.id.task5Btn:
                intent = new Intent(this, Task5Activity.class);
                startActivity(intent);

                break;
            case R.id.task6Btn:
                intent = new Intent("androidtasks.intent.action.task6");
                startActivity(intent);

                break;
            case R.id.task7Btn:
                intent = new Intent(this, Task7Activity.class);
                startActivity(intent);

                break;
            case R.id.task8Btn:
                intent = new Intent(this, Task8.class);
                startActivity(intent);
                break;
//            case R.id.task7Btn:
//                Log.d(LOG_TAG, "Task #4 clicked.");
//
//                Toast toast = Toast.makeText(MainActivity.this, "In developing.", Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.CENTER, 0, 0);
//                LinearLayout toastImage = (LinearLayout) toast.getView();
//                ImageView imageView = new ImageView(MainActivity.this);
//                imageView.setImageResource(R.drawable.toast_image);
//                toastImage.addView(imageView, 0);
//                toast.show();
//
//                break;
        }
    }

    private void renameButtons(){
        ArrayList<String> nameList = settingAdapter.getArrayTexts();

        if (nameList.size() > 0){
            for (int i=0; i<nameList.size(); i++){
                switch (i){
                    case 0:
                        task1Btn.setText(nameList.get(i));
                        break;
                    case 1:
                        task2Btn.setText(nameList.get(i));
                        break;
                    case 2:
                        task3Btn.setText(nameList.get(i));
                        break;
                    case 3:
                        task4Btn.setText(nameList.get(i));
                        break;
                    case 4:
                        task5Btn.setText(nameList.get(i));
                        break;
                    case 5:
                        task6Btn.setText(nameList.get(i));
                        break;
                    case 6:
                        task7Btn.setText(nameList.get(i));
                        break;
                }
            }
        }else {
            task1Btn.setText(R.string.task_1);
            task2Btn.setText(R.string.task_2);
            task3Btn.setText(R.string.task_3);
            task4Btn.setText(R.string.task_4);
            task5Btn.setText(R.string.task_5);
            task6Btn.setText(R.string.task_6);
            task7Btn.setText(R.string.task_7);
        }
    }

    private void startAnimation(){
        anim1 = AnimationUtils.loadAnimation(this, R.anim.task_btns_anim);
        anim2 = AnimationUtils.loadAnimation(this, R.anim.task_btns_anim);
        anim3 = AnimationUtils.loadAnimation(this, R.anim.task_btns_anim);
        anim4 = AnimationUtils.loadAnimation(this, R.anim.task_btns_anim);
        anim5 = AnimationUtils.loadAnimation(this, R.anim.task_btns_anim);
        anim6 = AnimationUtils.loadAnimation(this, R.anim.task_btns_anim);
        anim7 = AnimationUtils.loadAnimation(this, R.anim.task_btns_anim);

        task1Btn.startAnimation(anim1);
        anim2.setStartOffset(300);
        task2Btn.startAnimation(anim2);
        anim3.setStartOffset(600);
        task3Btn.startAnimation(anim3);
        anim4.setStartOffset(900);
        task4Btn.startAnimation(anim4);
        anim5.setStartOffset(1200);
        task5Btn.startAnimation(anim5);
        anim6.setStartOffset(1500);
        task6Btn.startAnimation(anim6);
        anim7.setStartOffset(2000);
        task7Btn.startAnimation(anim7);

    }
}
