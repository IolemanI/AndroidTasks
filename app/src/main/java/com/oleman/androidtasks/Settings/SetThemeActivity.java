package com.oleman.androidtasks.Settings;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.oleman.androidtasks.R;

import static com.oleman.androidtasks.Settings.SettingAdapter.SETTING_FILE_NAME;

public class SetThemeActivity extends AppCompatActivity {

    public final static String THEME_SETTINGS = "theme";

    private SettingAdapter adapterTheme;
    private Button lightBtn;
    private Button darkBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_set_theme);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        adapterTheme = new SettingAdapter(THEME_SETTINGS, getSharedPreferences(SETTING_FILE_NAME, MODE_PRIVATE));
        lightBtn = (Button) findViewById(R.id.light_theme);
        darkBtn = (Button) findViewById(R.id.dark_theme);


        (findViewById(R.id.light_theme)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterTheme.saveText(lightBtn.getText().toString());
                setResult(RESULT_OK);
                finish();
            }
        });

        (findViewById(R.id.dark_theme)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterTheme.saveText(darkBtn.getText().toString());
                setResult(RESULT_OK);
                finish();
            }
        });
    }


}
