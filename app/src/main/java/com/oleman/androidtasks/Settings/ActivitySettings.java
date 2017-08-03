package com.oleman.androidtasks.Settings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.oleman.androidtasks.R;

import static com.oleman.androidtasks.Settings.SettingAdapter.SETTING_FILE_NAME;

public class ActivitySettings extends AppCompatActivity implements View.OnClickListener{

    public final static String ANIMATION_SETTINGS = "animation";

    private String data;
    private SettingAdapter adapterAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        Button renameBtn = (Button) findViewById(R.id.renameTasks_settings);
        Button themeBtn = (Button) findViewById(R.id.theme_settings);
        Button fontBtn = (Button) findViewById(R.id.font_settings);
        Switch animSw = (Switch) findViewById(R.id.swAnimation_settings);

        renameBtn.setOnClickListener(this);
        themeBtn.setOnClickListener(this);
        fontBtn.setOnClickListener(this);

        adapterAnim = new SettingAdapter(ANIMATION_SETTINGS, getSharedPreferences(SETTING_FILE_NAME, MODE_PRIVATE));

        if (adapterAnim.loadText().equals("ON")){
            animSw.setChecked(true);
        }else if(adapterAnim.loadText().equals("")){
            animSw.setChecked(true);
            adapterAnim.saveText("ON");
        }else animSw.setChecked(false);


        animSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // в зависимости от значения isChecked выводим нужное сообщение
                if (isChecked) {
                    adapterAnim.saveText("ON");
                    Toast.makeText(getApplicationContext(), "Animation is ON", Toast.LENGTH_SHORT).show();
                } else {
                    adapterAnim.saveText("OFF");
                    Toast.makeText(getApplicationContext(), "Animation is OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.renameTasks_settings:
                intent = new Intent(this, RenameTasksActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.theme_settings:
                intent = new Intent(this, SetThemeActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.font_settings:
                intent = new Intent(this, FontSettingsActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) return;

//        this.data = data.getStringExtra("theme");

    }
}
