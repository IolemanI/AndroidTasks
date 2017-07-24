package com.oleman.androidtasks.tasks;

import android.content.Intent;
import android.net.Uri;
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

        Button callBtn = (Button) findViewById(R.id.call_t6);
        Button webBtn = (Button) findViewById(R.id.web_t6);
        Button mapBtn = (Button) findViewById(R.id.map_t6);

        openCPBtn.setOnClickListener(this);
        callBtn.setOnClickListener(this);
        webBtn.setOnClickListener(this);
        mapBtn.setOnClickListener(this);

        registerForContextMenu(openCPBtn);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()){
            case R.id.open_color_picker_t6:
                menu.add(0, 1, 0, "Set theme");
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                Intent intent = new Intent(this, Task6Result.class);
                startActivityForResult(intent, 1);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) return;

        theme = data.getStringExtra("theme");
        Toast.makeText(this, "Theme is: "+theme, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.open_color_picker_t6:
                intent = new Intent("androidtasks.intent.action.colorpicker");
                intent.putExtra("theme", theme);
                startActivity(intent);
                break;
            case R.id.call_t6:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0731343161"));
                startActivity(intent);
                break;
            case R.id.web_t6:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://oleman95.github.io/"));
                startActivity(intent);
                break;
            case R.id.map_t6:
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:-0.45609946,-90.26607513"));
                startActivity(intent);
                break;
        }
    }
}
