package com.example.mzdoes.addtask;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView titleTask;
    private Button nextTaskButton, calendarButton, toDoButton;
    private ImageButton addTaskButton, settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //wire widgets
        titleTask = (TextView) findViewById(R.id.textView_titleTask);
        nextTaskButton = (Button) findViewById(R.id.button_nextTask);
        calendarButton = (Button) findViewById(R.id.button_calendar);
        toDoButton = (Button) findViewById(R.id.button_toDo);
        addTaskButton = (ImageButton) findViewById(R.id.imageButton_addTask);
        settingsButton = (ImageButton) findViewById(R.id.imageButton_settings);

        nextTaskButton.setOnClickListener(this);
        calendarButton.setOnClickListener(this);
        toDoButton.setOnClickListener(this);
        addTaskButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_nextTask:
                //go to current displaying task
                break;
            case R.id.button_calendar:
                //open calendar activity
                break;
            case R.id.button_toDo:
                Intent i = new Intent(MainActivity.this, ToDoActivity.class);
                startActivity(i);
            case R.id.imageButton_addTask:
                //open add task activity
                break;
            case R.id.imageButton_settings:
                //open settings
                break;
            default:
                Toast.makeText(this, "Please press a valid button.", Toast.LENGTH_SHORT).show();
        }
    }
}
