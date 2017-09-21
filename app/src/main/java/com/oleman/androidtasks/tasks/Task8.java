package com.oleman.androidtasks.tasks;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oleman.androidtasks.Firebase.CVData;
import com.oleman.androidtasks.R;

import static com.oleman.androidtasks.MainActivity.LOG_TAG;

public class Task8 extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    private FirebaseUser user = mAuth.getInstance().getCurrentUser();
    private FirebaseListAdapter mAdapter;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task8);

        listView = (ListView) findViewById(R.id.listView_task8);

        myRef = FirebaseDatabase.getInstance().getReference();
        mAdapter = new FirebaseListAdapter <CVData>(this, CVData.class, R.layout.task8_item, myRef.child("messages")) {
            @Override
            protected void populateView(View v, CVData data, int position) {
                TextView message, email, name, time;
                message = (TextView)v.findViewById(R.id.tv_message_task8);
                email = (TextView)v.findViewById(R.id.tv_email_task8);
                name = (TextView)v.findViewById(R.id.tv_name_task_8);
                time = (TextView)v.findViewById(R.id.time_t8);

                message.setText(data.getMessage());
                email.setText(data.getEmail());
                name.setText(data.getName());
                time.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", data.getTimeMessage()));

            }
        };
        listView.setAdapter(mAdapter);

    }

}
