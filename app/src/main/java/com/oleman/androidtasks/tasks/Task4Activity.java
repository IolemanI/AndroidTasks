package com.oleman.androidtasks.tasks;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.oleman.androidtasks.Firebase.Message;
import com.oleman.androidtasks.R;


public class Task4Activity extends AppCompatActivity implements View.OnClickListener{

    private static int SIGN_IN_REQUEST_CODE = 1;
    private FirebaseListAdapter<Message> adapter;    // Это дженерик, который обеспечивает поддержку списка сообщений.
                                                     // В качестве параметризированного типа у него будет наш класс Message
    FloatingActionButton sendBtn;
    private RelativeLayout activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task4);

        activity = (RelativeLayout) findViewById(R.id.activity_main);
        sendBtn = (FloatingActionButton) findViewById(R.id.button_send_task4);
        sendBtn.setOnClickListener(this);

        // создание окна авторизации
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .build(), SIGN_IN_REQUEST_CODE);
        } else {
            displayChat();
        }

    }

    /**
     * Наполнение ListView
     */
    private void displayChat() {
        ListView listMessage = (ListView) findViewById(R.id.listView_task4);
        adapter = new FirebaseListAdapter<Message>(this, Message.class, R.layout.task4_item, FirebaseDatabase.getInstance().getReference()) {
            @Override
            protected void populateView(View v, Message model, int position) {

                TextView message, author, time;
                message = (TextView)v.findViewById(R.id.tv_message_task4);
                author = (TextView)v.findViewById(R.id.tv_user_task_4);
                time = (TextView)v.findViewById(R.id.tv_time_task4);

                message.setText(model.getTextMessage());
                author.setText(model.getAuthor());
                time.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getTimeMessage()));
            }
        };
        listMessage.setAdapter(adapter);
    }


    /**
     * Авторизация пользователя
     * Если пользователь не авторизован, то ему нужно показать форму авторизации.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                Snackbar.make(activity, "Sign in complete", Snackbar.LENGTH_SHORT).show();
                displayChat();
            }else {
                Snackbar.make(activity, "Sign in fault", Snackbar.LENGTH_SHORT).show();
                finish();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task4, menu);
        return true;
    }

    /**
     * Выход с учетной записи
     **/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_logout_task4){
            AuthUI.getInstance().signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Snackbar.make(activity, "Logout complete!", Snackbar.LENGTH_SHORT).show();
                            finish();
                        }
                    });
        }
        return true;
    }

    /**
     * Нажатие на кнопку SEND
     */
    @Override
    public void onClick(View view) {

        EditText input = (EditText)findViewById(R.id.editText_task4);

        //Далее считываем текст из поля ввода и отправляем новый экземпляр
        // сообщения в базу данных Firebase
        FirebaseDatabase.getInstance().getReference().push()
                .setValue(new Message(input.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getEmail()));
        input.setText("");

    }
}
