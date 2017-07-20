package com.oleman.androidtasks.Settings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.oleman.androidtasks.R;

public class ActivitySettings extends AppCompatActivity implements View.OnClickListener {

    private Button renameTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        renameTasks = (Button) findViewById(R.id.renameTasks_settings);
        renameTasks.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.renameTasks_settings:
                Intent intent = new Intent(this, RenameTasksActivity.class);
                startActivity(intent);
                break;
        }
    }
}
