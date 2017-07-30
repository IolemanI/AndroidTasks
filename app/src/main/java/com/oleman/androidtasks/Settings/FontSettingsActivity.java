package com.oleman.androidtasks.Settings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.oleman.androidtasks.R;

import static com.oleman.androidtasks.Settings.SettingAdapter.SETTING_FILE_NAME;

public class FontSettingsActivity extends AppCompatActivity implements View.OnClickListener{

    public final static String FONT_SIZE_SETTINGS = "font_size";

    private SettingAdapter settingAdapter;
    private Button smallBtn;
    private Button middleBtn;
    private Button largeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_font_settings);

        smallBtn = (Button) findViewById(R.id.small_font_settings);
        middleBtn = (Button) findViewById(R.id.middle_font_settings);
        largeBtn = (Button) findViewById(R.id.large_font_settings);

        smallBtn.setOnClickListener(this);
        middleBtn.setOnClickListener(this);
        largeBtn.setOnClickListener(this);

        settingAdapter = new SettingAdapter(FONT_SIZE_SETTINGS, getSharedPreferences(SETTING_FILE_NAME, MODE_PRIVATE));

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();

        switch (view.getId()){
            case R.id.small_font_settings:
                settingAdapter.saveText(smallBtn.getText().toString());
                Toast.makeText(this, smallBtn.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.middle_font_settings:
                settingAdapter.saveText(middleBtn.getText().toString());
                Toast.makeText(this, middleBtn.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.large_font_settings:
                settingAdapter.saveText(largeBtn.getText().toString());
                Toast.makeText(this, largeBtn.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
