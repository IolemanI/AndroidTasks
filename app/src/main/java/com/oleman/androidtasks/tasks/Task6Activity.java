package com.oleman.androidtasks.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.oleman.androidtasks.R;

public class Task6Activity extends AppCompatActivity implements View.OnClickListener{

    private String theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task6);

        Button openCPBtn = (Button) findViewById(R.id.open_color_picker_t6);
        Button openEmailBtn = (Button) findViewById(R.id.open_dial_t6);

        openCPBtn.setOnClickListener(this);
        openEmailBtn.setOnClickListener(this);

        registerForContextMenu(openCPBtn);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()){
            case R.id.open_color_picker_t6:
                menu.add(0, 1, 0, "Set blue theme");
                menu.add(0, 2, 0, "Set grey theme");
                menu.add(0, 3, 0, "Set yellow theme");
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                theme = "blue";
                Toast.makeText(this, "Selected blue theme", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                theme = "grey";
                Toast.makeText(this, "Selected grey theme", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                theme = "yellow";
                Toast.makeText(this, "Selected yellow theme", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.open_color_picker_t6:
//                if (theme != null){
//                    intent = new Intent(this, Task3Activity.class);
//                    intent.putExtra("theme", theme);
//                    startActivity(intent);
//                }
                intent = new Intent("androidtasks.intent.action.colorpicker");
                intent.putExtra("theme", theme);
                startActivity(intent);
                break;
            case R.id.open_dial_t6:
                intent = new Intent(Intent.ACTION_DIAL);
                startActivity(intent);
                break;
        }
    }
}
